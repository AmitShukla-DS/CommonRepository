package com.lnt.hmi.alerts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lnt.hmi.alerts.entity.AssetMeasurementExtended;
import com.lnt.hmi.alerts.entity.AssetTypeMeasurement;




@Repository
public interface AssetMeasurementExtendedDao extends CrudRepository<AssetMeasurementExtended,Integer> {

	public AssetMeasurementExtended findByAssetTypeMeasurementAndValue(AssetTypeMeasurement type, Double value);
	public List<AssetMeasurementExtended>  findByAssetTypeMeasurement(AssetTypeMeasurement type);
	
	public void deleteByAssetTypeMeasurement(AssetTypeMeasurement type);
}
