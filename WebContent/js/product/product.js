var lastOption = {};

$(document.body).ready(function(){
	initTmpls();
	$('#add2cart').click(function(){
		add2cart();
	});
	$('#add2wishlist').click(function(){
		add2wishlist();
	});
	searchList();
});

var initTmpls = function(){
	$('#templates [x-id]').each(function(){
		$.template($(this).attr('x-id'), this);
	});
}


var searchList = function(){
	lastOption.product_id = $('#product_id').val();
	$.getJSON('/Easybuy/product/review/list?_format=json', lastOption, function(r){
		if(r.status == 'success'){
			if(r.list == null || r.list.length == 0){
				$('#reviewList').html('<div class="message">no comments.</div>');
			}
			else{
				$('#reviewList').html('');
				$('#reviewList').append($.tmpl('list',{list:r.list}));
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

var add2cart = function(){
	
	var option = {};
	option.product_id = $('#product_id').val();
	$.getJSON('/Easybuy/shopcart/addProduct?_format=json', option, function(r){
		if(r.status == 'success'){
			$('#message').html('added to cart');
		}
	});
}

var add2wishlist = function(){
	var option = {};
	option.product_id = $('#product_id').val();
	$.getJSON('/Easybuy/wishlist/addProduct?_format=json', option, function(r){
		if(r.status == 'success'){
			$('#message').attr("class","message1");
			$('#message').html('added to wishlist');
		}
	});
}