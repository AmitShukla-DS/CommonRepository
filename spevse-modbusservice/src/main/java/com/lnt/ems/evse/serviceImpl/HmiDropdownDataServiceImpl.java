/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnt.ems.evse.dao.HMIDropDownDataDao;
import com.lnt.ems.evse.entity.HMIDropdownData;
import com.lnt.ems.evse.services.HmiDropdownDataService;

import java.util.List;

@Service
public class HmiDropdownDataServiceImpl implements HmiDropdownDataService {

    @Autowired
    HMIDropDownDataDao hmiDropDownDataDao;

    @Override
    public List<HMIDropdownData> getAllHmiDropdownData() {

        return (List<HMIDropdownData>) hmiDropDownDataDao.findAll();

    }


}
