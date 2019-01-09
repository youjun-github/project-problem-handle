/*
* Conditions Of Use
*
* This software was developed by employees of the Sigmatrix(Beijing) Corporation.
* This software is provided by sigmatrix as a service and is expressly
* provided "AS IS."  Sigmatrix MAKES NO WARRANTY OF ANY KIND, EXPRESS, IMPLIED
* OR STATUTORY, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTY OF
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT
* AND DATA ACCURACY.  Sigmatrix does not warrant or make any representations
* regarding the use of the software or the results thereof, including but
* not limited to the correctness, accuracy, reliability or usefulness of
* the software.
*
* Permission to use this software is contingent upon your acceptance
* of the terms of this agreement.
*
*/
package com.sigmatrix.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@ClassName: MD5Utils
 *@Description: MD5 encryption
 *@author youjun
 *@date 2017年1月4日 下午10:21:40
 */
public class MD5Utils {
	/**logger record*/
	private static final Logger LOGGER = LoggerFactory.getLogger(MD5Utils.class);
    
    /**Global array*/
    private static final String[] STRDIGITS = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
 
    /**
     *@Title: MD5Utils
     *@Description:
     */
    public MD5Utils() {
    }
 
    /**
     *@Title: byteToArrayString
     *@Description: Return to form for Numbers and strings
     *@return String
     *@author youjun
     *@date 2017年1月4日 下午10:22:48
     *@param bByte
     *@return
     */
    private static String byteToArrayString(final byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        final int iD1 = iRet / 16;
        final int iD2 = iRet % 16;
        return STRDIGITS[iD1] + STRDIGITS[iD2];
    }
 
    
    /**
     *@Title: byteToString
     *@Description: Convert the byte array to hexadecimal string
     *@return String
     *@author youjun
     *@date 2017年1月4日 下午10:23:27
     *@param bByte
     *@return
     */
    private static String byteToString(final byte[] bByte) {
        final StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
 
    /**
     *@Title: GetMD5Code
     *@Description: Get the MD5 encrypted string
     *@return String
     *@author youjun
     *@date 2017年1月4日 下午10:23:52
     *@param strObj
     *@return
     */
    public static String getMD5Code(final String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            final MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("GetMD5Code fail", ex);
        }
        return resultString;
    }
    public static void main(String[] args) {
    	System.out.println("xxxxxxxxxxx");
//		String md5Code = getMD5Code("ty111111");
    	String md5Code = getMD5Code("123456");
		System.out.println(md5Code);
	}
}

