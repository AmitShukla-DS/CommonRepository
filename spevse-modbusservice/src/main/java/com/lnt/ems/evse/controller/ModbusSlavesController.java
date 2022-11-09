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
import com.lnt.ems.evse.dto.ModbusSlavesDTO;
import com.lnt.ems.evse.dto.ResultModel;
import com.lnt.ems.evse.entity.ModbusSlaves;
import com.lnt.ems.evse.services.ModbusSlavesService;

import java.util.List;

@RestController
@RequestMapping(value = RESTUrls.MODBUS_SLAVE)
public class ModbusSlavesController {
    private static final Logger logger = LoggerFactory.getLogger(ModbusSlavesController.class);

    @Autowired
    private ModbusSlavesService modbusSlavesService;


    @GetMapping(value = RESTUrls.GET_BY_ID_MODBUS_SLAVE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_BY_ID_MODBUS_SLAVE)
    public ResponseEntity<ResultModel> getByIdModbusSlaves(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting modbus slaves By id ...");
        try {
            ModbusSlaves response = modbusSlavesService.getModbusSlavesById(id);
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

    @GetMapping(value = RESTUrls.GET_ALL_MODBUS_SLAVE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.GET_ALL_MODBUS_SLAVE)
    public ResponseEntity<ResultModel> getAllModbusSlaves() {
        ResultModel resultModel = new ResultModel();
        logger.info("Getting All modbus slaves ...");
        try {
            List<ModbusSlaves> response = modbusSlavesService.getAllModbusSlaves();
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

    @PostMapping(value = RESTUrls.SAVE_MODBUS_SLAVE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.SAVE_MODBUS_SLAVE)
    public ResponseEntity<ResultModel> saveModbusSlaves(@RequestBody ModbusSlavesDTO dto) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving Modbus Slaves ...");
        try {
            ModbusSlaves modbusSlaves = new ModbusSlaves();
            BeanUtils.copyProperties(dto, modbusSlaves, "modbusSlavesReadRegisters", "modbusSlavesWriteRegisters");
            String valid = modbusSlavesService.validateModbusSalve(modbusSlaves, false);
            if (valid.equals("valid")) {
                ModbusSlaves response = modbusSlavesService.saveModbusSlaves(modbusSlaves);
                resultModel.setData(response);
                resultModel.setMessage(CommonMessages.MSG_SUCCESS);
                logger.info("Saved Data Successfully.......");
            } else {
                resultModel.setData(valid);
                resultModel.setMessage(CommonMessages.FAILED);
                logger.info("Failed To Save Data.......");
            }

        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @PostMapping(value = RESTUrls.CLONE_MODBUS_SLAVE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.CLONE_MODBUS_SLAVE)
    public ResponseEntity<ResultModel> cloneModbusSlaves(@RequestBody ModbusSlavesDTO modbusSlavesDto) {
        ResultModel resultModel = new ResultModel();
        logger.info("Clone Modbus Slaves ...");
        try {
            ModbusSlaves modbusSlaves = new ModbusSlaves();
            BeanUtils.copyProperties(modbusSlavesDto, modbusSlaves, "cloneId");
            String valid = modbusSlavesService.validateModbusSalve(modbusSlaves, false);
            if (valid.equals("valid")) {
                ModbusSlaves response = modbusSlavesService.cloneModbusSlaves(modbusSlaves,
                        modbusSlavesDto.getCloneId());
                resultModel.setData(response);
                resultModel.setMessage(CommonMessages.MSG_SUCCESS);
                logger.info("Cloneed Data Successfully.......");
            } else {
                resultModel.setData(valid);
                resultModel.setMessage(CommonMessages.FAILED);
                logger.info(valid);
            }

        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @PutMapping(value = RESTUrls.UPDATE_MODBUS_SLAVE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.UPDATE_MODBUS_SLAVE)
    public ResponseEntity<ResultModel> updateModbusSlaves(@RequestBody ModbusSlavesDTO dto) {
        ResultModel resultModel = new ResultModel();
        logger.info("Updateing Modbus Slaves ...");
        try {
            ModbusSlaves modbusSlaves = new ModbusSlaves();
            BeanUtils.copyProperties(dto, modbusSlaves, "modbusSlavesReadRegisters", "modbusSlavesWriteRegisters");
            if (modbusSlaves.getModbusSlaveId() == null) {
                resultModel.setData("modbus Id is null");
                resultModel.setMessage(CommonMessages.FAILED);
                logger.info("Failed To Update Data.......");
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }
            String valid = modbusSlavesService.validateModbusSalve(modbusSlaves, true);
            if (valid.equals("valid")) {
                ModbusSlaves response = modbusSlavesService.saveModbusSlaves(modbusSlaves);
                resultModel.setData(response);
                resultModel.setMessage(CommonMessages.MSG_SUCCESS);
                logger.info("Updated Successfully.......");
            } else {
                resultModel.setData(valid);
                resultModel.setMessage(CommonMessages.FAILED);
                logger.info("Failed To Update Data.......");
            }

        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @PostMapping(value = RESTUrls.ENABLE_MODBUS_SLAVE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.ENABLE_MODBUS_SLAVE)
    public ResponseEntity<ResultModel> enableModbusSlaves(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Enable Disable Modbus Slaves ...");
        try {
            ModbusSlaves response = modbusSlavesService.enableModbusSlavesById(id);
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info("Enable Disable Data Successfully.......");
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @DeleteMapping(value = RESTUrls.DELETE_MODBUS_SLAVE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.DELETE_MODBUS_SLAVE)
    public ResponseEntity<ResultModel> deleteByIdModbusSlaves(@RequestParam("id") Integer id) {
        ResultModel resultModel = new ResultModel();
        logger.info("Deleting modbus slaves By id ...");
        try {
            modbusSlavesService.deleteModbusSlavesById(id);
            String response = "successfully deleted";
            resultModel.setData(response);
            resultModel.setMessage(CommonMessages.MSG_SUCCESS);
            logger.info("Deleted Data Successfully.......");
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error(e.toString());
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

}
