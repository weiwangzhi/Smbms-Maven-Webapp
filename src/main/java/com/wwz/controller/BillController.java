package com.wwz.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.wwz.entity.SmbmsBill;
import com.wwz.entity.SmbmsProvider;
import com.wwz.entity.SmbmsUser;
import com.wwz.service.SmbmsBillService;
import com.wwz.service.SmbmsProviderService;

@Controller
public class BillController {
	@Autowired
	private SmbmsBillService smbmsBillService;
	@Autowired
	private SmbmsProviderService smbmsProviderService;
	private List<SmbmsBill> smbmsBills = new ArrayList<SmbmsBill>();
	private List<SmbmsProvider> smbmsProviders = new ArrayList<SmbmsProvider>();
	private int pageSize = 8;

	/**
	 * 加载
	 * 
	 * @param queryProductName
	 * @param queryProviderId
	 * @param queryIsPayment
	 * @param pageIndex
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/initBill.do")
	public String initBill(@RequestParam(value = "queryProductName", required = false) String queryProductName,
			Integer queryProviderId, Integer queryIsPayment, @RequestParam(defaultValue = "1") int pageIndex,
			Map<String, Object> map) {
		smbmsBills = smbmsBillService.getBillsByPage(queryProductName, queryProviderId, queryIsPayment, pageIndex,
				pageSize);
		smbmsProviders = smbmsProviderService.getAllProvider();
		int totalCount = smbmsBillService.getBillCount(queryProductName, queryProviderId, queryIsPayment);
		// 当前页码
		int currentPageNo = pageIndex;
		// 总页数
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		map.put("billList", smbmsBills);
		map.put("providerList", smbmsProviders);
		map.put("totalCount", totalCount);
		map.put("currentPageNo", currentPageNo);
		map.put("totalPageCount", totalPageCount);
		map.put("queryProductName", queryProductName);
		map.put("queryProviderId", queryProviderId);
		map.put("queryIsPayment", queryIsPayment);
		return "jsp/billlist";
	}

	/**
	 * 获得供应商列表
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getproviderlist.do")
	public Object getProviderList(Map<String, Object> map) {
		map.put("list", smbmsProviders = smbmsProviderService.getAllProvider());
		return JSONArray.toJSONString(map);
	}

	/**
	 * 添加订单页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/billadd.do")
	public String billadd() {
		return "jsp/billadd";
	}

	/**
	 * 添加订单,修改订单
	 * 
	 * @param smbmsBill
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/billsave.do")
	public String billsave(SmbmsBill smbmsBill, String method, HttpSession session, Map<String, Object> map) {
		String result = "redirect:/initBill.do";
		if ("add".equals(method)) {
			smbmsBill.setCreatedBy(((SmbmsUser) session.getAttribute("userSession")).getId());
			smbmsBill.setCreationDate(new Timestamp(System.currentTimeMillis()));
			int num = smbmsBillService.addBill(smbmsBill);
			if (num > 0) {
				result = "redirect:/initBill.do";
			}else {
				result = "jsp/billadd";
			}
		} else if ("modify".equals(method)) {
			smbmsBill.setModifyBy(((SmbmsUser) session.getAttribute("userSession")).getId());
			smbmsBill.setModifyDate(new Timestamp(System.currentTimeMillis()));
			int num = smbmsBillService.updateBill(smbmsBill);
			if (num > 0) {
				result = "redirect:/initBill.do";
			}else {
				result = "jsp/billmodify";
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteBill.do")
	public String deleteBill(@RequestParam("billid") Long billid,Map<String, String> map){
		SmbmsBill smbmsBill = smbmsBillService.getSmbmsBillById(billid);
		if (smbmsBill == null) {
			// 不存在
			map.put("delResult", "notexist");
		} else {
			int num = smbmsBillService.deleteBill(billid);
			if (num > 0) {
				// 成功
				map.put("delResult", "true");
			} else {
				// 失败
				map.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * 查看，修改
	 * 
	 * @param billid
	 * @param method
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/bill.do")
	public String bill(@RequestParam("billid") Long billid, String method, Map<String, Object> map) {
		SmbmsBill smbmsBill = smbmsBillService.getSmbmsBillById(billid);
		map.put("bill", smbmsBill);
		if ("view".equals(method)) {
			return "jsp/billview";
		} else if ("modify".equals(method)) {
			return "jsp/billmodify";
		} else {
			return "jsp/billlist";
		}
	}
}
