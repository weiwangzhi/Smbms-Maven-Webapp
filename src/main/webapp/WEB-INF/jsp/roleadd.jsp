<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span><a href="initRoleList.do"
			style="color: #2179a9">角色管理页面</a> &gt;&gt; 角色添加页面</span>
	</div>
	<div class="providerAdd">
		<form id="roleForm" name="roleForm" method="post"
			action="rolesave.do" enctype="multipart/form-data">
			<input name="method" value="add" type="hidden" />
			<!--div的class 为error是验证错误，ok是验证成功-->
			<div>
				<label for="roleCode">角色编码：</label> <input type="text"
					name="roleCode" id="roleCode" value="">
				<!-- 放置提示信息 -->
				<font color="red"></font>
			</div>
			<div>
				<label for="roleName">角色名称：</label> <input type="text"
					name="roleName" id="roleName" value=""> <font color="red"></font>
			</div>
			<div class="providerAddBtn">
				<input type="button" name="add" id="add" value="保存"> <input
					type="button" id="back" name="back" value="返回">
			</div>
		</form>
	</div>
</div>
</section>
<%@include file="./common/foot.jsp"%>
<script type="text/javascript" src="statics/js/roleadd.js"></script>
