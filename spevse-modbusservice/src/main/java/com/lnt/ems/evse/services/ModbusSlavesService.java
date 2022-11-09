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

public interface ModbusSlavesService {

    List<ModbusSlaves> getAllModbusSlaves();

    ModbusSlaves saveModbusSlaves(ModbusSlaves modbusSlaves);

    ModbusSlaves cloneModbusSlaves(ModbusSlaves modbusSlaves, Integer cloneId);

    ModbusSlaves getModbusSlavesById(Integer id);

    ModbusSlaves enableModbusSlavesById(Integer id);

    void deleteModbusSlavesById(Integer id);

    String validateModbusSalve(ModbusSlaves modbusSlave, boolean isEdit);


}
