package frc.robot.subsystems;

import com.ctre.phoenix6.signals.ControlModeValue;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AlgaeIntakeSubsystem extends SubsystemBase{

    private AlgaeIntakeStates algaeIntakeState;
    private AlgaePivotStates algaePivotState;

    private SparkMax intakeMotor;
    private SparkClosedLoopController intakePID;

    private SparkMax pivotMotor;
    private SparkClosedLoopController pivotPID;
    private SparkMaxConfig sparkmaxconfig = new SparkMaxConfig();
    public RelativeEncoder algaePivotEncoder;

    public AlgaeIntakeSubsystem() {
        initMotors();
        algaeIntakeState = AlgaeIntakeStates.NONE;
        algaePivotState = AlgaePivotStates.DRIVE;
    }

    public enum AlgaeIntakeStates {
        INTAKE,
        OUTTAKE,
        NONE
    }

    public enum AlgaePivotStates {
        DRIVE,
        SHOOT,
        REEF,
        GROUND
    }

    private void initMotors() {
        intakeMotor = new SparkMax(Constants.AlgaeIntakeConstants.ALGAE_INTAKE_RUN_MOTOR_ID, SparkLowLevel.MotorType.kBrushless);
        Constants.AlgaeIntakeConstants.ALGAE_INTAKE_RUN_PID.setSparkMaxPID(intakeMotor, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        intakePID = intakeMotor.getClosedLoopController();
        Constants.AlgaeIntakeConstants.ALGAE_INTAKE_RUN_PID.setSparkMaxPID(intakeMotor, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters, IdleMode.kCoast);

        pivotMotor = new SparkMax(Constants.AlgaeIntakeConstants.ALGAE_PIVOT_MOTOR_ID, SparkLowLevel.MotorType.kBrushless);
        algaePivotEncoder = pivotMotor.getEncoder();
        sparkmaxconfig.idleMode(IdleMode.kBrake);
        Constants.AlgaeIntakeConstants.ALGAE_INTAKE_PIVOT_PID.setSparkMaxPID(pivotMotor, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters, IdleMode.kCoast);
        pivotPID = pivotMotor.getClosedLoopController();
    }

    public void setIntakeState(AlgaeIntakeStates state) {
        algaeIntakeState = state;
        updateIntakeMotors();
    }

    private void updateIntakeMotors() {
        switch (algaeIntakeState) {
            case INTAKE:
                intakePID.setReference(Constants.AlgaeIntakeConstants.ALGAE_INTAKE_RUN_SPEED, ControlType.kVelocity);
                break;
            case NONE:
                intakePID.setReference(0, ControlType.kCurrent);
                break;
            case OUTTAKE:
                intakePID.setReference(-Constants.AlgaeIntakeConstants.ALGAE_INTAKE_RUN_SPEED, ControlType.kVelocity);
                break;
        }
    }

    public void setPivotState(AlgaePivotStates state) {
        algaePivotState = state;
        updatePivotMotors();
    }

    private void updatePivotMotors() {
        switch (algaePivotState) {
            case DRIVE:
                pivotPID.setReference(Constants.AlgaeIntakeConstants.DRIVE_ANGLE, ControlType.kPosition);
                break;
            case GROUND:
                pivotPID.setReference(Constants.AlgaeIntakeConstants.ALGAE_GROUND_ANGLE, ControlType.kPosition);
                break;
            case REEF:
                pivotPID.setReference(Constants.AlgaeIntakeConstants.ALGAE_REEF_ANGLE, ControlType.kPosition);
                break;
            case SHOOT:
                pivotPID.setReference(Constants.AlgaeIntakeConstants.SHOOT_ANGLE, ControlType.kPosition);
                break;
        }
    }

    @Override
    public void periodic() {
        
    }

}
