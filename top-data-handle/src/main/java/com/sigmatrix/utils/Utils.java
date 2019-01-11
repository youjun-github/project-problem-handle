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

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Utils
 * @Description 常用的方法集合
 * @author zzw
 * @date 2016年10月21日 上午9:09:38
 */
public class Utils {

	/**
	 * @Method: isEmpty
	 * @Description: 字符串为空判断
	 * @param str
	 * @return boolean
	 * @date 2016年10月21日 上午9:27:57
	 */
	public static boolean isEmpty(final String str) {
		if (null == str || str.trim().length() == 0 || "null".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Method: isNotEmpty
	 * @Description: 字符串非空判断
	 * @param str
	 * @return boolean
	 * @date 2016年10月21日 上午9:30:25
	 */
	public static boolean isNotEmpty(final String str) {
		return !isEmpty(str);
	}

	/**
	 * @Title: isEmpty
	 * @Description: 判断StringBuilder是否为空
	 * @return boolean
	 * @date 2017年6月5日 下午8:30:15
	 * @param obj
	 */
	public static boolean isEmpty(final StringBuilder obj) {
		if (null == obj || obj.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Title: isNotEmpty
	 * @Description: StringBuilder非空判断
	 * @return boolean
	 * @date 2017年6月5日 下午8:35:57
	 * @param obj
	 */
	public static boolean isNotEmpty(final StringBuilder obj) {
		return !isEmpty(obj);
	}

	/**
	 * @Method: isEmpty
	 * @Description: 判断实体是否为空
	 * @param obj
	 * @return boolean
	 * @date 2016年10月21日 上午9:30:48
	 */
	public static boolean isEmpty(final Object obj) {
		return null == obj;
	}

	/**
	 * @Method: isNotEmpty
	 * @Description: 字符串不为空判断
	 * @param obj
	 * @return boolean
	 * @date 2016年11月1日 上午11:15:14
	 */
	public static boolean isNotEmpty(final Object obj) {
		return null != obj;
	}
	/**
	 *@Title: isNotEmptyZero
	 *@Description: 判断Integer不为空，且不等于0
	 *@return boolean
	 *@date 2017年8月28日 下午2:32:57
	 *@param obj
	 */
	public static boolean isNotEmptyZero(final Integer obj){
		return  !isEmptyZero(obj);
	}
	
	public static boolean isEmptyZero(final Integer obj){
		return isEmpty(obj) || obj==0;
	}
	
	/**
	 *@Title: isNotEmptyZero
	 *@Description: 判断Long不为空，企业不等于
	 *@return boolean
	 *@date 2017年8月28日 下午4:14:46
	 *@param obj
	 */
	public static boolean isNotEmptyZero(final Long obj){
		return  !isEmptyZero(obj);
	}
	
	public static boolean isEmptyZero(final Long obj){
		return isEmpty(obj) || obj==0;
	}
	
	//-----------------collection--------------------
	
	/**
	 *@Title: size
	 *@Description: 获取Collection集合长度
	 *@return int
	 *@date 2017年5月30日 下午4:29:28
	 *@param collection
	 */
	public static int size(final Collection<?> collection){
		return isEmpty(collection)?0:collection.size();
	}
	
	/**
	 *@Title: size
	 *@Description: 获取Map集合长度
	 *@return int
	 *@date 2017年5月30日 下午4:29:43
	 *@param map
	 */
	public static int size(final Map<?, ?> map){
		return isEmpty(map)?0:map.size();
	}
	
	
	/**
	 *@Title: isEmpty
	 *@Description: 判断Collection集合为空
	 *@return boolean
	 *@date 2017年5月30日 下午4:29:56
	 *@param collection
	 */
	public static boolean isEmpty(final Collection<?> collection) {
		return collection == null || collection.isEmpty() || collection.size() <=0;
	}
	
	
	/**
	 *@Title: isNotEmpty
	 *@Description: 判断Collection集合为空
	 *@return boolean
	 *@date 2017年5月30日 下午4:30:07
	 *@param collection
	 */
	public static boolean isNotEmpty(final Collection<?> collection) {
		return !isEmpty(collection);
	}
	
	/**
	 *@Title: isEmpty
	 *@Description: 判断Map集合为空
	 *@return boolean
	 *@date 2017年5月30日 下午4:30:24
	 *@param map
	 */
	public static boolean isEmpty(final Map<?, ?> map) {
		return map == null || map.isEmpty();
	}
	
	/**
	 *@Title: isNotEmpty
	 *@Description: 判断Map集合不为空
	 *@return boolean
	 *@date 2017年5月30日 下午4:30:40
	 *@param map
	 */
	public static boolean isNotEmpty(final Map<?, ?> map) {
		return !isEmpty(map);
	}
	
	/**
	 *@Title: listToInArray
	 *@Description: 整形List集合转化为int数组
	 *@return int[]
	 *@date 2017年7月4日 下午6:49:14
	 *@param list
	 */
	public static int[] listToInArray(final List<Integer> list){
		int[] intArry = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {  
			intArry[i] = list.get(i);  
        }  
		return intArry;
	}
	
	/**
	 * @Method: getLogId
	 * @Description: 根据日期生成 年月日时分秒毫秒_4位数字(循环累加一万级)
	 * @return String
	 */
	private static long num = 0;
	public static synchronized String getLogId(final String eseCode,final String formate) {
		String dateFormate = null;
		if (Utils.isEmpty(formate)) {
			dateFormate = "yyyyMMddHHmmssSSS";
		}else{
			dateFormate = formate;
		}
		SimpleDateFormat f = new SimpleDateFormat(dateFormate);
		String date = f.format(System.currentTimeMillis());
		if (num >= 9999)
			num = 0l;
		++num;
		return String.format("%s_%s%04d",eseCode,date,num);
	}
	

}
