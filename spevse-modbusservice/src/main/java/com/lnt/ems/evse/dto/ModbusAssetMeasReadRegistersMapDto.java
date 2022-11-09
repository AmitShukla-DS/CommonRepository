/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dto;

import java.util.List;

public class ModbusAssetMeasReadRegistersMapDto {

    private Integer id;

    private String measurementName;

    private Integer register;

    private String description;

    private String functionCode;

    private Float scalingFactor;

    private String size;

    private String binaryRead;

    private Integer mask;

    private String commFlag;

    private Integer commValue;

    private String bitRegister;

    private Integer modbusId;

    private Integer modbusSlavesReadRegisters;

    private String assetId;

    private List<String> errors;

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

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public Float getScalingFactor() {
        return scalingFactor;
    }

    public void setScalingFactor(Float scalingFactor) {
        this.scalingFactor = scalingFactor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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

    public Integer getModbusId() {
        return modbusId;
    }

    public void setModbusId(Integer modbusId) {
        this.modbusId = modbusId;
    }

    public Integer getModbusSlavesReadRegisters() {
        return modbusSlavesReadRegisters;
    }

    public void setModbusSlavesReadRegisters(Integer modbusSlavesReadRegisters) {
        this.modbusSlavesReadRegisters = modbusSlavesReadRegisters;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }


}
