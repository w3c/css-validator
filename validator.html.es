<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
	<head>
		<title>El Servicio de Validación de CSS del W3C</title>
		<link rev="made" href="mailto:www-validator@w3.org"/>
		<link rev="start" href="./" title="Página de Inicio"/>
		<style type="text/css" media="all">@import "./base.css";</style>
		<meta name="revision" content="$Id$"/>
	</head>
	<body>
		<div id="banner">
			<h1 id="title">
				<a href="http://www.w3.org/">
					<img height="48" alt="W3C" id="logo" src="http://www.w3.org/Icons/WWW/w3c_home"/>
				</a>
      Servicio de Validación de CSS</h1>
		</div>
		<ul class="navbar" id="menu">
			<li>
				<span class="hideme">
					<a href="#skip" accesskey="s" title="Saltar a la parte principal del la página">Saltar la Navegación</a> |</span>
				<a href="./" accesskey="h" title="Ir a la Página de Inicio del Servicio de Validación de CSS del W3C">[<em>Inicio</em>]</a>
				<span class="hideme">|</span>
			</li>
			<li>
				<a href="http://jigsaw.w3.org/css-validator/Email" title="Cómo hacer sugerencias sobre este servicio">Sugerencias</a>
				<span class="hideme">|</span>
			</li>
			<li>
				<a href="http://jigsaw.w3.org/css-validator/DOWNLOAD.html" title="Descarga del validador de CSS">Descarga</a>
				<span class="hideme">|</span>
			</li>
			<li>
				<a href="http://jigsaw.w3.org/css-validator/README.html" title="Documentación del Servicio">Documentación</a>
				<span class="hideme">|</span>
			</li>
			<li>
				<a href="http://jigsaw.w3.org/css-validator/thanks.html" title="Créditos y Reconocimientos">Créditos</a>
				<span class="hideme">|</span>
			</li>
		</ul>
		<div id="main">
			<!-- This DIV encapsulates everything in each actual page. -->
			<div id="skip" class="intro">
				<p>
        Bienvenido al Servicio de Validación de CSS del W3C; un servicio gratuito que comprueba que las Hojas de Estilo en Cascada (CSS) de los documentos (X)HTML, o independientes, sean acordes a las Recomendaciones del W3C.

      </p>
				<p>
					<strong>Nota</strong>: Si se quiere validar una hoja de estilo CSS dentro de un documento (X)HTML, primero se debería <a href="http://validator.w3.org/">comprobar que el 
					(X)HTML utilizado es válido</a>.
	</p>
			</div>
			<div>
				<h2>Valide su Hoja de Estilo</h2>
				<p class="navigate">
        Valide sus documentos: 
	<a href="#validate-by-uri">indicando su URI</a> |
        <a href="#validate-by-upload">enviando el Archivo</a> |
        <a href="#validate-by-input">introduciendo la CSS directamente</a>
				</p>
				<fieldset id="validate-by-uri">
					<legend>Validar indicando el URI</legend>
					<form method="get" action="validator">
						<p>
	Introduzca el URI del documento (HTML con CSS o CSS sólo) que quiera validar:     
      </p>
						<p>
							<label title="Dirección de la página a validar" for="uri">Dirección:
	<input type="text" name="uri" id="uri" size="45" />
							</label>
							<label title="Validar el archivo">
								<input type="submit" value="Comprobar" />
							</label>
							<input name="usermedium" value="all" type="hidden" />
						</p>
					</form>
					<p>
	Disponibles más opciones en el <a href="validator-uri">Interfaz avanzado</a>.
      </p>
				</fieldset>
				<fieldset id="validate-by-upload">
					<legend>Validar enviando el Archivo</legend>
					<form method="post" enctype="multipart/form-data" action="validator">
						<p>Indique el documento que quiera validar (sólo archivos CSS):</p>
						<p>
							<label title="Indique un archivo local para enviarlo y validarlo" for="file">Archivo CSS local:</label>
							<input type="file" id="file" name="file" size="30" />
							<input name="usermedium" value="all" type="hidden" />
							<label title="Enviar el archivo para validarlo">
								<input type="submit" value="Comprobar" />
							</label>
						</p>
					</form>
					<p>
	Disponibles más opciones en el <a href="validator-upload">Interfaz avanzado de envío de archivos</a>.
      </p>
				</fieldset>
				<fieldset id="validate-by-input">
					<legend>Validar introduciendo CSS directamente</legend>
					<form action="validator" method="get">
						<p>Introduzca el código CSS que quiera validar:</p>
						<p>
							<textarea name="text" rows="12" cols="75"></textarea>
							<input name="usermedium" value="all" type="hidden" />
							<br/>
							<label title="Enviar el texto para su validación">
								<input type="submit" value="Comprobar" />
							</label>
						</p>
					</form>
					<p>
	Disponibles más opciones en el <a href="validator-text">Interfaz avanzado para la introdución directa de CSS</a>.
      </p>
				</fieldset>
			</div>
		</div>
		<!-- End of "main" DIV. -->
		<address>
			<a href="http://validator.w3.org/check/referer">
				<img src="http://www.w3.org/Icons/valid-xhtml10" height="31" width="88" alt="¡XHTML 1.0 Válido!"/>
			</a>
			<a class="right" href="http://jigsaw.w3.org/css-validator">
				<img style="border:0;width:88px;height:31px" src="http://jigsaw.w3.org/css-validator/images/vcss" alt="¡CSS Válido!"/>
			</a>
			<a title="Haga sus sugerencias sobre el Servicio de Validación CSS del W3C" href="Email">El Equipo del Validador de CSS del W3C</a>
			<br/>
      $Fecha: 27/12/2004 15:26:54 $
      <!-- SSI Template Version: $Id$ -->
		</address>
		<p class="copyright">
			<a rel="Copyright" href="http://www.w3.org/Consortium/Legal/ipr-notice#Copyright" hreflang="en-us" xml:lang="en-us">Copyright</a> &#xa9; 1994-2004
<a href="http://www.w3.org" hreflang="en-us">
				<acronym title="World Wide Web Consortium" xml:lang="en-us">W3C</acronym>
			</a>
			<sup>&#xae;</sup> (<a href="http://www.lcs.mit.edu/" hreflang="en-us">
				<acronym title="Massachusetts Institute of Technology" xml:lang="en-us">MIT</acronym>
			</a>, <a href="http://www.ercim.org/" hreflang="en-us">
				<acronym title="European Research Consortium for Informatics and Mathematics" xml:lang="en-us">ERCIM</acronym>
			</a>, <a href="http://www.keio.ac.jp/" hreflang="en-us">Keio</a>), Todos los Derechos Reservados. Son aplicables las reglas de <a href="http://www.w3.org/Consortium/Legal/ipr-notice#Legal_Disclaimer" hreflang="en-us">responsabilidad</a>, <a href="http://www.w3.org/Consortium/Legal/ipr-notice#W3C_Trademarks" hreflang="en-us">marcas registradas</a>, <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-documents" hreflang="en-us">utilización de documentos</a>
y <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-software" hreflang="en-us">licencias de 
software</a>. Las interacciones con este sitio están de acuerdo con nuestra declaración de privacidad 
de <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Public" hreflang="en-us">usuarios</a> y 
<a href="http://www.w3.org/Consortium/Legal/privacy-statement#Members" hreflang="en-us">miembros</a>
		</p>
	</body>
</html>
