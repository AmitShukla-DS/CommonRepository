/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dto;

public class ModbusSlavesAssociatedAssetsDto {

    private Integer id;

    private Integer slaveServerId;

    private String[] assetIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSlaveServerId() {
        return slaveServerId;
    }

    public void setSlaveServerId(Integer slaveServerId) {
        this.slaveServerId = slaveServerId;
    }

    public String[] getAssetIds() {
        return assetIds;
    }

    public void setAssetIds(String[] assetIds) {
        this.assetIds = assetIds;
    }


}
