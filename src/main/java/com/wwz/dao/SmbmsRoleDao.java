package com.wwz.dao;

import java.util.List;

import com.wwz.entity.SmbmsRole;

public interface SmbmsRoleDao {
	/**
	 * ��ɫ�б�
	 * 
	 * @return
	 */
	public List<SmbmsRole> getRoles();

	/**
	 * ��ӽ�ɫ
	 * 
	 * @param smbmsRole
	 * @return
	 */
	public int addRole(SmbmsRole smbmsRole);

	/**
	 * ɾ����ɫ
	 * 
	 * @param id
	 * @return
	 */
	public int deleteRole(Long id);

	/**
	 * �޸Ľ�ɫ
	 * 
	 * @param smbmsRole
	 * @return
	 */
	public int updateRole(SmbmsRole smbmsRole);
	
	/**
	 * ��ɫ�ܼ�¼��
	 * @return
	 */
	public int getRoleCount();
	
	/**
	 * ͨ��id�鿴
	 * @param id
	 * @return
	 */
	public SmbmsRole getRoleById(Long id);
	
	/**
	 * ͨ��roleCode�鿴
	 * @param roleCode
	 * @return
	 */
	public int getRoleByRoleCode(String  roleCode);
	
	/**
	 * ͨ��roleName�鿴
	 * @param roleCode
	 * @return
	 */
	public int getRoleByRoleName(String  roleName);
	
}
