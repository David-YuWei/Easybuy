package com.easybuy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypMD5 {
	
	public byte[] eccrypt(String info) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcBytes = info.getBytes();
		md5.update(srcBytes);
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}
	
	
	public static void main(String args[]) throws NoSuchAlgorithmException{
		String msg = "123";
		EncrypMD5 md5 = new EncrypMD5();
		byte[] resultBytes = md5.eccrypt(msg);
		System.out.println("secrutKey:" + resultBytes);
		System.out.println("plaintext:" + msg);
	}

}
