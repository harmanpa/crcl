/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 *@author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class Utils {

    private Utils() {
    }

    public static String traceToString(StackTraceElement trace @Nullable []) {
        if (null == trace) {
            return "";
        }
        try (StringWriter stringWriter = new StringWriter()) {
            try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
                boolean first = true;
                for (StackTraceElement traceElement : trace) {
                    String traceString = traceElement.toString();
                    if (first && traceString.contains("Thread.getStackTrace(")) {
                        first = false;
                        continue;
                    }
                    first = false;
                    printWriter.println("\tat " + traceElement);
                }
            }
            stringWriter.flush();
            return stringWriter.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getCrclUserHomeDir() {
        boolean isWindows = System.getProperty("os.name").startsWith("Windows");

        String dir;
        if (isWindows) {
            dir = System.getProperty("windows.crcl.user.home", System.getProperty("crcl.user.home", System.getProperty("user.home")));
        } else {
            dir = System.getProperty("linux.crcl.user.home", System.getProperty("crcl.user.home", System.getProperty("user.home")));
        }
//        if(!dir.endsWith("netbeans_run_user_home")) {
//            System.out.println("System.getProperty(\"user.home\") = " + System.getProperty("user.home"));
//            System.out.println("System.getProperty(\"crcl.user.home\") = " + System.getProperty("crcl.user.home"));
//            System.out.println("dir = " + dir);
//            Properties props = System.getProperties();
//            props.list(System.out);
//        }
        return dir;
    }

}
