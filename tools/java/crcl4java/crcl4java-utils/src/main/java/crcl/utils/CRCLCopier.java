package crcl.utils;

import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.AngleUnitEnumType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CloseToolChangerType;
import crcl.base.CommandStateEnumType;
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
import crcl.base.ForceUnitEnumType;
import crcl.base.GetStatusType;
import crcl.base.GripperStatusType;
import crcl.base.GuardLimitEnumType;
import crcl.base.GuardType;
import crcl.base.GuardsStatusesType;
import crcl.base.InitCanonType;
import crcl.base.JointDetailsType;
import crcl.base.JointForceTorqueType;
import crcl.base.JointLimitType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.LengthUnitEnumType;
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
import crcl.base.StopConditionEnumType;
import crcl.base.StopMotionType;
import crcl.base.ThreeFingerGripperStatusType;
import crcl.base.TorqueUnitEnumType;
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
import crcl.utils.CRCLCommandWrapper;
import crcl.utils.CRCLCommandWrapperConsumer;
import java.util.AbstractCollection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;

public class CRCLCopier {
    // i = 0,clzzi=class crcl.base.DataThingType

    public static @Nullable
    DataThingType copy(@Nullable DataThingType in) {
        if(in != null) {
            DataThingType out;
            if(in instanceof CRCLCommandWrapper) {
                out = copy((CRCLCommandWrapper)in);
            } else if(in instanceof StopMotionType) {
                out = copy((StopMotionType)in);
            } else if(in instanceof PoseAndSetType) {
                out = copy((PoseAndSetType)in);
            } else if(in instanceof SetEndPoseToleranceType) {
                out = copy((SetEndPoseToleranceType)in);
            } else if(in instanceof ConfigureJointReportType) {
                out = copy((ConfigureJointReportType)in);
            } else if(in instanceof GetStatusType) {
                out = copy((GetStatusType)in);
            } else if(in instanceof TransAccelAbsoluteType) {
                out = copy((TransAccelAbsoluteType)in);
            } else if(in instanceof SetEndEffectorType) {
                out = copy((SetEndEffectorType)in);
            } else if(in instanceof TransAccelRelativeType) {
                out = copy((TransAccelRelativeType)in);
            } else if(in instanceof ActuateJointType) {
                out = copy((ActuateJointType)in);
            } else if(in instanceof MoveThroughToType) {
                out = copy((MoveThroughToType)in);
            } else if(in instanceof SetEndEffectorParametersType) {
                out = copy((SetEndEffectorParametersType)in);
            } else if(in instanceof SetTorqueUnitsType) {
                out = copy((SetTorqueUnitsType)in);
            } else if(in instanceof VacuumGripperStatusType) {
                out = copy((VacuumGripperStatusType)in);
            } else if(in instanceof ConfigureStatusReportType) {
                out = copy((ConfigureStatusReportType)in);
            } else if(in instanceof SensorStatusesType) {
                out = copy((SensorStatusesType)in);
            } else if(in instanceof SetAngleUnitsType) {
                out = copy((SetAngleUnitsType)in);
            } else if(in instanceof SetRotAccelType) {
                out = copy((SetRotAccelType)in);
            } else if(in instanceof CommandStatusType) {
                out = copy((CommandStatusType)in);
            } else if(in instanceof ScalarSensorStatusType) {
                out = copy((ScalarSensorStatusType)in);
            } else if(in instanceof SetTransSpeedType) {
                out = copy((SetTransSpeedType)in);
            } else if(in instanceof DisableRobotParameterStatusType) {
                out = copy((DisableRobotParameterStatusType)in);
            } else if(in instanceof GuardType) {
                out = copy((GuardType)in);
            } else if(in instanceof RotAccelAbsoluteType) {
                out = copy((RotAccelAbsoluteType)in);
            } else if(in instanceof TransSpeedAbsoluteType) {
                out = copy((TransSpeedAbsoluteType)in);
            } else if(in instanceof SetForceUnitsType) {
                out = copy((SetForceUnitsType)in);
            } else if(in instanceof MoveScrewType) {
                out = copy((MoveScrewType)in);
            } else if(in instanceof CountSensorStatusType) {
                out = copy((CountSensorStatusType)in);
            } else if(in instanceof WrenchType) {
                out = copy((WrenchType)in);
            } else if(in instanceof EnableGripperType) {
                out = copy((EnableGripperType)in);
            } else if(in instanceof MoveToType) {
                out = copy((MoveToType)in);
            } else if(in instanceof DisableGripperType) {
                out = copy((DisableGripperType)in);
            } else if(in instanceof SetRotSpeedType) {
                out = copy((SetRotSpeedType)in);
            } else if(in instanceof ActuateJointsType) {
                out = copy((ActuateJointsType)in);
            } else if(in instanceof ThreeFingerGripperStatusType) {
                out = copy((ThreeFingerGripperStatusType)in);
            } else if(in instanceof EndCanonType) {
                out = copy((EndCanonType)in);
            } else if(in instanceof CloseToolChangerType) {
                out = copy((CloseToolChangerType)in);
            } else if(in instanceof PoseToleranceType) {
                out = copy((PoseToleranceType)in);
            } else if(in instanceof CRCLStatusType) {
                out = copy((CRCLStatusType)in);
            } else if(in instanceof SetTransAccelType) {
                out = copy((SetTransAccelType)in);
            } else if(in instanceof TwistType) {
                out = copy((TwistType)in);
            } else if(in instanceof DisableSensorType) {
                out = copy((DisableSensorType)in);
            } else if(in instanceof RotAccelRelativeType) {
                out = copy((RotAccelRelativeType)in);
            } else if(in instanceof DwellType) {
                out = copy((DwellType)in);
            } else if(in instanceof PointType) {
                out = copy((PointType)in);
            } else if(in instanceof SettingsStatusType) {
                out = copy((SettingsStatusType)in);
            } else if(in instanceof JointForceTorqueType) {
                out = copy((JointForceTorqueType)in);
            } else if(in instanceof JointSpeedAccelType) {
                out = copy((JointSpeedAccelType)in);
            } else if(in instanceof ParallelGripperStatusType) {
                out = copy((ParallelGripperStatusType)in);
            } else if(in instanceof JointLimitType) {
                out = copy((JointLimitType)in);
            } else if(in instanceof SetRobotParametersType) {
                out = copy((SetRobotParametersType)in);
            } else if(in instanceof JointStatusesType) {
                out = copy((JointStatusesType)in);
            } else if(in instanceof VectorType) {
                out = copy((VectorType)in);
            } else if(in instanceof EnableRobotParameterStatusType) {
                out = copy((EnableRobotParameterStatusType)in);
            } else if(in instanceof CRCLCommandInstanceType) {
                out = copy((CRCLCommandInstanceType)in);
            } else if(in instanceof PoseStatusType) {
                out = copy((PoseStatusType)in);
            } else if(in instanceof JointStatusType) {
                out = copy((JointStatusType)in);
            } else if(in instanceof MessageType) {
                out = copy((MessageType)in);
            } else if(in instanceof OpenToolChangerType) {
                out = copy((OpenToolChangerType)in);
            } else if(in instanceof RotSpeedAbsoluteType) {
                out = copy((RotSpeedAbsoluteType)in);
            } else if(in instanceof OnOffSensorStatusType) {
                out = copy((OnOffSensorStatusType)in);
            } else if(in instanceof SetIntermediatePoseToleranceType) {
                out = copy((SetIntermediatePoseToleranceType)in);
            } else if(in instanceof TransSpeedRelativeType) {
                out = copy((TransSpeedRelativeType)in);
            } else if(in instanceof PoseType) {
                out = copy((PoseType)in);
            } else if(in instanceof ParameterSettingType) {
                out = copy((ParameterSettingType)in);
            } else if(in instanceof GuardsStatusesType) {
                out = copy((GuardsStatusesType)in);
            } else if(in instanceof RunProgramType) {
                out = copy((RunProgramType)in);
            } else if(in instanceof ConfigureJointReportsType) {
                out = copy((ConfigureJointReportsType)in);
            } else if(in instanceof SetMotionCoordinationType) {
                out = copy((SetMotionCoordinationType)in);
            } else if(in instanceof CRCLProgramType) {
                out = copy((CRCLProgramType)in);
            } else if(in instanceof InitCanonType) {
                out = copy((InitCanonType)in);
            } else if(in instanceof RotSpeedRelativeType) {
                out = copy((RotSpeedRelativeType)in);
            } else if(in instanceof SetLengthUnitsType) {
                out = copy((SetLengthUnitsType)in);
            } else if(in instanceof ForceTorqueSensorStatusType) {
                out = copy((ForceTorqueSensorStatusType)in);
            } else if(in instanceof SensorStatusType) {
                out = copy((SensorStatusType)in);
            } else if(in instanceof EnableSensorType) {
                out = copy((EnableSensorType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    // i = 1,clzzi=class crcl.base.CRCLCommandType

    public static @Nullable
    CRCLCommandType copy(@Nullable CRCLCommandType in) {
        if(in != null) {
            CRCLCommandType out;
            if(in instanceof CRCLCommandWrapper) {
                out = copy((CRCLCommandWrapper)in);
            } else if(in instanceof StopMotionType) {
                out = copy((StopMotionType)in);
            } else if(in instanceof SetEndPoseToleranceType) {
                out = copy((SetEndPoseToleranceType)in);
            } else if(in instanceof GetStatusType) {
                out = copy((GetStatusType)in);
            } else if(in instanceof SetEndEffectorType) {
                out = copy((SetEndEffectorType)in);
            } else if(in instanceof MoveThroughToType) {
                out = copy((MoveThroughToType)in);
            } else if(in instanceof SetEndEffectorParametersType) {
                out = copy((SetEndEffectorParametersType)in);
            } else if(in instanceof SetTorqueUnitsType) {
                out = copy((SetTorqueUnitsType)in);
            } else if(in instanceof ConfigureStatusReportType) {
                out = copy((ConfigureStatusReportType)in);
            } else if(in instanceof SetAngleUnitsType) {
                out = copy((SetAngleUnitsType)in);
            } else if(in instanceof SetRotAccelType) {
                out = copy((SetRotAccelType)in);
            } else if(in instanceof SetTransSpeedType) {
                out = copy((SetTransSpeedType)in);
            } else if(in instanceof DisableRobotParameterStatusType) {
                out = copy((DisableRobotParameterStatusType)in);
            } else if(in instanceof SetForceUnitsType) {
                out = copy((SetForceUnitsType)in);
            } else if(in instanceof MoveScrewType) {
                out = copy((MoveScrewType)in);
            } else if(in instanceof EnableGripperType) {
                out = copy((EnableGripperType)in);
            } else if(in instanceof MoveToType) {
                out = copy((MoveToType)in);
            } else if(in instanceof DisableGripperType) {
                out = copy((DisableGripperType)in);
            } else if(in instanceof SetRotSpeedType) {
                out = copy((SetRotSpeedType)in);
            } else if(in instanceof ActuateJointsType) {
                out = copy((ActuateJointsType)in);
            } else if(in instanceof EndCanonType) {
                out = copy((EndCanonType)in);
            } else if(in instanceof CloseToolChangerType) {
                out = copy((CloseToolChangerType)in);
            } else if(in instanceof SetTransAccelType) {
                out = copy((SetTransAccelType)in);
            } else if(in instanceof DisableSensorType) {
                out = copy((DisableSensorType)in);
            } else if(in instanceof DwellType) {
                out = copy((DwellType)in);
            } else if(in instanceof SetRobotParametersType) {
                out = copy((SetRobotParametersType)in);
            } else if(in instanceof EnableRobotParameterStatusType) {
                out = copy((EnableRobotParameterStatusType)in);
            } else if(in instanceof MessageType) {
                out = copy((MessageType)in);
            } else if(in instanceof OpenToolChangerType) {
                out = copy((OpenToolChangerType)in);
            } else if(in instanceof SetIntermediatePoseToleranceType) {
                out = copy((SetIntermediatePoseToleranceType)in);
            } else if(in instanceof RunProgramType) {
                out = copy((RunProgramType)in);
            } else if(in instanceof ConfigureJointReportsType) {
                out = copy((ConfigureJointReportsType)in);
            } else if(in instanceof SetMotionCoordinationType) {
                out = copy((SetMotionCoordinationType)in);
            } else if(in instanceof InitCanonType) {
                out = copy((InitCanonType)in);
            } else if(in instanceof SetLengthUnitsType) {
                out = copy((SetLengthUnitsType)in);
            } else if(in instanceof EnableSensorType) {
                out = copy((EnableSensorType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    // i = 2,clzzi=class crcl.base.MiddleCommandType

    public static @Nullable
    MiddleCommandType copy(@Nullable MiddleCommandType in) {
        if(in != null) {
            MiddleCommandType out;
            if(in instanceof CRCLCommandWrapper) {
                out = copy((CRCLCommandWrapper)in);
            } else if(in instanceof StopMotionType) {
                out = copy((StopMotionType)in);
            } else if(in instanceof SetEndPoseToleranceType) {
                out = copy((SetEndPoseToleranceType)in);
            } else if(in instanceof GetStatusType) {
                out = copy((GetStatusType)in);
            } else if(in instanceof SetEndEffectorType) {
                out = copy((SetEndEffectorType)in);
            } else if(in instanceof MoveThroughToType) {
                out = copy((MoveThroughToType)in);
            } else if(in instanceof SetEndEffectorParametersType) {
                out = copy((SetEndEffectorParametersType)in);
            } else if(in instanceof SetTorqueUnitsType) {
                out = copy((SetTorqueUnitsType)in);
            } else if(in instanceof ConfigureStatusReportType) {
                out = copy((ConfigureStatusReportType)in);
            } else if(in instanceof SetAngleUnitsType) {
                out = copy((SetAngleUnitsType)in);
            } else if(in instanceof SetRotAccelType) {
                out = copy((SetRotAccelType)in);
            } else if(in instanceof SetTransSpeedType) {
                out = copy((SetTransSpeedType)in);
            } else if(in instanceof DisableRobotParameterStatusType) {
                out = copy((DisableRobotParameterStatusType)in);
            } else if(in instanceof SetForceUnitsType) {
                out = copy((SetForceUnitsType)in);
            } else if(in instanceof MoveScrewType) {
                out = copy((MoveScrewType)in);
            } else if(in instanceof EnableGripperType) {
                out = copy((EnableGripperType)in);
            } else if(in instanceof MoveToType) {
                out = copy((MoveToType)in);
            } else if(in instanceof DisableGripperType) {
                out = copy((DisableGripperType)in);
            } else if(in instanceof SetRotSpeedType) {
                out = copy((SetRotSpeedType)in);
            } else if(in instanceof ActuateJointsType) {
                out = copy((ActuateJointsType)in);
            } else if(in instanceof CloseToolChangerType) {
                out = copy((CloseToolChangerType)in);
            } else if(in instanceof SetTransAccelType) {
                out = copy((SetTransAccelType)in);
            } else if(in instanceof DisableSensorType) {
                out = copy((DisableSensorType)in);
            } else if(in instanceof DwellType) {
                out = copy((DwellType)in);
            } else if(in instanceof SetRobotParametersType) {
                out = copy((SetRobotParametersType)in);
            } else if(in instanceof EnableRobotParameterStatusType) {
                out = copy((EnableRobotParameterStatusType)in);
            } else if(in instanceof MessageType) {
                out = copy((MessageType)in);
            } else if(in instanceof OpenToolChangerType) {
                out = copy((OpenToolChangerType)in);
            } else if(in instanceof SetIntermediatePoseToleranceType) {
                out = copy((SetIntermediatePoseToleranceType)in);
            } else if(in instanceof RunProgramType) {
                out = copy((RunProgramType)in);
            } else if(in instanceof ConfigureJointReportsType) {
                out = copy((ConfigureJointReportsType)in);
            } else if(in instanceof SetMotionCoordinationType) {
                out = copy((SetMotionCoordinationType)in);
            } else if(in instanceof SetLengthUnitsType) {
                out = copy((SetLengthUnitsType)in);
            } else if(in instanceof EnableSensorType) {
                out = copy((EnableSensorType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    // i = 3,clzzi=class crcl.base.EnableSensorType

    public static @Nullable
    EnableSensorType copy(@Nullable EnableSensorType in) {
        if(in != null) {
            EnableSensorType out = new EnableSensorType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    EnableSensorType copyTo(EnableSensorType in,EnableSensorType out) {
        assert(in.getClass()==EnableSensorType.class): "in.getClass()!=EnableSensorType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==EnableSensorType.class): "out.getClass()!=EnableSensorType.class: out.getClass()="+out.getClass();
        out.setSensorID(in.getSensorID());
        java.util.List<crcl.base.ParameterSettingType> outSensorOption = out.getSensorOption();
        assert(outSensorOption !=null):"outSensorOption==null";
        outSensorOption.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorSensorOption = in.getSensorOption().iterator();
        while(iteratorSensorOption.hasNext()) {
            outSensorOption.add(copy(iteratorSensorOption.next()));
        }
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 4,clzzi=class crcl.base.SensorStatusType

    public static @Nullable
    SensorStatusType copy(@Nullable SensorStatusType in) {
        if(in != null) {
            SensorStatusType out;
            if(in instanceof ScalarSensorStatusType) {
                out = copy((ScalarSensorStatusType)in);
            } else if(in instanceof CountSensorStatusType) {
                out = copy((CountSensorStatusType)in);
            } else if(in instanceof OnOffSensorStatusType) {
                out = copy((OnOffSensorStatusType)in);
            } else if(in instanceof ForceTorqueSensorStatusType) {
                out = copy((ForceTorqueSensorStatusType)in);
            } else if(in instanceof SensorStatusType) {
                out = new SensorStatusType();
                copyTo(in,out);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SensorStatusType copyTo(SensorStatusType in,SensorStatusType out) {
        assert(in.getClass()==SensorStatusType.class): "in.getClass()!=SensorStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SensorStatusType.class): "out.getClass()!=SensorStatusType.class: out.getClass()="+out.getClass();
        out.setSensorID(in.getSensorID());
        out.setReadCount(in.getReadCount());
        out.setLastReadTime(in.getLastReadTime());
        java.util.List<crcl.base.ParameterSettingType> outSensorParameterSetting = out.getSensorParameterSetting();
        assert(outSensorParameterSetting !=null):"outSensorParameterSetting==null";
        outSensorParameterSetting.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
        while(iteratorSensorParameterSetting.hasNext()) {
            outSensorParameterSetting.add(copy(iteratorSensorParameterSetting.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 5,clzzi=class crcl.base.ForceTorqueSensorStatusType

    public static @Nullable
    ForceTorqueSensorStatusType copy(@Nullable ForceTorqueSensorStatusType in) {
        if(in != null) {
            ForceTorqueSensorStatusType out = new ForceTorqueSensorStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ForceTorqueSensorStatusType copyTo(ForceTorqueSensorStatusType in,ForceTorqueSensorStatusType out) {
        assert(in.getClass()==ForceTorqueSensorStatusType.class): "in.getClass()!=ForceTorqueSensorStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ForceTorqueSensorStatusType.class): "out.getClass()!=ForceTorqueSensorStatusType.class: out.getClass()="+out.getClass();
        out.setFx(in.getFx());
        out.setFy(in.getFy());
        out.setFz(in.getFz());
        out.setTx(in.getTx());
        out.setTy(in.getTy());
        out.setTz(in.getTz());
        out.setSensorID(in.getSensorID());
        out.setReadCount(in.getReadCount());
        out.setLastReadTime(in.getLastReadTime());
        java.util.List<crcl.base.ParameterSettingType> outSensorParameterSetting = out.getSensorParameterSetting();
        assert(outSensorParameterSetting !=null):"outSensorParameterSetting==null";
        outSensorParameterSetting.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
        while(iteratorSensorParameterSetting.hasNext()) {
            outSensorParameterSetting.add(copy(iteratorSensorParameterSetting.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 6,clzzi=class crcl.base.SetLengthUnitsType

    public static @Nullable
    SetLengthUnitsType copy(@Nullable SetLengthUnitsType in) {
        if(in != null) {
            SetLengthUnitsType out = new SetLengthUnitsType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetLengthUnitsType copyTo(SetLengthUnitsType in,SetLengthUnitsType out) {
        assert(in.getClass()==SetLengthUnitsType.class): "in.getClass()!=SetLengthUnitsType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetLengthUnitsType.class): "out.getClass()!=SetLengthUnitsType.class: out.getClass()="+out.getClass();
        out.setUnitName(in.getUnitName());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 7,clzzi=class crcl.base.RotSpeedType

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
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    // i = 8,clzzi=class crcl.base.RotSpeedRelativeType

    public static @Nullable
    RotSpeedRelativeType copy(@Nullable RotSpeedRelativeType in) {
        if(in != null) {
            RotSpeedRelativeType out = new RotSpeedRelativeType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    RotSpeedRelativeType copyTo(RotSpeedRelativeType in,RotSpeedRelativeType out) {
        assert(in.getClass()==RotSpeedRelativeType.class): "in.getClass()!=RotSpeedRelativeType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==RotSpeedRelativeType.class): "out.getClass()!=RotSpeedRelativeType.class: out.getClass()="+out.getClass();
        out.setFraction(in.getFraction());
        out.setName(in.getName());
        return out;
    }

    // i = 9,clzzi=class crcl.base.InitCanonType

    public static @Nullable
    InitCanonType copy(@Nullable InitCanonType in) {
        if(in != null) {
            InitCanonType out = new InitCanonType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    InitCanonType copyTo(InitCanonType in,InitCanonType out) {
        assert(in.getClass()==InitCanonType.class): "in.getClass()!=InitCanonType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==InitCanonType.class): "out.getClass()!=InitCanonType.class: out.getClass()="+out.getClass();
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 10,clzzi=class java.lang.Enum
    // i = 11,clzzi=class crcl.base.AngleUnitEnumType
    // i = 12,clzzi=class crcl.base.CRCLProgramType

    public static @Nullable
    CRCLProgramType copy(@Nullable CRCLProgramType in) {
        if(in != null) {
            CRCLProgramType out = new CRCLProgramType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    CRCLProgramType copyTo(CRCLProgramType in,CRCLProgramType out) {
        assert(in.getClass()==CRCLProgramType.class): "in.getClass()!=CRCLProgramType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==CRCLProgramType.class): "out.getClass()!=CRCLProgramType.class: out.getClass()="+out.getClass();
        out.setInitCanon(copy(in.getInitCanon()));
        java.util.List<crcl.base.MiddleCommandType> outMiddleCommand = out.getMiddleCommand();
        assert(outMiddleCommand !=null):"outMiddleCommand==null";
        outMiddleCommand.clear();
        Iterator<crcl.base.MiddleCommandType> iteratorMiddleCommand = in.getMiddleCommand().iterator();
        while(iteratorMiddleCommand.hasNext()) {
            final MiddleCommandType middleCommandCopy = copy(iteratorMiddleCommand.next());
            if(middleCommandCopy instanceof CRCLCommandWrapper) {
               CRCLCommandWrapper wrapper = (CRCLCommandWrapper) middleCommandCopy;
               wrapper.setCurProgram(out);
            }
            outMiddleCommand.add(middleCommandCopy);
        }
        out.setEndCanon(copy(in.getEndCanon()));
        out.setName(in.getName());
        return out;
    }

    // i = 13,clzzi=class crcl.base.SetMotionCoordinationType

    public static @Nullable
    SetMotionCoordinationType copy(@Nullable SetMotionCoordinationType in) {
        if(in != null) {
            SetMotionCoordinationType out = new SetMotionCoordinationType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetMotionCoordinationType copyTo(SetMotionCoordinationType in,SetMotionCoordinationType out) {
        assert(in.getClass()==SetMotionCoordinationType.class): "in.getClass()!=SetMotionCoordinationType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetMotionCoordinationType.class): "out.getClass()!=SetMotionCoordinationType.class: out.getClass()="+out.getClass();
        out.setCoordinated(in.isCoordinated());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 14,clzzi=class crcl.base.ConfigureJointReportsType

    public static @Nullable
    ConfigureJointReportsType copy(@Nullable ConfigureJointReportsType in) {
        if(in != null) {
            ConfigureJointReportsType out = new ConfigureJointReportsType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ConfigureJointReportsType copyTo(ConfigureJointReportsType in,ConfigureJointReportsType out) {
        assert(in.getClass()==ConfigureJointReportsType.class): "in.getClass()!=ConfigureJointReportsType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ConfigureJointReportsType.class): "out.getClass()!=ConfigureJointReportsType.class: out.getClass()="+out.getClass();
        out.setResetAll(in.isResetAll());
        java.util.List<crcl.base.ConfigureJointReportType> outConfigureJointReport = out.getConfigureJointReport();
        assert(outConfigureJointReport !=null):"outConfigureJointReport==null";
        outConfigureJointReport.clear();
        Iterator<crcl.base.ConfigureJointReportType> iteratorConfigureJointReport = in.getConfigureJointReport().iterator();
        while(iteratorConfigureJointReport.hasNext()) {
            outConfigureJointReport.add(copy(iteratorConfigureJointReport.next()));
        }
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 15,clzzi=class crcl.base.RunProgramType

    public static @Nullable
    RunProgramType copy(@Nullable RunProgramType in) {
        if(in != null) {
            RunProgramType out = new RunProgramType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    RunProgramType copyTo(RunProgramType in,RunProgramType out) {
        assert(in.getClass()==RunProgramType.class): "in.getClass()!=RunProgramType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==RunProgramType.class): "out.getClass()!=RunProgramType.class: out.getClass()="+out.getClass();
        out.setProgramText(in.getProgramText());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 16,clzzi=class crcl.base.GuardsStatusesType

    public static @Nullable
    GuardsStatusesType copy(@Nullable GuardsStatusesType in) {
        if(in != null) {
            GuardsStatusesType out = new GuardsStatusesType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    GuardsStatusesType copyTo(GuardsStatusesType in,GuardsStatusesType out) {
        assert(in.getClass()==GuardsStatusesType.class): "in.getClass()!=GuardsStatusesType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==GuardsStatusesType.class): "out.getClass()!=GuardsStatusesType.class: out.getClass()="+out.getClass();
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setTriggerCount(in.getTriggerCount());
        out.setTriggerStopTimeMicros(in.getTriggerStopTimeMicros());
        out.setTriggerValue(in.getTriggerValue());
        out.setTriggerPose(copy(in.getTriggerPose()));
        out.setName(in.getName());
        return out;
    }

    // i = 17,clzzi=class crcl.base.ParameterSettingType

    public static @Nullable
    ParameterSettingType copy(@Nullable ParameterSettingType in) {
        if(in != null) {
            ParameterSettingType out = new ParameterSettingType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ParameterSettingType copyTo(ParameterSettingType in,ParameterSettingType out) {
        assert(in.getClass()==ParameterSettingType.class): "in.getClass()!=ParameterSettingType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ParameterSettingType.class): "out.getClass()!=ParameterSettingType.class: out.getClass()="+out.getClass();
        out.setParameterName(in.getParameterName());
        out.setParameterValue(in.getParameterValue());
        out.setName(in.getName());
        return out;
    }

    // i = 18,clzzi=class crcl.base.PoseType

    public static @Nullable
    PoseType copy(@Nullable PoseType in) {
        if(in != null) {
            PoseType out;
            if(in instanceof PoseAndSetType) {
                out = copy((PoseAndSetType)in);
            } else if(in instanceof PoseType) {
                out = new PoseType();
                copyTo(in,out);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    PoseType copyTo(PoseType in,PoseType out) {
        assert(in.getClass()==PoseType.class): "in.getClass()!=PoseType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==PoseType.class): "out.getClass()!=PoseType.class: out.getClass()="+out.getClass();
        out.setPoint(copy(in.getPoint()));
        out.setXAxis(copy(in.getXAxis()));
        out.setZAxis(copy(in.getZAxis()));
        out.setName(in.getName());
        return out;
    }

    // i = 19,clzzi=class crcl.base.TransSpeedType

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
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    // i = 20,clzzi=class crcl.base.TransSpeedRelativeType

    public static @Nullable
    TransSpeedRelativeType copy(@Nullable TransSpeedRelativeType in) {
        if(in != null) {
            TransSpeedRelativeType out = new TransSpeedRelativeType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    TransSpeedRelativeType copyTo(TransSpeedRelativeType in,TransSpeedRelativeType out) {
        assert(in.getClass()==TransSpeedRelativeType.class): "in.getClass()!=TransSpeedRelativeType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==TransSpeedRelativeType.class): "out.getClass()!=TransSpeedRelativeType.class: out.getClass()="+out.getClass();
        out.setFraction(in.getFraction());
        out.setName(in.getName());
        return out;
    }

    // i = 21,clzzi=class crcl.base.SetIntermediatePoseToleranceType

    public static @Nullable
    SetIntermediatePoseToleranceType copy(@Nullable SetIntermediatePoseToleranceType in) {
        if(in != null) {
            SetIntermediatePoseToleranceType out = new SetIntermediatePoseToleranceType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetIntermediatePoseToleranceType copyTo(SetIntermediatePoseToleranceType in,SetIntermediatePoseToleranceType out) {
        assert(in.getClass()==SetIntermediatePoseToleranceType.class): "in.getClass()!=SetIntermediatePoseToleranceType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetIntermediatePoseToleranceType.class): "out.getClass()!=SetIntermediatePoseToleranceType.class: out.getClass()="+out.getClass();
        out.setTolerance(copy(in.getTolerance()));
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 22,clzzi=class crcl.base.OnOffSensorStatusType

    public static @Nullable
    OnOffSensorStatusType copy(@Nullable OnOffSensorStatusType in) {
        if(in != null) {
            OnOffSensorStatusType out = new OnOffSensorStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    OnOffSensorStatusType copyTo(OnOffSensorStatusType in,OnOffSensorStatusType out) {
        assert(in.getClass()==OnOffSensorStatusType.class): "in.getClass()!=OnOffSensorStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==OnOffSensorStatusType.class): "out.getClass()!=OnOffSensorStatusType.class: out.getClass()="+out.getClass();
        out.setOn(in.isOn());
        out.setSensorID(in.getSensorID());
        out.setReadCount(in.getReadCount());
        out.setLastReadTime(in.getLastReadTime());
        java.util.List<crcl.base.ParameterSettingType> outSensorParameterSetting = out.getSensorParameterSetting();
        assert(outSensorParameterSetting !=null):"outSensorParameterSetting==null";
        outSensorParameterSetting.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
        while(iteratorSensorParameterSetting.hasNext()) {
            outSensorParameterSetting.add(copy(iteratorSensorParameterSetting.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 23,clzzi=class crcl.base.StopConditionEnumType
    // i = 24,clzzi=class crcl.base.RotSpeedAbsoluteType

    public static @Nullable
    RotSpeedAbsoluteType copy(@Nullable RotSpeedAbsoluteType in) {
        if(in != null) {
            RotSpeedAbsoluteType out = new RotSpeedAbsoluteType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    RotSpeedAbsoluteType copyTo(RotSpeedAbsoluteType in,RotSpeedAbsoluteType out) {
        assert(in.getClass()==RotSpeedAbsoluteType.class): "in.getClass()!=RotSpeedAbsoluteType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==RotSpeedAbsoluteType.class): "out.getClass()!=RotSpeedAbsoluteType.class: out.getClass()="+out.getClass();
        out.setSetting(in.getSetting());
        out.setName(in.getName());
        return out;
    }

    // i = 25,clzzi=class crcl.base.GuardLimitEnumType
    // i = 26,clzzi=class crcl.base.OpenToolChangerType

    public static @Nullable
    OpenToolChangerType copy(@Nullable OpenToolChangerType in) {
        if(in != null) {
            OpenToolChangerType out = new OpenToolChangerType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    OpenToolChangerType copyTo(OpenToolChangerType in,OpenToolChangerType out) {
        assert(in.getClass()==OpenToolChangerType.class): "in.getClass()!=OpenToolChangerType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==OpenToolChangerType.class): "out.getClass()!=OpenToolChangerType.class: out.getClass()="+out.getClass();
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 27,clzzi=class crcl.base.TorqueUnitEnumType
    // i = 28,clzzi=class crcl.base.MessageType

    public static @Nullable
    MessageType copy(@Nullable MessageType in) {
        if(in != null) {
            MessageType out;
            if(in instanceof CRCLCommandWrapper) {
                out = copy((CRCLCommandWrapper)in);
            } else if(in instanceof MessageType) {
                out = new MessageType();
                copyTo(in,out);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    MessageType copyTo(MessageType in,MessageType out) {
        assert(in.getClass()==MessageType.class): "in.getClass()!=MessageType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==MessageType.class): "out.getClass()!=MessageType.class: out.getClass()="+out.getClass();
        out.setMessage(in.getMessage());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 29,clzzi=class crcl.base.JointDetailsType

    public static @Nullable
    JointDetailsType copy(@Nullable JointDetailsType in) {
        if(in != null) {
            JointDetailsType out;
            if(in instanceof JointForceTorqueType) {
                out = copy((JointForceTorqueType)in);
            } else if(in instanceof JointSpeedAccelType) {
                out = copy((JointSpeedAccelType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    // i = 30,clzzi=class crcl.base.JointStatusType

    public static @Nullable
    JointStatusType copy(@Nullable JointStatusType in) {
        if(in != null) {
            JointStatusType out = new JointStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    JointStatusType copyTo(JointStatusType in,JointStatusType out) {
        assert(in.getClass()==JointStatusType.class): "in.getClass()!=JointStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==JointStatusType.class): "out.getClass()!=JointStatusType.class: out.getClass()="+out.getClass();
        out.setJointNumber(in.getJointNumber());
        out.setJointPosition(in.getJointPosition());
        out.setJointTorqueOrForce(in.getJointTorqueOrForce());
        out.setJointVelocity(in.getJointVelocity());
        out.setName(in.getName());
        return out;
    }

    // i = 31,clzzi=class crcl.base.PoseStatusType

    public static @Nullable
    PoseStatusType copy(@Nullable PoseStatusType in) {
        if(in != null) {
            PoseStatusType out = new PoseStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    PoseStatusType copyTo(PoseStatusType in,PoseStatusType out) {
        assert(in.getClass()==PoseStatusType.class): "in.getClass()!=PoseStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==PoseStatusType.class): "out.getClass()!=PoseStatusType.class: out.getClass()="+out.getClass();
        out.setPose(copy(in.getPose()));
        out.setTwist(copy(in.getTwist()));
        out.setWrench(copy(in.getWrench()));
        out.setConfiguration(in.getConfiguration());
        out.setName(in.getName());
        return out;
    }

    // i = 32,clzzi=class crcl.base.CRCLCommandInstanceType

    public static @Nullable
    CRCLCommandInstanceType copy(@Nullable CRCLCommandInstanceType in) {
        if(in != null) {
            CRCLCommandInstanceType out = new CRCLCommandInstanceType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    CRCLCommandInstanceType copyTo(CRCLCommandInstanceType in,CRCLCommandInstanceType out) {
        assert(in.getClass()==CRCLCommandInstanceType.class): "in.getClass()!=CRCLCommandInstanceType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==CRCLCommandInstanceType.class): "out.getClass()!=CRCLCommandInstanceType.class: out.getClass()="+out.getClass();
        out.setCRCLCommand(copy(in.getCRCLCommand()));
        out.setProgramFile(in.getProgramFile());
        out.setProgramIndex(in.getProgramIndex());
        out.setProgramLength(in.getProgramLength());
        out.setName(in.getName());
        return out;
    }

    // i = 33,clzzi=class crcl.base.EnableRobotParameterStatusType

    public static @Nullable
    EnableRobotParameterStatusType copy(@Nullable EnableRobotParameterStatusType in) {
        if(in != null) {
            EnableRobotParameterStatusType out = new EnableRobotParameterStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    EnableRobotParameterStatusType copyTo(EnableRobotParameterStatusType in,EnableRobotParameterStatusType out) {
        assert(in.getClass()==EnableRobotParameterStatusType.class): "in.getClass()!=EnableRobotParameterStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==EnableRobotParameterStatusType.class): "out.getClass()!=EnableRobotParameterStatusType.class: out.getClass()="+out.getClass();
        out.setRobotParameterName(in.getRobotParameterName());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 34,clzzi=class crcl.base.VectorType

    public static @Nullable
    VectorType copy(@Nullable VectorType in) {
        if(in != null) {
            VectorType out = new VectorType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    VectorType copyTo(VectorType in,VectorType out) {
        assert(in.getClass()==VectorType.class): "in.getClass()!=VectorType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==VectorType.class): "out.getClass()!=VectorType.class: out.getClass()="+out.getClass();
        out.setI(in.getI());
        out.setJ(in.getJ());
        out.setK(in.getK());
        out.setName(in.getName());
        return out;
    }

    // i = 35,clzzi=class crcl.base.JointStatusesType

    public static @Nullable
    JointStatusesType copy(@Nullable JointStatusesType in) {
        if(in != null) {
            JointStatusesType out = new JointStatusesType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    JointStatusesType copyTo(JointStatusesType in,JointStatusesType out) {
        assert(in.getClass()==JointStatusesType.class): "in.getClass()!=JointStatusesType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==JointStatusesType.class): "out.getClass()!=JointStatusesType.class: out.getClass()="+out.getClass();
        java.util.List<crcl.base.JointStatusType> outJointStatus = out.getJointStatus();
        assert(outJointStatus !=null):"outJointStatus==null";
        outJointStatus.clear();
        Iterator<crcl.base.JointStatusType> iteratorJointStatus = in.getJointStatus().iterator();
        while(iteratorJointStatus.hasNext()) {
            outJointStatus.add(copy(iteratorJointStatus.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 36,clzzi=class crcl.base.CommandStateEnumType
    // i = 37,clzzi=class crcl.base.SetRobotParametersType

    public static @Nullable
    SetRobotParametersType copy(@Nullable SetRobotParametersType in) {
        if(in != null) {
            SetRobotParametersType out = new SetRobotParametersType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetRobotParametersType copyTo(SetRobotParametersType in,SetRobotParametersType out) {
        assert(in.getClass()==SetRobotParametersType.class): "in.getClass()!=SetRobotParametersType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetRobotParametersType.class): "out.getClass()!=SetRobotParametersType.class: out.getClass()="+out.getClass();
        java.util.List<crcl.base.ParameterSettingType> outParameterSetting = out.getParameterSetting();
        assert(outParameterSetting !=null):"outParameterSetting==null";
        outParameterSetting.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorParameterSetting = in.getParameterSetting().iterator();
        while(iteratorParameterSetting.hasNext()) {
            outParameterSetting.add(copy(iteratorParameterSetting.next()));
        }
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 38,clzzi=class crcl.base.JointLimitType

    public static @Nullable
    JointLimitType copy(@Nullable JointLimitType in) {
        if(in != null) {
            JointLimitType out = new JointLimitType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    JointLimitType copyTo(JointLimitType in,JointLimitType out) {
        assert(in.getClass()==JointLimitType.class): "in.getClass()!=JointLimitType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==JointLimitType.class): "out.getClass()!=JointLimitType.class: out.getClass()="+out.getClass();
        out.setJointNumber(in.getJointNumber());
        out.setJointMinPosition(in.getJointMinPosition());
        out.setJointMaxPosition(in.getJointMaxPosition());
        out.setJointMaxTorqueOrForce(in.getJointMaxTorqueOrForce());
        out.setJointMaxVelocity(in.getJointMaxVelocity());
        out.setName(in.getName());
        return out;
    }

    // i = 39,clzzi=class crcl.base.GripperStatusType

    public static @Nullable
    GripperStatusType copy(@Nullable GripperStatusType in) {
        if(in != null) {
            GripperStatusType out;
            if(in instanceof VacuumGripperStatusType) {
                out = copy((VacuumGripperStatusType)in);
            } else if(in instanceof ThreeFingerGripperStatusType) {
                out = copy((ThreeFingerGripperStatusType)in);
            } else if(in instanceof ParallelGripperStatusType) {
                out = copy((ParallelGripperStatusType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    // i = 40,clzzi=class crcl.base.ParallelGripperStatusType

    public static @Nullable
    ParallelGripperStatusType copy(@Nullable ParallelGripperStatusType in) {
        if(in != null) {
            ParallelGripperStatusType out = new ParallelGripperStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ParallelGripperStatusType copyTo(ParallelGripperStatusType in,ParallelGripperStatusType out) {
        assert(in.getClass()==ParallelGripperStatusType.class): "in.getClass()!=ParallelGripperStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ParallelGripperStatusType.class): "out.getClass()!=ParallelGripperStatusType.class: out.getClass()="+out.getClass();
        out.setSeparation(in.getSeparation());
        out.setGripperName(in.getGripperName());
        java.util.List<crcl.base.ParameterSettingType> outGripperOption = out.getGripperOption();
        assert(outGripperOption !=null):"outGripperOption==null";
        outGripperOption.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorGripperOption = in.getGripperOption().iterator();
        while(iteratorGripperOption.hasNext()) {
            outGripperOption.add(copy(iteratorGripperOption.next()));
        }
        out.setHoldingObject(in.isHoldingObject());
        out.setName(in.getName());
        return out;
    }

    // i = 41,clzzi=class crcl.base.JointSpeedAccelType

    public static @Nullable
    JointSpeedAccelType copy(@Nullable JointSpeedAccelType in) {
        if(in != null) {
            JointSpeedAccelType out = new JointSpeedAccelType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    JointSpeedAccelType copyTo(JointSpeedAccelType in,JointSpeedAccelType out) {
        assert(in.getClass()==JointSpeedAccelType.class): "in.getClass()!=JointSpeedAccelType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==JointSpeedAccelType.class): "out.getClass()!=JointSpeedAccelType.class: out.getClass()="+out.getClass();
        out.setJointSpeed(in.getJointSpeed());
        out.setJointAccel(in.getJointAccel());
        out.setName(in.getName());
        return out;
    }

    // i = 42,clzzi=class crcl.base.JointForceTorqueType

    public static @Nullable
    JointForceTorqueType copy(@Nullable JointForceTorqueType in) {
        if(in != null) {
            JointForceTorqueType out = new JointForceTorqueType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    JointForceTorqueType copyTo(JointForceTorqueType in,JointForceTorqueType out) {
        assert(in.getClass()==JointForceTorqueType.class): "in.getClass()!=JointForceTorqueType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==JointForceTorqueType.class): "out.getClass()!=JointForceTorqueType.class: out.getClass()="+out.getClass();
        out.setSetting(in.getSetting());
        out.setChangeRate(in.getChangeRate());
        out.setName(in.getName());
        return out;
    }

    // i = 43,clzzi=class crcl.base.SettingsStatusType

    public static @Nullable
    SettingsStatusType copy(@Nullable SettingsStatusType in) {
        if(in != null) {
            SettingsStatusType out = new SettingsStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SettingsStatusType copyTo(SettingsStatusType in,SettingsStatusType out) {
        assert(in.getClass()==SettingsStatusType.class): "in.getClass()!=SettingsStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SettingsStatusType.class): "out.getClass()!=SettingsStatusType.class: out.getClass()="+out.getClass();
        out.setAngleUnitName(in.getAngleUnitName());
        java.util.List<crcl.base.ParameterSettingType> outEndEffectorParameterSetting = out.getEndEffectorParameterSetting();
        assert(outEndEffectorParameterSetting !=null):"outEndEffectorParameterSetting==null";
        outEndEffectorParameterSetting.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorEndEffectorParameterSetting = in.getEndEffectorParameterSetting().iterator();
        while(iteratorEndEffectorParameterSetting.hasNext()) {
            outEndEffectorParameterSetting.add(copy(iteratorEndEffectorParameterSetting.next()));
        }
        out.setEndEffectorSetting(in.getEndEffectorSetting());
        out.setForceUnitName(in.getForceUnitName());
        java.util.List<crcl.base.JointLimitType> outJointLimits = out.getJointLimits();
        assert(outJointLimits !=null):"outJointLimits==null";
        outJointLimits.clear();
        Iterator<crcl.base.JointLimitType> iteratorJointLimits = in.getJointLimits().iterator();
        while(iteratorJointLimits.hasNext()) {
            outJointLimits.add(copy(iteratorJointLimits.next()));
        }
        out.setIntermediatePoseTolerance(copy(in.getIntermediatePoseTolerance()));
        out.setLengthUnitName(in.getLengthUnitName());
        out.setMaxCartesianLimit(copy(in.getMaxCartesianLimit()));
        out.setMinCartesianLimit(copy(in.getMinCartesianLimit()));
        out.setMotionCoordinated(in.isMotionCoordinated());
        out.setPoseTolerance(copy(in.getPoseTolerance()));
        java.util.List<crcl.base.ParameterSettingType> outRobotParameterSetting = out.getRobotParameterSetting();
        assert(outRobotParameterSetting !=null):"outRobotParameterSetting==null";
        outRobotParameterSetting.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorRobotParameterSetting = in.getRobotParameterSetting().iterator();
        while(iteratorRobotParameterSetting.hasNext()) {
            outRobotParameterSetting.add(copy(iteratorRobotParameterSetting.next()));
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
    }

    // i = 44,clzzi=class crcl.base.PointType

    public static @Nullable
    PointType copy(@Nullable PointType in) {
        if(in != null) {
            PointType out = new PointType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    PointType copyTo(PointType in,PointType out) {
        assert(in.getClass()==PointType.class): "in.getClass()!=PointType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==PointType.class): "out.getClass()!=PointType.class: out.getClass()="+out.getClass();
        out.setX(in.getX());
        out.setY(in.getY());
        out.setZ(in.getZ());
        out.setName(in.getName());
        return out;
    }

    // i = 45,clzzi=class crcl.base.DwellType

    public static @Nullable
    DwellType copy(@Nullable DwellType in) {
        if(in != null) {
            DwellType out = new DwellType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    DwellType copyTo(DwellType in,DwellType out) {
        assert(in.getClass()==DwellType.class): "in.getClass()!=DwellType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==DwellType.class): "out.getClass()!=DwellType.class: out.getClass()="+out.getClass();
        out.setDwellTime(in.getDwellTime());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 46,clzzi=class crcl.base.RotAccelType

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
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    // i = 47,clzzi=class crcl.base.RotAccelRelativeType

    public static @Nullable
    RotAccelRelativeType copy(@Nullable RotAccelRelativeType in) {
        if(in != null) {
            RotAccelRelativeType out = new RotAccelRelativeType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    RotAccelRelativeType copyTo(RotAccelRelativeType in,RotAccelRelativeType out) {
        assert(in.getClass()==RotAccelRelativeType.class): "in.getClass()!=RotAccelRelativeType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==RotAccelRelativeType.class): "out.getClass()!=RotAccelRelativeType.class: out.getClass()="+out.getClass();
        out.setFraction(in.getFraction());
        out.setName(in.getName());
        return out;
    }

    // i = 48,clzzi=class crcl.base.DisableSensorType

    public static @Nullable
    DisableSensorType copy(@Nullable DisableSensorType in) {
        if(in != null) {
            DisableSensorType out = new DisableSensorType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    DisableSensorType copyTo(DisableSensorType in,DisableSensorType out) {
        assert(in.getClass()==DisableSensorType.class): "in.getClass()!=DisableSensorType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==DisableSensorType.class): "out.getClass()!=DisableSensorType.class: out.getClass()="+out.getClass();
        out.setSensorID(in.getSensorID());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 49,clzzi=class crcl.base.TwistType

    public static @Nullable
    TwistType copy(@Nullable TwistType in) {
        if(in != null) {
            TwistType out = new TwistType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    TwistType copyTo(TwistType in,TwistType out) {
        assert(in.getClass()==TwistType.class): "in.getClass()!=TwistType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==TwistType.class): "out.getClass()!=TwistType.class: out.getClass()="+out.getClass();
        out.setLinearVelocity(copy(in.getLinearVelocity()));
        out.setAngularVelocity(copy(in.getAngularVelocity()));
        out.setName(in.getName());
        return out;
    }

    // i = 50,clzzi=class crcl.base.SetTransAccelType

    public static @Nullable
    SetTransAccelType copy(@Nullable SetTransAccelType in) {
        if(in != null) {
            SetTransAccelType out = new SetTransAccelType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetTransAccelType copyTo(SetTransAccelType in,SetTransAccelType out) {
        assert(in.getClass()==SetTransAccelType.class): "in.getClass()!=SetTransAccelType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetTransAccelType.class): "out.getClass()!=SetTransAccelType.class: out.getClass()="+out.getClass();
        out.setTransAccel(copy(in.getTransAccel()));
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 51,clzzi=class crcl.base.CRCLStatusType

    public static @Nullable
    CRCLStatusType copy(@Nullable CRCLStatusType in) {
        if(in != null) {
            CRCLStatusType out = new CRCLStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    CRCLStatusType copyTo(CRCLStatusType in,CRCLStatusType out) {
        assert(in.getClass()==CRCLStatusType.class): "in.getClass()!=CRCLStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==CRCLStatusType.class): "out.getClass()!=CRCLStatusType.class: out.getClass()="+out.getClass();
        out.setCommandStatus(copy(in.getCommandStatus()));
        out.setJointStatuses(copy(in.getJointStatuses()));
        out.setPoseStatus(copy(in.getPoseStatus()));
        out.setGripperStatus(copy(in.getGripperStatus()));
        out.setSettingsStatus(copy(in.getSettingsStatus()));
        out.setSensorStatuses(copy(in.getSensorStatuses()));
        out.setGuardsStatuses(copy(in.getGuardsStatuses()));
        out.setName(in.getName());
        return out;
    }

    // i = 52,clzzi=class crcl.base.PoseToleranceType

    public static @Nullable
    PoseToleranceType copy(@Nullable PoseToleranceType in) {
        if(in != null) {
            PoseToleranceType out = new PoseToleranceType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    PoseToleranceType copyTo(PoseToleranceType in,PoseToleranceType out) {
        assert(in.getClass()==PoseToleranceType.class): "in.getClass()!=PoseToleranceType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==PoseToleranceType.class): "out.getClass()!=PoseToleranceType.class: out.getClass()="+out.getClass();
        out.setXPointTolerance(in.getXPointTolerance());
        out.setYPointTolerance(in.getYPointTolerance());
        out.setZPointTolerance(in.getZPointTolerance());
        out.setXAxisTolerance(in.getXAxisTolerance());
        out.setZAxisTolerance(in.getZAxisTolerance());
        out.setName(in.getName());
        return out;
    }

    // i = 53,clzzi=class crcl.base.CloseToolChangerType

    public static @Nullable
    CloseToolChangerType copy(@Nullable CloseToolChangerType in) {
        if(in != null) {
            CloseToolChangerType out = new CloseToolChangerType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    CloseToolChangerType copyTo(CloseToolChangerType in,CloseToolChangerType out) {
        assert(in.getClass()==CloseToolChangerType.class): "in.getClass()!=CloseToolChangerType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==CloseToolChangerType.class): "out.getClass()!=CloseToolChangerType.class: out.getClass()="+out.getClass();
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 54,clzzi=class crcl.base.EndCanonType

    public static @Nullable
    EndCanonType copy(@Nullable EndCanonType in) {
        if(in != null) {
            EndCanonType out = new EndCanonType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    EndCanonType copyTo(EndCanonType in,EndCanonType out) {
        assert(in.getClass()==EndCanonType.class): "in.getClass()!=EndCanonType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==EndCanonType.class): "out.getClass()!=EndCanonType.class: out.getClass()="+out.getClass();
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 55,clzzi=class crcl.base.ThreeFingerGripperStatusType

    public static @Nullable
    ThreeFingerGripperStatusType copy(@Nullable ThreeFingerGripperStatusType in) {
        if(in != null) {
            ThreeFingerGripperStatusType out = new ThreeFingerGripperStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ThreeFingerGripperStatusType copyTo(ThreeFingerGripperStatusType in,ThreeFingerGripperStatusType out) {
        assert(in.getClass()==ThreeFingerGripperStatusType.class): "in.getClass()!=ThreeFingerGripperStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ThreeFingerGripperStatusType.class): "out.getClass()!=ThreeFingerGripperStatusType.class: out.getClass()="+out.getClass();
        out.setFinger1Position(in.getFinger1Position());
        out.setFinger2Position(in.getFinger2Position());
        out.setFinger3Position(in.getFinger3Position());
        out.setFinger1Force(in.getFinger1Force());
        out.setFinger2Force(in.getFinger2Force());
        out.setFinger3Force(in.getFinger3Force());
        out.setGripperName(in.getGripperName());
        java.util.List<crcl.base.ParameterSettingType> outGripperOption = out.getGripperOption();
        assert(outGripperOption !=null):"outGripperOption==null";
        outGripperOption.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorGripperOption = in.getGripperOption().iterator();
        while(iteratorGripperOption.hasNext()) {
            outGripperOption.add(copy(iteratorGripperOption.next()));
        }
        out.setHoldingObject(in.isHoldingObject());
        out.setName(in.getName());
        return out;
    }

    // i = 56,clzzi=class crcl.base.ActuateJointsType

    public static @Nullable
    ActuateJointsType copy(@Nullable ActuateJointsType in) {
        if(in != null) {
            ActuateJointsType out = new ActuateJointsType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ActuateJointsType copyTo(ActuateJointsType in,ActuateJointsType out) {
        assert(in.getClass()==ActuateJointsType.class): "in.getClass()!=ActuateJointsType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ActuateJointsType.class): "out.getClass()!=ActuateJointsType.class: out.getClass()="+out.getClass();
        java.util.List<crcl.base.ActuateJointType> outActuateJoint = out.getActuateJoint();
        assert(outActuateJoint !=null):"outActuateJoint==null";
        outActuateJoint.clear();
        Iterator<crcl.base.ActuateJointType> iteratorActuateJoint = in.getActuateJoint().iterator();
        while(iteratorActuateJoint.hasNext()) {
            outActuateJoint.add(copy(iteratorActuateJoint.next()));
        }
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 57,clzzi=class crcl.base.SetRotSpeedType

    public static @Nullable
    SetRotSpeedType copy(@Nullable SetRotSpeedType in) {
        if(in != null) {
            SetRotSpeedType out = new SetRotSpeedType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetRotSpeedType copyTo(SetRotSpeedType in,SetRotSpeedType out) {
        assert(in.getClass()==SetRotSpeedType.class): "in.getClass()!=SetRotSpeedType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetRotSpeedType.class): "out.getClass()!=SetRotSpeedType.class: out.getClass()="+out.getClass();
        out.setRotSpeed(copy(in.getRotSpeed()));
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 58,clzzi=class crcl.base.DisableGripperType

    public static @Nullable
    DisableGripperType copy(@Nullable DisableGripperType in) {
        if(in != null) {
            DisableGripperType out = new DisableGripperType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    DisableGripperType copyTo(DisableGripperType in,DisableGripperType out) {
        assert(in.getClass()==DisableGripperType.class): "in.getClass()!=DisableGripperType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==DisableGripperType.class): "out.getClass()!=DisableGripperType.class: out.getClass()="+out.getClass();
        out.setGripperName(in.getGripperName());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 59,clzzi=class crcl.base.MoveToType

    public static @Nullable
    MoveToType copy(@Nullable MoveToType in) {
        if(in != null) {
            MoveToType out = new MoveToType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    MoveToType copyTo(MoveToType in,MoveToType out) {
        assert(in.getClass()==MoveToType.class): "in.getClass()!=MoveToType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==MoveToType.class): "out.getClass()!=MoveToType.class: out.getClass()="+out.getClass();
        out.setEndPosition(copy(in.getEndPosition()));
        out.setMoveStraight(in.isMoveStraight());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 60,clzzi=class crcl.base.EnableGripperType

    public static @Nullable
    EnableGripperType copy(@Nullable EnableGripperType in) {
        if(in != null) {
            EnableGripperType out = new EnableGripperType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    EnableGripperType copyTo(EnableGripperType in,EnableGripperType out) {
        assert(in.getClass()==EnableGripperType.class): "in.getClass()!=EnableGripperType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==EnableGripperType.class): "out.getClass()!=EnableGripperType.class: out.getClass()="+out.getClass();
        out.setGripperName(in.getGripperName());
        java.util.List<crcl.base.ParameterSettingType> outGripperOption = out.getGripperOption();
        assert(outGripperOption !=null):"outGripperOption==null";
        outGripperOption.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorGripperOption = in.getGripperOption().iterator();
        while(iteratorGripperOption.hasNext()) {
            outGripperOption.add(copy(iteratorGripperOption.next()));
        }
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 61,clzzi=class crcl.base.WrenchType

    public static @Nullable
    WrenchType copy(@Nullable WrenchType in) {
        if(in != null) {
            WrenchType out = new WrenchType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    WrenchType copyTo(WrenchType in,WrenchType out) {
        assert(in.getClass()==WrenchType.class): "in.getClass()!=WrenchType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==WrenchType.class): "out.getClass()!=WrenchType.class: out.getClass()="+out.getClass();
        out.setForce(copy(in.getForce()));
        out.setMoment(copy(in.getMoment()));
        out.setName(in.getName());
        return out;
    }

    // i = 62,clzzi=class crcl.base.CountSensorStatusType

    public static @Nullable
    CountSensorStatusType copy(@Nullable CountSensorStatusType in) {
        if(in != null) {
            CountSensorStatusType out = new CountSensorStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    CountSensorStatusType copyTo(CountSensorStatusType in,CountSensorStatusType out) {
        assert(in.getClass()==CountSensorStatusType.class): "in.getClass()!=CountSensorStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==CountSensorStatusType.class): "out.getClass()!=CountSensorStatusType.class: out.getClass()="+out.getClass();
        out.setCountValue(in.getCountValue());
        out.setSensorID(in.getSensorID());
        out.setReadCount(in.getReadCount());
        out.setLastReadTime(in.getLastReadTime());
        java.util.List<crcl.base.ParameterSettingType> outSensorParameterSetting = out.getSensorParameterSetting();
        assert(outSensorParameterSetting !=null):"outSensorParameterSetting==null";
        outSensorParameterSetting.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
        while(iteratorSensorParameterSetting.hasNext()) {
            outSensorParameterSetting.add(copy(iteratorSensorParameterSetting.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 63,clzzi=class crcl.base.ForceUnitEnumType
    // i = 64,clzzi=class crcl.base.MoveScrewType

    public static @Nullable
    MoveScrewType copy(@Nullable MoveScrewType in) {
        if(in != null) {
            MoveScrewType out = new MoveScrewType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    MoveScrewType copyTo(MoveScrewType in,MoveScrewType out) {
        assert(in.getClass()==MoveScrewType.class): "in.getClass()!=MoveScrewType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==MoveScrewType.class): "out.getClass()!=MoveScrewType.class: out.getClass()="+out.getClass();
        out.setStartPosition(copy(in.getStartPosition()));
        out.setAxisPoint(copy(in.getAxisPoint()));
        out.setAxialDistanceFree(in.getAxialDistanceFree());
        out.setAxialDistanceScrew(in.getAxialDistanceScrew());
        out.setTurn(in.getTurn());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 65,clzzi=class crcl.base.SetForceUnitsType

    public static @Nullable
    SetForceUnitsType copy(@Nullable SetForceUnitsType in) {
        if(in != null) {
            SetForceUnitsType out = new SetForceUnitsType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetForceUnitsType copyTo(SetForceUnitsType in,SetForceUnitsType out) {
        assert(in.getClass()==SetForceUnitsType.class): "in.getClass()!=SetForceUnitsType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetForceUnitsType.class): "out.getClass()!=SetForceUnitsType.class: out.getClass()="+out.getClass();
        out.setUnitName(in.getUnitName());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 66,clzzi=class crcl.base.TransSpeedAbsoluteType

    public static @Nullable
    TransSpeedAbsoluteType copy(@Nullable TransSpeedAbsoluteType in) {
        if(in != null) {
            TransSpeedAbsoluteType out = new TransSpeedAbsoluteType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    TransSpeedAbsoluteType copyTo(TransSpeedAbsoluteType in,TransSpeedAbsoluteType out) {
        assert(in.getClass()==TransSpeedAbsoluteType.class): "in.getClass()!=TransSpeedAbsoluteType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==TransSpeedAbsoluteType.class): "out.getClass()!=TransSpeedAbsoluteType.class: out.getClass()="+out.getClass();
        out.setSetting(in.getSetting());
        out.setName(in.getName());
        return out;
    }

    // i = 67,clzzi=class crcl.base.RotAccelAbsoluteType

    public static @Nullable
    RotAccelAbsoluteType copy(@Nullable RotAccelAbsoluteType in) {
        if(in != null) {
            RotAccelAbsoluteType out = new RotAccelAbsoluteType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    RotAccelAbsoluteType copyTo(RotAccelAbsoluteType in,RotAccelAbsoluteType out) {
        assert(in.getClass()==RotAccelAbsoluteType.class): "in.getClass()!=RotAccelAbsoluteType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==RotAccelAbsoluteType.class): "out.getClass()!=RotAccelAbsoluteType.class: out.getClass()="+out.getClass();
        out.setSetting(in.getSetting());
        out.setName(in.getName());
        return out;
    }

    // i = 68,clzzi=class crcl.base.GuardType

    public static @Nullable
    GuardType copy(@Nullable GuardType in) {
        if(in != null) {
            GuardType out = new GuardType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    GuardType copyTo(GuardType in,GuardType out) {
        assert(in.getClass()==GuardType.class): "in.getClass()!=GuardType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==GuardType.class): "out.getClass()!=GuardType.class: out.getClass()="+out.getClass();
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
    }

    // i = 69,clzzi=class crcl.base.DisableRobotParameterStatusType

    public static @Nullable
    DisableRobotParameterStatusType copy(@Nullable DisableRobotParameterStatusType in) {
        if(in != null) {
            DisableRobotParameterStatusType out = new DisableRobotParameterStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    DisableRobotParameterStatusType copyTo(DisableRobotParameterStatusType in,DisableRobotParameterStatusType out) {
        assert(in.getClass()==DisableRobotParameterStatusType.class): "in.getClass()!=DisableRobotParameterStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==DisableRobotParameterStatusType.class): "out.getClass()!=DisableRobotParameterStatusType.class: out.getClass()="+out.getClass();
        out.setRobotParameterName(in.getRobotParameterName());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 70,clzzi=class crcl.base.SetTransSpeedType

    public static @Nullable
    SetTransSpeedType copy(@Nullable SetTransSpeedType in) {
        if(in != null) {
            SetTransSpeedType out = new SetTransSpeedType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetTransSpeedType copyTo(SetTransSpeedType in,SetTransSpeedType out) {
        assert(in.getClass()==SetTransSpeedType.class): "in.getClass()!=SetTransSpeedType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetTransSpeedType.class): "out.getClass()!=SetTransSpeedType.class: out.getClass()="+out.getClass();
        out.setTransSpeed(copy(in.getTransSpeed()));
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 71,clzzi=class crcl.base.ScalarSensorStatusType

    public static @Nullable
    ScalarSensorStatusType copy(@Nullable ScalarSensorStatusType in) {
        if(in != null) {
            ScalarSensorStatusType out = new ScalarSensorStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ScalarSensorStatusType copyTo(ScalarSensorStatusType in,ScalarSensorStatusType out) {
        assert(in.getClass()==ScalarSensorStatusType.class): "in.getClass()!=ScalarSensorStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ScalarSensorStatusType.class): "out.getClass()!=ScalarSensorStatusType.class: out.getClass()="+out.getClass();
        out.setScalarValue(in.getScalarValue());
        out.setSensorID(in.getSensorID());
        out.setReadCount(in.getReadCount());
        out.setLastReadTime(in.getLastReadTime());
        java.util.List<crcl.base.ParameterSettingType> outSensorParameterSetting = out.getSensorParameterSetting();
        assert(outSensorParameterSetting !=null):"outSensorParameterSetting==null";
        outSensorParameterSetting.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorSensorParameterSetting = in.getSensorParameterSetting().iterator();
        while(iteratorSensorParameterSetting.hasNext()) {
            outSensorParameterSetting.add(copy(iteratorSensorParameterSetting.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 72,clzzi=class crcl.base.CommandStatusType

    public static @Nullable
    CommandStatusType copy(@Nullable CommandStatusType in) {
        if(in != null) {
            CommandStatusType out = new CommandStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    CommandStatusType copyTo(CommandStatusType in,CommandStatusType out) {
        assert(in.getClass()==CommandStatusType.class): "in.getClass()!=CommandStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==CommandStatusType.class): "out.getClass()!=CommandStatusType.class: out.getClass()="+out.getClass();
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
    }

    // i = 73,clzzi=class crcl.base.SetRotAccelType

    public static @Nullable
    SetRotAccelType copy(@Nullable SetRotAccelType in) {
        if(in != null) {
            SetRotAccelType out = new SetRotAccelType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetRotAccelType copyTo(SetRotAccelType in,SetRotAccelType out) {
        assert(in.getClass()==SetRotAccelType.class): "in.getClass()!=SetRotAccelType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetRotAccelType.class): "out.getClass()!=SetRotAccelType.class: out.getClass()="+out.getClass();
        out.setRotAccel(copy(in.getRotAccel()));
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 74,clzzi=class crcl.base.SetAngleUnitsType

    public static @Nullable
    SetAngleUnitsType copy(@Nullable SetAngleUnitsType in) {
        if(in != null) {
            SetAngleUnitsType out = new SetAngleUnitsType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetAngleUnitsType copyTo(SetAngleUnitsType in,SetAngleUnitsType out) {
        assert(in.getClass()==SetAngleUnitsType.class): "in.getClass()!=SetAngleUnitsType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetAngleUnitsType.class): "out.getClass()!=SetAngleUnitsType.class: out.getClass()="+out.getClass();
        out.setUnitName(in.getUnitName());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 75,clzzi=class crcl.base.SensorStatusesType

    public static @Nullable
    SensorStatusesType copy(@Nullable SensorStatusesType in) {
        if(in != null) {
            SensorStatusesType out = new SensorStatusesType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SensorStatusesType copyTo(SensorStatusesType in,SensorStatusesType out) {
        assert(in.getClass()==SensorStatusesType.class): "in.getClass()!=SensorStatusesType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SensorStatusesType.class): "out.getClass()!=SensorStatusesType.class: out.getClass()="+out.getClass();
        java.util.List<crcl.base.OnOffSensorStatusType> outOnOffSensorStatus = out.getOnOffSensorStatus();
        assert(outOnOffSensorStatus !=null):"outOnOffSensorStatus==null";
        outOnOffSensorStatus.clear();
        Iterator<crcl.base.OnOffSensorStatusType> iteratorOnOffSensorStatus = in.getOnOffSensorStatus().iterator();
        while(iteratorOnOffSensorStatus.hasNext()) {
            outOnOffSensorStatus.add(copy(iteratorOnOffSensorStatus.next()));
        }
        java.util.List<crcl.base.ScalarSensorStatusType> outScalarSensorStatus = out.getScalarSensorStatus();
        assert(outScalarSensorStatus !=null):"outScalarSensorStatus==null";
        outScalarSensorStatus.clear();
        Iterator<crcl.base.ScalarSensorStatusType> iteratorScalarSensorStatus = in.getScalarSensorStatus().iterator();
        while(iteratorScalarSensorStatus.hasNext()) {
            outScalarSensorStatus.add(copy(iteratorScalarSensorStatus.next()));
        }
        java.util.List<crcl.base.CountSensorStatusType> outCountSensorStatus = out.getCountSensorStatus();
        assert(outCountSensorStatus !=null):"outCountSensorStatus==null";
        outCountSensorStatus.clear();
        Iterator<crcl.base.CountSensorStatusType> iteratorCountSensorStatus = in.getCountSensorStatus().iterator();
        while(iteratorCountSensorStatus.hasNext()) {
            outCountSensorStatus.add(copy(iteratorCountSensorStatus.next()));
        }
        java.util.List<crcl.base.ForceTorqueSensorStatusType> outForceTorqueSensorStatus = out.getForceTorqueSensorStatus();
        assert(outForceTorqueSensorStatus !=null):"outForceTorqueSensorStatus==null";
        outForceTorqueSensorStatus.clear();
        Iterator<crcl.base.ForceTorqueSensorStatusType> iteratorForceTorqueSensorStatus = in.getForceTorqueSensorStatus().iterator();
        while(iteratorForceTorqueSensorStatus.hasNext()) {
            outForceTorqueSensorStatus.add(copy(iteratorForceTorqueSensorStatus.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 76,clzzi=class crcl.base.LengthUnitEnumType
    // i = 77,clzzi=class crcl.base.ConfigureStatusReportType

    public static @Nullable
    ConfigureStatusReportType copy(@Nullable ConfigureStatusReportType in) {
        if(in != null) {
            ConfigureStatusReportType out = new ConfigureStatusReportType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ConfigureStatusReportType copyTo(ConfigureStatusReportType in,ConfigureStatusReportType out) {
        assert(in.getClass()==ConfigureStatusReportType.class): "in.getClass()!=ConfigureStatusReportType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ConfigureStatusReportType.class): "out.getClass()!=ConfigureStatusReportType.class: out.getClass()="+out.getClass();
        out.setReportJointStatuses(in.isReportJointStatuses());
        out.setReportPoseStatus(in.isReportPoseStatus());
        out.setReportGripperStatus(in.isReportGripperStatus());
        out.setReportSettingsStatus(in.isReportSettingsStatus());
        out.setReportSensorsStatus(in.isReportSensorsStatus());
        out.setReportGuardsStatus(in.isReportGuardsStatus());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 78,clzzi=class crcl.base.VacuumGripperStatusType

    public static @Nullable
    VacuumGripperStatusType copy(@Nullable VacuumGripperStatusType in) {
        if(in != null) {
            VacuumGripperStatusType out = new VacuumGripperStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    VacuumGripperStatusType copyTo(VacuumGripperStatusType in,VacuumGripperStatusType out) {
        assert(in.getClass()==VacuumGripperStatusType.class): "in.getClass()!=VacuumGripperStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==VacuumGripperStatusType.class): "out.getClass()!=VacuumGripperStatusType.class: out.getClass()="+out.getClass();
        out.setIsPowered(in.isIsPowered());
        out.setGripperName(in.getGripperName());
        java.util.List<crcl.base.ParameterSettingType> outGripperOption = out.getGripperOption();
        assert(outGripperOption !=null):"outGripperOption==null";
        outGripperOption.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorGripperOption = in.getGripperOption().iterator();
        while(iteratorGripperOption.hasNext()) {
            outGripperOption.add(copy(iteratorGripperOption.next()));
        }
        out.setHoldingObject(in.isHoldingObject());
        out.setName(in.getName());
        return out;
    }

    // i = 79,clzzi=class crcl.base.SetTorqueUnitsType

    public static @Nullable
    SetTorqueUnitsType copy(@Nullable SetTorqueUnitsType in) {
        if(in != null) {
            SetTorqueUnitsType out = new SetTorqueUnitsType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetTorqueUnitsType copyTo(SetTorqueUnitsType in,SetTorqueUnitsType out) {
        assert(in.getClass()==SetTorqueUnitsType.class): "in.getClass()!=SetTorqueUnitsType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetTorqueUnitsType.class): "out.getClass()!=SetTorqueUnitsType.class: out.getClass()="+out.getClass();
        out.setUnitName(in.getUnitName());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 80,clzzi=class crcl.base.SetEndEffectorParametersType

    public static @Nullable
    SetEndEffectorParametersType copy(@Nullable SetEndEffectorParametersType in) {
        if(in != null) {
            SetEndEffectorParametersType out = new SetEndEffectorParametersType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetEndEffectorParametersType copyTo(SetEndEffectorParametersType in,SetEndEffectorParametersType out) {
        assert(in.getClass()==SetEndEffectorParametersType.class): "in.getClass()!=SetEndEffectorParametersType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetEndEffectorParametersType.class): "out.getClass()!=SetEndEffectorParametersType.class: out.getClass()="+out.getClass();
        java.util.List<crcl.base.ParameterSettingType> outParameterSetting = out.getParameterSetting();
        assert(outParameterSetting !=null):"outParameterSetting==null";
        outParameterSetting.clear();
        Iterator<crcl.base.ParameterSettingType> iteratorParameterSetting = in.getParameterSetting().iterator();
        while(iteratorParameterSetting.hasNext()) {
            outParameterSetting.add(copy(iteratorParameterSetting.next()));
        }
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 81,clzzi=class crcl.base.TransAccelType

    public static @Nullable
    TransAccelType copy(@Nullable TransAccelType in) {
        if(in != null) {
            TransAccelType out;
            if(in instanceof TransAccelAbsoluteType) {
                out = copy((TransAccelAbsoluteType)in);
            } else if(in instanceof TransAccelRelativeType) {
                out = copy((TransAccelRelativeType)in);
            } else {
                throw new RuntimeException("Unrecognized class for in="+in);
            }
            assert(out!=null):"out==null";
            return out;
        } else {
            return null;
        }
    }

    // i = 82,clzzi=class crcl.base.MoveThroughToType

    public static @Nullable
    MoveThroughToType copy(@Nullable MoveThroughToType in) {
        if(in != null) {
            MoveThroughToType out = new MoveThroughToType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    MoveThroughToType copyTo(MoveThroughToType in,MoveThroughToType out) {
        assert(in.getClass()==MoveThroughToType.class): "in.getClass()!=MoveThroughToType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==MoveThroughToType.class): "out.getClass()!=MoveThroughToType.class: out.getClass()="+out.getClass();
        out.setMoveStraight(in.isMoveStraight());
        java.util.List<crcl.base.PoseType> outWaypoint = out.getWaypoint();
        assert(outWaypoint !=null):"outWaypoint==null";
        outWaypoint.clear();
        Iterator<crcl.base.PoseType> iteratorWaypoint = in.getWaypoint().iterator();
        while(iteratorWaypoint.hasNext()) {
            outWaypoint.add(copy(iteratorWaypoint.next()));
        }
        out.setNumPositions(in.getNumPositions());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 83,clzzi=class crcl.base.ActuateJointType

    public static @Nullable
    ActuateJointType copy(@Nullable ActuateJointType in) {
        if(in != null) {
            ActuateJointType out = new ActuateJointType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ActuateJointType copyTo(ActuateJointType in,ActuateJointType out) {
        assert(in.getClass()==ActuateJointType.class): "in.getClass()!=ActuateJointType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ActuateJointType.class): "out.getClass()!=ActuateJointType.class: out.getClass()="+out.getClass();
        out.setJointNumber(in.getJointNumber());
        out.setJointPosition(in.getJointPosition());
        out.setJointDetails(copy(in.getJointDetails()));
        out.setName(in.getName());
        return out;
    }

    // i = 84,clzzi=class crcl.base.TransAccelRelativeType

    public static @Nullable
    TransAccelRelativeType copy(@Nullable TransAccelRelativeType in) {
        if(in != null) {
            TransAccelRelativeType out = new TransAccelRelativeType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    TransAccelRelativeType copyTo(TransAccelRelativeType in,TransAccelRelativeType out) {
        assert(in.getClass()==TransAccelRelativeType.class): "in.getClass()!=TransAccelRelativeType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==TransAccelRelativeType.class): "out.getClass()!=TransAccelRelativeType.class: out.getClass()="+out.getClass();
        out.setFraction(in.getFraction());
        out.setName(in.getName());
        return out;
    }

    // i = 85,clzzi=class crcl.base.SetEndEffectorType

    public static @Nullable
    SetEndEffectorType copy(@Nullable SetEndEffectorType in) {
        if(in != null) {
            SetEndEffectorType out = new SetEndEffectorType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetEndEffectorType copyTo(SetEndEffectorType in,SetEndEffectorType out) {
        assert(in.getClass()==SetEndEffectorType.class): "in.getClass()!=SetEndEffectorType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetEndEffectorType.class): "out.getClass()!=SetEndEffectorType.class: out.getClass()="+out.getClass();
        out.setSetting(in.getSetting());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 86,clzzi=class crcl.base.TransAccelAbsoluteType

    public static @Nullable
    TransAccelAbsoluteType copy(@Nullable TransAccelAbsoluteType in) {
        if(in != null) {
            TransAccelAbsoluteType out = new TransAccelAbsoluteType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    TransAccelAbsoluteType copyTo(TransAccelAbsoluteType in,TransAccelAbsoluteType out) {
        assert(in.getClass()==TransAccelAbsoluteType.class): "in.getClass()!=TransAccelAbsoluteType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==TransAccelAbsoluteType.class): "out.getClass()!=TransAccelAbsoluteType.class: out.getClass()="+out.getClass();
        out.setSetting(in.getSetting());
        out.setName(in.getName());
        return out;
    }

    // i = 87,clzzi=class crcl.base.GetStatusType

    public static @Nullable
    GetStatusType copy(@Nullable GetStatusType in) {
        if(in != null) {
            GetStatusType out = new GetStatusType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    GetStatusType copyTo(GetStatusType in,GetStatusType out) {
        assert(in.getClass()==GetStatusType.class): "in.getClass()!=GetStatusType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==GetStatusType.class): "out.getClass()!=GetStatusType.class: out.getClass()="+out.getClass();
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 88,clzzi=class crcl.base.ConfigureJointReportType

    public static @Nullable
    ConfigureJointReportType copy(@Nullable ConfigureJointReportType in) {
        if(in != null) {
            ConfigureJointReportType out = new ConfigureJointReportType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    ConfigureJointReportType copyTo(ConfigureJointReportType in,ConfigureJointReportType out) {
        assert(in.getClass()==ConfigureJointReportType.class): "in.getClass()!=ConfigureJointReportType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==ConfigureJointReportType.class): "out.getClass()!=ConfigureJointReportType.class: out.getClass()="+out.getClass();
        out.setReportPosition(in.isReportPosition());
        out.setReportTorqueOrForce(in.isReportTorqueOrForce());
        out.setReportVelocity(in.isReportVelocity());
        out.setJointNumber(in.getJointNumber());
        out.setName(in.getName());
        return out;
    }

    // i = 89,clzzi=class crcl.base.SetEndPoseToleranceType

    public static @Nullable
    SetEndPoseToleranceType copy(@Nullable SetEndPoseToleranceType in) {
        if(in != null) {
            SetEndPoseToleranceType out = new SetEndPoseToleranceType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    SetEndPoseToleranceType copyTo(SetEndPoseToleranceType in,SetEndPoseToleranceType out) {
        assert(in.getClass()==SetEndPoseToleranceType.class): "in.getClass()!=SetEndPoseToleranceType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==SetEndPoseToleranceType.class): "out.getClass()!=SetEndPoseToleranceType.class: out.getClass()="+out.getClass();
        out.setTolerance(copy(in.getTolerance()));
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 90,clzzi=class crcl.base.PoseAndSetType

    public static @Nullable
    PoseAndSetType copy(@Nullable PoseAndSetType in) {
        if(in != null) {
            PoseAndSetType out = new PoseAndSetType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    PoseAndSetType copyTo(PoseAndSetType in,PoseAndSetType out) {
        assert(in.getClass()==PoseAndSetType.class): "in.getClass()!=PoseAndSetType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==PoseAndSetType.class): "out.getClass()!=PoseAndSetType.class: out.getClass()="+out.getClass();
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
    }

    // i = 91,clzzi=class crcl.base.StopMotionType

    public static @Nullable
    StopMotionType copy(@Nullable StopMotionType in) {
        if(in != null) {
            StopMotionType out = new StopMotionType();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    StopMotionType copyTo(StopMotionType in,StopMotionType out) {
        assert(in.getClass()==StopMotionType.class): "in.getClass()!=StopMotionType.class : in.getClass()="+in.getClass();
        assert(out.getClass()==StopMotionType.class): "out.getClass()!=StopMotionType.class: out.getClass()="+out.getClass();
        out.setStopCondition(in.getStopCondition());
        out.setCommandID(in.getCommandID());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        out.setName(in.getName());
        return out;
    }

    // i = 92,clzzi=class crcl.utils.CRCLCommandWrapper

    public static @Nullable
    CRCLCommandWrapper copy(@Nullable CRCLCommandWrapper in) {
        if(in != null) {
            CRCLCommandWrapper out = new CRCLCommandWrapper();
            return copyTo(in,out);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"nullness"})
    public static
    CRCLCommandWrapper copyTo(CRCLCommandWrapper in,CRCLCommandWrapper out) {
        assert(in.getClass()==CRCLCommandWrapper.class): "in.getClass()!=CRCLCommandWrapper.class : in.getClass()="+in.getClass();
        assert(out.getClass()==CRCLCommandWrapper.class): "out.getClass()!=CRCLCommandWrapper.class: out.getClass()="+out.getClass();
        out.setName(in.getName());
        out.setCommandID(in.getCommandID());
        out.setCurProgramIndex(in.getCurProgramIndex());
        out.setCurProgram(in.getCurProgram());
        out.setWrappedCommand(copy(in.getWrappedCommand()));
        java.util.concurrent.ConcurrentLinkedDeque<crcl.utils.CRCLCommandWrapperConsumer> outOnStartListeners = out.getOnStartListeners();
        assert(outOnStartListeners !=null):"outOnStartListeners==null";
        outOnStartListeners.clear();
        Iterator<crcl.utils.CRCLCommandWrapperConsumer> iteratorOnStartListeners = in.getOnStartListeners().iterator();
        while(iteratorOnStartListeners.hasNext()) {
            outOnStartListeners.add(iteratorOnStartListeners.next());
        }
        java.util.concurrent.ConcurrentLinkedDeque<crcl.utils.CRCLCommandWrapperConsumer> outOnDoneListeners = out.getOnDoneListeners();
        assert(outOnDoneListeners !=null):"outOnDoneListeners==null";
        outOnDoneListeners.clear();
        Iterator<crcl.utils.CRCLCommandWrapperConsumer> iteratorOnDoneListeners = in.getOnDoneListeners().iterator();
        while(iteratorOnDoneListeners.hasNext()) {
            outOnDoneListeners.add(iteratorOnDoneListeners.next());
        }
        java.util.concurrent.ConcurrentLinkedDeque<crcl.utils.CRCLCommandWrapperConsumer> outOnErrorListeners = out.getOnErrorListeners();
        assert(outOnErrorListeners !=null):"outOnErrorListeners==null";
        outOnErrorListeners.clear();
        Iterator<crcl.utils.CRCLCommandWrapperConsumer> iteratorOnErrorListeners = in.getOnErrorListeners().iterator();
        while(iteratorOnErrorListeners.hasNext()) {
            outOnErrorListeners.add(iteratorOnErrorListeners.next());
        }
        out.setMessage(in.getMessage());
        java.util.List<crcl.base.GuardType> outGuard = out.getGuard();
        assert(outGuard !=null):"outGuard==null";
        outGuard.clear();
        Iterator<crcl.base.GuardType> iteratorGuard = in.getGuard().iterator();
        while(iteratorGuard.hasNext()) {
            outGuard.add(copy(iteratorGuard.next()));
        }
        return out;
    }

    // i = 93,clzzi=interface java.util.List
    // i = 94,clzzi=class java.lang.Number
    // i = 95,clzzi=class java.lang.Double
    // i = 96,clzzi=class java.lang.Integer
    // i = 97,clzzi=class java.lang.Boolean
    // i = 98,clzzi=class java.lang.Long
    // i = 99,clzzi=interface crcl.utils.CRCLCommandWrapperConsumer
    // i = 100,clzzi=class java.util.AbstractCollection
    // i = 101,clzzi=class java.util.concurrent.ConcurrentLinkedDeque
}

