/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lntds_modbus_slaves")
public class ModbusSlaves {

    @Id
    @Column(name = "modbus_slave_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modbusSlaveId;

    @Column(name = "name")
    private String name;

    @Column(name = "polling_slave_frequency")
    private Integer pollingSlaveFrequency;

    @Column(name = "description")
    private String description;

    @Column(name = "ip")
    private String ip;

    @Column(name = "port")
    private String port;

    @Column(name = "slave_id")
    private Integer slaveId;

    @Column(name = "status")
    private String status;

    @Column(name = "is_enabled")
    private String isEnabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "status_date_time")
    private Date statusDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

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

}
