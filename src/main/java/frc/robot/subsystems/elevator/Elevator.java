// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

import com.ma5951.utils.MAShuffleboard;
import com.ma5951.utils.subsystem.DefaultInternallyControlledSubsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.PortMap;


public class Elevator implements DefaultInternallyControlledSubsystem {

  private CANSparkMax firstElevatorMotor;
  private CANSparkMax secondElevatorMotor;

  private static Elevator instance;

  private SparkMaxPIDController pid;

  private RelativeEncoder encoder;

  private MAShuffleboard shuffleboard;

  private boolean isElevatorClose;

  private double setPoint;


  public Elevator() {
    firstElevatorMotor = new CANSparkMax(PortMap.Elevator.firstElevatorMotor, MotorType.kBrushless);
    secondElevatorMotor = new CANSparkMax(PortMap.Elevator.secondEkevatorMotor, MotorType.kBrushless);

    firstElevatorMotor.setIdleMode(IdleMode.kBrake);
    secondElevatorMotor.setIdleMode(IdleMode.kBrake);

    secondElevatorMotor.follow(firstElevatorMotor);

    pid = firstElevatorMotor.getPIDController();
    pid.setFeedbackDevice(encoder);
    pid.setP(ElevatorConstants.kP);
    pid.setI(ElevatorConstants.kI);
    pid.setD(ElevatorConstants.kD);

    encoder = firstElevatorMotor.getEncoder();

    encoder.setPositionConversionFactor(
        ElevatorConstants.positionConversionFactor);
    encoder.setVelocityConversionFactor(
      ElevatorConstants.positionConversionFactor / 60);
    encoder.setPosition(0);

    firstElevatorMotor.setClosedLoopRampRate(ElevatorConstants.closedLoopRampRate);
    firstElevatorMotor.setOpenLoopRampRate(0);

    shuffleboard = new MAShuffleboard(ElevatorConstants.shuffleboardTabName);
  }

  public void calculate(double setPoint) {
    pid.setReference(getSetPoint(),ControlType.kPosition);
  }

  public void setSetPoint(double setPoint){
    this.setPoint = setPoint;
  }

  public double getSetPoint(){
    return this.setPoint;
  } 
  
  public double getPosition() {
    return encoder.getPosition();
  }

  public void setVoltage(double voltage) {
    firstElevatorMotor.setVoltage(voltage);;
  }

  public void resetEncoder(){
    encoder.setPosition(0);
  }

  public boolean atPoint(){
    return Math.abs(getPosition() - setPoint) <= ElevatorConstants.tolerance;
  }

  public boolean isElevatorClose(){
    return isElevatorClose;
  }

  public boolean canMove() {
    return !isElevatorClose();//TODO add limitsPos
  }


  public static Elevator getInstance(){
    if(instance == null){
      instance = new Elevator();
    }
    return instance;
  }

  public void periodic() {
    if(firstElevatorMotor.getOutputCurrent() > ElevatorConstants.closedElevatorCurrentJump){
      isElevatorClose = true;
    }

    shuffleboard.addBoolean("isElevatorClose", isElevatorClose);
    shuffleboard.addNum("position", getPosition());
    shuffleboard.addBoolean("atPoint", atPoint());
  }
}
