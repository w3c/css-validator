# used by StyleSheet2HTML

# entites used by the HTML generator
go-errors: <li><a href="#errors">Ga naar Fouten</a></li>
go-warnings: <li><a href="#warnings">Ga naar Waarschuwingen</a></li>
go-rules: <li><a href="#css">Ga naar uw gevalideerde Cascading Style Sheet</a></li>
no-error-or-warning: <div><h2>Geen fout of waarschuwing gevonden</h2></div>

no-errors: \
<div> \
<h2>Gefeliciteerd!</h2>
<p>\
<img class='right' src="http://jigsaw.w3.org/css-validator/images/vcss" alt="Correct CSS!">\
Dit document is gevalideerd als <a\ \
href="http://www.w3.org/TR/REC-CSS2/">CSS</a>! \
  </p>\
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
\ \ \ \ \ \ \ \ \ \ http://jigsaw.w3.org/css-validator/validator?uri=<!-- #file-title -->\n\
\ \ \ \ \ \ \ of http://jigsaw.w3.org/css-validator/check/referer (alleen voor HTML documenten)\n\
</pre>\n\
<p>(Of u kunt de huidige pagina toevoegen aan uw bookmarks of hotlist.)</p></div>

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
<div id="css"><h2>Uw gevalideerde Cascading Style Sheet :</h2> \
<ul> \
<!-- #charset-rule --> \
<!-- #rule --> \
</ul></div>

charset-rule: \
<li class='vCharset'>@charset <!-- #charset -->;</li>

# Hoe fouten te melden

errors : \
<div id="errors">\
<h2>Fouten</h2>\n\
<!-- #error --></div>\n

# Hoe waarschuwingen te melden

warnings: \
<div id="warnings"> \
<h2>Waarschuwingen :</h2> \n\
<!-- #warning --></div>\n

warning:

# het begin van een document
document: \
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"\
                      "http://www.w3.org/TR/REC-html40/loose.dtd">\n\
<html lang="nl"> \n\
<head> \n\
<meta lang="nl" name="Translator" content="Sijtsche de Jong">\n\
<meta name="ROBOTS" content="NOINDEX, NOFOLLOW"> \n\
<title>CSS Validator resultaten</title> \n\
<link href="http://jigsaw.w3.org/css-validator/" rel="validator">\n\
<link type="text/css" rel='stylesheet' href='http://www.w3.org/StyleSheets/TR/base.css'>\n\
<link type="text/css" rel='stylesheet' href='http://jigsaw.w3.org/css-validator/style/results.css'>\n\
</head> \n\
<body> \n\
<p>\n\
<a href="http://www.w3.org"><img\n\
src="http://www.w3.org/Icons/w3c_home" alt="W3C" width="72" height="48"></a> <a\n\
style="float: right" href="http://www.w3.org/Jigsaw/"><img\n\
src="http://jigsaw.w3.org/Icons/jigpower" alt="Jigsaw Powered" width="94"\n\
height="52"></a>\n\
</p>\n\
<h1>W3C CSS Validator Resultaten met object : \
<a href="<!-- #file-title -->"><!-- #file-title --></a></h1> \n\
<ul> \n\
<!-- #go-errors --> \
<!-- #go-warnings --> \
<!-- #go-rules --> \n\
<li>\
<a href="http://jigsaw.w3.org/css-validator/">Terug naar de CSS Validator</a></li>\
</ul>\n\
<!-- #no-error-or-warning -->\
<!-- #no-errors -->\
<!-- #hook-html-validator -->\
<!-- #errors -->\
<!-- #warnings -->\
<!-- #rules -->\
<!-- #no-rules -->\
<hr /> \n\
<a class="right" href="http://jigsaw.w3.org/css-validator">\
 <img border="0" src="http://jigsaw.w3.org/css-validator/images/vcss" \
	alt="Correct CSS!" height="31" width="88"></a>\n\
<address><a href="<!-- #author-email -->"><!-- #author --></a><br />\
Last Updated : <!-- #today --> </address> \n\
\n\
<!-- Created: Wed Aug 20 14:40:43 GMT+03:30 1997--> \n\
</body> \n\
</html>
