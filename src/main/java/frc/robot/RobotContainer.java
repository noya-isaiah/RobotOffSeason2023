// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ma5951.utils.StateControl.StateControl;
import com.ma5951.utils.StateControl.StateRunner;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Automations.ResetElevator;
import frc.robot.subsystems.Intake.Intake;

public class RobotContainer {

  public static final CommandPS4Controller DRIVER_PS4_CONTROLLER = new CommandPS4Controller(
                        OperatorConstants.DRIVER_CONTROLLER_PORT);

  public static final CommandPS4Controller OPERATOR_PS4_CONTROLLER = new CommandPS4Controller(
                        OperatorConstants.OPERATOR_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    
    //robot driver buttons
    DRIVER_PS4_CONTROLLER.cross().whileTrue(new ResetElevator());//reset elevator


    // robot operator buttons
    OPERATOR_PS4_CONTROLLER.circle().whileTrue(new InstantCommand(()-> Intake.getInstance().intakeCone()));//whants to intake cone
    OPERATOR_PS4_CONTROLLER.cross().whileTrue(new InstantCommand(()-> Intake.getInstance().intakeCube()));//whants to intake cube
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
