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
import com.lnt.ems.evse.entity.ModbusSlavesWriteRegisters;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ModbusSlavesWriteRegisterDao extends CrudRepository<ModbusSlavesWriteRegisters, Integer> {

    List<ModbusSlavesWriteRegisters> findByModbusId(ModbusSlaves modbusId);

    @Transactional
    void deleteByModbusId(ModbusSlaves modbusId);

    @Query(value = "SELECT * FROM lntds_modbus_slave_write_registers\r\n"
            + "where modbus_slave_id = ?1 \r\n"
            + "and register = ?2 \r\n"
            + "and function_code = ?3 ", nativeQuery = true)
    List<ModbusSlavesWriteRegisters> findWriteRegister(Integer modbusId, Integer register, Integer functionCode);


    @Query(value = "select reg.*  from lntds_modbus_slave_write_registers reg\r\n"
            + "INNER JOIN lntds_modbus_asset_meas_writeregister_map map ON map.modbus_slave_id = reg.modbus_slave_id\r\n"
            + "where reg.id in (:ids) \r\n"
            + "and reg.id = map.modbus_slave_write\r\n"
            + "and reg.modbus_slave_id = map.modbus_slave_id ", nativeQuery = true)
    List<ModbusSlavesWriteRegisters> findByMap(List<Integer> ids);

    List<ModbusSlavesWriteRegisters> findByRegisterAndModbusId(Integer register, ModbusSlaves modbusSlavesById);
}
