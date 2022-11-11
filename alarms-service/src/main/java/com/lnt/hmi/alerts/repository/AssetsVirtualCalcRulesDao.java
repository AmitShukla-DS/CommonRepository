package com.lnt.hmi.alerts.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lnt.hmi.alerts.entity.Assets;
import com.lnt.hmi.alerts.entity.AssetsVirtualCalcRules;



@Repository
public interface AssetsVirtualCalcRulesDao extends CrudRepository<AssetsVirtualCalcRules, Integer> {

	@Modifying
	@Transactional
	@Query("delete from AssetsVirtualCalcRules m where m.assets= :assets")
	public int deleteByMemsDevices(@Param("assets") Assets assets);

	public List<AssetsVirtualCalcRules> findByAssetsAndAssetMeasurementName(Assets assets, String assetMeasurementName);

	public List<AssetsVirtualCalcRules> findByAssets(Assets assets);

}
