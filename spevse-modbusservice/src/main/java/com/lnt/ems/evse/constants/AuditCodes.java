/**
* EVSE is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.ems.evse.constants;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AuditCodes {

	// File Controller
	UPLOAD_READ_ASSET_MAP,
	UPLOAD_WRITE_ASSET_MAP,
	JSON_FILE,

	// Hmi DropDown Controller
	GET_HMI_DROPDOWN_DATA,

	// Modbus Asset MeasRead RegisterMap Controller
	GET_BY_ID_MODBUS_ASSET_MEAS_READ_MAP,
	GET_ALL_MODBUS_ASSET_MEAS_READ_MAP,
	SAVE_MODBUS_ASSET_MEAS_READ_MAP,
	DELETE_BY_ID_MODBUS_ASSET_MEAS_READ_MAP,
	UNIQUE_MODBUS_MEASUREMENT,
	GET_ALL_READ_MAP_BY_MODBUS_ID,

	// Modbus Asset MeasWrite RegisterMap Controller
	GET_BY_ID_MODBUS_ASSET_MEAS_WRITE_MAP,
	GET_ALL_MODBUS_ASSET_MEAS_WRITE_MAP,
	SAVE_MODBUS_ASSET_MEAS_WRITE_MAP,
	DELETE_BY_ID_MODBUS_ASSET_MEAS_WRITE_MAP,
	GET_ALL_WRITE_MAP_BY_MODBUS_ID,

	// Modbus Slaves Associated AssetsController
	GET_BY_ID_MODBUS_SLAVE_ASSETS,
	GET_ALL_MODBUS_SLAVE_ASSETS,
	SAVE_MODBUS_SLAVE_ASSETS,
	SAVE_ALL_MODBUS_SLAVE_ASSETS,
	DELETE_BY_ID_MODBUS_SLAVE_ASSETS,
	GET_MODBUS_SLAVE_ASSETS_BY_SERVER,
	DELETE_ALL_MODBUS_SLAVE_ASSETS_BY_SERVER_ID,
	DELETE_BY_ID_MODBUS_SLAVE_ASSETS_IN,


	// Modbus Slaves Controller
	GET_BY_ID_MODBUS_SLAVE,
	GET_ALL_MODBUS_SLAVE,
	SAVE_MODBUS_SLAVE,
	CLONE_MODBUS_SLAVE,
	UPDATE_MODBUS_SLAVE,
	ENABLE_MODBUS_SLAVE,
	DELETE_MODBUS_SLAVE,

	// Modbus Slaves Read Register Controller
	GET_BY_ID_MODBUS_SLAVE_READ,
	GET_ALL_MODBUS_SLAVE_READ,
	SAVE_MODBUS_SLAVE_READ,
	SAVE_ALL_MODBUS_SLAVE_READ,
	DELETE_BY_ID_MODBUS_SLAVE_READ,
	GET_ALL_MODBUS_READ_BY_SLAVE_ID,
	DELETE_MODBUS_SLAVE_READ_BY_MODEBUS_ID,
	VALIDATE_READ_REGISTER_MAPPING,


	//Modbus Slaves Write Register Controller
	GET_BY_ID_MODBUS_SLAVE_WRITE,
	GET_ALL_MODBUS_SLAVE_WRITE,
	SAVE_MODBUS_SLAVE_WRITE,
	SAVE_ALL_MODBUS_SLAVE_WRITE,
	DELETE_BY_ID_MODBUS_SLAVE_WRITE,
	GET_ALL_MODBUS_WRITE_BY_SLAVE_ID,
	DELETE_MODBUS_SLAVE_WRITE_BY_MODEBUS_ID,
	VALIDATE_WRITE_REGISTER_MAPPING,
	

	;





}