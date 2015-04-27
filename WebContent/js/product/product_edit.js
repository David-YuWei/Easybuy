var lastOption = {};

$(document.body).ready(function(){
	initTmpls();
});

var initTmpls = function(){
	$('#templates [x-id]').each(function(){
		$.template($(this).attr('x-id'), this);
	});
}


var add2Cart = function(){
	lastOption.product_id = $('#product_id').val();
	$.getJSON('/Easybuy/shopcart/addProduct?_format=json', lastOption, function(r){
		if(r.status == 'success'){
			$('#message').html('added to cart');
		}
	});
}

var add2Wishlist = function(){
	lastOption.product_id = $('#product_id').val();
	$.getJSON('/Easybuy/wishlist/addProduct?_format=json', lastOption, function(r){
		if(r.status == 'success'){
			$('#message').html('added to wishlist');
		}
	});
}