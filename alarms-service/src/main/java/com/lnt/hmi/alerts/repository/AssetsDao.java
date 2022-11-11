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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lnt.hmi.alerts.entity.AssetTypes;
import com.lnt.hmi.alerts.entity.Assets;
import com.lnt.hmi.alerts.entity.Vendors;



@Repository
public interface AssetsDao extends CrudRepository<Assets, String> {

	@Query(value = "Select m.assetId, m.assetName, m.assetTypes.assetTypeSeq From Assets m where m.parentCommAssetId=:parentCommAssetId and m.assetClass='Communication' and m.assetTypes.assetTypeName not in('Bus')")
	public List<Object[]> findAssetIdByParentAssetId(@Param("parentCommAssetId") String parentAssetId);

	@Query(value = "Select m.assetId, m.assetName, m.assetTypes.assetTypeSeq From Assets m where m.parentElecAssetId=:parentElecAssetId and m.assetClass='Electrical' and m.assetTypes.assetTypeName not in('Bus')")
	public List<Object[]> findAssetIdByParentAssetIdEle(@Param("parentElecAssetId") String parentAssetId);

	public Assets findByAssetName(String assetName);

	public List<Assets> findByVendors(Vendors vendors);

	@Query("select m.assetId, m.assetName from Assets m")
	public List<Object[]> findAllAssetIdAndName();

	@Query("select m from Assets m where m.assetTypes.assetTypeName=:assetTypeName")
	public List<Assets> findByAssetTypes(@Param("assetTypeName") String assetTypeName);

	@Query("select m.assetId from Assets m where m.assetTypes=:assetTypes")
	public List<String> findAssetsByAssetTypes(@Param("assetTypes") AssetTypes assetTypes);

	public Assets findByAssetId(String assetId);

	@Query("select m from Assets m where m.assetTypes.assetTypeName='Virtual Device'")
	public List<Assets> findVirtualAssets();

	@Query("select m.assetId, m.assetName from Assets m where m.parentCommAssetId is null and m.assetTypes.assetTypeName='Virtual Device'")
	public List<Object[]> findVirtualAssetIdByParentAssetIdNull();

	@Query("select distinct m.parentCommAssetId from Assets m where m.parentCommAssetId is not null and m.assetTypes.assetTypeName='Virtual Device'")
	public List<String> findDistinctVirtualParentAssetId();

	@Query("Select m.assetId, m.assetName, m.assetTypes.assetTypeSeq From Assets m where (m.parentCommAssetId is null or m.parentCommAssetId='') and m.assetClass='Communication' and m.assetTypes.assetTypeName not in('Bus')")
	public List<Object[]> findAssetidByParentAssetidNull();

	@Query("Select m.assetId, m.assetName, m.assetTypes.assetTypeSeq From Assets m where (m.parentElecAssetId is null or m.parentElecAssetId='') and m.assetClass='Electrical' and m.assetTypes.assetTypeName not in('Bus')")
	public List<Object[]> findAssetidByParentAssetidEleNull();

	@Query("Select distinct m.parentCommAssetId From Assets m where (m.parentCommAssetId is not null and not m.parentCommAssetId='') and m.assetTypes.assetTypeName not in('Bus')")
	public List<String> findDistinctParentdeviceid();

	@Query("Select distinct m.parentElecAssetId From Assets m where (m.parentElecAssetId is not null and not m.parentElecAssetId='') and m.assetClass='Electrical' and m.assetTypes.assetTypeName not in('Bus')")
	public List<String> findDistinctParentdeviceidEle();

	public List<Assets> findByAssetTypes(AssetTypes assetTypes);

	@Query("select m.assetId from Assets m where m.assetTypes=:assetTypes")
	public List<String> findAssetIdByAssetTypes(AssetTypes assetTypes);

	@Query("select m.assetId from Assets m where m.assetId=:assetId")
	public String findAssetId(String assetId);

	@Query("select m.assetId from Assets m")
	public List<String> findAssetId();
}
