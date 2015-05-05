var lastOption ={};

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
	lastOption.brand_name = $('#brand_name').val();
	lastOption.content = $('#content').val();
	lastOption.sortBy = $('input[type="radio"][name="sortBy"]:checked').val();
	$.getJSON('/Easybuy/product/searchProduct?_format=json', lastOption, function(r){
		if(r.status == 'success'){
			$('#productArea').html('');
			$('#productArea').append($.tmpl('list',{list:r.list}));
			var pageinfo = {
					pager: r.pager,
					pages: []
				};
				for(var i = pageinfo.pager.page - 5; i < pageinfo.pager.page + 5; i++){
					if(i >= 1 && i <= pageinfo.pager.totalPage){
						pageinfo.pages.push(i);
					}
				}
				$('#pagebar').empty().append($.tmpl('pagebar', pageinfo));
		}
	});
}

var gotopage = function(page, pageSize){
	if(page != null){
		lastOption.page = page;
	}
	if(pageSize != null){
		lastOption.pageSize = pageSize;
	}
	searchList();
}