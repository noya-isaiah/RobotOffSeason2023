// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Automations;

import com.ma5951.utils.commands.MotorCommand;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.Intake.Intake;


public class SetIntake extends SequentialCommandGroup {

  public SetIntake(double power) {
    addCommands(
      new ParallelDeadlineGroup(new WaitUntilCommand(Intake.getInstance()::isGamePiece), 
                                new MotorCommand(Intake.getInstance(),power, 0))
      
    );
  }
}
