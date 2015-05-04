var lastOption = {};

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
	lastOption.product_id = $('#product_id').val();
	$.getJSON('/Easybuy/product/reviewList?_format=json', lastOption, function(r){
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
	$.post('/Easybuy/product/add2cart.json?_decode=UTF-8', {
		product_id: $('#product_id').val()
	}, function(r){
		if(r.redirect){
			window.location.replace("/Easybuy"+r.redirect);
		}
		else if(r.status == 'success'){
			$('#message').attr("class","message");
			$('#message').html('added to cart');
		}
	}, 'json');
}

var add2wishlist = function(){
	$.post('/Easybuy/product/add2wishlist.json?_decode=UTF-8', {
		product_id: $('#product_id').val()
	}, function(r){
		if(r.redirect){
			window.location.replace("/Easybuy"+r.redirect);
		}
		else if(r.status == 'success'){
			$('#message').attr("class","message1");
			$('#message').html('added to wishlist');
		}
	}, 'json');
}