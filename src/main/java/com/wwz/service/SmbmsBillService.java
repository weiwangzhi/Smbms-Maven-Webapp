package com.wwz.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wwz.entity.SmbmsBill;

public interface SmbmsBillService {
	/**
	 * 添加订单
	 * 
	 * @param smbmsBill
	 * @return
	 */
	public int addBill(SmbmsBill smbmsBill);

	/**
	 * 删
	 * 
	 * @param id
	 * @return
	 */
	public int deleteBill(Long id);

	/**
	 * 改
	 * 
	 * @param smbmsBill
	 * @return
	 */
	public int updateBill(SmbmsBill smbmsBill);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public SmbmsBill getSmbmsBillById(Long id);

	/**
	 * 按条件分页查询
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
	 * 按条件查询总记录数
	 * 
	 * @param productName
	 * @param providerId
	 * @param isPayment
	 * @return
	 */
	public int getBillCount(@Param("productName") String productName, @Param("providerId") Integer providerId,
			@Param("isPayment") Integer isPayment);
}
