# Note that we will filter \u005c in simple \ as some IDEs have trouble with this.
CssParser.java: CssParser.jj
	javacc CssParser.jj
	cat CssParser.java | sed 's/u005c//g' > C && mv -f C CssParser.java
