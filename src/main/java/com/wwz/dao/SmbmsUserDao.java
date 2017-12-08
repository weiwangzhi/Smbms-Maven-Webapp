package com.wwz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wwz.entity.SmbmsUser;

public interface SmbmsUserDao {
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
	public List<SmbmsUser> getUsersByPage(@Param("userName") String userName, @Param("userRole") Integer userRole,
			@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

	/**
	 * ���������¼��
	 * 
	 * @param userName
	 * @param userRole
	 * @return
	 */
	public int userCount(@Param("userName") String userName, @Param("userRole") Integer userRole);

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
	public int getSmbmsUserByUserCode(String userCode);

}
