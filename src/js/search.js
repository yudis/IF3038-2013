var isLoading = {'user': false, 'task': false, 'category': false};

Rp(function() {
	window.loading = false;

	var pageCount = {
		'user': 1,
		'task': 1,
		'category': 1
	};

	getScrollTop = function() {
		return (window.pageYOffset !== undefined) ?
			window.pageYOffset :
			(document.documentElement || document.body.parentNode || document.body).scrollTop;
	}

	loadPartial = function(typ) {
		if (isLoading[typ])
			return;

		isLoading[typ] = true;

		page = pageCount[typ] + 1;
		var req = Rp.ajaxRequest('search_partial.php?q=' + q+ '&type=' + typ + '&page=' + page);
		req.onreadystatechange = function() {
			console.log(typ);
			list = document.getElementById(typ + 'List');
			if (req.readyState == 4) {
				if (req.responseText) {
					pageCount[typ]++;
					list.innerHTML += req.responseText;
				}
				list.className = '';
				isLoading[typ] = false;
			}
			else {
				list.className = 'loading';
			}
		}
		loading = true;
		req.post();
	}

	var lastScroll;

	window.onscroll = function(e) {
		// Only when scrolling downwards
		if (lastScroll < window.scrollY) {
			if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
				// We're at bottom
				if (isAll) {
					loadPartial('task');
					loadPartial('user');
					loadPartial('category');
				}
				else {
					loadPartial(currentType);
				}
			}
		}
		lastScroll = window.scrollY;
	}
});

/*----- Bagian Search ----*/
/*
var search_text = document.getElementById("search_text");

search_text.onmouseover = function()
{
	this.focus();	
}
search_text.onfocus = function()
{
	var search = document.getElementById("search");
	search.className = "active";
}
search_text.onblur = function()
{
	if (search_text.value==="")
	{
		var search = document.getElementById("search");
		search.className = "";
	}
}
*/