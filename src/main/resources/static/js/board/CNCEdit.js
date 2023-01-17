$(function() {
	$(".board-edit-mode").hide();
	$(".reply-edit-mode").hide();
});
function boardEditModeOn() {
	$(".board-veiw-mode").hide();
	$(".board-edit-mode").show();
}
function boardEditModeOff() {
	$(".board-edit-mode").hide();
	$(".board-veiw-mode").show();
}


//경조사일 선택시 오늘 이후가 아닌 이전날짜를 선택하는걸 막아놈
	$(function() {
		test();
      function test() {
      var now_utc = Date.now()
      var timeOff = new Date().getTimezoneOffset()*60000;
      var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
      document.getElementById("Date").setAttribute("min", today);}
	});
