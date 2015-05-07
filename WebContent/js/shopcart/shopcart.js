var subtotal = 0;

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
	$.getJSON('/Easybuy/shopcart/list?_format=json',{}, function(r){
		if(r.status == 'success'){
			$('#shopcart').html('');
			if(r.list !=null){
				$('#shopcart').append($.tmpl('list',{list:r.list}));
				subtotal = toDecimal2(r.subtotal);
				$('#subtotal').html("$ "+ subtotal);
				$('#subtotal-label').html("Subtotal ("+r.list.length+" items):");
			}
			else{
				$('#shopcart').append('<div style="margin-left:30px;margin-top:20px;">no product in this cart</div>');
				$('#checkout-area').remove();
			}
				
		}
	});
}

var del = function(product_id){
	if(confirm('are you sure you want to delete this data?')){
		$.post('/Easybuy/shopcart/deleteItem.json?_decode=UTF-8', {
			product_id: product_id
		}, function(r){
			if(r.status == 'success'){
				searchList();
			}
		}, 'json');
	}
}


var add2wishlist = function(product_id){
	option = {};
	option.product_id = product_id;
	$.getJSON('/Easybuy/product/add2wishlist?_format=json', option, function(r){
		if(r.redirect){
			window.location.replace("/Easybuy"+r.url);
		}
		else if(r.status == 'success'){
			$('#message_'+product_id).html('added to wishlist');
		}
	});
}

var checkout = function(){
	window.location.replace("/Easybuy/shopcart/checkout");
}

var calculateandsave = function(e,product_id){
	thisNode = $(e.target);
    productNode = $(e.target).parent().parent();
	quantity = $(e.target).val();
    old = productNode.children('#old').val();
    price = productNode.children('#price').val();
	if(quantity ==''){
		quantity = 1;
	}
	thisNode.val(quantity);
	if(old != quantity){
		option = {};
		option.product_id = product_id;
		option.quantity = quantity;
		$.getJSON('/Easybuy/shopcart/updateQuantity?_format=json', option, function(r){
			if(r.status == 'success'){
				difference = (quantity - old) * price;
				subtotal = toDecimal(subtotal) + difference;
				thisNode.val(quantity);
				productNode.children('#old').val(quantity);
				$('#subtotal').html("$ "+ toDecimal2(subtotal));
				productNode.children('#total').html("$ "+ toDecimal2(quantity * price));
			}
			else{
				thisNode.val(old);
			}
		});
	}
}

function toDecimal(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return;  
    }  
    f = Math.round(x*100)/100;  
    return f;  
}  
  
function toDecimal2(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return false;  
    }  
    var f = Math.round(x*100)/100;  
    var s = f.toString();  
    var rs = s.indexOf('.');  
    if (rs < 0) {  
        rs = s.length;  
        s += '.';  
    }  
    while (s.length <= rs + 2) {  
        s += '0';  
    }  
    return s;  
} 
