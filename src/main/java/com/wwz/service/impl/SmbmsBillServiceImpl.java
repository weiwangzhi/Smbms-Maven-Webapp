package com.wwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wwz.dao.SmbmsBillDao;
import com.wwz.entity.SmbmsBill;
import com.wwz.service.SmbmsBillService;

@Service
public class SmbmsBillServiceImpl implements SmbmsBillService {
	@Autowired
	private SmbmsBillDao smbmsBillDao;

	@Override
	public int addBill(SmbmsBill smbmsBill) {
		return smbmsBillDao.addBill(smbmsBill);
	}

	@Override
	public int deleteBill(Long id) {
		return smbmsBillDao.deleteBill(id);
	}

	@Override
	public int updateBill(SmbmsBill smbmsBill) {
		return smbmsBillDao.updateBill(smbmsBill);
	}

	@Override
	public SmbmsBill getSmbmsBillById(Long id) {
		return smbmsBillDao.getSmbmsBillById(id);
	}

	@Override
	public List<SmbmsBill> getBillsByPage(String productName, Integer providerId, Integer isPayment, int pageIndex,
			int pageSize) {
		return smbmsBillDao.getBillsByPage(productName, providerId, isPayment, (pageIndex - 1) * pageSize, pageSize);
	}

	@Override
	public int getBillCount(String productName, Integer providerId, Integer isPayment) {
		return smbmsBillDao.getBillCount(productName, providerId, isPayment);
	}

}
