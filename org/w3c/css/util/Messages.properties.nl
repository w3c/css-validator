# Definieert uw eigen error en waarschuwingsberichten hier
content-type: text/html; charset=us-ASCII
content-language: nl

# U kunt zo het niveau van waarschuwing aanpassen (voorbeeld) :
# warning.redefinition.level: 5
# level is een integer tussen 0 en 9 (alle andere waarden worden genegeerd)
warning.redefinition: Herdefinitie van %s

# used by org.w3c.css.properties.Css1Style
warning.same-colors: Dezelfde kleuren voor %s en %s
warning.no-color: U heeft geen kleur voor uw achtergrondkleur opgegeven
warning.no-background-color: U heeft geen achtergrondkleur opgegeven
warning.color.mixed-capitalization: Alhoewel namen van kleuren case-insensitive zijn is het beter om de gemengde  schrijfwijze te hanteren om de namen beter leesbaar te maken: %s
warning.no-generic-family: %s: Het is beter een algemene font-family op te geven als laatste alternatief
warning.with-space: Fanmilienamen die spaties bevatten moeten tussen aanhalingstekens worden geplaatst. \
Als quotes worden weggelaten wordt elke tussenruimte voor en achter de naam genegeerd en elke groep \
whitespace-tekens in de naam wordt omgezet naar een spatie.
warning.no-padding: Het is beter een padding te gebruiken met een achtergrondkleur
warning.same-colors2: Dezelfde kleur en achtergrondkleur in twee contexten %s en %s
warning.relative-absolute: U hebt absolute en relatieve lengtes in %s. Dit is geen robuuste style sheet.

# used by org.w3c.css.properties.CssSelectors
warning.unknown-html: %s is geen HTML Element
warning.html-inside: HTML element kan niet binnen een ander element voorkomen
warning.body-inside: BODY element kan niet binnen een ander element dan HTML voorkomen
warning.pseudo-classes: Anker pseudo-class %s heeft geen effect op andere elementen dan 'A'

# niet gebruikt door org.w3c.css.properties.CssSelectors op dit moment
warning.noinside: %s kan niet voorkomen binnen een inline element
warning.withblock: Wees voorzichtig. Pseudo-elementen kunnen alleen worden gekoppeld aan een block-level element
warning.block-level: Deze eigenschap is van toepassing op block-level elementen.

# gebruikt door org.w3c.css.parser.Frame
warning.no-declaration: Geen declaraties in de regel

# gebruikt door org.w3c.css.values.CssColor
warning.out-of-range: %s valt buiten het bereik
error.invalid-color: Ongeldige RGB functie

warning.marker: De marker-offset eigenschap is van toepassing op elementen met 'display: marker'

# gebruikt door org.w3c.css.properties.ACssStyle
warning.relative: Het gebruik van relatieve maten geeft robuustere stylesheets bij de eigenschap %s

# gebruikt door org.w3c.css.css.StyleSheetParser and org.w3c.css.css.StyleSheetXMLParser
warning.at-rule: Sorry, de at-regel %s is niet geimplementeerd.

# gebruikt voor alle eigenschappen en waarden
error.operator: %s is een incorrecte operator
error.negative-value: %s negatieve waarden zijn niet toegestaan
error.few-value: te weinig waarden voor de eigenschap %s

# gebruikt door org.w3c.css.properties3.CssToggleGroup
error.groupname: %s is geen correcte groepnaam. Gebruik een geldige identifier

# gebruikt door org.w3c.css.properties3.CssGroupReset
error.nogroup: %s is niet geinitialiseerd door een toggle-group eigenschap

# gebruikt door org.w3c.css.properties3.CssGlyphOrVert
error.anglevalue: Waarde moet tussen -360 en 360 zijn en deelbaar zijn door 90

#gebruikt door org.w3c.css.properties3.CssTextKashidaSpace
error.percentage: Waarde moet een percentage zijn

#used by org.w3c.css.properties.cssDirection
warning.direction: het is beter voor block-level elementen de CSS3 eigenschap 'writing-mode' te gebruiken

# wees voorzichtig, waarden komen eerst
# U kunt niet zoiets als dit schrijven: Voor de kleur is blauw een incorrecte waarde
error.value: %s geen %s waarde

# gebruikt door org.w3c.css.properties.CssTextDecoration
error.same-value: %s komt tweemaal voor

error.generic-family.quote: Generieke familienamen zijn sleutelwoorden en moeten daarom tussen aanhalingstekens worden geplaatst

# gebruikt door org.w3c.css.properties.CssClip
error.shape: Ongeldige vormdefinitie rect(<top>,<right>,<bottom>,<left>)
error.shape-separator: Ongeldig scheidingsteken in de vormdefinitie. Dit moet een komma zijn.

# gebruikt door org.w3c.css.properties.CssContent
error.attr: Ongeldige definitie van attr  attr(X)
error.counter: Ongeldige counter definitie counter(<identifier>[,<list-style-type>]?)
error.counters: Ongeldige counters definitie counters(<identifier>,<string>[,<list-style-type>]?)

# gebruikt door org.w3c.css.font.Src
error.format: Ongeldige format definitie format(<string>[,<string>]*)
error.local: Ongeldige format definitie local(<string>|<ident>+)

# gebruikt door org.w3c.css.values.CssAngle, org.w3c.css.values.CssFrequency, org.w3c.css.values.CssTime, org.w3c.css.values.CssLength
error.unit: %s is een incorrecte unit

# gebruikt door org.w3c.css.aural.ACssAzimuth
error.degree: De positie moet worden beschreven in gradaties.

# gebruikt door org.w3c.css.aural.ACssElevation
error.elevation.range: Specificeert de 'elevation' als een hoek, tussen '-90deg' en '90deg'.

# gebruikt door org.w3c.css.aural.ACssPitchRange
error.range: Deze waarde valt buiten het bereik. Deze moet tussen '0' en '100' liggen.

# gebruikt door org.w3c.css.properties.CssTextShadow
error.two-lengths: Een schaduw offset wordt gespecificeerd met twee <length> waarden (Een blur radius kan optioneel worden gespecificeerd na de schaduw offset.)

error.integer: Dit getal moet een integer zijn.
error.comma: Ontbrekende komma als scheidingsteken.

# gebruikt door org.w3c.css.values.CssPercentage
error.percent: %s is een incorrect percentage

# gebruikt door org.w3c.css.values.CssString
error.string: %s is een incorrecte string

# gebruikt door org.w3c.css.values.CssURL
error.url: %s is geen incorrecte URL

# gebruikt door org.w3c.css.values.CssColor
error.rgb: %s is geen geldige kleur 3 of 6 hexadecimale getallens
error.angle: %s is geen geldige hoek. De waarde moet tussen 0 en 360 liggen

# gebruikt door org.w3c.css.values.CssNumber
error.zero: alleen 0 kan een %s zijn. U moet een maat achter uw getal plaatsen

# gebruikt door org.w3c.css.parser.CssPropertyFactory
error.noexistence: Eigenschap %s bestaat niet
error.noexistence-media: Eigenschap %s bestaat niet voor media %s

# gebruikt door org.w3c.css.parser.CssFouffa
error.unrecognize: Te veel waarden of teveel onbekende waarden

# gebruikt door org.w3c.css.parser.CssFouffa
generator.unrecognize: Parse Error

# gebruikt door org.w3c.css.parser.CssSelectors
error.pseudo-element: Het pseudo-element :%s kan niet in deze context voorkomen %s
error.pseudo-class: De pseudo-class .%s kan niet in deze HTML context voorkomen %s
error.pseudo: Onbekend pseudo-element of pseudo-class
error.id: ID selector #%s is ongeldig ! Slechts een ID selector kan worden gespecificeerd in een eenvoudige selector: %s.
error.space: Als de attribuut selector ~= wordt gebruikt mag het woord in de waarde %s geen spaties bevatten.
error.todo : Sorry de feature %s is nog niet geimplementeerd.
error.incompatible: %s en %s zijn incompatible

error.media: onbekende media %s 
error.page: onbekende pseudo genaamde pagina %s


# gebruikt door StyleSheetGeneratorHTML
generator.context: Context
generator.request: Er is een fout opgetreden tijdens de uitvoer van uw style sheet. \
Corrigeer uw verzoek of stuur een mail naar plh@w3.org.
generator.unrecognized: onbekend
generator.invalid-number: Ongeldig getal
generator.property: Ongeldig getal
generator.line: Regel
generator.not-found: Bestand niet gevonden
generator.doc-html: <p>\
Om alles naar behoren te laten werken is het noodzakelijk dat uw document een correcte grammaticale opbouw heeft \
Dit betekent dat u <a href="http://validator.w3.org/check?uri=\
%s">correct HTML</a> dient te gebruiken.</p>

generator.doc:<p>\
Om alles naar behoren te laten werken is het noodzakelijk dat uw CSS stylesheet een correcte grammaticale \
opbouw heeft. Dit betekent dat u <a href="http://validator.w3.org/">correct \
HTML</a> dient te gebruiken.</p>


# gebruikt door the parser
parser.semi-colon: poging een puntkomma te vinden voor de eigenschapnaam: voog deze toe

parser.old_class:In CSS1 kon een class naam beginnen met een getal (".55ft"), \
tenzij het een dimensie was (".55in"). In CSS2 worden zulke classes geparsed als \
onbekende dimensions (dit maakt het mogelijk later nieuwe units toe te voegen)


# gebruikt door de servlet
servlet.invalid-request: U hebt een ongeldig verzoek ingediend.
servlet.process: Kan het object niet verwerken

error.notforcss1 : Waarde %s bestaat niet voor CSS1
warning.noothermedium : Eigenschappen voor andere media werken mogelijk niet voor dit gebruikersmedium
warning.notforusermedium : Eigenschap %s bestaat niet voor dit gebruikersmedium
error.noatruleyet : Andere @regels dan @import worden niet ondersteund door CSS1 %
error.notformobile : %s kan niet worden gebruikt voor het mobile profiel
warning.atsc : %s wordt mogelijk niet ondersteund door het medium atsc-tv
error.onlyATSC : deze functie is alleen voor @media atsc-tv

error.unrecognized.link: Unrecognized link element or xml-stylesheet PI.

warning.otherprofile : property %s bestaat niet in dit profiel, maar is gevalideerd conform een ander profiel
warning.deprecated : deze waarde is verouderd en kan beter niet meer worden gebruikt