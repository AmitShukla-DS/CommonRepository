/**
* EVSE is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.hmi.alerts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lnt.hmi.alerts.entity.AssetTypes;
import com.lnt.hmi.alerts.entity.Vendors;



@Repository
public interface AssetTypesDao extends CrudRepository<AssetTypes, Integer> {

	List<AssetTypes> findByVendors(Vendors vendor);

	@Query("Select m from AssetTypes m order by m.assetTypeSeq DESC")
	List<AssetTypes> findAllDesc();

	AssetTypes findByAssetTypeSeq(Integer assetTypeSeq);

	AssetTypes findByAssetTypeNameAndVendors(String assetTypeName, Vendors vendors);

	@Query("select m.assetTypeName from AssetTypes m")
	public List<String> findAssetTypeName();

}
