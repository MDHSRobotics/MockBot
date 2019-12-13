
package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MecDriveBoxGroup extends CommandGroup {

  public MecDriveBoxGroup() {

    addSequential(new MecDriveStraightDistance(6, 0.2));
    addSequential(new MecDriveRotateAngle(90., 0.5));   
    addSequential(new MecDriveStraightDistance(6, 0.2));
    addSequential(new MecDriveRotateAngle(90., 0.5));   
    addSequential(new MecDriveStraightDistance(6, 0.2));
    addSequential(new MecDriveRotateAngle(90., 0.5));   
    addSequential(new MecDriveStraightDistance(6, 0.2));
    addSequential(new MecDriveRotateAngle(90., 0.5));   
  }
}