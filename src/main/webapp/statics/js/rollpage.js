function page_nav(frm, num) {
	frm.pageIndex.value = num;
	frm.submit();
}

function jump_to(frm, num) {
	//验证用户的输入
	var regexp = /^[1-9]\d*$/;
	var totalPageCount = document.getElementById("totalPageCount").value;
	if (regexp.test(num) && (num - totalPageCount) <= 0) {
		page_nav(frm, num);
	}
}