package com.sigmatrix.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sigmatrix.utils.HttpClientUtil;

public class IpcService {

	private static final Logger logger = LoggerFactory.getLogger(IpcService.class);
	
	public static void main(String[] args) {
		// 1、解密工控机文件
		/*String ipcKey = "K9UYWVuG"; // stable环境ipcCode:IPC00005的key：K9UYWVuG
		String targetHandlePath = "E:/youjun/workspaces/STS/gylProduct/sigmatrix-sc-production-inter-copy/mfg-ipc-service/XJYTL/IPC00005/2018/12/19/";
		String srcFileName = "XJYTL_IPC00005_20181219132335919.zip";
		String srcFilePath = targetHandlePath+srcFileName;
		String[] srcFilePathArray = srcFilePath.split("\\.");
		String tagFilePath = srcFilePathArray[0]+"-jiemi"+"."+srcFilePathArray[1];
		try {
			EncryptUtil.decryptFile(srcFilePath, ipcKey, tagFilePath);
		} catch (Exception e) {
			logger.error("解密失败", e);
		}*/
		
		// 2、测试工控机上传接口
		String url = "http://localhost:9999/mfg-ipc-service/app/updateData";
		Map<String, String> params = new HashMap<String, String>();
		params.put("entCode", "XJYTL");
		params.put("planTime", "2018-12-18 11:00:00");
		params.put("batchCode", "20181119-1");
		params.put("ipcCode", "IPC00005");
		params.put("packLevel", "1");
		params.put("batchId", "177");
		params.put("md5", "d41d8cd98f00b204e9800998ecf8427e");
		List<File> files = new ArrayList<File>();
		File file = new File("E:/youjun/工作区/TOP/银桥乳业/工控机测试/149_1_2018-10-11_104850673_Encrypt.zip");
		files.add(file);
		Map<String, String> headers = new HashMap<String, String>();
		try{
			String returnStr = HttpClientUtil.httpPostFormMultipart(url, params, files, headers, "");
			logger.info("工控机上传接口返回:{}", returnStr);
		}catch(Exception e){
			logger.error("工控机上传接口报错", e);
		}
	}
}
