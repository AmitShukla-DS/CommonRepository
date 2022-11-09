/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.services;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public boolean hasCSVFormat(MultipartFile file);

    public JSONObject uploadReadAssetMap(Integer id, MultipartFile file);

    public JSONObject uploadWriteAssetMap(Integer id, MultipartFile file);

    JSONObject saveJson();

//	public JSONObject downloadModbusServerAssetConfigFile();

}
