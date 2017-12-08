var roleObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj) {
	$.ajax({
		type : "GET",
		url : "deleRole.do",
		data : {
			rid : obj.attr("roleid")
		},
		dataType : "json",
		success : function(data) {
			if (data.delResult == "true") { //删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			} else if (data.delResult == "false") { //删除失败
				changeDLGContent("对不起，删除角色【" + obj.attr("rolename") + "】失败");
			} else if (data.delResult == "notexist") {
				changeDLGContent("对不起，角色【" + obj.attr("rolename") + "】不存在");
			}
		},
		error : function(data) {
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG() {
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn() {
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr) {
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function() {
	/**
	 * bind、live、delegate
	 * on
	 */
	$(".modifyRole").on("click", function() {
		var obj = $(this);
		window.location.href = path + "/modifyrole.do?id=" + obj.attr("roleid");
	});

	$('#no').click(function() {
		cancleBtn();
	});

	$('#yes').click(function() {
		deleteUser(roleObj);
	});

	$(".deleteRole").on("click", function() {
		roleObj = $(this);
		changeDLGContent("你确定要删除角色【" + roleObj.attr("rolename") + "】吗？");
		openYesOrNoDLG();
	});
});