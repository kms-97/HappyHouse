package com.ssafy.happyhouse.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	
	// 입력받은 String 변수를 암호화하여 반환.
	public static String encrypt(String str) throws NoSuchAlgorithmException {
		return encode(hashing(str));
	}
	
	// SHA-256 알고리즘을 사용하여 암호화.
	private static byte[] hashing(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(str.getBytes());
		
		return md.digest();
	}
	
	// 바이트 결과를 String으로 반환.
	private static String encode(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b: bytes) {
			sb.append(String.format("%02x", b));
		}
		
		return sb.toString();
	}
}
