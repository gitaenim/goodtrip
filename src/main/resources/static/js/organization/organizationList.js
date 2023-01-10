
$(function(){
	alert("레디 펑션!?");
	var aaaa= $(".group-form>select>option").attr("value");
	alert(aaaa);
	
});

function departmentClicked(){
	alert("영차");
	department=$("#selectbox option:selected").val();
	alert(department);
	getList();
}

function getList(){
	$.ajax({
		url:`/ozc/groupList/${department}`,
		type:"get",
		success:function(){
			alert("여기까지 잘 오셨습니다~");
		}
		,error:function(){
			alert("다시 힘을 내요");
		}
	});
}
