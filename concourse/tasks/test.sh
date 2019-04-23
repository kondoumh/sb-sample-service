#!/bin/sh -xe
source /docker-lib.sh
start_docker
hostname
hostname -i
cd sb-sample-service
docker-compose up -d
sleep 60
curl -X POST "http://127.0.0.1:8080/api/user/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"id\": 1, \"name\": \"Mike\"}"
curl -X GET "http://127.0.0.1:8080/api/usr/1" -H "accept: */*"
docker-compose down
