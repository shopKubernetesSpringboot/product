#!/bin/bash
set -o xtrace
./gradlew build -Pprofile=prod -x test
docker build -t techtests/shopproduct .
docker tag techtests/shopproduct:latest davidgfolch/shop-product:latest
docker push davidgfolch/shop-product