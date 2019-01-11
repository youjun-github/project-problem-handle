package com.sigmatrix.core;

import tk.mybatis.mapper.common.Mapper;

/**
 * BaseMapper实现通用的增删改查方法<br>
 * 所有的Mapper都要继承该接口,
 * 并添加@{@link org.apache.ibatis.annotations.Mapper}注解
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>{

}
