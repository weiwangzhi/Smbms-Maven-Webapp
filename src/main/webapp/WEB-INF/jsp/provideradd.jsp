<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span><a href="initProviderList.do"
			style="color: #2179a9">供应商管理页面</a> &gt;&gt; 供应商添加页面</span>
	</div>
	<div class="providerAdd">
		<form id="providerForm" enctype="multipart/form-data"
			name="providerForm" method="post" action="providersave.do">
			<!-- <input type="hidden" name="method" value="add"> -->
			<!--div的class 为error是验证错误，ok是验证成功-->
			<div class="">
				<label for="proCode">供应商编码：</label> <input type="text"
					name="proCode" id="proCode" value="">
				<%-- <form:input path="procode" id="proCode"/> --%>
				<!-- 放置提示信息 -->
				<font color="red"></font>
			</div>
			<div>
				<label for="proName">供应商名称：</label> <input type="text"
					name="proName" id="proName" value="">
				<%-- <form:input path="proName" id="proName"/> --%>
				<font color="red"></font>
			</div>
			<div>
				<label for="proContact">联系人：</label>
				<%-- <form:input path="proContact" id="proContact"/> --%>
				<input type="text" name="proContact" id="proContact" value="">
				<font color="red"></font>

			</div>
			<div>
				<label for="proPhone">联系电话：</label>
				<%-- <form:input path="proPhone" id="proPhone"/> --%>
				<input type="text" name="proPhone" id="proPhone" value=""> <font
					color="red"></font>
			</div>
			<div>
				<label for="proAddress">联系地址：</label>
				<%-- <form:input path="proAddress" id="proAddress"/> --%>
				<input type="text" name="proAddress" id="proAddress" value="">
			</div>
			<div>
				<label for="proFax">传真：</label>
				<%-- <form:input path="proFax" id="proFax"/> --%>
				<input type="text" name="proFax" id="proFax" value="">
			</div>
			<div>
				<label for="proDesc">描述：</label>
				<%-- <form:input path="proDesc" id="proDesc"/> --%>
				<input type="text" name="proDesc" id="proDesc" value="">
			</div>
			<div>
				<label for="proYyPhoto">企业营业执照：</label> <input type="file"
					name="attach">
			</div>
			<div>
				<label for="proJgPhotos">组织机构代码证：</label> <input type="file"
					name="attach">
			</div>
			<div>
				<label></label> <label>${uploadFileError }</label>
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
<script type="text/javascript" src="statics/js/provideradd.js"></script>
