package com.wwz.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wwz.entity.SmbmsBill;

public interface SmbmsBillService {
	/**
	 * ��Ӷ���
	 * 
	 * @param smbmsBill
	 * @return
	 */
	public int addBill(SmbmsBill smbmsBill);

	/**
	 * ɾ
	 * 
	 * @param id
	 * @return
	 */
	public int deleteBill(Long id);

	/**
	 * ��
	 * 
	 * @param smbmsBill
	 * @return
	 */
	public int updateBill(SmbmsBill smbmsBill);

	/**
	 * ����id��ѯ
	 * 
	 * @param id
	 * @return
	 */
	public SmbmsBill getSmbmsBillById(Long id);

	/**
	 * ��������ҳ��ѯ
	 * 
	 * @param productName
	 * @param providerId
	 * @param isPayment
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SmbmsBill> getBillsByPage(@Param("productName") String productName,
			@Param("providerId") Integer providerId, @Param("isPayment") Integer isPayment,
			@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

	/**
	 * ��������ѯ�ܼ�¼��
	 * 
	 * @param productName
	 * @param providerId
	 * @param isPayment
	 * @return
	 */
	public int getBillCount(@Param("productName") String productName, @Param("providerId") Integer providerId,
			@Param("isPayment") Integer isPayment);
}
