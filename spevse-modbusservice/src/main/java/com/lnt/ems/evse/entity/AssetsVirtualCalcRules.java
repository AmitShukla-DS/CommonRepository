package com.lnt.ems.evse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lntds_assets_virtual_calc_rules")
public class AssetsVirtualCalcRules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "measurement_name")
    private String assetMeasurementName;

    @Column(name = "uom")
    private String uom;

    @Column(name = "calculation_equation")
    private String calculationEquation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_id")
    private Assets assets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssetMeasurementName() {
        return assetMeasurementName;
    }

    public void setAssetMeasurementName(String assetMeasurementName) {
        this.assetMeasurementName = assetMeasurementName;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCalculationEquation() {
        return calculationEquation;
    }

    public void setCalculationEquation(String calculationEquation) {
        this.calculationEquation = calculationEquation;
    }

    @JsonIgnore
    public Assets getAssets() {
        return assets;
    }

    @JsonProperty
    public void setAssets(Assets assets) {
        this.assets = assets;
    }
}
