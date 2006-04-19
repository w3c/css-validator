#!/bin/sh

java -cp .:/usr/share/java/jaxp-1.2.jar:/usr/share/java/xercesImpl.jar:/usr/share/java/xmlParserAPIs.jar:/usr/local/Jigsaw/classes/jigsaw.jar AutoTest $1
