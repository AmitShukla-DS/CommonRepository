/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dto;

import java.util.List;

public class ModbusAssetMeasWriteRegistersMapDto {


    private Integer id;

    private String measurementName;

    private Integer register;

    private String description;

    private String functionCode;

    private Float scalingFactor;

    private String size;

    private String roundingEnable;

    private String roundingMechanism;

    private Integer modbusSlavesWriteRegisters;

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

    public Integer getModbusSlavesWriteRegisters() {
        return modbusSlavesWriteRegisters;
    }

    public void setModbusSlavesWriteRegisters(Integer modbusSlavesWriteRegisters) {
        this.modbusSlavesWriteRegisters = modbusSlavesWriteRegisters;
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
