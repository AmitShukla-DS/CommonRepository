/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lntds_modbus_slave_write_registers")
public class ModbusSlavesWriteRegisters {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modbus_slave_id")
    private ModbusSlaves modbusId;

    @JsonIgnore
    @OneToMany(mappedBy = "modbusSlavesWriteRegisters", cascade = CascadeType.ALL)
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
