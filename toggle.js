// toggling event visibility
// $Id$
// v1.0 David Dorward for the W3C CSS Validation Service, 18/12/2006
// Based on v2.01 Kent Brewster, 6/8/2006 http://www.mindsack.com

// namespace protection: one global variable to rule them all
var W3C = {};
W3C.QA = {};
W3C.QA.Validator = {};
W3C.QA.Validator.CSS = {};
W3C.QA.Validator.CSS.toggle = 
{	
	init : function(toggle, closed, hidden)
	{
		// three class names, fed in from init call
		this.toggleClass = toggle;
		this.toggleClosed = closed;
		this.toggleHidden = hidden;
		// crawl through the document, look for toggled elements
		this.crawl(document.body);
	},
	crawl : function(el)
	{
		// get this element's next sibling
		var nextSib = this.getNextSibling(el);
		
		// if it has a class name, the class name matches our toggle class
		if (el.className && el.className.match(this.toggleClass))
		{
			// labels are nice, but redundant with the link we are adding
		 	for (i=0;i<el.childNodes.length;i++)
		  	{
		    	  current_child_node=el.childNodes[i];
			  if (current_child_node.tagName)
			  {
			    if (current_child_node.tagName.toLowerCase() == "legend")
			    {
			      current_child_node.className += 'hideme';
			    }
			  }
			}

			// Generate a paragraph for the pseudo-link we're adding
			var paragraph = document.createElement('p');
			var link = document.createElement('a');
			//var text = el.className.match(/linkText_(\S*)/)[1];
			//text = text.replace("_", " ", "g");
			var text = "Show/Hide extra validation options";
			text = document.createTextNode(text);
			link.appendChild(text);
			link.href="#" + el.id;
			link.onclick = this.newToggleState(this,paragraph,el);
			paragraph.appendChild(link);
			el.parentNode.insertBefore(paragraph, el);


			// if the next sib ought to be hidden and it isn't already, hide it
			// you can hard-code class=hidden in the HTML if you like, and avoid the Flash of Unstyled Content
			if (el.className.match(this.toggleClosed))
			{
				el.className += ' ' + this.toggleHidden;
				//el.parentNode.className += ' ' + this.toggleClosed;
			}
		}
		
		// is there more to do? Do it, if so:
		if (el.firstChild) 
		{
    			this.crawl(el.firstChild);
  		}
		if (nextSib) 
		{
			this.crawl(nextSib);
		}
	},
	newToggleState : function(o,element,nextEl) {
		return function () { return o.toggleState(element,nextEl) };
	},
	toggleState : function(el,nextEl)
	{
		// there's got to be a way to get this without stating it explicitly
		var o = W3C.QA.Validator.CSS.toggle;
				
		// change the style of the triggering element
		if(el.className.match(o.toggleClosed))
		{
			el.className = el.className.replace(o.toggleClosed, '');
		}
		else
		{
			el.className = el.className + ' ' + o.toggleClosed;
		}
		
		// yes, we need to check if it's really there; other scripts could have removed it
		if(nextEl && nextEl.className.match('hidden'))
		{
			nextEl.className = nextEl.className.replace(o.toggleHidden, '');
		}
		else
		{
			nextEl.className += ' ' + o.toggleHidden;
		}
		return false;
	},
	getNextSibling : function(el)
	{
		var nextSib = el.nextSibling;
		
		// hack for Gecko browsers
		if (nextSib && nextSib.nodeType != 1)
		{
			nextSib = nextSib.nextSibling;
		}
		return nextSib;
	},
	getEl : function(v)
	{
		var tg = (v) ? v : event;
		var el = null;
		if (tg.target) 
		{
			el = (tg.target.nodeType == 3) ? tg.target.parentNode : tg.target;
		} 
		else 
		{ 
			el = tg.srcElement;
		}
		return el;
	}
};

// feed it the CSS class names of your choice
window.onload = function()
{ 
	W3C.QA.Validator.CSS.toggle.init('alttoggle', 'closed', 'hidden');
};
