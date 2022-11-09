/**
* SRS is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.hmi.alerts.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

	public static Map<String, List<String>> split(String str) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> str1 = Arrays.asList(str.split(","));

		for (String s : str1) {
			List<String> str2 = new ArrayList<>();
			str2 = Arrays.asList(s.split(" |_"));
			map.put(str2.get(0), str2);

		}
		return map;
	}
}
