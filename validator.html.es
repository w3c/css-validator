<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
  <head>
    <title>Servicio de Validación de CSS del W3C</title>
    <link rev="made" href="mailto:www-validator-css@w3.org" />
    <link rev="start" href="./" title="Home Page" />
    <style type="text/css" media="all">@import "style/base.css";</style>
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
        Valide su Hoja de Estilo en Cascada (CSS) de los documentos (X)HTML, o independientes
      </p>
	<div id="frontforms">
      <ul class="tabset_tabs"><li><a href="#validate-by-uri" class="active">Por URI</a></li><li><a href="#validate-by-upload">Enviando el Archivo</a></li><li><a href="#validate-by-input">Introduciendo la CSS directamente</a></li></ul>
    
      <fieldset id="validate-by-uri" class="tabset_content front"><legend class="tabset_label">Validar por URI</legend>
      <form method="get" action="validator">
      <p class="instructions">
	Introduzca el URI del documento (HTML con CSS o CSS sólo) que quiera validar:     
      </p>
      <p>
	<label title="Address of page to Validate" for="uri">Dirección:
	<input type="text" name="uri" id="uri" size="45" /></label>
      </p>
      <fieldset id="extra_opt_uri" class="moreoptions">
      <p class="toggle closed" title="Show/Hide extra validation options">Más Opciones</p>
      <div>
		 Advertencias:
		      <select name="warning"> 
			<option value="2">Todas</option>
			<option selected="selected" value="1">Informe normal</option>
			<option value="0">Las más importantes</option>
			<option value="no">Ninguna</option>
		      </select>

		      Perfil:
		      <select name="profile">
			<option value="none">Ninguno en especial</option>
			<option value="css1">CSS versión 1</option>
			<option selected="selected" value="css2">CSS versión 2</option>
			<option value="css21">CSS versión 2.1</option>
		 	<option value="css3">CSS versión 3</option>
			<option value="svg">SVG</option>
			<option value="svgbasic">SVG Básico</option>
			<option value="svgtiny">SVG Reducido</option>	
			<option value="mobile">Móvil</option>
			<option value="atsc-tv">Perfil de TV ATSC</option>
			<option value="tv">Perfil de TV</option>
		      </select>

		      Medio:
		      <select name="usermedium">
			<option selected="selected" value="all">todos</option>	
			<option value="aural">auditivo</option>
			<option value="braille">braille</option>
			<option value="print">impresión</option>	
			<option value="screen">pantalla</option>
			<option value="handheld">pequeños dispositivos</option>
			<option value="presentation">presentación</option>
			<option value= "projection">proyección</option>
			<option value="embossed">relieve</option>
			<option value="tty">teletipo</option>
			<option value="tv">televisión</option>
		      </select>

</div><!-- item_contents -->
</fieldset><!-- invisible -->
	<p class="submit_button"><label title="Submit file for validation"><input type="submit" value="Comprobar" /></label></p>
      </form>
      </fieldset>

      <fieldset id="validate-by-upload"  class="tabset_content front"><legend class="tabset_label">Validar enviando el Archivo</legend>
      <form method="post" enctype="multipart/form-data" action="validator">
      <p class="instructions">Indique el documento que quiera validar (sólo archivos CSS):</p>
      <p>
	<label title="Choose a Local File to Upload and Validate" for="file">Archivo CSS local:
	<input type="file" id="file" name="file" size="30" /></label></p>
      <fieldset id="extra_opt_upload" class="moreoptions">
      <p class="toggle closed" title="Show/Hide extra validation options">Más Opciones</p>
      <div>
		 Advertencias:
		      <select name="warning"> 
			<option value="2">Todas</option>
			<option selected="selected" value="1">Informe normal</option>
			<option value="0">Las más importantes</option>
			<option value="no">Ninguna</option>
		      </select>

		      Perfil:
		      <select name="profile">
			<option value="none">Ninguno en especial</option>
			<option value="css1">CSS versión 1</option>
			<option selected="selected" value="css2">CSS versión 2</option>
			<option value="css21">CSS versión 2.1</option>
		 	<option value="css3">CSS versión 3</option>
			<option value="svg">SVG</option>
			<option value="svgbasic">SVG Básico</option>
			<option value="svgtiny">SVG Reducido</option>	
			<option value="mobile">Móvil</option>
			<option value="atsc-tv">Perfil de TV ATSC</option>
			<option value="tv">Perfil de TV</option>
		      </select>

		      Medio:
		      <select name="usermedium">
			<option selected="selected" value="all">todos</option>	
			<option value="aural">auditivo</option>
			<option value="braille">braille</option>
			<option value="print">impresión</option>	
			<option value="screen">pantalla</option>
			<option value="handheld">pequeños dispositivos</option>
			<option value="presentation">presentación</option>
			<option value= "projection">proyección</option>
			<option value="embossed">relieve</option>
			<option value="tty">teletipo</option>
			<option value="tv">televisión</option>
		      </select>
</div><!-- item_contents -->
</fieldset><!-- invisible -->
	<p class="submit_button"><label title="Submit file for validation"><input type="submit" value="Comprobar" /></label></p>
      </form>
      </fieldset>

      <fieldset id="validate-by-input"  class="tabset_content front"><legend class="tabset_label">Validar introduciendo CSS directamente</legend>
      <form action="validator" method="get">
      <p class="instructions">Enter the CSS you would like validated:</p>
      <p>
	<textarea name="text" rows="12" cols="70"></textarea>
	<input name="usermedium" value="all" type="hidden" />
      </p>      
      <fieldset id="extra_opt_direct" class="moreoptions">
      <p class="toggle closed" title="Show/Hide extra validation options">Más Opciones</p>
      <div>
		 Advertencias:
		      <select name="warning"> 
			<option value="2">Todas</option>
			<option selected="selected" value="1">Informe normal</option>
			<option value="0">Las más importantes</option>
			<option value="no">Ninguna</option>
		      </select>

		      Perfil:
		      <select name="profile">
			<option value="none">Ninguno en especial</option>
			<option value="css1">CSS versión 1</option>
			<option selected="selected" value="css2">CSS versión 2</option>
			<option value="css21">CSS versión 2.1</option>
		 	<option value="css3">CSS versión 3</option>
			<option value="svg">SVG</option>
			<option value="svgbasic">SVG Básico</option>
			<option value="svgtiny">SVG Reducido</option>	
			<option value="mobile">Móvil</option>
			<option value="atsc-tv">Perfil de TV ATSC</option>
			<option value="tv">Perfil de TV</option>
		      </select>

		      Medio:
		      <select name="usermedium">
			<option selected="selected" value="all">todos</option>	
			<option value="aural">auditivo</option>
			<option value="braille">braille</option>
			<option value="print">impresión</option>	
			<option value="screen">pantalla</option>
			<option value="handheld">pequeños dispositivos</option>
			<option value="presentation">presentación</option>
			<option value= "projection">proyección</option>
			<option value="embossed">relieve</option>
			<option value="tty">teletipo</option>
			<option value="tv">televisión</option>
		      </select>

</div><!-- item_contents -->
</fieldset><!-- invisible -->
	<p class="submit_button"><label title="Submit file for validation"><input type="submit" value="Comprobar" /></label></p>
      </form>
      </fieldset>
	</div> <!-- frontforms -->
	<p><strong>Nota</strong>: Si se quiere validar una hoja de estilo CSS dentro de un documento (X)HTML, primero se debería <a href="http://validator.w3.org/">comprobar que el (X)HTML utilizado es válido</a>.
	</p>
   <ul class="navbar"  id="menu">
        <li><a href="about.html" title="About this service">A Propósito</a> <span class="hideme">|</span></li>
        <li><a href="documentation.html" title="Documentation for the W3C CSS Validation Service">Documentation</a> <span class="hideme">|</span></li>
        <li><a href="DOWNLOAD.html" title="Download and install the CSS validator">Descarga e Installación</a> <span class="hideme">|</span></li>
        <li><a href="Email.html" title="How to provide feedback on this service">Sugerencias</a> <span class="hideme">|</span></li>
        <li><a href="thanks.html" title="Credits and Acknowlegments">Créditos</a><span class="hideme">|</span></li>
        <li><a href="languages.html" hreflang="en" lang="en" title="How to use the CSS Validator in your language">Other Languages</a><span class="hideme">|</span></li>
      </ul>

   <p id="activity_logos">
      <a href="http://www.w3.org/QA/" title="W3C's Quality Assurance Activity, bringing you free Web quality tools and more"><img src="http://www.w3.org/QA/2002/12/qa-small.png" alt="QA" /></a><a href="http://www.w3.org/Style/CSS/learning" title="Learn more about Cascading Style Sheets"><img src="images/woolly-icon" alt="CSS" /></a>
   </p>
   <p id="support_logo">
Support this tool, become a<br />
<a href="http://www.w3.org/Consortium/supporters"><img src="http://www.w3.org/Consortium/supporter-logos/csupporter.png" alt="W3C Supporter" /></a>
   </p>

    <p class="copyright">
      	<a rel="Copyright" href="http://www.w3.org/Consortium/Legal/ipr-notice#Copyright" hreflang="en-us"><br />
		Copyright</a> &copy; 1994-2006 <a href="http://www.w3.org/" hreflang="en-us"><acronym
		title="World Wide Web Consortium" xml:lang="en-us">W3C</acronym></a><sup>&reg;</sup> (<a
		href="http://www.lcs.mit.edu/" hreflang="en-us"><acronym
		title="Massachusetts Institute of Technology" xml:lang="en-us">MIT</acronym></a>, <a
		href="http://www.ercim.org" hreflang="en-us"><acronym
		title="European Research Consortium for Informatics and Mathematics" xml:lang="en-us">ERCIM</acronym></a>,
		<a href="http://www.keio.ac.jp/" hreflang="en-us">Keio</a>), Todos los derechos reservados.
		Son aplicables las reglas de <a
		href="http://www.w3.org/Consortium/Legal/ipr-notice#Legal_Disclaimer" hreflang="en-us">responsabilidad</a>,
		<a
		href="http://www.w3.org/Consortium/Legal/ipr-notice#W3C_Trademarks" hreflang="en-us">marcas registradas</a>,
		<a rel="Copyright"
		href="http://www.w3.org/Consortium/Legal/copyright-documents" hreflang="en-us">utilizaci&oacute;n de documentos</a> y <a rel="Copyright"
		href="http://www.w3.org/Consortium/Legal/copyright-software" hreflang="en-us">licencias de software</a>.
		Las interacciones con este sitio est&aacute;n de acuerdo con nuestra declaraci&oacute;n de privacidad de
		<a href="http://www.w3.org/Consortium/Legal/privacy-statement#Public" hreflang="en-us">usuarios</a>
		y <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Members" hreflang="en-us">miembros</a>.
    </p>

  </body>
</html>

