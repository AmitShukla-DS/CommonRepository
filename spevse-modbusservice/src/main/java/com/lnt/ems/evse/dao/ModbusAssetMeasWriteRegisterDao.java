/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lnt.ems.evse.entity.ModbusAssetMeasWriteRegistersMap;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ModbusAssetMeasWriteRegisterDao extends CrudRepository<ModbusAssetMeasWriteRegistersMap, Integer> {

    @Transactional
    @Modifying
    @Query(value = "Delete FROM lntds_modbus_asset_meas_writeregister_map a where a.asset_id in (?1) ", nativeQuery = true)
    void deleteByAssetIdIn(List<String> assetIds);

    List<ModbusAssetMeasWriteRegistersMap> findByModbusId(Integer modbusId);

    List<ModbusAssetMeasWriteRegistersMap> findByModbusIdAndAssetId(Integer id, String assetId);

    @Transactional
    @Modifying
    @Query(value = "delete FROM lntds_modbus_asset_meas_writeregister_map where modbus_slave_id = ?1", nativeQuery = true)
    void deleteByModbusId(Integer id);

    @Query(value = "SELECT * FROM lntds_modbus_asset_meas_writeregister_map\r\n"
            + "where modbus_slave_write = ?1 \r\n"
            + "and modbus_slave_id = ?2 ;", nativeQuery = true)
    List<ModbusAssetMeasWriteRegistersMap> findByModbusIdAndModbusSlavesWriteRegisters(Integer write, Integer modbusId);

}
