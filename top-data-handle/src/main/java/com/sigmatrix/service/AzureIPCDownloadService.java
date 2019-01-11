package com.sigmatrix.service;

/**
 * 从Azure Blob下载工控机文件
 *
 * @author youjun
 * @create 2019-01-10 10:49
 */
public interface AzureIPCDownloadService {

    /**
     * 补充下载工控机文件到本地存储目录,存在则跳过,不存在download
     * @param localStorageRootDirectory 本地工控机文件存放根目录,eg:E:/ipc/,最后形成:
     *                              E:/ipc/mfg-ipc-service/XJYTL/IPC00012/2018/11/19/XJYTL_IPC00012_20181119115801371.zip
     */
    public void supplementDownloadIPCFile(String localStorageRootDirectory);
}
