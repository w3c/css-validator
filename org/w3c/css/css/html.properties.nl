# used by StyleSheet2HTML

# entites used by the HTML generator
go-errors: <LI><A HREF="#errors">Ga naar Fouten</A>
go-warnings: <LI><A HREF="#warnings">Ga naar Waarschuwingen</A>
go-rules: <LI><A HREF="#css">Ga naar uw gevalideerde Cascading Style Sheet</A>
no-error-or-warning: <HR><H2>Geen fout of waarschuwing gevonden</H2>

no-errors: \
<hr> \
<p>\
<img src="http://jigsaw.w3.org/css-validator/images/vcss.gif" alt="Correct CSS!">\
Gefeliciteerd, dit document is gevalideerd als <a\ \
href="http://www.w3.org/TR/REC-CSS2/">CSS</a>! \
  </p>\
<p> \
Om uw lezers te laten zien dat u de moeite heeft genomen om een interoperabele webpagina te maken, \
kunt u dit pictogram op elke gevalideerde pagina plaatsen. Hier is de HTML code \
die u kunt gebruiken om dit pictogram aan uw webpagina toe te voegen: <pre> \n\
\
\  &lt;p>&lt;a href="http://jigsaw.w3.org/css-validator">&lt;img style="border:0;width:88px;height:31px" \n\
\ \ \ \ \ \ \ \ \ \ \ \ \ src="http://jigsaw.w3.org/css-validator/images/vcss.gif" \n\
\ \ \ \ \ \ \ \ \ \ \ \ \ alt="Correct CSS!">&lt;/a>&lt;/p></pre>\
\
<p> \
Als u wilt, kunt u een kopie van dit plaatje downloaden en in uw locale webdirectory \
te zetten en in het HTML fragment hierboven refereren aan uw lokaal opgeslagen afbeelding \
in plaats van aan die op deze server.</p>\
\
<p>Als u een link wilt maken naar deze pagina (i.e. naar dit validatieresultaat \
om het gemakkelijker te maken deze pagina later te revalideren of anderen \
in staat te stellen uw pagina te valideren is de URL:</p><pre>\n\
\
\ \ \ \ \ \ \ \ \ \ http://jigsaw.w3.org/css-validator/validator?uri=<!-- #file-title -->\n\
\ \ \ \ \ \ \ of http://jigsaw.w3.org/css-validator/check/referer (alleen voor HTML documenten)\n\
\n\
(Of u kunt de huidige pagina toevoegen aan uw bookmarks of hotlist.)</pre>

no-rules: <H2>Geen style sheet gevonden</H2>
important-style: <STRONG> !belangrijk</STRONG>
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
<hr> \
<a name="css"></a><h2>Uw gevalideerde Cascading Style Sheet :</h2> \
<dl> \
<!-- #rule --> \
</dl>

rule: \
<dt><strong><!-- #selectors --></strong> {<br> \
<!-- #declaration --> \
}<br><br>

# met contextuele selectors kunt u speciale dingen doen
selectors: \
<!-- #selector -->

declaration: \
   <!-- #property-name --> : <em><!-- #property-value --></em><!-- #important-style -->; <br>

# Hoe fouten te melden

errors : \
<hr> \
<a name="errors"></a>\
<h2>Fouten :</h2>\n\
<!-- #error -->\n

# Hoe waarschuwingen te melden

warnings: \
<hr> \
<a name="warnings"></a> \
<h2>Waarschuwingen :</h2> \n\
<!-- #warning -->\n

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
<style type="text/css"><!--\n\
H2 {\n\
  font-weight: bold; \
  font-size: large; \
  font-family: helvetica, sans-serif; \
  color: rgb(136,0,0); \
  margin-top: 2.0%; \
}\n\
BODY {\n\
  font-size: medium; \
  font-family: verdana, arial, helvetica, sans-serif; \
  color: rgb(0,0,0); \
  background-color: white; \
}\n\
ADDRESS {\n\
  font-weight: bold; \
  text-align: right; \
}\n\
.right { \n\
  float: right; \
}\n\
.left { \n\
  float: left; \
}\n\
HR { \n\
  margin-top: 2.0%; \
}\n\
#rules { margin-left : 2em }\n\
--></style>\n\
</head> \n\
<body> \n\
<a class="left" href="http://www.w3.org"><IMG \
 SRC="http://www.w3.org/Icons/w3c_home" BORDER="0" ALT="w3c"WIDTH="72" \
 HEIGHT="48"></A> \
<a style="text-align: right" href="http://www.w3.org/Jigsaw/"><IMG \
 SRC="http://jigsaw.w3.org/Icons/jigpower.gif" ALT="Jigsaw Powered" \
 BORDER="0" WIDTH="94" HEIGHT="52"></a> \
<br> \
<h2>W3C CSS Validator Resultaten met object : \
<A HREF="<!-- #file-title -->"><!-- #file-title --></A></h2> \n\
<ul> \n\
<!-- #go-errors --> \
<!-- #go-warnings --> \
<!-- #go-rules --> \n\
<li>\
<a href="http://jigsaw.w3.org/css-validator/">Terug naar de CSS Validator</a></li>\
</ul>\n\
<!-- #no-error-or-warning -->\
<!-- #errors -->\
<!-- #no-errors -->\
<!-- #warnings -->\
<!-- #hook-html-validator -->\
<!-- #rules -->\
<!-- #no-rules -->\
<hr> \n\
<a class="right" href="http://jigsaw.w3.org/css-validator">\
 <img border="0" src="http://jigsaw.w3.org/css-validator/images/vcss.gif" \
	alt="Correct CSS!" height="31" width="88"></a>\n\
<address><a href="mailto:<!-- #author-email -->"><!-- #author --></a><br>\
Last Updated : <!-- #today --> </address> \n\
\n\
<!-- Created: Wed Aug 20 14:40:43 GMT+03:30 1997--> \n\
</body> \n\
</html>
