package com.wwz.service;

import java.util.List;

import com.wwz.entity.SmbmsProvider;

public interface SmbmsProviderService {
	/**
	 * ��������ҳ��ѯ
	 * 
	 * @param proCode
	 * @param proName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SmbmsProvider> getProviderByPage(String proCode, String proName, int pageIndex, int pageSize);

	/**
	 * ��������ѯ�ܼ�¼��
	 * 
	 * @param proCode
	 * @param proName
	 * @return
	 */
	public int providerCount(String proCode, String proName);

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
