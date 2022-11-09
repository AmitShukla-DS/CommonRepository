/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "lntds_modbus_slave_associated_assets")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ModbusSlavesAssociatedAssets {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "slave_server_id", referencedColumnName = "modbus_slave_id")
    private ModbusSlaves slaveServerId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"assetGroups", "vendors", "assetTypes", "assetTypeMeasurement"})
    @JoinColumn(name = "asset_id")
    private Assets assets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModbusSlaves getSlaveServerId() {
        return slaveServerId;
    }

    public void setSlaveServerId(ModbusSlaves slaveServerId) {
        this.slaveServerId = slaveServerId;
    }

    @JsonProperty
    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }


}
