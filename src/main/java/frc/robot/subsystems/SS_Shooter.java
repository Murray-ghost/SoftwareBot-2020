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

public class SS_Shooter extends SubsystemBase {
  private CANSparkMax shooter;
  public SS_Shooter() {
    shooter = new CANSparkMax(RobotMap.SHOOTER_MOTOR, MotorType.kBrushless);
  }

  public void setSpeed(double speed){
    shooter.set(speed);
  }
  public double getRPM(){
    return shooter.getEncoder().getVelocity();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
