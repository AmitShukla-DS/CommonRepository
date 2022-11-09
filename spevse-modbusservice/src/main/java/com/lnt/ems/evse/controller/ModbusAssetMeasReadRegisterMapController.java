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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lnt.ems.evse.config.SecureActionDefinition;
import com.lnt.ems.evse.constants.AuditCodes;
import com.lnt.ems.evse.constants.CommonMessages;
import com.lnt.ems.evse.constants.RESTUrls;
import com.lnt.ems.evse.dto.ModbusAssetMeasReadRegistersMapDto;
import com.lnt.ems.evse.dto.ResultModel;
import com.lnt.ems.evse.entity.ModbusAssetMeasReadRegistersMap;
import com.lnt.ems.evse.services.ModbusAssetMeasReadRegisterService;

import java.util.List;

@RestController
@RequestMapping(value = RESTUrls.MODBUS_ASSET_MEAS_READ_MAP)
public class ModbusAssetMeasReadRegisterMapController {
    private static final Logger logger = LoggerFactory.getLogger(ModbusAssetMeasReadRegisterMapController.class);

    @Autowired
    private ModbusAssetMeasReadRegisterService modbusAssetMeasReadRegsiterService;

    @GetMapping(value = RESTUrls.GET_BY_ID_MODBUS_ASSET_MEAS_READ_MAP, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_BY_ID_MODBUS_ASSET_MEAS_READ_MAP)
    public ResponseEntity<ResultModel> getByIdModbusAssetMeasReadRegisters(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting modbusAssetMeasReadRegsiter By id ...");
        try {
            ModbusAssetMeasReadRegistersMap response = modbusAssetMeasReadRegsiterService
                    .getModbusAssetMeasReadRegistersById(id);
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

    @GetMapping(value = RESTUrls.GET_ALL_MODBUS_ASSET_MEAS_READ_MAP, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_MODBUS_ASSET_MEAS_READ_MAP)
    public ResponseEntity<ResultModel> getAllModbusAssetMeasReadRegisters() {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All ModbusAssetMeasReadRegisters ...");
        try {
            List<ModbusAssetMeasReadRegistersMap> response = modbusAssetMeasReadRegsiterService
                    .getAllModbusAssetMeasReadRegisters();
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

    @PostMapping(value = RESTUrls.SAVE_MODBUS_ASSET_MEAS_READ_MAP, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.SAVE_MODBUS_ASSET_MEAS_READ_MAP)
    public ResponseEntity<ResultModel> saveModbusAssetMeasReadRegisters(
            @RequestBody ModbusAssetMeasReadRegistersMapDto dto) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving modbusAssetMeasRead Register ...");
        try {
            ModbusAssetMeasReadRegistersMap map = new ModbusAssetMeasReadRegistersMap();
            BeanUtils.copyProperties(dto, map, "errors");
            ModbusAssetMeasReadRegistersMap response = modbusAssetMeasReadRegsiterService
                    .saveModbusAssetMeasReadRegisters(map);
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info("Saved Data Successfully.......");
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);

            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);

        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @DeleteMapping(value = RESTUrls.DELETE_BY_ID_MODBUS_ASSET_MEAS_READ_MAP, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_BY_ID_MODBUS_ASSET_MEAS_READ_MAP)
    public ResponseEntity<ResultModel> deleteByIdModbusAssetMeasReadRegisters(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbusAssetMeasReadRegsiter By id ...");
        try {
            modbusAssetMeasReadRegsiterService.deleteModbusAssetMeasReadRegistersById(id);
            String response = "successfully deleted";
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info("Deleted Data Successfully.......");
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);

            logger.error(CommonMessages.ERROR_OCCURE, e.getMessage());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);

        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = RESTUrls.UNIQUE_MODBUS_MEASUREMENT)
    @SecureActionDefinition(code = AuditCodes.UNIQUE_MODBUS_MEASUREMENT)
    public ResponseEntity<ResultModel> getModbusReadRegisterMeasurementsUnique() {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All Modbus Read Register Measurements .......!");
        try {
            List<String> response = modbusAssetMeasReadRegsiterService.getUniqueModbusReadRegisterMeasurementList();
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

    @GetMapping(value = RESTUrls.GET_ALL_READ_MAP_BY_MODBUS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_READ_MAP_BY_MODBUS_ID)
    public ResponseEntity<ResultModel> getAllModbusAssetMeasReadMapByModbuId(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All Associated Read Map by modbus  ...");
        try {
            List<ModbusAssetMeasReadRegistersMap> response = modbusAssetMeasReadRegsiterService.getAllByModbusId(id);
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

}
