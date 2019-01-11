package com.sigmatrix.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;

public class ZipUtils {
	
	public static byte[] getByte(InflaterInputStream zis) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            byte[] temp = new byte[1024];
            byte[] buf = null;
            int length = 0;
            while ((length = zis.read(temp, 0, 1024)) != -1) {
                bout.write(temp, 0, length);
            }
            buf = bout.toByteArray();
            return buf;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
        	 try {
				bout.close();
			} catch (IOException e) {
			}
		}
    }
	
	/**
	 *@Title: inputTobyte
	 *@Description: inStream转byte数组
	 *@return byte[]
	 *@author liyanyong
	 *@date 2018年11月5日 下午8:27:44
	 *@param inStream
	 *@return
	 *@throws IOException
	 */
	public static byte[] inputTobyte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int rc = 0;
		while ((rc = inStream.read(buff)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}
}
