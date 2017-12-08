<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>供应商管理页面 >> 信息查看</span>
	</div>
	<div class="providerView">
		<p>
			<strong>供应商编码：</strong><span>${provider.proCode }</span>
		</p>
		<p>
			<strong>供应商名称：</strong><span>${provider.proName }</span>
		</p>
		<p>
			<strong>联系人：</strong><span>${provider.proContact }</span>
		</p>
		<p>
			<strong>联系电话：</strong><span>${provider.proPhone }</span>
		</p>
		<p>
			<strong>传真：</strong><span>${provider.proFax }</span>
		</p>
		<p>
			<strong>描述：</strong><span>${provider.proDesc}</span>
		</p>
		<p>
			<strong>企业营业执照：</strong><span><img src="${provider.proYyPhoto}" /></span>
		</p>
		<p>
			<strong>组织机构代码证：</strong><span><img src="${provider.proJgPhotos}" /></span>
		</p>
		<div class="providerAddBtn">
			<input type="button" id="back" name="back" value="返回">
		</div>
	</div>
</div>
</section>
<%@include file="./common/foot.jsp"%>
<script type="text/javascript" src="statics/js/providerview.js"></script>
