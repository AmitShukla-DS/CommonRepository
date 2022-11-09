/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnt.ems.evse.dao.ModbusAssetMeasReadRegisterDao;
import com.lnt.ems.evse.entity.ModbusAssetMeasReadRegistersMap;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesReadRegisters;
import com.lnt.ems.evse.services.ModbusAssetMeasReadRegisterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModbusAssetMeasReadRegisterServiceImpl implements ModbusAssetMeasReadRegisterService {

    private static final Logger logger = LoggerFactory.getLogger(ModbusAssetMeasReadRegisterServiceImpl.class);

    @Autowired
    ModbusAssetMeasReadRegisterDao assetMeasReadRegisterDao;

    @Override
    public List<ModbusAssetMeasReadRegistersMap> getAllModbusAssetMeasReadRegisters() {
        return (List<ModbusAssetMeasReadRegistersMap>) assetMeasReadRegisterDao.findAll();
    }

    @Override
    public ModbusAssetMeasReadRegistersMap saveModbusAssetMeasReadRegisters(
            ModbusAssetMeasReadRegistersMap assetMeasReadRegisters) {
        return assetMeasReadRegisterDao.save(assetMeasReadRegisters);

    }

    @Override
    public List<ModbusAssetMeasReadRegistersMap> saveAll(List<ModbusAssetMeasReadRegistersMap> assetMeasReadRegisters) {
        try {
            assetMeasReadRegisterDao.saveAll(assetMeasReadRegisters);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;

    }

    @Override
    public ModbusAssetMeasReadRegistersMap getModbusAssetMeasReadRegistersById(Integer id) {
        Optional<ModbusAssetMeasReadRegistersMap> modbusSlaves = assetMeasReadRegisterDao.findById(id);
        if (modbusSlaves.isPresent()) {
            return modbusSlaves.get();
        }
        return null;
    }

    @Override
    public void deleteModbusAssetMeasReadRegistersById(Integer id) {
        assetMeasReadRegisterDao.deleteById(id);
    }

    public String[] validateAssetMeasReadRegisters(List<ModbusAssetMeasReadRegistersMap> registers) {
        String[] asd = {"Valid ", "true"};
        boolean isValid = true;
        for (ModbusAssetMeasReadRegistersMap register : registers) {

            for (ModbusAssetMeasReadRegistersMap registerList : registers) {
                if (registerList != register) {
                    if (register.getBinaryRead().equals("N")) {
                        if (register.getRegister().equals(registerList.getRegister())
                                || register.getFunctionCode().equals(registerList.getFunctionCode())) {
                            isValid = false;
                            break;
                        }
                    } else {

                        if (register.getRegister().equals(registerList.getRegister())
                                || register.getFunctionCode().equals(registerList.getFunctionCode())
                                || register.getMask().equals(registerList.getMask())) {
                            isValid = false;
                            break;
                        }

                    }

                }
            }

            if (!isValid) {
                asd[0] = "Validation falied for register " + register.getRegister();
                asd[1] = "false";
                break;
            }
        }

        return asd;
    }

    @Override
    public List<String> getUniqueModbusReadRegisterMeasurementList() {
        return assetMeasReadRegisterDao.findMeasurements();
    }

    @Override
    public void deleteByAssociatedIdsIn(List<String> ids) {
        assetMeasReadRegisterDao.deleteByAssetIdIn(ids);
    }

    @Override
    public void deleteBymodbusId(Integer id) {
        assetMeasReadRegisterDao.deleteByModbusId(id);
    }

    @Override
    public List<ModbusAssetMeasReadRegistersMap> getAllByModbusId(Integer id) {

        return assetMeasReadRegisterDao.findByModbusId(id);
    }

    public List<ModbusAssetMeasReadRegistersMap> findByModbusIdAndAssetId(Integer id, String assetId) {

        return assetMeasReadRegisterDao.findByModbusIdAndAssetId(id, assetId);
    }

    public void cloneModbusSlavesReadRegisters(ModbusSlaves saved, Integer cloneId,
                                               List<ModbusSlavesReadRegisters> readList) {

        List<ModbusAssetMeasReadRegistersMap> mapList = getAllByModbusId(cloneId);

        List<ModbusAssetMeasReadRegistersMap> newMapList = new ArrayList<ModbusAssetMeasReadRegistersMap>();

        for (ModbusAssetMeasReadRegistersMap map : mapList) {

            for (ModbusSlavesReadRegisters read : readList) {

                if (map.getRegister().equals(read.getRegister()) && map.getModbusId().equals(cloneId)
                        && map.getFunctionCode().equals(read.getFunctionCode())
                        && map.getBitRegister().equals(read.getBitRegister())
                        && map.getBinaryRead().equals(read.getBinaryRead())) {

                    ModbusAssetMeasReadRegistersMap newObj = new ModbusAssetMeasReadRegistersMap();
                    newObj.setId(null);
                    newObj.setAssetId(map.getAssetId());
                    newObj.setBinaryRead(map.getBinaryRead());
                    newObj.setBitRegister(map.getBitRegister());
                    newObj.setCommFlag(map.getCommFlag());
                    newObj.setCommValue(map.getCommValue());
                    newObj.setDescription(map.getDescription());
                    newObj.setFunctionCode(map.getFunctionCode());
                    newObj.setMask(null);
                    newObj.setRegister(read.getRegister());
                    newObj.setMeasurementName(map.getMeasurementName());
                    newObj.setModbusId(saved.getModbusSlaveId());
                    newObj.setModbusSlavesReadRegisters(read);
                    newObj.setScalingFactor(map.getScalingFactor());
                    newObj.setSize(map.getSize());
                    newMapList.add(newObj);

                }
            }
        }
        saveAll(newMapList);
    }

    @Override
    public List<ModbusAssetMeasReadRegistersMap> updateByReadRegister(ModbusSlavesReadRegisters register) {

        List<ModbusAssetMeasReadRegistersMap> mapList = assetMeasReadRegisterDao
                .findByModbusIdAndModbusSlavesReadRegisters(register.getId(),
                        register.getModbusId().getModbusSlaveId());

        for (ModbusAssetMeasReadRegistersMap map : mapList) {

            map.setBinaryRead(register.getBinaryRead());
            map.setBitRegister(register.getBitRegister());
            map.setCommFlag(register.getCommFlag());
            map.setCommValue(register.getCommValue());
            map.setDescription(register.getDescription());
            map.setFunctionCode(register.getFunctionCode());
            map.setRegister(register.getRegister());
            map.setScalingFactor(register.getScalingFactor());
            map.setSize(register.getSize());

        }
        return (List<ModbusAssetMeasReadRegistersMap>) assetMeasReadRegisterDao.saveAll(mapList);
    }

}
