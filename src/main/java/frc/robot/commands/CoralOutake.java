package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.CoralIntakeSubsystem;
import frc.robot.subsystems.CoralIntakeSubsystem.CoralIntakeStates;

public class CoralOutake extends InstantCommand {

    CoralIntakeSubsystem coralIntakeSub;
    CoralIntakeStates coralState;

    public CoralOutake(CoralIntakeSubsystem sub, CoralIntakeStates state) {
        coralIntakeSub = sub;
        coralState = state;
        addRequirements(sub);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
        coralIntakeSub.updateCoralState(coralState);
    }
    

}
