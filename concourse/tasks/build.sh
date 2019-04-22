#!/bin/sh
set -e
sb-sample-service/mvnw package
cp sb-sample-service/target/sb-sample-service.jar build-output/