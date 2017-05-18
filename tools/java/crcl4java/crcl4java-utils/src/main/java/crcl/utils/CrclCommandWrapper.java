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

import crcl.base.CRCLProgramType;
import crcl.base.MessageType;
import crcl.base.MiddleCommandType;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CrclCommandWrapper extends MessageType {

    public static interface CRCLCommandWrapperConsumer {

        public void accept(CrclCommandWrapper wrapper);
    }
    private MiddleCommandType wrappedCommand;
    private CRCLProgramType curProgram = null;
    private int curProgramIndex = -1;

    public int getCurProgramIndex() {
        return curProgramIndex;
    }

    public void setCurProgramIndex(int curProgramIndex) {
        this.curProgramIndex = curProgramIndex;
    }
    
    
    public CRCLProgramType getCurProgram() {
        return curProgram;
    }

    public void setCurProgram(CRCLProgramType curProgram) {
        this.curProgram = curProgram;
    }

    private CrclCommandWrapper(MiddleCommandType wrappedCommand) {
        this.wrappedCommand = wrappedCommand;
        this.commandID = wrappedCommand.getCommandID();
        this.name = wrappedCommand.getName();
        if (wrappedCommand instanceof MessageType) {
            this.message = ((MessageType) wrappedCommand).getMessage();
        }
    }

    public static CrclCommandWrapper wrapWithOnDone(MiddleCommandType wrappedCommand,
            CRCLCommandWrapperConsumer listener) {
        CrclCommandWrapper cmd = new CrclCommandWrapper(wrappedCommand);
        cmd.addOnDoneListener(listener);
        return cmd;
    }

    public static CrclCommandWrapper wrapWithOnStart(MiddleCommandType wrappedCommand,
            CRCLCommandWrapperConsumer listener) {
        CrclCommandWrapper cmd = new CrclCommandWrapper(wrappedCommand);
        cmd.addOnStartListener(listener);
        return cmd;
    }

    public static CrclCommandWrapper wrapWithOnError(MiddleCommandType wrappedCommand,
            CRCLCommandWrapperConsumer listener) {
        CrclCommandWrapper cmd = new CrclCommandWrapper(wrappedCommand);
        cmd.addOnErrorListener(listener);
        return cmd;
    }

    public static CrclCommandWrapper wrap(MiddleCommandType wrappedCommand,
            CRCLCommandWrapperConsumer onStartListener,
            CRCLCommandWrapperConsumer onDoneListener,
            CRCLCommandWrapperConsumer onErrorListener) {
        CrclCommandWrapper cmd = new CrclCommandWrapper(wrappedCommand);
        cmd.addOnStartListener(onStartListener);
        cmd.addOnDoneListener(onDoneListener);
        cmd.addOnErrorListener(onErrorListener);
        return cmd;
    }

    public MiddleCommandType getWrappedCommand() {
        return wrappedCommand;
    }

    public void setWrappedCommand(MiddleCommandType wrappedCommand) {
        this.wrappedCommand = wrappedCommand;
    }

    @Override
    public void setCommandID(long value) {
        wrappedCommand.setCommandID(value);
        this.commandID = wrappedCommand.getCommandID();
    }

    @Override
    public long getCommandID() {
        return wrappedCommand.getCommandID();
    }

    @Override
    public void setName(String value) {
        wrappedCommand.setName(value);
        this.name = wrappedCommand.getName();
    }

    @Override
    public String getName() {
        return wrappedCommand.getName();
    }

    private final ConcurrentLinkedDeque<CRCLCommandWrapperConsumer> onStartListeners
            = new ConcurrentLinkedDeque<>();

    public void addOnStartListener(CRCLCommandWrapperConsumer consumer) {
        onStartListeners.add(consumer);
    }

    public void removeOnStartListener(CRCLCommandWrapperConsumer consumer) {
        onStartListeners.add(consumer);
    }

    public void notifyOnStartListeners() {
        for (CRCLCommandWrapperConsumer consumer : onStartListeners) {
            consumer.accept(this);
        }
    }

    private final ConcurrentLinkedDeque<CRCLCommandWrapperConsumer> onDoneListeners
            = new ConcurrentLinkedDeque<>();

    public void addOnDoneListener(CRCLCommandWrapperConsumer consumer) {
        onDoneListeners.add(consumer);
    }

    public void removeOnDoneListener(CRCLCommandWrapperConsumer consumer) {
        onDoneListeners.add(consumer);
    }

    public void notifyOnDoneListeners() {
        for (CRCLCommandWrapperConsumer consumer : onDoneListeners) {
            consumer.accept(this);
        }
    }

    private final ConcurrentLinkedDeque<CRCLCommandWrapperConsumer> onErrorListeners
            = new ConcurrentLinkedDeque<>();

    public void addOnErrorListener(CRCLCommandWrapperConsumer consumer) {
        onErrorListeners.add(consumer);
    }

    public void removeOnErrorListener(CRCLCommandWrapperConsumer consumer) {
        onErrorListeners.add(consumer);
    }

    public void notifyOnErrorListeners() {
        for (CRCLCommandWrapperConsumer consumer : onErrorListeners) {
            consumer.accept(this);
        }
    }
}
