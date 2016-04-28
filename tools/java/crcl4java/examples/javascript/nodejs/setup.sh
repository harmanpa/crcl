#!/bin/sh

# This script was tested on Ubuntu 14.04
set -x;


sudo apt-get install wget nodejs npm openjdk-7-jdk


# Note in Ubuntu and Debian: "node" refers to a program that is unrelated to nodejs
# but on other platforms "node" is the name of the nodejs executable.
# Unfortunately one of the dependant npm install scripts refers to "node" 
# and has to be tricked into using the correct nodejs executable by moving
# the original program and setting up a symbolic link.
if test -f /usr/bin/node ; then
    sudo mv /usr/bin/node /usr/bin/node.original;
fi

sudo ln -s /usr/bin/nodejs /usr/bin/node

sudo npm install java

sudo rm /usr/bin/node
if test -f /usr/bin/node.original ; then
    sudo mv /usr/bin/node.original /usr/bin/node;
fi
sudo chown -R `whoami` node_modules 

jarfile="crcl4java-utils-1.3-jar-with-dependencies.jar"
if ! test -f "${jarfile}" ; then
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
fi
