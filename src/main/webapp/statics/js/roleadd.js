var roleCode = null;
var roleName = null;
var addBtn = null;
var backBtn = null;


$(function() {
	roleCode = $("#roleCode");
	roleName = $("#roleName");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	roleCode.next().html("*");
	roleName.next().html("*");

	roleCode.bind("blur", function() {
		$.ajax({
			type : "GET", //请求类型
			url : "rcexist.do", //请求的url
			data : {
				roleCode : roleCode.val()
			}, //请求参数
			dataType : "json", //ajax接口（请求url）返回的数据类型
			success : function(data) { //data：返回数据（json对象）
				if(roleCode.val() == '' || roleCode.val() == null){
					validateTip(roleCode.next(), {
						"color" : "red"
					}, imgNo + "* 角色编码不能为空", false);
				}else{
					if (data.roleCode == "exist") { //账号已存在，错误提示
						validateTip(roleCode.next(), {
							"color" : "red"
						}, imgNo + " 该角色编号已存在", false);
					} else {
						validateTip(roleCode.next(), {
							"color" : "green"
						}, imgYes + " 该角色编号可以使用", true);
					}
				}
			},
			error : function(data) { //当访问时候，404，500 等非200的错误状态码
				validateTip(roleCode.next(), {
					"color" : "red"
				}, imgNo + " 您访问的页面不存在", false);
			}
		});

	}).bind("focus", function() {
		//显示友情提示
		validateTip(roleCode.next(), {
			"color" : "#666666"
		}, "* 请输入角色编码", false);
	}).focus();

	roleName.bind("blur", function() {
		$.ajax({
			type : "GET", //请求类型
			url : "rnexist.do", //请求的url
			data : {
				roleName : roleName.val()
			}, //请求参数
			dataType : "json", //ajax接口（请求url）返回的数据类型
			success : function(data) { //data：返回数据（json对象）
				if(roleName.val() == '' || roleName.val() == null){
					validateTip(roleName.next(), {
						"color" : "red"
					}, imgNo + "* 角色名称不能为空", false);
				}else{
					if (data.roleName == "exist") { //账号已存在，错误提示
						validateTip(roleName.next(), {
							"color" : "red"
						}, imgNo + " 该角色名称已存在", false);
					} else {
						validateTip(roleName.next(), {
							"color" : "green"
						}, imgYes + " 该角色名称可以使用", true);
					}
				}
			},
			error : function(data) { //当访问时候，404，500 等非200的错误状态码
				validateTip(roleName.next(), {
					"color" : "red"
				}, imgNo + " 您访问的页面不存在", false);
			}
		});

	}).bind("focus", function() {
		//显示友情提示
		validateTip(roleName.next(), {
			"color" : "#666666"
		}, "* 请输入角色名称", false);
	}).focus();

	addBtn.bind("click", function() {
		if (roleCode.attr("validateStatus") != "true") {
			roleCode.blur();
		} else if (roleName.attr("validateStatus") != "true") {
			roleName.blur();
		} else {
			if (confirm("是否确认提交数据")) {
				$("#roleForm").submit();
			}
		}

	});

	backBtn.on("click", function() {
		if (referer != undefined
			&& null != referer
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4) {
			window.location.href = referer;
		} else {
			history.back(-1);
		}
	});
});