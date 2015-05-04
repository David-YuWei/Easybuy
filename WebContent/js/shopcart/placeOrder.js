var subtotal = 0;
var tax = 0;
var total = 0;
var shippingCost = 0;

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
			$('#itemList').html('');
			$('#itemList').append($.tmpl('list',{list:r.list}));
			subtotal = toDecimal(r.subtotal);
			tax = toDecimal(subtotal * 0.0824);
			total = toDecimal2(tax + subtotal);
			$('#subtotal').html("$ "+ toDecimal2(r.subtotal));
			$('#price').val(subtotal);
			$('#tax').html("$ "+ toDecimal2(tax));
			$('#tax_input').val(toDecimal2(tax));
			$('#shippingCost').html("$ "+ toDecimal2(shippingCost));
			$('#total').html("$ "+ total);
		}
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
	$('#shippingCost').html("$ "+ toDecimal2(shippingCost));
	total = toDecimal2(tax + subtotal + toDecimal(shippingCost));
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
