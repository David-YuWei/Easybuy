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

var searchList = function(option){
	$.getJSON('/Easybuy/order/list?_format=json',option, function(r){
		if(r.status == 'success'){
			$('#orders').html('');
			$('#orders').append($.tmpl('list',{list:r.list}));
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


var delOrder = function(order_id){
	if(confirm('are you sure you want to delete this order?')){
		$.post('/Easybuy/order/deleteOrder.json?_decode=UTF-8', {
			order_id: order_id
		}, function(r){
			if(r.status == 'success'){
				searchList();
			}
		}, 'json');
	}
}

var delItem = function(product_id,order_id){
	if(confirm('are you sure you want to delete this product ?')){
		$.post('/Easybuy/order/deleteItem.json?_decode=UTF-8', {
			product_id: product_id,
			order_id:order_id
		}, function(r){
			if(r.status == 'success'){
				searchList();
			}
		}, 'json');
	}
}


var add2cart = function(product_id,order_id){
	option = {};
	option.product_id = product_id;
	$.getJSON('/Easybuy/product/add2cart?_format=json', option, function(r){
		if(r.redirect){
			window.location.replace("/Easybuy"+r.url);
		}
		else if(r.status == 'success'){
			$('#message_'+order_id+'_'+product_id).html('added to cart');
		}
	});
}

var shipItem = function(product_id,order_id){
	option = {};
	option.product_id = product_id;
	option.order_id = order_id;
	$.getJSON('/Easybuy/order/shipItem?_format=json', option, function(r){
		if(r.status == 'success'){
			searchList();
		}
	});
}

