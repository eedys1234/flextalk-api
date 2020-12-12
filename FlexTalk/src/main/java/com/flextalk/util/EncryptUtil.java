package com.flextalk.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class EncryptUtil {
	
	private EncryptUtil() {
		throw new AssertionError();
	}
	
	public static String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result +=
                Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }
	
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	    	data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	} 
	
	public static KeyPair genRSAKeyPair() {
		
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator gen;
		KeyPair keyPair = null;
		
		try {
			gen = KeyPairGenerator.getInstance("RSA");
			gen.initialize(2048, secureRandom);
			
			keyPair = gen.genKeyPair();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyPair;
	}
	
	public static String encryptStringRSA(String plainText, String publicKeyString) throws Exception{
//	Source: http://www.cs.berkeley.edu/~jonah/bc/org/bouncycastle/crypto/engines/RSAEngine.html	
		 String encryptedData = null;
	        try {
	 
	            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	 
	            AsymmetricKeyParameter publicKey = 
	                (AsymmetricKeyParameter) PublicKeyFactory.createKey(Base64.getDecoder().decode((publicKeyString)));
	            AsymmetricBlockCipher e = new RSAEngine();
	            e = new org.bouncycastle.crypto.encodings.PKCS1Encoding(e);
	            e.init(true, publicKey);
	 
	            byte[] messageBytes = plainText.getBytes();
	            byte[] hexEncodedCipher = e.processBlock(messageBytes, 0, messageBytes.length);
	 
	            encryptedData = getHexString(hexEncodedCipher);
	    
	        }
	        catch (Exception e) {
	            System.out.println(e);
	        }
	        
	        return encryptedData;
	}
	
	public static String decryptStringRSA(String encrypted, String privateKeyString){
//	Source: http://www.mysamplecode.com/2011/08/java-rsa-decrypt-string-using-bouncy.html
		
		String dncryptString = null;
		
		try {
			Security.addProvider(new BouncyCastleProvider());

	        AsymmetricKeyParameter privateKey;
			privateKey = (AsymmetricKeyParameter) PrivateKeyFactory.createKey(Base64.getDecoder().decode(privateKeyString));
	        AsymmetricBlockCipher e = new RSAEngine();
	        e = new org.bouncycastle.crypto.encodings.PKCS1Encoding(e);
	        e.init(true, privateKey);

	        byte[] messageBytes = hexStringToByteArray(encrypted);
	        byte[] hexEncodedCipher = e.processBlock(messageBytes, 0, messageBytes.length);

	        dncryptString = new String(hexEncodedCipher, "UTF-8");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dncryptString;
	}
	
	/**
	 * AES 암호화 함수
	 * @param strPlainText
	 * @param strAESKey
	 * @return
	 */
	public static String encryptAES128(String strPlainText, String strAESKey)
	{
		String encryptString = null;

		try {
			
			byte[] keyBytes = new byte[16];
			byte[] b = strAESKey.getBytes("UTF-8");
			int len = b.length;
			
			if(len > keyBytes.length) {
				len = keyBytes.length;
			}
			
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(strAESKey.getBytes()));
			
			byte[] encrypt = cipher.doFinal(strPlainText.getBytes("UTF-8"));
			encryptString = new String(FlexTalkUtil.base64Encoding(encrypt));
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		catch (InvalidKeyException e) {
			e.printStackTrace();
		} 
		catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} 
		catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return encryptString;		
	}
	
	/**
	 * AES 복호화 함수
	 * @param strEncryptText
	 * @param strAESKey
	 * @return
	 */
	public static String decryptAES128(String strEncryptText, String strAESKey)
	{
		String decryptString = null;
		
		try {
            byte[] keyBytes = new byte[16];
            byte[] b = strAESKey.getBytes("UTF-8");
            int len = b.length;

            if(len > keyBytes.length) {
                len = keyBytes.length;
            }

            System.arraycopy(b, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(strAESKey.getBytes("UTF-8")));

            Base64.Decoder decoder = Base64.getDecoder();
            byte[] BaseDecode = decoder.decode(strEncryptText);
            byte[] decrypt = cipher.doFinal(BaseDecode);
            decryptString  = new String(decrypt);

		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		catch (InvalidKeyException e) {
			e.printStackTrace();
		} 
		catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} 
		catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return decryptString;
	}

}
