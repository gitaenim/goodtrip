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
function replyEditModeOn() {
	$(".reply-veiw-mode").hide();
	$(".reply-edit-mode").show();
}
function replyEditModeOff() {
	$(".reply-edit-mode").hide();
	$(".reply-veiw-mode").show();
}