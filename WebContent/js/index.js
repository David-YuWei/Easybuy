
$(document.body).ready(function(){
	initTmpls();
	searchList();
});

var initTmpls = function(){
	$('#templates [x-id]').each(function(){
		$.template($(this).attr('x-id'), this);
	});
}

var searchList = function(){
	$.getJSON('/Easybuy/product/hot?_format=json', {}, function(r){
		if(r.status == 'success'){
			$('#hotProduct').html('');
			$('#brand_name').append($.tmpl('brand_list',{brand_list:r.brand_list}));
			$('#hotProduct').append($.tmpl('list',{list:r.list}));
		}
	});
}
