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
import com.lnt.ems.evse.entity.ModbusSlavesWriteRegisters;

public interface ModbusSlavesWriteRegisterService {

    List<ModbusSlavesWriteRegisters> getAllModbusSlavesWriteRegisters();

    ModbusSlavesWriteRegisters saveModbusSlavesWriteRegisters(ModbusSlavesWriteRegisters modbusSlavesWriteRegisters);

    List<ModbusSlavesWriteRegisters> cloneModbusSlavesWriteRegisters(ModbusSlaves savedModbus, Integer cloneId);

    List<ModbusSlavesWriteRegisters> saveAll(List<ModbusSlavesWriteRegisters> modbusSlavesWriteRegisters);

    ModbusSlavesWriteRegisters getModbusSlavesWriteRegistersById(Integer id);

    void deleteModbusSlavesWriteRegistersById(List<Integer> id);

    void deleteWriteRegistersByModbusSlavesId(Integer id);

    List<ModbusSlavesWriteRegisters> getAllModbusWriteRegistersBySlaveId(Integer id);

    List<ModbusSlavesWriteRegisters> getAllByRegistersAndSlaveId(Integer register, Integer modbusId);

    boolean validateMapping(Integer id);

    boolean validateMapping(List<Integer> ids);

    boolean validateMapping(ModbusSlavesWriteRegisters readRegister);


}
