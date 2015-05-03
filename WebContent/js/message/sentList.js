var lastOption ={};

$(document.body).ready(function(){
	initTmpls();
	searchSentList();
});

var initTmpls = function(){
	$('#templates [x-id]').each(function(){
		$.template($(this).attr('x-id'), this);
	});
}
var searchSentList = function(){
	
	$.getJSON('/Easybuy/message/sentList?_format=json', {}, function(r){
		if(r.status == 'success'){
			$('#list-table').find('> tbody > tr:gt(0)').remove();
			$('#list-table').append($.tmpl('sentMessages',{list:r.list}));
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
	searchSentList(lastOption);
}


