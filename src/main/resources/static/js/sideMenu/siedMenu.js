$(function() {
	display();
	$(".menuList dt").click(menuClicked);
	$(".menuForm li:nth-child(1)").click(moveHome);
});
function menuClicked() {
	display();
	$(".menuList dd").removeClass("target");
	$(this).parent().children("dd").addClass("target");

	if ($(".menuList dd").hasClass("target")) {
		$(".target").show();
	}

}
function display() {
	$(".menuList dd").hide();
}
function moveHome(){
	location.href = "/";
}