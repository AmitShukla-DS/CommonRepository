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
import com.lnt.ems.evse.entity.ModbusSlavesReadRegisters;

import java.util.List;

@Repository
public interface ModbusSlavesReadRegisterDao extends CrudRepository<ModbusSlavesReadRegisters, Integer> {

    List<ModbusSlavesReadRegisters> findByModbusId(ModbusSlaves modbusId);

    void deleteByModbusId(ModbusSlaves modbusId);

    @Query(value = "select id from lntds_modbus_slave_read_registers where id = ?1 and modbus_slave_id = ?2", nativeQuery = true)
    List<ModbusSlavesReadRegisters> findByIdAndModbusId(Integer registerId, Integer modbusid);

//	@Query(value = "select * from lntds_modbus_slave_read_registers where register = ?1 and modbus_slave_id = ?2" , nativeQuery = true)
//	List<ModbusSlavesReadRegisters> findByRegisterAndModbusId(Integer register ,Integer modbusid);

    @Query(value = "SELECT * FROM lntds_modbus_slave_read_registers\r\n"
            + "where modbus_slave_id = ?1 \r\n"
            + "and binary_read = ?2 \r\n"
            + "and register = ?3 \r\n"
            + "and function_code = ?4 "
            + "and bit_register = ?5 \r\n", nativeQuery = true)
    List<ModbusSlavesReadRegisters> findReadRegister(Integer modbusid, String binaryRead, Integer register, Integer functionCode, String bit);

    @Query(value = "SELECT * FROM lntds_modbus_slave_read_registers\r\n"
            + "where modbus_slave_id = ?1 \r\n"
            + "and register = ?2 \r\n"
            + "and function_code = ?3 ", nativeQuery = true)
    List<ModbusSlavesReadRegisters> findReadRegisterByFunction(Integer modbusid, Integer register, Integer functionCode);

    @Query(value = "SELECT * FROM lntds_modbus_slave_read_registers\r\n"
            + "where modbus_slave_id = ?1 \r\n"
            + "and bit_register = ?2 \r\n"
            + "and register = ?3 \r\n", nativeQuery = true)
    List<ModbusSlavesReadRegisters> findReadRegisterByBit(Integer modbusid, String bit, Integer register);

    @Query(value = "select reg.*  from lntds_modbus_slave_read_registers reg\r\n"
            + "INNER JOIN lntds_modbus_asset_meas_readregister_map map ON map.modbus_slave_id = reg.modbus_slave_id\r\n"
            + "where reg.id in (:ids) \r\n"
            + "and reg.id = map.modbus_slave_read\r\n"
            + "and reg.modbus_slave_id = map.modbus_slave_id", nativeQuery = true)
    List<ModbusSlavesReadRegisters> findByMap(List<Integer> ids);

    List<ModbusSlavesReadRegisters> findByRegisterAndModbusId(Integer register, ModbusSlaves modbusId);

}
