package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.CustomTypes.PID;

public class CoralIntakeSubsystem extends SubsystemBase{
    private LaserCANSubsystem laserCANSubsystem;
    private CoralIntakeStates coralIntakeState = CoralIntakeStates.NONE;
    
    private SparkMax intakeMotor1;
    private SparkMax intakeMotor2;

    private SparkClosedLoopController PID1;
    private SparkClosedLoopController PID2;

    public CoralIntakeSubsystem(LaserCANSubsystem laserCANSubsystem) {
        this.laserCANSubsystem = laserCANSubsystem;
        initMotors();
    }

    public enum CoralIntakeStates {
        INTAKE,
        OUTTAKE_L1,
        OUTTAKE_L2_TO_L3,
        OUTTAKE_L4,
        NONE
    }

    private void initMotors() {
        intakeMotor1 = new SparkMax(Constants.CoralIntakeConstants.CORAL_INTAKE_MOTOR_ID1, SparkLowLevel.MotorType.kBrushless);
        Constants.CoralIntakeConstants.CORAL_INTAKE_PID.setSparkMaxPID(intakeMotor1, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        PID1 = intakeMotor1.getClosedLoopController();
    
        intakeMotor2 = new SparkMax(Constants.CoralIntakeConstants.CORAL_INTAKE_MOTOR_ID2, SparkLowLevel.MotorType.kBrushless);
        Constants.CoralIntakeConstants.CORAL_INTAKE_PID.setSparkMaxPID(intakeMotor2, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        // IntakeEncoderTwo = IntakeMotorTwo.getEncoder();
        PID2 = intakeMotor2.getClosedLoopController();
    }

    public void setIntakeState(CoralIntakeStates state) {
        coralIntakeState = state;
        updateIntake();
    }

    private void updateIntake() {
        switch (coralIntakeState) {
            case INTAKE: 
                break;
            case NONE:
                break;
            case OUTTAKE_L1:
                break;
            case OUTTAKE_L2_TO_L3:
                break;
            case OUTTAKE_L4:
                break;
        }
    }

    @Override
    public void periodic() {
        
    }
    


}
