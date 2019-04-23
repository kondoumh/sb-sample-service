#!/bin/sh
set -e
#apk add --no-cache py-pip python-dev libffi-dev openssl-dev gcc libc-dev make curl
#pip install docker-compose
cd sb-sample-service
docker-compose up -d
sleep 30
curl -X POST "http://docker:8080/api/user/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"id\": 1, \"name\": \"Mike\"}"
curl -X GET "http://docker:8080/api/usr/1" -H "accept: */*"
docker-compose down
