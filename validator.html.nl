<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="nl" lang="nl">
  <head>
    <title>De W3C CSS Validatie Service</title>
    <link rev="made" href="mailto:www-validator-css@w3.org" />
    <link rev="start" href="./" title="Home Pagina" />
    <style type="text/css" media="all">@import "style/base.css";
</style>
    <script type="text/javascript" src="tabtastic/addclasskillclass.js"></script>
    <script type="text/javascript" src="tabtastic/attachevent.js"></script>
    <script type="text/javascript" src="tabtastic/addcss.js"></script>
    <script type="text/javascript" src="tabtastic/tabtastic.js"></script>
    <script src="toggle.js" type="text/javascript"></script>
    <meta name="revision" content="$Id$" />
  </head>

  <body>
   <div id="banner">
    <h1 id="title"><a href="http://www.w3.org/"><img height="48" alt="W3C" id="logo" src="http://www.w3.org/Icons/WWW/w3c_home_nb" /></a>
    <img src="images/css_validation_service.png" alt="CSS Validation Service" /></h1>
   </div>
      <p id="tagline">
        Controleer Cascading Style Sheets (CSS) en (X)HTML documenten die gebruik maken van style sheets
      </p>
	<div id="frontforms">
      <ul class="tabset_tabs"><li><a href="#validate-by-uri" class="active">via een URI</a></li><li><a href="#validate-by-upload">via het uploaden van een bestand</a></li><li><a href="#validate-by-input">via directe invoer</a></li></ul>
    		    <div id="fields">

      <fieldset id="validate-by-uri" class="tabset_content front"><legend class="tabset_label">Validate via een URI</legend>
      <form method="get" action="validator">
      <p class="instructions">
	Geef de URI op van een document (HTML met CSS of alleen CSS) die je wilt valideren:     
      </p>
      <p>
	<label title="Address of page to Validate" for="uri">Adres:
	<input type="text" name="uri" id="uri" size="45" /></label>
      </p>
      <fieldset id="extra_opt_uri" class="moreoptions">
      <p class="toggle closed" title="Weergeven/Verbergen van extra validatie opties">Meer Opties:</p>
      <div>
      Waarschuwingen :
      <select name="warning"> 
	<option value="2">Alle</option>
	<option selected="selected" value="1">Normaal</option>
	<option value="0">Meest belangrijk</option>
	<option value="no">Geen</option>
      </select>
      
      Profiel :
      <select name="profile">
	<option value="none">Geen speciaal profiel</option>
	<option value="css1">CSS versie 1</option>
	<option value="css2">CSS versie 2</option>
	<option selected="selected" value="css21">CSS versie 2.1</option>
 	<option value="css3">CSS versie 3</option>
	<option value="svg">SVG</option>
	<option value="svgbasic">SVG Basic</option>
	<option value="svgtiny">SVG Tiny</option>	
	<option value="mobile">mobile</option>
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
      </select>
</div><!-- item_contents -->
</fieldset><!-- invisible -->
	<p class="submit_button"><label title="Verzenden om te valideren"><input type="submit" value="Controleer" /></label></p>
      </form>
      </fieldset>

      <fieldset id="validate-by-upload"  class="tabset_content front"><legend class="tabset_label">Valideer via het uploaden van een bestand</legend>
      <form method="post" enctype="multipart/form-data" action="validator">
      <p class="instructions">Kies het document dat je wilt valideren (alleen CSS bestanden):</p>
      <p>
	<label title="Kies een Lokaal Bestand dat je wilt Uploaden en Valideren" for="file">Local CSS file:
	<input type="file" id="file" name="file" size="30" /></label></p>
      <fieldset id="extra_opt_upload" class="moreoptions">
      <p class="toggle closed" title="Weergeven/Verbergen van extra validatie opties">Meer Opties:</p>
      <div>
      Waarschuwingen :
      <select name="warning"> 
	<option value="2">Alle</option>
	<option selected="selected" value="1">Normaal</option>
	<option value="0">Meest belangrijk</option>
	<option value="no">Geen</option>
      </select>
      
      Profiel :
      <select name="profile">
	<option value="none">Geen speciaal profiel</option>
	<option value="css1">CSS versie 1</option>
	<option value="css2">CSS versie 2</option>
	<option selected="selected" value="css21">CSS versie 2.1</option>
 	<option value="css3">CSS versie 3</option>
	<option value="svg">SVG</option>
	<option value="svgbasic">SVG Basic</option>
	<option value="svgtiny">SVG Tiny</option>	
	<option value="mobile">mobile</option>
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
      </select>
</div><!-- item_contents -->
</fieldset><!-- invisible -->
	<p class="submit_button"><label title="Verzenden om te valideren"><input type="submit" value="Controleer" /></label></p>
      </form>
      </fieldset>

      <fieldset id="validate-by-input"  class="tabset_content front"><legend class="tabset_label">Valideer via directe invoer</legend>
      <form action="validator" method="get">
      <p class="instructions">Voer de CSS in die je wilt valideren:</p>
      <p>
	<textarea name="text" rows="12" cols="70"></textarea>
	<input name="usermedium" value="all" type="hidden" />
      </p>      
      <fieldset id="extra_opt_direct" class="moreoptions">
      <p class="toggle closed" title="Weergeven/Verbergen van extra validatie opties">Meer Opties:</p>
      <div>
      Waarschuwingen :
      <select name="warning"> 
	<option value="2">Alle</option>
	<option selected="selected" value="1">Normaal</option>
	<option value="0">Meest belangrijk</option>
	<option value="no">Geen</option>
      </select>
      
      Profiel :
      <select name="profile">
	<option value="none">Geen speciaal profiel</option>
	<option value="css1">CSS versie 1</option>
	<option value="css2">CSS versie 2</option>
	<option selected="selected" value="css21">CSS versie 2.1</option>
 	<option value="css3">CSS versie 3</option>
	<option value="svg">SVG</option>
	<option value="svgbasic">SVG Basic</option>
	<option value="svgtiny">SVG Tiny</option>	
	<option value="mobile">mobile</option>
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
      </select>
</div><!-- item_contents -->
</fieldset><!-- invisible -->
	<p class="submit_button"><label title="Verzenden om te valideren"><input type="submit" value="Controleer" /></label></p>
      </form>
      </fieldset>
      </div><!-- fields -->
	</div> <!-- frontforms -->
	<p><strong>Opmerking</strong>: Als je een CSS style sheet wilt valideren die gebruikt wordt in een (X)HTML document,
	zou je die eerst moeten <a href="http://validator.w3.org/">laten controleren</a>.
	</p>
	<ul class="navbar"  id="menu">
		<li><a href="about.html" title="Over deze service">Over</a> <span class="hideme">|</span></li>
        <li><a href="documentation.html" title="Documentatie voor de W3C CSS Validatie Service">Documentatie</a> <span class="hideme">|</span></li>
        <li><a href="DOWNLOAD.html" title="Download de CSS validator">Download</a> <span class="hideme">|</span></li>
        <li><a href="Email.html" title="Hoe reacties te geven over deze service">Reacties</a> <span class="hideme">|</span></li>
        <li><a href="thanks.html" title="Credits en Erkenning">Credits</a><span class="hideme">|</span></li>
      </ul>

      <ul id="lang_choice">
           <li><a href="validator.html.de"
               lang="de"
               xml:lang="de"
               hreflang="de"
               rel="alternate">Deutsch</a></li>
           <li><a href="validator.html.en"
               lang="en"
               xml:lang="en"
           hreflang="en"
           rel="alternate">English</a>  </li>
           <li><a href="validator.html.es"
           lang="es" xml:lang="es" hreflang="es"
           rel="alternate">Español</a></li>
           <li><a href="validator.html.fr"
           lang="fr"
           xml:lang="fr"
           hreflang="fr"
           rel="alternate">Français</a> </li>
           <li><a href="validator.html.it"
               lang="it"
               xml:lang="it"
               hreflang="it"
               rel="alternate">Italiano</a> </li>
           <li><a href="validator.html.nl"
               lang="nl"
               xml:lang="nl"
               hreflang="nl"
               rel="alternate">Nederlands</a> </li>
           <li><a href="validator.html.ja"
               lang="ja"
               xml:lang="ja"
               hreflang="ja"
               rel="alternate">日本語</a> </li>
           <li><a href="validator.html.pl-PL"
               lang="pl"
               xml:lang="pl"
               hreflang="pl"
               rel="alternate">Polski</a> </li>
           <li><a href="validator.html.zh-cn"
               lang="zh-hans"
               xml:lang="zh-hans"
               hreflang="zh-hans"
               rel="alternate">中文</a></li>
      </ul>


   <p id="activity_logos">
      <a href="http://www.w3.org/QA/" title="W3C's Quality Assurance Activity, brengt je gratis Web kwaliteits tools en meer"><img src="http://www.w3.org/QA/2002/12/qa-small.png" alt="QA" /></a><a href="http://www.w3.org/Style/CSS/learning" title="Leer meer over Cascading Style Sheets"><img src="images/woolly-icon" alt="CSS" /></a>
   </p>

   <p id="support_logo">
Steun deze tool, wordt een<br />
<a href="http://www.w3.org/Consortium/supporters"><img src="http://www.w3.org/Consortium/supporter-logos/csupporter.png" alt="W3C Supporter" /></a>
   </p>

    <p class="copyright">

      <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/ipr-notice#Copyright">Copyright</a> &copy; 1994-2006
      <a href="http://www.w3.org/"><acronym title="World Wide Web Consortium">W3C</acronym></a>&reg;

      (<a href="http://www.csail.mit.edu/"><acronym title="Massachusetts Institute of Technology">MIT</acronym></a>,
      <a href="http://www.ercim.org/"><acronym title="European Research Consortium for Informatics and Mathematics">ERCIM</acronym></a>,
      <a href="http://www.keio.ac.jp/">Keio</a>),
      Alle Rechten Voorbehouden.
      W3C <a href="http://www.w3.org/Consortium/Legal/ipr-notice#Legal_Disclaimer">aansprakelijkheid</a>,
      <a href="http://www.w3.org/Consortium/Legal/ipr-notice#W3C_Trademarks">handelsmerk</a>,
      <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-documents">document gebruik</a>

      en de <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-software">software licentie</a>

      regels zijn van toepassing. Je interacties met deze site zijn in overeenstemming
      met onze <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Public">publieke</a> en
      <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Members">Member</a> privacy
      verklaringen.
    </p>

  </body>
</html>

