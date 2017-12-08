var roleCode = null;
var roleName = null;
$(function() {
	roleCode = $("#roleCode");
	roleName = $("#roleName");
	saveBtn = $("#save");
	backBtn = $("#back");

	roleCode.next().html("*");
	roleName.next().html("*");

	function checkRoleInfo(){
		if(roleCode.val() == ''){
			roleCode.next().html("* 请输入角色编号");
			return false;
		}
		if(roleName.val() == ''){
			roleName.next().html("* 请输入角色名称");
			return false;
		}
		return true;
	}
	
	saveBtn.on("click", function() {
		if(checkRoleInfo()){
			$("#roleForm").submit();
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