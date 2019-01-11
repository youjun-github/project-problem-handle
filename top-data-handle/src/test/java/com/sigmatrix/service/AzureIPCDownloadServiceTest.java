package com.sigmatrix.service;

import com.sigmatrix.TopDataHandleApplication;
import com.sigmatrix.exception.SigmatrixServiceException;
import com.sigmatrix.utils.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author youjun
 * @create 2019-01-10 11:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TopDataHandleApplication.class)
public class AzureIPCDownloadServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(AzureIPCDownloadServiceTest.class);

    @Autowired
    private AzureIPCDownloadService azureIPCDownloadService;

    /**
     * 填补下载工控机文件
     */
    @Test
    public void downloadIPCFileTest() {
        try {
//            String localStorageRootDirector = "E:/youjun/工作区/TOP/生产环境工控机文件/";
            String localStorageRootDirector = "";
            String notarizeEnv = "stable"; // 环境
            if ("stable".equals(notarizeEnv)) {
                localStorageRootDirector = "E:/youjun/工作区/TOP/stable环境工控机文件/";
            } else if ("prod".equals(notarizeEnv)) {
                localStorageRootDirector = "E:/youjun/工作区/TOP/生产环境工控机文件/";
            } else {
                System.out.println("未知环境,退出!");
                return;
            }
            System.out.println("当前的目标根目录是:" + localStorageRootDirector);
            azureIPCDownloadService.supplementDownloadIPCFile(localStorageRootDirector);
        } catch (Exception e) {
            logger.error("下载Blob工控机文件失败", e);
        }
    }

    /**
     * main方法也可以执行达到目的
     * @param args
     */
    public static void main(String[] args) {
        try{
            String localStorageRootDirector = "";
            System.err.println("请确认当前环境stable/prod");
            InputStreamReader is = new InputStreamReader(System.in); //new构造InputStreamReader对象
            BufferedReader br = new BufferedReader(is); //拿构造的方法传到BufferedReader中，此时获取到的就是整个缓存流
            String notarizeEnv = br.readLine();
            if ("stable".equals(notarizeEnv)) {
                localStorageRootDirector = "E:/youjun/工作区/TOP/stable环境工控机文件/";
            } else if ("prod".equals(notarizeEnv)) {
                localStorageRootDirector = "E:/youjun/工作区/TOP/生产环境工控机文件/";
            } else {
                System.err.println("未知环境,退出!");
                return;
            }
            System.err.println("确认的当前的目标根目录是:" + localStorageRootDirector);
            ApplicationContext ctx = SpringApplication.run(TopDataHandleApplication.class, args);
            AzureIPCDownloadService service = (AzureIPCDownloadService) ctx.getBean("azureIPCDownloadService");
            service.supplementDownloadIPCFile(localStorageRootDirector);
        }catch(Exception e){
            logger.error("main fail", e);
        }
    }
}
