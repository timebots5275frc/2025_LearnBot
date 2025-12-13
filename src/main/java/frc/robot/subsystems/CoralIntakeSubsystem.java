package frc.robot.subsystems;



import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CoralIntakeSubsystem extends SubsystemBase{
    private LaserCANSubsystem laserCANSubsystem;
   

    public CoralIntakeSubsystem(LaserCANSubsystem laserCANSubsystem) {
        this.laserCANSubsystem = laserCANSubsystem;
       
    }

    @Override
    public void periodic() {
        
    }
    


}
