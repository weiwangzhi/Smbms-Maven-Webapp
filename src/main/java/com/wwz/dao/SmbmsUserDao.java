package com.wwz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wwz.entity.SmbmsUser;

public interface SmbmsUserDao {
	/**
	 * 登录
	 * 
	 * @param smbmsUser
	 * @return
	 */
	public SmbmsUser login(SmbmsUser smbmsUser);

	/**
	 * 按条件分页
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
	 * 按条件查记录数
	 * 
	 * @param userName
	 * @param userRole
	 * @return
	 */
	public int userCount(@Param("userName") String userName, @Param("userRole") Integer userRole);

	/**
	 * 添加用户
	 * 
	 * @param smbmsUser
	 * @return
	 */
	public int addSmbmsUser(SmbmsUser smbmsUser);

	/**
	 * 根据id查看用户信息
	 * 
	 * @param id
	 * @return
	 */
	public SmbmsUser getSmbmsUserById(Long id);

	/**
	 * 修改用户信息
	 * 
	 * @param smbmsUser
	 * @return
	 */
	public int updateSmbmsUser(SmbmsUser smbmsUser);

	/**
	 * 根据id删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSmbmsUser(Long id);
	
	/**
	 * 通过用户编码查询
	 * @param userCode
	 * @return
	 */
	public int getSmbmsUserByUserCode(String userCode);

}
