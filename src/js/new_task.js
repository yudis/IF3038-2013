/**
 * @author gmochid
 */

window.onload = function() {
	window.newtaskform = {
		nametask: $id("newtaskform-nametask"),
		attachment: $id("newtaskform-attachment"),
		deadline: $id("newtaskform-deadline"),
		asigneetext: $id("newtaskform-asigneetext"),
		tagtext: $id("newtaskform-tagtext")
	}
	
	window.newtaskform_error_message = {
		nametask: 'Nametask must be alphanumerical character max 25',
		attachment: '',
		deadline: 'Incorrect date format',
		asigneetext: 'Minimal 5 characters, only alphabetic, numeric and underscore allowed',
		tagtext: 'Each tag separated by comma'
	}
	
	window.newtaskform_validation = {};
	
	newtask_check();
	
	for(key in window.newtaskform) {
		window.newtaskform[key].onkeyup = function(e) {
			newtask_check();
			var key = $id(this.id).id.split('-')[1];
			if(!window.newtaskform_validation[key]) {
				if(!hasClass(window.newtaskform[key], 'error')) {
					
					var e = document.createElement('div');
					addClass(e, 'errorMessage');
					e.innerHTML = newtaskform_error_message[key];
			
					addClass(window.newtaskform[key], 'error');
					window.newtaskform[key].parentNode.appendChild(e);
				}
			} else if(hasClass(window.newtaskform[key], 'error')) {
				console.log(1);
				deleteClass(window.newtaskform[key], 'error');
				window.newtaskform[key].parentNode.removeChild(window.newtaskform[key].parentNode.lastChild);
			}
		}
	}
	
	$id("profilename").innerHTML = JSON.parse(window.localStorage['login_user']).fullname;
}

function newtask_check() {
	var regex_nametask = /^[A-Za-z0-9]{1,25}$/;
	var regex_deadline = /^\d{4}\-\d{2}\-\d{2}$/;
	var regex_asigneetext = /^[A-Za-z0-9_]{5,}$/;
	
	window.newtaskform_validation['nametask'] = regex_nametask.test($id("newtaskform-nametask").value); 
	window.newtaskform_validation['attachment'] = true;
	window.newtaskform_validation['deadline'] = regex_deadline.test(window.newtaskform.deadline.value);
	window.newtaskform_validation['asigneetext'] = regex_asigneetext.test(window.newtaskform.asigneetext.value); 
	window.newtaskform_validation['tagtext'] = true;
	
	for (key in window.newtaskform_validation) {
	  if(!window.newtaskform_validation[key]) {
	  	$id("newtaskform-submit").disabled = true;
	  	return;
	  }
	}
	$id("newtaskform-submit").disabled = false;
}
