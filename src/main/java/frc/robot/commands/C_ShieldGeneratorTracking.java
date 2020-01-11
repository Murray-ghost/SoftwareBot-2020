/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SS_Vision;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class C_ShieldGeneratorTracking extends CommandBase {
  /**
   * Creates a new ShieldGeneratorTracking.
   */

float KpAim = -0.1f;
float KpDistance = -0.1f;
float minAimCommand = 0.05f;
float steeringAdjaus = 0.0f;
double ch = 0; // camera hight
double th = 0; // target hight
double angleOftarget = 0;
double angleofcamera = 0;
SS_Vision vision;

  public C_ShieldGeneratorTracking() {
    // Use addRequirements() here to declare subsystem dependencies.
    SS_Vision vision = new SS_Vision();
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double distance = EstimateDistance(vision.getY());
    SmartDashboard.putNumber("Distacnec: ", distance);
    vision.updateTelemetry();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public float GetXPos(float tx, float ty){
    float headingError = -tx;
    float distanceError = -ty;
    float steeringAdjaus = this.steeringAdjaus;

    if (tx > 1.0){
      steeringAdjaus = KpAim*headingError - minAimCommand;
    }else if (tx < 1.0){
      steeringAdjaus = KpAim*headingError + minAimCommand;
    }
    float distanceAdjust = KpDistance * distanceError;

    return distanceAdjust + steeringAdjaus;
  }

  public double EstimateDistance(double tx){
    double distance = 0;
    angleOftarget = tx;
    distance = (th - ch) / Math.tan((angleofcamera+angleOftarget)*Math.PI/180.0);

    return distance;
    
  }
}
