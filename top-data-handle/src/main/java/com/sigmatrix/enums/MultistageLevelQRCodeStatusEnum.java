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
package com.sigmatrix.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *@ClassName: MultistageLevelQRCodeStatusEnum
 *@Description: 多级码状态
 *@author youjun
 *@date 2017年8月12日 上午12:53:34
 */
public enum MultistageLevelQRCodeStatusEnum {

	/**生产*/
	PRODUCTION(0, "生产"),
	
	/**入库*/
	IN_STORAGE(1, "入库"),
	
	/**销售出库*/
	SALES_OUT_STORAGE(2, "销售出库"),
	
	/**返工出库*/
	REWORK_OUT_STORAGE(3, "返工出库"),
	
	/**调拨出库*/
	TRANSFERS_OUT_STORAGE(4, "调拨出库"),
	
	/**其他出库*/
	OTHER_OUT_STORAGE(5, "其他出库");
	
	private Integer id;
	
	private String name;

	/**
	 *@Title: MultistageLevelQRCodeStatusEnum
	 *@Description: TODO
	 *@param name
	 *@param ordinal
	 */
	private MultistageLevelQRCodeStatusEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 *@Title: getAll
	 *@Description: TODO()
	 *@return Map<Integer,String>
	 *@author youjun
	 *@date 2017年8月12日 上午1:02:08
	 *@return
	 */
	public static Map<Integer, String> getAll() {
		final Map<Integer, String> statusMap = new HashMap<Integer, String>();
		for(final MultistageLevelQRCodeStatusEnum item:MultistageLevelQRCodeStatusEnum.values()) {
			statusMap.put(item.getId(), item.getName());
		}
		return statusMap;
	}

	/**
	 *@return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 *@return the name
	 */
	public String getName() {
		return name;
	}
}
