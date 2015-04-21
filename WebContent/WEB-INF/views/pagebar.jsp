<!-- <div class="click-box box-8" onclick="gotopage(1, {{= pager.pageSize}});">First Page</div> -->
<div class="{{if pager.page == 1}}click-box-inactive{{else}}click-box{{/if}} box-12" onclick="{{if pager.page != 1}}gotopage({{= pager.previousPage}}, {{= pager.pageSize}});{{/if}}">&lt; Previous Page</div>
{{each(i,v) pages}}
	<div class="{{if v == pager.page}}click-box-active{{else}}click-box{{/if}} box-4 " onclick="gotopage({{= v}}, {{= pager.pageSize}});">{{= v}}</div>
{{/each}}
<div class="pagemore">...</div>
<div class="{{if pager.page == pager.totalPage}}click-box-inactive{{else}}click-box{{/if}} box-12" onclick="{{if pager.page != pager.totalPage}}gotopage({{= pager.nextPage}}, {{= pager.pageSize}});{{/if}}" >Next Page &gt;</div>
<!-- <div class="click-box box-8" onclick="gotopage({{= pager.totalPage}}, {{= pager.pageSize}});" >Last page</div> -->