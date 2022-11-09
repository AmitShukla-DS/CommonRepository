package com.lnt.hmi.alerts.constants;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AuditCodes {
	// Login Controller
	GET_CLIENT_CONFIG,

	// UserController
	GET_ALL_USERS, GET_USER_PERMISSION_BY_ID, GET_ALL_ROLES, SAVE_ROLE, ENABLE_USER, ACTIVATE_USER, CREATE_USER,
	UPDATE_USER, SAVE_LANGUAGE, SET_USER_PASSWORD, GET_ALL_ORGANIZATIONS, GET_ALL_PASSWORD_RULES,
	GET_ALL_ORGANIZATION_BY_SITE_ID, ASSIGN_ROLE, TERMINATE_ROLE, GET_ROLES_FOR_USER, ROLE_PERMISSION_BY_ROLE,
	SET_ROLE_PERMISSIONS, SET_ROLE_AND_PERMISSIONS, UPDATE_ROLE_PERMISSIONS, UPDATE_ROLE,;

}