// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Intake;

import com.ma5951.utils.MAShuffleboard;
import com.ma5951.utils.subsystem.MotorSubsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalOutput;
import frc.robot.PortMap;

public class Intake implements MotorSubsystem {

  private CANSparkMax intakeMotor;

  private static Intake instance;

  private DigitalOutput sensor;

  private MAShuffleboard shuffleboard;

  private boolean isCone;

  private boolean isCube;

  
  public Intake() {
    intakeMotor = new CANSparkMax(PortMap.Intake.intakeMotorID, MotorType.kBrushless);
    sensor = new DigitalOutput(PortMap.Intake.intakeSensorChannel);

    shuffleboard = new MAShuffleboard(IntakeConstants.shuffleboardTabName);
  }

  @Override
  public boolean canMove() {
    return !isGamePiece();
  }

  @Override
  public void setVoltage(double voltage) {
    intakeMotor.setVoltage(voltage);
  }

  public double getCurrent(){
    return intakeMotor.getOutputCurrent();
  }

  public boolean isSensorPressed(){
    return sensor.get();
  }

  public boolean isCube(){
    return isCube;
  }

  public boolean isCone(){
    return isCone ;
  }

  public boolean isGamePiece(){
    return isCube() || isCone();
  }

  public static Intake getInstance(){
    if(instance == null){
      instance = new Intake();
    }
    return instance;
  }

  @Override
  public void periodic() {
    if(isSensorPressed()){isCube = true;}
    else{isCube = false;}

    if(getCurrent() > IntakeConstants.minConeCurrentJump){isCone = true;}
    else{isCone = false;}

    shuffleboard.addBoolean("isCone", isCone());
    shuffleboard.addBoolean("isCube", isCube());
    shuffleboard.addBoolean("isGamePiece", isGamePiece());
  }
}
