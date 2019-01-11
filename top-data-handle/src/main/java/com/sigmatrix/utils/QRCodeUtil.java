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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.sigmatrix.constants.Constant;
import com.sigmatrix.entity.FirstLevelQRCode;
import com.sigmatrix.entity.MultistageLevelQRCode;
import com.sigmatrix.entity.QRCodeCompose;
import com.sigmatrix.enums.MultistageLevelQRCodeStatusEnum;
import com.sigmatrix.exception.SigmatrixServiceException;

/**
 *@ClassName: QRCodeUtil
 *@Description: 码工具类
 *@author youjun
 *@date 2017年8月8日 下午4:31:12
 */
public class QRCodeUtil {
	
	/**logger record*/
	private static final Logger LOGGER = LoggerFactory.getLogger(QRCodeUtil.class);

	/**SHA-256算法*/
	private static final String SHA256 = "SHA-256";
	
	/**SHA-512算法*/
	private static final String SHA512 = "SHA-512";
	
	/**
	 *@Title: conversionQRCode
	 *@Description: 转换传递的码
	 *@return String
	 *@author youjun
	 *@date 2017年8月8日 下午4:33:51
	 *@param qRCode
	 *@return
	 */
	public static String conversionQRCode(final String qRCode, final boolean isNeedConversion) {
		if(StringUtils.isNotBlank(qRCode)) {
			// ................转换过程
			String code = qRCode;
			if(isNeedConversion){
//				code = testGetFriendCode(qRCode);
				try {
					code = digestSHA256(splitCode(code).getCodeData());
				} catch (Exception e) {
					LOGGER.error("摘要码失败", e);
				}
			}
			return code;
		}
		return "";
	}
	
	/**
	 *@Title: testGetFriendCode
	 *@Description: 测试用的,测试数据形如：HTTP://TCC.SO/A/R*HF:2UAA1LW+Q.HGVCQL1?P=fl101
	 *@return String
	 *@author youjun
	 *@date 2017年8月8日 下午8:54:50
	 *@param qRCode
	 *@return
	 */
	public static String testGetFriendCode(final String qRCode) {
		final String[] codeArray = qRCode.split("=");
		return codeArray[1];
	}
	
	/**
	 *@Title: splitCode
	 *@Description: 将一个完整的码数据拆分
	 *@return QRCodeCompose
	 *@author liuzelei
	 *@date 2017年8月13日 下午4:02:51
	 *@param code
	 *@return
	 */
	public static QRCodeCompose splitCode(String code){
		if(StringUtils.isEmpty(code)){
			return new QRCodeCompose(code);
		}
		final QRCodeCompose qrCode = new QRCodeCompose();
		qrCode.setQrcode(code);
		
		int frist = code.lastIndexOf("/")+1;
		int second = code.indexOf("?");
		if(frist != -1){
			qrCode.setDomain(code.substring(0, frist));
		}
		if(frist != -1 && second == -1){
			qrCode.setCodeData(code.substring(frist));
		}else if(frist != -1 && second != -1){
			qrCode.setCodeData(code.substring(frist,second));
			qrCode.setArgument(code.substring(second));
		}
		return qrCode;
	}
	
	/**
	 *@Title: digestSHA256
	 *@Description: 码使用的SHA256算法,加密后为64位
	 *@return String
	 *@author youjun
	 *@date 2017年8月10日 上午1:37:03
	 *@param src 码
	 *@return
	 *@throws Exception
	 */
	public static String digestSHA256(final String src) throws Exception {
		return digestSHA(src, SHA256);
	}
		
	/**
	 * 
	 * @param src
	 * @param algorithmName it must be SHA-256 or SHA-512
	 * @return
	 * @throws Exception
	 */
	private static String digestSHA(final String src, final String algorithmName) throws Exception{
		String ret = "";
		if(src != null && !src.trim().equals("")) {
			MessageDigest digest = MessageDigest.getInstance(algorithmName);
            byte[] hash = digest.digest(src.getBytes());
            ret = Base64.encodeBase64URLSafeString(hash);
		}
		return ret;
	}

	/**
	 *@Title: getOutStorageCodeStatus
	 *@Description: TODO()
	 *@return Integer
	 *@author youjun
	 *@date 2017年8月13日 下午6:26:40
	 *@param channelType
	 *@return
	 */
	public static Integer getOutStorageCodeStatus(final Short channelType) {

		if(channelType==null) {
			return -1;
		}
		int status = 0;
		switch (channelType) {
		case Constant.OUT_STORAGE_CHANNEL_TYPE_SALES:
			status = MultistageLevelQRCodeStatusEnum.SALES_OUT_STORAGE.getId();
			break;
		case Constant.OUT_STORAGE_CHANNEL_TYPE_REWORK:
			status = MultistageLevelQRCodeStatusEnum.REWORK_OUT_STORAGE.getId();
			break;
		case Constant.OUT_STORAGE_CHANNEL_TYPE_TRANSFERS:
			status = MultistageLevelQRCodeStatusEnum.TRANSFERS_OUT_STORAGE.getId();
			break;
		case Constant.OUT_STORAGE_CHANNEL_TYPE_OTHER:
			status = MultistageLevelQRCodeStatusEnum.OTHER_OUT_STORAGE.getId();
			break;
		default:
			break;
		}
		return status;
	}
	
	/**
	 *@Title: classificationOutByBatch
	 *@Description: 根据产品、批次分组已出库单品码和包装单位码,返回map,key为批次号,value[]为{出库单品数量,出库包装数量}
	 *@return Map<String,Integer []>
	 *@author youjun
	 *@date 2017年8月13日 下午2:40:11
	 *@param singleLevelCodeList
	 *@param packLevelCodeList
	 *@param productId
	 *@return
	 * @throws SigmatrixServiceException 
	 */
	public static Map<String, Integer[]> classificationOutOrInByBatch(final List<FirstLevelQRCode> singleLevelCodeList,
			final List<MultistageLevelQRCode> packLevelCodeList, final int productId) throws SigmatrixServiceException {
		
		// 不能同时为空（同时为空,说明一个码也没出）
		if(CollectionUtils.isEmpty(singleLevelCodeList)&&CollectionUtils.isEmpty(packLevelCodeList)) {
			throw new SigmatrixServiceException("参数有误");
		}
		final Map<String, Integer[]> map = new HashMap<String, Integer[]>();
		// 先取出产品
		if(!CollectionUtils.isEmpty(singleLevelCodeList)) {
			for(final FirstLevelQRCode firstItem:singleLevelCodeList) {
				if(firstItem.getProductId()==productId) {
					final String batchCode = firstItem.getBatchCode();
					if(map.containsKey(batchCode)){
						final Integer[] nums = map.get(batchCode);
						final Integer[] tempNums = new Integer[]{nums[0]+1, nums[1]};
						map.put(batchCode, tempNums); // 放入新的
					}else{
						map.put(batchCode, new Integer[]{1, 0});
					}
				}
			}
		}
		if(!CollectionUtils.isEmpty(packLevelCodeList)) {
			for(final MultistageLevelQRCode multisItem:packLevelCodeList) {
				if(multisItem.getProductId()==productId) {
					final String batchCode = multisItem.getBatchCode();
					if(map.containsKey(batchCode)){
						final Integer[] nums = map.get(batchCode);
						final Integer[] tempNums = new Integer[]{nums[0], nums[1]+1};
						map.put(batchCode, tempNums); // 放入新的
					}else{
						map.put(batchCode, new Integer[]{0, 1});
					}
				}
			}
		}
		return map;
	}
	
	/**
	 *@Title: generatedCode
	 *@Description: 生成码,prefix+15位数字
	 *@return String
	 *@author youjun
	 *@date 2017年8月20日 下午6:02:36
	 *@param prefix
	 *@return
	 */
	public static String generatedCode(final String prefix) {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0     
        // 15 代表长度为15     
        // d 代表参数为正数型
        return prefix+String.format("%015d", hashCodeV);
    }
	
	public static void main(String[] args) throws Exception {
//		String xiangCode = "HTTP://TCC.SO/A/R*HF:2UAA1LW+Q.HGVCQL1?P=fl101";
//		String xiangCode = "HTTP://TT.SAO.SO/Q/Y+F4S90SDDNNYD-LM*H?P=1493822";
		List<String> codeList = new ArrayList<String>();
		codeList.add("HTTP://SAO.SO/A/C1*INHL0$AP6XLKL0G:20E?H");
		
		codeList.add("HTTP://SAO.SO/A/VU5JG9K3+W5:7FWZ62LQ5W?H");
		
		codeList.add("HTTP://SAO.SO/A/KBZRM1:U6C+2V0PKD9L+F6?H");
		
		codeList.add("HTTP://SAO.SO/A/+W+RCMIK+6+J$.2YE6LY3U?P=101912685");
//		codeList.add("HTTP://TT.SAO.SO/Q/:+Z24*JI$4N6OBC*+N9");
//		codeList.add("HTTP://TT.SAO.SO/Q/BI+.*QWE-*7762L6SEZ");
//		codeList.add("HTTP://TT.SAO.SO/Q/+D17VHPF8VWPJGG*FGL");
//		codeList.add("HTTP://TT.SAO.SO/Q/TMEL$-VC7$V5PMOVU:I");
		codeList.add("HTTP://SAO.SO/A/GC2X1.50..:F4XDXLDOM::?P=0170902027");
		codeList.add("HTTP://SAO.SO/A/PSEKZM3C23DIQ$08.MZ-9U?P=0170900704");
		codeList.add("HTTP://SAO.SO/A/0$HFWYD8KP:A$DRNLB:ZW2?P=0170897176");
		codeList.add("HTTP://SAO.SO/A/ML4+-ZF6WMV*ZS8K5*V1PI?P");
		codeList.add("HTTP://SAO.SO/A/ML4+-ZF6WMV*ZS8K5*V1PI");
		codeList.add("-qfFUCghav_Nr0-8wP_gsfMpKlPZc11qiY9RQfhi3i");
		
		codeList.add("*********************************");
		codeList.add("HTTP://WWW.TT/Q/PCGIRR4JEQ3*BLKS8+BO:*C:");
		codeList.add("HTTP://WWW.TT/Q/-6JONNMDP90D2Q.VW1V2Z21Y");
		codeList.add("HTTP://WWW.TT/Q/MQ.7CCKT4OHC+F0$S3J.:*HF");
		codeList.add("HTTP://WWW.TT/Q/JSPBWW.-GLMKY9UDF-LVLN1U");
		codeList.add("HTTP://WWW.TT/Q/08*VLL:CHGU1R0PENNFJ*IZ7");
		codeList.add("HTTP://WWW.TT/Q/4UV6**Z.3IUCBPSY6FSL*ZVW");
		codeList.add("HTTP://WWW.TT/Q/KIX9UU-HZ7I.1QV2KY9Z:F:S");
		codeList.add("HTTP://WWW.TT/Q/08*VLL:CHG88Y73JW119LA77");
		codeList.add("HTTP://WWW.TT/Q/1W$YDDGZ8SWGZC0HIZVXOVS*");
		codeList.add("HTTP://WWW.TT/Q/9-L*ZZO5QR-3BTT$L$.TL14.");
		codeList.add("HTTP://WWW.TT/Q/OMB+PP78I*N*OA$KF96PZISX");
		codeList.add("HTTP://WWW.TT/Q/W5RGAA0NT1.7-EU:G-YD:Z:O");
		codeList.add("****************************");
		codeList.add("HTTP://WWW.XX/Q/XFZL::JYJ1O9PCCDUO7SZL3C");
		codeList.add("HTTP://WWW.TT/Q/9-L*ZZO5QR-3BTT$L$.TL14.");
		codeList.add("HTTP://WWW.TT/Q/OMB+PP78I*N*OA$KF96PZISX");
		codeList.add("*************2018-11-26***************");
		
		codeList.add("HTTP://WWW.TT/Q/9-L*ZZO5QR-3BTT$L$.TL14.");
		codeList.add("HTTP://WWW.XX/Q/XFZL::JYJ1O9PCCDUO7SZL3C");
		codeList.add("HTTP://WWW.TT/Q/OMB+PP78I*N*OA$KF96PZISX");
		codeList.add("HTTP://WWW.XX/Q/XFZL::JYJ1O9PCCDUO7SZL3C");
		codeList.add("HTTP://WWW.TT/Q/4UV6**Z.3I4BJAVI05PX:SJW");
		codeList.add("HTTP://WWW.XX/Q/UE:ZOON$N+JR+E3Z3C3KLQX5");
		codeList.add("HTTP://WWW.TT/Q/DA-8FFT+J5$3:NZ8M.K1LUKQ");
		codeList.add("HTTP://WWW.XX/Q/E*2NMM3R37SV519.16LAZH1D");
		codeList.add("HTTP://WWW.TT/Q/C$0499US*PCH+A4-ZLA9JTC2");
		codeList.add("HTTP://WWW.XX/Q/E*2NMM3R37SV519.16LAZH1D");
		codeList.add("HTTP://WWW.TT/Q/YGUX88FQ:C1PLX-+$P85L$M3");
		codeList.add("HTTP://WWW.XX/Q/3+5CKK$*$9Y3$U$H5BC$ODHT");
		codeList.add("HTTP://WWW.TT/Q/V:3SHH145E06MAYZBXQ2:*JR");
		codeList.add("HTTP://WWW.XX/Q/3+5CKK$*$9Y3$U$H5BC$ODHT");
		codeList.add("HTTP://WWW.TT/Q/1W$YDDGZ83Z:I$Q3O3B0:B1*");
		codeList.add("HTTP://WWW.XX/Q/I4F-EE6P6NTENLWTP429**L1");
		codeList.add("HTTP://WWW.TT/Q/E*2NMM3G7U$ZCD43YRNW:8JD");
		codeList.add("HTTP://WWW.XX/Q/I4F-EE6P6NTENLWTP429**L1");
		codeList.add("HTTP://WWW.TT/Q/:2+1BBWUDV4:S459DKOXOOV9");
		codeList.add("HTTP://WWW.XX/Q/C$0499U3U*XRV:MH$HB9O6*2");
		codeList.add("HTTP://WWW.TT/Q/V:3SHH145FIN60MN$I.I:*-R");
		codeList.add("HTTP://WWW.XX/Q/C$0499U3U*XRV:MH$HB9O6*2");
		codeList.add("HTTP://WWW.TT/Q/PCGIRR4JE1H7::90NIFMO6S:");
		codeList.add("HTTP://WWW.XX/Q/8TO:JJ2D2BNQ135.6*$K*X8K");
		codeList.add("HTTP://WWW.TT/Q/QPYK$$ILUS2CJA+U$5D7OFRV");
		codeList.add("HTTP://WWW.XX/Q/8TO:JJ2D2BNQ135.6*$K*X8K");
		
		codeList.add("HTTP://SAO.SO/A/BMF5L2LU8QF3XQCFYZ*:.X?P=0146135912");
		codeList.add("206554348850");
				codeList.add("206556257175");
						codeList.add("206554348578");
								codeList.add("206556228179");
										codeList.add("206554348095");
		codeList.add("206543740497");
		codeList.add("206538236214");
		
		codeList.add("20181201-10mapping文件-1**************************");
		codeList.add("206700005483");
		codeList.add("206700005520");
		codeList.add("206700005458");
		codeList.add("206700005469");
		codeList.add("206700005479");
		codeList.add("206701229044");
		codeList.add("206700707416");
		codeList.add("206700708235");
		codeList.add("206703199948");
		codeList.add("206703199955");
		codeList.add("206703199973");
		codeList.add("206703199986");
		codeList.add("206703199997");
		codeList.add("20181201-10mapping文件-2**************************");
		codeList.add("206711225647");
		codeList.add("206711225680");
		codeList.add("206711225618");
		codeList.add("206711225628");
		codeList.add("206711225638");
		codeList.add("206709255075");
		codeList.add("206705059052");
		codeList.add("206713002324");
		codeList.add("206709251064");
		codeList.add("206709251079");
		codeList.add("206709251097");
		codeList.add("206709251100");
		codeList.add("206709251113");
		codeList.add("20181201-10mapping文件-3**************************");
		codeList.add("206709251161");
		codeList.add("206709251205");
		codeList.add("206709251139");
		codeList.add("206709251148");
		codeList.add("206709251150");
		codeList.add("206720263145");
		codeList.add("206717219554");
		codeList.add("206709657959");
		codeList.add("206720799946");
		codeList.add("206720799952");
		codeList.add("206720799977");
		codeList.add("206720799981");
		codeList.add("206720799998");
		codeList.add("20180614mapping文件");
		codeList.add("206546747574");
		codeList.add("206546747611");
		codeList.add("206546747543");
		codeList.add("206546747555");
		codeList.add("206546747566");
		codeList.add("206548234867");
		codeList.add("206549467285");
		codeList.add("206556681277");
		codeList.add("206559547476");
		codeList.add("206559547482");
		codeList.add("206559547505");
		codeList.add("206559547515");
		codeList.add("206559547523");
		
		codeList.add("400804800044");
		
		codeList.add("HTTP://SAO.SO/A/ZF$M4E0U4L6JW97ZUR*AUC?P");
		codeList.add("HTTP://SAO.SO/A/+2-C*ML:VI3:XX*-HRLBD9?P=0215338937");
		
		codeList.add("906512066061");
		codeList.add("206542185173");
		codeList.add("906512088259");
		codeList.add("900000000001");
		codeList.add("500000000001");
		codeList.add("HTTP://SAO.SO/A/BMF5L2SC$RB3+NUEWBLLLX?P=0171671945");
		codeList.add("HTTP://SAO.SO/A/BMF5LS8ZM8AG5+VXGVLL*X");
		codeList.add("HTTP://SAO.SO/A/ML4+-*Z1PI$6+QI:EV*+EI?P=0171659518");
		codeList.add("HTTP://SAO.SO/A/$WO43P74F*2CRGDV0MZYZ*?P=0172141847");
		codeList.add("HTTP://SAO.SO/A/$WO43P74-WRN6FJ-ONLZ9*?P=0172142620");
		
		for(final String code:codeList) {
			System.out.println(code+"  摘要后:    "+conversionQRCode(code, true));
		}
	}

	/**
	 *@Title: classificationByProductIdAndBatch
	 *@Description: 从出入库的包装码集合中按产品、批次找出对应的包装码
	 *@return List<String>
	 *@author youjun
	 *@date 2017年8月27日 下午9:43:02
	 *@param inPackLevelCodeList
	 *@param productId
	 * @param batchCode 可为null
	 *@return
	 */
	public static List<String> classificationByProductIdAndBatch(final List<MultistageLevelQRCode> packLevelCodeList,
			int productId, String batchCode) {

		batchCode = StringUtils.isNotBlank(batchCode)?batchCode:"";
		if(!CollectionUtils.isEmpty(packLevelCodeList)) {
			final List<String> boxList = new ArrayList<String>();
			for(final MultistageLevelQRCode item:packLevelCodeList) {
				final String batchCodeItem = StringUtils.isNotBlank(item.getBatchCode())?item.getBatchCode():"";
				if(batchCodeItem.equals(batchCode)) {
					boxList.add(item.getPackContent());
				}
			}
			return boxList;
		}
		return null;
	}
}
