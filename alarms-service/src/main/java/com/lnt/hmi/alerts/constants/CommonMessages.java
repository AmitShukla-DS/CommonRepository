/**
* SRS is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.hmi.alerts.constants;

public final class CommonMessages {

	private CommonMessages() {
		throw new IllegalStateException("CommonMessages class");
	}

	public static final String MSG_OBJECT = "Object";
	public static final String MSG_LIST_SIZE = "List Size !";

	// for devicemanagement
	public static final String SUCCESS = "Success";
	public static final String FAILED = "Failed";
	public static final String NOT_FOUND = "No Data Found";
	public static final String EXISTING_ENTITY = "Entity Already Exists";

	public static final String MSG_SUCCESS = "Success";
	public static final String MSG_ERROR = "Error";
	public static final String MSG_EXCEPTION = "Exception occurred Due To !";
	public static final String MSG_FAILED = "Failed";
	// login signup messages
	public static final String USER_EMAIL_EXISTS = "User with provided Email already exists";
	public static final String USER_MOBILE_EXISTS = "User with provided Mobile Number already exists";
	public static final String USER_USERNAME_EXISTS = "Username already taken";
	public static final String SIGN_UP_SUCCES = "User registered successfully";
	public static final String SIGN_UP_ERROR = "Error occurred while registering user";
	public static final String SIGN_UP_ERROR_MAIL = "Error occurred while registering user & sending Mail";

	// LOGIN MESSAGES
	public static final String USER_NOT_ENABLED = "Not Enabled";
	public static final String WRONG_CREDENTIALS = "Wrong Credentials";
	public static final String UNVERIFIED = "Not Verified";
	public static final String PASSWORD_NOT_SET = "User password not set";
	public static final String USER_NOT_FOUND = "User Not Found";
	public static final String DEVICE_NOT_FOUND = "Device Not Found";

	// DATA RETRIEVAL MESSAGES
	public static final String DATA_SUCCESS = "Data retrieved successfully!!!!";
	public static final String DATA_FAILED = "Data Not Found";

	// JWT MESSAGES
	public static final String JWT_TOKEN_EXPIRED = "JWT Token has expired";
	public static final String JWT_WRONG_SIGNATURE = "Wrong Signature";

	// LOGGER MESSAGES
	public static final String GETTING_DATA_SUCCESSFULLY = "Getting Data Successfully.......";
	public static final String SAVED_SUCCESSFULLY = "Saved Successfully.......";
	public static final String GET_SUCCESSFULLY = "Get Successfully.......";
	public static final String ERROR_OCCURE = "Error Occurred {}";
	public static final String GETTING_DATA_FROMCODES = "Getting Data From Codes.......";
	public static final String GETTING_DATA_IEC = "Getting Data from IEC...";
	public static final String SAVE_FAILED = "Save data in  Failed........!";

	// TIME INTERVAL MESSAGES
	public static final String ONE_HOUR = "1 hour";
	public static final String ONE_DAY = "1 day";
	public static final String ONE_MONTH = "1 month";

	// FILE RELATED MESSAGE
	public static final String INVALID_FILE = "Invalid File";
	public static final String EXCEPTION_READING_FILE = "Exception reading file";

	public static final String FALSE = "false";

	// FILE SERVICE IMPLEMENTATION
	public static final String STATUS = "status";
	public static final String MEASUREMENT = "measurement";
	public static final String REGISTER = "register";
	public static final String DISCRIPTION = "discription";
	public static final String FUNCTION = "function";
	public static final String SCALING = "scaling";
	public static final String BINARY_READ = "binaryRead";
	public static final String COMM_VALUE = "commvalue";
	public static final String COMM_FLAG = "commflag";
	public static final String ROUND_ENAB = "roundEnab";
	public static final String ROUND_MECH = "roundMech";
	public static final String DEVICE = "Devices";
	public static final String DEVICENOFOUND = "Devices Not Found......!";

	public static final String DELETE_SUCCESSFULLY = "Deleted Data Successfully.......";

	public static final String SUCCESSFULLY_DELETED = "successfully deleted";

}
