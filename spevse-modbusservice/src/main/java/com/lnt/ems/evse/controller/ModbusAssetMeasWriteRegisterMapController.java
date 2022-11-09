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
import com.lnt.ems.evse.dto.ModbusAssetMeasWriteRegistersMapDto;
import com.lnt.ems.evse.dto.ResultModel;
import com.lnt.ems.evse.entity.ModbusAssetMeasWriteRegistersMap;
import com.lnt.ems.evse.services.ModbusAssetMeasWriteRegisterService;

import java.util.List;

@RestController
@RequestMapping(value = RESTUrls.MODBUS_ASSET_MEAS_WRITE_MAP)
public class ModbusAssetMeasWriteRegisterMapController {
    private static final Logger logger = LoggerFactory.getLogger(ModbusAssetMeasWriteRegisterMapController.class);

    @Autowired
    private ModbusAssetMeasWriteRegisterService modbusAssetMeasWriteRegsiterService;

    @GetMapping(value = RESTUrls.GET_BY_ID_MODBUS_ASSET_MEAS_WRITE_MAP, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_BY_ID_MODBUS_ASSET_MEAS_WRITE_MAP)
    public ResponseEntity<ResultModel> getByIdModbusAssetMeasWriteRegisters(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting modbusAssetMeasWriteRegsiter By id ...");
        try {
            ModbusAssetMeasWriteRegistersMap response = modbusAssetMeasWriteRegsiterService
                    .getModbusAssetMeasWriteRegistersById(id);
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

    @GetMapping(value = RESTUrls.GET_ALL_MODBUS_ASSET_MEAS_WRITE_MAP, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_MODBUS_ASSET_MEAS_WRITE_MAP)
    public ResponseEntity<ResultModel> getAllModbusAssetMeasWriteRegisters() {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All ModbusAssetMeasWriteRegisters ...");
        try {
            List<ModbusAssetMeasWriteRegistersMap> response = modbusAssetMeasWriteRegsiterService
                    .getAllModbusAssetMeasWriteRegisters();
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

    @PostMapping(value = RESTUrls.SAVE_MODBUS_ASSET_MEAS_WRITE_MAP, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.SAVE_MODBUS_ASSET_MEAS_WRITE_MAP)
    public ResponseEntity<ResultModel> saveModbusAssetMeasWriteRegisters(
            @RequestBody ModbusAssetMeasWriteRegistersMapDto dto) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving modbusAssetMeasWrite Register ...");
        try {
            ModbusAssetMeasWriteRegistersMap map = new ModbusAssetMeasWriteRegistersMap();
            BeanUtils.copyProperties(dto, map, "errors");
            ModbusAssetMeasWriteRegistersMap response = modbusAssetMeasWriteRegsiterService
                    .saveModbusAssetMeasWriteRegisters(map);
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.SAVED_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @DeleteMapping(value = RESTUrls.DELETE_BY_ID_MODBUS_ASSET_MEAS_WRITE_MAP, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_BY_ID_MODBUS_ASSET_MEAS_WRITE_MAP)
    public ResponseEntity<ResultModel> deleteByIdModbusAssetMeasWriteRegisters(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbusAssetMeasWriteRegsiter By id ...");
        try {
            modbusAssetMeasWriteRegsiterService.deleteModbusAssetMeasWriteRegistersById(id);
            String response = CommonMessages.SUCCESSFULLY_DELETED;
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.DELETE_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }


    @GetMapping(value = RESTUrls.GET_ALL_WRITE_MAP_BY_MODBUS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_WRITE_MAP_BY_MODBUS_ID)
    public ResponseEntity<ResultModel> getAllModbusAssetMeasWriteMapByModbuId(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All Associated Write Map by modbus  ...");
        try {
            List<ModbusAssetMeasWriteRegistersMap> response = modbusAssetMeasWriteRegsiterService.getAllByModbusId(id);
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info(CommonMessages.GETTING_DATA_SUCCESSFULLY);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

}
