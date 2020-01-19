/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class SS_Intake extends SubsystemBase {
  /**
   * Creates a new SS_Intake.
   */
  private CANSparkMax intake;
  public SS_Intake() {
    intake = new CANSparkMax(RobotMap.INTAKE_MOTOR, MotorType.kBrushless);
  }
  public void setSpeed(double speed){
    intake.set(speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
