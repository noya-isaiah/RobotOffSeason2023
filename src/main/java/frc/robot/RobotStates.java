package frc.robot;

import com.ma5951.utils.StateControl.State;

import frc.robot.commands.Automations.ResetElevator;
import frc.robot.commands.Automations.SetElevator;
import frc.robot.commands.Automations.SetElevatorAndIntake;
import frc.robot.subsystems.Intake.IntakeConstants;
import frc.robot.subsystems.elevator.ElevatorConstants;

public class RobotStates {
    public static final State CLOSE_STATE = new State(null, new ResetElevator());
    public static final State LOW_CONE_STATE = new State(null, new SetElevator(ElevatorConstants.LOW_CONE_POS));
    public static final State LOW_CUBE_STATE = new State(null, new SetElevator(ElevatorConstants.LOW_CUBE_POS));
    public static final State MIDDLE_CONE_STATE = new State(null, new SetElevator(ElevatorConstants.MIDDLE_CONE_POS));
    public static final State MIDDLE_CUBE_STATE = new State(null, new SetElevator(ElevatorConstants.MIDDLE_CUBE_POS));
    public static final State HIGH_CONE_STATE = new State(null, new SetElevator(ElevatorConstants.HIGH_CONE_POS));
    public static final State HIGH_CUBE_STATE = new State(null, new SetElevator(ElevatorConstants.HIGH_CUBE_POS));
    public static final State SHELF_CONE_STATE = new State(null, new SetElevatorAndIntake(ElevatorConstants.elevatorPower, IntakeConstants.intakeConePower));
    public static final State SHELF_CUBE_STATE = new State(null, new SetElevatorAndIntake(ElevatorConstants.elevatorPower, IntakeConstants.intakeCubePower));

    public static final State[] robotStates = {
        CLOSE_STATE,
        LOW_CONE_STATE,
        LOW_CUBE_STATE,
        MIDDLE_CONE_STATE,
        MIDDLE_CUBE_STATE,
        HIGH_CONE_STATE,
        HIGH_CUBE_STATE,
        SHELF_CONE_STATE,
        SHELF_CUBE_STATE
    };
}
