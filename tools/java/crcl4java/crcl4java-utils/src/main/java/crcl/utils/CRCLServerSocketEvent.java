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

import crcl.base.CRCLCommandInstanceType;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CRCLServerSocketEvent {

    
    private final CRCLSocket source;

    /**
     * Get the value of source
     *
     * @return the value of source
     */
    public CRCLSocket getSource() {
        return source;
    }

    private final CRCLCommandInstanceType instance;

    /**
     * Get the value of instance
     *
     * @return the value of instance
     */
    public CRCLCommandInstanceType getInstance() {
        return instance;
    }

    final private Exception exception;

    /**
     * Get the value of exception
     *
     * @return the value of exception
     */
    public Exception getException() {
        return exception;
    }

    public CRCLServerSocketEvent(CRCLSocket source, CRCLCommandInstanceType instance, Exception exception) {
        this.source = source;
        this.instance = instance;
        this.exception = exception;
    }

}
