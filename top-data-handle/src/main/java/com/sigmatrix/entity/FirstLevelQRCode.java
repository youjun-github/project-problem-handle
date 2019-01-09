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
import java.util.Date;

/**
 * @ClassName: FirstLevelQRCode
 * @Description: 一级码表
 * @author zzw
 * @date 2017年6月23日 下午6:33:11
 */
public class FirstLevelQRCode implements Serializable {
	/**
	 *@Fields serialVersionUID : TODO()
	 */
	private static final long serialVersionUID = 61901134333350021L;
	
	private String qrCode;// 一级码存储码，采用加密方式:SHA-256
	private String enterpriseCode;// 企业代码
	private Integer activated;// 状态,0-非激活, 1-激活
	private Integer status; // 0-生产, 1-入库, 2-出库, 3-分销
	private Date createTime;// 创建时间
	private String inputCode;// 输入码
	private String outsideCode;// 外码
	private String outInputCode;// 外码输入码
	private String encryptedParentCode;// 上级存储码
	private String codeUrl; // 码域名
	private String seqNum; // 码序列号
	/**友好码,等于friendlyCodeFront+friendlyCodeEnd*/
	private String friendlyCode;
	private String friendlyCodeFront;
	private String friendlyCodeEnd;
	private String parentCode;// 上级码
	private Integer productId; // 产品ID
	private String productCode;// 产品码
	private String batchCode;// 批次号
	private Date produceTime;// 生产时间
	private Integer warehouseId;// 仓库ID
	private String warehouseCode;// 仓库编码
	private Date outStockTime;// 出库时间
	private Date inStockTime;// 入库时间
	private String storageEntryId;// 入库单号
	private String storageExitId;// 出库单号
	private String storageExitSourceId; // 出库来源单号
	private Integer dealersId;// 经销商ID
	private String dealersCode; // 经销商代码
	private Long orderId;//订单id
	private Integer isDimSync;//码维度文件同步标识 null 0-没有同步 1-已经同步

	/**
	 * @return the qrCode
	 */
	public String getQrCode() {
		return qrCode;
	}

	/**
	 * @return the enterpriseCode
	 */
	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	/**
	 * @return the activated
	 */
	public Integer getActivated() {
		return activated;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @return the inputCode
	 */
	public String getInputCode() {
		return inputCode;
	}

	/**
	 * @return the outsideCode
	 */
	public String getOutsideCode() {
		return outsideCode;
	}

	/**
	 * @return the outInputCode
	 */
	public String getOutInputCode() {
		return outInputCode;
	}

	/**
	 * @return the encryptedParentCode
	 */
	public String getEncryptedParentCode() {
		return encryptedParentCode;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @return the batchCode
	 */
	public String getBatchCode() {
		return batchCode;
	}

	/**
	 * @return the produceTime
	 */
	public Date getProduceTime() {
		return produceTime;
	}

	/**
	 * @return the warehouseId
	 */
	public Integer getWarehouseId() {
		return warehouseId;
	}

	/**
	 * @return the warehouseCode
	 */
	public String getWarehouseCode() {
		return warehouseCode;
	}

	/**
	 * @return the storageEntryId
	 */
	public String getStorageEntryId() {
		return storageEntryId;
	}

	/**
	 * @return the storageExitId
	 */
	public String getStorageExitId() {
		return storageExitId;
	}

	/**
	 * @return the dealersId
	 */
	public Integer getDealersId() {
		return dealersId;
	}

	/**
	 * @param qrCode the qrCode to set
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	/**
	 * @param enterpriseCode the enterpriseCode to set
	 */
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(Integer activated) {
		this.activated = activated;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param inputCode the inputCode to set
	 */
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	/**
	 * @param outsideCode the outsideCode to set
	 */
	public void setOutsideCode(String outsideCode) {
		this.outsideCode = outsideCode;
	}

	/**
	 * @param outInputCode the outInputCode to set
	 */
	public void setOutInputCode(String outInputCode) {
		this.outInputCode = outInputCode;
	}

	/**
	 * @param encryptedParentCode the encryptedParentCode to set
	 */
	public void setEncryptedParentCode(String encryptedParentCode) {
		this.encryptedParentCode = encryptedParentCode;
	}

	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @param batchCode the batchCode to set
	 */
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	/**
	 * @param produceTime the produceTime to set
	 */
	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}

	/**
	 * @param warehouseId the warehouseId to set
	 */
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**
	 * @param warehouseCode the warehouseCode to set
	 */
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	/**
	 * @param storageEntryId the storageEntryId to set
	 */
	public void setStorageEntryId(String storageEntryId) {
		this.storageEntryId = storageEntryId;
	}

	/**
	 * @param storageExitId the storageExitId to set
	 */
	public void setStorageExitId(String storageExitId) {
		this.storageExitId = storageExitId;
	}

	/**
	 * @param dealersId the dealersId to set
	 */
	public void setDealersId(Integer dealersId) {
		this.dealersId = dealersId;
	}

	/**
	 *@return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 *@param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 *@return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 *@param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 *@return the friendlyCode
	 */
	public String getFriendlyCode() {
		return friendlyCode;
	}

	/**
	 *@param friendlyCode the friendlyCode to set
	 */
	public void setFriendlyCode(String friendlyCode) {
		this.friendlyCode = friendlyCode;
	}

	/**
	 *@return the friendlyCodeFront
	 */
	public String getFriendlyCodeFront() {
		return friendlyCodeFront;
	}

	/**
	 *@param friendlyCodeFront the friendlyCodeFront to set
	 */
	public void setFriendlyCodeFront(String friendlyCodeFront) {
		this.friendlyCodeFront = friendlyCodeFront;
	}

	/**
	 *@return the friendlyCodeEnd
	 */
	public String getFriendlyCodeEnd() {
		return friendlyCodeEnd;
	}

	/**
	 *@param friendlyCodeEnd the friendlyCodeEnd to set
	 */
	public void setFriendlyCodeEnd(String friendlyCodeEnd) {
		this.friendlyCodeEnd = friendlyCodeEnd;
	}

	/**
	 *@return the codeUrl
	 */
	public String getCodeUrl() {
		return codeUrl;
	}

	/**
	 *@param codeUrl the codeUrl to set
	 */
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	/**
	 *@return the seqNum
	 */
	public String getSeqNum() {
		return seqNum;
	}

	/**
	 *@param seqNum the seqNum to set
	 */
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	/**
	 *@return the isDimSync
	 */
	public Integer getIsDimSync() {
		return isDimSync;
	}

	/**
	 *@param isDimSync the isDimSync to set
	 */
	public void setIsDimSync(Integer isDimSync) {
		this.isDimSync = isDimSync;
	}

	/**
	 *@return the outStockTime
	 */
	public Date getOutStockTime() {
		return outStockTime;
	}

	/**
	 *@return the inStockTime
	 */
	public Date getInStockTime() {
		return inStockTime;
	}

	/**
	 *@param outStockTime the outStockTime to set
	 */
	public void setOutStockTime(Date outStockTime) {
		this.outStockTime = outStockTime;
	}

	/**
	 *@param inStockTime the inStockTime to set
	 */
	public void setInStockTime(Date inStockTime) {
		this.inStockTime = inStockTime;
	}

	/**
	 *@return the dealersCode
	 */
	public String getDealersCode() {
		return dealersCode;
	}

	/**
	 *@param dealersCode the dealersCode to set
	 */
	public void setDealersCode(String dealersCode) {
		this.dealersCode = dealersCode;
	}

	/**
	 *@return the storageExitSourceId
	 */
	public String getStorageExitSourceId() {
		return storageExitSourceId;
	}

	/**
	 *@param storageExitSourceId the storageExitSourceId to set
	 */
	public void setStorageExitSourceId(String storageExitSourceId) {
		this.storageExitSourceId = storageExitSourceId;
	}

}
