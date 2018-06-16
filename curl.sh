#! /bin/bash

if [ $(curl -o -I -L -s -w "%{http_code}" http://localhost:7777/hello-service/hello) -eq 200 ]
then
        tput setaf 2;echo 'OK';
else
        tput setaf 1; echo 'FAILURE';
fi

tput sgr 0