package com.sigmatrix;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.sigmatrix.utils.DesUtils;
import com.sigmatrix.utils.ZipUtils;

public class FileTest {

	@Test
	public void test1() throws Exception {
		String key = "Dvaywk15";
		InputStream is = new FileInputStream("E:\\json.zip");
		byte[] dataByte = ZipUtils.inputTobyte(is);
		byte[] encrypt = DesUtils.encrypt(dataByte, key);
		File file = new File("E:\\jsonEncrypt1.zip");
		OutputStream output = new FileOutputStream(file);
		BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
		bufferedOutput.write(encrypt);
		bufferedOutput.flush();
		bufferedOutput.close();
		output.flush();
		output.close();
	}
	
	@Test
	public void test2() throws Exception {
//		String key = "Dvaywk15";
//		InputStream is = new FileInputStream("E:\\nojson.zip");
		String key = "K9UYWVuG";
		InputStream is = new FileInputStream("E:\\youjun\\工作区\\TOP\\银桥乳业\\工控机测试\\149_1_2018-10-11_104850673.zip");
		byte[] dataByte = ZipUtils.inputTobyte(is);
		byte[] encrypt = DesUtils.encrypt(dataByte, key);
//		File file = new File("E:\\nojsonEncrypt.zip");
		File file = new File("E:\\youjun\\工作区\\TOP\\银桥乳业\\工控机测试\\149_1_2018-10-11_104850673_Encrypt.zip");
		OutputStream output = new FileOutputStream(file);
		BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
		bufferedOutput.write(encrypt);
		bufferedOutput.flush();
		bufferedOutput.close();
		output.flush();
		output.close();
	}
}
