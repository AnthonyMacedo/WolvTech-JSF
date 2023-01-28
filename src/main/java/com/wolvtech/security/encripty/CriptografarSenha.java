package com.wolvtech.security.encripty;

import java.math.BigInteger;
import java.security.MessageDigest;

public class CriptografarSenha {

	public static String md5(String senha) { 
		String retorno = "";
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1,md.digest(senha.getBytes()));
		retorno = hash.toString(16);
		}
		catch(Exception e) {
		}return retorno;
	}
	
}
