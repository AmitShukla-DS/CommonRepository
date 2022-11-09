/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dto;

import java.util.List;

import com.lnt.ems.evse.entity.ModbusAssetMeasWriteRegistersMap;
import com.lnt.ems.evse.entity.ModbusSlaves;

public class ModbusSlavesWriteRegistersDto {

    private Integer id;

    private Integer register;

    private String description;

    private Integer functionCode;

    private Float scalingFactor;

    private Integer size;

    private String roundingEnable;

    private String roundingMechanism;

    private ModbusSlaves modbusId;

    private List<ModbusAssetMeasWriteRegistersMap> assetMeasWriteRegisters;

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

    public ModbusSlaves getModbusId() {
        return modbusId;
    }

    public void setModbusId(ModbusSlaves modbusId) {
        this.modbusId = modbusId;
    }

    public List<ModbusAssetMeasWriteRegistersMap> getAssetMeasWriteRegisters() {
        return assetMeasWriteRegisters;
    }

    public void setAssetMeasWriteRegisters(List<ModbusAssetMeasWriteRegistersMap> assetMeasWriteRegisters) {
        this.assetMeasWriteRegisters = assetMeasWriteRegisters;
    }

}
