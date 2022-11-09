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

import com.lnt.ems.evse.dao.ModbusSlavesReadRegisterDao;
import com.lnt.ems.evse.entity.ModbusAssetMeasReadRegistersMap;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesReadRegisters;
import com.lnt.ems.evse.services.ModbusAssetMeasReadRegisterService;
import com.lnt.ems.evse.services.ModbusSlavesReadRegisterService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class ModbusSlavesReadRegisterServiceImpl implements ModbusSlavesReadRegisterService {

    private static final Logger logger = LoggerFactory.getLogger(ModbusSlavesReadRegisterServiceImpl.class);

    @Autowired
    ModbusSlavesReadRegisterDao modbusSlavesReadRegisterDao;

    @Autowired
    private ModbusSlavesServiceImpl modbusSlavesServiceImpl;

    @Autowired
    private ModbusAssetMeasReadRegisterService readMapService;

    @Override
    public List<ModbusSlavesReadRegisters> getAllModbusSlavesReadRegisters() {
        return (List<ModbusSlavesReadRegisters>) modbusSlavesReadRegisterDao.findAll();
    }

    @Override
    public ModbusSlavesReadRegisters saveModbusSlavesReadRegisters(
            ModbusSlavesReadRegisters modbusSlavesReadRegisters) {
        ModbusSlavesReadRegisters saved = modbusSlavesReadRegisterDao.save(modbusSlavesReadRegisters);
        readMapService.updateByReadRegister(saved);
        return saved;
    }

    @Override
    public ModbusSlavesReadRegisters getModbusSlavesReadRegistersById(Integer id) {
        Optional<ModbusSlavesReadRegisters> modbusSlaves = modbusSlavesReadRegisterDao.findById(id);
        if (modbusSlaves.isPresent()) {
            return modbusSlaves.get();
        }
        return null;
    }

    @Override
    public void deleteModbusSlavesReadRegistersById(List<Integer> ids) {
        for (Integer id : ids)
            modbusSlavesReadRegisterDao.deleteById(id);
    }

    @Override
    public boolean validateMapping(Integer id) {

        boolean valid = false;

        List<ModbusSlavesReadRegisters> readRegisters = getAllModbusReadRegistersBySlaveId(id);
        List<Integer> ids = readRegisters.stream().map(ModbusSlavesReadRegisters::getId).collect(Collectors.toList());
        if (ids.size() > 0) {
            List<ModbusSlavesReadRegisters> reg = modbusSlavesReadRegisterDao.findByMap(ids);
            if (reg.size() == 0) {
                valid = true;
            }
        } else if (ids.size() == 0) {
            valid = true;
        }
        return valid;
    }

    @Override
    public boolean validateMapping(ModbusSlavesReadRegisters readRegister) {

        boolean valid = false;

        if (readRegister.getId() == null) {
            return true;
        }

        List<Integer> ids = new ArrayList<Integer>();
        ids.add(readRegister.getId());
        if (ids.size() > 0) {
            List<ModbusSlavesReadRegisters> reg = modbusSlavesReadRegisterDao.findByMap(ids);
            if (reg.size() == 0) {
                valid = true;
            }
        }
        return valid;
    }

    @Override
    public boolean validateMapping(List<Integer> readids) {

        boolean valid = false;

        List<ModbusSlavesReadRegisters> reg = modbusSlavesReadRegisterDao.findByMap(readids);
        if (reg.size() == 0) {
            valid = true;
        }

        return valid;
    }

    @Override
    public void deleteReadRegistersByModbusSlavesId(Integer id) {
        ModbusSlaves modbus = modbusSlavesServiceImpl.getModbusSlavesById(id);
        modbusSlavesReadRegisterDao.deleteByModbusId(modbus);
    }

    @Override
    public List<ModbusSlavesReadRegisters> getAllModbusReadRegistersBySlaveId(Integer id) {
        return modbusSlavesReadRegisterDao.findByModbusId(modbusSlavesServiceImpl.getModbusSlavesById(id));
    }

    @Override
    public List<ModbusSlavesReadRegisters> saveAll(List<ModbusSlavesReadRegisters> registers) {

        List<ModbusSlavesReadRegisters> newList = new ArrayList<ModbusSlavesReadRegisters>();

        for (ModbusSlavesReadRegisters register : registers) {

            // composite key register
            // Register, function code, binary read, bit register
            List<ModbusSlavesReadRegisters> checkList = modbusSlavesReadRegisterDao.findReadRegister(
                    register.getModbusId().getModbusSlaveId(), register.getBinaryRead(), register.getRegister(),
                    register.getFunctionCode(), register.getBitRegister());

            if (checkList.size() > 0) {
                ModbusSlavesReadRegisters oldReg = checkList.get(0);
                oldReg.setSize(register.getSize());
                oldReg.setScalingFactor(register.getScalingFactor());
                oldReg.setCommFlag(register.getCommFlag());
                oldReg.setCommValue(register.getCommValue());
                oldReg.setDescription(register.getDescription());
                newList.add(saveModbusSlavesReadRegisters(oldReg));

            }
            if (checkList.size() == 0) {
                newList.add(saveModbusSlavesReadRegisters(register));
            }

        }

        return (List<ModbusSlavesReadRegisters>) modbusSlavesReadRegisterDao.saveAll(newList);
    }

    @Override
    public List<ModbusSlavesReadRegisters> cloneModbusSlavesReadRegisters(ModbusSlaves savedModbus, Integer cloneId) {

        List<ModbusSlavesReadRegisters> registerList = getAllModbusReadRegistersBySlaveId(cloneId);
        List<ModbusSlavesReadRegisters> newList = new ArrayList<ModbusSlavesReadRegisters>();

        for (ModbusSlavesReadRegisters register : registerList) {
            ModbusSlavesReadRegisters newObj = new ModbusSlavesReadRegisters();

            newObj.setId(null);
            newObj.setRegister(register.getRegister());
            newObj.setFunctionCode(register.getFunctionCode());
            newObj.setBitRegister(register.getBitRegister());
            newObj.setModbusId(savedModbus);
            newObj.setBinaryRead(register.getBinaryRead());
            newObj.setCommFlag(register.getCommFlag());
            newObj.setCommValue(register.getCommValue());
            newObj.setDescription(register.getDescription());
            newObj.setMask(register.getMask());
            newObj.setScalingFactor(register.getScalingFactor());
            newObj.setSize(register.getSize());

            newList.add(newObj);

        }

        return saveAll(newList);
    }

    public boolean checkForRegister() {

        return false;
    }

    public ModbusSlavesReadRegisters findByRegisterAndModbusId(ModbusAssetMeasReadRegistersMap map, Integer modbusid) {
        List<ModbusSlavesReadRegisters> registerList = modbusSlavesReadRegisterDao.findReadRegister(modbusid,
                map.getBinaryRead(), map.getRegister(), map.getFunctionCode(), map.getBitRegister());
        if (registerList.size() == 1)
            return registerList.get(0);

        return null;
    }

    public ModbusSlavesReadRegisters findReadRegisterByFunction(ModbusAssetMeasReadRegistersMap map, Integer modbusid) {
        List<ModbusSlavesReadRegisters> registerList = modbusSlavesReadRegisterDao.findReadRegisterByFunction(modbusid,
                map.getRegister(), map.getFunctionCode());
        if (registerList.size() > 0)
            return registerList.get(0);

        return null;
    }

    public ModbusSlavesReadRegisters findReadRegisterByBit(ModbusAssetMeasReadRegistersMap map, Integer modbusid) {
        List<ModbusSlavesReadRegisters> registerList = modbusSlavesReadRegisterDao.findReadRegisterByBit(modbusid,
                map.getBitRegister(), map.getRegister());
        if (registerList.size() > 0)
            return registerList.get(0);

        return null;
    }

    @Override
    public List<ModbusSlavesReadRegisters> getAllByRegistersAndSlaveId(Integer register, Integer modbusId) {
        return modbusSlavesReadRegisterDao.findByRegisterAndModbusId(register,
                modbusSlavesServiceImpl.getModbusSlavesById(modbusId));
    }

}
