#!/usr/bin/env bash

./postgres-stop.sh

./postgres-create-db.sh
./postgres-start.sh
./mvnw quarkus:dev clean

