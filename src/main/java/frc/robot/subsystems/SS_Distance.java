/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.SS_Vision;

public class SS_Distance extends SubsystemBase {
  /**
   * Creates a new SS_Distance.
   */

  double CamerHight; // this is the hight of the camera off the ground
  double CamerAngleFromeGround; // this is the camera angle frome the ground
  double TargetCenterHigth; // this is the hight of the center of the target
 

  SS_Vision vision;

  public SS_Distance(double CamerHight,double CamerAngleFromeGround, double TargetCenterHigth) {

    // calls SS_vision as vision
    vision = new SS_Vision();


    this.CamerHight = CamerHight;
    this.CamerAngleFromeGround = CamerAngleFromeGround;
    this.TargetCenterHigth = TargetCenterHigth;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  //this returns the Distance between the taget and the camera if they were on the same plane
  public double GetTargetDistance(){

    double distance = 0;
    distance = (TargetCenterHigth - CamerHight) / Math.tan((CamerAngleFromeGround + vision.getY()) * Math.PI/180.0);
    return distance;
    
  }
}
