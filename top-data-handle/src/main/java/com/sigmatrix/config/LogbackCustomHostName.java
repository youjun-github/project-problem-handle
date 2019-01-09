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
package com.sigmatrix.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang.StringUtils;

import ch.qos.logback.core.PropertyDefinerBase;

/**
 * @ClassName: LogbackCustomHostName
 * @Description: TODO()
 * @author youjun
 * @date 2017年10月10日 上午11:09:27
 */
public class LogbackCustomHostName extends PropertyDefinerBase {

	public static String LOGBACK_HOSTNAME = "";
	
	static{
		try {
			final InetAddress netAddress = getInetAddress();
			// 获取主机名 linux多网卡无法根据环境指定具体网卡，此方法只能在windows下使用
			LOGBACK_HOSTNAME = getHostName(netAddress);
			if(StringUtils.isNotBlank(LOGBACK_HOSTNAME)&&LOGBACK_HOSTNAME.length()>=15) {
				LOGBACK_HOSTNAME = LOGBACK_HOSTNAME.substring(LOGBACK_HOSTNAME.length()-15);
			}
		} catch (Exception e) {
			LOGBACK_HOSTNAME = "unknown_exception";
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @Title: getPropertyValue
	 * @Description: 
	 * @see ch.qos.logback.core.spi.PropertyDefiner#getPropertyValue()
	 * @author youjun
	 * @date 2017年10月10日 上午11:10:17
	 */
	@Override
	public String getPropertyValue() {
		
		return LOGBACK_HOSTNAME;
	}

	public static InetAddress getInetAddress() {

		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getHostName(InetAddress netAddress) {
		if (null == netAddress) {
			return null;
		}
		String ip = netAddress.getHostName();
		return ip;
	}

}
