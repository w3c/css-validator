<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
<title>El Servicio de Validaci&oacute;n de CSS del W3C</title>
<link rev="made" href="mailto:www-validator-css@w3.org"/>
<link rev="start" href="./" title="Home Page"/>
<style type="text/css" media="all">@import "style/base.css";
</style>
<script type="text/javascript" src="tabtastic/addclasskillclass.js"/>
<script type="text/javascript" src="tabtastic/attachevent.js"/>
<script type="text/javascript" src="tabtastic/addcss.js"/>
<script type="text/javascript" src="tabtastic/tabtastic.js"/>
<script src="toggle.js" type="text/javascript"/>
<meta name="revision" content="$Id$"/>
</head>
<body>
<div id="banner">
<h1 id="title">
<a href="http://www.w3.org/">
<img height="48" alt="W3C" id="logo" src="http://www.w3.org/Icons/WWW/w3c_home_nb"/>
</a>
<img src="images/css_validation_service.png" alt="Servicio de Validaci&oacute;n de CSS"/>
</h1>
</div>
<p id="tagline">
        Verifica Hojas de Estilo en Cascada (CSS) y documentos (X)HTML con hojas de estilo
      </p>
<div id="frontforms">
<ul class="tabset_tabs"><li><a href="#validate-by-uri" class="active">mediante URI</a></li><li><a href="#validate-by-upload">mediante Carga de Archivo</a></li><li><a href="#validate-by-input">mediante Entrada directa</a></li></ul>
<div id="fields">
<fieldset id="validate-by-uri" class="tabset_content front">
<legend class="tabset_label">Validar mediante URI</legend>
<form method="get" action="validator">
<p class="instructions">
	Introduce la URI de un documento (HTML con CSS o s&oacute;lo CSS) que desees validar:     
      </p>
<p>
<label title="Direcci&oacute;n de la p&aacute;gina a Validar" for="uri">Direcci&oacute;n:
	<input type="text" name="uri" id="uri" size="45"/>
</label>
</p>
<fieldset id="extra_opt_uri" class="moreoptions alttoggle closed">
<legend class="toggletext" title="Mostrar/Ocultar opciones extra de validaci&oacute;n">M&aacute;s Opciones</legend>
<div>
      <label for="warning_uri">Advertencias :</label>
      <select id="warning_uri" name="warning">
<option value="2">Todas</option>
<option selected="selected" value="1">Informe normal</option>
<option value="0">Las m&aacute;s importantes</option>
<option value="no">Sin advertencias</option>
</select>
      
      <label for="profile_uri">Perfil :</label>
      <select id="profile_uri" name="profile">
<option value="none">Ninguno en especial</option>
<option value="css1">CSS versi&oacute;n 1</option>
<option selected="selected" value="css2">CSS versi&oacute;n 2</option>
<option value="css21">CSS versi&oacute;n 2.1</option>
<option value="css3">CSS versi&oacute;n 3</option>
<option value="svg">SVG</option>
<option value="svgbasic">SVG B&aacute;sico</option>
<option value="svgtiny">SVG Reducido</option>
<option value="mobile">M&oacute;vil</option>
<option value="atsc-tv">Perfil de TV ATSC</option>
<option value="tv">Perfil de TV</option>
</select>

      <label for="medium_uri">Medio :</label>
      <select id="medium_uri" name="usermedium">
<option selected="selected" value="all">todos</option>
<option value="aural">auditivo</option>
<option value="braille">braille</option>
<option value="embossed">relieve</option>
<option value="handheld">peque&ntilde;os dispositivos</option>
<option value="print">impresi&oacute;n</option>
<option value="projection">proyecci&oacute;n</option>
<option value="screen">pantalla</option>
<option value="tty">teletipo</option>
<option value="tv">televisi&oacute;n</option>
<option value="presentation">presentaci&oacute;n</option>
</select>
</div>
<!-- item_contents -->
</fieldset>
<!-- invisible -->
<p class="submit_button">
<label title="Enviar archivo para su validaci&oacute;n">
<input type="submit" value="Check"/>
</label>
</p>
</form>
</fieldset>
<fieldset id="validate-by-upload" class="tabset_content front">
<legend class="tabset_label">Validar mediante Carga de un Archivo</legend>
<form method="post" enctype="multipart/form-data" action="validator">
<p class="instructions">Elige el documento que desees validar (&uacute;nicamente archivos CSS):</p>
<p>
<label title="Elige un Archivo Local para su Carga y Validaci&oacute;n" for="file">Archivo CSS local:
	<input type="file" id="file" name="file" size="30"/>
</label>
</p>
<fieldset id="extra_opt_upload" class="moreoptions alttoggle closed">
<legend class="toggletext" title="Mostrar/Ocultar opciones extra de validaci&oacute;n">M&aacute;s opciones</legend>
<div>
      <label for="warning_upload">Advertencias :</label>
      <select id="warning_upload" name="warning">
<option value="2">Todos</option>
<option selected="selected" value="1">Informe normal</option>
<option value="0">Las m&aacute;s importantes</option>
<option value="no">Sin advertencias</option>
</select>
      
      <label for="profile_upload">Perfil :</label>
      <select id="profile_upload" name="profile">
<option value="none">Ninguno en especial</option>
<option value="css1">CSS versi&oacute;n 1</option>
<option selected="selected" value="css2">CSS versi&oacute;n 2</option>
<option value="css21">CSS versi&oacute;n 2.1</option>
<option value="css3">CSS versi&oacute;n 3</option>
<option value="svg">SVG</option>
<option value="svgbasic">SVG B&aacute;sico</option>
<option value="svgtiny">SVG Reducido</option>
<option value="mobile">M&oacute;vil</option>
<option value="atsc-tv">Perfil de TV ATSC</option>
<option value="tv">Perfil de TV</option>
</select>

      <label for="medium_upload">Medio :</label>
      <select id="medium_upload" name="usermedium">
<option selected="selected" value="all">todos</option>
<option value="aural">auditivo</option>
<option value="braille">braille</option>
<option value="embossed">relieve</option>
<option value="handheld">peque&ntilde;os dispositivos</option>
<option value="print">impresi&oacute;n</option>
<option value="projection">proyecci&oacute;n</option>
<option value="screen">pantalla</option>
<option value="tty">teletipo</option>
<option value="tv">televisi&oacute;n</option>
<option value="presentation">presentaci&oacute;n</option>
</select>
</div>
<!-- item_contents -->
</fieldset>
<!-- invisible -->
<p class="submit_button">
<label title="Enviar archivo para su validaci&oacute;n">
<input type="submit" value="Check"/>
</label>
</p>
</form>
</fieldset>
<fieldset id="validate-by-input" class="tabset_content front">
<legend class="tabset_label">Validar mediante entrada directa</legend>
<form action="validator" method="get">
<p class="instructions">Introduce el c&oacute;digo CSS que desees validar:</p>
<p>
<textarea name="text" rows="12" cols="70"></textarea>
<input name="usermedium" value="all" type="hidden"/>
</p>
<fieldset id="extra_opt_direct" class="moreoptions alttoggle closed">
<legend class="toggletext" title="Mostrar/Ocultar opciones extra de validaci&oacute;n">M&aacute;s opciones</legend>
<div>
      <label for="warning_direct">Advertencias :</label>
      <select id="warning_direct" name="warning">
<option value="2">Todos</option>
<option selected="selected" value="1">Informe normal</option>
<option value="0">Las m&aacute;s importantes</option>
<option value="no">Sin advertencias</option>
</select>
      
      <label for="profile_direct">Perfil :</label>
      <select id="profile_direct" name="profile">
<option value="none">Ninguno en especial</option>
<option value="css1">CSS versi&oacute;n 1</option>
<option selected="selected" value="css2">CSS versi&oacute;n 2</option>
<option value="css21">CSS versi&oacute;n 2.1</option>
<option value="css3">CSS versi&oacute;n 3</option>
<option value="svg">SVG</option>
<option value="svgbasic">SVG B&aacute;sico</option>
<option value="svgtiny">SVG Reducido</option>
<option value="mobile">M&oacute;vil</option>
<option value="atsc-tv">Perfil de TV ATSC</option>
<option value="tv">Perfil de TV</option>
</select>

      <label for="medium_direct">Medio :</label>
      <select id="medium_direct" name="usermedium">
<option selected="selected" value="all">todos</option>
<option value="aural">auditivo</option>
<option value="braille">braille</option>
<option value="embossed">relieve</option>
<option value="handheld">peque&ntilde;os dispositivos</option>
<option value="print">impresi&oacute;n</option>
<option value="projection">proyecci&oacute;n</option>
<option value="screen">pantalla</option>
<option value="tty">teletipo</option>
<option value="tv">televisi&oacute;n</option>
<option value="presentation">presentaci&oacute;n</option>
</select>
</div>
<!-- item_contents -->
</fieldset>
<!-- invisible -->
<p class="submit_button">
<label title="Enviar archivo para su validaci&oacute;n">
<input type="submit" value="Check"/>
</label>
</p>
</form>
</fieldset>
</div>
<!-- fields -->
</div>
<!-- frontforms -->
<p>
<strong>Nota</strong>: Si deseas validar tu hoja de estilo CSS incrustada en un documento (X)HTML, deber&iacute;as antes <a href="http://validator.w3.org/">comprobar que el  (X)HTML utilizado es v&aacute;lido</a>.
	</p>
<ul class="navbar" id="menu">
<li>
<a href="about.html" title="Acerca de este servicio">Acerca de este servicio</a>
<span class="hideme">|</span>
</li>
<li>
<a href="documentation.html" title="Documentaci&oacute;n del Servicio de Validaci&oacute;n CSS del W3C">Documentaci&oacute;n</a>
<span class="hideme">|</span>
</li>
<li>
<a href="DOWNLOAD.html" title="Descarga el Validador CSS">Descarga</a>
<span class="hideme">|</span>
</li>
<li>
<a href="Email.html" title="C&oacute;mo realizar comentarios sobre este servicio">Comentarios</a>
<span class="hideme">|</span>
</li>
<li>
<a href="thanks.html" title="Cr&eacute;ditos y Agradecimientos">Cr&eacute;ditos</a>
<span class="hideme">|</span>
</li>
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
<a href="http://www.w3.org/QA/" title="Actividad de Garant&iacute;a de Calidad del W3C, ofreci&eacute;ndote herramientas Web de calidad libres y m&aacute;s">
<img src="http://www.w3.org/QA/2002/12/qa-small.png" alt="QA"/>
</a>
<a href="http://www.w3.org/Style/CSS/learning" title="Aprende m&aacute;s sobre Hojas de Estilo en Cascada">
<img src="images/woolly-icon" alt="CSS"/>
</a>
</p>
<p id="support_logo">
Apoya esta herramienta, convi&eacute;rtete en un <br/>
<a href="http://www.w3.org/Consortium/supporters">
<img src="http://www.w3.org/Consortium/supporter-logos/csupporter.png" alt="W3C Supporter"/>
</a>
</p>
<p class="copyright">
<a rel="Copyright" href="http://www.w3.org/Consortium/Legal/ipr-notice#Copyright">Copyright</a> &copy; 1994-2006
      <a href="http://www.w3.org/">
<acronym title="World Wide Web Consortium">W3C</acronym>
</a>&reg;

      (<a href="http://www.csail.mit.edu/">
<acronym title="Massachusetts Institute of Technology">MIT</acronym>
</a>,
      <a href="http://www.ercim.org/">
<acronym title="European Research Consortium for Informatics and Mathematics">ERCIM</acronym>
</a>,
      <a href="http://www.keio.ac.jp/">Keio</a>),
      All Rights Reserved.
      W3C <a href="http://www.w3.org/Consortium/Legal/ipr-notice#Legal_Disclaimer">liability</a>,
      <a href="http://www.w3.org/Consortium/Legal/ipr-notice#W3C_Trademarks">trademark</a>,
      <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-documents">document use</a>
      and <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-software">software licensing</a>

      rules apply. Your interactions with this site are in accordance
      with our <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Public">public</a> and
      <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Members">Member</a> privacy
      statements.
    </p>
</body>
</html>
