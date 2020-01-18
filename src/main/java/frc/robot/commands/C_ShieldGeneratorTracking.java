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

double ch = 0; // camera hight
double th = 0; // target hight
double angleOftarget = 0;
double angleOfcamera = 0;
double minimumTrackingDistance;
double maximumTrackingDistance;
double minimumVewingAngle;
double maximumVewingAngle;
double curentRotation = 0;

SS_Vision vision;

  public C_ShieldGeneratorTracking( double ch, double th, double angleOfcamera, double minimumTrackingDistance,double maximumTrackingDistance,double minimumVewingAngle,double maximumVewingAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    vision = new SS_Vision();
    


    this.ch = ch;
    this.th = th;
    this.angleOfcamera = angleOfcamera;
    this.minimumTrackingDistance = minimumTrackingDistance;
    this.maximumTrackingDistance = maximumTrackingDistance;
    this.minimumVewingAngle = minimumVewingAngle;
    this.maximumVewingAngle = maximumVewingAngle;
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    final double distance = vision.GetTargetDistance(26.5f, 0, 58.5f);
    SmartDashboard.putNumber("Camera ty:", vision.getY());
    SmartDashboard.putNumber("Distacnec: ", distance);
    vision.updateTelemetry();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  //looks at the target and gets the angle
  public double LookAtTargetAngle(){


    double angle = 0;
    double fealedAngle = 0; // this would bee replaced with the Fialed oreatation when we get the gyro subsystem working
    angle = Math.abs(fealedAngle - curentRotation);


    return angle;
  }


  //gets the Estimated Distance between your target and the camera
  public double EstimateDistance(){
    
      double distance = 0;
      angleOftarget = vision.getY();
      distance = (th - ch) / Math.tan((angleOfcamera + angleOftarget) * Math.PI/180.0);
  
      return distance;

    
  }
  public boolean IsOutOfRangeDistance(){

    if(EstimateDistance() > maximumTrackingDistance || EstimateDistance() < minimumTrackingDistance){
      SmartDashboard.getBoolean("Is in range", true);
      return true;
    }else{
      SmartDashboard.getBoolean("Is in range", false);
      return false;
    }

    
  }

  public boolean IsOutOfRangeRotation(){
    if(curentRotation > maximumVewingAngle || curentRotation < minimumVewingAngle){
      return true;
    }

    return false;
  }
}
