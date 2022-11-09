/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.entity;

import javax.persistence.*;

@Entity
@Table(name = "lntds_modbus_asset_meas_writeregister_map")
public class ModbusAssetMeasWriteRegistersMap {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "measurement_name")
    private String measurementName;

    @Column(name = "register")
    private Integer register;

    @Column(name = "description")
    private String description;

    @Column(name = "function_code")
    private Integer functionCode;

    @Column(name = "scaling_factor")
    private Float scalingFactor;

    @Column(name = "size")
    private Integer size;

    @Column(name = "rounding_enable")
    private String roundingEnable;

    @Column(name = "rounding_mechanism")
    private String roundingMechanism;

    @Column(name = "modbus_slave_id")
    private Integer modbusId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modbus_slave_write")
    private ModbusSlavesWriteRegisters modbusSlavesWriteRegisters;

    private String assetId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(Integer functionCode) {
        this.functionCode = functionCode;
    }

    public Float getScalingFactor() {
        return scalingFactor;
    }

    public void setScalingFactor(Float scalingFactor) {
        this.scalingFactor = scalingFactor;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getRoundingEnable() {
        return roundingEnable;
    }

    public void setRoundingEnable(String roundingEnable) {
        this.roundingEnable = roundingEnable;
    }

    public String getRoundingMechanism() {
        return roundingMechanism;
    }

    public void setRoundingMechanism(String roundingMechanism) {
        this.roundingMechanism = roundingMechanism;
    }

    public ModbusSlavesWriteRegisters getModbusSlavesWriteRegisters() {
        return modbusSlavesWriteRegisters;
    }

    public void setModbusSlavesWriteRegisters(ModbusSlavesWriteRegisters modbusSlavesWriteRegisters) {
        this.modbusSlavesWriteRegisters = modbusSlavesWriteRegisters;
    }

    public Integer getModbusId() {
        return modbusId;
    }

    public void setModbusId(Integer modbusId) {
        this.modbusId = modbusId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }


}
