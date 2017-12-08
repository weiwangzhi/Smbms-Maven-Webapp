package com.wwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wwz.dao.SmbmsRoleDao;
import com.wwz.entity.SmbmsRole;
import com.wwz.service.SmbmsRoleService;

@Service
public class SmbmsRoleServiceImpl implements SmbmsRoleService {
	@Autowired
	private SmbmsRoleDao smbmsRoleDao;

	@Override
	public List<SmbmsRole> getRoles() {
		return smbmsRoleDao.getRoles();
	}

	@Override
	public int addRole(SmbmsRole smbmsRole) {
		return smbmsRoleDao.addRole(smbmsRole);
	}

	@Override
	public int deleteRole(Long id) {
		return smbmsRoleDao.deleteRole(id);
	}

	@Override
	public int updateRole(SmbmsRole smbmsRole) {
		return smbmsRoleDao.updateRole(smbmsRole);
	}

	@Override
	public int getRoleCount() {
		return smbmsRoleDao.getRoleCount();
	}

	@Override
	public SmbmsRole getRoleById(Long id) {
		return smbmsRoleDao.getRoleById(id);
	}

	@Override
	public SmbmsRole getRoleByRoleCode(String roleCode) {
		SmbmsRole smbmsRole = new SmbmsRole();
		if (smbmsRoleDao.getRoleByRoleCode(roleCode) > 0) {
			smbmsRole.setRoleCode("exist");
		}else {
			smbmsRole.setRoleCode("");
		}
		return smbmsRole;
	}

	@Override
	public SmbmsRole getRoleByRoleName(String roleName) {
		SmbmsRole smbmsRole = new SmbmsRole();
		if (smbmsRoleDao.getRoleByRoleName(roleName) > 0) {
			smbmsRole.setRoleName("exist");
		}else {
			smbmsRole.setRoleName("");
		}
		return smbmsRole;
	}

}
