/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class SS_Hatch extends SubsystemBase {
  /**
   * Creates a new SS_Hatch.
   */
  private Solenoid hatch; 
  public SS_Hatch() {
    hatch = new Solenoid(RobotMap.HATCH_SOLENOID);
  }

  public void closeHatch(boolean state){
    hatch.set(state);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
