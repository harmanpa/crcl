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
package com.github.wshackle.crcl4java.vaadin.webapp;

import crcl.base.CRCLProgramType;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ProgramInfo {

    final private String[] remotePrograms;

    /**
     * Get the value of remotePrograms
     *
     * @return the value of remotePrograms
     */
    public String[] getRemotePrograms() {
        return remotePrograms;
    }

    /**
     * Get the value of remotePrograms at specified index
     *
     * @param index the index of remotePrograms
     * @return the value of remotePrograms at specified index
     */
    public String getRemotePrograms(int index) {
        return this.remotePrograms[index];
    }

    final private String currentFileName;

    /**
     * Get the value of currentFileName
     *
     * @return the value of currentFileName
     */
    public String getCurrentFileName() {
        return currentFileName;
    }

    final private CRCLProgramType currentProgram;

    /**
     * Get the value of currentProgram
     *
     * @return the value of currentProgram
     */
    public CRCLProgramType getCurrentProgram() {
        return currentProgram;
    }

    final private int programIndex;

    /**
     * Get the value of programIndex
     *
     * @return the value of programIndex
     */
    public int getProgramIndex() {
        return programIndex;
    }

    public ProgramInfo(String[] remotePrograms, String currentFileName, CRCLProgramType currentProgram, int programIndex) {
        this.remotePrograms = remotePrograms;
        this.currentFileName = currentFileName;
        this.currentProgram = currentProgram;
        this.programIndex = programIndex;
    }

}
