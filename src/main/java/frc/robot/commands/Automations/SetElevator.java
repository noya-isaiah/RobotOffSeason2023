package frc.robot.commands.Automations;

import com.ma5951.utils.commands.RunInternallyControlledSubsystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.elevator.Elevator;

public class SetElevator extends SequentialCommandGroup {

  public SetElevator(double setPoint) {
    addCommands(
      new RunInternallyControlledSubsystem(Elevator.getInstance(),setPoint,false)
    );
  }
}
