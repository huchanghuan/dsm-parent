package com.iwancool.dsm.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 基础ORM DAO接口定义
 * 
 * @author chenshilong
 * @version 1.0.0
 * @2014-3-11 上午12:26:18
 */
public interface IGeneralORMDao<T, PK extends Serializable> {

	/**
	 * 保存一个对象数据
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:34:54
	 * @since
	 * @param entity
	 * @return
	 */
	public Serializable save(T entity);

	/**
	 * 保存更新一个数据对象
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:35:19
	 * @since
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * 更新一个数据对象
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:35:29
	 * @since
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 删除一个数据对象
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:35:37
	 * @since
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 通过主键加载数据对象
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:35:46
	 * @since
	 * @param pk
	 * @return
	 */
	public T getByPrimaryKey(PK pk);

	/**
	 * 通过主键删除主键
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:36:00
	 * @since
	 * @param pk
	 */
	public void deleteByPrimaryKey(PK pk);

	/**
	 * 通过HQL语句获取一个对象
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:37:15
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	public T get(String hql);

	/**
	 * 通过HQL语句获取一个对象
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:37:15
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	public T get(String hql, Map<String, Object> params);

	/**
	 * 获得对象列表
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:37:59
	 * @since
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 获得对象列表
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:38:18
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	//public List<T> find(String hql, Map<String, Object> params);

	public List<T> find(String hql, Map<String, Object> params);
	
	
	/**
	 * 分页获取对象列表
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:38:37
	 * @since
	 * @param hql
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, int page, int rows);

	/**
	 * 获得分页后的对象列表
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:39:23
	 * @since
	 * @param hql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	/**
	 * 统计数目
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:39:43
	 * @since
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * 统计数目
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:39:59
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 执行一条HQL语句
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:40:14
	 * @since
	 * @param hql
	 * @return
	 */
	public int executeHql(String hql);

	/**
	 * 执行一条HQL语句
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:40:33
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	public int executeHql(String hql, Map<String, Object> params);

	/**
	 * 获得结果集
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:40:56
	 * @since
	 * @param sql
	 * @return
	 */
	public List<Object[]> findBySql(String sql);

	/**
	 * 获得结果集
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:41:05
	 * @since
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Object[]> findBySql(String sql, int page, int rows);

	/**
	 * 获得结果集
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:41:13
	 * @since
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Object[]> findBySql(String sql, Map<String, Object> params);

	/**
	 * 获得结果集
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:41:20
	 * @since
	 * @param sql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows);

	/**
	 * 执行SQL语句
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:41:32
	 * @since
	 * @param sql
	 * @return
	 */
	public int executeSql(String sql);

	/**
	 * 执行SQL语句
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:41:38
	 * @since
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeSql(String sql, Map<String, Object> params);

	/**
	 * 统计
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:41:45
	 * @since
	 * @param sql
	 * @return
	 */
	public BigInteger countBySql(String sql);

	/**
	 * 统计
	 * 
	 * @author chenshilong
	 * @create 2014-3-11 上午12:41:55
	 * @since
	 * @param sql
	 * @param params
	 * @return
	 */
	public BigInteger countBySql(String sql, Map<String, Object> params);

	/**
	 * 根据sql语句获得一个对象
	 * @param hql
	 * @return
	 */
	public Object getObject(String sql, Map<String, Object> params);
	
	/**
	 * 更具偏移量查询数据列表
	 * 
	 * @author   long
	 * @param hql
	 * @param params
	 * @param offset
	 * @param limit
	 * @return
	 * @return List<T>
	 * @exception
	 * @author   long
	 * @since 1.0.0
	 * @date 2015年8月5日 下午12:09:27
	 */
	public List<T> findByOffset(String hql, Map<String, Object> params, int offset, int limit);

	

}
