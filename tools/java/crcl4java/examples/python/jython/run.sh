#!/bin/sh

set -x;

export CLASSPATH=../../../crcl4java-utils/target/crcl4java-utils-1.0-SNAPSHOT-jar-with-dependencies.jar
jython crclclient.py

