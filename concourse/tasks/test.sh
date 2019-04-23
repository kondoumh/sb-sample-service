#!/bin/sh
set -e
source /docker-lib.sh
start_docker

cd sb-sample-service
docker-compose up -d
sleep 60
curl -X POST "http://localhost:8080/api/user/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"id\": 1, \"name\": \"Mike\"}"
curl -X GET "http://localhost:8080/api/usr/1" -H "accept: */*"
docker-compose down
