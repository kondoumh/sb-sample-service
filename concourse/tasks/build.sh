#!bin/sh -xe
pwd
ls -l
sb-sample-service/mvnw package
cp sb-sample-service/target/sb-sample-service.jar build-output/