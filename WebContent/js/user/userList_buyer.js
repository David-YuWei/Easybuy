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
	$.getJSON('/Easybuy/user/buyer/list?_format=json', lastOption, function(r){
		if(r.status == 'success'){
			$('#list-table').find('> tbody > tr:gt(0)').remove();
			$('#list-table').append($.tmpl('list',{list:r.list}));
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

var create = function(){
	edit();
}

var del = function(user_name){
	if(confirm('are you sure you want to delete this user?')){
		$.post('/Easybuy/user/buyer/delete.json?_decode=UTF-8', {
			user_name: user_name
		}, function(r){
			if(r.status == 'success'){
				searchList();
			}
		}, 'json');
	}
}