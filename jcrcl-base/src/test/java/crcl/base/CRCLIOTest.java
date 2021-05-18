/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl.base;

import crcl.base.exceptions.CRCLException;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author peter
 */
public class CRCLIOTest {

    @Test
    public void test() throws IOException, CRCLException {
        CRCLIO.readProgram(ClassLoader.getSystemResourceAsStream("forceTorqueStatusExample.xml")).getMiddleCommand().forEach(command -> System.out.println(command));
        CRCLIO.readProgram(ClassLoader.getSystemResourceAsStream("MOVE_ALL_RED_PEGS.xml")).getMiddleCommand().forEach(command -> System.out.println(command));
        CRCLIO.readProgram(ClassLoader.getSystemResourceAsStream("programAll.xml")).getMiddleCommand().forEach(command -> System.out.println(command));
        CRCLIO.readProgram(ClassLoader.getSystemResourceAsStream("programExample.xml")).getMiddleCommand().forEach(command -> System.out.println(command));
        CRCLIO.readProgram(ClassLoader.getSystemResourceAsStream("test2Program.xml")).getMiddleCommand().forEach(command -> System.out.println(command));
    }
}
