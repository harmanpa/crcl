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
 *  This software is experimental. NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgement if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.utils;

import crcl.base.CRCLStatusType;
import java.util.logging.Logger;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmPose;
import rcs.posemath.PmQuaternion;

public class AnnotatedPose extends PmPose {
    private static final Logger LOG = Logger.getLogger(AnnotatedPose.class.getName());

    private final long time;
    private final long lastCommandIdSent;
    private final String commandName;
    private final CRCLStatusType status;

    /**
     *
     * @param time the value of time
     * @param lastCommandIdSent the value of lastCommandIdSent
     * @param commandName the value of commandName
     * @param starttran the value of starttran
     * @param startrot the value of startrot
     * @param status the value of status
     */
    public AnnotatedPose(
            long time, long lastCommandIdSent, String commandName, PmCartesian starttran, PmQuaternion startrot, CRCLStatusType status) {
        super(starttran, startrot);
        this.time = time;
        this.lastCommandIdSent = lastCommandIdSent;
        this.commandName = commandName;
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public long getLastCommandIdSent() {
        return lastCommandIdSent;
    }

    public String getCommandName() {
        return commandName;
    }

    public PmCartesian getTran() {
        return tran;
    }

    public PmQuaternion getRot() {
        return rot;
    }

    public CRCLStatusType getStatus() {
        return status;
    }

    @Override
    public AnnotatedPose clone() {
        return new AnnotatedPose(time, lastCommandIdSent, commandName, tran, rot, status);
    }

}
