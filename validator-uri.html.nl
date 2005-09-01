<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="nl" xml:lang="nl">
  <head>
    <meta lang="nl" name="Author" content="Philippe Le Hegaret" />
    <meta lang="nl" name="Translator" content="Sijtsche de Jong" />
    <title>W3C CSS Validatieservice</title>
    <link href="style/general.css" type="text/css" rel="stylesheet" />
    <meta name="ROBOTS" content="NOINDEX, NOFOLLOW" />
  </head>
  <body>
    <a class="left" href="http://www.w3.org"><img
	src="http://www.w3.org/Icons/w3c_home" border="0" alt="w3c" width="72"
	height="48" /></a>
    <a class="right" href="http://www.w3.org/Jigsaw/"><img
	  src="http://jigsaw.w3.org/Icons/jigpower.gif" alt="Jigsaw Powered"
	  border="0" width="94" height="52" /></a>
    <br /> 
    
    <div class="t1">CSS</div>
    <div class="t2">Validator</div>
    

    <h1>W3C CSS Validatieservice</h1>
    
    <p>
      Welkom bij de W3C CSS Validatieservice!
    </p>
    <hr />
    <h2>Valideer documenten via URL</h2>
     
    <p>
      Typ de URL van het document (HTML met CSS of enkel CSS)
      dat u wilt laten valideren:
    </p>
    
    <form action="validator" method="get">
      
      <input type="text" name="uri"
	size="60" /><br />
      
      Waarschuwingen :
      <select name="warning"> 
	<option selected="selected" value="2">Alle</option>
	<option selected="selected" value="1">Normale weergave</option>
	<option selected="selected" value="0">Alleen de belangrijkste</option>
	<option selected="selected" value="no">Geen waarschuwingen</option>
      </select><br />
      <!-- <input type="checkbox" name="error" value="no">Don't show errors -->
      
      Profiel :
      <select name="profile">
	<option value="none">Geen speciaal profiel</option>
	<option value="css1">CSS versie 1</option>
	<option selected="selected" value="css2">CSS versie 2</option>
	<option value="css21">CSS versie 2.1</option>
 	<option value="css3">CSS versie 3</option>
	<option value="svg">SVG</option>
	<option value="svgbasic">SVG basic</option>
	<option value="svgtiny">SVG tiny</option>
	<option value="mobile">mobiel</option>
	<option value="atsc-tv">ATSC TV profiel</option>
	<option value="tv">TV profiel</option>
      </select>

      Medium :
      <select name="usermedium">
	<option selected="selected" value="all">alle</option>
	<option value="aural">aural</option>
	<option value="braille">braille</option>
	<option value="embossed">embossed</option>
	<option value="handheld">handheld</option>
	<option value="print">print</option>
	<option value= "projection">projection</option>
	<option value="screen">screen</option>
	<option value="tty">tty</option>
	<option value="tv">tv</option>
	<option value="presentation">presentation</option>
      </select><br />      
      
      <br />
      <input type="submit" value="Verstuur deze URL voor validatie" />
      <input type="reset" name="Alles wissen" value="Wis dit formulier" />
    </form>
    
    <br />
    <br />
    <p>
		  Om alles naar behoren te laten werken dient uw CSS style sheet een correcte
		  grammaticale opbouw te hebben. Dit betekent dat u <a href="http://validator.w3.org/">
		  correct HTML</a> dient te gebruiken.
    </p>
    
    <hr />
    <address><a href="Email.html">www-validator-css</a></address><br />
    <!-- Created: Thu Jul 24 17:46:39 WET DST 1997 -->
  </body>
</html>




