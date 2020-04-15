#!/bin/bash
set -o xtrace
docker build -t techtests/shopproduct-mongo . -f Dockerfile
docker tag techtests/shopproduct-mongo:latest davidgfolch/shop-product-mongo:latest
docker push davidgfolch/shop-product-mongo
