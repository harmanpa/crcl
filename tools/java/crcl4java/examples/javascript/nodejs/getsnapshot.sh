#!/bin/sh

set -x;

snapshotjarfile="crcl4java-utils-1.4-20160422.130648-1-jar-with-dependencies.jar";
if ! test -f "${snapshotjarfile}" ; then
    snapshotremotejarurl="https://oss.sonatype.org/content/repositories/snapshots/com/github/wshackle/crcl4java-utils/1.4-SNAPSHOT/crcl4java-utils-1.4-20160422.130648-1-jar-with-dependencies.jar";
    echo "Downloading ${snapshotremotejarurl}";
    wget "${snapshotremotejarurl}"
fi

