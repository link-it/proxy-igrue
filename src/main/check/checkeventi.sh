#!/bin/bash

value=$(grep "igrue.check.eventi" igrue.check 2> /dev/null |cut -d "=" -f 2)
if [ -z "$value" ] 
then 
	echo "Checkfile di Igrue non trovato." 
	exit 1 
fi
if [ "$value" == "0"  ]
then
	echo "Servizio Eventi del Sistema Centrale correttamente funzionante."
	exit $value
fi
if [ "$value" == "2"  ]
then
        echo "Servizio Eventi del Sistema Centrale non funzionante."
        exit $value
fi
echo "Codice del check non previsto."
exit 1

