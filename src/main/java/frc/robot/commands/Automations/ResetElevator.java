package frc.robot.commands.Automations;

import com.ma5951.utils.commands.MotorCommand;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.elevator.ElevatorConstants;


public class ResetElevator extends SequentialCommandGroup {

  public ResetElevator() {
    addCommands(
      new MotorCommand(Elevator.getInstance(),ElevatorConstants.closeElevatorPower, 0)
    );
  }
}
