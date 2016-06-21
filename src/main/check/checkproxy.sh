#!/bin/bash

value=$(grep "igrue.check.proxy" igrue.check 2> /dev/null |cut -d "=" -f 2)
if [ -z "$value" ] 
then 
	echo "Checkfile di Igrue non trovato." 
	exit 1 
fi
if [ "$value" == "0"  ]
then
	echo "Proxy Igrue correttamente funzionante."
	exit $value
fi
if [ "$value" == "2"  ]
then
        echo "Proxy Igrue non funzionante."
        exit $value
fi
echo "Codice del check non previsto."
exit 1

