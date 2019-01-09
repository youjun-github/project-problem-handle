package com.sigmatrix.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 *@ClassName: EncryptUtil
 *@Description: 加密工具类
 *@author liuzelei
 *@date 2017年6月14日 下午10:14:01
 */
@Component
public class EncryptUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(EncryptUtil.class);
	
	/**
	 * 
	 *@Title: encryptData
	 *@Description: (对字字符串加密)
	 *@return byte[]
	 *@date 2017年6月16日 下午7:17:36
	 *@param encryptdata
	 *@param keys
	 *@return
	 *@throws Exception
	 */
	public static byte[] encryptData(String encryptdata, String keys)
			throws Exception {
		final SecureRandom sr = new SecureRandom();

		final byte[] rawKeyData = keys.getBytes("utf-8");
		final DESKeySpec dks = new DESKeySpec(rawKeyData);

		final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		final SecretKey key = keyFactory.generateSecret(dks);
		final Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);

		return cipher.doFinal(encryptdata.getBytes("UTF-8"));
	}
	
	public static String decryptData(byte[] decryptdata, String keys)
			throws Exception {

		SecureRandom sr = new SecureRandom();

		byte[] rawKeyData = keys.getBytes("utf-8");
		DESKeySpec dks = new DESKeySpec(rawKeyData);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key, sr);

		return new String(cipher.doFinal(decryptdata), "UTF-8");
	}
	/**
	 * 
	 *@Title: encryptByteData
	 *@Description: (对字节数组加密)
	 *@return byte[]
	 *@date 2017年6月16日 下午7:16:18
	 *@param encryptdata
	 *@param keys
	 *@throws Exception
	 */
	public static byte[] encryptByteData(byte[] encryptdata, String keys)
			throws Exception {
		SecureRandom sr = new SecureRandom();

		byte[] rawKeyData = keys.getBytes("utf-8");
		DESKeySpec dks = new DESKeySpec(rawKeyData);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);

		return cipher.doFinal(encryptdata);
	}

	public static byte[] decryptByteData(byte[] decryptdata, String keys)
			throws Exception {

		SecureRandom sr = new SecureRandom();
		
		byte[] rawKeyData = keys.getBytes("utf-8");
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		
		Cipher cipher = Cipher.getInstance("DES"); 
		cipher.init(Cipher.DECRYPT_MODE, key, sr);

		return cipher.doFinal(decryptdata);
	}

	/**
	 * Encode a string using Base64 encoding. Used when storing passwords as
	 * cookies.
	 * 
	 * This is weak encoding in that anyone can use the decodeString routine to
	 * reverse the encoding.
	 * 
	 * @param str
	 * @return String
	 */
	@SuppressWarnings("restriction")
	public static String encodeString(String str) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		try {
			return encoder.encodeBuffer(str.getBytes("utf-8")).trim();
		} catch (Exception e) {
			LOGGER.error("",e);
			return null;
		}
	}

	@SuppressWarnings("restriction")
	public static String encodeString(byte[] bdata) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		try {
			return encoder.encodeBuffer(bdata).trim();
		} catch (Exception e) {
			LOGGER.error("",e);
			return null;
		}
	}

	/**
	 * Decode a string using Base64 encoding.
	 * 
	 * @param str
	 * @return String
	 */
	@SuppressWarnings("restriction")
	public static byte[] decodeString(String str) {
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		try {
			return dec.decodeBuffer(str);
		} catch (IOException io) {
			LOGGER.error("",io);
			return null;
		}
	}

	/**
	 *@Title: decryptFile
	 *@Description: 原文件解密到目标文件
	 *@return String
	 *@date 2017年8月21日 上午9:00:00
	 *@param srcFilePath
	 *@param keys
	 *@param tagFilePath
	 *@throws Exception
	 */
	public static String decryptFile(String srcFilePath, String keys, String tagFilePath)
			throws Exception {
		FileInputStream in = new FileInputStream(srcFilePath);
		int i = in.available();
		byte[] b1 = new byte[i];
		in.read(b1);
		in.close();
		byte[] b2 = decryptByteData(b1, keys);

		FileOutputStream out = new FileOutputStream(tagFilePath);
		out.write(b2);
		out.close();
		return tagFilePath;
	}
	/**
	 *@Title: encryptFile
	 *@Description:
	 *@return String
	 *@param filePath
	 *@param keys
	 *@return
	 *@throws Exception
	 */
	public static String encryptFile(String filePath, String keys)
			throws Exception {
		FileInputStream in = new FileInputStream(filePath);
		int i = in.available();
		byte[] b1 = new byte[i];
		in.read(b1);
		in.close();
		byte[] b2 = encryptByteData(b1, keys);

		String rnFilePath = filePath + ".des";
		FileOutputStream out = new FileOutputStream(rnFilePath);
		out.write(b2);
		out.close();
		return rnFilePath;
	}
	
	/**
	 *@Title: encrypt
	 *@Description: 加密
	 *@return String
	 *@author liuzelei
	 *@date 2017年7月21日 下午5:47:58
	 *@param msg
	 *@param password
	 *@return
	 */
	@SuppressWarnings("restriction")
	public static String encrypt(String msg, String password) {
		String encryptedData = null;  
        try {  
            // DES算法要求有一个可信任的随机数源  
            SecureRandom sr = new SecureRandom();  
            DESKeySpec deskey = new DESKeySpec(password.getBytes());  
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
            SecretKey key = keyFactory.generateSecret(deskey);  
            // 加密对象  
            Cipher cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);  
            // 加密，并把字节数组编码成字符串  
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(msg.getBytes("utf8")));  
        } catch (Exception e) {  
//            log.error("加密错误，错误信息：", e);  
            throw new RuntimeException("加密错误，错误信息：", e);  
        }  
        return encryptedData;  
	}
	
	/**
	 *@Title: decrypt
	 *@Description: 解密
	 *@return String
	 *@date 2017年7月21日 下午5:48:34
	 *@param msg
	 *@param password
	 *@return
	 *@throws Exception
	 */
	@SuppressWarnings("restriction")
	public static String decrypt(String msg, String password) throws Exception {
		String decryptedData = null;  
        try {  
            // DES算法要求有一个可信任的随机数源  
            SecureRandom sr = new SecureRandom();  
            DESKeySpec deskey = new DESKeySpec(password.getBytes());  
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
            SecretKey key = keyFactory.generateSecret(deskey);  
            // 解密对象  
            Cipher cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.DECRYPT_MODE, key, sr);  
            // 把字符串解码为字节数组，并解密  
            decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(msg)),"utf8");
        } catch (Exception e) {  
//            log.error("解密错误，错误信息：", e);  
            throw new RuntimeException("解密错误，错误信息：", e);  
        }  
        return decryptedData;  
	}
	
	public static void main(String[] args) throws Exception {
//		encryptFile("D:/桌面/temp/alldata/ff.txt", "WtzQPdzj");
		//decryptFile("C:\\Users\\gaomingkai\\Desktop\\temp\\XJYTL_IPC00001_20180301183015569.zip", "x9d7ZGPE", "C:\\Users\\gaomingkai\\Desktop\\temp\\IPC00001_20180301183015569.zip");
		/*decryptFile("C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\hrxh0009_IPC00001_20180416131812482.zip", "fEaYOBRc", "C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\IPC00001_20180416131812482.zip");
		decryptFile("C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\hrxh0009_IPC00001_20180416131812655.zip", "fEaYOBRc", "C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\IPC00001_20180416131812655.zip");
		decryptFile("C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\hrxh0009_IPC00001_20180416142915512.zip", "fEaYOBRc", "C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\IPC00001_20180416142915512.zip");
		decryptFile("C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\hrxh0009_IPC00001_20180416142915719.zip", "fEaYOBRc", "C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\IPC00001_20180416142915719.zip");
		decryptFile("C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\hrxh0009_IPC00001_20180416143100920.zip", "fEaYOBRc", "C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\IPC00001_20180416143100920.zip");
		decryptFile("C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\hrxh0009_IPC00001_20180416143106114.zip", "fEaYOBRc", "C:\\Users\\youjun\\Desktop\\TOP_upload\\16_bufen\\1-2\\IPC00001_20180416143106114.zip");
		*/
//		decryptFile("E:/top_ipc_barcode_file/hrxh0009_IPC00001_20180630170017669.zip", "2G51f1r0", "E:/top_ipc_barcode_file/IPC00001_20180630170017669.zip");
		//decryptFile("E:/top_ipc_barcode_file/hrxh0009_IPC00001_20180521182537663.zip", "2G51f1r0", "E:/top_ipc_barcode_file/IPC00001_20180521182537663.zip");
		
//		decryptFile("E:\\youjun\\工作区\\TOP\\湖南雪花\\工控机上传的码包\\hrxh0009_IPC00001_20180729211548772.zip", "2G51f1r0", "E:\\youjun\\工作区\\TOP\\湖南雪花\\工控机上传的码包\\hrxh0009_IPC00001_20180729211548772-jiemi.zip");
//		
		//decryptFile("E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\XJYTL_IPC00008_20180809110338015.zip", "coORo06A", "E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\XJYTL_IPC00008_20180809110338015-jiemi.zip");
		
		//decryptFile("E:\\youjun\\工作区\\TOP\\海升\\成品文件\\QGTYSP\\IPC00002\\2018\\09\\01\\QGTYSP_IPC00002_20180901155833192.zip", "gfneoxUU", "E:\\youjun\\工作区\\TOP\\海升\\成品文件\\QGTYSP\\IPC00002\\2018\\09\\01\\QGTYSP_IPC00002_20180901155833192-jiemi.zip");
		
//		decryptFile("E:\\youjun\\工作区\\TOP\\海升\\成品文件\\QGTYSP\\IPC00002\\2018\\09\\01\\QGTYSP_IPC00002_20180901184325941.zip", "gfneoxUU", "E:\\youjun\\工作区\\TOP\\海升\\成品文件\\QGTYSP\\IPC00002\\2018\\09\\01\\QGTYSP_IPC00002_20180901184325941-jiemi.zip");
//		decryptFile("E:\\youjun\\工作区\\TOP\\海升\\成品文件\\QGTYSP\\IPC00002\\2018\\09\\01\\QGTYSP_IPC00002_20180901173703026.zip", "gfneoxUU", "E:\\youjun\\工作区\\TOP\\海升\\成品文件\\QGTYSP\\IPC00002\\2018\\09\\01\\QGTYSP_IPC00002_20180901173703026-jiemi.zip");
//		decryptFile("E:\\youjun\\工作区\\TOP\\海升\\成品文件\\QGTYSP\\IPC00002\\2018\\09\\01\\QGTYSP_IPC00002_20180901115746048.zip", "gfneoxUU", "E:\\youjun\\工作区\\TOP\\海升\\成品文件\\QGTYSP\\IPC00002\\2018\\09\\01\\QGTYSP_IPC00002_20180901115746048-jiemi.zip");
//		
//		decryptFile("E:\\youjun\\工作区\\TOP\\海升\\QGTYSP_IPC00002_20180904140403455.zip", "gfneoxUU", "E:\\youjun\\工作区\\TOP\\海升\\QGTYSP_IPC00002_20180904140403455-jiemi.zip");
//		decryptFile("E:\\youjun\\工作区\\TOP\\海升\\QGTYSP_IPC00002_20180904140549667.zip", "gfneoxUU", "E:\\youjun\\工作区\\TOP\\海升\\QGTYSP_IPC00002_20180904140549667-jiemi.zip");
//	
//		decryptFile("E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\XJYTL_IPC00004_20180830202532412.zip", "pwSiptXW", "E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\XJYTL_IPC00004_20180830202532412-jiemi.zip");
//		
//		decryptFile("E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\stable\\XJYTL_IPC00011 _20181010111634902.zip", "ZCkX1MNg", "E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\stable\\XJYTL_IPC00011 _20181010111634902-jiemi.zip");
//		
//		decryptFile("E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\错误码文件\\stable\\16\\XJYTL_IPC00012_20181016145909250.zip", "YJWWjfhR", "E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\错误码文件\\stable\\16\\XJYTL_IPC00012_20181016145909250-jiemi.zip");
//		decryptFile("E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\错误码文件\\stable\\16\\XJYTL_IPC00012_20181016151119768.zip", "YJWWjfhR", "E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\错误码文件\\stable\\16\\XJYTL_IPC00012_20181016151119768-jiemi.zip");
//		
		//decryptFile("E:\\youjun\\工作区\\TOP\\银桥乳业\\工控机测试\\149_1_2018-10-11_104850673_Encrypt.zip", "K9UYWVuG", "E:\\youjun\\工作区\\TOP\\银桥乳业\\工控机测试\\149_1_2018-10-11_104850673_Encrypt-jiemi.zip");
		decryptFile("E:\\youjun\\工作区\\TOP\\银桥乳业\\工控机测试\\不能正常处理的\\XJYTL_IPC00005_20181218172847291.zip", "K9UYWVuG", "E:\\youjun\\工作区\\TOP\\银桥乳业\\工控机测试\\不能正常处理的\\XJYTL_IPC00005_20181218172847291-jiemi.zip");
		
	}
	
}
