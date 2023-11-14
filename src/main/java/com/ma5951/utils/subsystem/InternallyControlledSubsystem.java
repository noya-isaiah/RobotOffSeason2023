package com.ma5951.utils.subsystem;

public interface InternallyControlledSubsystem extends ControllableSubsystem {
  public void calculate(double setPoint);

  public boolean atPoint(double tolerance);

  public void setSetPoint(double setPoint);
}
