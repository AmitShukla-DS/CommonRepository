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

import com.lnt.hmi.alerts.entity.AssetGroups;



@Repository
public interface AssetGroupsDao extends CrudRepository<AssetGroups, Integer> {

	public AssetGroups findByGroupSeq(Integer groupSeq);

	public AssetGroups findByGroupName(String groupName);

	@Query("select m.groupName from AssetGroups m")
	public List<String> findGroupName();

	public List<AssetGroups> findAllByGroupType(String type);
}
