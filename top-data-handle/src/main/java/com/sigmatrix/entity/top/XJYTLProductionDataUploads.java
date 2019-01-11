package com.sigmatrix.entity.top;

import java.util.Date;
import javax.persistence.*;

@Table(name = "_xjytl_production_data_uploads")
public class XJYTLProductionDataUploads {
    @Id
    @Column(name = "_id")
    private Integer id;

    @Column(name = "_zip_url")
    private String zipUrl;

    /**
     * 码包是否下载，0否，1是，默认为null
     */
    @Column(name = "_is_download")
    private Short isDownload;

    @Column(name = "_pack_success_num")
    private Integer packSuccessNum;

    @Column(name = "_pack_fail_num")
    private Integer packFailNum;

    @Column(name = "_pack_total_num")
    private Integer packTotalNum;

    @Column(name = "_create_time")
    private Date createTime;

    @Column(name = "_status")
    private Short status;

    @Column(name = "_pack_level")
    private Short packLevel;

    @Column(name = "_process_detail")
    private String processDetail;

    @Column(name = "_product_line_id")
    private Integer productLineId;

    @Column(name = "_product_id")
    private Integer productId;

    @Column(name = "_batch_code")
    private String batchCode;

    @Column(name = "_success_file_uri")
    private String successFileUri;

    @Column(name = "_fail_file_uri")
    private String failFileUri;

    @Column(name = "_server_id")
    private Integer serverId;

    @Column(name = "_warehouse_id")
    private Integer warehouseId;

    @Column(name = "_serial_number")
    private String serialNumber;

    @Column(name = "_produce_time")
    private Date produceTime;

    @Column(name = "_team_id")
    private Integer teamId;

    @Column(name = "_production_task_id")
    private Integer productionTaskId;

    @Column(name = "_ipc_code")
    private String ipcCode;

    @Column(name = "_key")
    private String key;

    @Column(name = "_failure_times")
    private Integer failureTimes;

    @Column(name = "_md5")
    private String md5;

    /**
     * @return _id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return _zip_url
     */
    public String getZipUrl() {
        return zipUrl;
    }

    /**
     * @param zipUrl
     */
    public void setZipUrl(String zipUrl) {
        this.zipUrl = zipUrl == null ? null : zipUrl.trim();
    }

    /**
     * 获取码包是否下载，0否，1是，默认为null
     *
     * @return _is_download - 码包是否下载，0否，1是，默认为null
     */
    public Short getIsDownload() {
        return isDownload;
    }

    /**
     * 设置码包是否下载，0否，1是，默认为null
     *
     * @param isDownload 码包是否下载，0否，1是，默认为null
     */
    public void setIsDownload(Short isDownload) {
        this.isDownload = isDownload;
    }

    /**
     * @return _pack_success_num
     */
    public Integer getPackSuccessNum() {
        return packSuccessNum;
    }

    /**
     * @param packSuccessNum
     */
    public void setPackSuccessNum(Integer packSuccessNum) {
        this.packSuccessNum = packSuccessNum;
    }

    /**
     * @return _pack_fail_num
     */
    public Integer getPackFailNum() {
        return packFailNum;
    }

    /**
     * @param packFailNum
     */
    public void setPackFailNum(Integer packFailNum) {
        this.packFailNum = packFailNum;
    }

    /**
     * @return _pack_total_num
     */
    public Integer getPackTotalNum() {
        return packTotalNum;
    }

    /**
     * @param packTotalNum
     */
    public void setPackTotalNum(Integer packTotalNum) {
        this.packTotalNum = packTotalNum;
    }

    /**
     * @return _create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return _status
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * @return _pack_level
     */
    public Short getPackLevel() {
        return packLevel;
    }

    /**
     * @param packLevel
     */
    public void setPackLevel(Short packLevel) {
        this.packLevel = packLevel;
    }

    /**
     * @return _process_detail
     */
    public String getProcessDetail() {
        return processDetail;
    }

    /**
     * @param processDetail
     */
    public void setProcessDetail(String processDetail) {
        this.processDetail = processDetail == null ? null : processDetail.trim();
    }

    /**
     * @return _product_line_id
     */
    public Integer getProductLineId() {
        return productLineId;
    }

    /**
     * @param productLineId
     */
    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }

    /**
     * @return _product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return _batch_code
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * @param batchCode
     */
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode == null ? null : batchCode.trim();
    }

    /**
     * @return _success_file_uri
     */
    public String getSuccessFileUri() {
        return successFileUri;
    }

    /**
     * @param successFileUri
     */
    public void setSuccessFileUri(String successFileUri) {
        this.successFileUri = successFileUri == null ? null : successFileUri.trim();
    }

    /**
     * @return _fail_file_uri
     */
    public String getFailFileUri() {
        return failFileUri;
    }

    /**
     * @param failFileUri
     */
    public void setFailFileUri(String failFileUri) {
        this.failFileUri = failFileUri == null ? null : failFileUri.trim();
    }

    /**
     * @return _server_id
     */
    public Integer getServerId() {
        return serverId;
    }

    /**
     * @param serverId
     */
    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    /**
     * @return _warehouse_id
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * @param warehouseId
     */
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * @return _serial_number
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    /**
     * @return _produce_time
     */
    public Date getProduceTime() {
        return produceTime;
    }

    /**
     * @param produceTime
     */
    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    /**
     * @return _team_id
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * @param teamId
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * @return _production_task_id
     */
    public Integer getProductionTaskId() {
        return productionTaskId;
    }

    /**
     * @param productionTaskId
     */
    public void setProductionTaskId(Integer productionTaskId) {
        this.productionTaskId = productionTaskId;
    }

    /**
     * @return _ipc_code
     */
    public String getIpcCode() {
        return ipcCode;
    }

    /**
     * @param ipcCode
     */
    public void setIpcCode(String ipcCode) {
        this.ipcCode = ipcCode == null ? null : ipcCode.trim();
    }

    /**
     * @return _key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     */
    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    /**
     * @return _failure_times
     */
    public Integer getFailureTimes() {
        return failureTimes;
    }

    /**
     * @param failureTimes
     */
    public void setFailureTimes(Integer failureTimes) {
        this.failureTimes = failureTimes;
    }

    /**
     * @return _md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5
     */
    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }
}