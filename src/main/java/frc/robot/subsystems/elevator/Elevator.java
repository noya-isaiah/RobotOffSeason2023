// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

import com.ma5951.utils.MAShuffleboard;
import com.ma5951.utils.subsystem.AbstractDefaultInternallyControlledSubsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.PortMap;


public class Elevator extends AbstractDefaultInternallyControlledSubsystem {

  private CANSparkMax firstElevatorMotor;
  private CANSparkMax secondElevatorMotor;

  private static Elevator instance;

  private SparkMaxPIDController pid;

  private RelativeEncoder encoder;

  private MAShuffleboard shuffleboard;

  private boolean isElevatorClose;


  public Elevator() {
    firstElevatorMotor = new CANSparkMax(PortMap.Elevator.firstElevatorMotor, MotorType.kBrushless);
    secondElevatorMotor = new CANSparkMax(PortMap.Elevator.secondEkevatorMotor, MotorType.kBrushless);

    secondElevatorMotor.follow(firstElevatorMotor);

    pid = firstElevatorMotor.getPIDController();
    pid.setP(ElevatorConstants.kP);
    pid.setI(ElevatorConstants.kI);
    pid.setD(ElevatorConstants.kD);

    encoder = firstElevatorMotor.getEncoder();

    shuffleboard = new MAShuffleboard(ElevatorConstants.shuffleboardTabName);
  }

  @Override
  public void calculate(double setPoint) {
    pid.setReference(getSetPoint(),ControlType.kPosition);
  }

  @Override
  public double getMeasurement() {
    return encoder.getPosition();
  }

  @Override
  public void setVoltage(double voltage) {
    firstElevatorMotor.setVoltage(voltage);;
  }

  public void resetEncoder(){
    encoder.setPosition(0);
  }

  public boolean isElevatorClose(){
    return isElevatorClose;
  }

  @Override
  public boolean canMove() {
    return !isElevatorClose();
  }

  public static Elevator getInstance(){
    if(instance == null){
      instance = new Elevator();
    }
    return instance;
  }

  @Override
  public void periodic() {
    if(firstElevatorMotor.getOutputCurrent() > ElevatorConstants.closedElevatorCurrentJump){
      isElevatorClose = true;
      resetEncoder();
    }

    shuffleboard.addBoolean("isElevatorClose", isElevatorClose);
    shuffleboard.addNum("position", getMeasurement());
    shuffleboard.addBoolean("atPoint", atPoint());
  }
}
