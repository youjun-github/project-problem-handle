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

import java.io.File;
import java.net.URI;

import com.sigmatrix.constants.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sigmatrix.sc.common.storage.Account;
import com.sigmatrix.sc.common.storage.EndPoint;
import com.sigmatrix.sc.common.storage.EndPointProtocol;
import com.sigmatrix.sc.common.storage.StorageFactory;
import com.sigmatrix.sc.common.storage.azure.BlobContainer;
import com.sigmatrix.sc.common.storage.azure.BlobStorage;

/**
 *@ClassName: AzureUtils
 *@Description: 微软云工具类
 *@author liuzelei
 *@date 2017年7月23日 下午12:14:10
 */
@Component
public class AzureUtils {
	
	@Value("${azure.storage.account-name}")
	public String accountName;
	
	@Value("${azure.storage.account-key}")
	public String accountKey;
	
	@Value("${azure.storage.file-shares}")
	public String fileShares;
	
	@Value("${azure.storage.endpoint.suffix}")
	public String suffix;
	
	/**
	 *@Title: createBlobStorage
	 *@Description: 创建blob
	 *@return BlobStorage
	 *@author liuzelei
	 *@date 2017年7月24日 上午10:36:53
	 *@return
	 */
	public BlobStorage createBlobStorage(){
		final BlobStorage blobStorage = createBlobStorage(accountName,accountKey,suffix);
		return blobStorage;
	}
	
	/**
	 *@Title: createBlobStorage
	 *@Description: 创建blob
	 *@return BlobStorage
	 *@author liuzelei
	 *@date 2017年7月24日 上午10:57:54
	 *@param accountName
	 *@param accountKey
	 *@return
	 */
	public BlobStorage createBlobStorage(String accountName,String accountKey,String suffix){
		final Account account = new Account(accountName,accountKey);
		EndPoint endPoint = new EndPoint();
		endPoint.setSuffix(suffix);
		endPoint.setEndpointProtocol(EndPointProtocol.HTTPS);
		final BlobStorage blobStorage = StorageFactory.createAzureBlobStorage(account, endPoint);
		return blobStorage;
	}
	
	/**
	 *@Title: createBlobContainer
	 *@Description: 创建指定的容器blob
	 *@return BlobContainer
	 *@author liuzelei
	 *@date 2017年7月24日 上午10:38:44
	 *@param containerName
	 *@return
	 */
	public BlobContainer createBlobContainer(String containerName){
		final BlobStorage blobStorage = createBlobStorage();
		BlobContainer container = blobStorage.createBlobContainer(containerName);
		return container;
	}
	
	/**
	 *@Title: uploadBlobToAzure
	 *@Description: 上传数据导指定文件
	 *@return String
	 *@author liuzelei
	 *@date 2017年7月24日 上午10:40:56
	 *@param fileName 要保存到云端的文件名
	 *@param localFilePath 要上传文件的路径
	 *@param containerName 容器名
	 *@return
	 */
	public String uploadBlobToAzure(final String fileName,final String localFilePath,final String containerName){
		final BlobContainer container = createBlobContainer(containerName);
		container.saveBlob(fileName, URI.create(localFilePath));
		return fileName;
	}
	
	/**
	 *@Title: uploadBlobToAzure
	 *@Description: 上传数据
	 *@return String
	 *@author liuzelei
	 *@date 2017年7月26日 上午11:50:00
	 *@param fileName
	 *@param localFilePath
	 *@return
	 */
	public String uploadBlobToAzure(final String fileName,final String localFilePath){
		final BlobContainer container = createBlobContainer(fileShares);
		container.saveBlob(fileName, URI.create(Constant.FILE_IDENTIFIER+localFilePath));
		return fileName;
	}
	/**
	 *@Title: uploadBlobToAzure
	 *@Description: 
	 *@return String
	 *@author liuzelei
	 *@date 2017年7月27日 上午10:02:34
	 *@param fileName
	 *@param uri
	 *@return 返回容量名
	 */
	public String uploadBlobToAzure(final String fileName,final URI uri){
		final BlobContainer container = createBlobContainer(fileShares);
		container.saveBlob(fileName, uri);
		return fileShares;
	}
	
	public String uploadBlobToAzure(final String fileName,final URI uri,String path){
		final BlobContainer container = createBlobContainer(fileShares+path);
		container.saveBlob(fileName, uri);
		return fileName;
	}
	/**
	 *@Title: uploadBlobToAzure
	 *@Description: 
	 *@return String
	 *@author liuzelei
	 *@date 2017年7月27日 上午10:09:24
	 *@param fileName
	 *@param file
	 */
	public String uploadBlobToAzure(final String fileName,final File file){
		BlobContainer container = null;
		if(null != file && file.exists()){
			container = createBlobContainer(fileShares);
			container.saveBlob(fileName, file.toURI());
		}
		return fileName;
	}
	/**
	 *@Title: downloadFileToAzure
	 *@Description: 下载云端的文件
	 *@return String 
	 *@author liuzelei
	 *@date 2017年7月23日 下午1:55:19
	 *@param containerName 容器名
	 *@param localFilePath 要保存的路径
	 *@param fileName 要下载到云端的文件名
	 *@return
	 */
	public String downloadBlobFromAzure(String containerName,String localFilePath,String fileName){
		final BlobContainer container = createBlobContainer(containerName);
		container.downloadBlobItem(fileName,localFilePath);
		return localFilePath + fileName;
	}
	
	/**
	 *@Title: downloadBlobFromAzure
	 *@Description: 从微软云下载文件 返回下载的文件路径
	 *@return String
	 *@date 2017年8月20日 下午10:27:49
	 *@param localFilePath 本地目录
	 *@param fileName 需要下载的文件名称
	 */
	public String downloadBlobFromAzure1(final String localFilePath,final String fileName){
		final BlobContainer container = createBlobContainer(fileShares);
		container.downloadBlobItem(fileName,localFilePath);
		return localFilePath + fileName;
	}
	
	/**
	 *@Title: downloadBlobFromAzure
	 *@Description: 从微软云下载文件 返回下载的文件路径
	 *@return String
	 *@date 2017年8月20日 下午10:27:49
	 *@param localFilePath 本地文件名称全路径
	 *@param fileName 需要下载的文件名称
	 */
	public void downloadBlobFromAzure(final String localFilePath,final String fileName){
		final BlobContainer container = createBlobContainer(fileShares);
		container.downloadBlobItem(fileName,localFilePath);
	}
	
	/**
	 *@Title: deleteBlobItem
	 *@Description: 删除blob
	 *@return boolean
	 *@author liuzelei
	 *@date 2017年7月24日 上午10:55:34
	 *@param containerName
	 *@param fileName
	 *@return
	 */
	public boolean deleteBlobItem(String containerName, String fileName){
		final BlobContainer container = createBlobContainer(containerName);
		return container.deleteBlobItem(fileName);
	}
	
	/**
	 *@return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 *@param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 *@return the accountKey
	 */
	public String getAccountKey() {
		return accountKey;
	}

	/**
	 *@param accountKey the accountKey to set
	 */
	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
	
	/**
	 *@return the fileShares
	 */
	public String getFileShares() {
		return fileShares;
	}

	/**
	 *@param fileShares the fileShares to set
	 */
	public void setFileShares(String fileShares) {
		this.fileShares = fileShares;
	}

	/**
	 *@return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 *@param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	
	public static void main(String[] args) {
		AzureUtils au = new AzureUtils();
		BlobStorage blob = au.createBlobStorage(
				"topdev2",
				"GHZurSVrtlHxedwYMyQ3XAvfLjz1IiEYZRCBC5+b4AsfB0RfoKGwvsuwKCmmktACIozqs632NjbH6dNxuBTYfA==",
				"core.chinacloudapi.cn");
		BlobContainer container = blob.createBlobContainer("gyl-storage-dev");
		container.downloadBlobItem("IPC00028_20170821182339938_ux08laet.zip","D:/a/a.zip");
//		container.setPublic();
//		File file = new File("C:\\Users\\sigmatrix\\git\\sigmatrix-sc-production-inter\\11\\201707\\11-20170727093722-19oaka62.zip");
//		URI uri = file.toURI();
//		container.saveBlob("11-20170727093722-19oaka62.zip", uri);
	}
}
