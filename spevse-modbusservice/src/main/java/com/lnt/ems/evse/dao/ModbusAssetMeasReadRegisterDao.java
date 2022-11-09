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

import com.lnt.ems.evse.entity.ModbusAssetMeasReadRegistersMap;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ModbusAssetMeasReadRegisterDao extends CrudRepository<ModbusAssetMeasReadRegistersMap, Integer> {

    @Query(value = "SELECT distinct CONCAT(a.asset_id,'.',a.measurement_name) AS meas FROM lntds_modbus_asset_meas_readregister_map a", nativeQuery = true)
    List<String> findMeasurements();

    @Transactional
    @Modifying
    @Query(value = "Delete FROM lntds_modbus_asset_meas_readregister_map where asset_id in (?1) ", nativeQuery = true)
    void deleteByAssetIdIn(List<String> assetIds);

    @Transactional
    @Modifying
    @Query(value = "delete FROM lntds_modbus_asset_meas_readregister_map where modbus_slave_id = ?1", nativeQuery = true)
    void deleteByModbusId(Integer id);

    List<ModbusAssetMeasReadRegistersMap> findByModbusId(Integer modbusId);

    List<ModbusAssetMeasReadRegistersMap> findByModbusIdAndAssetId(Integer modbusId, String assetId);

    @Query(value = "SELECT * FROM lntds_modbus_asset_meas_readregister_map \r\n"
            + "where modbus_slave_read = ?1 \r\n"
            + "and modbus_slave_id = ?2 ", nativeQuery = true)
    List<ModbusAssetMeasReadRegistersMap> findByModbusIdAndModbusSlavesReadRegisters(Integer read, Integer modbusId);

}
