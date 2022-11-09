/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnt.ems.evse.config.SecureActionDefinition;
import com.lnt.ems.evse.constants.AuditCodes;
import com.lnt.ems.evse.constants.CommonMessages;
import com.lnt.ems.evse.constants.RESTUrls;
import com.lnt.ems.evse.dto.ModbusAssetMeasReadRegistersMapDto;
import com.lnt.ems.evse.dto.ModbusAssetMeasWriteRegistersMapDto;
import com.lnt.ems.evse.dto.ResultModel;
import com.lnt.ems.evse.services.FileService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = RESTUrls.FILE_DATA)
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    //	@ApiOperation(value = "upload Read Asset Map")
    @PostMapping(value = RESTUrls.UPLOAD_READ_ASSET_MAP, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.UPLOAD_READ_ASSET_MAP)
    public ResponseEntity<ResultModel> uploadReadAssetMap(@RequestParam("id") Integer id,
                                                          @RequestPart("file") MultipartFile file) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving Read Asset Map");
        try {
            JSONObject response = fileService.uploadReadAssetMap(id, file);
            JSONArray jsonList = (JSONArray) response.get("assetMeasMapDtoList");
            ObjectMapper objectMapper = new ObjectMapper();
            List<ModbusAssetMeasReadRegistersMapDto> mapList = objectMapper.readValue(jsonList.toString(),
                    new TypeReference<List<ModbusAssetMeasReadRegistersMapDto>>() {
                    });
            resultModel.setData(mapList);
            resultModel.setMessage(response.get("status").toString());

        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error("Failed to save Read registers");
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    //	@ApiOperation(value = "upload Write Asset Map")
    @PostMapping(value = RESTUrls.UPLOAD_WRITE_ASSET_MAP, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.UPLOAD_WRITE_ASSET_MAP)
    public ResponseEntity<ResultModel> uploadWriteAssetMap(@RequestParam("id") Integer id,
                                                           @RequestPart("file") MultipartFile file) {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving Write Asset Map");
        try {
            JSONObject response = fileService.uploadWriteAssetMap(id, file);
            JSONArray jsonList = (JSONArray) response.get("assetMeasMapDtoList");
            ObjectMapper objectMapper = new ObjectMapper();
            List<ModbusAssetMeasWriteRegistersMapDto> mapList = objectMapper.readValue(jsonList.toString(),
                    new TypeReference<List<ModbusAssetMeasWriteRegistersMapDto>>() {
                    });
            resultModel.setData(mapList);
            resultModel.setMessage(response.get("status").toString());

        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error("Failed to save Write registers");
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    //	@ApiOperation(value = "get json file")
    @GetMapping(value = RESTUrls.JSON_FILE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SecureActionDefinition(code = AuditCodes.JSON_FILE)
    public ResponseEntity<ResultModel> saveJson() {
        ResultModel resultModel = new ResultModel();
        logger.info("Saving json file");
        try {
            JSONObject response = fileService.saveJson();
            resultModel.setData(response.toMap());
            resultModel.setMessage(CommonMessages.SUCCESS);
        } catch (Exception e) {
            resultModel.setMessage(CommonMessages.MSG_ERROR);
            logger.error("Failed to get json file");
            return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

}
