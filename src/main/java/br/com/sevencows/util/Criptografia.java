package br.com.sevencows.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Criptografia {

	public static String criptografar(String senha) {
		
		String s = null;
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(senha.getBytes("ISO-8859-1"));
			
			BigInteger hash = new BigInteger(1, md.digest());
			
			s = hash.toString(16);
			
		} catch (NoSuchAlgorithmException e) {
		
			e.printStackTrace();
			System.err.println(e.getMessage());
		
		} catch (UnsupportedEncodingException e) {
		
			e.printStackTrace();
			System.err.println(e.getMessage());
		
		} catch (Exception e) {
		
			e.printStackTrace();
			System.err.println(e.getMessage());
		
		}
		
		return s;
		
	}
	
}
