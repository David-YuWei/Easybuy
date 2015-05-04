var shippingCost = 0;

$(document.body).ready(function(){
	initTmpls();
});

var initTmpls = function(){
	$('#templates [x-id]').each(function(){
		$.template($(this).attr('x-id'), this);
	});
}


var calculate = function(){
	option = $('input[type="radio"][name="options"]:checked').val();
	if(option == '1'){
		shippingCost = 3;
	}
	else if(option == '2'){
		shippingCost = 10;
	}
	else{
		shippingCost = 0;
	}
	$('#shippingCost').html(toDecimal2(shippingCost));
	subtotal = toDecimal($('#subtotal').html());
	tax = toDecimal($('#tax').html());
	total = toDecimal2(tax + subtotal + toDecimal(shippingCost));
	$('#total').html("$ "+ total);
}


var updateQuantity = function(e){
	thisNode = $(e.target);
    productNode = $(e.target).parent().parent();
	quantity = $(e.target).val();
    price = productNode.children('#price').val();
	if(quantity ==''){
		quantity = 1;
	}
	thisNode.val(quantity);
	subtotal = toDecimal(price * quantity);
	tax = toDecimal($('#tax').html());
	shipppingCost = toDecimal($('#shippingCost').html());
	total = toDecimal2(tax + subtotal + shippingCost);
	$('#subtotal').html(toDecimal2(subtotal));
	$('#total').html("$ "+ total);
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
