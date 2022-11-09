/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lnt.ems.evse.entity.ModbusSlavesReadRegisters;
import com.lnt.ems.evse.entity.ModbusSlavesWriteRegisters;

import java.util.Date;
import java.util.List;

public class ModbusSlavesDTO {


    private Integer modbusSlaveId;

    private String name;

    private Integer pollingSlaveFrequency;

    private String description;

    private String ip;

    private String port;

    private Integer slaveId;

    private String status;

    private String isEnabled;

    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private Date updatedDate;

    private List<ModbusSlavesReadRegisters> modbusSlavesReadRegisters;

    private List<ModbusSlavesWriteRegisters> modbusSlavesWriteRegisters;

    private Integer cloneId;

    public Integer getModbusSlaveId() {
        return modbusSlaveId;
    }

    public void setModbusSlaveId(Integer modbusSlaveId) {
        this.modbusSlaveId = modbusSlaveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPollingSlaveFrequency() {
        return pollingSlaveFrequency;
    }

    public void setPollingSlaveFrequency(Integer pollingSlaveFrequency) {
        this.pollingSlaveFrequency = pollingSlaveFrequency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(Integer slaveId) {
        this.slaveId = slaveId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Date getStatusDateTime() {
        return statusDateTime;
    }

    public void setStatusDateTime(Date statusDateTime) {
        this.statusDateTime = statusDateTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<ModbusSlavesReadRegisters> getModbusSlavesReadRegisters() {
        return modbusSlavesReadRegisters;
    }

    public void setModbusSlavesReadRegisters(List<ModbusSlavesReadRegisters> modbusSlavesReadRegisters) {
        this.modbusSlavesReadRegisters = modbusSlavesReadRegisters;
    }

    public List<ModbusSlavesWriteRegisters> getModbusSlavesWriteRegisters() {
        return modbusSlavesWriteRegisters;
    }

    public void setModbusSlavesWriteRegisters(List<ModbusSlavesWriteRegisters> modbusSlavesWriteRegisters) {
        this.modbusSlavesWriteRegisters = modbusSlavesWriteRegisters;
    }

    public Integer getCloneId() {
        return cloneId;
    }

    public void setCloneId(Integer cloneId) {
        this.cloneId = cloneId;
    }


}
