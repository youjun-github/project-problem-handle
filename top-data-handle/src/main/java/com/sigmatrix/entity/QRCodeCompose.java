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
package com.sigmatrix.entity;

import java.io.Serializable;

/**
 *@ClassName: QRCodeCompose
 *@Description: 标准码的组成对象
 *@author liuzelei
 *@date 2017年8月13日 下午3:54:25
 */
public class QRCodeCompose implements Serializable {

	/**
	 *@Fields serialVersionUID : TODO()
	 */
	private static final long serialVersionUID = 4131977760286435859L;
	
	/**完整的标准码数据*/
	private String qrcode;
	
	/**域名*/
	private String domain;
	
	/**码内容*/
	private String codeData;
	
	/**参数信息*/
	private String argument;

	/**
	 *@Title: QRCodeCompose
	 *@Description: TODO
	 *@param qrcode
	 */
	public QRCodeCompose(String qrcode) {
		super();
		this.qrcode = qrcode;
	}

	/**
	 *@Title: QRCodeCompose
	 *@Description: TODO
	 */
	public QRCodeCompose() {
		super();
	}

	/**
	 *@Title: QRCodeCompose
	 *@Description: TODO
	 *@param qrcode
	 *@param domain
	 *@param codeData
	 *@param argument
	 */
	public QRCodeCompose(String qrcode, String domain, String codeData, String argument) {
		super();
		this.qrcode = qrcode;
		this.domain = domain;
		this.codeData = codeData;
		this.argument = argument;
	}

	/**
	 *@return the qrcode
	 */
	public String getQrcode() {
		return qrcode;
	}

	/**
	 *@param qrcode the qrcode to set
	 */
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	/**
	 *@return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 *@param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 *@return the codeData
	 */
	public String getCodeData() {
		return codeData;
	}

	/**
	 *@param codeData the codeData to set
	 */
	public void setCodeData(String codeData) {
		this.codeData = codeData;
	}

	/**
	 *@return the argument
	 */
	public String getArgument() {
		return argument;
	}

	/**
	 *@param argument the argument to set
	 */
	public void setArgument(String argument) {
		this.argument = argument;
	}
}
