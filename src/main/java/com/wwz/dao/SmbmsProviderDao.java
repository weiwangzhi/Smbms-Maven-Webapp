package com.wwz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wwz.entity.SmbmsProvider;

public interface SmbmsProviderDao {
	/**
	 * 按条件分页查询
	 * 
	 * @param proCode
	 * @param proName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SmbmsProvider> getProviderByPage(@Param("proCode") String proCode, @Param("proName") String proName,
			@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

	/**
	 * 按条件查询总记录数
	 * 
	 * @param proCode
	 * @param proName
	 * @return
	 */
	public int providerCount(@Param("proCode") String proCode, @Param("proName") String proName);

	/**
	 * 根据id查看供应商
	 * 
	 * @param id
	 * @return
	 */
	public SmbmsProvider getProviderById(Long id);

	/**
	 * 添加供应商
	 * 
	 * @param smbmsProvider
	 * @return
	 */
	public int addProvider(SmbmsProvider smbmsProvider);

	/**
	 * 修改供应商信息
	 * 
	 * @param id
	 * @return
	 */
	public int updateProviderById(SmbmsProvider id);

	/**
	 * 根据id删除供应商信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteProviderById(Long id);
	
	/**
	 * 所有
	 * @return
	 */
	public List<SmbmsProvider> getAllProvider();
}
