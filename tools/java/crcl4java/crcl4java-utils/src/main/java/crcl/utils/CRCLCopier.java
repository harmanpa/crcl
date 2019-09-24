/*
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.utils;

import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CloseToolChangerType;
import crcl.base.CommandStatusType;
import crcl.base.ConfigureJointReportType;
import crcl.base.ConfigureJointReportsType;
import crcl.base.ConfigureStatusReportType;
import crcl.base.CountSensorStatusType;
import crcl.base.DataThingType;
import crcl.base.DisableGripperType;
import crcl.base.DisableRobotParameterStatusType;
import crcl.base.DisableSensorType;
import crcl.base.DwellType;
import crcl.base.EnableGripperType;
import crcl.base.EnableRobotParameterStatusType;
import crcl.base.EnableSensorType;
import crcl.base.EndCanonType;
import crcl.base.ForceTorqueSensorStatusType;
import crcl.base.GetStatusType;
import crcl.base.GripperStatusType;
import crcl.base.GuardType;
import crcl.base.GuardsStatusesType;
import crcl.base.InitCanonType;
import crcl.base.JointDetailsType;
import crcl.base.JointForceTorqueType;
import crcl.base.JointLimitType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MessageType;
import crcl.base.MiddleCommandType;
import crcl.base.MoveScrewType;
import crcl.base.MoveThroughToType;
import crcl.base.MoveToType;
import crcl.base.OnOffSensorStatusType;
import crcl.base.OpenToolChangerType;
import crcl.base.ParallelGripperStatusType;
import crcl.base.ParameterSettingType;
import crcl.base.PointType;
import crcl.base.PoseAndSetType;
import crcl.base.PoseStatusType;
import crcl.base.PoseToleranceType;
import crcl.base.PoseType;
import crcl.base.RotAccelAbsoluteType;
import crcl.base.RotAccelRelativeType;
import crcl.base.RotAccelType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedRelativeType;
import crcl.base.RotSpeedType;
import crcl.base.RunProgramType;
import crcl.base.ScalarSensorStatusType;
import crcl.base.SensorStatusType;
import crcl.base.SensorStatusesType;
import crcl.base.SetAngleUnitsType;
import crcl.base.SetEndEffectorParametersType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetEndPoseToleranceType;
import crcl.base.SetForceUnitsType;
import crcl.base.SetIntermediatePoseToleranceType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetMotionCoordinationType;
import crcl.base.SetRobotParametersType;
import crcl.base.SetRotAccelType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTorqueUnitsType;
import crcl.base.SetTransAccelType;
import crcl.base.SetTransSpeedType;
import crcl.base.SettingsStatusType;
import crcl.base.StopMotionType;
import crcl.base.ThreeFingerGripperStatusType;
import crcl.base.TransAccelAbsoluteType;
import crcl.base.TransAccelRelativeType;
import crcl.base.TransAccelType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.base.TwistType;
import crcl.base.VacuumGripperStatusType;
import crcl.base.VectorType;
import crcl.base.WrenchType;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;

public class CRCLCopier {

    public static @Nullable
    DataThingType copy(@Nullable DataThingType in) {
        if(in != null) {
            DataThingType out;
            if(in instanceof WrenchType) {
                out = copy((WrenchType)in);
            } else if(in instanceof EnableGripperType) {
                out = copy((EnableGripperType)in);
            } else if(in instanceof MoveToType) {
                out = copy((MoveToType)in);
            } else if(in instanceof GuardType) {
                out = copy((GuardType)in);
            } else if(in instanceof RotAccelAbsoluteType) {
                out = copy((RotAccelAbsoluteType)in);
            } else if(in instanceof SetEndEffectorType) {
                out = copy((SetEndEffectorType)in);
            } else if(in instanceof TransAccelRelativeType) {
                out = copy((TransAccelRelativeType)in);
            } else if(in instanceof MessageType) {
                out = copy((MessageType)in);
            } else if(in instanceof CommandStatusType) {
                out = copy((CommandStatusType)in);
            } else if(in instanceof ConfigureJointReportsType) {
                out = copy((ConfigureJointReportsType)in);
            } else if(in instanceof DisableSensorType) {
                out = copy((DisableSensorType)in);
            } else if(in instanceof RotAccelRelativeType) {
                out = copy((RotAccelRelativeType)in);
            } else if(in instanceof SetEndPoseToleranceType) {
                out = copy((SetEndPoseToleranceType)in);
            } else if(in instanceof ConfigureJointReportType) {
                out = copy((ConfigureJointReportType)in);
            } else if(in instanceof PoseStatusType) {
                out = copy((PoseStatusType)in);
            } else if(in instanceof JointSpeedAccelType) {
                out = copy((JointSpeedAccelType)in);
            } else if(in instanceof ParallelGripperStatusType) {
                out = copy((ParallelGripperStatusType)in);
            } else if(in instanceof MoveThroughToType) {
                out = copy((MoveThroughToType)in);
            } else if(in instanceof ScalarSensorStatusType) {
                out = copy((ScalarSensorStatusType)in);
            } else if(in instanceof SetTransSpeedType) {
                out = copy((SetTransSpeedType)in);
            } else if(in instanceof SetEndEffectorParametersType) {
                out = copy((SetEndEffectorParametersType)in);
            } else if(in instanceof SetTorqueUnitsType) {
                out = copy((SetTorqueUnitsType)in);
            } else if(in instanceof EnableRobotParameterStatusType) {
                out = copy((EnableRobotParameterStatusType)in);
            } else if(in instanceof CRCLCommandInstanceType) {
                out = copy((CRCLCommandInstanceType)in);
            } else if(in instanceof GetStatusType) {
                out = copy((GetStatusType)in);
            } else if(in instanceof TransAccelAbsoluteType) {
                out = copy((TransAccelAbsoluteType)in);
            } else if(in instanceof JointStatusType) {
                out = copy((JointStatusType)in);
            } else if(in instanceof SetForceUnitsType) {
                out = copy((SetForceUnitsType)in);
            } else if(in instanceof MoveScrewType) {
                out = copy((MoveScrewType)in);
            } else if(in instanceof TwistType) {
                out = copy((TwistType)in);
            } else if(in instanceof SetMotionCoordinationType) {
                out = copy((SetMotionCoordinationType)in);
            } else if(in instanceof DisableGripperType) {
                out = copy((DisableGripperType)in);
            } else if(in instanceof OnOffSensorStatusType) {
                out = copy((OnOffSensorStatusType)in);
            } else if(in instanceof TransSpeedAbsoluteType) {
                out = copy((TransSpeedAbsoluteType)in);
            } else if(in instanceof ActuateJointType) {
                out = copy((ActuateJointType)in);
            } else if(in instanceof SetRotAccelType) {
                out = copy((SetRotAccelType)in);
            } else if(in instanceof ParameterSettingType) {
                out = copy((ParameterSettingType)in);
            } else if(in instanceof PoseToleranceType) {
                out = copy((PoseToleranceType)in);
            } else if(in instanceof CRCLStatusType) {
                out = copy((CRCLStatusType)in);
            } else if(in instanceof SetTransAccelType) {
                out = copy((SetTransAccelType)in);
            } else if(in instanceof RotSpeedAbsoluteType) {
                out = copy((RotSpeedAbsoluteType)in);
            } else if(in instanceof SetRotSpeedType) {
                out = copy((SetRotSpeedType)in);
            } else if(in instanceof SettingsStatusType) {
                out = copy((SettingsStatusType)in);
            } else if(in instanceof ThreeFingerGripperStatusType) {
                out = copy((ThreeFingerGripperStatusType)in);
            } else if(in instanceof EndCanonType) {
                out = copy((EndCanonType)in);
            } else if(in instanceof SetLengthUnitsType) {
                out = copy((SetLengthUnitsType)in);
            } else if(in instanceof JointLimitType) {
                out = copy((JointLimitType)in);
            } else if(in instanceof StopMotionType) {
                out = copy((StopMotionType)in);
            } else if(in instanceof JointStatusesType) {
                out = copy((JointStatusesType)in);
            } else if(in instanceof VectorType) {
                out = copy((VectorType)in);
            } else if(in instanceof DwellType) {
                out = copy((DwellType)in);
            } else if(in instanceof PointType) {
                out = copy((PointType)in);
            } else if(in instanceof InitCanonType) {
                out = copy((InitCanonType)in);
            } else if(in instanceof OpenToolChangerType) {
                out = copy((OpenToolChangerType)in);
            } else if(in instanceof CountSensorStatusType) {
                out = copy((CountSensorStatusType)in);
            } else if(in instanceof DisableRobotParameterStatusType) {
                out = copy((DisableRobotParameterStatusType)in);
            } else if(in instanceof CRCLProgramType) {
                out = copy((CRCLProgramType)in);
            } else if(in instanceof ActuateJointsType) {
                out = copy((ActuateJointsType)in);
            } else if(in instanceof SetIntermediatePoseToleranceType) {
                out = copy((SetIntermediatePoseToleranceType)in);
            } else if(in instanceof TransSpeedRelativeType) {
                out = copy((TransSpeedRelativeType)in);
            } else if(in instanceof RotSpeedRelativeType) {
                out = copy((RotSpeedRelativeType)in);
            } else if(in instanceof CloseToolChangerType) {
                out = copy((CloseToolChangerType)in);
            } else if(in instanceof GuardsStatusesType) {
                out = copy((GuardsStatusesType)in);
            } else if(in instanceof RunProgramType) {
                out = copy((RunProgramType)in);
            } else if(in instanceof SetRobotParametersType) {
                out = copy((SetRobotParametersType)in);
            } else if(in instanceof PoseAndSetType) {
                out = copy((PoseAndSetType)in);
            } else if(in instanceof PoseType) {
                out = copy((PoseType)in);
            } else if(in instanceof VacuumGripperStatusType) {
                out = copy((VacuumGripperStatusType)in);
            } else if(in instanceof ConfigureStatusReportType) {
                out = copy((ConfigureStatusReportType)in);
            } else if(in instanceof JointForceTorqueType) {
                out = copy((JointForceTorqueType)in);
            } else if(in instanceof SensorStatusesType) {
                out = copy((SensorStatusesType)in);
            } else if(in instanceof SetAngleUnitsType) {
                out = copy((SetAngleUnitsType)in);
            } else if(in instanceof ForceTorqueSensorStatusType) {
                out = copy((ForceTorqueSensorStatusType)in);
            } else if(in instanceof SensorStatusType) {
                out = copy((SensorStatusType)in);
            } else if(in instanceof EnableSensorType) {
                out = copy((EnableSensorType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    CRCLCommandType copy(@Nullable CRCLCommandType in) {
        if(in != null) {
            CRCLCommandType out;
            if(in instanceof EnableGripperType) {
                out = copy((EnableGripperType)in);
            } else if(in instanceof MoveToType) {
                out = copy((MoveToType)in);
            } else if(in instanceof SetEndEffectorType) {
                out = copy((SetEndEffectorType)in);
            } else if(in instanceof MessageType) {
                out = copy((MessageType)in);
            } else if(in instanceof ConfigureJointReportsType) {
                out = copy((ConfigureJointReportsType)in);
            } else if(in instanceof DisableSensorType) {
                out = copy((DisableSensorType)in);
            } else if(in instanceof SetEndPoseToleranceType) {
                out = copy((SetEndPoseToleranceType)in);
            } else if(in instanceof MoveThroughToType) {
                out = copy((MoveThroughToType)in);
            } else if(in instanceof SetTransSpeedType) {
                out = copy((SetTransSpeedType)in);
            } else if(in instanceof SetEndEffectorParametersType) {
                out = copy((SetEndEffectorParametersType)in);
            } else if(in instanceof SetTorqueUnitsType) {
                out = copy((SetTorqueUnitsType)in);
            } else if(in instanceof EnableRobotParameterStatusType) {
                out = copy((EnableRobotParameterStatusType)in);
            } else if(in instanceof GetStatusType) {
                out = copy((GetStatusType)in);
            } else if(in instanceof SetForceUnitsType) {
                out = copy((SetForceUnitsType)in);
            } else if(in instanceof MoveScrewType) {
                out = copy((MoveScrewType)in);
            } else if(in instanceof SetMotionCoordinationType) {
                out = copy((SetMotionCoordinationType)in);
            } else if(in instanceof DisableGripperType) {
                out = copy((DisableGripperType)in);
            } else if(in instanceof SetRotAccelType) {
                out = copy((SetRotAccelType)in);
            } else if(in instanceof SetTransAccelType) {
                out = copy((SetTransAccelType)in);
            } else if(in instanceof SetRotSpeedType) {
                out = copy((SetRotSpeedType)in);
            } else if(in instanceof EndCanonType) {
                out = copy((EndCanonType)in);
            } else if(in instanceof SetLengthUnitsType) {
                out = copy((SetLengthUnitsType)in);
            } else if(in instanceof StopMotionType) {
                out = copy((StopMotionType)in);
            } else if(in instanceof DwellType) {
                out = copy((DwellType)in);
            } else if(in instanceof InitCanonType) {
                out = copy((InitCanonType)in);
            } else if(in instanceof OpenToolChangerType) {
                out = copy((OpenToolChangerType)in);
            } else if(in instanceof DisableRobotParameterStatusType) {
                out = copy((DisableRobotParameterStatusType)in);
            } else if(in instanceof ActuateJointsType) {
                out = copy((ActuateJointsType)in);
            } else if(in instanceof SetIntermediatePoseToleranceType) {
                out = copy((SetIntermediatePoseToleranceType)in);
            } else if(in instanceof CloseToolChangerType) {
                out = copy((CloseToolChangerType)in);
            } else if(in instanceof RunProgramType) {
                out = copy((RunProgramType)in);
            } else if(in instanceof SetRobotParametersType) {
                out = copy((SetRobotParametersType)in);
            } else if(in instanceof ConfigureStatusReportType) {
                out = copy((ConfigureStatusReportType)in);
            } else if(in instanceof SetAngleUnitsType) {
                out = copy((SetAngleUnitsType)in);
            } else if(in instanceof EnableSensorType) {
                out = copy((EnableSensorType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    MiddleCommandType copy(@Nullable MiddleCommandType in) {
        if(in != null) {
            MiddleCommandType out;
            if(in instanceof EnableGripperType) {
                out = copy((EnableGripperType)in);
            } else if(in instanceof MoveToType) {
                out = copy((MoveToType)in);
            } else if(in instanceof SetEndEffectorType) {
                out = copy((SetEndEffectorType)in);
            } else if(in instanceof MessageType) {
                out = copy((MessageType)in);
            } else if(in instanceof ConfigureJointReportsType) {
                out = copy((ConfigureJointReportsType)in);
            } else if(in instanceof DisableSensorType) {
                out = copy((DisableSensorType)in);
            } else if(in instanceof SetEndPoseToleranceType) {
                out = copy((SetEndPoseToleranceType)in);
            } else if(in instanceof MoveThroughToType) {
                out = copy((MoveThroughToType)in);
            } else if(in instanceof SetTransSpeedType) {
                out = copy((SetTransSpeedType)in);
            } else if(in instanceof SetEndEffectorParametersType) {
                out = copy((SetEndEffectorParametersType)in);
            } else if(in instanceof SetTorqueUnitsType) {
                out = copy((SetTorqueUnitsType)in);
            } else if(in instanceof EnableRobotParameterStatusType) {
                out = copy((EnableRobotParameterStatusType)in);
            } else if(in instanceof GetStatusType) {
                out = copy((GetStatusType)in);
            } else if(in instanceof SetForceUnitsType) {
                out = copy((SetForceUnitsType)in);
            } else if(in instanceof MoveScrewType) {
                out = copy((MoveScrewType)in);
            } else if(in instanceof SetMotionCoordinationType) {
                out = copy((SetMotionCoordinationType)in);
            } else if(in instanceof DisableGripperType) {
                out = copy((DisableGripperType)in);
            } else if(in instanceof SetRotAccelType) {
                out = copy((SetRotAccelType)in);
            } else if(in instanceof SetTransAccelType) {
                out = copy((SetTransAccelType)in);
            } else if(in instanceof SetRotSpeedType) {
                out = copy((SetRotSpeedType)in);
            } else if(in instanceof SetLengthUnitsType) {
                out = copy((SetLengthUnitsType)in);
            } else if(in instanceof StopMotionType) {
                out = copy((StopMotionType)in);
            } else if(in instanceof DwellType) {
                out = copy((DwellType)in);
            } else if(in instanceof OpenToolChangerType) {
                out = copy((OpenToolChangerType)in);
            } else if(in instanceof DisableRobotParameterStatusType) {
                out = copy((DisableRobotParameterStatusType)in);
            } else if(in instanceof ActuateJointsType) {
                out = copy((ActuateJointsType)in);
            } else if(in instanceof SetIntermediatePoseToleranceType) {
                out = copy((SetIntermediatePoseToleranceType)in);
            } else if(in instanceof CloseToolChangerType) {
                out = copy((CloseToolChangerType)in);
            } else if(in instanceof RunProgramType) {
                out = copy((RunProgramType)in);
            } else if(in instanceof SetRobotParametersType) {
                out = copy((SetRobotParametersType)in);
            } else if(in instanceof ConfigureStatusReportType) {
                out = copy((ConfigureStatusReportType)in);
            } else if(in instanceof SetAngleUnitsType) {
                out = copy((SetAngleUnitsType)in);
            } else if(in instanceof EnableSensorType) {
                out = copy((EnableSensorType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    EnableSensorType copy(@Nullable EnableSensorType in) {
        if(in != null) {
            EnableSensorType out = new EnableSensorType();
            out.setSensorID(in.getSensorID());
            out.getSensorOption().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorSensorOption = in.getSensorOption().iterator();
            while(iteratorSensorOption.hasNext()) {
                out.getSensorOption().add(copy(iteratorSensorOption.next()));
            }
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SensorStatusType copy(@Nullable SensorStatusType in) {
        if(in != null) {
            SensorStatusType out;
            if(in instanceof ScalarSensorStatusType) {
                out = copy((ScalarSensorStatusType)in);
            } else if(in instanceof OnOffSensorStatusType) {
                out = copy((OnOffSensorStatusType)in);
            } else if(in instanceof CountSensorStatusType) {
                out = copy((CountSensorStatusType)in);
            } else if(in instanceof ForceTorqueSensorStatusType) {
                out = copy((ForceTorqueSensorStatusType)in);
            } else if(in instanceof SensorStatusType) {
                out = new SensorStatusType();
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setSensorID(in.getSensorID());
            out.setReadCount(in.getReadCount());
            out.setLastReadTime(in.getLastReadTime());
            out.getSensorParameterSetting().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
            while(iteratorSensorParameterSetting.hasNext()) {
                out.getSensorParameterSetting().add(copy(iteratorSensorParameterSetting.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ForceTorqueSensorStatusType copy(@Nullable ForceTorqueSensorStatusType in) {
        if(in != null) {
            ForceTorqueSensorStatusType out = new ForceTorqueSensorStatusType();
            out.setFx(in.getFx());
            out.setFy(in.getFy());
            out.setFz(in.getFz());
            out.setTx(in.getTx());
            out.setTy(in.getTy());
            out.setTz(in.getTz());
            out.setSensorID(in.getSensorID());
            out.setReadCount(in.getReadCount());
            out.setLastReadTime(in.getLastReadTime());
            out.getSensorParameterSetting().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
            while(iteratorSensorParameterSetting.hasNext()) {
                out.getSensorParameterSetting().add(copy(iteratorSensorParameterSetting.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetAngleUnitsType copy(@Nullable SetAngleUnitsType in) {
        if(in != null) {
            SetAngleUnitsType out = new SetAngleUnitsType();
            out.setUnitName(in.getUnitName());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SensorStatusesType copy(@Nullable SensorStatusesType in) {
        if(in != null) {
            SensorStatusesType out = new SensorStatusesType();
            out.getOnOffSensorStatus().clear();
            Iterator<crcl.base.OnOffSensorStatusType> iteratorOnOffSensorStatus = in.getOnOffSensorStatus().iterator();
            while(iteratorOnOffSensorStatus.hasNext()) {
                out.getOnOffSensorStatus().add(copy(iteratorOnOffSensorStatus.next()));
            }
            out.getScalarSensorStatus().clear();
            Iterator<crcl.base.ScalarSensorStatusType> iteratorScalarSensorStatus = in.getScalarSensorStatus().iterator();
            while(iteratorScalarSensorStatus.hasNext()) {
                out.getScalarSensorStatus().add(copy(iteratorScalarSensorStatus.next()));
            }
            out.getCountSensorStatus().clear();
            Iterator<crcl.base.CountSensorStatusType> iteratorCountSensorStatus = in.getCountSensorStatus().iterator();
            while(iteratorCountSensorStatus.hasNext()) {
                out.getCountSensorStatus().add(copy(iteratorCountSensorStatus.next()));
            }
            out.getForceTorqueSensorStatus().clear();
            Iterator<crcl.base.ForceTorqueSensorStatusType> iteratorForceTorqueSensorStatus = in.getForceTorqueSensorStatus().iterator();
            while(iteratorForceTorqueSensorStatus.hasNext()) {
                out.getForceTorqueSensorStatus().add(copy(iteratorForceTorqueSensorStatus.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    JointDetailsType copy(@Nullable JointDetailsType in) {
        if(in != null) {
            JointDetailsType out;
            if(in instanceof JointSpeedAccelType) {
                out = copy((JointSpeedAccelType)in);
            } else if(in instanceof JointForceTorqueType) {
                out = copy((JointForceTorqueType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    JointForceTorqueType copy(@Nullable JointForceTorqueType in) {
        if(in != null) {
            JointForceTorqueType out = new JointForceTorqueType();
            out.setSetting(in.getSetting());
            out.setChangeRate(in.getChangeRate());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ConfigureStatusReportType copy(@Nullable ConfigureStatusReportType in) {
        if(in != null) {
            ConfigureStatusReportType out = new ConfigureStatusReportType();
            out.setReportJointStatuses(in.isReportJointStatuses());
            out.setReportPoseStatus(in.isReportPoseStatus());
            out.setReportGripperStatus(in.isReportGripperStatus());
            out.setReportSettingsStatus(in.isReportSettingsStatus());
            out.setReportSensorsStatus(in.isReportSensorsStatus());
            out.setReportGuardsStatus(in.isReportGuardsStatus());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    GripperStatusType copy(@Nullable GripperStatusType in) {
        if(in != null) {
            GripperStatusType out;
            if(in instanceof ParallelGripperStatusType) {
                out = copy((ParallelGripperStatusType)in);
            } else if(in instanceof ThreeFingerGripperStatusType) {
                out = copy((ThreeFingerGripperStatusType)in);
            } else if(in instanceof VacuumGripperStatusType) {
                out = copy((VacuumGripperStatusType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setGripperName(in.getGripperName());
            out.getGripperOption().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorGripperOption = in.getGripperOption().iterator();
            while(iteratorGripperOption.hasNext()) {
                out.getGripperOption().add(copy(iteratorGripperOption.next()));
            }
            out.setHoldingObject(in.isHoldingObject());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    VacuumGripperStatusType copy(@Nullable VacuumGripperStatusType in) {
        if(in != null) {
            VacuumGripperStatusType out = new VacuumGripperStatusType();
            out.setIsPowered(in.isIsPowered());
            out.setGripperName(in.getGripperName());
            out.getGripperOption().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorGripperOption = in.getGripperOption().iterator();
            while(iteratorGripperOption.hasNext()) {
                out.getGripperOption().add(copy(iteratorGripperOption.next()));
            }
            out.setHoldingObject(in.isHoldingObject());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    PoseType copy(@Nullable PoseType in) {
        if(in != null) {
            PoseType out;
            if(in instanceof PoseAndSetType) {
                out = copy((PoseAndSetType)in);
            } else if(in instanceof PoseType) {
                out = new PoseType();
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setPoint(copy(in.getPoint()));
            out.setXAxis(copy(in.getXAxis()));
            out.setZAxis(copy(in.getZAxis()));
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    PoseAndSetType copy(@Nullable PoseAndSetType in) {
        if(in != null) {
            PoseAndSetType out = new PoseAndSetType();
            out.setCoordinated(in.isCoordinated());
            out.setTransSpeed(copy(in.getTransSpeed()));
            out.setRotSpeed(copy(in.getRotSpeed()));
            out.setTransAccel(copy(in.getTransAccel()));
            out.setRotAccel(copy(in.getRotAccel()));
            out.setTolerance(copy(in.getTolerance()));
            out.setPoint(copy(in.getPoint()));
            out.setXAxis(copy(in.getXAxis()));
            out.setZAxis(copy(in.getZAxis()));
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetRobotParametersType copy(@Nullable SetRobotParametersType in) {
        if(in != null) {
            SetRobotParametersType out = new SetRobotParametersType();
            out.getParameterSetting().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorParameterSetting = in.getParameterSetting().iterator();
            while(iteratorParameterSetting.hasNext()) {
                out.getParameterSetting().add(copy(iteratorParameterSetting.next()));
            }
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    RunProgramType copy(@Nullable RunProgramType in) {
        if(in != null) {
            RunProgramType out = new RunProgramType();
            out.setProgramText(in.getProgramText());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    GuardsStatusesType copy(@Nullable GuardsStatusesType in) {
        if(in != null) {
            GuardsStatusesType out = new GuardsStatusesType();
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setTriggerCount(in.getTriggerCount());
            out.setTriggerStopTimeMicros(in.getTriggerStopTimeMicros());
            out.setTriggerValue(in.getTriggerValue());
            out.setTriggerPose(copy(in.getTriggerPose()));
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    CloseToolChangerType copy(@Nullable CloseToolChangerType in) {
        if(in != null) {
            CloseToolChangerType out = new CloseToolChangerType();
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    RotSpeedType copy(@Nullable RotSpeedType in) {
        if(in != null) {
            RotSpeedType out;
            if(in instanceof RotSpeedAbsoluteType) {
                out = copy((RotSpeedAbsoluteType)in);
            } else if(in instanceof RotSpeedRelativeType) {
                out = copy((RotSpeedRelativeType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    RotSpeedRelativeType copy(@Nullable RotSpeedRelativeType in) {
        if(in != null) {
            RotSpeedRelativeType out = new RotSpeedRelativeType();
            out.setFraction(in.getFraction());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    TransSpeedType copy(@Nullable TransSpeedType in) {
        if(in != null) {
            TransSpeedType out;
            if(in instanceof TransSpeedAbsoluteType) {
                out = copy((TransSpeedAbsoluteType)in);
            } else if(in instanceof TransSpeedRelativeType) {
                out = copy((TransSpeedRelativeType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    TransSpeedRelativeType copy(@Nullable TransSpeedRelativeType in) {
        if(in != null) {
            TransSpeedRelativeType out = new TransSpeedRelativeType();
            out.setFraction(in.getFraction());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetIntermediatePoseToleranceType copy(@Nullable SetIntermediatePoseToleranceType in) {
        if(in != null) {
            SetIntermediatePoseToleranceType out = new SetIntermediatePoseToleranceType();
            out.setTolerance(copy(in.getTolerance()));
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ActuateJointsType copy(@Nullable ActuateJointsType in) {
        if(in != null) {
            ActuateJointsType out = new ActuateJointsType();
            out.getActuateJoint().clear();
            Iterator<crcl.base.ActuateJointType> iteratorActuateJoint = in.getActuateJoint().iterator();
            while(iteratorActuateJoint.hasNext()) {
                out.getActuateJoint().add(copy(iteratorActuateJoint.next()));
            }
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    CRCLProgramType copy(@Nullable CRCLProgramType in) {
        if(in != null) {
            CRCLProgramType out = new CRCLProgramType();
            out.setInitCanon(copy(in.getInitCanon()));
            out.getMiddleCommand().clear();
            Iterator<crcl.base.MiddleCommandType> iteratorMiddleCommand = in.getMiddleCommand().iterator();
            while(iteratorMiddleCommand.hasNext()) {
                out.getMiddleCommand().add(copy(iteratorMiddleCommand.next()));
            }
            out.setEndCanon(copy(in.getEndCanon()));
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    RotAccelType copy(@Nullable RotAccelType in) {
        if(in != null) {
            RotAccelType out;
            if(in instanceof RotAccelAbsoluteType) {
                out = copy((RotAccelAbsoluteType)in);
            } else if(in instanceof RotAccelRelativeType) {
                out = copy((RotAccelRelativeType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    DisableRobotParameterStatusType copy(@Nullable DisableRobotParameterStatusType in) {
        if(in != null) {
            DisableRobotParameterStatusType out = new DisableRobotParameterStatusType();
            out.setRobotParameterName(in.getRobotParameterName());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    CountSensorStatusType copy(@Nullable CountSensorStatusType in) {
        if(in != null) {
            CountSensorStatusType out = new CountSensorStatusType();
            out.setCountValue(in.getCountValue());
            out.setSensorID(in.getSensorID());
            out.setReadCount(in.getReadCount());
            out.setLastReadTime(in.getLastReadTime());
            out.getSensorParameterSetting().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
            while(iteratorSensorParameterSetting.hasNext()) {
                out.getSensorParameterSetting().add(copy(iteratorSensorParameterSetting.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    OpenToolChangerType copy(@Nullable OpenToolChangerType in) {
        if(in != null) {
            OpenToolChangerType out = new OpenToolChangerType();
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    InitCanonType copy(@Nullable InitCanonType in) {
        if(in != null) {
            InitCanonType out = new InitCanonType();
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    PointType copy(@Nullable PointType in) {
        if(in != null) {
            PointType out = new PointType();
            out.setX(in.getX());
            out.setY(in.getY());
            out.setZ(in.getZ());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    DwellType copy(@Nullable DwellType in) {
        if(in != null) {
            DwellType out = new DwellType();
            out.setDwellTime(in.getDwellTime());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    VectorType copy(@Nullable VectorType in) {
        if(in != null) {
            VectorType out = new VectorType();
            out.setI(in.getI());
            out.setJ(in.getJ());
            out.setK(in.getK());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    JointStatusesType copy(@Nullable JointStatusesType in) {
        if(in != null) {
            JointStatusesType out = new JointStatusesType();
            out.getJointStatus().clear();
            Iterator<crcl.base.JointStatusType> iteratorJointStatus = in.getJointStatus().iterator();
            while(iteratorJointStatus.hasNext()) {
                out.getJointStatus().add(copy(iteratorJointStatus.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    StopMotionType copy(@Nullable StopMotionType in) {
        if(in != null) {
            StopMotionType out = new StopMotionType();
            out.setStopCondition(in.getStopCondition());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    JointLimitType copy(@Nullable JointLimitType in) {
        if(in != null) {
            JointLimitType out = new JointLimitType();
            out.setJointNumber(in.getJointNumber());
            out.setJointMinPosition(in.getJointMinPosition());
            out.setJointMaxPosition(in.getJointMaxPosition());
            out.setJointMaxTorqueOrForce(in.getJointMaxTorqueOrForce());
            out.setJointMaxVelocity(in.getJointMaxVelocity());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetLengthUnitsType copy(@Nullable SetLengthUnitsType in) {
        if(in != null) {
            SetLengthUnitsType out = new SetLengthUnitsType();
            out.setUnitName(in.getUnitName());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    EndCanonType copy(@Nullable EndCanonType in) {
        if(in != null) {
            EndCanonType out = new EndCanonType();
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ThreeFingerGripperStatusType copy(@Nullable ThreeFingerGripperStatusType in) {
        if(in != null) {
            ThreeFingerGripperStatusType out = new ThreeFingerGripperStatusType();
            out.setFinger1Position(in.getFinger1Position());
            out.setFinger2Position(in.getFinger2Position());
            out.setFinger3Position(in.getFinger3Position());
            out.setFinger1Force(in.getFinger1Force());
            out.setFinger2Force(in.getFinger2Force());
            out.setFinger3Force(in.getFinger3Force());
            out.setGripperName(in.getGripperName());
            out.getGripperOption().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorGripperOption = in.getGripperOption().iterator();
            while(iteratorGripperOption.hasNext()) {
                out.getGripperOption().add(copy(iteratorGripperOption.next()));
            }
            out.setHoldingObject(in.isHoldingObject());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SettingsStatusType copy(@Nullable SettingsStatusType in) {
        if(in != null) {
            SettingsStatusType out = new SettingsStatusType();
            out.setAngleUnitName(in.getAngleUnitName());
            out.getEndEffectorParameterSetting().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorEndEffectorParameterSetting = in.getEndEffectorParameterSetting().iterator();
            while(iteratorEndEffectorParameterSetting.hasNext()) {
                out.getEndEffectorParameterSetting().add(copy(iteratorEndEffectorParameterSetting.next()));
            }
            out.setEndEffectorSetting(in.getEndEffectorSetting());
            out.setForceUnitName(in.getForceUnitName());
            out.getJointLimits().clear();
            Iterator<crcl.base.JointLimitType> iteratorJointLimits = in.getJointLimits().iterator();
            while(iteratorJointLimits.hasNext()) {
                out.getJointLimits().add(copy(iteratorJointLimits.next()));
            }
            out.setIntermediatePoseTolerance(copy(in.getIntermediatePoseTolerance()));
            out.setLengthUnitName(in.getLengthUnitName());
            out.setMaxCartesianLimit(copy(in.getMaxCartesianLimit()));
            out.setMinCartesianLimit(copy(in.getMinCartesianLimit()));
            out.setMotionCoordinated(in.isMotionCoordinated());
            out.setPoseTolerance(copy(in.getPoseTolerance()));
            out.getRobotParameterSetting().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorRobotParameterSetting = in.getRobotParameterSetting().iterator();
            while(iteratorRobotParameterSetting.hasNext()) {
                out.getRobotParameterSetting().add(copy(iteratorRobotParameterSetting.next()));
            }
            out.setRotAccelAbsolute(copy(in.getRotAccelAbsolute()));
            out.setRotAccelRelative(copy(in.getRotAccelRelative()));
            out.setRotSpeedAbsolute(copy(in.getRotSpeedAbsolute()));
            out.setRotSpeedRelative(copy(in.getRotSpeedRelative()));
            out.setTorqueUnitName(in.getTorqueUnitName());
            out.setTransAccelAbsolute(copy(in.getTransAccelAbsolute()));
            out.setTransAccelRelative(copy(in.getTransAccelRelative()));
            out.setTransSpeedAbsolute(copy(in.getTransSpeedAbsolute()));
            out.setTransSpeedRelative(copy(in.getTransSpeedRelative()));
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetRotSpeedType copy(@Nullable SetRotSpeedType in) {
        if(in != null) {
            SetRotSpeedType out = new SetRotSpeedType();
            out.setRotSpeed(copy(in.getRotSpeed()));
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    RotSpeedAbsoluteType copy(@Nullable RotSpeedAbsoluteType in) {
        if(in != null) {
            RotSpeedAbsoluteType out = new RotSpeedAbsoluteType();
            out.setSetting(in.getSetting());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetTransAccelType copy(@Nullable SetTransAccelType in) {
        if(in != null) {
            SetTransAccelType out = new SetTransAccelType();
            out.setTransAccel(copy(in.getTransAccel()));
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    CRCLStatusType copy(@Nullable CRCLStatusType in) {
        if(in != null) {
            CRCLStatusType out = new CRCLStatusType();
            out.setCommandStatus(copy(in.getCommandStatus()));
            out.setJointStatuses(copy(in.getJointStatuses()));
            out.setPoseStatus(copy(in.getPoseStatus()));
            out.setGripperStatus(copy(in.getGripperStatus()));
            out.setSettingsStatus(copy(in.getSettingsStatus()));
            out.setSensorStatuses(copy(in.getSensorStatuses()));
            out.setGuardsStatuses(copy(in.getGuardsStatuses()));
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    PoseToleranceType copy(@Nullable PoseToleranceType in) {
        if(in != null) {
            PoseToleranceType out = new PoseToleranceType();
            out.setXPointTolerance(in.getXPointTolerance());
            out.setYPointTolerance(in.getYPointTolerance());
            out.setZPointTolerance(in.getZPointTolerance());
            out.setXAxisTolerance(in.getXAxisTolerance());
            out.setZAxisTolerance(in.getZAxisTolerance());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ParameterSettingType copy(@Nullable ParameterSettingType in) {
        if(in != null) {
            ParameterSettingType out = new ParameterSettingType();
            out.setParameterName(in.getParameterName());
            out.setParameterValue(in.getParameterValue());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetRotAccelType copy(@Nullable SetRotAccelType in) {
        if(in != null) {
            SetRotAccelType out = new SetRotAccelType();
            out.setRotAccel(copy(in.getRotAccel()));
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ActuateJointType copy(@Nullable ActuateJointType in) {
        if(in != null) {
            ActuateJointType out = new ActuateJointType();
            out.setJointNumber(in.getJointNumber());
            out.setJointPosition(in.getJointPosition());
            out.setJointDetails(copy(in.getJointDetails()));
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    TransSpeedAbsoluteType copy(@Nullable TransSpeedAbsoluteType in) {
        if(in != null) {
            TransSpeedAbsoluteType out = new TransSpeedAbsoluteType();
            out.setSetting(in.getSetting());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    OnOffSensorStatusType copy(@Nullable OnOffSensorStatusType in) {
        if(in != null) {
            OnOffSensorStatusType out = new OnOffSensorStatusType();
            out.setOn(in.isOn());
            out.setSensorID(in.getSensorID());
            out.setReadCount(in.getReadCount());
            out.setLastReadTime(in.getLastReadTime());
            out.getSensorParameterSetting().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
            while(iteratorSensorParameterSetting.hasNext()) {
                out.getSensorParameterSetting().add(copy(iteratorSensorParameterSetting.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    DisableGripperType copy(@Nullable DisableGripperType in) {
        if(in != null) {
            DisableGripperType out = new DisableGripperType();
            out.setGripperName(in.getGripperName());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetMotionCoordinationType copy(@Nullable SetMotionCoordinationType in) {
        if(in != null) {
            SetMotionCoordinationType out = new SetMotionCoordinationType();
            out.setCoordinated(in.isCoordinated());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    TwistType copy(@Nullable TwistType in) {
        if(in != null) {
            TwistType out = new TwistType();
            out.setLinearVelocity(copy(in.getLinearVelocity()));
            out.setAngularVelocity(copy(in.getAngularVelocity()));
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    MoveScrewType copy(@Nullable MoveScrewType in) {
        if(in != null) {
            MoveScrewType out = new MoveScrewType();
            out.setStartPosition(copy(in.getStartPosition()));
            out.setAxisPoint(copy(in.getAxisPoint()));
            out.setAxialDistanceFree(in.getAxialDistanceFree());
            out.setAxialDistanceScrew(in.getAxialDistanceScrew());
            out.setTurn(in.getTurn());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetForceUnitsType copy(@Nullable SetForceUnitsType in) {
        if(in != null) {
            SetForceUnitsType out = new SetForceUnitsType();
            out.setUnitName(in.getUnitName());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    JointStatusType copy(@Nullable JointStatusType in) {
        if(in != null) {
            JointStatusType out = new JointStatusType();
            out.setJointNumber(in.getJointNumber());
            out.setJointPosition(in.getJointPosition());
            out.setJointTorqueOrForce(in.getJointTorqueOrForce());
            out.setJointVelocity(in.getJointVelocity());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    TransAccelType copy(@Nullable TransAccelType in) {
        if(in != null) {
            TransAccelType out;
            if(in instanceof TransAccelRelativeType) {
                out = copy((TransAccelRelativeType)in);
            } else if(in instanceof TransAccelAbsoluteType) {
                out = copy((TransAccelAbsoluteType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    TransAccelAbsoluteType copy(@Nullable TransAccelAbsoluteType in) {
        if(in != null) {
            TransAccelAbsoluteType out = new TransAccelAbsoluteType();
            out.setSetting(in.getSetting());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    GetStatusType copy(@Nullable GetStatusType in) {
        if(in != null) {
            GetStatusType out = new GetStatusType();
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    CRCLCommandInstanceType copy(@Nullable CRCLCommandInstanceType in) {
        if(in != null) {
            CRCLCommandInstanceType out = new CRCLCommandInstanceType();
            out.setCRCLCommand(copy(in.getCRCLCommand()));
            out.setProgramFile(in.getProgramFile());
            out.setProgramIndex(in.getProgramIndex());
            out.setProgramLength(in.getProgramLength());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    EnableRobotParameterStatusType copy(@Nullable EnableRobotParameterStatusType in) {
        if(in != null) {
            EnableRobotParameterStatusType out = new EnableRobotParameterStatusType();
            out.setRobotParameterName(in.getRobotParameterName());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetTorqueUnitsType copy(@Nullable SetTorqueUnitsType in) {
        if(in != null) {
            SetTorqueUnitsType out = new SetTorqueUnitsType();
            out.setUnitName(in.getUnitName());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetEndEffectorParametersType copy(@Nullable SetEndEffectorParametersType in) {
        if(in != null) {
            SetEndEffectorParametersType out = new SetEndEffectorParametersType();
            out.getParameterSetting().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorParameterSetting = in.getParameterSetting().iterator();
            while(iteratorParameterSetting.hasNext()) {
                out.getParameterSetting().add(copy(iteratorParameterSetting.next()));
            }
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetTransSpeedType copy(@Nullable SetTransSpeedType in) {
        if(in != null) {
            SetTransSpeedType out = new SetTransSpeedType();
            out.setTransSpeed(copy(in.getTransSpeed()));
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ScalarSensorStatusType copy(@Nullable ScalarSensorStatusType in) {
        if(in != null) {
            ScalarSensorStatusType out = new ScalarSensorStatusType();
            out.setScalarValue(in.getScalarValue());
            out.setSensorID(in.getSensorID());
            out.setReadCount(in.getReadCount());
            out.setLastReadTime(in.getLastReadTime());
            out.getSensorParameterSetting().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
            while(iteratorSensorParameterSetting.hasNext()) {
                out.getSensorParameterSetting().add(copy(iteratorSensorParameterSetting.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    MoveThroughToType copy(@Nullable MoveThroughToType in) {
        if(in != null) {
            MoveThroughToType out = new MoveThroughToType();
            out.setMoveStraight(in.isMoveStraight());
            out.getWaypoint().clear();
            Iterator<crcl.base.PoseType> iteratorWaypoint = in.getWaypoint().iterator();
            while(iteratorWaypoint.hasNext()) {
                out.getWaypoint().add(copy(iteratorWaypoint.next()));
            }
            out.setNumPositions(in.getNumPositions());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ParallelGripperStatusType copy(@Nullable ParallelGripperStatusType in) {
        if(in != null) {
            ParallelGripperStatusType out = new ParallelGripperStatusType();
            out.setSeparation(in.getSeparation());
            out.setGripperName(in.getGripperName());
            out.getGripperOption().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorGripperOption = in.getGripperOption().iterator();
            while(iteratorGripperOption.hasNext()) {
                out.getGripperOption().add(copy(iteratorGripperOption.next()));
            }
            out.setHoldingObject(in.isHoldingObject());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    JointSpeedAccelType copy(@Nullable JointSpeedAccelType in) {
        if(in != null) {
            JointSpeedAccelType out = new JointSpeedAccelType();
            out.setJointSpeed(in.getJointSpeed());
            out.setJointAccel(in.getJointAccel());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }


    public static @Nullable
    PoseStatusType copy(@Nullable PoseStatusType in) {
        if(in != null) {
            PoseStatusType out = new PoseStatusType();
            out.setPose(copy(in.getPose()));
            out.setTwist(copy(in.getTwist()));
            out.setWrench(copy(in.getWrench()));
            out.setConfiguration(in.getConfiguration());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ConfigureJointReportType copy(@Nullable ConfigureJointReportType in) {
        if(in != null) {
            ConfigureJointReportType out = new ConfigureJointReportType();
            out.setJointNumber(in.getJointNumber());
            out.setReportPosition(in.isReportPosition());
            out.setReportTorqueOrForce(in.isReportTorqueOrForce());
            out.setReportVelocity(in.isReportVelocity());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetEndPoseToleranceType copy(@Nullable SetEndPoseToleranceType in) {
        if(in != null) {
            SetEndPoseToleranceType out = new SetEndPoseToleranceType();
            out.setTolerance(copy(in.getTolerance()));
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    RotAccelRelativeType copy(@Nullable RotAccelRelativeType in) {
        if(in != null) {
            RotAccelRelativeType out = new RotAccelRelativeType();
            out.setFraction(in.getFraction());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    DisableSensorType copy(@Nullable DisableSensorType in) {
        if(in != null) {
            DisableSensorType out = new DisableSensorType();
            out.setSensorID(in.getSensorID());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    ConfigureJointReportsType copy(@Nullable ConfigureJointReportsType in) {
        if(in != null) {
            ConfigureJointReportsType out = new ConfigureJointReportsType();
            out.setResetAll(in.isResetAll());
            out.getConfigureJointReport().clear();
            Iterator<crcl.base.ConfigureJointReportType> iteratorConfigureJointReport = in.getConfigureJointReport().iterator();
            while(iteratorConfigureJointReport.hasNext()) {
                out.getConfigureJointReport().add(copy(iteratorConfigureJointReport.next()));
            }
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    CommandStatusType copy(@Nullable CommandStatusType in) {
        if(in != null) {
            CommandStatusType out = new CommandStatusType();
            out.setCommandID(in.getCommandID());
            out.setProgramFile(in.getProgramFile());
            out.setProgramIndex(in.getProgramIndex());
            out.setProgramLength(in.getProgramLength());
            out.setStatusID(in.getStatusID());
            out.setCommandState(in.getCommandState());
            out.setStateDescription(in.getStateDescription());
            out.setOverridePercent(in.getOverridePercent());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    MessageType copy(@Nullable MessageType in) {
        if(in != null) {
            MessageType out = new MessageType();
            out.setMessage(in.getMessage());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    TransAccelRelativeType copy(@Nullable TransAccelRelativeType in) {
        if(in != null) {
            TransAccelRelativeType out = new TransAccelRelativeType();
            out.setFraction(in.getFraction());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    SetEndEffectorType copy(@Nullable SetEndEffectorType in) {
        if(in != null) {
            SetEndEffectorType out = new SetEndEffectorType();
            out.setSetting(in.getSetting());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    RotAccelAbsoluteType copy(@Nullable RotAccelAbsoluteType in) {
        if(in != null) {
            RotAccelAbsoluteType out = new RotAccelAbsoluteType();
            out.setSetting(in.getSetting());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    GuardType copy(@Nullable GuardType in) {
        if(in != null) {
            GuardType out = new GuardType();
            out.setSensorID(in.getSensorID());
            out.setSubField(in.getSubField());
            out.setLimitType(in.getLimitType());
            out.setLimitValue(in.getLimitValue());
            out.setRecheckTimeMicroSeconds(in.getRecheckTimeMicroSeconds());
            out.setCheckCount(in.getCheckCount());
            out.setLastCheckTime(in.getLastCheckTime());
            out.setLastCheckValue(in.getLastCheckValue());
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    MoveToType copy(@Nullable MoveToType in) {
        if(in != null) {
            MoveToType out = new MoveToType();
            out.setEndPosition(copy(in.getEndPosition()));
            out.setMoveStraight(in.isMoveStraight());
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    EnableGripperType copy(@Nullable EnableGripperType in) {
        if(in != null) {
            EnableGripperType out = new EnableGripperType();
            out.setGripperName(in.getGripperName());
            out.getGripperOption().clear();
            Iterator<crcl.base.ParameterSettingType> iteratorGripperOption = in.getGripperOption().iterator();
            while(iteratorGripperOption.hasNext()) {
                out.getGripperOption().add(copy(iteratorGripperOption.next()));
            }
            out.setCommandID(in.getCommandID());
            out.getGuard().clear();
            Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
            while(iteratorGuard.hasNext()) {
                out.getGuard().add(copy(iteratorGuard.next()));
            }
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }

    public static @Nullable
    WrenchType copy(@Nullable WrenchType in) {
        if(in != null) {
            WrenchType out = new WrenchType();
            out.setForce(copy(in.getForce()));
            out.setMoment(copy(in.getMoment()));
            out.setName(in.getName());
            return out;
        } else {
            return null;
        }
    }
}
// end CRCLCopier

