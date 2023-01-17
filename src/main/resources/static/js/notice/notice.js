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
