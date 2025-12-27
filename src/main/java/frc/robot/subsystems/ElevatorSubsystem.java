package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {

    private ElevatorState elevatorState = ElevatorState.NONE;

    private SparkMax motor1;
    private SparkMax motor2;
    private SparkClosedLoopController elevatorPID;
    private double targetPosition;

    private SlewRateLimiter slewRateLimiter = new SlewRateLimiter(18); //where did we get this number from
  

    public ElevatorSubsystem() {
        initMotors();
    }

    public enum ElevatorState {
        L2,
        L3,
        L4,
        CORALINTAKE,
        NONE,
        ALGAE,
        DRIVE
    } 

    

    //add a slew rate limiter or whatever
    private void initMotors() {
        motor1 = new SparkMax(Constants.ElevatorConstants.ELEVATOR_MOTOR_1_ID, SparkLowLevel.MotorType.kBrushless);
        SparkMaxConfig ehm1 = ElevatorConstants.ELEVATOR_PID.setSparkMaxPID(motor1, IdleMode.kBrake);
        motor1.configure(ehm1, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        elevatorPID = motor1.getClosedLoopController();

        motor2 = new SparkMax(Constants.ElevatorConstants.ELEVATOR_MOTOR_2_ID, SparkLowLevel.MotorType.kBrushless);
        SparkMaxConfig ehm2 = ElevatorConstants.ELEVATOR_PID.setSparkMaxPID(motor1, IdleMode.kBrake);
        ehm2.follow(motor1, true);
        motor2.configure(ehm2, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
        elevatorState = ElevatorState.NONE;
    } 

    public void setElevatorHeight(ElevatorState elevatorState2) {
        elevatorState = elevatorState2;
        updateElevatorHeight();
    }
    private void setTarget(Double target) {
        targetPosition = target;
    }

    private void updateElevatorHeight() {
        switch (elevatorState) {
            case CORALINTAKE:
                setTarget(Constants.ElevatorConstants.INTAKE);
                break;
        
            case L2:
                setTarget(Constants.ElevatorConstants.LEVEL_TWO);
                break;
        
            case L3:
                setTarget(Constants.ElevatorConstants.LEVEL_THREE);
                break;
        
            case L4:
                setTarget(Constants.ElevatorConstants.LEVEL_FOUR);
                break;
        
            case ALGAE:
                setTarget(Constants.ElevatorConstants.ALGAE);
                break;
        
            case DRIVE:
                setTarget(Constants.ElevatorConstants.DRIVE);
                break;
        
            case NONE:
                elevatorPID.setReference(0, ControlType.kCurrent);
                break;
        }

    }


    
    @Override
    public void periodic() {

        
        double currentPos = motor1.getEncoder().getPosition();
        double movePos = slewRateLimiter.calculate(targetPosition);
        if(elevatorState == ElevatorState.NONE) {
            elevatorPID.setReference(currentPos, ControlType.kPosition);
        } else {
            elevatorPID.setReference(movePos, ControlType.kPosition);
        }
        
    }

 
}
