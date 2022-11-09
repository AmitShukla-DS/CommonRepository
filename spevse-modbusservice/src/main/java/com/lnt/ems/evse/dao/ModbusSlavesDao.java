/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lnt.ems.evse.entity.ModbusSlaves;

import java.util.List;

@Repository
public interface ModbusSlavesDao extends CrudRepository<ModbusSlaves, Integer> {

    List<ModbusSlaves> findAllByOrderByUpdatedDateDescCreatedDateDesc();


    Boolean existsByName(String name);

    Boolean existsByIpAndSlaveIdAndPort(String ip, Integer slaveId, String port);

    @Query("Select Count(ms.modbusSlaveId) from ModbusSlaves ms where ms.name =:name and not ms.modbusSlaveId = :id ")
    Integer existsByName(String name, Integer id);

    @Query("Select Count(ms.modbusSlaveId) from ModbusSlaves ms where ms.ip = ?1 \r\n"
            + "and ms.slaveId = ?2 \r\n"
            + "and ms.port = ?3 \r\n"
            + "and not ms.modbusSlaveId = ?4")
    Integer countByIpAndSlaveIdAndPort(String ip, Integer slaveId, String port, Integer id);

}
