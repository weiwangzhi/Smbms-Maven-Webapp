package com.wwz.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.wwz.entity.SmbmsProvider;
import com.wwz.entity.SmbmsUser;
import com.wwz.service.SmbmsProviderService;

@Controller
public class ProviderController {
	@Autowired
	private SmbmsProviderService smbmsProviderService;
	private List<SmbmsProvider> SmbmsProviders = new ArrayList<SmbmsProvider>();
	private int pageSize = 8;

	@RequestMapping("/initProviderList.do")
	public String getProviderByPage(@RequestParam(value = "proCode", required = false) String proCode, String proName,
			@RequestParam(defaultValue = "1") int pageIndex, Map<String, Object> map) {
		try {
			SmbmsProviders = smbmsProviderService.getProviderByPage(proCode, proName, pageIndex, pageSize);
			// �ܼ�¼��
			int totalCount = smbmsProviderService.providerCount(proCode, proName);
			// ��ǰҳ��
			int currentPageNo = pageIndex;
			// ��ҳ��
			int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
			map.put("providerList", SmbmsProviders);
			map.put("totalCount", totalCount);
			map.put("currentPageNo", currentPageNo);
			map.put("totalPageCount", totalPageCount);
			map.put("proCode", proCode);
			map.put("proName", proName);
			return "jsp/providerlist";
		} catch (Exception e) {
			return "jsp/providerlist";
		}
	}

	/**
	 * ����id�鿴
	 * 
	 * @param proid
	 * @param map
	 * @return
	 */
	@RequestMapping("/viewProvider.do")
	public String viewProvider(@ModelAttribute("proid") Long proid, Map<String, Object> map) {
		try {
			SmbmsProvider smbmsProvider = smbmsProviderService.getProviderById(proid);
			if (smbmsProvider != null) {
				map.put("provider", smbmsProvider);
				return "jsp/providerview";
			}
			return "jsp/providerlist";
		} catch (Exception e) {
			e.printStackTrace();
			return "jsp/providerlist";
		}
	}

	/**
	 * ��ȡid����ѯ
	 * 
	 * @param proid
	 * @param map
	 * @return
	 */

	@RequestMapping("/provider.do")
	public String providerShow(@ModelAttribute("proid") Long proid, String method, Map<String, Object> map) {
		String variable = "";
		SmbmsProvider smbmsProvider = smbmsProviderService.getProviderById(proid);
		if (smbmsProvider != null) {
			map.put("provider", smbmsProvider);
			if (method.equals("view")) {
				variable = "jsp/providerview";
			} else if (method.equals("modify")) {
				variable = "jsp/providermodify";
			} else {
				variable = "jsp/providerlist";
			}
		}
		return variable;
	}

	/**
	 * ɾ��
	 * 
	 * @param proid
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteProvider.do")
	public String deleteProviderById(@ModelAttribute("proid") Long proid, Map<String, String> map) {
		SmbmsProvider smbmsProvider = smbmsProviderService.getProviderById(proid);
		if (smbmsProvider == null) {
			// ������
			map.put("delResult", "notexist");
		} else {
			int num = smbmsProviderService.deleteProviderById(proid);
			if (num > 0) {
				// �ɹ�
				map.put("delResult", "true");
			} else {
				// ʧ��
				map.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * �޸�SmbmsProvider
	 * 
	 * @param smbmsProvider
	 * @param map
	 * @return
	 */
	@RequestMapping("/modifyProvider.do")
	public String modifyProvider(SmbmsProvider smbmsProvider, Map<String, Object> map, HttpSession session) {
		try {
			smbmsProvider.setModifyDate(new Timestamp(System.currentTimeMillis()));
			smbmsProvider.setModifyBy(((SmbmsUser) session.getAttribute("userSession")).getId());
			smbmsProviderService.updateProviderById(smbmsProvider);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/initProviderList.do";
	}

	@RequestMapping("/provideradd.do")
	public String provideradd() {
		return "jsp/provideradd";
	}

	@RequestMapping(value = "/providersave.do", method = RequestMethod.POST)
	public String providersave(SmbmsProvider smbmsProvider, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "attach", required = false) MultipartFile[] attach) {
		String idPicPath = null;
		String workPicPath = null;
		String errorInfo = null;
		boolean flag = true;
		String filePath = "statics" + File.separator + "uploadfiles";
		String path = request.getSession().getServletContext().getRealPath(filePath);
		for (int i = 0; i < attach.length; i++) {
			MultipartFile pic = attach[i];
			if (!pic.isEmpty()) {
				if (i == 0) {
					errorInfo = "uploadFileError";
				} else if (i == 1) {
					errorInfo = "uploadWpError";
				}
				String oldFileName = pic.getOriginalFilename();// ԭ�ļ���
				String prefix = FilenameUtils.getExtension(oldFileName);// ԭ�ļ���׺
				int filesize = 500000;
				if (pic.getSize() > filesize) {// �ϴ���С���ó��� 500k
					request.setAttribute(errorInfo, " * �ϴ���С���ó��� 500k");
					flag = false;
				} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// �ϴ�ͼƬ��ʽ����ȷ
					String fileName = System.currentTimeMillis() + new Random().nextInt(10000000) + "." + prefix;
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// ����
					try {
						pic.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute(errorInfo, " * �ϴ�ʧ�ܣ�");
						flag = false;
					}
					if (i == 0) {
						idPicPath = filePath + File.separator + fileName;
					} else if (i == 1) {
						workPicPath = filePath + File.separator + fileName;
					}

				} else {
					request.setAttribute(errorInfo, " * �ϴ�ͼƬ��ʽ����ȷ");
					flag = false;
				}
			}
		}
		if (flag) {
			smbmsProvider.setCreatedBy(((SmbmsUser) session.getAttribute("userSession")).getId());
			smbmsProvider.setCreationDate(new Timestamp(System.currentTimeMillis()));
			smbmsProvider.setProYyPhoto(idPicPath);
			smbmsProvider.setProJgPhotos(workPicPath);
			if (smbmsProviderService.addProvider(smbmsProvider) > 0) {
				return "redirect:/initProviderList.do";
			}
		}
		return "jsp/provideradd";
	}
}
