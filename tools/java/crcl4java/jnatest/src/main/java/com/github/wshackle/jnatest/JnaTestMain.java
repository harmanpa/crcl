/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wshackle.jnatest;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class JnaTestMain {

    public interface CLibrary extends Library {

        CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"),
                CLibrary.class);

        void printf(String format, Object... args);
        int SIGUSR1 = 30;

        interface sig_t extends Callback {

            void invoke(int signal);
        }

        sig_t signal(int sig, sig_t fn);

        int raise(int sig);
    }

     static boolean quit = false;
        
    public static void main(String[] args) throws InterruptedException {
        CLibrary.INSTANCE.printf("Hello, World\n");
        args = new String[]{"arg1", "arg2"};
        for (int i = 0; i < args.length; i++) {
            CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }
       
        CLibrary.sig_t sighandler =  new CLibrary.sig_t() {
            @Override
            public void invoke(int signal) {
                System.out.println("signal = " + signal);
                quit = true;
            }
        };
        for (int i = 1; i <63; i++) {
            CLibrary.INSTANCE.signal(1, sighandler);
        }
        int count = 0;
        while(!quit) {
            System.out.println("count = " + count);
            Thread.sleep(200);
        }
    }

}
