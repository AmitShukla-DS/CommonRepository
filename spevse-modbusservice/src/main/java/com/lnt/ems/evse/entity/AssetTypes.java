/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "lntds_asset_types")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AssetTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_type_seq")
    private Integer assetTypeSeq;

    @Column(name = "asset_type_name")
    private String assetTypeName;


    @Column(name = "asset_type_ver", length = 4)
    private String assetTypeVer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_seq", nullable = false)
    private Vendors vendors;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "description", length = 4000)
    private String description;

    @Lob
    @Column(name = "image_symbol")
    private byte[] imageSymbol;

    @Column(name = "is_virtual")
    private String isVirtual;

    @Column(name = "create_user", length = 32)
    private String createUser;

    @Column(name = "create_timestamp")
    private Timestamp createTimestamp;

    @Column(name = "update_user", length = 32)
    private String updateUser;

    @Column(name = "update_timestamp")
    private Timestamp updateTimestamp;

    @JsonIgnore
    @OneToMany(mappedBy = "assetTypes")
    private List<Assets> assets;

    @OneToMany(mappedBy = "assetTypes")
    private List<AssetTypeMeasurement> assetTypeMeasurement;

    public Integer getAssetTypeSeq() {
        return assetTypeSeq;
    }

    public void setAssetTypeSeq(Integer assetTypeSeq) {
        this.assetTypeSeq = assetTypeSeq;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public String getAssetTypeVer() {
        return assetTypeVer;
    }

    public void setAssetTypeVer(String assetTypeVer) {
        this.assetTypeVer = assetTypeVer;
    }

    public Vendors getVendors() {
        return vendors;
    }

    public void setVendors(Vendors vendors) {
        this.vendors = vendors;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImageSymbol() {
        return imageSymbol;
    }

    public void setImageSymbol(byte[] imageSymbol) {
        this.imageSymbol = imageSymbol;
    }

    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
        this.isVirtual = isVirtual;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public List<Assets> getAssets() {
        return assets;
    }

    public void setAssets(List<Assets> assets) {
        this.assets = assets;
    }

    public List<AssetTypeMeasurement> getAssetTypeMeasurement() {
        return assetTypeMeasurement;
    }

    public void setAssetTypeMeasurement(List<AssetTypeMeasurement> assetTypeMeasurement) {
        this.assetTypeMeasurement = assetTypeMeasurement;
    }
}
