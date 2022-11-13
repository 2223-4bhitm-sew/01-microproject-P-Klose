#!/usr/bin/env bash

pushd backend
./mvnw clean package
#mvn -Dmaven.test.skip=true clean package
popd
