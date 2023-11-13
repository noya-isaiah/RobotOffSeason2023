// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Automations;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SetElevatorAndIntake extends SequentialCommandGroup {

  public SetElevatorAndIntake(double elevatorPower, double intakePower) {
    addCommands(
      new SetElevator(0),
      new SetIntake(0)
    );
  }
}
