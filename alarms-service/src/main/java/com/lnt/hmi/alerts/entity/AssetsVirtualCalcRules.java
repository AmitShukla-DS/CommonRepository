package com.lnt.hmi.alerts.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
