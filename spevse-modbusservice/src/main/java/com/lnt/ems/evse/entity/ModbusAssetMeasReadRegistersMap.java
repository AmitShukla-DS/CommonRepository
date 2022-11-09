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
@Table(name = "lntds_modbus_asset_meas_readregister_map")
public class ModbusAssetMeasReadRegistersMap {

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

    @Column(name = "binary_read")
    private String binaryRead;

    @Column(name = "mask")
    private Integer mask;

    @Column(name = "comm_flag")
    private String commFlag;

    @Column(name = "comm_value")
    private Integer commValue;

    @Column(name = "bit_register")
    private String bitRegister;

    @Column(name = "modbus_slave_id")
    private Integer modbusId;

    //	@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modbus_slave_read", nullable = false)
    private ModbusSlavesReadRegisters modbusSlavesReadRegisters;

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

    public String getBinaryRead() {
        return binaryRead;
    }

    public void setBinaryRead(String binaryRead) {
        this.binaryRead = binaryRead;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public String getCommFlag() {
        return commFlag;
    }

    public void setCommFlag(String commFlag) {
        this.commFlag = commFlag;
    }

    public Integer getCommValue() {
        return commValue;
    }

    public void setCommValue(Integer commValue) {
        this.commValue = commValue;
    }

    public String getBitRegister() {
        return bitRegister;
    }

    public void setBitRegister(String bitRegister) {
        this.bitRegister = bitRegister;
    }

    public ModbusSlavesReadRegisters getModbusSlavesReadRegisters() {
        return modbusSlavesReadRegisters;
    }

    public void setModbusSlavesReadRegisters(ModbusSlavesReadRegisters modbusSlavesReadRegisters) {
        this.modbusSlavesReadRegisters = modbusSlavesReadRegisters;
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
