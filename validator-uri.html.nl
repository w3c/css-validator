<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "http://www.w3.org/TR/REC-html40/loose.dtd">
<html lang="nl">
  <head>
    <meta lang="nl" name="Author" content="Philippe Le Hegaret">
    <meta lang="nl" name="Translator" content="Sijtsche de Jong">
    <title>W3C CSS Validatieservice</title>
    <link href="style/general.css" type="text/css" rel="STYLESHEET">
    <meta name="ROBOTS" content="NOINDEX, NOFOLLOW">
  </head>
  <body bgcolor="#FFFFFF" text="#000000">
    <a class="left" href="http://www.w3.org"><IMG
	SRC="http://www.w3.org/Icons/w3c_home" BORDER="0" ALT="w3c" WIDTH="72"
	HEIGHT="48"></A>
    <a class="right" href="http://www.w3.org/Jigsaw/"><IMG
	  SRC="http://jigsaw.w3.org/Icons/jigpower.gif" ALT="Jigsaw Powered"
	  BORDER="0" WIDTH="94" HEIGHT="52"></a>
    <br>
    
    <div class="t1">CSS</div>
    <div class="t2">Validator</div>
    

    <h1>W3C CSS Validatieservice</h1>
    
    <p>
      Welkom bij de W3C CSS Validatieservice!
    </p>
    <hr>
    <h2>Valideer documenten via URL</h2>
     
    <p>
      Typ de URL van het document (HTML met CSS of enkel CSS)
      dat u wilt laten valideren:
    </p>
    
    <form action="validator" method="get">
      
      <input type="text" name="uri"
	size="60"><br>
      
      Waarschuwingen :
      <select name="warning"> 
	<option selected="selected" value="2">Alle
	<option selected="selected" value="1">Normale weergave
	<option selected="selected" value="0">Alleen de belangrijkste
	<option selected="selected" value="no">Geen waarschuwingen</option>
      </select><br>
      <!-- <input type="checkbox" name="error" value="no">Don't show errors -->
      
      <br>
      <input type="submit" value="Verstuur deze URL voor validatie">
      <input type="reset" name="Alles wissen" value="Wis dit formulier">
    </form>
    
    <br>
    <br>
    <p>
		  Om alles naar behoren te laten werken dient uw CSS style sheet een correcte
		  grammaticale opbouw te hebben. Dit betekent dat u <a href="http://validator.w3.org/">
		  correct HTML</a>.
    </p>
    
    <hr>
    <address class="right"><a href="mailto:plh@w3.org">Philippe Le
	H&eacute;garet</A></address>
    <!-- Created: Thu Jul 24 17:46:39 WET DST 1997 -->
  </body>
</html>




