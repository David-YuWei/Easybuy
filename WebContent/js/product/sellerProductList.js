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

var searchList = function(option){
	$.getJSON('/Easybuy/product/sellerProducts/list?_format=json', option, function(r){
		if(r.status == 'success'){
			$('#productList').html('');
			$('#productList').append($.tmpl('list',{list:r.list}));
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
	searchList(lastOption);
}

var del = function(product_id){
	if(confirm('are you sure you want to delete this data?')){
		$.post('/Easybuy/product/delete.json?_decode=UTF-8', {
			product_id: product_id
		}, function(r){
			if(r.status == 'success'){
				searchList();
			}
		}, 'json');
	}
}