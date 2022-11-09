package com.lnt.ems.evse.constants;

public class RESTUrls {

    public RESTUrls() {
    }

    public static final String BASE_URL = "/spevse/modbusService";

    public static final String HASH_TAG = "/#";

    //Modbus slave
    public static final String MODBUS_SLAVE = BASE_URL + "/modbusSlave";
    public static final String SAVE_MODBUS_SLAVE = "/save";
    public static final String UPDATE_MODBUS_SLAVE = "/update";
    public static final String GET_ALL_MODBUS_SLAVE = "/getAll";
    public static final String GET_BY_ID_MODBUS_SLAVE = "/id";
    public static final String ENABLE_MODBUS_SLAVE = "/enable";
    public static final String DELETE_MODBUS_SLAVE = "/delete";
    public static final String CLONE_MODBUS_SLAVE = "/clone";

    //Modbus slave read register
    public static final String MODBUS_SLAVE_READ = BASE_URL + "/modbusSlaveRead";
    public static final String SAVE_MODBUS_SLAVE_READ = "/saveModbusSlaveRead";
    public static final String SAVE_ALL_MODBUS_SLAVE_READ = "/saveAllModbusSlaveRead";
    public static final String GET_ALL_MODBUS_SLAVE_READ = "/getAllModbusSlaveRead";
    public static final String GET_BY_ID_MODBUS_SLAVE_READ = "/idModbusSlaveRead";
    public static final String GET_ALL_MODBUS_READ_BY_SLAVE_ID = "/getAllReadByModbusSlaveId";
    public static final String DELETE_BY_ID_MODBUS_SLAVE_READ = "/deleteModbusSlaveRead";
    public static final String DELETE_MODBUS_SLAVE_READ_BY_MODEBUS_ID = "/deleteModbusSlaveReadByModebusId";
    public static final String VALIDATE_READ_REGISTER_MAPPING = "/validateReadRegisterMapping";

    //Modbus slave write register
    public static final String MODBUS_SLAVE_WRITE = BASE_URL + "/modbusSlaveWrite";
    public static final String SAVE_MODBUS_SLAVE_WRITE = "/saveModbusSlaveWrite";
    public static final String SAVE_ALL_MODBUS_SLAVE_WRITE = "/saveAllModbusSlaveWrite";
    public static final String GET_ALL_MODBUS_SLAVE_WRITE = "/getAllModbusSlaveWrite";
    public static final String GET_BY_ID_MODBUS_SLAVE_WRITE = "/idModbusSlaveWrite";
    public static final String GET_ALL_MODBUS_WRITE_BY_SLAVE_ID = "/getAllWriteByModbusSlaveId";
    public static final String DELETE_BY_ID_MODBUS_SLAVE_WRITE = "/deleteModbusSlaveWrite";
    public static final String DELETE_MODBUS_SLAVE_WRITE_BY_MODEBUS_ID = "/deleteModbusSlaveWriteByModebusId";
    public static final String VALIDATE_WRITE_REGISTER_MAPPING = "/validateWriteRegisterMapping";


    //Modbus asset meas read register map
    public static final String MODBUS_ASSET_MEAS_READ_MAP = BASE_URL + "/modbusAssetReadMap";
    public static final String SAVE_MODBUS_ASSET_MEAS_READ_MAP = "/saveModbusAssetReadMap";
    public static final String GET_ALL_MODBUS_ASSET_MEAS_READ_MAP = "/getAllModbusAssetReadMap";
    public static final String GET_BY_ID_MODBUS_ASSET_MEAS_READ_MAP = "/idModbusAssetReadMap";
    public static final String DELETE_BY_ID_MODBUS_ASSET_MEAS_READ_MAP = "/deleteModbusAssetReadMap";
    public static final String GET_ALL_READ_MAP_BY_MODBUS_ID = "/getAllReadMapByModbusId";

    //Modbus asset meas write register map
    public static final String MODBUS_ASSET_MEAS_WRITE_MAP = BASE_URL + "/modbusAssetWriteMap";
    public static final String SAVE_MODBUS_ASSET_MEAS_WRITE_MAP = "/saveModbusAssetWriteMap";
    public static final String GET_ALL_MODBUS_ASSET_MEAS_WRITE_MAP = "/getAllModbusAssetWriteMap";
    public static final String GET_BY_ID_MODBUS_ASSET_MEAS_WRITE_MAP = "/idModbusAssetWriteMap";
    public static final String DELETE_BY_ID_MODBUS_ASSET_MEAS_WRITE_MAP = "/deleteModbusAssetWriteMap";
    public static final String GET_ALL_WRITE_MAP_BY_MODBUS_ID = "/getAllWriteMapByModbusId";

    //Modbus slave Associated Assets
    public static final String MODBUS_SLAVE_ASSETS = BASE_URL + "/modbusSlaveAssets";
    public static final String SAVE_MODBUS_SLAVE_ASSETS = "/saveModbusSlaveAssets";
    public static final String SAVE_ALL_MODBUS_SLAVE_ASSETS = "/saveAllModbusSlaveAssets";
    public static final String GET_ALL_MODBUS_SLAVE_ASSETS = "/getAllModbusSlaveAssets";
    public static final String GET_BY_ID_MODBUS_SLAVE_ASSETS = "/idModbusSlaveAssets";
    public static final String DELETE_BY_ID_MODBUS_SLAVE_ASSETS = "/deleteModbusSlaveAssets";
    public static final String DELETE_BY_ID_MODBUS_SLAVE_ASSETS_IN = "/deleteModbusSlaveAssetsIn";
    public static final String DELETE_ALL_MODBUS_SLAVE_ASSETS_BY_SERVER_ID = "/deleteAllModbusSlaveAssetsByServerId";
    public static final String GET_MODBUS_SLAVE_ASSETS_BY_SERVER = "/getModbusSlaveAssetsByServer";

    public static final String UNIQUE_MODBUS_MEASUREMENT = "/Measurements/unique";

    public static final String FILE_DATA = BASE_URL + "/fileData";
    public static final String UPLOAD_READ_ASSET_MAP = "/uploadReadAssetMap";
    public static final String UPLOAD_WRITE_ASSET_MAP = "/uploadWriteAssetMap";
    public static final String JSON_FILE = "/jsonFile";

    // dropdown data
    public static final String HMI_DROPDOWN= BASE_URL +"/hmiDropdown";
    public static final String GET_HMI_DROPDOWN_DATA = "/getHmiDropdownData";


}