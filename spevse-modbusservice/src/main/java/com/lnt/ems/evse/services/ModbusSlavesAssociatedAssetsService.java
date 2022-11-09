/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.services;

import java.util.List;

import com.lnt.ems.evse.dto.AssetAndTypeDto;
import com.lnt.ems.evse.dto.ModbusSlavesAssociatedAssetsDto;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesAssociatedAssets;

public interface ModbusSlavesAssociatedAssetsService {

    List<ModbusSlavesAssociatedAssets> getAllModbusSlavesAssociatedAssets();

    ModbusSlavesAssociatedAssets saveModbusSlavesAssociatedAssets(
            ModbusSlavesAssociatedAssetsDto modbusSlavesDto);

    List<ModbusSlavesAssociatedAssets> saveAll(ModbusSlavesAssociatedAssetsDto modbusSlavesDto);

    List<AssetAndTypeDto> getAllByModbusSlaveServer(Integer id);

    ModbusSlavesAssociatedAssets getById(Integer id);

    void deleteModbusSlavesAssociatedAssetsById(Integer id);

    void deleteModbusSlavesAssociatedAssetsByIds(List<Integer> ids);

    void deleteByModbusSlaveServerId(Integer id);

    List<ModbusSlavesAssociatedAssets> cloneModbusSlavesAssociatedAssets(ModbusSlaves savedModbus, Integer cloneId);

    boolean validateAssociation(List<Integer> ids);

}
