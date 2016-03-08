#!/bin/sh
java -DCONF_PATH=/opt/geoph/geoph-api  -DLOG_PATH=/opt/geoph/geoph-api   -Xms1G -Xmx2G -XX:+UseConcMarkSweepGC -jar /opt/geoph/geoph-api/geoph-api-standalone-1.0-SNAPSHOT.jar
