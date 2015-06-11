#!/bin/sh
SERVICE_NAME=IIFramework
PATH_TO_JAR='target/iiframework-0.0.1-DEV.jar'
JAVA_OPTIONS='-Dserver.port=8083 -Dserver.contextPath=/iot-master'
PID_PATH_NAME=/tmp/IIFramework-pid

cd iot-industrial-internet

case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java $JAVA_OPTIONS -jar $PATH_TO_JAR /tmp 2>> ../logs/errorlog >> ../logs/log &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java $JAVA_OPTIONS -jar $PATH_TO_JAR /tmp 2>> ../logs/errorlog >> ../logs/log &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac
