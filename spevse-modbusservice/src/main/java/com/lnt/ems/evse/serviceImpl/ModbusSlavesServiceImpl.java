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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.lnt.ems.evse.dao.ModbusSlavesDao;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesReadRegisters;
import com.lnt.ems.evse.entity.ModbusSlavesWriteRegisters;
import com.lnt.ems.evse.services.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ModbusSlavesServiceImpl implements ModbusSlavesService {

    private static final Logger logger = LoggerFactory.getLogger(ModbusSlavesServiceImpl.class);

    @Autowired
    private ModbusSlavesDao modbusSlavesDao;

    @Autowired
    private ModbusSlavesReadRegisterService readRegsiterService;

    @Autowired
    private ModbusSlavesWriteRegisterService writeRegsiterService;

    @Autowired
    private ModbusSlavesAssociatedAssetsService associatedAssetsService;

    @Autowired
    private ModbusAssetMeasReadRegisterService readMapServiceImpl;

    @Autowired
    private ModbusAssetMeasWriteRegisterService writeMapServiceImpl;

    @Override
    public List<ModbusSlaves> getAllModbusSlaves() {
        return (List<ModbusSlaves>) modbusSlavesDao.findAllByOrderByUpdatedDateDescCreatedDateDesc();
    }

    @Override
    public ModbusSlaves saveModbusSlaves(ModbusSlaves modbusSlaves) {

        return modbusSlavesDao.save(modbusSlaves);
    }

    @Override
    public ModbusSlaves cloneModbusSlaves(ModbusSlaves modbusSlaves, Integer cloneId) {
        logger.info("Clonning Server");
        ModbusSlaves cloneFrom = getModbusSlavesById(cloneId);
        modbusSlaves.setIsEnabled(cloneFrom.getIsEnabled());
        modbusSlaves.setDescription(cloneFrom.getDescription());
        modbusSlaves.setStatusDateTime(new Date());
        modbusSlaves.setCreatedDate(new Date());
        ModbusSlaves saved = modbusSlavesDao.save(modbusSlaves);

        logger.info("Clonning Read Registers");
        List<ModbusSlavesReadRegisters> newReadList = readRegsiterService.cloneModbusSlavesReadRegisters(saved,
                cloneId);

        logger.info("Clonning Write Registers");
        List<ModbusSlavesWriteRegisters> newWriteList = writeRegsiterService.cloneModbusSlavesWriteRegisters(saved,
                cloneId);

        logger.info("Clonning Associated Assets");
        associatedAssetsService.cloneModbusSlavesAssociatedAssets(saved, cloneId);

        logger.info("Clonning Read Map");
        readMapServiceImpl.cloneModbusSlavesReadRegisters(saved, cloneId, newReadList);


        logger.info("Clonning Write Map");
        writeMapServiceImpl.cloneModbusSlavesWriteRegisters(saved, cloneId, newWriteList);

        return saved;
    }

    @Override
    public ModbusSlaves getModbusSlavesById(Integer id) {
        Optional<ModbusSlaves> modbusSlaves = modbusSlavesDao.findById(id);
        if (modbusSlaves.isPresent()) {
            return modbusSlaves.get();
        }
        return null;
    }

    @Override
    public ModbusSlaves enableModbusSlavesById(Integer id) {
        ModbusSlaves modbusSlaves = getModbusSlavesById(id);
        if (modbusSlaves != null) {
            modbusSlaves.setUpdatedDate(new Date());
            if (modbusSlaves.getIsEnabled().equals("Y"))
                modbusSlaves.setIsEnabled("N");
            else
                modbusSlaves.setIsEnabled("Y");
            modbusSlaves = saveModbusSlaves(modbusSlaves);
        }
        return modbusSlaves;
    }

    @Override
    @Modifying
    @Transactional
    public void deleteModbusSlavesById(Integer id) {

        // delete read map
        logger.info("deleting read map");
        readMapServiceImpl.deleteBymodbusId(id);

        // delete write map
        logger.info("deleting write map");
        writeMapServiceImpl.deleteBymodbusId(id);

        // delete associates
        logger.info("deleting associated assets");
        associatedAssetsService.deleteByModbusSlaveServerId(id);

        // delete read Regsiter
        logger.info("deleting read registers");
        readRegsiterService.deleteReadRegistersByModbusSlavesId(id);

        // delete write Regsiter
        logger.info("deleting write registers");
        writeRegsiterService.deleteWriteRegistersByModbusSlavesId(id);

        logger.info("deleting server");
        modbusSlavesDao.deleteById(id);

    }

    @Override
    public String validateModbusSalve(ModbusSlaves modbusSlave, boolean IsEdit) {

        if (modbusSlave.getSlaveId() < 0 || modbusSlave.getSlaveId() > 255) {
            return "Slave ID value should be between 0 to 255.";
        }

        if (!IsEdit) {
            if (modbusSlave.getModbusSlaveId() != null && modbusSlavesDao.existsById(modbusSlave.getModbusSlaveId())) {
                // return "ID already exists";
                logger.error("ID already exists");
            }
            if (modbusSlavesDao.existsByIpAndSlaveIdAndPort(modbusSlave.getIp(), modbusSlave.getSlaveId(),
                    modbusSlave.getPort())) {
                return "IP Address, Port & Slave ID already exists for given modbus client configuration.";
            }

        } else {
            if (modbusSlavesDao.countByIpAndSlaveIdAndPort(modbusSlave.getIp(), modbusSlave.getSlaveId(),
                    modbusSlave.getPort(), modbusSlave.getModbusSlaveId()) > 0) {
                return "IP Address, Port & Slave ID already exists for given modbus client configuration.";
            }

        }

        return "valid";
    }

}
