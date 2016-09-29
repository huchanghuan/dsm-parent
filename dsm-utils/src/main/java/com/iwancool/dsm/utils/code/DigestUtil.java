package com.iwancool.dsm.utils.code;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.DigestUtils;

public class DigestUtil {

	/**
	 * 获得MD5值
	 * @Description (TODO
	 * @param data
	 * @return
	 */
	public static String md5 (byte[] data) {
		return DigestUtils.md5DigestAsHex(data);
	}
	
	/**
	 * 获得文件MD5值
	 * @author	huchanghuan
	 * @param file
	 * @return
	 * @since   1.0.0
	 */
	public static String md5(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return md5(fis);
		} catch (Exception e) {
			return ""; 
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获得流的MD5值
	 * @author	huchanghuan
	 * @param is
	 * @return
	 * @since   1.0.0
	 */
	public static String md5 (InputStream is) {
		int buffSize = 256 * 1024;
		DigestInputStream dis = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			dis = new DigestInputStream(is, md);
			byte[] buf = new byte[buffSize];
			while (dis.read(buf) > 0);
			byte[] result = dis.getMessageDigest().digest();
			return bytesToHex(result);
		} catch (Exception e) {
			return "";
		}finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 摘要输入流
	 * @author	huchanghuan
	 * @param bytes
	 * @param algorithm
	 * @return
	 * @since   1.0.0
	 * @date    2016年4月1日 上午10:58:46
	 */
	public static byte[] digestInputStream(byte[] bytes,String algorithm){
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("不能识别的算法！");
		}
		
		DigestInputStream dis = new DigestInputStream(bis, md);
		
		try {
			dis.read(bytes);
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dis.getMessageDigest().digest();
	}
	
	/**
	 * 摘要输出流
	 * @Description (TODO
	 * @param bytes
	 * @param algorithm
	 * @return
	 */
	public static byte[] digestOutputStream(byte[] bytes,String algorithm){
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		MessageDigest md = null;
		try {
			 md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("不能识别的算法！");
		}
		
		DigestOutputStream dos = new DigestOutputStream(bos, md);
		
		try {
			//更新摘要
			dos.write(bytes);
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dos.getMessageDigest().digest();
	}
	
	/**
	 * 字节数组转16进制字符串
	 * @Description (TODO
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		return new HexBinaryAdapter().marshal(bytes);
	}
	
	/**
	 * 16进制字符串转字节数组
	 * @Description (TODO
	 * @param str
	 * @return
	 */
	public static byte[] hexToBytes(String str) {
		return new HexBinaryAdapter().unmarshal(str);
	}
	
	/**
	 * base64编码
	 * @Description (TODO
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encodeBase64(String data) throws Exception {
		
		return Base64.encodeBase64String(data.getBytes("utf-8"));
	}
	
	/**
	 * base64解码
	 * @Description (TODO
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decodeBase64(String data) throws Exception {
		return new String(Base64.decodeBase64(data),"utf-8");
	}
	
	/*public static void main(String[] args) throws Exception {
		String s = "hello";
		String s1 = encodeBase64(s);
		String s2 = decodeBase64(s1);
		System.out.println(s1);
		System.out.println(s2);
	}*/
}
