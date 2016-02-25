
C++ can use Java libraries through JNI or more conveniently through java4cpp.
https://github.com/wshackle/java4cpp

Generate wrapper C++ files with:

java -jar java4cpp-1.0-20150909.152549-3-jar-with-dependencies.jar -p crcl -n crclj -j ../../crcl4java-utils/target/crcl4java-utils-1.0-SNAPSHOT-jar-with-dependencies.jar;

Compile with:

g++ -O0 -g -I "${JAVA_HOME}/include"  -I "${JAVA_HOME}/include/linux" crclj*.cpp use_crcl.cpp  -L "${JVM_LIBDIR}" -Wl,--rpath "${JVM_LIBDIR}" -ljvm -o crclj_test

See build.sh for details.

