package com.sigmatrix.service.impl;

import com.microsoft.windowsazure.exception.ServiceException;
import com.netflix.discovery.converters.Auto;
import com.sigmatrix.constants.Constant;
import com.sigmatrix.entity.top.XJYTLProductionDataUploads;
import com.sigmatrix.exception.SigmatrixServiceException;
import com.sigmatrix.service.AzureIPCDownloadService;
import com.sigmatrix.service.XJYTLProductionDataUploadsService;
import com.sigmatrix.utils.AzureUtils;
import com.sigmatrix.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author youjun
 * @create 2019-01-11 10:27
 */
@Service(value = "azureIPCDownloadService")
public class AzureIPCDownloadServiceImpl implements AzureIPCDownloadService {
    private static final Logger logger = LoggerFactory.getLogger(AzureIPCDownloadService.class);

    @Autowired
    private AzureUtils azureUtils;
    
    @Autowired
    private XJYTLProductionDataUploadsService xJYTLProductionDataUploadsService;

    @Override
    public void supplementDownloadIPCFile(String localStorageRootDirectory) {

        // 获取表中待下载的工控机文件列表
        XJYTLProductionDataUploads record = new XJYTLProductionDataUploads();
        record.setIsDownload(Constant.PRODUCTION_RECORD_IS_DOWN_NO);
        List<XJYTLProductionDataUploads> waitDownloadProductionDataList = xJYTLProductionDataUploadsService.select(record);
        if(CollectionUtils.isEmpty(waitDownloadProductionDataList)) {
           logger.info("无需下载");
           return;
        }
        int downloadFailCount = 0; // 下载失败数
        for (XJYTLProductionDataUploads item :
                waitDownloadProductionDataList) {
            // localStorageDirectory,eg:E:/ipc/
            String zipUrl = item.getZipUrl(); // eg:mfg-ipc-service/XJYTL/IPC00012/2018/11/19/XJYTL_IPC00012_20181119115801371.zip
            // 下载工控机文件
            try {
                downloadIPCFile(localStorageRootDirectory, zipUrl);
                XJYTLProductionDataUploads updateRecord = new XJYTLProductionDataUploads();
                updateRecord.setIsDownload(Constant.PRODUCTION_RECORD_IS_DOWN_YES);
                updateRecord.setId(item.getId()); // 主键
                xJYTLProductionDataUploadsService.updateSelective(updateRecord);
            } catch (SigmatrixServiceException e) {
                downloadFailCount++;
                logger.error("当前下载失败,id:"+item.getId()+",zipUrl:"+item.getZipUrl(), e);
            }
        }
        logger.info("本次待下载总数:{},下载失败数:{}", waitDownloadProductionDataList.size(), downloadFailCount);
    }
    
    /**
     * @param localStorageRootDirectory E:/ipc/
     * @param zipUrl  形如:mfg-ipc-service/XJYTL/IPC00012/2018/12/20/XJYTL_IPC00012_20181220143416987.zip
     * @throws SigmatrixServiceException
     */
    private void downloadIPCFile(String localStorageRootDirectory, String zipUrl) throws SigmatrixServiceException {

        // 目标目录名称,eg:E:/ipc/+mfg-ipc-service/XJYTL/IPC00012/2018/12/20/
        String destDirName = localStorageRootDirectory + zipUrl.substring(0, zipUrl.lastIndexOf("/")) + "/";
        logger.info("目标存放目录:{}", destDirName);
        if (!FileUtils.createDir("", destDirName)) {//创建本地存放文件目录
            throw new SigmatrixServiceException("目录创建失败");
        }
        // eg:E:/ipc/+mfg-ipc-service/XJYTL/IPC00012/2018/12/20/XJYTL_IPC00012_20181220143416987.zip
        String sourceFilePath = destDirName + zipUrl.substring(zipUrl.lastIndexOf("/") + 1);//微软云下载下来的文件名称
        logger.info("目标文件路径:{}", sourceFilePath);
        azureUtils.downloadBlobFromAzure(sourceFilePath, zipUrl);//从微软云下载文件 加密的
    }

    public static void main(String[] args) {

        String eseCode = "XJYTL";
        String zipUrl = "mfg-ipc-service/XJYTL/IPC00012/2018/11/19/XJYTL_IPC00012_20181119115801371.zip";
        try {
            AzureIPCDownloadServiceImpl azureIPCDownloadService = new AzureIPCDownloadServiceImpl();
            azureIPCDownloadService.downloadIPCFile(eseCode, zipUrl);
        } catch (SigmatrixServiceException e) {
            logger.error("从Blob下载文件出错", e);
        }
    }
}
