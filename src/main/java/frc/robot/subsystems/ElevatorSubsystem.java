package frc.robot.subsystems;

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
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {

    private ElevatorState elevatorState = ElevatorState.Base;

    private SparkMax motor1;
    private SparkMax motor2;
    private SparkClosedLoopController elevatorPID;
  

    public ElevatorSubsystem() {
        initMotors();
    }

    public enum ElevatorState {
        L2,
        L3,
        L4,
        Base,
        CoralIntake,
        None,
        Algae
    } 


    private void initMotors() {
        motor1 = new SparkMax(Constants.ElevatorConstants.ELEVATOR_MOTOR_1_ID, SparkLowLevel.MotorType.kBrushless);
        SparkMaxConfig ehm1 = ElevatorConstants.ELEVATOR_PID.setSparkMaxPID(motor1, IdleMode.kBrake);
        motor1.configure(ehm1, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        elevatorPID = motor1.getClosedLoopController();

        motor2 = new SparkMax(Constants.ElevatorConstants.ELEVATOR_MOTOR_2_ID, SparkLowLevel.MotorType.kBrushless);
        SparkMaxConfig ehm2 = ElevatorConstants.ELEVATOR_PID.setSparkMaxPID(motor1, IdleMode.kBrake);
        ehm2.follow(motor1, true);
        motor2.configure(ehm2, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
        elevatorState = ElevatorState.None;
    } 

    public void setElevatorHeight(ElevatorState state) {
        elevatorState = state;
        updateElevatorHeight();
    }


    private void updateElevatorHeight() {
        switch (elevatorState) {
            case CoralIntake:
                elevatorPID.setReference(Constants.ElevatorConstants.INTAKE, ControlType.kPosition);
                break;
            case L2:
                elevatorPID.setReference(Constants.ElevatorConstants.LEVEL_TWO, ControlType.kPosition);    
                break;
            case L3:
                elevatorPID.setReference(Constants.ElevatorConstants.LEVEL_THREE, ControlType.kPosition);
                break;
            case L4:
                elevatorPID.setReference(Constants.ElevatorConstants.LEVEL_FOUR, ControlType.kPosition);
                break;
            case None:
                elevatorPID.setReference(0, ControlType.kVoltage);
                break;
            case Algae:
                elevatorPID.setReference(Constants.ElevatorConstants.ALGAE, ControlType.kPosition);
                break;
            default:
                elevatorPID.setReference(0, ControlType.kVoltage);
                break;
        }

    }




 
}
