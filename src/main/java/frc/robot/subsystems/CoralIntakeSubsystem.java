package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.ControlType;
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
   

    public CoralIntakeSubsystem(LaserCANSubsystem laserCANSubsystem) {
        this.laserCANSubsystem = laserCANSubsystem;
       
    }

    @Override
    public void periodic() {
        
    }
    


}
