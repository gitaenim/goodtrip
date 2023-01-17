function departmentClicked(){
	department=$("#department option:selected").val();
	//alert(department);
	if(department!=0) getList();
	else location.href="/personnelEvaList";
}

function getList(){
	$.ajax({
		url:`/ozc/groupList/${department}`,
		type:"get",
		success:function(result){
			
			$("#emp-list").html(result);
		}
		,error:function(){
			alert("다시 힘을 내요");
		}
	});
}



