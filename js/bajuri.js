// BAJURI
// BukAn JqUeRI
// Sebuah pustaka Javascript yang mengimitasi jQuery, tapi bukan
// All of this was created through reverse engineering with some Stack Overflow help,
// but no looking at jQuery code at all!

bajuri = function(selector, context) {
	return new bajuri.fn.init(selector, context);
};

bajuri.fn =
bajuri.prototype = {
	nodes: [],

	getElements: function() {
		return this;
	},

	init: function(selector, context) {
		this.nodes = [];
		this.add(selector, context);

		return this;
	},

	add: function(selector, context) {
		if (typeof selector == 'function') {
			// http://stackoverflow.com/questions/799981/document-ready-equivalent-without-jquery
			if (document.addEventListener)
				document.addEventListener('DOMContentLoaded', selector);
			else if (document.attachEvent) {
				document.attachEvent('onreadystatechange', function() {
					if (document.readyState == 'complete') {
						document.detachEvent('onreadystatechange', arguments.callee);
						selector();
					}
				});
			}
		}
		else if (selector instanceof HTMLElement) {
			this.nodes.push(selector);
		}
		else if (selector instanceof bajuri) {
			for (node in selector.nodes) {
				this.nodes.push(node);
			}
		}
		else if (document.querySelectorAll && typeof selector == 'string') {
			n = document.querySelectorAll(selector);
			for (i = 0; i < n.length; i++) {
				this.nodes.push(n.item(i));
			}
		}
		else if (m = selector.match(/^#(.*)$/)) {
			this.nodes.push(document.getElementById(m[1]));
		}
		else if (m = selector.match(/^([a-zA-Z\-]*)(?:.([a-zA-Z_\-]+)|)?$/)) {
			tagName = m[1] ? m[1] : '*';
			className = m[2];
			elements = document.getElementsByTagName(selector);
			for (i = 0; i < elements.length; i++) {
				if (className && context)
				this.nodes.push(elements.item(i));
			}
		}

		return this;
	},

	hasClass: function(cls) {
		pattern = new RegExp(cls +' | ' + cls + '|^' + cls + '$');
		return this.nodes[0].className.match(pattern);
	},

	toggleClass: function(cls) {
		if (this.hasClass(cls))
			this.removeClass(cls);
		else
			this.addClass(cls);
	},

	addClass: function(cls) {
		this.nodes.forEach(function(node) {
			bajuri._addClassToNode(node, cls);
		});
	},

	removeClass: function(cls) {
		this.nodes.forEach(function(node) {
			pattern = new RegExp(cls +' | ' + cls + '|^' + cls + '$');
			while (node.className.match(pattern)) {
				node.className = node.className.replace(pattern, ' ');
			}
		});
	},

	attr: function(a, b) {
		if (b !== undefined) {
			this.nodes.forEach(function(node) {
				node.setAttribute(a, b);
			});

			return this;
		}
		else {
			return this.nodes[0].getAttribute(a);
		}
	},

	hasAttribute: function(a) {
		return this.nodes[0].getAttribute(a) && true;
	},

	removeAttribute: function(a) {
		this.nodes.forEach(function(node) {
			node.removeAttribute(a);
		});
	},

	first: function() {
		return bajuri([this.nodes[0]]);
	},

	on: function(e, callback) {
		this.nodes.forEach(function(node) {
			if (node) {
				if (node.addEventListener)
					node.addEventListener(e, callback);
				else if (node.attachEvent)
					node.attachEvent(e, callback);
			}
		});
	},

	val: function() {
		return this.nodes[0].value;
	},

	text: function() {
		el = this.nodes[0];
		if (el) {
			if (el.textContent)
				return el.textContent;
			else if (el.innerText)
				return el.innerText;
		}
	},

	append: function(content) {
		this.each(function(node) {
			if (content instanceof HTMLElement)
				node.appendChild(content);
			else if (content instanceof bajuri)
				content.forEach(function(subnode) {
					node.appendChild(subnode);
				});
		});
	},

	each: function(callback) {
		this.nodes.forEach(function(node) {
			callback.call(node);
		});
	},

	data: function(key, value) {
		if (value !== undefined) {
			if (typeof bajuri.cache[this.nodes[0]] !== 'object')
				bajuri.cache[this.nodes[0]] = {};

			bajuri.cache[this.nodes[0]][key] = value;
			return this;
		}
		else {
			if (bajuri.cache[this.nodes[0]])
				return bajuri.cache[this.nodes[0]][key];
			else
				return undefined;
		}
	},

	show: function() {
		this.each(function() {
			if (this.style.display === 'none')
				this.style.display = Rp(this).data('formerDisplayValue') ? Rp(this).data('formerDisplayValue') : 'block';
		});

		return this;
	},

	hide: function() {
		this.each(function() {
			currentDisplay = this.style.display;
			Rp(this).data('formerDisplayValue', currentDisplay);
			this.style.display = 'none';
		});

		return this;
	}
}

bajuri.cache = {};

// Split classes from an element's class attribute, case-insensitively
bajuri._parseClasses = function(className) {
	return className.toLowerCase().split(/\s+/);
};

bajuri._addClassToNode = function(node, className) {
	// case insensitivity
	// className = className.toLowerCase();

	// found = node.className.toLowerCase().match(new RegExp('(?:^|\\s)' + className + '(?:\\s|$)'));

	// console.log(found);

	// if (!found)
		node.className += ' ' + className;
};

bajuri._nodeHasClass = function(node, className) {
	// case insensitivity
	className = className.toLowerCase();

	// split classes
	classes = this._parseClasses(classes);
	found = false;
	for (cls in classes) {
		if (cls === className.toLowerCase()) {
			return true;
		}
	}
};

bajuri.factory = function(tagName) {
	return bajuri(document.createElement(tagName));
}

bajuri.fn.init.prototype = bajuri.fn;

Rp = bajuri;