/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.services;

import java.util.List;

import com.lnt.ems.evse.entity.ModbusAssetMeasReadRegistersMap;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesReadRegisters;

public interface ModbusAssetMeasReadRegisterService {

    List<ModbusAssetMeasReadRegistersMap> getAllModbusAssetMeasReadRegisters();

    ModbusAssetMeasReadRegistersMap saveModbusAssetMeasReadRegisters(
            ModbusAssetMeasReadRegistersMap assetMeasReadRegisters);

    ModbusAssetMeasReadRegistersMap getModbusAssetMeasReadRegistersById(Integer id);

    void deleteModbusAssetMeasReadRegistersById(Integer id);

    void deleteByAssociatedIdsIn(List<String> ids);

    void deleteBymodbusId(Integer id);

    List<ModbusAssetMeasReadRegistersMap> saveAll(List<ModbusAssetMeasReadRegistersMap> assetMeasReadRegisters);

    List<String> getUniqueModbusReadRegisterMeasurementList();

    List<ModbusAssetMeasReadRegistersMap> getAllByModbusId(Integer id);

    void cloneModbusSlavesReadRegisters(ModbusSlaves saved, Integer cloneId,
                                        List<ModbusSlavesReadRegisters> newReadList);

    List<ModbusAssetMeasReadRegistersMap> updateByReadRegister(ModbusSlavesReadRegisters register);

}
