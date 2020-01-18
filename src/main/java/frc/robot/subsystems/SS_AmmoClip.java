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

public class SS_AmmoClip extends SubsystemBase {
  /**
   * Creates a new SS_AmmoClip.
   */
  private CANSparkMax clip;
  public SS_AmmoClip() {
    clip = new CANSparkMax(RobotMap.AMMO_CLIP_MOTOR, MotorType.kBrushless);
  }

  public void setSpeed(double speed){
    clip.set(speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
