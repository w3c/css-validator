// toggling event visibility
// v2.01 Kent Brewster, 6/8/2006
// questions? comments? please visit:
// http://www.mindsack.com

// namespace protection: one global variable to rule them all
var MINDSACK = {};
MINDSACK.uxe = {};
MINDSACK.uxe.toggle = 
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
		
		// if it has a class name, the class name matches our toggle class, and there's something there to toggle:
		if (el.className && el.className.match(this.toggleClass) && nextSib)
		{
			// attach onmouseup to the toggle function
			el.onmouseup = this.toggleState;
			
			// if the next sib ought to be hidden and it isn't already, hide it
			// you can hard-code class=hidden in the HTML if you like, and avoid the Flash of Unstyled Content
			if (el.className.match(this.toggleClosed) && nextSib && !nextSib.className.match(this.toggleHidden))
			{
				nextSib.className += ' ' + this.toggleHidden;
				el.parentNode.className += ' ' + this.toggleClosed;
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
	toggleState : function(v)
	{
		// there's got to be a way to get this without stating it explicitly
		var o = MINDSACK.uxe.toggle;
		
		// v is the event; we don't need to pass this in when calling!
		// what element did we click?
		var el = o.getEl(v);
		
		// change the style of the triggering element
		if(el.className.match(o.toggleClosed))
		{
			el.className = el.className.replace(o.toggleClosed, '');
		}
		else
		{
			el.className = el.className + ' ' + o.toggleClosed;
		}
		var p = el.parentNode;
		if(p.className.match(o.toggleClosed))
		{
			p.className = p.className.replace(o.toggleClosed, '');
		}
		else
		{
			p.className = p.className + ' ' + o.toggleClosed;
		}
		
		// change the style of the parent node's next block-level element
		var nextSib = o.getNextSibling(el);
		
		// yes, we need to check if it's really there; other scripts could have removed it
		if(nextSib && nextSib.className.match('hidden'))
		{
			nextSib.className = nextSib.className.replace(o.toggleHidden, '');
		}
		else
		{
			nextSib.className += ' ' + o.toggleHidden;
		}
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
	MINDSACK.uxe.toggle.init('toggle', 'closed', 'hidden');
};

