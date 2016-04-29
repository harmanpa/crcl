


C++ can use Java libraries through JNI or more conveniently through java4cpp.
https://github.com/wshackle/java4cpp.


The example can be built with the build.sh script or by following these instructions:

Install Java 8 from http://java.oracle.com/.


Download 
	 http://repo.maven.apache.org/maven2/com/github/wshackle/java4cpp/1.1/java4cpp-1.1-jar-with-dependencies.jar 
	 
	 and

	 https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar

into the current directory.


Generate wrapper C++ files with:

java -jar java4cpp-1.1-jar-with-dependencies.jar -p crcl -n crclj -j crcl4java-utils-1.3-jar-with-dependencies.jar

Set the environment variable JAVA_HOME to the location of your java installation.( eg /usr/lib/jvm/java-8-oracle/. 

Set the JVM_LIBDIR variable to the sub-directory of JAVA_HOME that contains libjvm.so. (eg /usr/lib/jvm/java-8-oracle/jre/lib/amd64/server/ )

Compile with:

g++ -O0 -g -I "${JAVA_HOME}/include"  -I "${JAVA_HOME}/include/linux" crclj*.cpp use_crcl.cpp  -L "${JVM_LIBDIR}" -Wl,--rpath "${JVM_LIBDIR}" -ljvm -o crclj_test

See build.sh for details.


Before running a client you will need to have started a server.  
The scripts runJavaGuiServer.sh (for Linux) or runJavaGuiServer.bat (for Windows)
provide an option for a server to start.

For a reference for all classes and functions available download and unzip:


    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-javadoc.jar
    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-base/1.3/crcl4java-base-1.3-javadoc.jar

Or use the scripts showDoc.sh / showDoc.bat to download and open the docs.
The scripts will open two tabs. One for utils packages and one for the base package.
