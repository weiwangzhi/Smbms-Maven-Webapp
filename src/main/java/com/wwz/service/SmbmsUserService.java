package com.wwz.service;

import java.util.List;

import com.wwz.entity.SmbmsUser;

public interface SmbmsUserService {
	/**
	 * ��¼
	 * 
	 * @param smbmsUser
	 * @return
	 */
	public SmbmsUser login(SmbmsUser smbmsUser);

	/**
	 * ��������ҳ
	 * 
	 * @param userName
	 * @param roleName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SmbmsUser> getUsersByPage(String userName, Integer userRole, int pageIndex, int pageSize);

	/**
	 * ���������¼��
	 * 
	 * @param userName
	 * @param userRole
	 * @return
	 */
	public int userCount(String userName, Integer userRole);

	/**
	 * ����û�
	 * 
	 * @param smbmsUser
	 * @return
	 */
	public int addSmbmsUser(SmbmsUser smbmsUser);

	/**
	 * ����id�鿴�û���Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public SmbmsUser getSmbmsUserById(Long id);

	/**
	 * �޸��û���Ϣ
	 * 
	 * @param smbmsUser
	 * @return
	 */
	public int updateSmbmsUser(SmbmsUser smbmsUser);

	/**
	 * ����idɾ���û���Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSmbmsUser(Long id);
	
	/**
	 * ͨ���û������ѯ
	 * @param userCode
	 * @return
	 */
	public SmbmsUser getSmbmsUserByUserCode(String userCode);
}
