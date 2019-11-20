/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.commands.idle.MecDriverStop;


public class MecDriveBoxGroup extends CommandGroup {

  public MecDriveBoxGroup() {
    addSequential(new MecDriveStraightDistance());
    addSequential(new MecDriverStop());
    addSequential(new MecDriveRotateAngle(90., 0.1));
    addSequential(new MecDriverStop());
    addSequential(new MecDriveStraightDistance());
    addSequential(new MecDriverStop());
    addSequential(new MecDriveRotateAngle(90., 0.1));
    addSequential(new MecDriverStop());
    addSequential(new MecDriveStraightDistance());
    addSequential(new MecDriverStop());
    addSequential(new MecDriveRotateAngle(90., 0.1));
    addSequential(new MecDriverStop());
    addSequential(new MecDriveStraightDistance());
    addSequential(new MecDriverStop());
    addSequential(new MecDriveRotateAngle(90., 0.1));
    addSequential(new MecDriverStop());
  }
}
