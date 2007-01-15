# used by StyleSheet2HTML

# entites used by the HTML generator
go-errors: <li><a href="#errors">Ga naar Fouten</a></li>
go-warnings: <li><a href="#warnings">Ga naar Waarschuwingen</a></li>
go-rules: <li><a href="#css">Ga naar uw gevalideerde Cascading Style Sheet</a></li>
no-error-or-warning: <div><h2>Geen fout of waarschuwing gevonden</h2></div>

no-errors: \
<div id='congrats'> \
<h2>Gefeliciteerd!</h2>\
<p>\
<img class='right' src="http://jigsaw.w3.org/css-validator/images/vcss" alt="Correct CSS!">\
Dit document is gevalideerd als <a\ \
href="http://www.w3.org/TR/REC-CSS2/">CSS</a>! \
  </p>\
  <div class="alttoggle closed">\
<p class="toggletext">More info</p>\
<p> \
Om uw lezers te laten zien dat u de moeite heeft genomen om een interoperabele webpagina te maken, \
kunt u dit pictogram op elke gevalideerde pagina plaatsen. Hier is de HTML code \
die u kunt gebruiken om dit pictogram aan uw webpagina toe te voegen: <pre class='example'> \n\
&lt;p>\n\
\ &lt;a href="http://jigsaw.w3.org/css-validator/">\n\
\ \ &lt;img style="border:0;width:88px;height:31px"\n\
\ \ \ \ \ \ \ src="http://jigsaw.w3.org/css-validator/images/vcss" \n\
\ \ \ \ \ \ \ alt="Valid CSS!" />\n\
\ &lt;/a>\n\
&lt;/p>\
</pre>\n\
<p> \
Als u wilt, kunt u een kopie van dit plaatje downloaden en in uw locale webdirectory \
te zetten en in het XHTML fragment hierboven refereren aan uw lokaal opgeslagen afbeelding \
in plaats van aan die op deze server.</p>\
\
<p>Als u een link wilt maken naar deze pagina (i.e. naar dit validatieresultaat \
om het gemakkelijker te maken deze pagina later te revalideren of anderen \
in staat te stellen uw pagina te valideren is de URL:</p><pre>\n\
\
\ \ \ \ \ http://jigsaw.w3.org/css-validator/validator?uri=<!-- #file-title -->\n\
\ \ \ of http://jigsaw.w3.org/css-validator/check/referer (alleen voor HTML documenten)\n\
</pre>\n\
<p>(Of u kunt de huidige pagina toevoegen aan uw bookmarks of hotlist.)</p>\
</div>\
</div>

no-rules: <H2>Geen style sheet gevonden</H2>
not-css1-style: /* VOORZICHTIG ! Dit is geen CSS1 eigenschap ! */

# Er is een aantal voorgedefinieerde entiteiten
#  file-title : de naam van de style sheet
#  today : de datum van vandaag
#  errors-count : aantal fouten
#  warnings-count : aantal waarschuwingen
#  items-count : aantal regels in uw style sheet
#  author : mijn naam :-)
#  author-email : mijn e-mail :-))

# u kunt zo newlines toevoegen: \n

#
# Hoe alle regels te schrijven
#
# wees voorzichtig, gebruik alleen:
#   - selector en declaratie entiteiten in de entiteitregel
#   - element, class, id, pseudo-class en pseudo-element entiteiten in de entiteit selector
#   - property-name, property-value, important-style en niet-css1-style entiteiten in de entiteit declaratie

rules: \
<div id="css" class="alttoggle closed">\
<h2 class="toggletext">Uw gevalideerde Cascading Style Sheet :</h2> \
<div class='vAtRule'> \n\
<!-- #charset-rule --> \
<!-- #rule --> \
</div></div>

charset-rule: \
<div class='vCharset'>@charset <!-- #charset -->;</div>

# Hoe fouten te melden

errors : \
<div id="errors">\
<h2>Fouten</h2>\n\
<!-- #error --></div>\n

# Hoe waarschuwingen te melden

warnings: \
<div id="warnings" class="alttoggle closed"> \
<h2 class="toggletext">Waarschuwingen (<!-- #warnings-count -->) :</h2> \n\
<!-- #warning --></div>\n

warning:

# het begin van een document
document: \
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'\n\
                      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">\n\
<html lang='nl' xml:lang='nl' xmlns='http://www.w3.org/1999/xhtml'>\n\
<head> \n\
<meta name="ROBOTS" content="NOINDEX, NOFOLLOW" /> \n\
<title>W3C CSS Validator Resultaten met object : <!-- #file-title --></title> \n\
<link href="http://jigsaw.w3.org/css-validator/" rel="validator" />\n\
<link type="text/css" rel='stylesheet' href='style/base.css' />\n\
<link type="text/css" rel='stylesheet' href='style/results.css' />\n\
<script src="toggle.js" type="text/javascript"></script>\n\
</head> \n\
<body> \n\
<div id="banner">\n\
 <h1 id="title"><a href="http://www.w3.org/"><img height="48" alt="W3C" id="logo" src="http://www.w3.org/Icons/WWW/w3c_home_nb" /></a>\n\
 <a href="./"><img src="images/css_validation_service.png" alt="CSS Validation Service" /></a></h1>\n\
</div>\n\
\n\
<h2>W3C CSS Validator Resultaten met object : \
<a href="<!-- #file-title -->"><!-- #file-title --></a></h2> \n\
<!-- #no-error-or-warning -->\n\
<!-- #no-errors -->\n\
<!-- #hook-html-validator -->\n\
<!-- #errors -->\n\
<!-- #warnings -->\n\
<!-- #rules -->\n\
<!-- #no-rules -->\n\
	<ul class="navbar"  id="menu">\n\
  <li><strong><a href="./" title="Home pagina van de W3C CSS Validatie Service">Home</a></strong> <span class="hideme">|</span></li>\n\
  <li><a href="about.html" title="Over deze service">Over</a> <span class="hideme">|</span></li>\n\
  <li><a href="documentation.html" title="Documentatie voor de W3C CSS Validatie Service">Documentatie</a> <span class="hideme">|</span></li>\n\
  <li><a href="DOWNLOAD.html" title="Download de CSS validator">Download</a> <span class="hideme">|</span></li>\n\
  <li><a href="Email.html" title="Hoe reacties te geven over deze service">Reacties</a> <span class="hideme">|</span></li>\n\
  <li><a href="thanks.html" title="Credits en Erkenning">Credits</a></li>\n\
</ul>
   <p id="activity_logos"> \n\
      <a href="http://www.w3.org/QA/" title="W3C's Quality Assurance Activity, bringing you free Web quality tools and more"><img src="http://www.w3.org/QA/2002/12/qa-small.png" alt="QA" /></a><a href="http://www.w3.org/Style/CSS/learning" title="Learn more about Cascading Style Sheets"><img src="images/woolly-icon" alt="CSS" /></a> \n\
   </p> \n\
   <p id="support_logo"> \n\
Support this tool, become a<br /> \n\
<a href="http://www.w3.org/Consortium/supporters"><img src="http://www.w3.org/Consortium/supporter-logos/csupporter.png" alt="W3C Supporter" /></a> \n\
   </p> \n\
    <p class="copyright"> \n\
      <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/ipr-notice#Copyright">Copyright</a> &copy; 1994-2006 \n\
      <a href="http://www.w3.org/"><acronym title="World Wide Web Consortium">W3C</acronym></a>&reg; \n\
      (<a href="http://www.csail.mit.edu/"><acronym title="Massachusetts Institute of Technology">MIT</acronym></a>, \n\
      <a href="http://www.ercim.org/"><acronym title="European Research Consortium for Informatics and Mathematics">ERCIM</acronym></a>, \n\
      <a href="http://www.keio.ac.jp/">Keio</a>), \n\
      All Rights Reserved. \n\
      W3C <a href="http://www.w3.org/Consortium/Legal/ipr-notice#Legal_Disclaimer">liability</a>, \n\
      <a href="http://www.w3.org/Consortium/Legal/ipr-notice#W3C_Trademarks">trademark</a>, \n\
      <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-documents">document use</a> \n\
      and <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-software">software licensing</a> \n\
      rules apply. Your interactions with this site are in accordance \n\
      with our <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Public">public</a> and \n\
      <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Members">Member</a> privacy \n\
      statements. \n\
    </p> \n\
  </body> \n\
</html>