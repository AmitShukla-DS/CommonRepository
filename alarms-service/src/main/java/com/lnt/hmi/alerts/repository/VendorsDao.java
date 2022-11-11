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

import com.lnt.hmi.alerts.entity.Vendors;



@Repository
public interface VendorsDao extends CrudRepository<Vendors, Integer> {

	Vendors findByVendorSeq(Integer vendorSeq);

	List<Vendors> findAllByOrderByVendorSeqDesc();

	Vendors findByVendorName(String string);

	@Query("select m.vendorName from Vendors m")
	public List<String> findVendorName();

}
