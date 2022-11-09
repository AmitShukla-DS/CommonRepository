/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.services;

import java.util.List;

import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesReadRegisters;

public interface ModbusSlavesReadRegisterService {

    List<ModbusSlavesReadRegisters> getAllModbusSlavesReadRegisters();

    ModbusSlavesReadRegisters saveModbusSlavesReadRegisters(ModbusSlavesReadRegisters modbusSlavesReadRegisters);

    List<ModbusSlavesReadRegisters> cloneModbusSlavesReadRegisters(ModbusSlaves savedModbus, Integer cloneId);

    ModbusSlavesReadRegisters getModbusSlavesReadRegistersById(Integer id);

    void deleteModbusSlavesReadRegistersById(List<Integer> id);

    List<ModbusSlavesReadRegisters> getAllModbusReadRegistersBySlaveId(Integer id);

    List<ModbusSlavesReadRegisters> getAllByRegistersAndSlaveId(Integer register, Integer modbusId);

    List<ModbusSlavesReadRegisters> saveAll(List<ModbusSlavesReadRegisters> modbusSlavesReadRegisters);

    void deleteReadRegistersByModbusSlavesId(Integer id);

    boolean validateMapping(Integer id);

    boolean validateMapping(ModbusSlavesReadRegisters readRegister);

    boolean validateMapping(List<Integer> ids);


}
