/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lnt.ems.evse.config.SecureActionDefinition;
import com.lnt.ems.evse.constants.AuditCodes;
import com.lnt.ems.evse.constants.CommonMessages;
import com.lnt.ems.evse.constants.RESTUrls;
import com.lnt.ems.evse.dto.AssetAndTypeDto;
import com.lnt.ems.evse.dto.ModbusSlavesAssociatedAssetsDto;
import com.lnt.ems.evse.dto.ResultModel;
import com.lnt.ems.evse.entity.ModbusSlavesAssociatedAssets;
import com.lnt.ems.evse.services.ModbusSlavesAssociatedAssetsService;

import java.util.List;

@RestController
@RequestMapping(value = RESTUrls.MODBUS_SLAVE_ASSETS)
public class ModbusSlavesAssociatedAssetsController {
    private static final Logger logger = LoggerFactory.getLogger(ModbusSlavesAssociatedAssetsController.class);

    @Autowired
    private ModbusSlavesAssociatedAssetsService modbusSlavesAssociatedAssetsService;

    @GetMapping(value = RESTUrls.GET_BY_ID_MODBUS_SLAVE_ASSETS, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_BY_ID_MODBUS_SLAVE_ASSETS)
    public ResponseEntity<ResultModel> getByIdModbusSlavesAssociatedAssets(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting modbus associated assets By id ...");
        try {
            ModbusSlavesAssociatedAssets response = modbusSlavesAssociatedAssetsService.getById(id);
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.GETTING_DATA_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);

            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }


    @GetMapping(value = RESTUrls.GET_ALL_MODBUS_SLAVE_ASSETS, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_MODBUS_SLAVE_ASSETS)
    public ResponseEntity<ResultModel> getAllModbusSlavesAssociatedAssets() {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All modbus associated assets ...");
        try {
            List<ModbusSlavesAssociatedAssets> response = modbusSlavesAssociatedAssetsService
                    .getAllModbusSlavesAssociatedAssets();
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.GETTING_DATA_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);

            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @PostMapping(value = RESTUrls.SAVE_MODBUS_SLAVE_ASSETS, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.SAVE_MODBUS_SLAVE_ASSETS)
    public ResponseEntity<ResultModel> saveModbusSlavesAssociatedAssets(
            @RequestBody ModbusSlavesAssociatedAssetsDto modbusSlavesAssociatedAssetsDto) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving Modbus associated assets ...");
        try {
            ModbusSlavesAssociatedAssets response = modbusSlavesAssociatedAssetsService
                    .saveModbusSlavesAssociatedAssets(modbusSlavesAssociatedAssetsDto);
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.SAVED_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);

            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @PostMapping(value = RESTUrls.SAVE_ALL_MODBUS_SLAVE_ASSETS, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.SAVE_ALL_MODBUS_SLAVE_ASSETS)
    public ResponseEntity<ResultModel> saveAllModbusSlavesAssociatedAssets(
            @RequestBody ModbusSlavesAssociatedAssetsDto modbusSlavesAssociatedAssetsDtos) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving Modbus associated assets ...");
        try {
            List<ModbusSlavesAssociatedAssets> response = modbusSlavesAssociatedAssetsService
                    .saveAll(modbusSlavesAssociatedAssetsDtos);
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.SAVED_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);

            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);

    }

    @DeleteMapping(value = RESTUrls.DELETE_BY_ID_MODBUS_SLAVE_ASSETS, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_BY_ID_MODBUS_SLAVE_ASSETS)
    public ResponseEntity<ResultModel> deleteByIdModbusSlavesAssociatedAssets(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbus associated assets By id ...");
        try {
            modbusSlavesAssociatedAssetsService.deleteModbusSlavesAssociatedAssetsById(id);
            String response = CommonMessages.SUCCESSFULLY_DELETED;
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.DELETE_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);

            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @GetMapping(value = RESTUrls.GET_MODBUS_SLAVE_ASSETS_BY_SERVER, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_MODBUS_SLAVE_ASSETS_BY_SERVER)
    public ResponseEntity<ResultModel> getModbusSlavesAssociatedAssetsByServer(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All modbus associated assets ...");
        try {
            List<AssetAndTypeDto> response = modbusSlavesAssociatedAssetsService.getAllByModbusSlaveServer(id);
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.GETTING_DATA_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);

            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @DeleteMapping(value = RESTUrls.DELETE_ALL_MODBUS_SLAVE_ASSETS_BY_SERVER_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_ALL_MODBUS_SLAVE_ASSETS_BY_SERVER_ID)
    public ResponseEntity<ResultModel> deleteAllModbusSlavesAssociatedAssetsByServerId(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbus associated assets By server id ...");
        try {
            modbusSlavesAssociatedAssetsService.deleteByModbusSlaveServerId(id);
            String response = CommonMessages.SUCCESSFULLY_DELETED;
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.DELETE_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);

            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @PostMapping(value = RESTUrls.DELETE_BY_ID_MODBUS_SLAVE_ASSETS_IN, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_BY_ID_MODBUS_SLAVE_ASSETS_IN)
    public ResponseEntity<ResultModel> deleteByIdModbusSlavesAssociatedAssetsIn(@RequestBody List<Integer> ids) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbus associated assets By Muliple id ...");
        try {
            boolean valid = modbusSlavesAssociatedAssetsService.validateAssociation(ids);
            if (valid) {
                modbusSlavesAssociatedAssetsService.deleteModbusSlavesAssociatedAssetsByIds(ids);
                String response = CommonMessages.SUCCESSFULLY_DELETED;
                resultModel.setData(response);
                resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            } else {
                resultModel.setData("Cannot remove mapped asset(s).");
                resultModel.setMessage(CommonMessages.MSG_ERROR);
            }

            logger.info(CommonMessages.DELETE_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

}
