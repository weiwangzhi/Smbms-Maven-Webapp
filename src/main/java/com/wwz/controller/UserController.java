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
import com.wwz.entity.SmbmsRole;
import com.wwz.entity.SmbmsUser;
import com.wwz.service.SmbmsRoleService;
import com.wwz.service.SmbmsUserService;

@Controller
public class UserController {
	@Autowired
	private SmbmsUserService smbmsUserService;
	@Autowired
	private SmbmsRoleService smbmsRoleService;
	private List<SmbmsRole> smbmsRoles = new ArrayList<SmbmsRole>();
	private List<SmbmsUser> smbmsUsers = new ArrayList<SmbmsUser>();

	/**
	 * 登录
	 * 
	 * @param smbmsUser
	 * @param model
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(SmbmsUser smbmsUser, HttpSession httpServletRequest, Map<String, Object> map) {
		map.remove("error");
		httpServletRequest.removeAttribute("userSession");
		// 登录
		smbmsUser = smbmsUserService.login(smbmsUser);
		if (smbmsUser != null) {
			httpServletRequest.setAttribute("userSession", smbmsUser);
			return "jsp/frame";
		}
		map.put("error", "用户名或密码错误！");
		return "jsp/login";
	}

	/**
	 * 退出
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("userSession");
		return "jsp/login";
	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pwdmodify.do")
	public String pwdmodify() {
		return "jsp/pwdmodify";
	}

	@ResponseBody
	@RequestMapping(value = "/updatePwd.do")
	public String updatePwd(@RequestParam("oldpassword") String oldpassword, HttpSession session,
			Map<String, Object> map) {
		SmbmsUser smbmsUser = (SmbmsUser) session.getAttribute("userSession");// 得到登录的用户信息
		if (smbmsUser != null) {
			smbmsUser.setUserPassword(oldpassword);
			if (smbmsUserService.login(smbmsUser) != null) {
				map.put("result", "true");// 旧密码正确
			} else {
				map.put("result", "false");// 旧密码错误
			}
		} else if (oldpassword.isEmpty()) {
			map.put("result", "error");// 旧密码为空
		} else {
			map.put("result", "sessionerror");// 会话过期
		}
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/pwdmodifysave.do")
	public String pwdmodifysave(@RequestParam("newpassword") String newpassword, HttpSession session,
			Map<String, Object> map) {
		SmbmsUser smbmsUser = (SmbmsUser) session.getAttribute("userSession");
		smbmsUser.setUserPassword(newpassword);
		int num = smbmsUserService.updateSmbmsUser(smbmsUser);// 执行修改密码
		if (num > 0) {
			return "redirect:/login.do";
		}
		return "jsp/pwdmodify";
	}

	// 每页记录数
	private int pageSize = 8;

	/**
	 * 分页
	 * 
	 * @param queryname
	 * @param queryUserRole
	 * @param pageIndex
	 * @param model
	 * @return
	 */
	@RequestMapping("/initUserList.do")
	public String getUsersByPage(@RequestParam(value = "queryname", required = false) String queryname,
			Integer queryUserRole, @RequestParam(defaultValue = "1") Integer pageIndex, Map<String, Object> model) {
		// 用户角色
		smbmsRoles = smbmsRoleService.getRoles();
		// 分页
		smbmsUsers = smbmsUserService.getUsersByPage(queryname, queryUserRole, pageIndex, pageSize);
		// 总记录数
		int totalCount = smbmsUserService.userCount(queryname, queryUserRole);
		// 当前页码
		int currentPageNo = pageIndex;
		// 总页数
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		model.put("roleList", smbmsRoles);
		model.put("queryUserRole", queryUserRole);
		model.put("queryUserName", queryname);
		model.put("userList", smbmsUsers);
		model.put("totalCount", totalCount);
		model.put("currentPageNo", currentPageNo);
		model.put("totalPageCount", totalPageCount);
		return "jsp/userlist";
	}

	/**
	 * 角色列表
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getrolelist.do")
	public Object getRoleList(Map<String, Object> map) {
		map.put("list", smbmsRoles = smbmsRoleService.getRoles());
		return JSONArray.toJSONString(map);
	}

	/**
	 * 删除用户
	 * 
	 * @param uid
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleUser.do")
	public String deleUser(@RequestParam("uid") Long uid, Map<String, Object> map) {
		SmbmsUser smbmsUser = smbmsUserService.getSmbmsUserById(uid);
		if (smbmsUser == null) {
			// 不存在
			map.put("delResult", "notexist");
		} else {
			int num = smbmsUserService.deleteSmbmsUser(uid);
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
	 * 获取id并查询
	 * 
	 * @param proid
	 * @param map
	 * @return
	 */
	@RequestMapping("/user.do")
	public String providerShow(@ModelAttribute("uid") Long uid, String method, Map<String, Object> map) {
		String variable = "";
		SmbmsUser smbmsUser = smbmsUserService.getSmbmsUserById(uid);
		if (smbmsUser != null) {
			map.put("user", smbmsUser);
			if (method.equals("view")) {
				variable = "jsp/userview";
			} else if (method.equals("modify")) {
				variable = "jsp/usermodify";
			} else {
				variable = "jsp/userlist";
			}
		}
		return variable;
	}

	/**
	 * 添加、修改用户
	 * 
	 * @param method
	 * @param smbmsUser
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/userAddOrModify.do", method = RequestMethod.POST)
	public String userAddOrModify(String method, SmbmsUser smbmsUser, HttpServletRequest request, HttpSession session,
			Map<String, Object> map, @RequestParam(value = "attachs", required = false) MultipartFile[] attachs) {
		String result = "redirect:/initUserList.do";
		String idPicPath = null;
		String workPicPath = null;
		String errorInfo = null;
		boolean flag = true;
		String filePath = "statics" + File.separator + "uploadfiles";
		String path = request.getSession().getServletContext().getRealPath(filePath);
		for (int i = 0; i < attachs.length; i++) {
			MultipartFile pic = attachs[i];
			if (!pic.isEmpty()) {
				if (i == 0) {
					errorInfo = "uploadFileError";
				} else if (i == 1) {
					errorInfo = "uploadWpError";
				}
				String oldFileName = pic.getOriginalFilename();// 原文件名
				String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
				int filesize = 500000;
				if (pic.getSize() > filesize) {// 上传大小不得超过 500k
					request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
					flag = false;
				} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
					String fileName = System.currentTimeMillis() + new Random().nextInt(10000000) + "." + prefix;
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					try {
						pic.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute(errorInfo, " * 上传失败！");
						flag = false;
					}
					if (i == 0) {
						idPicPath = filePath + File.separator + fileName;
					} else if (i == 1) {
						workPicPath = filePath + File.separator + fileName;
					}

				} else {
					request.setAttribute(errorInfo, " * 上传图片格式不正确");
					flag = false;
				}
			}
		}
		if ("add".equals(method) && flag) {
			smbmsUser.setCreatedBy(((SmbmsUser) session.getAttribute("userSession")).getId());
			smbmsUser.setCreationDate(new Timestamp(System.currentTimeMillis()));
			smbmsUser.setIdPic(idPicPath);
			smbmsUser.setWorkPic(workPicPath);
			if (smbmsUserService.addSmbmsUser(smbmsUser) > 0) {
				result = "redirect:/initUserList.do";
			} else {
				result = "jsp/useradd";
			}
		} else if ("modify".equals(method) && flag) {
			smbmsUser.setModifyBy(((SmbmsUser) session.getAttribute("userSession")).getId());
			smbmsUser.setModifyDate(new Timestamp(System.currentTimeMillis()));
			smbmsUser.setIdPic(idPicPath);
			smbmsUser.setWorkPic(workPicPath);
			int num = smbmsUserService.updateSmbmsUser(smbmsUser);
			if (num > 0) {
				result = "redirect:/initUserList.do";
			} else {
				result = "jsp/usermodify";
			}
		}
		return result;
	}

	@RequestMapping(value = "/userAdd.do")
	public String userAdd() {
		return "jsp/useradd";
	}

	@ResponseBody
	@RequestMapping(value = "/ucexist.do")
	public Object ucExist(@RequestParam("userCode") String userCode, Map<String, Object> map) {
		SmbmsUser smbmsUser = smbmsUserService.getSmbmsUserByUserCode(userCode.trim());
		map.put("userCode", smbmsUser.getUserCode());
		return JSONArray.toJSONString(map);
	}
}
