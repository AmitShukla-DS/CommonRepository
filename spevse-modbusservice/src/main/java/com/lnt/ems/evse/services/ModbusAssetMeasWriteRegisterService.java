/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.services;

import java.util.List;

import com.lnt.ems.evse.entity.ModbusAssetMeasWriteRegistersMap;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesWriteRegisters;

public interface ModbusAssetMeasWriteRegisterService {

    List<ModbusAssetMeasWriteRegistersMap> getAllModbusAssetMeasWriteRegisters();

    ModbusAssetMeasWriteRegistersMap saveModbusAssetMeasWriteRegisters(
            ModbusAssetMeasWriteRegistersMap assetMeasWriteRegisters);

    ModbusAssetMeasWriteRegistersMap getModbusAssetMeasWriteRegistersById(Integer id);

    void deleteModbusAssetMeasWriteRegistersById(Integer id);

    void deleteByAssociatedIdsIn(List<String> ids);

    void deleteBymodbusId(Integer id);

    List<ModbusAssetMeasWriteRegistersMap> saveAll(List<ModbusAssetMeasWriteRegistersMap> assetMeasWriteRegisters);

    List<ModbusAssetMeasWriteRegistersMap> getAllByModbusId(Integer id);

    void cloneModbusSlavesWriteRegisters(ModbusSlaves saved, Integer cloneId,
                                         List<ModbusSlavesWriteRegisters> newWriteList);

    List<ModbusAssetMeasWriteRegistersMap> updateByWriteRegister(ModbusSlavesWriteRegisters register);
}
