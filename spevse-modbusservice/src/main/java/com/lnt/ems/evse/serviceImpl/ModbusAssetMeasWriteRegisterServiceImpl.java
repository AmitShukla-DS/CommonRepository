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

import com.lnt.ems.evse.dao.ModbusAssetMeasWriteRegisterDao;
import com.lnt.ems.evse.entity.ModbusAssetMeasWriteRegistersMap;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesWriteRegisters;
import com.lnt.ems.evse.services.ModbusAssetMeasWriteRegisterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModbusAssetMeasWriteRegisterServiceImpl implements ModbusAssetMeasWriteRegisterService {

    private static final Logger logger = LoggerFactory.getLogger(ModbusAssetMeasWriteRegisterServiceImpl.class);

    @Autowired
    ModbusAssetMeasWriteRegisterDao assetMeasWriteRegisterDao;

    @Override
    public List<ModbusAssetMeasWriteRegistersMap> getAllModbusAssetMeasWriteRegisters() {
        return (List<ModbusAssetMeasWriteRegistersMap>) assetMeasWriteRegisterDao.findAll();
    }

    @Override
    public ModbusAssetMeasWriteRegistersMap saveModbusAssetMeasWriteRegisters(
            ModbusAssetMeasWriteRegistersMap assetMeasWriteRegisters) {
        return assetMeasWriteRegisterDao.save(assetMeasWriteRegisters);

    }

    @Override
    public List<ModbusAssetMeasWriteRegistersMap> saveAll(
            List<ModbusAssetMeasWriteRegistersMap> assetMeasWriteRegisters) {
        return (List<ModbusAssetMeasWriteRegistersMap>) assetMeasWriteRegisterDao.saveAll(assetMeasWriteRegisters);

    }

    @Override
    public ModbusAssetMeasWriteRegistersMap getModbusAssetMeasWriteRegistersById(Integer id) {
        Optional<ModbusAssetMeasWriteRegistersMap> modbusSlaves = assetMeasWriteRegisterDao.findById(id);
        if (modbusSlaves.isPresent()) {
            return modbusSlaves.get();
        }
        return null;
    }

    @Override
    public void deleteModbusAssetMeasWriteRegistersById(Integer id) {
        assetMeasWriteRegisterDao.deleteById(id);
    }

    @Override
    public void deleteByAssociatedIdsIn(List<String> ids) {
        assetMeasWriteRegisterDao.deleteByAssetIdIn(ids);

    }

    @Override
    public List<ModbusAssetMeasWriteRegistersMap> getAllByModbusId(Integer id) {
        return assetMeasWriteRegisterDao.findByModbusId(id);
    }

    public List<ModbusAssetMeasWriteRegistersMap> findByModbusIdAndAssetId(Integer id, String assetId) {

        return assetMeasWriteRegisterDao.findByModbusIdAndAssetId(id, assetId);
    }

    @Override
    public void deleteBymodbusId(Integer id) {
        assetMeasWriteRegisterDao.deleteByModbusId(id);
    }

    public void cloneModbusSlavesWriteRegisters(ModbusSlaves saved, Integer cloneId,
                                                List<ModbusSlavesWriteRegisters> writeList) {

        List<ModbusAssetMeasWriteRegistersMap> mapList = getAllByModbusId(cloneId);
        List<ModbusAssetMeasWriteRegistersMap> newMapList = new ArrayList<ModbusAssetMeasWriteRegistersMap>();

        for (ModbusAssetMeasWriteRegistersMap map : mapList) {

            for (ModbusSlavesWriteRegisters write : writeList) {
                if (map.getRegister().equals(write.getRegister()) && map.getModbusId().equals(cloneId)
                        && map.getFunctionCode().equals(write.getFunctionCode())
                        && map.getRoundingEnable().equals(write.getRoundingEnable())
                        && map.getRoundingMechanism().equals(write.getRoundingMechanism())) {
                    ModbusAssetMeasWriteRegistersMap newObj = new ModbusAssetMeasWriteRegistersMap();
                    newObj.setId(null);
                    newObj.setAssetId(map.getAssetId());
                    newObj.setDescription(map.getDescription());
                    newObj.setFunctionCode(map.getFunctionCode());
                    newObj.setRegister(write.getRegister());
                    newObj.setMeasurementName(map.getMeasurementName());
                    newObj.setModbusId(saved.getModbusSlaveId());
                    newObj.setModbusSlavesWriteRegisters(write);
                    newObj.setScalingFactor(map.getScalingFactor());
                    newObj.setRoundingEnable(map.getRoundingEnable());
                    newObj.setRoundingMechanism(map.getRoundingMechanism());
                    newMapList.add(newObj);

                }
            }
        }
        saveAll(newMapList);

    }

    @Override
    public List<ModbusAssetMeasWriteRegistersMap> updateByWriteRegister(ModbusSlavesWriteRegisters register) {

        List<ModbusAssetMeasWriteRegistersMap> mapList = assetMeasWriteRegisterDao
                .findByModbusIdAndModbusSlavesWriteRegisters(register.getId(),
                        register.getModbusId().getModbusSlaveId());

        for (ModbusAssetMeasWriteRegistersMap map : mapList) {

            map.setDescription(register.getDescription());
            map.setFunctionCode(register.getFunctionCode());
            map.setRegister(register.getRegister());
            map.setScalingFactor(register.getScalingFactor());
            map.setSize(register.getSize());
            map.setRoundingEnable(register.getRoundingEnable());
            map.setRoundingMechanism(register.getRoundingMechanism());

        }
        return (List<ModbusAssetMeasWriteRegistersMap>) assetMeasWriteRegisterDao.saveAll(mapList);
    }

}
