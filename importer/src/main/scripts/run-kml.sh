#!/bin/sh
java -Xms1G -Xmx2G -XX:+UseConcMarkSweepGC -jar geoph-import-1.0-SNAPSHOT.jar --type=kml --path=/opt/geoph/kml
