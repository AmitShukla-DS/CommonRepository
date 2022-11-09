/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.serviceImpl;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AES {

	private static final Logger logger = LoggerFactory.getLogger(AES.class);

	private final String ALGO = "AES"; // Default uses ECB PKCS5Padding

	@Value("${secretKey}")
	private String secretKey;

	@Value("${secretKey}")
	public void setSecretKeyStatic(String name) {
		secretKey = name;
	}

	public String encrypt(String Data, String secret) throws Exception {
		Key key = generateKey(secret);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	public String decrypt(String strToDecrypt) {
		String encodedBase64Key = encodeKey(secretKey); // shared with UI
		try {
			Key key = generateKey(encodedBase64Key);
			Cipher cipher = Cipher.getInstance(ALGO);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}

	private Key generateKey(String secret) throws Exception {
		byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
		Key key = new SecretKeySpec(decoded, ALGO);
		return key;
	}

	public String decodeKey(String str) {
		byte[] decoded = Base64.getDecoder().decode(str.getBytes());
		return new String(decoded);
	}

	public String encodeKey(String str) {
		byte[] encoded = Base64.getEncoder().encode(str.getBytes());
		return new String(encoded);
	}

}