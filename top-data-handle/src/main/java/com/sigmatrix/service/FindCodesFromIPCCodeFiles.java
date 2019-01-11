package com.sigmatrix.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.sigmatrix.exception.SigmatrixServiceException;
import com.sigmatrix.utils.DateUtil;
import com.sigmatrix.utils.EncryptUtil;
import com.sigmatrix.utils.FileUtils;
import com.sigmatrix.utils.QRCodeUtil;
import com.sigmatrix.utils.Utils;


/**
 * 在工控机上传的码包中寻找某些码
 * @author 
 *
 */
public class FindCodesFromIPCCodeFiles {

	private static final Logger logger = LoggerFactory.getLogger(FindCodesFromIPCCodeFiles.class);
	
	/**
	 * 工控机key的map,key为工控机code,value为工控机的key
	 */
	private static Map<String, String> ipcKeyMap = new HashMap<String, String>();
	
	private static String currentEnv = "prod"; // 当前环境,prod-生产环境,stable-测试环境
	
	static {
		// XJYTL的
		if(currentEnv.equals("prod")) {
			ipcKeyMap.put("IPC00001", "x9d7ZGPE");
			ipcKeyMap.put("IPC00002", "KoYrHwPI");
			ipcKeyMap.put("IPC00003", "zQRvDkWg");
			ipcKeyMap.put("IPC00004", "pwSiptXW");
			ipcKeyMap.put("IPC00005", "13JCIQq9");
			ipcKeyMap.put("IPC00006", "nFDamsGa");
			ipcKeyMap.put("IPC00007", "YvLhJyoE");
			ipcKeyMap.put("IPC00008", "coORo06A");
			ipcKeyMap.put("IPC00009", "5cWnnHVe");
			ipcKeyMap.put("IPC00010", "wY7wDNlv");
			ipcKeyMap.put("IPC00011", "xkG5jll4");
			ipcKeyMap.put("IPC00012", "gHoEVX7s");
			ipcKeyMap.put("IPC00013", "nIza6Dw4");
			ipcKeyMap.put("IPC00014", "UuHQilPI");
		}else if(currentEnv.equals("stable")) {
			ipcKeyMap.put("IPC00001", "FlzVtlU6");
			ipcKeyMap.put("IPC00005", "K9UYWVuG");
			ipcKeyMap.put("IPC00007", "wlqm6iCi");
			ipcKeyMap.put("IPC00009", "Ri1Z6bkb");
			ipcKeyMap.put("IPC00011", "ZCkX1MNg");
			ipcKeyMap.put("IPC00012", "QLgmq7PN");
		}
			
	}
	
	public static void main(String[] args) throws Exception {
		
		currentEnv = "prod"; // 当前环境
//		String folderPathName = "E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\stable\\11";
		//String folderPathName = "E:\\youjun\\工作区\\TOP\\伊力特\\20181012成品数据处理测试\\stable\\IPC00012key-YJWWjfhR\\11";
//		String folderPathName = "E:\\youjun\\工作区\\TOP\\伊力特\\20181012成品数据处理测试\\stable\\IPC00012key-YJWWjfhR\\12";
		//String folderPathName = "E:\\youjun\\工作区\\TOP\\伊力特\\成品数据处理的码包及错误文件\\prod\\历史\\XJYTL\\IPC00001\\11";
		String folderPathName = "E:\\youjun\\工作区\\TOP\\生产环境工控机文件\\mfg-ipc-service\\XJYTL";
		String resultFixPrefix = DateUtil.getCurrentDateStr(DateUtil.YMDHMSN);
		String resultFilePath = "E:\\youjun\\工作区\\TOP\\生产环境工控机文件\\mfg-ipc-service\\XJYTL\\"+resultFixPrefix+"分析结果.txt";
		
		// 1、解密某个文件夹下的工控机文件
		try{
//			jiemiIPCZIP(folderPathName, "YJWWjfhR");
			jiemiIPCZIP(folderPathName, "");
		}catch(Exception e){
			logger.error("jiemiIPCZIP fail(可能为多次解密<文件已存在>)", e);
		}
		String regulationFile = "-jiemi.zip";
		String fileContentSplit = ",";
		/*String code = "HTTP://WWW.AA/Q/8TO:JJ2D0*8B1Q5DRUFHLJ7K";
		int codeType = 1; // 1标准码,2密文
*/		
//		String code = "S_a7dUW8Q-sFQaN7UlP56ytSFexyw80ZIMbLjr6ble8";
		/*String code = "Sq0NxTAv6Y9xitMMI0tLcawIfdPlrYUpR_Ay39E6iwU";
		int codeType = 2; // 1标准码,2密文
		Map<String, String> map = fromFileToFindCode(folderPathName, regulationFile, fileContentSplit, code, codeType);*/
		/*List<String> codeList = Arrays.asList(
				"http://sao.so/A/J6GHXTVHRVOX9OW6UEL+XY",
				"http://sao.so/A/GC2X1.50..:F4XDXLDOM::", // 有
				"HTTP://SAO.SO/A/PSEKZM3C23DIQ$08.MZ-9U",
				"HTTP://SAO.SO/A/0$HFWYD8KP:A$DRNLB:ZW2",
				"HTTP://SAO.SO/A/3:9PEZO+ZT2$A1JZYALM6R", // 有
				"HTTP://SAO.SO/A/1N8.VJ2WOV*7$S0.9PZF50", // 有
				"HTTP://SAO.SO/A/9DQE7$I-$W**IG4CF:ZACM" // 有
				);*/
		List<String> codeList = Arrays.asList(
				"HTTP://SAO.SO/A/1N8.VN4PC+F1A-B9DFL1-0"
//				"HTTP://SAO.SO/A/7H6$O3Q$4QFAPSCI48L9:-?P=0172142214"
				//"HTTP://SAO.SO/A/W3TY:SHYA+:PNZUP9ILN38?P=0172143630"
				);
		int codeType = 1; // 1标准码,2密文
		Map<String, List<String>> map = fromFileToFindCodeList(folderPathName, regulationFile, fileContentSplit, codeList, codeType);
		// 3、打印出上述码存在过的文件
		List<String> dbIPCFileList = new ArrayList<String>();
		
		// 写入文件:
		
		if(!CollectionUtils.isEmpty(map)){
			System.out.println("***************3、找到的文件及码start*****************");
			List<String> keySetToList = new ArrayList<String>();
			keySetToList.addAll(map.keySet()); // 排序
			Collections.sort(keySetToList);
			
			FileUtils.fileLinesWrite(resultFilePath, "查询的码:", true); // 追加到分析结果文件
			for(String code:codeList) {
				FileUtils.fileLinesWrite(resultFilePath, code, true); // 追加到分析结果文件
			}
			for (String key:keySetToList) {
				List<String> lineContentList = map.get(key);
				System.out.println("文件名:"+key);
				FileUtils.fileLinesWrite(resultFilePath, "文件名:"+key, true); // 追加到分析结果文件
				int i=1;
				for(String lineContent:lineContentList) {
					String content = "\t"+i+"、码:"+lineContent
							+"\n\t密文:"+getQrCode(lineContent.split(fileContentSplit)[0])+","+getQrCode(lineContent.split(fileContentSplit)[1]);
					System.out.println(content);
					FileUtils.fileLinesWrite(resultFilePath, content, true); // 追加到分析结果文件
					i++;
				}
				// 将文件名全路径拆分取得文件名
				String fileName[] = key.split("\\\\");
				dbIPCFileList.add(fileName[fileName.length-1].replace("-jiemi", ""));
			}
			System.out.println("***************3、找到的文件及码end*****************");
			System.out.println("***************4、db中的文件名start***************");
			// 4、获得原文件名(数据库中的)
			Collections.sort(dbIPCFileList); // 排序
			for(String dbFile:dbIPCFileList) {
				System.out.println(dbFile);
			}
			System.out.println("***************4、db中的文件名end***************");
			// 5、sql
			System.out.println("***************5、sql start***************");
			String prefix = "select * from _XJYTL_production_data_uploads where _zip_url like ('%";
			String suffix = "');";
			for(String dbFile:dbIPCFileList) {
				System.out.println(prefix+dbFile+suffix);
			}
			System.out.println("***************5、sql end***************");
		}else{
			System.err.println("未找到");
		}
	}
	
	/**
	 * 返回文件集合
	 * @param files 最终返回的文件集合
	 * @param tempList 文件目录集合
	 * @param regulationFile 如果不为空串的话,取指定规则文件名的文件
	 * @return
	 */
	private static List<String> getFileList(List<String> files, File[] tempList, String regulationFile) {
		for (int i = 0; i < tempList.length; i++) { 
			if (tempList[i].isFile()) {
				
				if(StringUtils.isNotBlank(regulationFile)) { // 取指定文件
					String filePathName = tempList[i].toString();
					if (!filePathName.contains(regulationFile)) { 
						continue; // 继续下一次循环
					} 
				}
				// 是文件添加到files中
				files.add(tempList[i].toString()); 
			} 
			if (tempList[i].isDirectory()) {
				// 是目录仍执行该方法
				getFileList(files, tempList[i].listFiles(), regulationFile);
			} 
		}
		return files;
	}
	
	/**
	 * 解密某一個目錄下的所有工控機上傳的碼包文件(如果是目录则继续往下)
	 * @param folderName 文件夹路徑名称
	 * @param ipcKeys 解密密码,<font color="red"><b>2018-11-21修改了,传递空字符串即可,是从静态变量ipcKeyMap中获取的</b></font>
	 * @throws Exception 
	 */
	static void jiemiIPCZIP(String folderPathName, String ipcKeys) throws Exception {
		// 1、获取文件夹下的所有文件
		ArrayList<String> files = new ArrayList<String>(); 
		File file = new File(folderPathName); 
		File[] tempList = file.listFiles(); 
		/*for (int i = 0; i < tempList.length; i++) { 
			if (tempList[i].isFile()) { 
				//              System.out.println("文     件：" + tempList[i]); 
				files.add(tempList[i].toString()); 
			} 
			if (tempList[i].isDirectory()) { 
				//              System.out.println("文件夹：" + tempList[i]); 
			} 
		}*/
		getFileList(files, tempList, "");
		// 2、解密IPC碼文件
		for(String srcFilePath:files) {
			// srcFilePath形如:E:\youjun\工作区\TOP\伊力特\20181012成品数据处理测试\stable\IPC00012key-YJWWjfhR\12\XJYTL_IPC00012_20181012093212981-jiemi.zip
			if(srcFilePath.contains("-jiemi.")) {
				logger.debug("解密时跳过文件:{}", srcFilePath);
				continue;
			}
			String tagFilePath = srcFilePath.replace(".", "-jiemi.");
			// 文件名形如:XJYTL_IPC00012_20181011104609987.zip  截取IPC00012部分,从ipcKeyMap中获取对应的解密密码
			String[] fileSplits = srcFilePath.split("\\\\");
			String fileName = fileSplits[fileSplits.length-1];
			String[] fileNameSplits = fileName.split("_");
			ipcKeys = ipcKeyMap.get(fileNameSplits[1]);
			File tagFile = new File(tagFilePath);
			if(tagFile.exists()) { // 已经在该目录中找到了解密文件(证明已解密过),跳过
				logger.debug("{}已做过解密", srcFilePath);
				continue;
			}
			EncryptUtil.decryptFile(srcFilePath, ipcKeys, tagFilePath);
		}
		
	}
	
	/**
	 * 从某个文件夹下指定某些文件中查找码,返回Map,key为文件名(可能会有多个),value为标准码
	 * @param folderPathName 文件夹路径
	 * @param regulationFile 文件夹路径下的包含指定文件名的文件
	 * @param fileContentSplit 文件中的内容分割符,如","
	 * @param code 码,标准码：HTTP://AA.com/A/safdsadsaas或者密文码:saffwefwewqwq
	 * @param codeType 1标准码,2密文码
	 * @return
	 * @throws Exception 
	 */
	static Map<String, String> fromFileToFindCode(String folderPathName, String regulationFile, String fileContentSplit, String code, int codeType) throws Exception {
		// 1、获取文件夹下的所有文件
		ArrayList<String> files = new ArrayList<String>(); 
		File file = new File(folderPathName); 
		File[] tempList = file.listFiles(); 
		for (int i = 0; i < tempList.length; i++) { 
			// 筛选出指定的文件名的文件
			String filePathName = tempList[i].toString();
			if (tempList[i].isFile()&&filePathName.contains(regulationFile)) { 
				//              System.out.println("文     件：" + tempList[i]); 
				files.add(filePathName); 
			} 
		}
		Map<String, String> map = new HashMap<String, String>();
		// 2、readZipFile
		for(String filePathName:files) {
			List<String> fileContentList = readZipFile(filePathName); // 文件内容
			for(String lineContent:fileContentList) { // 取出每行内容
				// 分割每行的内容进行比对
				String[] contents = lineContent.split(fileContentSplit);
				for(String singleContent:contents) {
					String newMiWenSingleContent = getQrCode(singleContent); // 文件中的码加密
					String newMiWenCode = code;
					// code和文件中的码有可能会存在些许不同(有可能code带?P=而文件中不带),所以统一转成密文来比对
					if(1==codeType) { // 非密文的话,把传递的code加密比对
						newMiWenCode = getQrCode(code);
					}
					if(newMiWenCode.equals(newMiWenSingleContent)) {
						// 返回当前文件名和明文码
						map.put(filePathName, lineContent);
					}
				}
			}
		}
		
		return map;
	}
	
	/**
	 * 从某个文件夹下指定某些文件中查找码,返回Map,key为文件名(可能会有多个),value为标准码
	 * @param folderPathName 文件夹路径
	 * @param regulationFile 文件夹路径下的包含指定文件名的文件
	 * @param fileContentSplit 文件中的内容分割符,如","
	 * @param codeList 码集合,标准码：HTTP://AA.com/A/safdsadsaas或者密文码:saffwefwewqwq
	 * @param codeType 1标准码,2密文码,集合中的码必须是同一类
	 * @return
	 * @throws Exception 
	 */
	static Map<String, List<String>> fromFileToFindCodeList(String folderPathName, String regulationFile, String fileContentSplit, List<String> codeList, int codeType) throws Exception {
		// 1、获取文件夹下的所有文件
		ArrayList<String> files = new ArrayList<String>(); 
		File file = new File(folderPathName); 
		File[] tempList = file.listFiles(); 
		/*for (int i = 0; i < tempList.length; i++) { 
			// 筛选出指定的文件名的文件
			String filePathName = tempList[i].toString();
			if (tempList[i].isFile()&&filePathName.contains(regulationFile)) { 
				//              System.out.println("文     件：" + tempList[i]); 
				files.add(filePathName); 
			} 
		}*/
		getFileList(files, tempList, regulationFile);
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 2、readZipFile
		for(String filePathName:files) {
			List<String> fileContentList = readZipFile(filePathName); // 文件内容
			for(String lineContent:fileContentList) { // 取出每行内容
				// 分割每行的内容进行比对
				String[] contents = lineContent.split(fileContentSplit);
				for(String singleContent:contents) {
					String newMiWenSingleContent = getQrCode(singleContent); // 文件中的码加密
					List<String> lineContentList = null;
					for(String code:codeList) {
						String newMiWenCode = code;
						// code和文件中的码有可能会存在些许不同(有可能code带?P=而文件中不带),所以统一转成密文来比对
						if(1==codeType) { // 非密文的话,把传递的code加密比对
							newMiWenCode = getQrCode(code);
						}
						if(newMiWenCode.equals(newMiWenSingleContent)) {
							// 返回当前文件名和明文码
							lineContentList = new ArrayList<String>();
							if(map.containsKey(filePathName)) {
								lineContentList = map.get(filePathName);
							}
							lineContentList.add(lineContent);
							map.put(filePathName, lineContentList);
						}
					}
					
				}
			}
		}
		
		return map;
	}

	public static List<String> readZipFile(String file) throws Exception { 
		List<String> fileContentList = new ArrayList<String>();
		
        ZipFile zf = new ZipFile(file);  
        InputStream in = new BufferedInputStream(new FileInputStream(file));  
        ZipInputStream zin = new ZipInputStream(in);  
        ZipEntry ze;  
        while ((ze = zin.getNextEntry()) != null) {  
            if (ze.isDirectory()) {
            } else {  
//                System.err.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");  
                long size = ze.getSize();  
                if (size > 0) {  
                    BufferedReader br = new BufferedReader(  
                            new InputStreamReader(zf.getInputStream(ze)));  
                    String line;  
                    while ((line = br.readLine()) != null) {  
//                        System.out.println(line);  
                        fileContentList.add(line); // 将文件内容放入list中
                    }  
                    br.close();  
                }  
                System.out.println();  
            }  
        }  
        zin.closeEntry();  
        
        return fileContentList;
    }  
	
	private static String getQrCode(final String urlCode) throws SigmatrixServiceException{
		try {
			if(Utils.isEmpty(urlCode)){
				return null;
			}
			String qrCode = "";
			String codeTemp = urlCode.toUpperCase();
			if (codeTemp.startsWith("HTTP://") || codeTemp.startsWith("HTTPS://")) {
				int index1 = urlCode.lastIndexOf("/")+1;//反斜杠最后一次出现的位置
				int index2 = urlCode.length();//问号第一次出现的位置，默认整个码的长度
				int indexTemp = urlCode.indexOf("?");//问号第一次出现
				if(indexTemp!=-1){
					index2 = indexTemp;
				}
				String code = urlCode.substring(index1, index2);
			
				if (Utils.isNotEmpty(code)) {
					qrCode = QRCodeUtil.digestSHA256(code);
				} 
			}
			return qrCode;
		} catch (Exception e) {
			logger.error("{} 摘要失败 {}",urlCode);
			return null;
		}
		
	} 
}
