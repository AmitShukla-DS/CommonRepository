/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lntds_asset_types_measurements")
public class AssetTypeMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asset_type_seq", nullable = false)
    private AssetTypes assetTypes;

    @Column(name = "measurement_name")
    private String assetMeasurementName;

    @Column(name = "display_measurement_name")
    private String displayMeasurementName;

    @Column(name = "asset_tag_name")
    private String assetTagName;

    @Column(name = "measurement_class")
    private String measurementClass;

    @Column(name = "measurement_type")
    private String measurementType;

    @Column(name = "generate_consumption_data")
    private String generateConsumptionData;

    @Column(name = "consumption_meas_name", length = 64)
    private String consumptionMeasurementName;

    @Column(name = "consumption_offset")
    private Double consumptionOffset;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_date")
    private Timestamp updateDate;

    @Column(name = "uom")
    private String uom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public AssetTypes getAssetTypes() {
        return assetTypes;
    }

    @JsonProperty
    public void setAssetTypes(AssetTypes assetTypes) {
        this.assetTypes = assetTypes;
    }

    public String getAssetMeasurementName() {
        return assetMeasurementName;
    }

    public void setAssetMeasurementName(String assetMeasurementName) {
        this.assetMeasurementName = assetMeasurementName;
    }

    public String getDisplayMeasurementName() {
        return displayMeasurementName;
    }

    public void setDisplayMeasurementName(String displayMeasurementName) {
        this.displayMeasurementName = displayMeasurementName;
    }

    public String getAssetTagName() {
        return assetTagName;
    }

    public void setAssetTagName(String assetTagName) {
        this.assetTagName = assetTagName;
    }

    public String getMeasurementClass() {
        return measurementClass;
    }

    public void setMeasurementClass(String measurementClass) {
        this.measurementClass = measurementClass;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getGenerateConsumptionData() {
        return generateConsumptionData;
    }

    public void setGenerateConsumptionData(String generateConsumptionData) {
        this.generateConsumptionData = generateConsumptionData;
    }

    public String getConsumptionMeasurementName() {
        return consumptionMeasurementName;
    }

    public void setConsumptionMeasurementName(String consumptionMeasurementName) {
        this.consumptionMeasurementName = consumptionMeasurementName;
    }

    public Double getConsumptionOffset() {
        return consumptionOffset;
    }

    public void setConsumptionOffset(Double consumptionOffset) {
        this.consumptionOffset = consumptionOffset;
    }
}
