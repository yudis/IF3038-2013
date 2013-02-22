// BAJURI
// BukAn JqUeRI
// Sebuah pustaka Javascript yang mengimitasi jQuery, tapi bukan

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
		this.add(selector, context);

		return this;
	},

	// Split classes from an element's class attribute, case-insensitively
	_parseClasses: function(className) {
		return className.toLowerCase().split(/\s+/);
	},

	_addClassToNode: function(node, className) {
		// case insensitivity
		className = className.toLowerCase();

		// split classes
		classes = this._parseClasses(classes);
		found = false;
		for (cls in classes) {
			if (cls === className.toLowerCase()) {
				found = true;
				break;
			}
		}

		if (!found)
			classes.push(className);
	},

	_nodeHasClass: function(node, className) {
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
	},

	add: function(selector, context) {
		if (selector instanceof HTMLElement) {
			this.nodes.push(selector);
		}
		else if (m = selector.match(/^#(^ )$/)) {
			this.nodes.push(document.getElementById(m[1]));
		}
		else if (m = selector.match(/^([a-zA-Z\-]*)(?:.([a-zA-Z_\-]+)|)?$/)) {
			tagName = m[1] ? m[1] : '*';
			className = m[2];
			elements = document.getElementsByTagName(selector);
			for (i = 0; i < elements.length; i++) {
				this.nodes.push(elements.item(i));
			}
		}

		return this;
	}
}

bajuri.fn.init.prototype = bajuri.fn;

Rp = bajuri;