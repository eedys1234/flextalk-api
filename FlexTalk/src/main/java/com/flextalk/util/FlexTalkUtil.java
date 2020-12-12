package com.flextalk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class FlexTalkUtil {

	private FlexTalkUtil() {
		throw new AssertionError();
	}

	/**
	 * URLEncoding 함수
	 * @param strPlainText
	 * @return
	 */
	public static String urlEncoding(String strPlainText) 
	{
		String strEncodingText = null;

		try {
			strEncodingText = URLEncoder.encode(strPlainText, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return strEncodingText;
	}
	
	/**
	 * URLDecoding 함수
	 * @param strPlainText
	 * @return
	 */
	public static String urlDecoding(String strPlainText) 
	{
		String strDecodingText = null;

		try {
			strDecodingText = URLDecoder.decode(strPlainText, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return strDecodingText;
	}
	
	/**
	 * Base64 Encoding 함수
	 * @param strPlainText
	 * @return
	 */
	public static String base64Encoding(String strPlainText)
	{
		Encoder encoder = Base64.getEncoder();
		byte[] BaseEncode =  encoder.encode(strPlainText.getBytes());
		return new String(BaseEncode);	
	}
	
	/**
	 * Base64 Encoding 함수
	 * @param strPlainText
	 * @return
	 */
	public static String base64Encoding(byte[] encoding)
	{
		Encoder encoder = Base64.getEncoder();
		byte[] BaseEncode =  encoder.encode(encoding);
		return new String(BaseEncode);	
	}
	
	/**
	 * Base64 Decoding 함수
	 * @param strPlainText
	 * @return
	 */
	public static String base64Decoding(String strPlainText)
	{
		Decoder decoder = Base64.getDecoder();

		byte[] BaseDecode = decoder.decode(strPlainText);
		try {
			return new String(BaseDecode, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Base64 Decoding 함수
	 * @param strPlainText
	 * @return
	 */
	public static String base64Decoding(byte[] decoding)
	{
		Decoder decoder = Base64.getDecoder();

		byte[] BaseDecode = decoder.decode(decoding);
		try {
			return new String(BaseDecode, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    /**
     * 소문자 생성 함수
     * @param digit
     * @return
     */
    public static String getSmallLetter(int digit) {

        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();

        for(int i=0;i<digit; i++) {
            sb.append(String.valueOf((char)((int)rnd.nextInt(26) + 97)));
        }

        return sb.toString();
    }

    /**
     * 대문자 생성 함수
     * @param digit
     * @return
     */
    public static String getBigLetter(int digit) {
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();

        for(int i=0;i<digit; i++) {
            sb.append(String.valueOf((char)((int)rnd.nextInt(26) + 65)));
        }

        return sb.toString();
    }

    /**
     * 숫자 생성 함수
     * @param digit
     * @return
     */
    public static String getNum(int digit) {
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();

        for(int i=0;i<digit; i++) {
            sb.append(String.valueOf((char)((int)rnd.nextInt(10))));
        }

        return sb.toString();
    }

	/**
	 * 랜덤 문자열 생성 함수
	 * @param digit
	 * @return
	 */
	public static String getRandomString(int digit)
	{

		StringBuilder randString = new StringBuilder();
		
		Random rnd = new Random();
		int type = rnd.nextInt(3);
		
		for(int i=0;i<digit;i++)
		{
			switch(type) {
				//숫자
				case 0 : {
					randString.append(getNum(1));
					break;
				}
					//영소문자
				case 1 : {
					randString.append(getSmallLetter(1));
	
					break;
				}
				//영대문자
				case 2 : {
					randString.append(getBigLetter(1));				
					break;
				}
			}
		}
		
		return randString.toString();
	}
	
	/**
	 * UUID 생성 함수
	 * @return
	 */
	public static String createUUID()
	{
		String uuidString = null;
		uuidString = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		return uuidString;
	}
	
	/**
	 * 현재시간을 String으로 변환하여 반환하는 함수
	 * @param pattern
	 * @return
	 */
	public static String getNOWString(String pattern)
	{
		String nowString = null;
		DateFormat sdFormat = new SimpleDateFormat(pattern);
		Date nowDate = new Date();
		nowString = sdFormat.format(nowDate);
		
		return nowString;
	}
	
	/**
	 * 특정시간을 String으로 변환하여 반환하는 함수
	 * @param pattern
	 * @return
	 */
	public static String getNOWString(Date dt, String pattern)
	{
		String nowString = null;
		DateFormat sdFormat = new SimpleDateFormat(pattern);
		nowString = sdFormat.format(dt);
		
		return nowString;
	}
	
}
