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

function getPosition(n,endNode) {
    var left = 0;
    var top = 0;
    var node = n;
    
    done = false;
    while (!done) {
        if (node.offsetLeft != null)
            left += node.offsetLeft;
        if (node.offsetTop != null)
            top += node.offsetTop;
        if (node.offsetParent) {
            node = node.offsetParent;
        } else {
            done = true;
        }
        if(node == endNode)
            done = true;
    }
    
    done=false;
    node = n;
    while (!done) {
        if (document.all && node.style && parseInt(node.style.borderLeftWidth)){
            left += parseInt(node.style.borderLeftWidth);
        }
        if (document.all && node.style && parseInt(node.style.borderTopWidth)){
            top += parseInt(node.style.borderTopWidth);
        }

        if (node.scrollLeft) {
            left -= node.scrollLeft;
        }
        if (node.scrollTop)
            top -= node.scrollTop;
        if (node.parentNode)
            node = node.parentNode;
        else
            done=true;
    }
    return new Array(left, top);
}

function showDivFloatBelow(anchor, floatDiv) {
   var tmp = document.getElementById(floatDiv);
   var searchbox = document.getElementById(anchor); // add that id, not just class name to html
   var pos = getPosition(searchbox);

   tmp.style.left = pos[0] + "px";
   tmp.style.top = (pos[1] + searchbox.offsetHeight) + "px";
   tmp.style.visibility = "visible";
 }