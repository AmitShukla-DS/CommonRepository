/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.lnt.ems.evse.constants.CommonMessages;
import com.lnt.ems.evse.dao.AssetTypeMeasurementDao;
import com.lnt.ems.evse.dto.AssetAndTypeDto;
import com.lnt.ems.evse.dto.ModbusAssetMeasReadRegistersMapDto;
import com.lnt.ems.evse.dto.ModbusAssetMeasWriteRegistersMapDto;
import com.lnt.ems.evse.entity.*;
import com.lnt.ems.evse.services.FileService;
import com.lnt.ems.evse.services.ModbusSlavesService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    ModbusSlavesService modbusSlavesService;

    @Autowired
    ModbusSlavesReadRegisterServiceImpl slavesReadRegisterServiceImpl;

    @Autowired
    ModbusSlavesWriteRegisterServiceImpl slavesWriteRegisterServiceImpl;

    @Autowired
    ModbusAssetMeasReadRegisterServiceImpl assetMeasReadRegisterServiceImpl;

    @Autowired
    ModbusAssetMeasWriteRegisterServiceImpl assetMeasWriteRegisterServiceImpl;

    @Autowired
    ModbusSlavesAssociatedAssetsServiceImpl assetsServiceImpl;

    @Autowired
    HmiDropdownDataServiceImpl dataServiceImpl;

    @Autowired
    AssetTypeMeasurementDao measurementDao;

    public static final String TYPE = "text/csv";

    public boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    /// Read Map
    @Override
    public JSONObject uploadReadAssetMap(Integer id, MultipartFile file) {
        JSONObject response = new JSONObject();
        response.put(CommonMessages.STATUS, false);
        boolean isValid = true;
        List<ModbusAssetMeasReadRegistersMapDto> assetMeasMapDtoList = new ArrayList<ModbusAssetMeasReadRegistersMapDto>();
        List<ModbusAssetMeasReadRegistersMap> assetMeasMapList = new ArrayList<ModbusAssetMeasReadRegistersMap>();
        List<HMIDropdownData> hmiData = dataServiceImpl.getAllHmiDropdownData();
        try {
            logger.info("Validating File");

            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {
                    ModbusAssetMeasReadRegistersMapDto assetMeasMapDto = new ModbusAssetMeasReadRegistersMapDto();
                    ModbusAssetMeasReadRegistersMap assetMeasMap = new ModbusAssetMeasReadRegistersMap();
                    List<String> error = new ArrayList<String>();

                    // check if asset id is present
                    boolean isAssetIdPresent = assetsServiceImpl.checkForAssetId(csvRecord.get(0), id);
                    if (!isAssetIdPresent) {
                        error.add("assetId");
                        isValid = false;
                    }

                    // check if measurement is present
                    boolean isMeasurementPresent = assetsServiceImpl.checkForMeasurement(csvRecord.get(1),
                            csvRecord.get(0), id);
                    if (!isMeasurementPresent) {
                        error.add(CommonMessages.MEASUREMENT);
                        isValid = false;
                    }

                    Integer register = null;
                    String funCodeStr = null;
                    Float scale = null;
                    String commFlag = "";
                    Integer commValue = null;
                    String binaryRead = null;
                    String sizeStr = null;

                    if (csvRecord.get(2).equals("") || csvRecord.get(2) == null) {
                        error.add(CommonMessages.REGISTER);
                        error.add(CommonMessages.DISCRIPTION);
                        error.add(CommonMessages.FUNCTION);
                        error.add("bit");
                        error.add(CommonMessages.SCALING);
                        error.add("size");
                        error.add(CommonMessages.BINARY_READ);
                        error.add(CommonMessages.COMM_VALUE);
                        error.add(CommonMessages.COMM_FLAG);
                        isValid = false;
                    } else {
                        try {
                            register = Integer.parseInt(csvRecord.get(2));
                        } catch (Exception e) {
                            register = null;
                            error.add(CommonMessages.REGISTER);
                            isValid = false;
                        }
                    }

                    if (csvRecord.get(4).equals("") || csvRecord.get(4) == null) {
                        error.add(CommonMessages.FUNCTION);
                        isValid = false;
                    } else {
                        funCodeStr = csvRecord.get(4);
                    }

                    if (csvRecord.get(6).equals("") || csvRecord.get(6) == null) {
                        error.add(CommonMessages.SCALING);
                        isValid = false;
                    } else {
                        try {
                            scale = Float.parseFloat(csvRecord.get(6));
                        } catch (Exception e) {
                            scale = null;
                            error.add(CommonMessages.SCALING);
                            isValid = false;
                        }

                    }

                    if (csvRecord.get(9).equals("") || csvRecord.get(9) == null) {
                        error.add(CommonMessages.COMM_FLAG);
                        isValid = false;
                    } else {
                        commFlag = csvRecord.get(9);
                    }

                    if (csvRecord.get(10).equals("") || csvRecord.get(10) == null) {
                        error.add(CommonMessages.COMM_VALUE);
                        isValid = false;
                    } else {
                        commValue = Integer.parseInt(csvRecord.get(10));
                    }

                    if (csvRecord.get(7).equals("") || csvRecord.get(7) == null) {
                        error.add(CommonMessages.BINARY_READ);
                        binaryRead = "";
                        isValid = false;
                    } else {
                        Optional<HMIDropdownData> bRead = hmiData.stream()
                                .filter(a -> a.getDropdownValue().toCharArray()[0] == csvRecord.get(7).toCharArray()[0]
                                        && a.getType().equals("Binary Read"))
                                .findFirst();
                        binaryRead = csvRecord.get(7);
                        if (!bRead.isPresent()) {
                            error.add(CommonMessages.BINARY_READ);
                            isValid = false;
                        } else {
                            binaryRead = bRead.get().getId().toString();
                        }
                    }

                    Optional<HMIDropdownData> bitReg = hmiData.stream().filter(
                                    a -> Arrays.equals(a.getDropdownValue().toCharArray(), csvRecord.get(8).toCharArray())
                                            && a.getType().equals("Bit Register"))
                            .findFirst();
                    String bitRegStr = csvRecord.get(9);
                    if (!bitReg.isPresent()) {
                        error.add("bit");
                        isValid = false;
                    } else {
                        bitRegStr = bitReg.get().getId().toString();
                    }

                    Optional<HMIDropdownData> funCode = hmiData.stream().filter(
                                    a -> Arrays.equals(a.getDropdownValue().toCharArray(), csvRecord.get(4).toCharArray())
                                            && a.getType().equals("Function Code"))
                            .findFirst();

                    if (!funCode.isPresent()) {
                        error.add(CommonMessages.FUNCTION);
                        isValid = false;
                        funCodeStr = "";
                    } else {
                        funCodeStr = funCode.get().getId().toString();
                    }

                    Optional<HMIDropdownData> size = hmiData.stream().filter(
                                    a -> Arrays.equals(a.getDropdownValue().toCharArray(), csvRecord.get(5).toCharArray())
                                            && a.getType().equals("Size"))
                            .findFirst();

                    if (!size.isPresent()) {
                        error.add("size");
                        isValid = false;
                        sizeStr = csvRecord.get(5);
                    } else {
                        sizeStr = size.get().getId().toString();
                    }

                    // when binary read == N bit register should Bit-0
                    if (binaryRead.equals("8")) {
                        if (!bitRegStr.equals("9")) {
                            error.add("bit");
                            isValid = false;
                        }
                    }

                    for (CSVRecord csvRecord2 : csvRecords) {
                        if (!csvRecord.equals(csvRecord2)) {
                            if (csvRecord.get(0).equals(csvRecord2.get(0))) {

                                // check for duplicate measurement
                                if (csvRecord.get(1).equals(csvRecord2.get(1))) {
                                    error.add(CommonMessages.MEASUREMENT);
                                    isValid = false;
                                }

                                // check for same asset and register combination
                                // functionCode + bit register + binary read
                                if (csvRecord.get(2).equals(csvRecord2.get(2))) {

                                    if (csvRecord.get(4).equals(csvRecord2.get(4))
                                            && csvRecord.get(8).equals(csvRecord2.get(8))
                                            && csvRecord.get(7).equals(csvRecord2.get(7))) {
                                        error.add(CommonMessages.FUNCTION);
                                        error.add(CommonMessages.BINARY_READ);
                                        error.add(CommonMessages.REGISTER);
                                        error.add("bit");
                                        isValid = false;
                                    }

                                }

                            }
                        }
                    }

                    if (!error.contains(CommonMessages.REGISTER)) {

                        List<ModbusSlavesReadRegisters> regList = slavesReadRegisterServiceImpl
                                .getAllByRegistersAndSlaveId(Integer.parseInt(csvRecord.get(2)), id);
                        if (regList.size() == 0) {
                            error.add(CommonMessages.REGISTER);
                            error.add(CommonMessages.DISCRIPTION);
                            error.add(CommonMessages.FUNCTION);
                            error.add("bit");
                            error.add(CommonMessages.SCALING);
                            error.add("size");
                            error.add(CommonMessages.BINARY_READ);
                            error.add(CommonMessages.COMM_VALUE);
                            error.add(CommonMessages.COMM_FLAG);
                            isValid = false;
                        } else {

                            boolean exists = false;
                            for (ModbusSlavesReadRegisters reg : regList) {

                                if (!funCodeStr.equals("") && reg.getFunctionCode() == Integer.parseInt(funCodeStr)
                                        && reg.getBinaryRead().equals(binaryRead)
                                        && reg.getBitRegister().equals(bitRegStr)) {

                                    exists = true;

                                    if (!error.contains("size") && reg.getSize() != Integer.parseInt(sizeStr)) {
                                        error.add("size");
                                        isValid = false;
                                    }
                                    if (!error.contains(CommonMessages.SCALING)
                                            && reg.getScalingFactor() != Float.parseFloat(csvRecord.get(6))) {
                                        error.add(CommonMessages.SCALING);
                                        isValid = false;
                                    }
                                    if (!error.contains(CommonMessages.COMM_FLAG)
                                            && !reg.getCommFlag().equals(csvRecord.get(9))) {
                                        error.add(CommonMessages.COMM_FLAG);
                                        isValid = false;
                                    }
                                    if (!error.contains(CommonMessages.COMM_VALUE)
                                            && reg.getCommValue() != Integer.parseInt(csvRecord.get(10))) {
                                        error.add(CommonMessages.COMM_VALUE);
                                        isValid = false;
                                    }

                                }

                            }

                            if (!exists) {
                                error.add(CommonMessages.FUNCTION);
                                error.add(CommonMessages.BINARY_READ);
                                error.add("bit");
                                isValid = false;
                            }
                        }

                    }

                    // setting values to dto
                    assetMeasMapDto.setId(null);
                    assetMeasMapDto.setAssetId(csvRecord.get(0));
                    assetMeasMapDto.setMeasurementName(csvRecord.get(1));
                    assetMeasMapDto.setRegister(register);
                    assetMeasMapDto.setDescription(csvRecord.get(3));
                    assetMeasMapDto.setFunctionCode(csvRecord.get(4));
                    assetMeasMapDto.setSize(csvRecord.get(5));
                    assetMeasMapDto.setScalingFactor(scale);
                    assetMeasMapDto.setBinaryRead(csvRecord.get(7));
                    assetMeasMapDto.setMask(null);
                    assetMeasMapDto.setBitRegister(csvRecord.get(8));
                    assetMeasMapDto.setCommFlag(commFlag);
                    assetMeasMapDto.setCommValue(commValue);
                    assetMeasMapDto.setModbusId(id);
                    assetMeasMapDto.setErrors(error);
                    assetMeasMapDtoList.add(assetMeasMapDto);

                    if (error.isEmpty()) {

                        assetMeasMap.setId(null);
                        assetMeasMap.setAssetId(csvRecord.get(0));
                        assetMeasMap.setMeasurementName(csvRecord.get(1));
                        assetMeasMap.setRegister(Integer.parseInt(csvRecord.get(2)));
                        assetMeasMap.setDescription(csvRecord.get(3));
                        assetMeasMap.setFunctionCode(Integer.parseInt(funCodeStr));
                        assetMeasMap.setSize(Integer.parseInt(sizeStr));
                        assetMeasMap.setScalingFactor(Float.parseFloat(csvRecord.get(6)));
                        assetMeasMap.setBinaryRead(binaryRead);
                        assetMeasMap.setMask(null);
                        assetMeasMap.setBitRegister(bitRegStr);
                        assetMeasMap.setCommFlag(csvRecord.get(9));
                        assetMeasMap.setCommValue(Integer.parseInt(csvRecord.get(10)));
                        assetMeasMap.setModbusId(id);
                        assetMeasMapList.add(assetMeasMap);

                    }

                }

                if (isValid) {
                    // saving data
                    assetMeasReadRegisterServiceImpl.deleteBymodbusId(id);
                    saveReadmap(assetMeasMapList, id);
                    response.put(CommonMessages.STATUS, isValid);
                }

            } catch (IOException e) {
                throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        response.put("assetMeasMapDtoList", assetMeasMapDtoList);
        return response;
    }

    private void saveReadmap(List<ModbusAssetMeasReadRegistersMap> assetMeasMapList, Integer id) {
        logger.info("Saving read map");
        List<ModbusAssetMeasReadRegistersMap> mapList = new ArrayList<ModbusAssetMeasReadRegistersMap>();
        for (ModbusAssetMeasReadRegistersMap map : assetMeasMapList) {
            ModbusSlavesReadRegisters slave = slavesReadRegisterServiceImpl.findByRegisterAndModbusId(map, id);
            map.setModbusSlavesReadRegisters(slave);
            map.setId(null);
            mapList.add(map);
        }
        assetMeasReadRegisterServiceImpl.saveAll(mapList);

    }

    /// write map
    @Override
    public JSONObject uploadWriteAssetMap(Integer id, MultipartFile file) {
        JSONObject response = new JSONObject();
        response.put(CommonMessages.STATUS, false);
        boolean isValid = true;
        List<ModbusAssetMeasWriteRegistersMapDto> assetMeasMapDtoList = new ArrayList<ModbusAssetMeasWriteRegistersMapDto>();
        List<ModbusAssetMeasWriteRegistersMap> assetMeasMapList2 = new ArrayList<ModbusAssetMeasWriteRegistersMap>();
        List<HMIDropdownData> hmiData = dataServiceImpl.getAllHmiDropdownData();
        try {
            logger.info("Validating File");

            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {
                    ModbusAssetMeasWriteRegistersMapDto assetMeasMapDto = new ModbusAssetMeasWriteRegistersMapDto();
                    ModbusAssetMeasWriteRegistersMap assetMeasMap2 = new ModbusAssetMeasWriteRegistersMap();
                    List<String> error = new ArrayList<String>();
                    String binaryRead = null;

                    // check if asset id is present
                    boolean isAssetIdPresent = assetsServiceImpl.checkForAssetId(csvRecord.get(0), id);

                    // check if measurement is present
                    boolean isMeasurementPresent = assetsServiceImpl.checkForMeasurement(csvRecord.get(1),
                            csvRecord.get(0), id);

                    if (!isAssetIdPresent) {
                        error.add("assetId");
                        isValid = false;
                    }
                    if (!isMeasurementPresent) {
                        error.add(CommonMessages.MEASUREMENT);
                        isValid = false;
                    }

                    Float scale = null;
                    if (csvRecord.get(6).equals("") || csvRecord.get(6) == null) {
                        error.add(CommonMessages.SCALING);
                        isValid = false;
                    } else {
                        scale = Float.parseFloat(csvRecord.get(6));
                    }

                    Integer funCodeStr = null;
                    Integer sizeStr = null;
                    String roundEnabStr = csvRecord.get(7);
                    String roundMechStr = csvRecord.get(8);

                    Optional<HMIDropdownData> funCode = hmiData.stream().filter(
                                    a -> Arrays.equals(a.getDropdownValue().toCharArray(), csvRecord.get(4).toCharArray())
                                            && a.getType().equals("Function Code"))
                            .findFirst();

                    if (csvRecord.get(4).equals("") || csvRecord.get(4) == null) {
                        error.add(CommonMessages.FUNCTION);
                        isValid = false;
                    } else {
                        if (!funCode.isPresent()) {
                            error.add(CommonMessages.FUNCTION);
                            isValid = false;
                        } else {
                            funCodeStr = funCode.get().getId();
                        }
                    }

                    Optional<HMIDropdownData> size = hmiData.stream()
                            .filter(a -> Arrays.equals(a.getDropdownValue().toCharArray(),
                                    csvRecord.get(5).toCharArray()) && a.getType().equalsIgnoreCase("Size"))
                            .findFirst();
                    if (csvRecord.get(5).equals("") || csvRecord.get(5) == null) {
                        error.add("size");
                        isValid = false;
                    } else {
                        if (!size.isPresent()) {
                            error.add("size");
                            isValid = false;
                            sizeStr = Integer.parseInt(csvRecord.get(5));
                        } else {
                            sizeStr = size.get().getId();
                        }

                    }

                    if (csvRecord.get(7).equals("") || csvRecord.get(7) == null) {
                        error.add(CommonMessages.ROUND_ENAB);
                        isValid = false;
                    } else {
                        roundEnabStr = csvRecord.get(7);
                    }

                    if (csvRecord.get(8).equals("") || csvRecord.get(8) == null) {
                        error.add(CommonMessages.ROUND_MECH);
                        isValid = false;
                    } else {
                        roundMechStr = csvRecord.get(8);
                    }

                    for (CSVRecord csvRecord2 : csvRecords) {
                        if (!csvRecord.equals(csvRecord2)) {

                            if (csvRecord.get(0).equals(csvRecord2.get(0))) {

                                // check for duplicate measurement
                                if (csvRecord.get(1).equals(csvRecord2.get(1))) {
                                    error.add(CommonMessages.MEASUREMENT);
                                    isValid = false;
                                }

                                // check for duplicate register and function
                                if (csvRecord.get(2).equals(csvRecord2.get(2))
                                        && csvRecord.get(4).equals(csvRecord2.get(4))) {
                                    error.add(CommonMessages.REGISTER);
                                    error.add(CommonMessages.FUNCTION);
                                    isValid = false;
                                }

                            }
                        }
                    }

                    List<ModbusSlavesWriteRegisters> regList = slavesWriteRegisterServiceImpl
                            .getAllByRegistersAndSlaveId(Integer.parseInt(csvRecord.get(2)), id);
                    if (regList.size() == 0) {
                        error.add(CommonMessages.REGISTER);
                        error.add(CommonMessages.DISCRIPTION);
                        error.add(CommonMessages.FUNCTION);
                        error.add(CommonMessages.SCALING);
                        error.add("size");
                        error.add(CommonMessages.ROUND_ENAB);
                        error.add(CommonMessages.ROUND_MECH);
                        isValid = false;
                    } else {
                        boolean exists = false;
                        for (ModbusSlavesWriteRegisters reg : regList) {
                            if (reg.getFunctionCode().equals(funCodeStr)) {
                                exists = true;

                                if (!reg.getSize().equals(sizeStr)) {
                                    error.add("size");
                                    isValid = false;
                                }
                                if (reg.getScalingFactor() != Float.parseFloat(csvRecord.get(6))) {
                                    error.add(CommonMessages.SCALING);
                                    isValid = false;
                                }
                                if (!Arrays.equals(reg.getRoundingEnable().toCharArray(), roundEnabStr.toCharArray())) {
                                    error.add(CommonMessages.ROUND_ENAB);
                                    isValid = false;
                                }

                                if (!Arrays.equals(reg.getRoundingMechanism().toCharArray(),
                                        roundMechStr.toCharArray())) {
                                    error.add(CommonMessages.ROUND_MECH);
                                    isValid = false;
                                }

                            }

                        }
                        if (!exists) {
                            error.add(CommonMessages.FUNCTION);
                            error.add(CommonMessages.BINARY_READ);
                            isValid = false;
                        }

                    }

                    // setting values) to dto
                    assetMeasMapDto.setId(null);
                    assetMeasMapDto.setAssetId(csvRecord.get(0));
                    assetMeasMapDto.setMeasurementName(csvRecord.get(1));
                    assetMeasMapDto.setRegister(Integer.parseInt(csvRecord.get(2)));
                    assetMeasMapDto.setDescription(csvRecord.get(3));
                    assetMeasMapDto.setFunctionCode(csvRecord.get(4));
                    assetMeasMapDto.setSize(csvRecord.get(5));
                    assetMeasMapDto.setScalingFactor(Float.parseFloat(csvRecord.get(6)));
                    assetMeasMapDto.setRoundingEnable(csvRecord.get(7));
                    assetMeasMapDto.setRoundingMechanism(csvRecord.get(8));
                    assetMeasMapDto.setErrors(error);
                    assetMeasMapDtoList.add(assetMeasMapDto);

                    if (isValid) {
                        assetMeasMap2.setId(null);
                        assetMeasMap2.setAssetId(csvRecord.get(0));
                        assetMeasMap2.setMeasurementName(csvRecord.get(1));
                        assetMeasMap2.setRegister(Integer.parseInt(csvRecord.get(2)));
                        assetMeasMap2.setDescription(csvRecord.get(3));
                        assetMeasMap2.setFunctionCode(funCodeStr);
                        assetMeasMap2.setSize(sizeStr);
                        assetMeasMap2.setScalingFactor(scale);
                        assetMeasMap2.setRoundingEnable(roundEnabStr);
                        assetMeasMap2.setRoundingMechanism(roundMechStr);
                        assetMeasMapList2.add(assetMeasMap2);
                    }

                }

                if (isValid) {
                    // saving data
                    saveWritemap(assetMeasMapList2, id);
                    response.put(CommonMessages.STATUS, isValid);
                }

            } catch (IOException e) {
                throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        response.put("assetMeasMapDtoList", assetMeasMapDtoList);

        return response;
    }

    private void saveWritemap(List<ModbusAssetMeasWriteRegistersMap> assetMeasMapDtoList, Integer id) {

        List<ModbusAssetMeasWriteRegistersMap> mapList = new ArrayList<ModbusAssetMeasWriteRegistersMap>();

        for (ModbusAssetMeasWriteRegistersMap map : assetMeasMapDtoList) {
            ModbusSlavesWriteRegisters slave = slavesWriteRegisterServiceImpl.findByRegisterAndModbusId(map, id);
            map.setModbusSlavesWriteRegisters(slave);
            map.setModbusId(id);
            mapList.add(map);
        }

        assetMeasWriteRegisterServiceImpl.deleteBymodbusId(id);
        assetMeasWriteRegisterServiceImpl.saveAll(mapList);

    }

    @Override
    public JSONObject saveJson() {

        List<ModbusSlaves> modbusSlaves = modbusSlavesService.getAllModbusSlaves();
        List<HMIDropdownData> hmiData = dataServiceImpl.getAllHmiDropdownData();

        JSONObject jsonFile = new JSONObject();
        JSONArray slaves = new JSONArray();
        jsonFile.put("Slaves", slaves);
        for (ModbusSlaves modbus : modbusSlaves) {

            JSONObject slave = new JSONObject();
            JSONObject connection = new JSONObject();
            slave.put("Connection", connection);

            connection.put("id", modbus.getModbusSlaveId());
            connection.put("IP", modbus.getIp());
            connection.put("Port", modbus.getPort());
            connection.put("Slave", modbus.getSlaveId());
            connection.put("polling_slave_frequency", modbus.getPollingSlaveFrequency());

            JSONArray assets = new JSONArray();
            slave.put(CommonMessages.ASSET, assets);

            List<AssetAndTypeDto> assetList = assetsServiceImpl.getAllByModbusSlaveServer(modbus.getModbusSlaveId());

            for (AssetAndTypeDto asset : assetList) {

                JSONObject asset_j = new JSONObject();

                // read Register
                JSONObject readRegisters = new JSONObject();

                JSONObject READ_REGISTERS = new JSONObject();
                JSONObject READ_REGISTERS_DESC = new JSONObject();
                JSONObject READ_REGISTERS_FUNCTION_CODE = new JSONObject();
                JSONObject READ_REGISTERS_SIZE = new JSONObject();
                JSONObject READ_REGISTERS_SCALING_FAC = new JSONObject();
                JSONObject READ_REGISTERS_BINARY_READ = new JSONObject();
                JSONObject READ_REGISTERS_MASK = new JSONObject();
                JSONObject READ_REGISTERS_COMM_FLAG = new JSONObject();
                JSONObject READ_REGISTERS_COMM_VALUE = new JSONObject();
                JSONObject READ_REGISTERS_UNIT = new JSONObject();

                readRegisters.put("READ_REGISTERS", READ_REGISTERS);
                readRegisters.put("READ_REGISTERS_DESC", READ_REGISTERS_DESC);
                readRegisters.put("READ_REGISTERS_FUNCTION_CODE", READ_REGISTERS_FUNCTION_CODE);
                readRegisters.put("READ_REGISTERS_SIZE", READ_REGISTERS_SIZE);
                readRegisters.put("READ_REGISTERS_SCALING_FAC", READ_REGISTERS_SCALING_FAC);
                readRegisters.put("READ_REGISTERS_BINARY_READ", READ_REGISTERS_BINARY_READ);
                readRegisters.put("READ_REGISTERS_MASK", READ_REGISTERS_MASK);
                readRegisters.put("READ_REGISTERS_COMM_FLAG", READ_REGISTERS_COMM_FLAG);
                readRegisters.put("READ_REGISTERS_COMM_VALUE", READ_REGISTERS_COMM_VALUE);
                readRegisters.put("READ_REGISTERS_UNIT", READ_REGISTERS_UNIT);

                List<ModbusAssetMeasReadRegistersMap> readMapList = assetMeasReadRegisterServiceImpl
                        .findByModbusIdAndAssetId(modbus.getModbusSlaveId(), asset.getAssetId());
                for (ModbusAssetMeasReadRegistersMap map : readMapList) {
                    if (map.getAssetId().equals(asset.getAssetId())) {

                        asset_j.put("ReadRegisters", readRegisters);

                        JSONObject assetType = new JSONObject();
                        assetType.put("AssetTypeId", asset.getAssetTypeId());
                        assetType.put("AssetTypeName", asset.getAssetType());
                        asset_j.put("AssetType", assetType);
                        asset_j.put("AssetId", asset.getAssetId());
                        asset_j.put("AssetName", asset.getAssetName());
                        assets.put(asset_j);

                        Optional<HMIDropdownData> function_hmi = hmiData.stream()
                                .filter(a -> a.getId().equals(map.getFunctionCode())).findFirst();
                        if (function_hmi.isPresent()) {
                            String function = function_hmi.get().getJsonfileValue();
                            READ_REGISTERS_FUNCTION_CODE.put(map.getMeasurementName(), Integer.parseInt(function));
                        }

                        Optional<HMIDropdownData> size_hmi = hmiData.stream()
                                .filter(a -> a.getId().equals(map.getSize())).findFirst();
                        if (size_hmi.isPresent()) {
                            String size = size_hmi.get().getJsonfileValue();
                            READ_REGISTERS_SIZE.put(map.getMeasurementName(), Integer.parseInt(size));
                        }

                        Optional<HMIDropdownData> binaryRead_hmi = hmiData.stream()
                                .filter(a -> a.getId().toString().equals(map.getBinaryRead())).findFirst();
                        if (binaryRead_hmi.isPresent()) {
                            String binaryRead = binaryRead_hmi.get().getJsonfileValue();
                            READ_REGISTERS_BINARY_READ.put(map.getMeasurementName(), binaryRead);
                        }
                        Optional<HMIDropdownData> bit_hmi = hmiData.stream()
                                .filter(a -> a.getId().toString().equals(map.getBitRegister())).findFirst();
                        if (bit_hmi.isPresent()) {
                            String bit = bit_hmi.get().getJsonfileValue();
                            READ_REGISTERS_MASK.put(map.getMeasurementName(), Integer.parseInt(bit));
                        }

                        READ_REGISTERS.put(map.getMeasurementName(), map.getRegister());
                        READ_REGISTERS_DESC.put(map.getMeasurementName(), map.getDescription());
                        READ_REGISTERS_SCALING_FAC.put(map.getMeasurementName(), map.getScalingFactor());
                        READ_REGISTERS_COMM_FLAG.put(map.getMeasurementName(), map.getCommFlag());
                        READ_REGISTERS_COMM_VALUE.put(map.getMeasurementName(), map.getCommValue());
                        String uom = measurementDao.findByAssetTypeSeqAndMeasurementName(asset.getAssetTypeId(),map.getMeasurementName()).get(0).getUom();
                        READ_REGISTERS_UNIT.put(map.getMeasurementName(), uom == null ? "" : uom);

                    }

                    try {



                        JSONArray deviceL = slave.getJSONArray(CommonMessages.ASSET);

                        // remove duplicate assets
                        for (int i = 0; i < deviceL.length(); i++) {

                            for (int j = 0; j < deviceL.length(); j++) {

                                if (i != j) {

                                    JSONObject deviceOne = (JSONObject) deviceL.get(i);
                                    JSONObject deviceTwo = (JSONObject) deviceL.get(j);

                                    if (deviceOne.equals(deviceTwo)) {
                                        slave.getJSONArray(CommonMessages.ASSET).remove(i);
                                    }

                                }

                            }

                        }

                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                }

                // write register
                JSONObject writeRegisters = new JSONObject();

                JSONObject WRITE_REGISTERS = new JSONObject();
                JSONObject WRITE_REGISTERS_DESC = new JSONObject();
                JSONObject WRITE_REGISTERS_FUNCTION_CODE = new JSONObject();
                JSONObject WRITE_REGISTERS_SIZE = new JSONObject();
                JSONObject WRITE_REGISTERS_SCALING_FAC = new JSONObject();
                JSONObject WRITE_REGISTERS_ROUNDING_ENABLE = new JSONObject();
                JSONObject WRITE_REGISTERS_ROUNDING_MECHANISM = new JSONObject();
                JSONObject WRITE_REGISTERS_UNIT = new JSONObject();

                writeRegisters.put("WRITE_REGISTERS", WRITE_REGISTERS);
                writeRegisters.put("WRITE_REGISTERS_DESC", WRITE_REGISTERS_DESC);
                writeRegisters.put("WRITE_REGISTERS_FUNCTION_CODE", WRITE_REGISTERS_FUNCTION_CODE);
                writeRegisters.put("WRITE_REGISTERS_SIZE", WRITE_REGISTERS_SIZE);
                writeRegisters.put("WRITE_REGISTERS_SCALING_FAC", WRITE_REGISTERS_SCALING_FAC);
                writeRegisters.put("WRITE_REGISTERS_ROUNDING_ENABLE", WRITE_REGISTERS_ROUNDING_ENABLE);
                writeRegisters.put("WRITE_REGISTERS_ROUNDING_MECHANISM", WRITE_REGISTERS_ROUNDING_MECHANISM);
                writeRegisters.put("WRITE_REGISTERS_UNIT", WRITE_REGISTERS_UNIT);

                List<ModbusAssetMeasWriteRegistersMap> writeMapList = assetMeasWriteRegisterServiceImpl
                        .findByModbusIdAndAssetId(modbus.getModbusSlaveId(), asset.getAssetId());

                for (ModbusAssetMeasWriteRegistersMap map : writeMapList) {

                    if (map.getAssetId().equals(asset.getAssetId())) {

                        asset_j.put("WriteRegisters", writeRegisters);

                        JSONObject assetType = new JSONObject();
                        assetType.put("AssetTypeId", asset.getAssetTypeId());
                        assetType.put("AssetTypeName", asset.getAssetType());
                        asset_j.put("AssetType", assetType);
                        asset_j.put("AssetId", asset.getAssetId());
                        asset_j.put("AssetName", asset.getAssetName());
                        assets.put(asset_j);

                        Optional<HMIDropdownData> function_hmi = hmiData.stream()
                                .filter(a -> a.getId().equals(map.getFunctionCode())).findFirst();
                        if (function_hmi.isPresent()) {
                            String function = function_hmi.get().getJsonfileValue();
                            WRITE_REGISTERS_FUNCTION_CODE.put(map.getMeasurementName(), Integer.parseInt(function));
                        }

                        Optional<HMIDropdownData> size_hmi = hmiData.stream()
                                .filter(a -> a.getId().equals(map.getSize())).findFirst();
                        if (size_hmi.isPresent()) {
                            String size = size_hmi.get().getJsonfileValue();
                            WRITE_REGISTERS_SIZE.put(map.getMeasurementName(), Integer.parseInt(size));
                        }

                        Optional<HMIDropdownData> roundEnab_hmi = hmiData.stream()
                                .filter(a -> a.getDropdownValue().toString().equals(map.getRoundingEnable()))
                                .findFirst();
                        if (roundEnab_hmi.isPresent()) {
                            String roundEnab = roundEnab_hmi.get().getDropdownValue();
                            WRITE_REGISTERS_ROUNDING_ENABLE.put(map.getMeasurementName(), roundEnab);
                        }

                        Optional<HMIDropdownData> roundMech_hmi = hmiData.stream()
                                .filter(a -> a.getDropdownValue().toString().equals(map.getRoundingMechanism()))
                                .findFirst();
                        if (roundMech_hmi.isPresent()) {
                            String roundMech = roundMech_hmi.get().getJsonfileValue();
                            WRITE_REGISTERS_ROUNDING_MECHANISM.put(map.getMeasurementName(), roundMech);
                        }

                        WRITE_REGISTERS.put(map.getMeasurementName(), map.getRegister());
                        WRITE_REGISTERS_DESC.put(map.getMeasurementName(), map.getDescription());
                        WRITE_REGISTERS_SCALING_FAC.put(map.getMeasurementName(), map.getScalingFactor());
                        WRITE_REGISTERS_UNIT.put(map.getMeasurementName(),
                                measurementDao.findByAssetTypeSeqAndMeasurementName(asset.getAssetTypeId(),map.getMeasurementName()).get(0).getUom());

                    }

                    try {

                        JSONArray deviceL = slave.getJSONArray(CommonMessages.ASSET);

                        // remove duplicate assets
                        for (int i = 0; i < deviceL.length(); i++) {

                            for (int j = 0; j < deviceL.length(); j++) {

                                if (i != j) {

                                    JSONObject deviceOne = (JSONObject) deviceL.get(i);
                                    JSONObject deviceTwo = (JSONObject) deviceL.get(j);

                                    if (deviceOne.equals(deviceTwo)) {
                                        slave.getJSONArray(CommonMessages.ASSET).remove(i);
                                    }

                                }

                            }

                        }

                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                }

            }

            slaves.put(slave);

        }

        File file = new File(getenv("MODBUS_SERVER_CONFIG_PATH") + "/modbusserverassetconfig.json");

        try {
            setPermission(file);
        } catch (IOException e1) {
            logger.error(e1.toString());
        }

        ObjectMapper mapper = new ObjectMapper();
        try {

            // MODBUS_SERVER_CONFIG_PATH
            logger.info(getenv("MODBUS_SERVER_CONFIG_PATH") + "/modbusserverassetconfig.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonFile.toMap());
        } catch (IOException e1) {
            logger.error(e1.toString());
        }

        return jsonFile;
    }

    public void setPermission(File file) throws IOException {

        if (!getOperatingSystem().contains("Windows")) {
            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            perms.add(PosixFilePermission.OWNER_EXECUTE);

            perms.add(PosixFilePermission.OTHERS_READ);
            perms.add(PosixFilePermission.OTHERS_WRITE);
            perms.add(PosixFilePermission.OTHERS_EXECUTE);

            perms.add(PosixFilePermission.GROUP_READ);
            perms.add(PosixFilePermission.GROUP_WRITE);
            perms.add(PosixFilePermission.GROUP_EXECUTE);

            Files.setPosixFilePermissions(file.toPath(), perms);
        }
    }

    public String getOperatingSystem() {
        String os = System.getProperty("os.name");
        return os;
    }

    private static String getenv(String variable) {
        return ((Function<String, String>) System::getenv).apply(variable);
    }

}
