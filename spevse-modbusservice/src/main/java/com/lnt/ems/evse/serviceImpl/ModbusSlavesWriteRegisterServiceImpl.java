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

import com.lnt.ems.evse.dao.ModbusSlavesWriteRegisterDao;
import com.lnt.ems.evse.entity.ModbusAssetMeasWriteRegistersMap;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesWriteRegisters;
import com.lnt.ems.evse.services.ModbusAssetMeasWriteRegisterService;
import com.lnt.ems.evse.services.ModbusSlavesWriteRegisterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModbusSlavesWriteRegisterServiceImpl implements ModbusSlavesWriteRegisterService {

    private static final Logger logger = LoggerFactory.getLogger(ModbusSlavesWriteRegisterServiceImpl.class);

    @Autowired
    ModbusSlavesWriteRegisterDao modbusSlavesWriteRegisterDao;

    @Autowired
    private ModbusSlavesServiceImpl modbusSlavesServiceImpl;

    @Autowired
    ModbusAssetMeasWriteRegisterService writeMapService;

    @Override
    public List<ModbusSlavesWriteRegisters> getAllModbusSlavesWriteRegisters() {
        return (List<ModbusSlavesWriteRegisters>) modbusSlavesWriteRegisterDao.findAll();
    }

    @Override
    public ModbusSlavesWriteRegisters saveModbusSlavesWriteRegisters(
            ModbusSlavesWriteRegisters modbusSlavesWriteRegisters) {
        ModbusSlavesWriteRegisters saved = modbusSlavesWriteRegisterDao.save(modbusSlavesWriteRegisters);
        writeMapService.updateByWriteRegister(saved);
        return saved;
    }

    @Override
    public ModbusSlavesWriteRegisters getModbusSlavesWriteRegistersById(Integer id) {
        Optional<ModbusSlavesWriteRegisters> modbusSlaves = modbusSlavesWriteRegisterDao.findById(id);
        if (modbusSlaves.isPresent()) {
            return modbusSlaves.get();
        }
        return null;
    }

    @Override
    public void deleteModbusSlavesWriteRegistersById(List<Integer> ids) {
        for (Integer id : ids)
            modbusSlavesWriteRegisterDao.deleteById(id);
    }

    @Override
    public void deleteWriteRegistersByModbusSlavesId(Integer id) {
        ModbusSlaves modbus = modbusSlavesServiceImpl.getModbusSlavesById(id);
        modbusSlavesWriteRegisterDao.deleteByModbusId(modbus);
    }

    @Override
    public List<ModbusSlavesWriteRegisters> getAllModbusWriteRegistersBySlaveId(Integer id) {
        return modbusSlavesWriteRegisterDao.findByModbusId(modbusSlavesServiceImpl.getModbusSlavesById(id));
    }

    @Override
    public List<ModbusSlavesWriteRegisters> saveAll(List<ModbusSlavesWriteRegisters> registers) {
        List<ModbusSlavesWriteRegisters> newList = new ArrayList<ModbusSlavesWriteRegisters>();

        for (ModbusSlavesWriteRegisters register : registers) {

            // composite key register
            // Register, function code, binary read, bit register
            List<ModbusSlavesWriteRegisters> checkList = modbusSlavesWriteRegisterDao.findWriteRegister(
                    register.getModbusId().getModbusSlaveId(), register.getRegister(), register.getFunctionCode());

            if (checkList.size() > 0) {
                ModbusSlavesWriteRegisters oldReg = checkList.get(0);
                oldReg.setSize(register.getSize());
                oldReg.setScalingFactor(register.getScalingFactor());
                oldReg.setRoundingEnable(register.getRoundingEnable());
                oldReg.setRoundingMechanism(register.getRoundingMechanism());
                oldReg.setDescription(register.getDescription());
                newList.add(saveModbusSlavesWriteRegisters(oldReg));

            }
            if (checkList.size() == 0) {
                newList.add(saveModbusSlavesWriteRegisters(register));
            }

        }

        return (List<ModbusSlavesWriteRegisters>) modbusSlavesWriteRegisterDao.saveAll(newList);
    }

    public String[] validateWriteRegisters(List<ModbusSlavesWriteRegisters> registers) {
        String[] asd = { "Valid ", "true" };
        boolean isValid = true;
        for (ModbusSlavesWriteRegisters register : registers) {
            for (ModbusSlavesWriteRegisters registerList : registers) {
                if (registerList != register) {
                    if (register.getRegister().equals(registerList.getRegister())
                            || register.getFunctionCode().equals(registerList.getFunctionCode())) {
                        isValid = false;
                        break;
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
    public List<ModbusSlavesWriteRegisters> cloneModbusSlavesWriteRegisters(ModbusSlaves savedModbus, Integer cloneId) {
        List<ModbusSlavesWriteRegisters> registerList = getAllModbusWriteRegistersBySlaveId(cloneId);
        List<ModbusSlavesWriteRegisters> newList = new ArrayList<ModbusSlavesWriteRegisters>();

        for (ModbusSlavesWriteRegisters register : registerList) {
            ModbusSlavesWriteRegisters newObj = new ModbusSlavesWriteRegisters();

            newObj.setId(null);
            newObj.setModbusId(savedModbus);
            newObj.setRegister(register.getRegister());
            newObj.setFunctionCode(register.getFunctionCode());
            newObj.setRoundingEnable(register.getRoundingEnable());
            newObj.setRoundingMechanism(register.getRoundingMechanism());
            newObj.setDescription(register.getDescription());
            newObj.setScalingFactor(register.getScalingFactor());
            newObj.setSize(register.getSize());
            newList.add(newObj);

        }

        return saveAll(newList);
    }

    public ModbusSlavesWriteRegisters findByRegisterAndModbusId(ModbusAssetMeasWriteRegistersMap dto,
                                                                Integer modbusId) {

        List<ModbusSlavesWriteRegisters> registerList = modbusSlavesWriteRegisterDao.findWriteRegister(modbusId,
                dto.getRegister(), dto.getFunctionCode());
        if (registerList.size() == 1)
            return registerList.get(0);

        return null;
    }

    @Override
    public boolean validateMapping(Integer id) {
        boolean valid = false;

        List<ModbusSlavesWriteRegisters> writeRegisters = getAllModbusWriteRegistersBySlaveId(id);
        List<Integer> ids = writeRegisters.stream().map(ModbusSlavesWriteRegisters::getId).collect(Collectors.toList());
        if (ids.size() > 0) {
            List<ModbusSlavesWriteRegisters> reg = modbusSlavesWriteRegisterDao.findByMap(ids);
            if (reg.size() == 0) {
                valid = true;
            }
        } else if (ids.size() == 0) {
            valid = true;
        }

        return valid;
    }

    @Override
    public boolean validateMapping(ModbusSlavesWriteRegisters writeRegister) {
        boolean valid = false;

        if (writeRegister.getId() == null) {
            return true;
        }

        List<Integer> ids = new ArrayList<Integer>();
        ids.add(writeRegister.getId());
        if (ids.size() > 0) {
            List<ModbusSlavesWriteRegisters> reg = modbusSlavesWriteRegisterDao.findByMap(ids);
            if (reg.size() == 0) {
                valid = true;
            }
        }

        return valid;
    }

    @Override
    public boolean validateMapping(List<Integer> writeids) {
        boolean valid = false;

        List<ModbusSlavesWriteRegisters> reg = modbusSlavesWriteRegisterDao.findByMap(writeids);
        if (reg.size() == 0) {
            valid = true;
        }

        return valid;
    }

    @Override
    public List<ModbusSlavesWriteRegisters> getAllByRegistersAndSlaveId(Integer register, Integer modbusId) {
        return modbusSlavesWriteRegisterDao.findByRegisterAndModbusId(register,
                modbusSlavesServiceImpl.getModbusSlavesById(modbusId));
    }
}
