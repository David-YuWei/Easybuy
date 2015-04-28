
var check = function(){
	option = {};
	option.username = $('#username').val();
	$.getJSON('/Easybuy/registration/checkusername?_format=json', option, function(r){
		if(r.status == 'success'){
			$('#message').html(r.msg);
		}
	});
}