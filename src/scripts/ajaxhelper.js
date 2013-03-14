function getXmlHttpRequest() {
	var xhr;
	
	if(typeof XMLHttpRequest !== 'undefined') xhr = new XMLHttpRequest();
	else {
		var versions = ["Microsoft.XmlHttp", 
						"MSXML2.XmlHttp",
						"MSXML2.XmlHttp.3.0", 
						"MSXML2.XmlHttp.4.0",
						"MSXML2.XmlHttp.5.0"];
		 
		 for(var i = 0, len = versions.length; i < len; i++) {
			try {
				xhr = new ActiveXObject(versions[i]);
				break;
			}
			catch(e){}
		 } // end for
	} 
	return xhr;
}

function ajax_get(url, callback) {
	var xhr = getXmlHttpRequest();
	xhr.onreadystatechange = ensureReadiness;
	
	function ensureReadiness() {
		if(xhr.readyState < 4) {
			return;
		}
		
		if(xhr.status !== 200) {
			return;
		}

		// all is well	
		if(xhr.readyState === 4) {
			callback(xhr);
		}			
	}
	
	xhr.open('GET', url, true);
	xhr.send('');
}

function ajax_post(url, data, callback) {
	var xhr = getXmlHttpRequest();
	xhr.onreadystatechange = ensureReadiness;
	
	function ensureReadiness() {
		if(xhr.readyState < 4) {
			return;
		}
		
		if(xhr.status !== 200) {
			return;
		}

		// all is well	
		if(xhr.readyState === 4) {
			callback(xhr);
		}			
	}
	
	xhr.open('POST', url, true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send(data);
}