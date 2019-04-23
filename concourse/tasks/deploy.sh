#!/bin/sh -xe
source /docker-lib.sh
start_docker

cd sb-sample-service
docker-compose up -d
sleep 30
docker ps
docker-compose down
