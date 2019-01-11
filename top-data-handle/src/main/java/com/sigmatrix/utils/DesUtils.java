package com.sigmatrix.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 
 * @author maheng
 *
 */
public class DesUtils {
	
	/**
	 * 加密
	 * @param src
	 * @param key	key长度必须是8的倍数
	 * @return
	 */
	public static byte[] encrypt(byte[] src, String key) {
		try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(src);
        }catch(Throwable e){
           throw new RuntimeException(e);
        }
	}
	
	/**
	 * 解密
	 * @param src
	 * @param key	key长度必须是8的倍数
	 * @return
	 */
	public static byte[] decrypt(byte[] src, String key) {
        try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			return cipher.doFinal(src);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
    }
	
	public static void main(String[] args) {
		String a = "123456";
		String key = MD5Utils.getMD5Code("aasd");
		
		byte[] bs = DesUtils.encrypt(a.getBytes(), key);
		System.out.println("加密后："+new String(bs));
		byte[] bs2 = DesUtils.decrypt(bs, key);
		System.out.println("解密后："+new String(bs2));
	}
}
