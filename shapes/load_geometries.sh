#!/bin/sh
tar -xvf ./admLevels.tar.gz
psql -Upostgres -d geoph -c "CREATE EXTENSION postgis"
psql -Upostgres -d geoph -f ./admLevels.sql


