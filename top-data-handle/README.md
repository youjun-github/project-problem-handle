一、top-data-handle项目说明
用于TOP生产供应(微软云版本)问题处理的项目,可处理如下问题
1、
2、
3、
二、目录说明
1、src/main/java
	com.sigmatrix.utils
		FileUtils  
			a)getConTextPath 可以获取当前项目的路径
		EncryptUtil  加解密工具类
			a)
			b)decryptFile(String srcFilePath, String keys, String tagFilePath)
2、src/main/resources
1)ipc 工控机文件
	处理类:
	original 原始文件,工控机采集的上下级关系码,如这里的:149_1_2018-10-11_104850673.zip
		a、文件命名:
			
		b、内容格式为:下级码,上级码,0,时间戳,1
			HTTP://WWW.AA/Q/R9TE66*7$85J311ZOV*-*U*B,HTTP://WWW.BB/Q/2H7W..5TZQARW7VBDRQ1:XI-,0,20181011104602,1
	encrypt	加密后的文件,工控机在调用接口传递的过程中,将原始文件进行加密(依托于工控机密钥)后进行上传
	
	decrypt	解密后的文件,成品数据处理程序在拿到Blob上的文件后,解密(依托于工控机密钥后进行文件处理
2)jgj 处理top上的酒鬼酒这个定制企业的数据,酒鬼酒的码来自于第三方:天鉴,发货也由第三方进行发货,但是通路行销使用的是top,也就存在了top生产供应
作为桥梁,需要接收两类码文件(2-6等格式的code文件、mapping文件)
	处理类:
	code 2-6等格式的码文件
		source:
		target:
	处理类:com.sigmatrix.service.HandleNotInStorageMapping
	mapping
		source:
		target:	
三、
