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
import com.wwz.entity.SmbmsRole;
import com.wwz.entity.SmbmsUser;
import com.wwz.service.SmbmsRoleService;

@Controller
public class RoleController {
	@Autowired
	private SmbmsRoleService smbmsRoleService;
	private List<SmbmsRole> smbmsRoles = new ArrayList<SmbmsRole>();

	/**
	 * 加载角色列表
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/initRoleList.do")
	public String initRoleList(Map<String, Object> map) {
		smbmsRoles = smbmsRoleService.getRoles();
		map.put("rolelist", smbmsRoles);
		return "jsp/rolelist";
	}

	/**
	 * 点击添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/roleAdd.do")
	public String roleAdd() {
		return "jsp/roleadd";
	}

	/**
	 * 点击修改
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/modifyrole.do")
	public String modifyrole(@RequestParam("id") Long id, Map<String, Object> map) {
		SmbmsRole smbmsRole = smbmsRoleService.getRoleById(id);
		map.put("role", smbmsRole);
		return "jsp/rolemodify";
	}

	@ResponseBody
	@RequestMapping(value = "/rcexist.do")
	public String rcexist(@RequestParam("roleCode") String roleCode, Map<String, Object> map) {
		map.put("roleCode", smbmsRoleService.getRoleByRoleCode(roleCode).getRoleCode());
		return JSONArray.toJSONString(map);
	}

	@ResponseBody
	@RequestMapping(value = "/rnexist.do")
	public String rnexist(@RequestParam("roleName") String roleName, Map<String, Object> map) {
		map.put("roleName", smbmsRoleService.getRoleByRoleName(roleName).getRoleName());
		return JSONArray.toJSONString(map);
	}

	/**
	 * 添加、修改角色
	 * 
	 * @param smbmsRole
	 * @param method
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/rolesave.do")
	public String rolesave(SmbmsRole smbmsRole, @RequestParam("method") String method, HttpSession session,
			Map<String, Object> map) {
		String result = "redirect:/initRoleList.do";
		if ("add".equals(method)) {
			smbmsRole.setCreatedBy(((SmbmsUser) session.getAttribute("userSession")).getId());
			smbmsRole.setCreationDate(new Timestamp(System.currentTimeMillis()));
			if (smbmsRoleService.addRole(smbmsRole) > 0) {
				result = "redirect:/initRoleList.do";
			} else {
				result = "jsp/roleadd";
			}
		} else if ("modify".equals(method)) {
			smbmsRole.setModifyBy(((SmbmsUser) session.getAttribute("userSession")).getId());
			smbmsRole.setModifyDate(new Timestamp(System.currentTimeMillis()));
			if (smbmsRoleService.updateRole(smbmsRole) > 0) {
				result = "redirect:/initRoleList.do";
			} else {
				result = "jsp/rolemodify";
			}
		}
		return result;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleRole.do")
	public String deleRole(@RequestParam("rid") Long rid, Map<String, Object> map) {
		SmbmsRole smbmsRole = smbmsRoleService.getRoleById(rid);
		if (smbmsRole == null) {
			// 不存在
			map.put("delResult", "notexist");
		} else {
			int num = smbmsRoleService.deleteRole(rid);
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
}
