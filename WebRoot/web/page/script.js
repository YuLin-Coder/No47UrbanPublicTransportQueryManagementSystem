// JavaScript DocumentDlHighlight.HELPERS.highlightByName("code", "pre");

$('#light-pagination').pagination({
	pages: 20,
	cssStyle: 'light-theme',
	displayedPages: 1,
	edges: 3,
	onPageClick:function(pageNumber, event){alert("haha: "+pageNumber);}
});

$('#dark-pagination').pagination({
	pages: 7,
	cssStyle: 'dark-theme',
	displayedPages: 1,
	edges: 3,
	onPageClick:function(pageNumber, event){alert("haha: "+pageNumber);}
});

$('#compact-pagination').pagination({
	pages: 70,
	cssStyle: 'compact-theme',
	displayedPages: 7
});