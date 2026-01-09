package frc.robot.subsystems;



import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class CoralIntakeSubsystem extends SubsystemBase{
    private LaserCANSubsystem laserCANSubsystem;

    private SparkMax motor1;
    private SparkMax motor2;
    private SparkClosedLoopController IntakePIDOne;
    private SparkClosedLoopController IntakePIDTwo;

    private CoralIntakeStates coralIntakeState = CoralIntakeStates.NONE;
    public boolean coralOutOfWay;
   

    public CoralIntakeSubsystem(LaserCANSubsystem laserCANSubsystem) {
        this.laserCANSubsystem = laserCANSubsystem;
        
        motor1 = new SparkMax(Constants.CoralIntakeConstants.CORAL_INTAKE_MOTOR_ID1, SparkLowLevel.MotorType.kBrushless);
        Constants.CoralIntakeConstants.CORAL_INTAKE_PID.setSparkMaxPID(motor1, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        IntakePIDOne = motor1.getClosedLoopController();
  
        motor2 = new SparkMax(Constants.CoralIntakeConstants.CORAL_INTAKE_MOTOR_ID2, SparkLowLevel.MotorType.kBrushless);
        Constants.CoralIntakeConstants.CORAL_INTAKE_PID.setSparkMaxPID(motor2, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
        IntakePIDTwo = motor2.getClosedLoopController();
    }

    @Override
    public void periodic() {
        coralOutOfWay = coralOutOfWay();
    }

    private boolean coralOutOfWay() {
        if (laserCANSubsystem.LC1() && !laserCANSubsystem.LC2()) {
            return true;
        }
        return false;
    }

    public void updateCoralState(CoralIntakeStates state) {
        coralIntakeState = state;
        updateCoralIntake();
    }


    private void updateCoralIntake() {
        switch (coralIntakeState) {
            case NONE: IntakePIDOne.setReference(0, ControlType.kCurrent);
                IntakePIDTwo.setReference(0, ControlType.kCurrent);
                break;
            case INTAKE: IntakePIDOne.setReference(Constants.CoralIntakeConstants.CORAL_INTAKE_RUN_SPEED_NORMAL, ControlType.kVelocity);
                IntakePIDTwo.setReference(-Constants.CoralIntakeConstants.CORAL_INTAKE_RUN_SPEED_NORMAL, ControlType.kVelocity);
                break;
            case OUTTAKE_L1: IntakePIDOne.setReference(Constants.CoralIntakeConstants.CORAL_INTAKE_RUN_SPEED_L1, ControlType.kVelocity);
                IntakePIDTwo.setReference(-Constants.CoralIntakeConstants.CORAL_INTAKE_RUN_SPEED_L1, ControlType.kVelocity);
                break;
            case OUTTAKE_L2_L3: IntakePIDOne.setReference(Constants.CoralIntakeConstants.CORAL_INTAKE_RUN_SPEED_NORMAL, ControlType.kVelocity);
                IntakePIDTwo.setReference(-Constants.CoralIntakeConstants.CORAL_INTAKE_RUN_SPEED_NORMAL, ControlType.kVelocity);
                break;
            case OUTTAKE_L4:IntakePIDOne.setReference(Constants.CoralIntakeConstants.CORAL_INTAKE_RUN_SPEED_L4, ControlType.kVelocity);
                IntakePIDTwo.setReference(-Constants.CoralIntakeConstants.CORAL_INTAKE_RUN_SPEED_L4, ControlType.kVelocity);
                break;
        
        }
    }

    public enum CoralIntakeStates {
        INTAKE,
        OUTTAKE_L1,
        OUTTAKE_L2_L3,
        OUTTAKE_L4,
        NONE
    }

    
    


}
