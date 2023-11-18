// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Intake;

import com.ma5951.utils.MAShuffleboard;
import com.ma5951.utils.subsystem.MotorSubsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.PortMap;

public class Intake implements MotorSubsystem {

  private CANSparkMax intakeMotor;

  private static Intake instance;

  private DigitalInput sensor;

  private MAShuffleboard shuffleboard;

  private boolean isCone = false;
  private boolean isCube = false;

  private boolean intakeCone = false;
  private boolean intakeCube = false;

  
  public Intake() {
    intakeMotor = new CANSparkMax(PortMap.Intake.intakeMotorID, MotorType.kBrushless);
    sensor = new DigitalInput(PortMap.Intake.intakeSensorChannel);

    shuffleboard = new MAShuffleboard(IntakeConstants.shuffleboardTabName);
  }

  @Override
  public boolean canMove() {
    return true;
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

  public void intakeCone(){
    intakeCube = false;
    intakeCone = true;
  }

  public void intakeCube(){
    intakeCone = false;
    intakeCube = true;
  }

  public boolean intakeConeState(){
    return intakeCone;
  }

  public boolean intakeCubeState(){
    return intakeCube;
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
