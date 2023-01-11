var today = new Date();//오늘 날짜
var date = new Date();//today의 Date를 세어주는 역할
function prevCalendar() {//이전 달
	today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
	buildCalendar(); //달력 cell 만들어 출력 
	}
 
function nextCalendar() {//다음 달
	today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
	buildCalendar();//달력 cell 만들어 출력
	}
function buildCalendar(){//현재 달 달력 만들기
	var doMonth = new Date(today.getFullYear(),today.getMonth(),1);
	var lastDate = new Date(today.getFullYear(),today.getMonth()+1,0);
	//이번 달의 마지막 날
	       
	var tbCalendar = document.getElementById("calendar");
	            
	var tbCalendarYM = document.getElementById("tbCalendarYM");
	           
	tbCalendarYM.innerHTML = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월"; 
	 
	while (tbCalendar.rows.length > 2) {
		
		tbCalendar.deleteRow(tbCalendar.rows.length-1);
	                
	}
	var row = null;
	row = tbCalendar.insertRow();
	            
	var cnt = 0;// count, 셀의 갯수를 세어주는 역할
	           
	for (i=0; i<doMonth.getDay(); i++) {
	             
		cell = row.insertCell();
		cnt = cnt + 1;
	}
	/*달력 출력*/
	for (i=1; i<=lastDate.getDate(); i++) { 
	           
		cell = row.insertCell();
		cell.innerHTML = i;
		cnt = cnt + 1;
		if (cnt % 7 == 1) {/*일요일 계산*/
		                
		cell.innerHTML = "<font color=#F79DC2>" + i
		//1번째의 cell에만 색칠
	}    
	if (cnt%7 == 0){/* 토요일 구하기*/
		cell.innerHTML = "<font color=skyblue>" + i
		//7번째의 cell에만 색칠
		row = calendar.insertRow();
		                
	}
	/*오늘의 날짜에 노란색 칠하기*/
	if (today.getFullYear() == date.getFullYear()
	&& today.getMonth() == date.getMonth()
	&& i == date.getDate()) {
	                 
		cell.bgColor = "#FAF58C";//셀의 배경색을 노랑으로 
		}
	}
}