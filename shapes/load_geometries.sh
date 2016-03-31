#!/bin/sh
tar -xvf ./region_geometry.tar.gz
psql -Upostgres -d geoph -c "CREATE EXTENSION postgis"
psql -Upostgres -d geoph -f ./region_geometry.sql


