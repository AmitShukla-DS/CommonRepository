/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lnt.ems.evse.entity.AssetTypeMeasurement;

import java.util.List;

@Repository
public interface AssetTypeMeasurementDao extends CrudRepository<AssetTypeMeasurement, Integer> {

    @Query("Select m from AssetTypeMeasurement m where  m.assetTypes.assetTypeSeq=:assetTypeSeq And m.assetMeasurementName=:assetMeasurementName ")
	List<AssetTypeMeasurement> findByAssetTypeSeqAndMeasurementName(Integer assetTypeSeq, String assetMeasurementName);

    public AssetTypeMeasurement findByAssetMeasurementName(String assetMeasurementName);

}
