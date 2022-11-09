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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "lntds_assets")
@Entity
public class Assets {

    @Id
    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "system_interface_seq")
    private Integer systemInterfaceSeq;

    @Column(name = "adapter_seq")
    private Integer adapterSeq;

    @Column(name = "asset_class")
    private String assetClass;

    @Column(name = "facility_id")
    private Integer facilityId;

    @Column(name = "asset_ipaddr")
    private String assetIpaddr;

    @Column(name = "asset_port")
    private Integer assetPort;

    @Column(name = "asset_access_type")
    private String assetAccessType;

    @Column(name = "asset_userid")
    private String assetUserid;

    @Column(name = "asset_password")
    private String assetPassword;

    @Column(name = "asset_iot_conn_str")
    private String assetIotConnStr;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "asset_alias")
    private String assetAlias;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "parent_comm_asset_id")
    private String parentCommAssetId;

    @Column(name = "parent_elec_asset_id")
    private String parentElecAssetId;

    @Column(name = "asset_record_ver", columnDefinition = "varchar(4) DEFAULT '1.0'")
    private String assetRecordVer;

    @Column(name = "installation_date")
    private Timestamp installationDate;

    @Column(name = "registration_ts")
    private Timestamp registrationTs;

    @Column(name = "provisioning_ts")
    private Timestamp provisioningTs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_seq", nullable = false, columnDefinition = "int DEFAULT '1'")
    private AssetGroups assetGroups;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_seq", nullable = false)
    private Vendors vendors;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_type_seq", nullable = false)
    private AssetTypes assetTypes;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "geo_x")
    private BigDecimal geoX;

    @Column(name = "geo_y")
    private BigDecimal geoY;

    @Column(name = "altitude")
    private String altitude;

    @Column(name = "data_multiplier", columnDefinition = "double DEFAULT '1'")
    private Double dataMultiplier;

    @Column(name = "attribute01", length = 64)
    private String attribute01;

    @Column(name = "attribute02", length = 64)
    private String attribute02;

    @Column(name = "attribute03", length = 64)
    private String attribute03;

    @Column(name = "attribute04", length = 64)
    private String attribute04;

    @Column(name = "attribute05", length = 64)
    private String attribute05;

    @Column(name = "attribute06", length = 64)
    private String attribute06;

    @Column(name = "attribute07", length = 64)
    private String attribute07;

    @Column(name = "attribute08", length = 64)
    private String attribute08;

    @Column(name = "attribute09", length = 64)
    private String attribute09;

    @Column(name = "attribute10", length = 64)
    private String attribute10;

    @Column(name = "attribute11", length = 64)
    private String attribute11;

    @Column(name = "attribute12", length = 64)
    private String attribute12;

    @Column(name = "attribute13", length = 64)
    private String attribute13;

    @Column(name = "attribute14", length = 64)
    private String attribute14;

    @Column(name = "attribute15", length = 64)
    private String attribute15;

    @Column(name = "attribute16", length = 64)
    private String attribute16;

    @Column(name = "attribute17", length = 64)
    private String attribute17;

    @Column(name = "attribute18", length = 64)
    private String attribute18;

    @Column(name = "attribute19", length = 64)
    private String attribute19;

    @Column(name = "attribute20", length = 64)
    private String attribute20;

    @Column(name = "attribute21", length = 64)
    private String attribute21;

    @Column(name = "attribute22", length = 64)
    private String attribute22;

    @Column(name = "attribute23", length = 64)
    private String attribute23;

    @Column(name = "attribute24", length = 64)
    private String attribute24;

    @Column(name = "attribute25", length = 64)
    private String attribute25;

    @Column(name = "attribute26", length = 64)
    private String attribute26;

    @Column(name = "attribute27", length = 64)
    private String attribute27;

    @Column(name = "attribute28", length = 64)
    private String attribute28;

    @Column(name = "attribute29", length = 64)
    private String attribute29;

    @Column(name = "attribute30", length = 64)
    private String attribute30;

    @Column(name = "attribute31", length = 64)
    private String attribute31;

    @Column(name = "attribute32", length = 64)
    private String attribute32;

    @Column(name = "attribute33", length = 64)
    private String attribute33;

    @Column(name = "attribute34", length = 64)
    private String attribute34;

    @Column(name = "attribute35", length = 64)
    private String attribute35;

    @Column(name = "attribute36", length = 64)
    private String attribute36;

    @Column(name = "attribute37", length = 64)
    private String attribute37;

    @Column(name = "attribute38", length = 64)
    private String attribute38;

    @Column(name = "attribute39", length = 64)
    private String attribute39;

    @Column(name = "attribute40", length = 64)
    private String attribute40;

    @Column(name = "attribute41", length = 64)
    private String attribute41;

    @Column(name = "attribute42", length = 64)
    private String attribute42;

    @Column(name = "attribute43", length = 64)
    private String attribute43;

    @Column(name = "attribute44", length = 64)
    private String attribute44;

    @Column(name = "attribute45", length = 64)
    private String attribute45;

    @Column(name = "attribute46", length = 64)
    private String attribute46;

    @Column(name = "attribute47", length = 64)
    private String attribute47;

    @Column(name = "attribute48", length = 64)
    private String attribute48;

    @Column(name = "attribute49", length = 64)
    private String attribute49;

    @Column(name = "attribute50", length = 64)
    private String attribute50;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_timestamp")
    private Timestamp createTimestamp;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_timestamp")
    private Timestamp updateTimestamp;

    @Column(name = "asset_tz")
    private String assetTz;

    @Column(name = "used_in_optimization", columnDefinition = "char(1) DEFAULT 'N'")
    private String usedInOptimization;

    @Column(name = "controllable_asset", columnDefinition = "char(1) DEFAULT 'N'")
    private String controllableAsset;

    @Column(name = "is_virtual", columnDefinition = "char(1) DEFAULT 'N'")
    private String isVirtual;

    @OneToMany(mappedBy = "assets", cascade = CascadeType.ALL)
    private List<AssetPicture> assetPicture;

    @OneToMany(mappedBy = "assets", cascade = CascadeType.ALL)
    private List<AssetNotes> assetNotes;

    @JsonIgnore
    @OneToMany(mappedBy = "assets", cascade = CascadeType.ALL)
    private List<ModbusSlavesAssociatedAssets> modbusSlavesAssociatedAssets;

    @JsonIgnore
    @OneToMany(mappedBy = "assets", cascade = CascadeType.ALL)
    private List<AssetsVirtualCalcRules> assetsVirtualCalcRules;

    public List<ModbusSlavesAssociatedAssets> getModbusSlavesAssociatedAssets() {
        return modbusSlavesAssociatedAssets;
    }

    public void setModbusSlavesAssociatedAssets(List<ModbusSlavesAssociatedAssets> modbusSlavesAssociatedAssets) {
        this.modbusSlavesAssociatedAssets = modbusSlavesAssociatedAssets;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Integer getSystemInterfaceSeq() {
        return systemInterfaceSeq;
    }

    public void setSystemInterfaceSeq(Integer systemInterfaceSeq) {
        this.systemInterfaceSeq = systemInterfaceSeq;
    }

    public Integer getAdapterSeq() {
        return adapterSeq;
    }

    public void setAdapterSeq(Integer adapterSeq) {
        this.adapterSeq = adapterSeq;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getAssetIpaddr() {
        return assetIpaddr;
    }

    public void setAssetIpaddr(String assetIpaddr) {
        this.assetIpaddr = assetIpaddr;
    }

    public Integer getAssetPort() {
        return assetPort;
    }

    public void setAssetPort(Integer assetPort) {
        this.assetPort = assetPort;
    }

    public String getAssetAccessType() {
        return assetAccessType;
    }

    public void setAssetAccessType(String assetAccessType) {
        this.assetAccessType = assetAccessType;
    }

    public String getAssetUserid() {
        return assetUserid;
    }

    public void setAssetUserid(String assetUserid) {
        this.assetUserid = assetUserid;
    }

    public String getAssetPassword() {
        return assetPassword;
    }

    public void setAssetPassword(String assetPassword) {
        this.assetPassword = assetPassword;
    }

    public String getAssetIotConnStr() {
        return assetIotConnStr;
    }

    public void setAssetIotConnStr(String assetIotConnStr) {
        this.assetIotConnStr = assetIotConnStr;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetAlias() {
        return assetAlias;
    }

    public void setAssetAlias(String assetAlias) {
        this.assetAlias = assetAlias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParentCommAssetId() {
        return parentCommAssetId;
    }

    public void setParentCommAssetId(String parentCommAssetId) {
        this.parentCommAssetId = parentCommAssetId;
    }

    public String getParentElecAssetId() {
        return parentElecAssetId;
    }

    public void setParentElecAssetId(String parentElecAssetId) {
        this.parentElecAssetId = parentElecAssetId;
    }

    public String getAssetRecordVer() {
        return assetRecordVer;
    }

    public void setAssetRecordVer(String assetRecordVer) {
        this.assetRecordVer = assetRecordVer;
    }

    public Timestamp getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Timestamp installationDate) {
        this.installationDate = installationDate;
    }

    public Timestamp getRegistrationTs() {
        return registrationTs;
    }

    public void setRegistrationTs(Timestamp registrationTs) {
        this.registrationTs = registrationTs;
    }

    public Timestamp getProvisioningTs() {
        return provisioningTs;
    }

    public void setProvisioningTs(Timestamp provisioningTs) {
        this.provisioningTs = provisioningTs;
    }

    public AssetGroups getAssetGroups() {
        return assetGroups;
    }

    public void setAssetGroups(AssetGroups assetGroups) {
        this.assetGroups = assetGroups;
    }

    public Vendors getVendors() {
        return vendors;
    }

    public void setVendors(Vendors vendors) {
        this.vendors = vendors;
    }

    public AssetTypes getAssetTypes() {
        return assetTypes;
    }

    public void setAssetTypes(AssetTypes assetTypes) {
        this.assetTypes = assetTypes;
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

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public Double getDataMultiplier() {
        return dataMultiplier;
    }

    public void setDataMultiplier(Double dataMultiplier) {
        this.dataMultiplier = dataMultiplier;
    }

    public String getAttribute01() {
        return attribute01;
    }

    public void setAttribute01(String attribute01) {
        this.attribute01 = attribute01;
    }

    public String getAttribute02() {
        return attribute02;
    }

    public void setAttribute02(String attribute02) {
        this.attribute02 = attribute02;
    }

    public String getAttribute03() {
        return attribute03;
    }

    public void setAttribute03(String attribute03) {
        this.attribute03 = attribute03;
    }

    public String getAttribute04() {
        return attribute04;
    }

    public void setAttribute04(String attribute04) {
        this.attribute04 = attribute04;
    }

    public String getAttribute05() {
        return attribute05;
    }

    public void setAttribute05(String attribute05) {
        this.attribute05 = attribute05;
    }

    public String getAttribute06() {
        return attribute06;
    }

    public void setAttribute06(String attribute06) {
        this.attribute06 = attribute06;
    }

    public String getAttribute07() {
        return attribute07;
    }

    public void setAttribute07(String attribute07) {
        this.attribute07 = attribute07;
    }

    public String getAttribute08() {
        return attribute08;
    }

    public void setAttribute08(String attribute08) {
        this.attribute08 = attribute08;
    }

    public String getAttribute09() {
        return attribute09;
    }

    public void setAttribute09(String attribute09) {
        this.attribute09 = attribute09;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute11() {
        return attribute11;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    public String getAttribute12() {
        return attribute12;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    public String getAttribute15() {
        return attribute15;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }

    public String getAttribute16() {
        return attribute16;
    }

    public void setAttribute16(String attribute16) {
        this.attribute16 = attribute16;
    }

    public String getAttribute17() {
        return attribute17;
    }

    public void setAttribute17(String attribute17) {
        this.attribute17 = attribute17;
    }

    public String getAttribute18() {
        return attribute18;
    }

    public void setAttribute18(String attribute18) {
        this.attribute18 = attribute18;
    }

    public String getAttribute19() {
        return attribute19;
    }

    public void setAttribute19(String attribute19) {
        this.attribute19 = attribute19;
    }

    public String getAttribute20() {
        return attribute20;
    }

    public void setAttribute20(String attribute20) {
        this.attribute20 = attribute20;
    }

    public String getAttribute21() {
        return attribute21;
    }

    public void setAttribute21(String attribute21) {
        this.attribute21 = attribute21;
    }

    public String getAttribute22() {
        return attribute22;
    }

    public void setAttribute22(String attribute22) {
        this.attribute22 = attribute22;
    }

    public String getAttribute23() {
        return attribute23;
    }

    public void setAttribute23(String attribute23) {
        this.attribute23 = attribute23;
    }

    public String getAttribute24() {
        return attribute24;
    }

    public void setAttribute24(String attribute24) {
        this.attribute24 = attribute24;
    }

    public String getAttribute25() {
        return attribute25;
    }

    public void setAttribute25(String attribute25) {
        this.attribute25 = attribute25;
    }

    public String getAttribute26() {
        return attribute26;
    }

    public void setAttribute26(String attribute26) {
        this.attribute26 = attribute26;
    }

    public String getAttribute27() {
        return attribute27;
    }

    public void setAttribute27(String attribute27) {
        this.attribute27 = attribute27;
    }

    public String getAttribute28() {
        return attribute28;
    }

    public void setAttribute28(String attribute28) {
        this.attribute28 = attribute28;
    }

    public String getAttribute29() {
        return attribute29;
    }

    public void setAttribute29(String attribute29) {
        this.attribute29 = attribute29;
    }

    public String getAttribute30() {
        return attribute30;
    }

    public void setAttribute30(String attribute30) {
        this.attribute30 = attribute30;
    }

    public String getAttribute31() {
        return attribute31;
    }

    public void setAttribute31(String attribute31) {
        this.attribute31 = attribute31;
    }

    public String getAttribute32() {
        return attribute32;
    }

    public void setAttribute32(String attribute32) {
        this.attribute32 = attribute32;
    }

    public String getAttribute33() {
        return attribute33;
    }

    public void setAttribute33(String attribute33) {
        this.attribute33 = attribute33;
    }

    public String getAttribute34() {
        return attribute34;
    }

    public void setAttribute34(String attribute34) {
        this.attribute34 = attribute34;
    }

    public String getAttribute35() {
        return attribute35;
    }

    public void setAttribute35(String attribute35) {
        this.attribute35 = attribute35;
    }

    public String getAttribute36() {
        return attribute36;
    }

    public void setAttribute36(String attribute36) {
        this.attribute36 = attribute36;
    }

    public String getAttribute37() {
        return attribute37;
    }

    public void setAttribute37(String attribute37) {
        this.attribute37 = attribute37;
    }

    public String getAttribute38() {
        return attribute38;
    }

    public void setAttribute38(String attribute38) {
        this.attribute38 = attribute38;
    }

    public String getAttribute39() {
        return attribute39;
    }

    public void setAttribute39(String attribute39) {
        this.attribute39 = attribute39;
    }

    public String getAttribute40() {
        return attribute40;
    }

    public void setAttribute40(String attribute40) {
        this.attribute40 = attribute40;
    }

    public String getAttribute41() {
        return attribute41;
    }

    public void setAttribute41(String attribute41) {
        this.attribute41 = attribute41;
    }

    public String getAttribute42() {
        return attribute42;
    }

    public void setAttribute42(String attribute42) {
        this.attribute42 = attribute42;
    }

    public String getAttribute43() {
        return attribute43;
    }

    public void setAttribute43(String attribute43) {
        this.attribute43 = attribute43;
    }

    public String getAttribute44() {
        return attribute44;
    }

    public void setAttribute44(String attribute44) {
        this.attribute44 = attribute44;
    }

    public String getAttribute45() {
        return attribute45;
    }

    public void setAttribute45(String attribute45) {
        this.attribute45 = attribute45;
    }

    public String getAttribute46() {
        return attribute46;
    }

    public void setAttribute46(String attribute46) {
        this.attribute46 = attribute46;
    }

    public String getAttribute47() {
        return attribute47;
    }

    public void setAttribute47(String attribute47) {
        this.attribute47 = attribute47;
    }

    public String getAttribute48() {
        return attribute48;
    }

    public void setAttribute48(String attribute48) {
        this.attribute48 = attribute48;
    }

    public String getAttribute49() {
        return attribute49;
    }

    public void setAttribute49(String attribute49) {
        this.attribute49 = attribute49;
    }

    public String getAttribute50() {
        return attribute50;
    }

    public void setAttribute50(String attribute50) {
        this.attribute50 = attribute50;
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

    public String getAssetTz() {
        return assetTz;
    }

    public void setAssetTz(String assetTz) {
        this.assetTz = assetTz;
    }

    public String getUsedInOptimization() {
        return usedInOptimization;
    }

    public void setUsedInOptimization(String usedInOptimization) {
        this.usedInOptimization = usedInOptimization;
    }

    public String getControllableAsset() {
        return controllableAsset;
    }

    public void setControllableAsset(String controllableAsset) {
        this.controllableAsset = controllableAsset;
    }

    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
        this.isVirtual = isVirtual;
    }

    @JsonIgnore
    public List<AssetPicture> getAssetPicture() {
        return assetPicture;
    }

    @JsonProperty
    public void setAssetPicture(List<AssetPicture> assetPicture) {
        this.assetPicture = assetPicture;
    }

    @JsonIgnore
    public List<AssetNotes> getAssetNotes() {
        return assetNotes;
    }

    @JsonProperty
    public void setAssetNotes(List<AssetNotes> assetNotes) {
        this.assetNotes = assetNotes;
    }

    public List<AssetsVirtualCalcRules> getAssetsVirtualCalRules() {
        return assetsVirtualCalcRules;
    }

    public void setAssetsVirtualCalRules(List<AssetsVirtualCalcRules> assetsVirtualCalcRules) {
        this.assetsVirtualCalcRules = assetsVirtualCalcRules;
    }
}