// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.CustomTypes.PID;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final double INTAKE_PIVOT_ROTATIONS_PER_DEGREE = (270.0/360.0)/*(270/(double)360)*/;
  
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;

  }

  public final class ElevatorConstants {
    public static final PID ELEVATOR_PID = new PID(0,0,0,0,0);//idk 


    public static final int ELEVATOR_MOTOR_1_ID = 41;
    public static final int ELEVATOR_MOTOR_2_ID = 42;

    public static final double LEVEL_TWO = (INTAKE_PIVOT_ROTATIONS_PER_DEGREE * (360*.07));
    public static final double LEVEL_THREE = (INTAKE_PIVOT_ROTATIONS_PER_DEGREE * (360*.14));
    public static final double LEVEL_FOUR = (INTAKE_PIVOT_ROTATIONS_PER_DEGREE * (360*.27));
    public static final double DRIVE      = 0;
    public static final double INTAKE     = (INTAKE_PIVOT_ROTATIONS_PER_DEGREE * (360*.0233));
    public static final double ALGAE =INTAKE_PIVOT_ROTATIONS_PER_DEGREE*360*.2;


  }
}
