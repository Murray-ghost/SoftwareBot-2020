/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SS_Vision;
import frc.robot.util.PIDController;
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
private final double MAX_TARGET_RANGE = 200;

SS_Vision vision;
PIDController pid;

  public C_ShieldGeneratorTracking( double ch, double th, double angleOfcamera, double minimumTrackingDistance,double maximumTrackingDistance,double minimumVewingAngle,double maximumVewingAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    vision = new SS_Vision(26.5f, 0, 59f);   
    pid = new PIDController(0.5,0,0,0);
    


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

    final double distance = vision.GetTargetDistance();
    SmartDashboard.putBoolean("Is in Range:", IsOutOfRangeDistance());
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
  
  // This is a bool that sees if you are out of range of the target
  public boolean IsOutOfRangeDistance(){
    if(MAX_TARGET_RANGE > vision.GetTargetDistance()){
      return true;
    }else{
      return false;
    }
    
  }


}
