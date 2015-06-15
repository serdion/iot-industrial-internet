#!/bin/bash
exec nohup java -Dserver.port=8083 -Dserver.contextPath=/iot-master -jar target/iiframework-0.0.1-DEV.jar < /dev/null 2>> ../logs/errorlog >> ../logs/log

