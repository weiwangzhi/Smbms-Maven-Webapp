package com.wwz.dao;

import java.util.List;

import com.wwz.entity.SmbmsRole;

public interface SmbmsRoleDao {
	/**
	 * 角色列表
	 * 
	 * @return
	 */
	public List<SmbmsRole> getRoles();

	/**
	 * 添加角色
	 * 
	 * @param smbmsRole
	 * @return
	 */
	public int addRole(SmbmsRole smbmsRole);

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	public int deleteRole(Long id);

	/**
	 * 修改角色
	 * 
	 * @param smbmsRole
	 * @return
	 */
	public int updateRole(SmbmsRole smbmsRole);
	
	/**
	 * 角色总记录数
	 * @return
	 */
	public int getRoleCount();
	
	/**
	 * 通过id查看
	 * @param id
	 * @return
	 */
	public SmbmsRole getRoleById(Long id);
	
	/**
	 * 通过roleCode查看
	 * @param roleCode
	 * @return
	 */
	public int getRoleByRoleCode(String  roleCode);
	
	/**
	 * 通过roleName查看
	 * @param roleCode
	 * @return
	 */
	public int getRoleByRoleName(String  roleName);
	
}
