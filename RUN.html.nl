<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
  <head>
    <title>CSS Validator Project</title>

    <link href="style/page.css" rel="STYLESHEET">
    <meta name="ROBOTS" content="NOINDEX, NOFOLLOW">
    <LINK REL="STYLESHEET" TITLE="default" MEDIA="screen" HREF="style/general.css" TYPE="text/css">
		<META name="ROBOTS" content="NOINDEX, NOFOLLOW">
		<meta lang="nl" name="Translator" content="Sijtsche de Jong">
		
</head>
  <body>
    <a class="left" href="http://www.w3.org"><IMG SRC="http://www.w3.org/Icons/w3c_home" BORDER="0" ALT="w3c"></a>
    <a class="right" href="http://www.w3.org/Jigsaw/"><IMG
	  SRC="http://jigsaw.w3.org/Icons/jigpower.gif" ALT="Jigsaw Powered"
	  BORDER="0" WIDTH="94" HEIGHT="52"></a>
    <br>

    <div class="t1">CSS</div>
    <div class="t2">Validator</div>

    <h1>CSS Validator versie 2.0 : UITVOEREN</h1>
    
    <h2>Command line versie</h2>

    <P>
      De eenvoudigste manier is als volgt:
    </P>
    <OL>
	<li>
	Vind een Java interpreter en installeer deze. Op Jigsaw is Java versie 1.1
	vereist. U kunt Sun's Java interpreter gratis vinden op
	<a 	href="http://www.javasoft.com">http://www.javasoft.com</a>
	
      <li>
	Zet de locatie van de classes van de validator in uw CLASSPATH omgevingsvariabele.
	Om dit te doen moet u:
	<dl>
	   <dt>In Windows
	  <dd>set CLASSPATH=&lt;installatiedir>\validator.zip
	  <dt>In UNIX
	  <dd>export CLASSPATH=&lt;installatiedir>/validator.zip
	</dl>
	Hier moet &lt;instdir> worden vervangen met de volledige padnaam van de
	directory waarin u de uitgepakte validator hebt geplaatst.
		
      <li>
	Controleer of uw PATH instelling het u mogelijk maakt de Java
	interpreter te gebruiken. Dit is gewoonlijk het geval als u 
	Sun's JDK hebt geinstalleerd.
	
      <li>
	Voer de validator uit:
	<p>
	  java org.w3c.css.css.StyleSheetCom &lt uw-bestand-of-URL>
	</p>
	<p>
	  Als u de validator zonder argumenten uitvoert geeft het u alle
	  te gebruiken argumenten voor de commandline. Hier zijn een aantal voorbeelden:
	  	</p>
    <pre>
java org.w3c.css.css.StyleSheetCom http://www.w3.org/
of
java org.w3c.css.css.StyleSheetCom http://style.verso.com/stylebot.html?family=1&amp;doc=url4
    </pre>

    </ol>
    <h2>Servlet version</h2>

    <p>
      De servlet class is <a
      href="org/w3c/css/servlet/CssValidator.java">org.w3c.css.servlet.CssValidator</a>. 
      Als u van plan bent Jigsaw te gebruiken, zie dan de <a
      href="http://www.w3.org/Jigsaw/Doc/Reference/org.w3c.jigsaw.servlet.ServletWrapper.html">ServletWrapper</a>
      documentatie.
    </P>
    <hr>
    <img src="images/mwcss.gif" alt="made with CSS">
    <address><a href="mailto:Philippe.Le_Hegaret@sophia.inria.fr">
	Philippe Le H&eacute;garet</a></address>
  </body>
</html>
