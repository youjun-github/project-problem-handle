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
 * @ClassName: BarcodeMultistageLevel
 * @Description: 多级码表
 * @author zzw
 * @date 2017年6月23日 下午6:52:59
 */
public class MultistageLevelQRCode implements Serializable {

	/**
	 *@Fields serialVersionUID : TODO()
	 */
	private static final long serialVersionUID = 7362735328655950580L;
	
	private String packContent;// 包装码内容,若为实体码,采用加密方式:SHA-256
	private String enterpriseCode;// 企业代码
	private String packName;// 包装名称
	private String childs;// 下级码内容/（一级码直接读取，不存）
	private Integer packLevel;// 包装级别
	private String codeUrl; // 码域名
	private String seqNum; // 码序列号
	/**友好码,等于friendlyCodeFront+friendlyCodeEnd*/
	private String friendlyCode;
	private String friendlyCodeFront;
	private String friendlyCodeEnd;
	private Integer childNum;// 下级数量
	private String parentCode;//上级码
	private Integer productId;// 产品ID
	private String productCode; // 产品code
	private Integer status;// 0-生产, 1-入库, 2销售出库, 3-返工出库,4-调拨, 5-其他出库
	private Integer activeStatus;// 0-未激活，1-已激活
	private Integer normalStatus;// 1-正常码，2-补码
	private Integer warehouseId;// 仓库ID
	private Integer dealersId;// 经销商ID
	private String dealersCode; // 经销商代码
	private String planCode;// 生产计划code
	private String taskCode;//生产任务code
	private String batchCode;// 批次号
	private Date produceTime;// 生产时间
	private Integer layerNum;// 层数码
	private Date outStockTime;// 出库时间
	private Date inStockTime;// 入库时间
	private String storageEntryId;// 入库单号
	private String storageExitId;// 出库单号
	private String storageExitSourceId; // 出库来源单号
	private Date createTime;// 创建时间
	private Date activatedTime;// 激活时间
	private Long orderId;//订单id
	private Integer isDimSync;//码维度文件同步标识 null 0-没有同步 1-已经同步

	private Long sumChildNum;// 下级码数量总和  非表字段

	/**
	 * @return the packContent
	 */
	public String getPackContent() {
		return packContent;
	}

	/**
	 * @return the packName
	 */
	public String getPackName() {
		return packName;
	}

	/**
	 * @return the childs
	 */
	public String getChilds() {
		return childs;
	}


	/**
	 * @return the childNum
	 */
	public Integer getChildNum() {
		return childNum;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @return the activeStatus
	 */
	public Integer getActiveStatus() {
		return activeStatus;
	}

	/**
	 * @return the normalStatus
	 */
	public Integer getNormalStatus() {
		return normalStatus;
	}

	/**
	 * @return the warehouseId
	 */
	public Integer getWarehouseId() {
		return warehouseId;
	}

	/**
	 * @return the dealersId
	 */
	public Integer getDealersId() {
		return dealersId;
	}


	/**
	 * @return the layerNum
	 */
	public Integer getLayerNum() {
		return layerNum;
	}

	/**
	 * @return the outStockTime
	 */
	public Date getOutStockTime() {
		return outStockTime;
	}

	/**
	 * @return the inStockTime
	 */
	public Date getInStockTime() {
		return inStockTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @return the activatedTime
	 */
	public Date getActivatedTime() {
		return activatedTime;
	}

	/**
	 * @param packContent the packContent to set
	 */
	public void setPackContent(String packContent) {
		this.packContent = packContent;
	}

	/**
	 * @param packName the packName to set
	 */
	public void setPackName(String packName) {
		this.packName = packName;
	}

	/**
	 * @param childs the childs to set
	 */
	public void setChilds(String childs) {
		this.childs = childs;
	}

	/**
	 * @param childNum the childNum to set
	 */
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	/**
	 * @param normalStatus the normalStatus to set
	 */
	public void setNormalStatus(Integer normalStatus) {
		this.normalStatus = normalStatus;
	}

	/**
	 * @param warehouseId the warehouseId to set
	 */
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**
	 * @param dealersId the dealersId to set
	 */
	public void setDealersId(Integer dealersId) {
		this.dealersId = dealersId;
	}

	/**
	 * @param layerNum the layerNum to set
	 */
	public void setLayerNum(Integer layerNum) {
		this.layerNum = layerNum;
	}

	/**
	 * @param outStockTime the outStockTime to set
	 */
	public void setOutStockTime(Date outStockTime) {
		this.outStockTime = outStockTime;
	}

	/**
	 * @param inStockTime the inStockTime to set
	 */
	public void setInStockTime(Date inStockTime) {
		this.inStockTime = inStockTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param activatedTime the activatedTime to set
	 */
	public void setActivatedTime(Date activatedTime) {
		this.activatedTime = activatedTime;
	}

	/**
	 * @return the sumChildNum
	 */
	public Long getSumChildNum() {
		return sumChildNum;
	}

	/**
	 * @param sumChildNum the sumChildNum to set
	 */
	public void setSumChildNum(Long sumChildNum) {
		this.sumChildNum = sumChildNum;
	}

	/**
	 *@return the packLevel
	 */
	public Integer getPackLevel() {
		return packLevel;
	}

	/**
	 *@return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 *@param packLevel the packLevel to set
	 */
	public void setPackLevel(Integer packLevel) {
		this.packLevel = packLevel;
	}

	/**
	 *@param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 *@return the planCode
	 */
	public String getPlanCode() {
		return planCode;
	}

	/**
	 *@param planCode the planCode to set
	 */
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	/**
	 *@return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 *@param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 *@return the taskCode
	 */
	public String getTaskCode() {
		return taskCode;
	}

	/**
	 *@param taskCode the taskCode to set
	 */
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	/**
	 *@return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 *@param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 *@return the enterpriseCode
	 */
	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	/**
	 *@param enterpriseCode the enterpriseCode to set
	 */
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
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
	 *@return the batchCode
	 */
	public String getBatchCode() {
		return batchCode;
	}

	/**
	 *@param batchCode the batchCode to set
	 */
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
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
	 *@return the storageEntryId
	 */
	public String getStorageEntryId() {
		return storageEntryId;
	}

	/**
	 *@return the storageExitId
	 */
	public String getStorageExitId() {
		return storageExitId;
	}

	/**
	 *@param storageEntryId the storageEntryId to set
	 */
	public void setStorageEntryId(String storageEntryId) {
		this.storageEntryId = storageEntryId;
	}

	/**
	 *@param storageExitId the storageExitId to set
	 */
	public void setStorageExitId(String storageExitId) {
		this.storageExitId = storageExitId;
	}

	/**
	 *@return the produceTime
	 */
	public Date getProduceTime() {
		return produceTime;
	}

	/**
	 *@param produceTime the produceTime to set
	 */
	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
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
