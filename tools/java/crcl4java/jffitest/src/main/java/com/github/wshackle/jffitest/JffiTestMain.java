/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wshackle.jffitest;

import java.nio.ByteBuffer;
import jnr.constants.platform.Sysconf;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Variable;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.types.intptr_t;
import jnr.posix.FileStat;
import jnr.posix.LibC;
import jnr.posix.MsgHdr;
import jnr.posix.NativeGroup;
import jnr.posix.NativePasswd;
import jnr.posix.NativeTimes;
import jnr.posix.RLimit;
import jnr.posix.Timeval;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class JffiTestMain {

    public static interface LibAdd {

        int add(int i, int j);
    }

    public static interface MyLibC {

        public interface MyLibCSignalHandler {

            @Delegate
            void signal(int sig);
        }

        @intptr_t
        long signal(int sig, MyLibCSignalHandler handler);
    }

    static boolean quit = false;

    public static void main(String[] args) throws InterruptedException {
        LibAdd libadd = LibraryLoader.create(LibAdd.class)
                .mapper(new FunctionMapper() {
                    @Override
                    public String mapFunctionName(String functionName, FunctionMapper.Context context) {
                        System.out.println("context = " + context);
                        System.out.println("functionName = " + functionName);
                        if (functionName.equals("add")) {
                            return "_Z3addii";
                        }
                        return functionName;
                    }
                })
                .search(System.getProperty("user.home")).load("add");

        int out = libadd.add(2, 4);
        System.out.println("out = " + out);
        MyLibC libc = LibraryLoader.create(MyLibC.class).load("c");
        libc.signal(2, new MyLibC.MyLibCSignalHandler() {
            @Override
            public void signal(int sig) {
                System.out.println("sig = " + sig);
                quit = true;
            }
        });
        int count = 0;
        while (!quit) {
            count++;
            System.out.println("count = " + count);
            Thread.sleep(200);
        }

    }

}
