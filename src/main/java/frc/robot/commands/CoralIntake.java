package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralIntakeSubsystem;
import frc.robot.subsystems.CoralIntakeSubsystem.CoralIntakeStates;

public class CoralIntake extends Command {

    CoralIntakeSubsystem coralIntakeSub;
    CoralIntakeStates coralState;

    public CoralIntake(CoralIntakeSubsystem sub, CoralIntakeStates state) {
        coralIntakeSub = sub;
        coralState = state;
        addRequirements(sub);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        coralIntakeSub.updateCoralState(coralState);
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
        return coralIntakeSub.getCoralOutOfWay();
    }

    

}
