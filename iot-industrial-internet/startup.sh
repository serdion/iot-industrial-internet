#!/bin/bash
exec java -Dserver.port=8083 -Dserver.contextPath=/iot-master -jar target/iiframework-0.0.1-DEV.jar 2>> ../logs/errorlog >> ../logs/log

