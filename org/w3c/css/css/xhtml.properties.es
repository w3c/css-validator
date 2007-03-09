# used by StyleSheet2HTML

# entites used by the HTML generator
go-errors: <li><a href="#errors">los Errores (<!-- #errors-count -->)</a></li>
go-warnings: <li><a href="#warnings">las Advertencias (<!-- #warnings-count -->)</a></li>
go-rules: <li><a href="#css">su Hoja de Estilo validada</a></li>
no-error-or-warning: <hr /><h2>No hay errores ni advertencias</h2>
backtop: <p class="backtop"><a href="#banner">&uarr; Top</a></p>

navbar: <ul class="navbar" id="jumpbar"><li><strong>Ir a:</strong></li>\n\
<!-- #go-errors -->\n\
<!-- #go-warnings -->\n\
<!-- #go-rules -->\n\
</ul>\n\
no-errors: \
<div id='congrats'>\n\
<h3>¡Enhorabuena!</h3>\n\
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
<p>(cierre la etiqueta img con &gt; en lugar de /&gt; si utiliza HTML &lt;= 4.01)</p>\
<p> \
Si lo desea, puede descargar una copia de la imagen para guardarla en su \
directorio web local y cambiar el fragmento anterior de XHTML para referenciar \
a la imagen en local en lugar de a la de éste servidor. </p>\n\
\
<p>Si desea crear un enlace con esta página (es decir, con los resultados \
de la validación) para hacer que sea más fácil revalidar la página en el futuro, \
o para permitir que otras personas validen su página, el URI es: </p><pre>\n\
\
\ \ \ \ \ http://jigsaw.w3.org/css-validator/validator?uri=<!-- #file-title -->\n\
\ \ \ o http://jigsaw.w3.org/css-validator/check/referer (para documentos HTML/XML únicamente)\n\
</pre>\n\
<p>(O, simplemente, puede añadir la página actual a su lista de marcadores o favoritos.)</p>\n\
</div><!-- #backtop -->

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
<div id="css" class="alt closed">\n\
<h3>Información de CSS válida</h3> \n\
<div class='vAtRule'> \n\
<!-- #charset-rule --> \
<!-- #rule --> \
</div>\n\
</div><!-- #backtop -->

charset-rule: \
<div class='vCharset'>@charset <!-- #charset -->;</div>

# How to write errors

errors : \
<div id="errors">\n\
<h3>Errores</h3>\n\
<!-- #error -->\n\
</div><!-- #backtop -->

# How to write warnings

warnings: \
<div id="warnings" class="alt closed">\n\
<h3>Advertencias (<!-- #warnings-count -->)</h3> \n\
<!-- #warning -->\n\
</div><!-- #backtop -->

warning:

# the beginning of the document
document: \
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'\n\
                      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">\n\
<html lang='es' xml:lang='es' xmlns='http://www.w3.org/1999/xhtml'>\n\
<head> \n\
<meta name="ROBOTS" content="NOINDEX, NOFOLLOW" /> \n\
<title>Resultados del Validador CSS del W3C para <!-- #file-title --></title> \n\
<link href="http://jigsaw.w3.org/css-validator/" rel="validator" />\n\
<link type="text/css" rel='stylesheet' href='style/base.css' />\n\
<link type="text/css" rel='stylesheet' href='style/results.css' />\n\
</head> \n\
<body> \n\
<div id="banner">\n\
 <h1 id="title"><a href="http://www.w3.org/"><img height="48" alt="W3C" id="logo" src="http://www.w3.org/Icons/WWW/w3c_home_nb" /></a>\n\
 <a href="./"><img src="images/css_validation_service.png" alt="CSS Validation Service" /></a></h1>\n\
</div>\n\
\n\
<h2>Resultados del Validador CSS del W3C para \
<a href="<!-- #file-title -->"><!-- #file-title --></a></h2> \n\
<!-- #no-error-or-warning -->\n\
<!-- #no-errors -->\n\
<!-- #hook-html-validator -->\n\
<!-- #errors -->\n\
<!-- #warnings -->\n\
<!-- #rules -->\n\
<!-- #no-rules -->\
<ul class="navbar"  id="menu">\n\
	<li><strong><a href="./" title="P&aacute;gina de inicio del Servicio de Validaci&oacute;n CSS del  W3C">Inicio</a></strong> <span class="hideme">|</span></li>\n\
	<li><a href="about.html" title="Acerca de este servicio">Acerca de este servicio</a> <span class="hideme">|</span></li>\n\
     <li><a href="documentation.html" title="Documentaci&oacute;n del Servicio de Validaci&oacute;n CSS del W3C">Documentaci&oacute;n</a> <span class="hideme">|</span></li>\n\
     <li><a href="Email.html" title="C&oacute;mo realizar comentarios sobre este servicio">Comentarios</a> <span class="hideme">|</span></li>\n\
     <li><a href="thanks.html" title="Cr&eacute;ditos y Agradecimientos">Cr&eacute;ditos</a><span class="hideme">|</span></li>\n\
   </ul>\n\
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