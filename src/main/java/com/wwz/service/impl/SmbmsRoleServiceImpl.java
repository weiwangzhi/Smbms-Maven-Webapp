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

}
