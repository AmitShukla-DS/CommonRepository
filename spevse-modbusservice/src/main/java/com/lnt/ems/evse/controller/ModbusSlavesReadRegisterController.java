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
import com.lnt.ems.evse.dto.ModbusSlavesReadRegistersDto;
import com.lnt.ems.evse.dto.ResultModel;
import com.lnt.ems.evse.entity.ModbusSlavesReadRegisters;
import com.lnt.ems.evse.services.ModbusSlavesReadRegisterService;

import java.util.List;

@RestController
@RequestMapping(value = RESTUrls.MODBUS_SLAVE_READ)
public class ModbusSlavesReadRegisterController {
    private static final Logger logger = LoggerFactory.getLogger(ModbusSlavesReadRegisterController.class);

    @Autowired
    private ModbusSlavesReadRegisterService modbusSlavesReadRegsiterService;

    @GetMapping(value = RESTUrls.GET_BY_ID_MODBUS_SLAVE_READ, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_BY_ID_MODBUS_SLAVE_READ)
    public ResponseEntity<ResultModel> getByIdModbusSlavesReadRegisters(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting modbusSlavesReadRegsiter By id ...");
        try {
            ModbusSlavesReadRegisters response = modbusSlavesReadRegsiterService.getModbusSlavesReadRegistersById(id);
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

    @GetMapping(value = RESTUrls.GET_ALL_MODBUS_SLAVE_READ, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_MODBUS_SLAVE_READ)
    public ResponseEntity<ResultModel> getAllModbusSlavesReadRegisters() {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All ModbusSlavesReadRegisters ...");
        try {
            List<ModbusSlavesReadRegisters> response = modbusSlavesReadRegsiterService
                    .getAllModbusSlavesReadRegisters();
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

    @PostMapping(value = RESTUrls.SAVE_MODBUS_SLAVE_READ, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.SAVE_MODBUS_SLAVE_READ)
    public ResponseEntity<ResultModel> saveModbusSlavesReadRegisters(@RequestBody ModbusSlavesReadRegistersDto dto) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving modbusSlavesRead Register ...");
        try {
            ModbusSlavesReadRegisters modbusSlavesReadRegisters = new ModbusSlavesReadRegisters();
            BeanUtils.copyProperties(dto, modbusSlavesReadRegisters);
            ModbusSlavesReadRegisters response = modbusSlavesReadRegsiterService
                    .saveModbusSlavesReadRegisters(modbusSlavesReadRegisters);
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

    @PostMapping(value = RESTUrls.SAVE_ALL_MODBUS_SLAVE_READ, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.SAVE_ALL_MODBUS_SLAVE_READ)
    public ResponseEntity<ResultModel> saveAllModbusSlavesReadRegisters(
            @RequestBody List<ModbusSlavesReadRegisters> modbusSlaves) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving modbusSlavesRead Register ...");
        try {
            List<ModbusSlavesReadRegisters> response = modbusSlavesReadRegsiterService.saveAll(modbusSlaves);
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

    @DeleteMapping(value = RESTUrls.DELETE_BY_ID_MODBUS_SLAVE_READ, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_BY_ID_MODBUS_SLAVE_READ)
    public ResponseEntity<ResultModel> deleteByIdModbusSlavesReadRegisters(@RequestParam("id") List<Integer> ids) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbusSlavesReadRegsiter By id ...");
        try {
            boolean valid = modbusSlavesReadRegsiterService.validateMapping(ids);
            if (valid) {
                modbusSlavesReadRegsiterService.deleteModbusSlavesReadRegistersById(ids);
                String response = CommonMessages.SUCCESSFULLY_DELETED;
                resultModel.setData(response);
                resultModel.setMessage(CommonMessages.MSG_SUCCESS);
                logger.info(CommonMessages.DELETE_SUCCESSFULLY);
            } else {
                resultModel.setData("Cannot delete mapped register.");
                resultModel.setMessage(CommonMessages.MSG_ERROR);
            }
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @GetMapping(value = RESTUrls.GET_ALL_MODBUS_READ_BY_SLAVE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_MODBUS_READ_BY_SLAVE_ID)
    public ResponseEntity<ResultModel> getModbusReadRegisterByModbusSlavesId(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting Modbus Slaves Read Regsiter By Modbus Salve id ...");
        try {
            List<ModbusSlavesReadRegisters> response = modbusSlavesReadRegsiterService
                    .getAllModbusReadRegistersBySlaveId(id);
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

    @DeleteMapping(value = RESTUrls.DELETE_MODBUS_SLAVE_READ_BY_MODEBUS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_MODBUS_SLAVE_READ_BY_MODEBUS_ID)
    public ResponseEntity<ResultModel> deleteModbusSlaveReadByModebusId(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbusSlavesReadRegsiter By modbus slave id ...");
        try {

            boolean valid = modbusSlavesReadRegsiterService.validateMapping(id);
            if (valid) {
                modbusSlavesReadRegsiterService.deleteReadRegistersByModbusSlavesId(id);
                String response = CommonMessages.SUCCESSFULLY_DELETED;
                resultModel.setData(response);
                resultModel.setMessage(CommonMessages.MSG_SUCCESS);
                logger.info(CommonMessages.DELETE_SUCCESSFULLY);
            } else {
                resultModel.setData("Cannot update mapped register.");
                resultModel.setMessage(CommonMessages.MSG_ERROR);
            }

        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @GetMapping(value = RESTUrls.VALIDATE_READ_REGISTER_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.VALIDATE_READ_REGISTER_MAPPING)
    public ResponseEntity<ResultModel> validateReadRegisterMapping(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("checking mapping for read regsiter by id ...");
        try {
            ModbusSlavesReadRegisters readRegister = modbusSlavesReadRegsiterService
                    .getModbusSlavesReadRegistersById(id);
            if (readRegister != null) {
                boolean response = modbusSlavesReadRegsiterService.validateMapping(readRegister);
                resultModel.setData(response);
                resultModel.setMessage(CommonMessages.MSG_SUCCESS);
                logger.info("checked mapping Successfully.......");
            } else {
                resultModel.setData(null);
                resultModel.setMessage(CommonMessages.DATA_FAILED);
                logger.info(CommonMessages.DATA_FAILED);
            }

        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

}
