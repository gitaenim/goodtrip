/*한아 작성*/

$(function(){
	//var aaaa= $(".group-form>select>option").attr("value");
	//alert(aaaa);
});

function departmentClicked(){
	department=$("#department option:selected").val();
	//alert(department);
	if(department!=0) getList();
	else location.href="/ozc/groupList";
}

function getList(){
	$.ajax({
		url:`/ozc/groupList/${department}`,
		type:"get",
		success:function(result){
			
			$("#emp-list").html(result);
			$("#paging").hide();
		}
		,error:function(){
			alert("다시 힘을 내요");
		}
	});
}

