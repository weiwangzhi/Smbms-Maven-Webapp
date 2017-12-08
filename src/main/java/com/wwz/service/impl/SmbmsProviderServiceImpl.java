package com.wwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wwz.dao.SmbmsProviderDao;
import com.wwz.entity.SmbmsProvider;
import com.wwz.service.SmbmsProviderService;

@Service
public class SmbmsProviderServiceImpl implements SmbmsProviderService {
	@Autowired
	private SmbmsProviderDao smbmsProviderDao;

	@Override
	public List<SmbmsProvider> getProviderByPage(String proCode, String proName, int pageIndex, int pageSize) {
		return smbmsProviderDao.getProviderByPage(proCode, proName, (pageIndex - 1) * pageSize, pageSize);
	}

	@Override
	public int providerCount(String proCode, String proName) {
		return smbmsProviderDao.providerCount(proCode, proName);
	}

	@Override
	public SmbmsProvider getProviderById(Long id) {
		return smbmsProviderDao.getProviderById(id);
	}

	@Override
	public int addProvider(SmbmsProvider smbmsProvider) {
		return smbmsProviderDao.addProvider(smbmsProvider);
	}

	@Override
	public int updateProviderById(SmbmsProvider id) {
		return smbmsProviderDao.updateProviderById(id);
	}

	@Override
	public int deleteProviderById(Long id) {
		return smbmsProviderDao.deleteProviderById(id);
	}

	@Override
	public List<SmbmsProvider> getAllProvider() {
		return smbmsProviderDao.getAllProvider();
	}

}
