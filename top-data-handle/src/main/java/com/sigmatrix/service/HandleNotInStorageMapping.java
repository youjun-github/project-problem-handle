package com.sigmatrix.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sigmatrix.utils.FileUtils;
import com.sigmatrix.utils.QRCodeUtil;

/**
 * 处理没有入库的酒鬼酒的mapping文件,包括:全部未入库,部分未入库,根据原始mapping文件产生insert语句,产生的insert语句会先判断
 * 数据在codeAssociated中是否存在,存在不insert
 * @author 
 *
 */
public class HandleNotInStorageMapping {
	
	public static final String antiFakeCode = "antiFakeCode";
	public static final String packContent = "packContent";
	public static final String logisticsCode = "logisticsCode";
	public static final String logisticsCodeCrypto = "logisticsCodeCrypto";

	/**
	 * 只用于处理酒鬼酒天鉴的mapping文件,若要处理其他的,请通读具体的每一行代码
	 * @param sourceFilePath 源文件,也就是天鉴的mapping,读取该源文件,文件格式:营销码或防伪码,物流码<br/>
	 * 形如:<br/>
	 * 400804800044,206546747574<br/>
	 * 对应codeAssociated表说明:<br/>
	 * antiFakeCode:对应400804800044<br/>
	 * packContent:对应400804800044的密文<br/>
	 * logisticsCode:对应206546747574<br/>
	 * logisticsCodeCrypto:对应206546747574的密文<br/>
	 * @param targetFilePath 组装成最终的mongo sql后写入目标文件
	 * @throws Exception 
	 */
	private static void createCodeAssociatedMongoSQL(String sourceFilePath, String targetFilePath) throws Exception {
		
		StringBuilder mongoSQLTemplate = new StringBuilder("if(!db.codeAssociated.find({"); // 开始
		mongoSQLTemplate.append("\"packContent\":\"?\"}).hasNext()){"); // 0位置
		mongoSQLTemplate.append("db.codeAssociated.insert(({"); 
		mongoSQLTemplate.append("\"antiFakeCode\":\"?\","); // 1位置
		mongoSQLTemplate.append("\"packContent\":\"?\","); // 2位置
		mongoSQLTemplate.append("\"logisticsCode\":\"?\","); // 3位置
		mongoSQLTemplate.append("\"logisticsCodeCrypto\":\"?\""); // 4位置
		mongoSQLTemplate.append("}))};"); // 结尾
		
		String[] mongoSQL = mongoSQLTemplate.toString().split("\\?");
		// 以行为单位读取源文件中每一行
		Map<String, String> map = null;
		List<String> sourceFileContentList = FileUtils.readFileContent(sourceFilePath);
		int i = 1;
		int index = 1;
		for(String lineContent:sourceFileContentList) {
			String[] lineContentArray = lineContent.split(",");
			String antiFakeCode = lineContentArray[0]; // 第一行营销码或防伪码,400804800044
			String logisticsCode = lineContentArray[1]; // 第二行物流码,206546747574
			map = new HashMap<String, String>();
			map.put(antiFakeCode, antiFakeCode); // 营销码或防伪码
			// 注意,若是待http的就要先拆了取得字符串后才能调用加密方法
			/*QRCodeCompose splitCode = splitCode(antiFakeCode);
			System.out.println(JSON.toJSONString(splitCode));
			System.out.println(digestSHA256(splitCode.getCodeData()));*/
			map.put(packContent, QRCodeUtil.digestSHA256(antiFakeCode)); // antiFakeCode的密文
			map.put(logisticsCode, logisticsCode); // 物流码
			map.put(logisticsCodeCrypto, QRCodeUtil.digestSHA256(logisticsCode)); // logisticsCode的密文
			// 组装mongo sql语句
			StringBuilder targetMongoSQL = new StringBuilder("");
			targetMongoSQL.append(mongoSQL[0]).append(map.get(packContent));
			targetMongoSQL.append(mongoSQL[1]).append(map.get(antiFakeCode));
			targetMongoSQL.append(mongoSQL[2]).append(map.get(packContent));
			targetMongoSQL.append(mongoSQL[3]).append(map.get(logisticsCode));
			targetMongoSQL.append(mongoSQL[4]).append(map.get(logisticsCodeCrypto));
			targetMongoSQL.append(mongoSQL[5]); // 结尾
			// 读取一行组装完毕后,即写入目标文件
			if(i%120000==0) {
				index+=1;
				System.out.println("当前处理到:"+i);
			}
			String[] targetFileArray = targetFilePath.split("\\.");
			FileUtils.fileLinesWrite(targetFileArray[0]+"-"+index+ "" +targetFileArray[1], targetMongoSQL.toString(), true);
			i++;
		}
		System.out.println(i+"行完成!");
	}
	
	public static void main(String[] args) {
		String sourceFilePath = "E:\\youjun\\工作区\\TOP\\酒鬼酒\\天鉴码数据\\prod\\20180614020849\\mapping.txt";
		String targetFilePath = "E:\\youjun\\工作区\\TOP\\酒鬼酒\\天鉴码数据\\prod\\20180614020849\\mapping-target.txt";
		try {
			createCodeAssociatedMongoSQL(sourceFilePath, targetFilePath);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
}
