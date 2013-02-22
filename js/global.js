/**
 * @author gmochid
 */

(function($) {
	$.$id = function(id) {
		var ret = document.getElementById(id);
		return ret;
	}
})(window);

function hasClass(x, y) {
	var classes = x.className.split(' ');
	return (classes.indexOf(y) != -1);
}

function addClass(x, y) {
	var classes = x.className.split(' ');
	if(classes.indexOf(y) != -1) {
		return;
	}
	if(x.className === '') {
		x.className += y;
	} else {
		x.className += ' ' + y;
	}
}

function deleteClass(x, y) {
	var classes = x.className.split(' ');
	var index = classes.indexOf(y);
	if(index != -1) {
		classes.splice(index, 1);
		x.className = classes.join(" ");
	}
}
