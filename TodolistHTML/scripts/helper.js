String.prototype.trim = function(){return this.replace(/^\s+|\s+$/g, '');};

// create function, it expects 2 values.
// http://snipplr.com/view/2107
function insertAfter(newElement,targetElement) {
    //target is what you want it to go after. Look for this elements parent.
    var parent = targetElement.parentNode;
	
    //if the parents lastchild is the targetElement...
    if(parent.lastchild == targetElement) {
        //add the newElement after the target element.
        parent.appendChild(newElement);
    } else {
        // else the target has siblings, insert the new element between the target and it's next sibling.
        parent.insertBefore(newElement, targetElement.nextSibling);
    }
}

// http://www.thimbleopensource.com/tutorials-snippets/get-request-parameters-using-javascript
function getQueryParameter(parameterName) {
    var queryString = window.top.location.search.substring(1);
    var param =  parameterName + "=";
    if (queryString.length > 0) {
        var begin = queryString.indexOf(param);
        if (begin != -1) {
            begin +=  param.length;
            var end = queryString.indexOf("&" , begin);
            if (end == -1) {
                end = queryString.length
            }
            return unescape(queryString.substring(begin, end));
        }
    }
    return null;
}
