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
package com.sigmatrix.constants;

/**
 *@ClassName: Constant
 *@Description: 常量类
 *@author youjun
 *@date 2017年6月22日 上午11:38:24
 */
public class Constant {

	/**成功返回数据*/
	public static final int RESPONSE_CODE_SUCCESS = 1000;
	
	/**请求参数无效*/
	public static final int RESPONSE_CODE_PARAM_INVALID = 1001;
	
	/**数据不存在*/
	public static final int RESPONSE_CODE_NO_DATA = 1002;
	
	/**数据已经存在*/
	public static final int RESPONSE_CODE_EXISTED_DATA = 1003;
	
	/**请求参数为空*/
	public static final int RESPONSE_CODE_PARAM_NULL = 1004;
	
	/**数据异常*/
	public static final int RESPONSE_CODE_DATA_EXCEPTION = 1005;
	
	/**约定2001-2999为具体的业务模块需要的特定响应code,此为:出库或入库上传接口时有“码异常”*/
	public static final int RESPONSE_CODE_STORAGR_SUMBIT_CODE_EXCEPTION = 2001;
	
	/**约定2001-2999为具体的业务模块需要的特定响应code,此为:拆补箱/垛上传接口时有“码异常”*/
	public static final int RESPONSE_CODE_PACK_BOX_STOCK_EXCEPTION = 3001;
	
	/**返回系统错误*/
	public static final int RESPONSE_CODE_EXCEPTION = 9999;
	
	/**成功返回的字符串*/
	public static final String SUCCESS_MESSAGE = "success";
	
	/**业务中数值类型为null的取值*/
	public static final int NULL_VALUE = -1;
	
	/**Integer类型主键为null取值为0*/
	public static final int NULL_PRIMARY_KEY_VALUE = 0;
	
	/**基础数据之产品*/
	public static final int BASIC_DATA_PRODUCT = 1;
	
	/**基础数据之经销商*/
	public static final int BASIC_DATA_DEALERS = 2;
	
	/**基础数据之仓库*/
	public static final int BASIC_DATA_WAREHOUSE = 3;
	
	/*public static List<ProductData> productList = new ArrayList<ProductData>();
	
	public static Map<Integer, ProductData> productMap = new HashMap<Integer, ProductData>();
	
	public static List<DealersData> dealersList = new ArrayList<DealersData>();
	
	public static Map<Integer, DealersData> dealersMap = new HashMap<Integer, DealersData>();
	
	public static List<WarehouseData> warehouseList = new ArrayList<WarehouseData>();
	
	public static Map<Integer, WarehouseData> warehouseMap = new HashMap<Integer, WarehouseData>();*/
	/**企业产品map,key为企业code,map为企业的所有产品列表*//*
	public static Map<String, List<ProductData>> entProductMap = new HashMap<String, List<ProductData>>();
	*//**企业经销商map,key为企业code,map为企业的所有经销商列表*//*
	public static Map<String, List<DealersData>> entDealerMap = new HashMap<String, List<DealersData>>();
	*//**企业仓库map,key为企业code,map为企业的所有仓库列表*//*
	public static Map<String, List<WarehouseData>> entWarehouseMap = new HashMap<String, List<WarehouseData>>();*/
	
	/**删除状态,-1-删除,0-停用,1-启用*/
	public static final short STATUS_DELETE = -1;
	
	/**禁用状态,-1-删除,0-停用,1-启用*/
	public static final short STATUS_DISABLE = 0;
	
	/**启用状态,-1-删除,0-停用,1-启用*/
	public static final short STATUS_ENABLE = 1;
	
	/**map中存储的当前手持登录用户id,terminalId*/
	public static final String MAP_KEY_USER_ID = "terminalId";
	
	/**查询码操作:出库查询码*/
	public static final int QR_CODE_OPERATION_TYPE_OUT_STORAGE = 1;
	
	/**查询码操作:入库查询码*/
	public static final int QR_CODE_OPERATION_TYPE_IN_STORAGE = 2;
	
	/**操作状态:1手持新增出库或入库单时扫码*/
	public static final int QR_CODE_OPERATION_STATUS_NEW = 1;
	
	/**操作状态:2手持修改(操作已存在的)出库或入库单时扫码*/
	public static final int QR_CODE_OPERATION_STATUS_UPDATE = 2;
	
	/**库存状态:等待入库*/
	public static final short WAREHOUSE_STOCK_STATUS_WAIT_IN_STORAGE = 2;

	/**库存状态:已入库*/
	public static final short WAREHOUSE_STOCK_STATUS_ALREADY_IN_STORAGE = 3;
	
	/**出库单类型:销售出库单*/
	public static final short OUT_STORAGE_CHANNEL_TYPE_SALES = 1;
	
	/**出库单类型:返工出库单*/
	public static final short OUT_STORAGE_CHANNEL_TYPE_REWORK = 2;
	
	/**出库单类型:调拨出库单*/
	public static final short OUT_STORAGE_CHANNEL_TYPE_TRANSFERS = 3;
	
	/**出库单类型:其他出库单*/
	public static final short OUT_STORAGE_CHANNEL_TYPE_OTHER = 4;
	
	/**出库单未同步*/
	public static final short OUT_STORAGE_IS_SYNC_NO = 0;
	
	/**出库单已同步*/
	public static final short OUT_STORAGE_IS_SYNC_YES = 1;
	
	/**出库单来源,1-终端手持机*/
	public static final short OUT_STORAGE_SOURCE_TERMINAL = 1;
	
	/**出库单来源,2-系统平台*/
	public static final short OUT_STORAGE_SOURCE_SYSTEM = 2;
	
	/**出库单来源,3-第三方ERP*/
	public static final short OUT_STORAGE_SOURCE_THIRD_ERP = 3;
	
	/**入库渠道类型,1-生产入库*/
	public static final short IN_CHANNEL_TYPE_PRO = 1;
	
	/**入库渠道类型,2-销售退货入库*/
	public static final short IN_CHANNEL_TYPE_RET = 2;
	
	/**入库渠道类型,3-其他入库*/
	public static final short IN_CHANNEL_TYPE_OTH = 3;
	
	/**入库渠道类型,4-收货入库*/
	public static final short IN_CHANNEL_TYPE_REC = 4;
	
	
	/**入库方式:1-平台入库*/
	public static final short IN_STORAGE_TYPE_PLAT = 1;
	
	/**入库方式:2-手持入库*/
	public static final short IN_STORAGE_TYPE_PDA  = 2;
	
	/**入库方式:3-混合入库*/
	public static final short IN_STORAGE_TYPE_MIX  = 3;
	
	/**库存状态：待入库*/
	public static final short WH_STOCK_STATUS_WAITING_IN_STORAGE = 2;
	
	/**库存状态：已入库*/
	public static final short WH_STOCK_STATUS_ALREADY_IN_STORAGE = 3;
	
	/**库存详情状态：出库*/
	public static final short WH_STOCK_DETAILS_STATUS_OUT_STORAGE = 2;
	
	/**库存详情状态：在库，默认在库*/
	public static final short WH_STOCK_DETAILS_STATUS_IN_STORAGE = 3;
	
	/**手持终端更新方式:0-不需要升级*/
	public static final Integer TERMINA_UPDATE_NO = 0;
	
	/**手持终端更新方式:1-可选升级*/
	public static final Integer TERMINA_UPDATE_MAY  = 1;
	
	/**手持终端更新方式:2-强制升级*/
	public static final Integer TERMINA_UPDATE_MUST  = 2;
	/**版本号正则   例如1.0.0*/
	public static final String VERSION_RULE   = "\\d+(\\.\\d+){2}";
	
	/**手持用户状态:0-禁用*/
	public static final Integer TERMINA_USER_NO = 0;
	
	/**手持用户状态:-1-删除*/
	public static final Integer TERMINA_USER_DELETE= -1;
	
	/**手持用户状态:1-启用*/
	public static final Integer TERMINA_USER_USE  = 1;
	
	
	/**手持状态:0-禁用*/
	public static final Integer TERMINA_NO = 0;
	
	/**手持状态:-1-删除*/
	public static final Integer TERMINA_DELETE= -1;
	
	/**手持状态:1-启用*/
	public static final Integer TERMINA_USE  = 1;
	
	/**拆补箱下的瓶码数*/
	public static final short PACKAGE_SCAN_LEVEL_1  = 1;
	/**拆补垛下的箱码数及垛*/
	public static final short PACKAGE_SCAN_LEVEL_2  = 2;
	/**补垛查询虚拟垛码 */
	public static final short PACKAGE_SCAN_LEVEL_3  = 3;
	
	/**各模块code组成中无日期组成部分*/
	public static final int DATA_PART_TYPE_NO = 0;
	
	/**各模块code组成中日期组成部分为:YYYYMMDD*/
	public static final int DATA_PART_TYPE_YYYYMMMDD = 1;
	
	/**各模块code组成中日期组成部分为:YYMMDD*/
	public static final int DATA_PART_TYPE_YYMMMDD = 2;
	
	/**多级码表中子码的分隔符*/
	public static final String CHILD_CODE_SEPARATOR = ",";
	
	/**查询的二维码的类型:1、标准码(出入库上传接口中代表加密后的码)*/
	public static final int CODE_TYPE_STANDARD = 1;
	
	/**查询的二维码的类型:2、友好码*/
	public static final int CODE_TYPE_FRIENDLY = 2;
	
	/**查询的二维码的类型:3、序列号*/
	public static final int CODE_TYPE_SEQNUM = 3;
	
	/**查询的二维码的类型:4、密文码*/
	public static final int CODE_TYPE_CIPHER = 4;
	
	/**1-拆箱*/
	public static final short PACK_BOX_SPLIT = 1;
	
	/**2-补箱*/
	public static final short PACK_BOX_FILL = 2;
	
	/**1、拆垛*/
	public static final short PACK_STACK_SPLIT = 1;
	
	/**2、补垛*/
	public static final short PACK_STACK_FILL = 2;
	
	/**生成的垛码的前缀*/
	public static final String CODE_CATEGORY_PREFIX_STACK = "D";
	
	/**生成的层码的前缀*/
	public static final String CODE_CATEGORY_PREFIX_LAYER = "C";
	
	/**当字符串为该值时表明需要特殊处理*/
	public static final String SPECIAL_HANDLING_STRING = "SPECIAL_HANDLING_XXXXXX";
	
	/**码流转记录表基础表名:CodeCirculation*/
	public static final String BASIC_CODECIRCULATION_DOCUMENT_NAME = "CodeCirculation";
	
	public static int LOGBACK_SEQNUM = 0;
	
	public static String LOGBACK_HOSTNAME = "";
	
	/**库存不存在*/
	public static final String STOCK_NOT_EXIST = "stock_no_exist";

	/**异步任务状态:未处理*/
	public static final short SYNC_TASK_STATUS_NO_HANDLE = 0;
	
	/**异步任务状态:处理中*/
	public static final short SYNC_TASK_STATUS_HANDLE_ING = 1;
	
	/**异步任务状态:已处理*/
	public static final short SYNC_TASK_STATUS_HANDLE_FINISH = 2;

	/**异步任务处理:成功*/
	public static final short SYNC_TASK_SUCCESS = 1;
	
	/**异步任务处理:失败*/
	public static final short SYNC_TASK_FAIL = 0;

	/**圣戈班产品包装单位级别*/
	public static final int SGB_PRODUCT_PACK_LEVEL = 2;
	
	/**出库单操作状态:0待处理*/
	public static final short OPERATION_STATUS_WAIT = 0;
	/**出库单操作状态:1处理中*/
	public static final short OPERATION_STATUS_ING = 1;
	/**出库单操作状态:2处理完毕*/
	public static final short OPERATION_STATUS_FINISH = 2;

	/**操作类型:1新增*/
	public static final Short OPERATION_TYPE_INSERT = 1;

	/**操作类型:2修改*/
	public static final Short OPERATION_TYPE_UPDATE = 2;
	
	/**操作类型:3关闭*/
	public static final Short OPERATION_TYPE_CLOSE = 3;

	/**不予处理的终端用户ID*/
	public static final Integer TERMINAL_USER_ID_NOT_HANDLE = -1;
	
	/**不予处理的终端ID*/
	public static final Integer TERMINAL_ID_NOT_HANDLE = -1;

	/**sql条件中的IN中数量过大,以该数量进行拆分*/
	public static final int SPLIT_IN_COUNT = 100;

	/**企业对经销商的销售出库,经销商自动收货入库,单号组成为:单号前缀+出库单号*/
	public static final String AUTO_DEALER_IN_STORAGE_PREFIX = "OSI";

	/**激活节点:生产即激活*/
	public static final Integer ACTIVATE_NODE_TYPE_PRODUCT = 1;
	
	/**激活节点:销售出库后激活*/
	public static final Integer ACTIVATE_NODE_TYPE_OUTSTORAGE = 2;

	/**一级码的级别*/
	public static final Integer FIRST_LEVEL = 1;

	/**未设置参数配置默认返回值*/
	public static final int PARAM_CONFIG_NO_SET = -1;

	/**参数配置默认产线,1-代表生产供应*/
	public static final short PARAM_CONFIG_DEFAULT_PRODUCT_LINE = 1;

	/**销售出库是否满包装*/
	public static final String PARAM_CONFIG_OUTINSTORAGE_IS_FULL_PACK = "OutInStorage_IS_FULL_PACK";
	
	/**mongo的码查询in的值集合最大数*/
	public static int CODE_IN_ARRAY_MAX_COUNT = 1000;

	/**新疆伊力特的企业code*/
	public static final String ENT_CODE_XJYTL = "XJYTL";
	/**湖南雪花的企业code*/
	public static final String ENT_CODE_HNXH = "hrxh0009";
	/**海升的企业code*/
	public static final String ENT_CODE_HS = "QGTYSP";

	/**文件标识*/
	public static final String FILE_IDENTIFIER = "file:/";

	/**工控机上传记录是否被下载：1是*/
	public static final Short PRODUCTION_RECORD_IS_DOWN_YES = 1;
	/**工控机上传记录是否被下载：0否*/
	public static final Short PRODUCTION_RECORD_IS_DOWN_NO = 0;
}
