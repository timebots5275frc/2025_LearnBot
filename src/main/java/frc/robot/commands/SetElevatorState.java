package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem.ElevatorState;

public class SetElevatorState extends Command{

    public ElevatorSubsystem elevatorSubsystem;
    ElevatorState elevatorState;
    
    public SetElevatorState(ElevatorSubsystem sub, ElevatorState state) {
        this.elevatorSubsystem = sub;
        this.elevatorState = state;

        addRequirements(sub);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        elevatorSubsystem.setElevatorHeight(elevatorState);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
       return false;
    }


}
