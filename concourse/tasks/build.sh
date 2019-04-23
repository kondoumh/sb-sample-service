#!/bin/sh
set -e
(
  cd sb-sample-service
  ./mvnw package
)
#cp sb-sample-service/target/sb-sample-service.jar target/