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

import com.lnt.hmi.alerts.entity.Codes;



@Repository
public interface CodesDao extends CrudRepository<Codes, Integer> {

	public List<Codes> findAllByOrderByIdDesc();

	@Query("select distinct m.codeType from Codes m")
	public List<Codes> findAllCodeType();

	@Query("select m from Codes m where m.codeType=:codeType Order BY m.displayOrder ASC")
	public List<Codes> findByCodeType(String codeType);

	@Query("select m.codeDisplayTxt from Codes m where m.codeType='TIME_ZONE'")
	public List<String> findCodeTypeTimezoneList();

	@Query("select m.codeDisplayTxt from Codes m where m.codeType='LOGICAL_ATTRIBUTE_NAME'")
	public List<String> findLogicalAttributeNames();

	@Query("select m.codeDisplayTxt from Codes m where m.codeType='TIMESTAMP_FORMATS'")
	public List<String> findTimestampTypes();

	@Query("select m.codeDisplayTxt from Codes m where m.codeType='DATA_TYPES'")
	public List<String> findDataTypeList();

	@Query("select m.codeDisplayTxt from Codes m where m.codeType='UOM'")
	public List<String> findUomList();

	@Query("select m.codeDisplayTxt from Codes m where m.codeType='MEASUREMENT_TYPE'")
	public List<String> findMeasurementTypeList();

}
