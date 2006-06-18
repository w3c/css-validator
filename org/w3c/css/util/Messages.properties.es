# Defines your own error and warning message here
content-type: text/html; charset=utf-8
content-language: es
output-encoding-name: utf-8

# You can change the level warning like this (example) :
# warning.redefinition.level: 5
#  level is an integer between 0 and 9 (all others values are ignored)
warning.redefinition: Redefinición de %s

# used by org.w3c.css.properties.Css1Style
warning.same-colors: Colores iguales para %s y %s
warning.no-color: Hay un color de fondo establecido y no hay color de primer plano
warning.no-background-color: Hay un color de primer plano establecido y no hay color de fondo
warning.color.mixed-capitalization: Aunque los nombres de los colores no son sensibles a las mayúsculas, es recomendable utilizar capitalización mixta para hacerlos más legibles: %s
warning.no-generic-family: %s: Es recomendable ofrecer una familia genérica como última alternativa
warning.with-space: Los nombres de familias que contengan espacios en blanco deben entrecomillarse. Si no se hace, cualquier espacio \
en blanco anterior o posterior al nombre será ignorado y cualquier secuencia de espacios en blanco dentro del nombre \
será convertida a un único espacio. 
warning.no-padding: Es recomendable tener un área de relleno (padding) con el color de fondo
warning.same-colors2: Color de primer plano y color de fondo iguales en dos contextos %s y %s
warning.relative-absolute: Hay algunas longitudes absolutas y relativas en %s. No es una hoja de estilo robusta.

# used by org.w3c.css.properties.CssSelectors
warning.unknown-html: %s no es un elemento de HTML
warning.html-inside: El elemento HTML no puede estar dentro de otro elemento
warning.body-inside: El elemento BODY no puede estar dentro de otro elemento que no sea el elemento HTML
warning.pseudo-classes: La pseudo-clase de Anchor %s sólo tiene efecto en los elementos 'A'

# not used by org.w3c.css.properties.CssSelectors for the moment
warning.noinside: %s no puede estar dentro de un elemento de línea
warning.withblock: Cuidado. Los pseudo-elementos sólo se pueden unir a elementos de bloque
warning.block-level: Estas propiedad se aplica a elementos de bloque.

# used by org.w3c.css.parser.Frame
warning.no-declaration: No hay declaraciones en la regla

# used by org.w3c.css.values.CssColor
warning.out-of-range: %s está fuera de rango
error.invalid-color: Función RGB no válida

warning.marker: La propiedad marker-offset se aplica a elementos con 'display: marker'

# used by org.w3c.css.properties.ACssStyle
warning.relative: Utilizar unidades relativas da lugar a hojas de estilo más robustas en la propiedad %s

# used by org.w3c.css.css.StyleSheetParser and org.w3c.css.css.StyleSheetXMLParser
error.at-rule: Lo lamentamos, la regla-arroba %s no está implementada.

# used by all properties and values
error.operator: %s es un operador incorrecto
error.negative-value: Valores negativos de %s no están permitidos
error.few-value: Faltan valores para la propiedad %s

# be careful here, values comes first
# You can't write something like this : For the color, blue is an incorrect value
error.value: %s no es un valor de %s

#used by org.w3c.css.properties3.CssToggleGroup
error.groupname: %s no es un nombre de grupo correcto. Use un identificador válido

#used by org.w3c.css.properties3.CssGroupReset
error.nogroup: %s no ha sido establecido por la propiedad toggle-group

#used by org.w3c.css.properties3.CssGlyphOrVert
error.anglevalue: El valor tiene que estar comprendido entre -360 y 360, y ser divisible por 90

#used by org.w3c.css.properties3.CssTextKashidaSpace
error.percentage: se espera un valor en porcentaje

#used by org.w3c.css.properties.CssTextAlign
warning.xsl: el valor %s sólo se aplica a XSL

#used by org.w3c.css.parser.analyzer.CssParser
warning.medialist : la lista de medios (medialist) debería comenzar por 'media :' %s
error.nocomb: El combinador %s entre selectores no está permitido en este perfil o versión

#used by org.w3c.css.properties.CssDirection
warning.direction: use la nueva propiedad de CSS3 'writing-mode' en lugar de usar 'direction' para los elementos de bloque

# used by org.w3c.css.properties.CssTextDecoration
error.same-value: %s aparece dos veces

error.generic-family.quote: Los nombres de familia genéricos son palabras reservadas y, por tanto, no deben entrecomillarse.

# used by org.w3c.css.properties.CssClip
error.shape: Definición de figura no válida rect(<top>,<right>,<bottom>,<left>)
error.shape-separator: Separador no válido en la definición de figura. Debe ser una coma.

# used by org.w3c.css.properties.CssContent
error.attr: Definición de attr no válida attr(X)
error.function: Definición de función no válida 
error.counter: Definición de contador no válida counter(<identifier>[,<list-style-type>]?)
error.counters: Definición de contadores no válida counters(<identifier>,<string>[,<list-style-type>]?)

# used by org.w3c.css.font.Src
error.format: Definición de formato no válida format(<string>[,<string>]*)
error.local: Definición de localización no válida local(<string>|<ident>+)

# used by org.w3c.css.values.CssAngle, org.w3c.css.values.CssFrequency, org.w3c.css.values.CssTime, org.w3c.css.values.CssLength
error.unit: %s es una unidad incorrecta

# used by org.w3c.css.aural.ACssAzimuth
error.degree: La posición debe estar especificada en términos de grados.

# used by org.w3c.css.aural.ACssElevation
error.elevation.range: Especificar la elevación como un ángulo entre '-90deg' y '90deg'.

# used by org.w3c.css.aural.ACssPitchRange
error.range: El valor está fuera del rango. Este valor debe estar comprendido entre '0' y '100'.

# used by org.w3c.css.properties.CssTextShadow
error.two-lengths: Un offset de sombra se especifica con dos valores <length> (Opcionalmente, depués del offset de sombra puede especificarse un ratio de difuminado.)

error.integer: Éste número debe ser un entero.
error.comma: Falta una coma para separar.

# used by org.w3c.css.values.CssPercentage
error.percent: %s no es un porcentaje correcto

# used by org.w3c.css.values.CssString
error.string: %s no es una cadena correcta

# used by org.w3c.css.values.CssURL
error.url: %s no es un URL correcto

# used by org.w3c.css.values.CssColor
error.rgb: %s no es un color válido de 3 o 6 cifras hexadecimales
error.angle: %s no es un ángulo válido. El valor debe estar comprendido entre 0 y 360

# used by org.w3c.css.values.CssNumber
error.zero: Únicamente 0 puede ser un %s. Debe especificarse una unidad detrás de la cifra

# used by org.w3c.css.parser.CssPropertyFactory
error.noexistence: La propiedad %s no existe
error.noexistence-media: La propiedad %s no existe en el medio %s
warning.noexistence-media: La propiedad %s no existe en el medio %s
warning.notforusermedium : La propiedad %s no existe en este medio de usuario
warning.noothermedium : Las propiedades de otros medios podrían no funcionar en el medio de usuario
# used by org.w3c.css.parser.AtRule*
error.noatruleyet : Las reglas-arroba que no sean @import no son soportadas por CSS1 %s
# used by org.w3c.css.parser.analyzer.CssParser
error.notforcss1 : El valor %s no existe en CSS1

# used by org.w3c.css.parser.CssFouffa
error.unrecognize: Faltan valores o no se reconocen los valores

# used by org.w3c.css.parser.CssFouffa
generator.unrecognize: Error de análisis sintáctico

# used by org.w3c.css.parser.CssSelectors
error.pseudo-element: El pseudo-elemento :%s no puede aparecer aquí en el contexto %s
error.pseudo-class: La pseudo-clase .%s no puede aparecer aquí en el contexto de HTML %s
error.pseudo: Pseudo-clase o pseudo-elemento %s desconocido(a)
error.id: ¡El selector de ID #%s no es válido! En un selector simple sólo puede especificarse un selector de ID: %s.
error.space: Si se utiliza el selector de atributo ~= entonces el valor de %s no puede contener espacios.
error.todo: Lo lamentamos, esta función %s todavía no está implementada.
error.incompatible: %s y %s son incompatibles
warning.incompatible: %s y %s son incompatibles
error.notformobile : %s no puede usarse en perfiles móviles
error.notforatsc : %s no puede usarse en perfiles ATSC
error.notfortv : %s no puede usarse en perfiles de televisión
error.notversion : %s no puede usarse en esta versión de CSS: %s

error.media: medio no reconocido %s 
error.page: página pseudo-nombrada no reconocida %s

error.unrecognized.link: elemento de enlace o instrucción de procesamiento de hoja de estilo xml no reconocida.

# used by StyleSheetGeneratorHTML
generator.context: Contexto
generator.request: Se ha producido un error en el procesado de su hoja de estilo. \
Por favor, corrija su petición o envíe un correo a plh@w3.org.
generator.unrecognized: No reconocido
generator.invalid-number: Número no válido
generator.property: Propiedad no válida
generator.line: Línea
generator.not-found: Archivo no encontrado
generator.doc-html: <p class='vDocHTML'>\
Su hoja de estilo CSS necesita un árbol de análisis del documento válido \
para funcionar correctamente. Por tanto debe usar <a href="http://validator.w3.org/check?uri=\
%s">HTML válido</a>.</p>

generator.doc:<p class='vDocHTML'>\
Su hoja de estilo CSS necesita un árbol de análisis del documento válido \
para funcionar correctamente. Por tanto debe usar <a href="http://validator.w3.org/">HTML \
válido</a>.</p>


# used by the parser
parser.semi-colon: Tentativa de encontrar un punto y coma antes del nombre de la propiedad. Añádalo

parser.old_class: En CSS1, un nombre de clase puede empezar por un dígito (".55ft"), \
excepto si es una magnitud de medida (".55in"). En CSS2, esas clases son interpretadas como \
magnitudes de medida desconocidas (para permitir añadir nuevas magnitudes en un futuro)

parser.old_id: En CSS1, un nombre de id puede empezar por un dígito ("#55ft"), \
excepto si es una magnitud de medida ("#55in"). En CSS2, esos nombres son interpretados como \
magnitudes de medida desconocidas (para permitir añadir nuevas magnitudes en un futuro)

parser.class_dim: En CSS1, un nombre de clase puede empezar por un dígito (".55ft"), \
excepto si es una magnitud de medida (".55in")

parser.id_dim: En CSS1, un nombre de id puede empezar por un dígito ("#55ft"), \
excepto si es una magnitud de medida ("#55in")

parser.charset:La regla @charset sólo puede aparecer al comienzo de la hoja \
de estilo. Por favor, compruebe que no hay espacios antes.

parser.charsetspecial:Este perfil tiene una sintaxis muy específica para @charset: \
@charset seguido de un espacio exactamente, seguido por el nombre de la codificación \
entre comillas, seguido inmediatamente por un punto y coma.

warning.old_id:En CSS1, un nombre de id puede empezar por un dígito ("#55ft"), \
excepto si es una magnitud de medida ("#55in"). En CSS2, esos nombres son interpretados como \
magnitudes de medida desconocidas (para permitir añadir nuevas magnitudes en un futuro)

warning.old_class:En CSS1, un nombre de id puede empezar por un dígito (".55ft"), \
excepto si es una magnitud de medida (".55in"). En CSS2, esos nombres son interpretados como \
magnitudes de medida desconocidas (para permitir añadir nuevas magnitudes en un futuro)

# used by the servlet
servlet.invalid-request: Se ha enviado una petición no válida.
servlet.process: No se puede procesar el objeto

warning.atsc : %s podría no ser compatible con el medio atsc-tv
error.onlyATSC : %s esta función es sólo para el medio atsc-tv

warning.otherprofile : la propiedad %s no existe en este perfil, pero es válida conforme a otro perfil
warning.deprecated : este valor está desaprobado

#used by org.w3c.css.parser.analyzer.CssParser
error.nocomb: La combinación %s entre selectores no está permitida en este perfil o versión