/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dto;

import java.util.List;

import com.lnt.ems.evse.entity.ModbusAssetMeasReadRegistersMap;
import com.lnt.ems.evse.entity.ModbusSlaves;

public class ModbusSlavesReadRegistersDto {


    private Integer id;

    private Integer register;

    private String description;

    private Integer functionCode;

    private Float scalingFactor;

    private Integer size;

    private String binaryRead;

    private Integer mask;

    private String commFlag;

    private Integer commValue;

    private String bitRegister;

    private ModbusSlaves modbusId;

    private List<ModbusAssetMeasReadRegistersMap> assetMeasReadRegisters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ModbusSlaves getModbusId() {
        return modbusId;
    }

    public void setModbusId(ModbusSlaves modbusId) {
        this.modbusId = modbusId;
    }

    public List<ModbusAssetMeasReadRegistersMap> getAssetMeasReadRegisters() {
        return assetMeasReadRegisters;
    }

    public void setAssetMeasReadRegisters(List<ModbusAssetMeasReadRegistersMap> assetMeasReadRegisters) {
        this.assetMeasReadRegisters = assetMeasReadRegisters;
    }


}
