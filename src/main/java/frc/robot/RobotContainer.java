// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.SetElevatorState;
import frc.robot.commands.TeleopJoystickDrive;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DriveTrain.SwerveDrive;
import frc.robot.subsystems.Input.Input;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem.ElevatorState;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  
  ElevatorSubsystem elevatorSub;
  Joystick joy;
  Input input;
  TeleopJoystickDrive teleJoyDrive;
  SwerveDrive swerve;
  GenericHID bBoard;

  /*  Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  */

  /** The container for the robot. Contains subsystems, OI devices, and commands. */

  public RobotContainer() {
    bBoard = new GenericHID(1);
    joy = new Joystick(0);

    input = new Input(joy);
    swerve = new SwerveDrive();

    elevatorSub = new ElevatorSubsystem();


    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    teleJoyDrive = new TeleopJoystickDrive(swerve, input, true);
    swerve.setDefaultCommand(teleJoyDrive);
    swerve.resetPigeon();


    new JoystickButton(joy, 7).onTrue(new InstantCommand(swerve::flipFieldRelative ,swerve));
    /*tmp */
    //pigeon
    new JoystickButton(joy, 8).onTrue(new InstantCommand(swerve::resetPigeon, swerve));


    //Elevator
    new JoystickButton(bBoard, Constants.ButtonConstants.ELEVATOR_L2).onTrue(new SetElevatorState(elevatorSub, ElevatorState.L2));
    new JoystickButton(bBoard, Constants.ButtonConstants.ELEVATOR_L3).onTrue(new SetElevatorState(elevatorSub, ElevatorState.L3));
    new JoystickButton(bBoard, Constants.ButtonConstants.ELEVATOR_L4).onTrue(new SetElevatorState(elevatorSub, ElevatorState.L4));
    //new JoystickButton(bBoard, Constants.ButtonConstants.ELEVATOR_DRIVE).onTrue(new SetElevatorState(elevatorSub, ElevatorState.DRIVE));  set button
    new JoystickButton(bBoard, Constants.ButtonConstants.ELEVATOR_INTAKE).onTrue(new SetElevatorState(elevatorSub, ElevatorState.CORALINTAKE));
    

    /*  Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    /*  An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
    */
    return null;
  }
}
