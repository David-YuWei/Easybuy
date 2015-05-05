package com.easybuy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypSHA {
	
	public byte[] eccrypt(String info) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance("SHA");
		byte[] srcBytes = info.getBytes();
		md5.update(srcBytes);
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String msg = "12346grwert";
		EncrypSHA sha = new EncrypSHA();
		byte[] resultBytes = sha.eccrypt(msg);
		System.out.println("secrutkey:" + msg);
		System.out.println("plaintext:" + resultBytes);
		
	}

}
