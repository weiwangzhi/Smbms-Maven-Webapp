<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span><a href="initRoleList.do"
			style="color: #2179a9">角色管理页面</a> &gt;&gt; 角色修改页面</span>
	</div>
	<div class="providerAdd">
		<form id="roleForm" name="roleForm" method="post" action="rolesave.do">
			<input type="hidden" name="method" value="modify"> <input
				type="hidden" name="id" value="${role.id }" />
			<div>
				<label for="roleCode">角色编码：</label> <input type="text"
					name="roleCode" id="roleCode" value="${role.roleCode }"> <font
					color="red"></font>
			</div>
			<div>
				<label for="roleName">角色名称：</label> <input type="text"
					name="roleName" id="roleName" value="${role.roleName }"> <font
					color="red"></font>
			</div>
			<div class="providerAddBtn">
				<input type="button" name="save" id="save" value="保存" /> <input
					type="button" id="back" name="back" value="返回" />
			</div>
		</form>
	</div>
</div>
</section>
<%@include file="./common/foot.jsp"%>
<script type="text/javascript" src="statics/js/rolemodify.js"></script>
