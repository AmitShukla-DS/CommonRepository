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

import com.lnt.ems.evse.dao.AssetTypeMeasurementDao;
import com.lnt.ems.evse.entity.AssetTypeMeasurement;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DeviceManagementService {

    @Autowired
    private AssetTypeMeasurementDao assetTypeMeasurementDao;


    public List<AssetTypeMeasurement> findByAssetTypeSeqAndMeasurementName(Integer sequence, String measurement) {
        return assetTypeMeasurementDao.findByAssetTypeSeqAndMeasurementName(sequence, measurement);
    }

}
