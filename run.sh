#!/bin/bash

# Java 21 사용
export JAVA_HOME=$(/usr/libexec/java_home -v 21)

echo "Using JAVA_HOME: $JAVA_HOME"
echo "Starting Shared Calendar BE Server..."

# 프로젝트 디렉터리로 이동
cd "$(dirname "$0")"

# 빌드 및 실행
gradle bootRun
