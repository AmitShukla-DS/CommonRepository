/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnt.ems.evse.dao.AssetTypesDao;
import com.lnt.ems.evse.dao.AssetsDao;
import com.lnt.ems.evse.dao.ModbusSlavesAssociatedAssetsDao;
import com.lnt.ems.evse.dao.ModbusSlavesDao;
import com.lnt.ems.evse.dto.AssetAndTypeDto;
import com.lnt.ems.evse.dto.ModbusSlavesAssociatedAssetsDto;
import com.lnt.ems.evse.entity.AssetTypeMeasurement;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.entity.ModbusSlavesAssociatedAssets;
import com.lnt.ems.evse.services.ModbusSlavesAssociatedAssetsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModbusSlavesAssociatedAssetsServiceImpl implements ModbusSlavesAssociatedAssetsService {

    private static final Logger logger = LoggerFactory.getLogger(ModbusSlavesAssociatedAssetsServiceImpl.class);

    @Autowired
    DeviceManagementService deviceManagementService;

    @Autowired
    private ModbusSlavesAssociatedAssetsDao associatedAssetsDao;

    @Autowired
    private ModbusSlavesDao modbusSlavesDao;

    @Autowired
    private AssetsDao assetsDao;

    @Autowired
    private AssetTypesDao assetTypesDao;


    @Override
    public List<ModbusSlavesAssociatedAssets> getAllModbusSlavesAssociatedAssets() {
        return (List<ModbusSlavesAssociatedAssets>) associatedAssetsDao.findAll();
    }

    @Override
    public ModbusSlavesAssociatedAssets saveModbusSlavesAssociatedAssets(ModbusSlavesAssociatedAssetsDto dto) {
        ModbusSlavesAssociatedAssets associatedAssets = new ModbusSlavesAssociatedAssets();

        associatedAssets.setId(dto.getId());
        associatedAssets.setAssets(assetsDao.findByAssetId(dto.getAssetIds()[0]));
        Optional<ModbusSlaves> optSlaveServer = modbusSlavesDao.findById(dto.getSlaveServerId());
        if (optSlaveServer.isPresent()) {
            associatedAssets.setSlaveServerId(optSlaveServer.get());
            return associatedAssetsDao.save(associatedAssets);
        } else {
            return null;
        }
    }

    @Override
    public ModbusSlavesAssociatedAssets getById(Integer id) {
        Optional<ModbusSlavesAssociatedAssets> modbusSlaves = associatedAssetsDao.findById(id);
        if (modbusSlaves.isPresent()) {
            return modbusSlaves.get();
        }
        return null;
    }

    @Override
    public void deleteModbusSlavesAssociatedAssetsById(Integer id) {
        associatedAssetsDao.deleteById(id);
    }

    @Override
    public List<ModbusSlavesAssociatedAssets> saveAll(ModbusSlavesAssociatedAssetsDto dto) {

        List<ModbusSlavesAssociatedAssets> associatedAssets = new ArrayList<ModbusSlavesAssociatedAssets>();
        for (String assetId : dto.getAssetIds()) {

            Optional<ModbusSlaves> optSlaveServer = modbusSlavesDao.findById(dto.getSlaveServerId());
            if (optSlaveServer.isPresent()) {
                ModbusSlavesAssociatedAssets associatedAsset = new ModbusSlavesAssociatedAssets();
                associatedAsset.setSlaveServerId(optSlaveServer.get());
                associatedAsset.setId(dto.getId());
                associatedAsset.setAssets(assetsDao.findByAssetId(assetId));
                associatedAssets.add(associatedAsset);
            }
        }

        return (List<ModbusSlavesAssociatedAssets>) associatedAssetsDao.saveAll(associatedAssets);
    }

    @Override
    public List<AssetAndTypeDto> getAllByModbusSlaveServer(Integer id) {

        List<AssetAndTypeDto> typeList = new ArrayList<AssetAndTypeDto>();

        Optional<ModbusSlaves> optional = modbusSlavesDao.findById(id);
        if (optional.isPresent()) {
            List<ModbusSlavesAssociatedAssets> assetList = associatedAssetsDao.findBySlaveServerId(optional.get());
            for (ModbusSlavesAssociatedAssets asset : assetList) {
                AssetAndTypeDto newDto = new AssetAndTypeDto();
                newDto.setAssetId(asset.getAssets().getAssetId());
                newDto.setAssetName(asset.getAssets().getAssetName());
                newDto.setAssetType(asset.getAssets().getAssetTypes().getAssetTypeName());
                newDto.setAssetTypeId(asset.getAssets().getAssetTypes().getAssetTypeSeq());
                newDto.setAssociateId(asset.getId());
                typeList.add(newDto);
            }
        }

        return typeList;
    }

    @Override
    public void deleteByModbusSlaveServerId(Integer id) {
        associatedAssetsDao.deleteByServerId(id);
    }

    @Override
    public boolean validateAssociation(List<Integer> ids) {

        boolean valid = false;
        if (ids.size() > 0) {

            List<ModbusSlavesAssociatedAssets> assetReadList = associatedAssetsDao.findByReadMap(ids);
            List<ModbusSlavesAssociatedAssets> assetWriteList = associatedAssetsDao.findByWriteMap(ids);

            if ((assetReadList.size() + assetWriteList.size()) == 0) {
                valid = true;
            }
        }

        return valid;
    }

    @Override
    public void deleteModbusSlavesAssociatedAssetsByIds(List<Integer> ids) {
        associatedAssetsDao.deleteByIdIn(ids);
    }

    @Override
    public List<ModbusSlavesAssociatedAssets> cloneModbusSlavesAssociatedAssets(ModbusSlaves savedModbus,
                                                                                Integer cloneId) {
        List<ModbusSlavesAssociatedAssets> newList = new ArrayList<ModbusSlavesAssociatedAssets>();
        Optional<ModbusSlaves> optAssetList = modbusSlavesDao.findById(cloneId);
        if (optAssetList.isPresent()) {
            List<ModbusSlavesAssociatedAssets> assetList = associatedAssetsDao.findBySlaveServerId(optAssetList.get());
            for (ModbusSlavesAssociatedAssets assoAsset : assetList) {
                ModbusSlavesAssociatedAssets newObj = new ModbusSlavesAssociatedAssets();
                newObj.setId(null);
                newObj.setSlaveServerId(savedModbus);
                newObj.setAssets(assoAsset.getAssets());
                newList.add(newObj);
            }
        }
        return (List<ModbusSlavesAssociatedAssets>) associatedAssetsDao.saveAll(newList);
    }

    public boolean checkForAssetId(String assetId, Integer modbusId) {

        List<AssetAndTypeDto> assoList = getAllByModbusSlaveServer(modbusId);
        for (AssetAndTypeDto dto : assoList) {
            if (dto.getAssetId().equals(assetId)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkForMeasurement(String measurement, String assetId, Integer modbusId) {

        List<ModbusSlavesAssociatedAssets> associatedAsset = associatedAssetsDao.findBySlaveServerIdAndAssetId(modbusId,
                assetId);

        if (associatedAsset.size() == 1) {

            Integer asetTypeSeq = associatedAsset.get(0).getAssets().getAssetTypes().getAssetTypeSeq();
            List<AssetTypeMeasurement> mesList = deviceManagementService
                    .findByAssetTypeSeqAndMeasurementName(asetTypeSeq, measurement);

            if (mesList.size() == 1) {
                return true;
            }
            return false;
        }

        return false;
    }

}