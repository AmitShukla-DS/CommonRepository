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
import com.lnt.ems.evse.dto.ModbusSlavesWriteRegistersDto;
import com.lnt.ems.evse.dto.ResultModel;
import com.lnt.ems.evse.entity.ModbusSlavesWriteRegisters;
import com.lnt.ems.evse.services.ModbusSlavesWriteRegisterService;

import java.util.List;

@RestController
@RequestMapping(value = RESTUrls.MODBUS_SLAVE_WRITE)
public class ModbusSlavesWriteRegisterController {
    private static final Logger logger = LoggerFactory.getLogger(ModbusSlavesWriteRegisterController.class);

    @Autowired
    private ModbusSlavesWriteRegisterService modbusSlavesWriteRegsiterService;

    @GetMapping(value = RESTUrls.GET_BY_ID_MODBUS_SLAVE_WRITE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_BY_ID_MODBUS_SLAVE_WRITE)
    public ResponseEntity<ResultModel> getByIdModbusSlavesWriteRegisters(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting modbusSlavesWriteRegsiter By id ...");
        try {
            ModbusSlavesWriteRegisters response = modbusSlavesWriteRegsiterService
                    .getModbusSlavesWriteRegistersById(id);
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

    @GetMapping(value = RESTUrls.GET_ALL_MODBUS_SLAVE_WRITE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_MODBUS_SLAVE_WRITE)
    public ResponseEntity<ResultModel> getAllModbusSlavesWriteRegisters() {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All ModbusSlavesWriteRegisters ...");
        try {
            List<ModbusSlavesWriteRegisters> response = modbusSlavesWriteRegsiterService
                    .getAllModbusSlavesWriteRegisters();
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

    @PostMapping(value = RESTUrls.SAVE_MODBUS_SLAVE_WRITE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.SAVE_MODBUS_SLAVE_WRITE)
    public ResponseEntity<ResultModel> saveModbusSlavesWriteRegisters(@RequestBody ModbusSlavesWriteRegistersDto dto) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving modbusSlavesWrite Register ...");
        try {
            ModbusSlavesWriteRegisters modbusSlavesWriteRegisters = new ModbusSlavesWriteRegisters();
            BeanUtils.copyProperties(dto, modbusSlavesWriteRegisters);
            ModbusSlavesWriteRegisters response = modbusSlavesWriteRegsiterService
                    .saveModbusSlavesWriteRegisters(modbusSlavesWriteRegisters);
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

    @PostMapping(value = RESTUrls.SAVE_ALL_MODBUS_SLAVE_WRITE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.SAVE_ALL_MODBUS_SLAVE_WRITE)
    public ResponseEntity<ResultModel> saveAllModbusSlavesWriteRegisters(
            @RequestBody List<ModbusSlavesWriteRegisters> modbusSlaves) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving modbusSlavesWrite Register ...");
        try {
            List<ModbusSlavesWriteRegisters> response = modbusSlavesWriteRegsiterService.saveAll(modbusSlaves);
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

    @DeleteMapping(value = RESTUrls.DELETE_BY_ID_MODBUS_SLAVE_WRITE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_BY_ID_MODBUS_SLAVE_WRITE)
    public ResponseEntity<ResultModel> deleteByIdModbusSlavesWriteRegisters(@RequestParam("id") List<Integer> ids) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbusSlavesWriteRegsiter By id ...");
        try {
            boolean valid = modbusSlavesWriteRegsiterService.validateMapping(ids);
            if (valid) {
                modbusSlavesWriteRegsiterService.deleteModbusSlavesWriteRegistersById(ids);
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

    @GetMapping(value = RESTUrls.GET_ALL_MODBUS_WRITE_BY_SLAVE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_MODBUS_WRITE_BY_SLAVE_ID)
    public ResponseEntity<ResultModel> getModbusWriteRegisterByModbusSlavesId(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting Modbus Slaves Write Regsiter By Modbus Salve id ...");
        try {
            List<ModbusSlavesWriteRegisters> response = modbusSlavesWriteRegsiterService
                    .getAllModbusWriteRegistersBySlaveId(id);
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

    @DeleteMapping(value = RESTUrls.DELETE_MODBUS_SLAVE_WRITE_BY_MODEBUS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_MODBUS_SLAVE_WRITE_BY_MODEBUS_ID)
    public ResponseEntity<ResultModel> deleteModbusSlaveWriteByModebusId(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbusSlavesWriteRegsiter By modbus slave id ...");
        try {

            boolean valid = modbusSlavesWriteRegsiterService.validateMapping(id);
            if (valid) {
                modbusSlavesWriteRegsiterService.deleteWriteRegistersByModbusSlavesId(id);
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
            logger.error(String.format(e.toString()));
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @GetMapping(value = RESTUrls.VALIDATE_WRITE_REGISTER_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.VALIDATE_WRITE_REGISTER_MAPPING)
    public ResponseEntity<ResultModel> validateWriteRegisterMapping(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("checking mapping for write regsiter by id ...");
        try {
            ModbusSlavesWriteRegisters writeRegister = modbusSlavesWriteRegsiterService
                    .getModbusSlavesWriteRegistersById(id);
            if (writeRegister != null) {
                boolean response = modbusSlavesWriteRegsiterService.validateMapping(writeRegister);
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
