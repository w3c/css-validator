# used by StyleSheet2HTML

# entites used by the HTML generator
go-errors: <li><a href="#errors">Ir a los Errores</a></li>
go-warnings: <li><a href="#warnings">Ir a las Advertencias</a></li>
go-rules: <li><a href="#css">Ir a su Hoja de Estilo validada</a></li>
no-error-or-warning: <hr /><h2>No hay errores ni advertencias</h2>

no-errors: \
<div id='congrats'>\n\
<h2>¡Enhorabuena!</h2>\n\
<p>\
<a style="float: right" href="http://jigsaw.w3.org/css-validator/">\n\
<img src="http://jigsaw.w3.org/css-validator/images/vcss" alt="¡CSS Válido!" /></a>\
 ¡Este documento es <a\ \
href="http://www.w3.org/TR/REC-CSS2/">CSS</a> válido! \
  </p>\
<p> \
Puede mostrar este icono en cualquier página que valide para que los usuarios vean \
que se ha preocupado por crear una página Web interoperable. A continuación \
se encuentra el XHTML que puede usar para añadir el icono a su página Web:</p> <pre class='example'> \n\
&lt;p>\n\
\ &lt;a href="http://jigsaw.w3.org/css-validator/">\n\
\ \ &lt;img style="border:0;width:88px;height:31px"\n\
\ \ \ \ \ \ \ src="http://jigsaw.w3.org/css-validator/images/vcss" \n\
\ \ \ \ \ \ \ alt="¡CSS Válido!" />\n\
\ &lt;/a>\n\
&lt;/p>\
</pre>\n\
(cierre la etiqueta img con &gt; en lugar de /&gt; si utiliza HTML &lt;= 4.01)\
<p> \
Si lo desea, puede descargar una copia de la imagen para guardarla en su \
directorio web local y cambiar el fragmento anterior de XHTML para referenciar \
a la imagen en local en lugar de a la de éste servidor. </p>\n\
\
<p>Si desea crear un enlace con esta página (es decir, con los resultados \
de la validación) para hacer que sea más fácil revalidar la página en el futuro, \
o para permitir que otras personas validen su página, el URI es: </p><pre>\n\
\
\ \ \ \ \ \ \ \ \ \ http://jigsaw.w3.org/css-validator/validator?uri=<!-- #file-title -->\n\
\ \ \ \ \ \ \ o http://jigsaw.w3.org/css-validator/check/referer (para documentos HTML/XML únicamente)\n\
</pre>\n\
<p>(O, simplemente, puede añadir la página actual a su lista de marcadores o favoritos.)</p>\n\
</div>

no-rules: <div><h2>No se ha encontrado ninguna hoja de estilo</h2></div>
not-css1-style: /* ¡ TENGA CUIDADO ! ¡ Esta propiedad no está incluida en CSS1 ! */

# You have some predefined entities :
#  file-title : the name of the style sheet
#  today : the date of this day
#  errors-count : number of errors
#  warnings-count : number of warnings
#  items-count : number of rules in your style sheet
#  author : my name :-)
#  author-email : my e-mail :-))

# you can add newlines like this : \n

#
# How to write all rules
#
# be careful, use only :
#   - selector and declaration entities in the entity rule
#   - element, class, id, pseudo-class and pseudo-element entities in the entity selector
#   - property-name, property-value, important-style and not-css1-style entities in the entity declaration

rules: \
<div id="css">\n\
<h2>Información de CSS válida</h2> \n\
<ul class='vAtRule'> \n\
<!-- #charset-rule --> \
<!-- #rule --> \
</ul>\n\
</div>

charset-rule: \
<li class='vCharset'>@charset <!-- #charset -->;</li>

# How to write errors

errors : \
<div id="errors">\n\
<h2>Errores</h2>\n\
<!-- #error -->\n\
</div>

# How to write warnings

warnings: \
<div id="warnings">\n\
<h2>Advertencias</h2> \n\
<!-- #warning -->\n\
</div>

warning:

# the beginning of the document
document: \
<?xml version='1.0' encoding='iso-8859-1'?>\n\
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'\n\
                      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">\n\
<html lang='es' xml:lang='es' xmlns='http://www.w3.org/1999/xhtml'>\n\
<head> \n\
<meta name="ROBOTS" content="NOINDEX, NOFOLLOW" /> \n\
<title>Resultados del Validador CSS del W3C para <!-- #file-title --></title> \n\
<link href="http://jigsaw.w3.org/css-validator/" rel="validator" />\n\
<link type="text/css" rel='stylesheet' href='http://www.w3.org/StyleSheets/TR/base.css' />\n\
<link type="text/css" rel='stylesheet' href='http://jigsaw.w3.org/css-validator/style/results.css' />\n\
</head> \n\
<body> \n\
<p>\n\
<a href="http://www.w3.org"><img\n\
src="http://www.w3.org/Icons/w3c_home" alt="W3C" width="72" height="48" /></a> <a\n\
style="float: right" href="http://www.w3.org/Jigsaw/"><img\n\
src="http://jigsaw.w3.org/Icons/jigpower" alt="Jigsaw Powered" width="94"\n\
height="52" /></a>\n\
</p>\n\
\n\
<h1>Resultados del Validador de CSS del W3C para \
<a href="<!-- #file-title -->"><!-- #file-title --></a></h1> \n\
<!-- #no-error-or-warning -->\n\
<!-- #no-errors -->\n\
<!-- #hook-html-validator -->\n\
<!-- #errors -->\n\
<!-- #warnings -->\n\
<!-- #rules -->\n\
<!-- #no-rules -->\
<hr /> \n\
<p><a class="right" href="http://jigsaw.w3.org/css-validator/">\
 <img src="http://jigsaw.w3.org/css-validator/images/vcss" \
	alt="¡CSS Válido!" height="31" width="88" /></a></p>\n\
<address><a href="<!-- #author-email -->"><!-- #author --></a><br />\
Última Modificación: <!-- #today --> </address> \n\
\n\
<!-- Created: Wed Aug 20 14:40:43 GMT+03:30 1997--> \n\
</body> \n\
</html>
