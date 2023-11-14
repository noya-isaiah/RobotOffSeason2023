package frc.robot;

import com.ma5951.utils.StateControl.State;

import frc.robot.commands.Automations.ResetElevator;
import frc.robot.commands.Automations.SetElevator;
import frc.robot.subsystems.Intake.Intake;
import frc.robot.subsystems.elevator.ElevatorConstants;

public class RobotStates {

    public static class ElevatorStates{
        public static final State LOW_CONE_STATE = new State(null, new SetElevator(ElevatorConstants.LOW_CONE_POS));//TODO
        public static final State LOW_CUBE_STATE = new State(null, new SetElevator(ElevatorConstants.LOW_CUBE_POS));//TODO
        public static final State MIDDLE_CONE_STATE = new State(null, new SetElevator(ElevatorConstants.MIDDLE_CONE_POS));//TODO
        public static final State MIDDLE_CUBE_STATE = new State(null, new SetElevator(ElevatorConstants.MIDDLE_CUBE_POS));//TODO
        public static final State HIGH_CONE_STATE = new State(null, new SetElevator(ElevatorConstants.HIGH_CONE_POS));//TODO
        public static final State HIGH_CUBE_STATE = new State(null, new SetElevator(ElevatorConstants.HIGH_CUBE_POS));//TODO
        public static final State SHELF_CONE_STATE = new State(null, new SetElevator(ElevatorConstants.SHELF_CONE_POS));//TODO
        public static final State SHELF_CUBE_STATE = new State(null, new SetElevator(ElevatorConstants.SHELF_CUBE_POS));//TODO
    
        public static final State[] elevatorStates = {
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

    public static class IntakeStates{
        public static final State INTAKE_CONE_STATE = new State(Intake.getInstance()::intakeConetate, null);//TODO
        public static final State INTKE_CUBE_STATE = new State(Intake.getInstance()::intakeCubeState, null);//TODO
        public static final State EJECT_CONE_STATE = new State(null, null);//TODO
        public static final State EJECT_CUBE_STATE = new State(null, null);//TODO

        public static final State[] intakeStates = {
            INTAKE_CONE_STATE,
            INTKE_CUBE_STATE,
            EJECT_CONE_STATE,
            EJECT_CUBE_STATE
        };

    }
    
}
