package com.wwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wwz.dao.SmbmsUserDao;
import com.wwz.entity.SmbmsUser;
import com.wwz.service.SmbmsUserService;

@Service
public class SmbmsUserServiceImpl implements SmbmsUserService {
	@Autowired
	private SmbmsUserDao smbmsUserDao;

	@Override
	public SmbmsUser login(SmbmsUser smbmsUser) {
		return smbmsUserDao.login(smbmsUser);
	}

	@Override
	public List<SmbmsUser> getUsersByPage(String userName, Integer userRole, int pageIndex, int pageSize) {
		return smbmsUserDao.getUsersByPage(userName, userRole, (pageIndex - 1) * pageSize, pageSize);
	}

	@Override
	public int userCount(String userName, Integer userRole) {
		return smbmsUserDao.userCount(userName, userRole);
	}

	@Override
	public int addSmbmsUser(SmbmsUser smbmsUser) {
		return smbmsUserDao.addSmbmsUser(smbmsUser);
	}

	@Override
	public SmbmsUser getSmbmsUserById(Long id) {
		return smbmsUserDao.getSmbmsUserById(id);
	}

	@Override
	public int updateSmbmsUser(SmbmsUser smbmsUser) {
		return smbmsUserDao.updateSmbmsUser(smbmsUser);
	}

	@Override
	public int deleteSmbmsUser(Long id) {
		return smbmsUserDao.deleteSmbmsUser(id);
	}

	@Override
	public SmbmsUser getSmbmsUserByUserCode(String userCode) {
		SmbmsUser smbmsUser = new SmbmsUser();
		if (smbmsUserDao.getSmbmsUserByUserCode(userCode) > 0) {
			smbmsUser.setUserCode("exist");
		} else {
			smbmsUser.setUserCode("");
		}
		return smbmsUser;

	}

}
