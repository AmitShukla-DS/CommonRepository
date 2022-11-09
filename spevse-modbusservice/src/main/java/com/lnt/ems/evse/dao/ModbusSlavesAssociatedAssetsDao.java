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

import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesAssociatedAssets;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ModbusSlavesAssociatedAssetsDao extends CrudRepository<ModbusSlavesAssociatedAssets, Integer> {


    List<ModbusSlavesAssociatedAssets> findBySlaveServerId(ModbusSlaves slaveServerId);

    @Query(value = "SELECT * FROM lntds_modbus_slave_associated_assets where slave_server_id = ?1 and asset_id = ?2 ", nativeQuery = true)
    List<ModbusSlavesAssociatedAssets> findBySlaveServerIdAndAssetId(Integer modbusId, String assetid);

    @Transactional
    @Modifying
    @Query(value = "delete from lntds_modbus_slave_associated_assets where slave_server_id = ?1 ", nativeQuery = true)
    void deleteByServerId(Integer id);

    @Transactional
    @Modifying
    @Query(value = "delete from lntds_modbus_slave_associated_assets where id in (?1)", nativeQuery = true)
    void deleteByIdIn(List<Integer> ids);


    @Query(value = "select asset.*  from lntds_modbus_slave_associated_assets asset\r\n"
            + "	INNER JOIN lntds_modbus_asset_meas_readregister_map map ON map.modbus_slave_id = asset.slave_server_id\r\n"
            + "	where asset.id in (?1) \r\n"
            + "	and asset.asset_id = map.asset_id", nativeQuery = true)
    List<ModbusSlavesAssociatedAssets> findByReadMap(List<Integer> ids);


    @Query(value = "select asset.*  from lntds_modbus_slave_associated_assets asset\r\n"
            + "	INNER JOIN lntds_modbus_asset_meas_writeregister_map map ON map.modbus_slave_id = asset.slave_server_id\r\n"
            + "	where asset.id in (?1) \r\n"
            + "	and asset.asset_id = map.asset_id", nativeQuery = true)
    List<ModbusSlavesAssociatedAssets> findByWriteMap(List<Integer> ids);


}
