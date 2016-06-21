#!/bin/bash
value=$(curl -s http://localhost/proxyigrue/ | grep -c "Proxy IGRUE")
if [ "$value" == "1" ]
then
	echo "Web UI del Proxy Igrue correttamente funzionanti"
	exit 0 
else
	echo "Web UI del Proxy Igrue non disponibili"
	exit 2
fi

