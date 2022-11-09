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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "lntds_asset_groups")
public class AssetGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_seq")
    private Integer groupSeq;

    @Column(name = "group_name", length = 64)
    private String groupName;

    @Column(name = "latitude", length = 32)
    private String latitude;

    @Column(name = "longitude", length = 32)
    private String longitude;

    @Column(name = "parent_group_seq")
    private Integer parentGroupSeq;

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "altitude", length = 32)
    private String altitude;

    @Column(name = "geo_x", precision = 10, scale = 0, columnDefinition = "decimal")
    private BigDecimal geoX;

    @Column(name = "geo_y", precision = 10, scale = 0, columnDefinition = "decimal")
    private BigDecimal geoY;

    @Column(name = "default_yn", length = 1)
    private String defaultYn;

    @Column(name = "xmin", precision = 10, scale = 0, columnDefinition = "decimal")
    private BigDecimal xmin;

    @Column(name = "xmax", precision = 10, scale = 0, columnDefinition = "decimal")
    private BigDecimal xmax;

    @Column(name = "ymin", precision = 10, scale = 0, columnDefinition = "decimal")
    private BigDecimal ymin;

    @Column(name = "umax", precision = 10, scale = 0, columnDefinition = "decimal")
    private BigDecimal ymax;

    @Column(name = "create_user", length = 32)
    private String createUser;

    @Column(name = "create_timestamp")
    private Timestamp createTimestamp;

    @Column(name = "update_user", length = 32)
    private String updateUser;

    @Column(name = "update_timestamp")
    private Timestamp updateTimestamp;

    @JsonIgnore
    @OneToMany(mappedBy = "assetGroups", cascade = CascadeType.ALL)
    private List<Assets> assets;

    public Integer getGroupSeq() {
        return groupSeq;
    }

    public void setGroupSeq(Integer groupSeq) {
        this.groupSeq = groupSeq;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getParentGroupSeq() {
        return parentGroupSeq;
    }

    public void setParentGroupSeq(Integer parentGroupSeq) {
        this.parentGroupSeq = parentGroupSeq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public BigDecimal getGeoX() {
        return geoX;
    }

    public void setGeoX(BigDecimal geoX) {
        this.geoX = geoX;
    }

    public BigDecimal getGeoY() {
        return geoY;
    }

    public void setGeoY(BigDecimal geoY) {
        this.geoY = geoY;
    }

    public String getDefaultYn() {
        return defaultYn;
    }

    public void setDefaultYn(String defaultYn) {
        this.defaultYn = defaultYn;
    }

    public BigDecimal getXmin() {
        return xmin;
    }

    public void setXmin(BigDecimal xmin) {
        this.xmin = xmin;
    }

    public BigDecimal getXmax() {
        return xmax;
    }

    public void setXmax(BigDecimal xmax) {
        this.xmax = xmax;
    }

    public BigDecimal getYmin() {
        return ymin;
    }

    public void setYmin(BigDecimal ymin) {
        this.ymin = ymin;
    }

    public BigDecimal getYmax() {
        return ymax;
    }

    public void setYmax(BigDecimal ymax) {
        this.ymax = ymax;
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
}
