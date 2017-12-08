package com.wwz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wwz.entity.SmbmsProvider;

public interface SmbmsProviderDao {
	/**
	 * ��������ҳ��ѯ
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
	 * ��������ѯ�ܼ�¼��
	 * 
	 * @param proCode
	 * @param proName
	 * @return
	 */
	public int providerCount(@Param("proCode") String proCode, @Param("proName") String proName);

	/**
	 * ����id�鿴��Ӧ��
	 * 
	 * @param id
	 * @return
	 */
	public SmbmsProvider getProviderById(Long id);

	/**
	 * ��ӹ�Ӧ��
	 * 
	 * @param smbmsProvider
	 * @return
	 */
	public int addProvider(SmbmsProvider smbmsProvider);

	/**
	 * �޸Ĺ�Ӧ����Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public int updateProviderById(SmbmsProvider id);

	/**
	 * ����idɾ����Ӧ����Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public int deleteProviderById(Long id);
	
	/**
	 * ����
	 * @return
	 */
	public List<SmbmsProvider> getAllProvider();
}
