#!/bin/bash
value=$(curl -s http://localhost/Igrue/ws?wsdl | grep -c "wsdl:service name=\"IgrueService\"")
if [ "$value" == "1" ]
then
	echo "Web Service del Proxy Igrue correttamente funzionanti"
	exit 0 
else
	echo "Web Service del Proxy Igrue non disponibili"
	exit 2
fi

