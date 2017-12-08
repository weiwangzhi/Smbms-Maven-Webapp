package com.wwz.service;

import java.util.List;

import com.wwz.entity.SmbmsProvider;

public interface SmbmsProviderService {
	/**
	 * 按条件分页查询
	 * 
	 * @param proCode
	 * @param proName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SmbmsProvider> getProviderByPage(String proCode, String proName, int pageIndex, int pageSize);

	/**
	 * 按条件查询总记录数
	 * 
	 * @param proCode
	 * @param proName
	 * @return
	 */
	public int providerCount(String proCode, String proName);

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
