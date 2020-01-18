/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Code from limelight example: http://docs.limelightvision.io/en/latest/getting_started.html#basic-programming

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SS_Vision extends SubsystemBase {

  //led mode constants
  public static final int LED_PIPELINE = 0; //use the LED Mode set in the current pipeline
  public static final int LED_OFF = 1; //force off
  public static final int LED_BLINK = 2; //force blink
  public static final int LED_ON = 3; //force on
  public static final int LED_DEFAULT_MODE = LED_ON;

  //camera mode constants
  public static final int CAMERA_VISION = 0; //Vision processor
  public static final int CAMERA_DRIVE = 1; //Driver Camera (Increases exposure, disables vision processing)
  public static final int CAMERA_DEFAULT_MODE = CAMERA_VISION;

  //piplines (can be a numbers from 0-9)
  public static final int TRACKING_PIPELINE = 0;
  public static final int DEFAULT_PIPELINE = TRACKING_PIPELINE;

  //Distance constants
  public static final double CAMERA_ANGLE = 45;
  public static final double CAMERA_HEIGHT = 24; //in inches
  public static final double TARGET_HEIGHT = 200; //in inches

  //vision network table
  private NetworkTable visionTable;
  private NetworkTableEntry tx; //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
  private NetworkTableEntry ty; //Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
  private NetworkTableEntry tv; //Whether the limelight has any valid targets (0 or 1)
  private NetworkTableEntry ta; //Target Area (0% of image to 100% of image)
  private NetworkTableEntry ts; //Skew or rotation (-90 degrees to 0 degrees)
  
  public SS_Vision() {

    visionTable = NetworkTableInstance.getDefault().getTable("limelight-testing");
    tx = visionTable.getEntry("tx");
    ty = visionTable.getEntry("ty");
    tv = visionTable.getEntry("tv");
    ta = visionTable.getEntry("ta");
    ts = visionTable.getEntry("ts");
    setMode(CAMERA_DEFAULT_MODE, LED_DEFAULT_MODE, DEFAULT_PIPELINE);
  }

  @Override
  public void periodic() {}

  public void updateTelemetry() {

    //read values 
    final double x = tx.getDouble(-1);
    final double y = ty.getDouble(-1);
    final double area = ta.getDouble(-1);
    final boolean target = tv.getDouble(-1) == 1;
    final double skew = ts.getDouble(-1);

    //post to smart dashboard
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putBoolean("Valid Target", target);
    SmartDashboard.putNumber("Skew", skew);
  }

  public void setMode(int cameraMode, int ledMode, int pipeline) { 

    if(cameraMode > -1 && cameraMode < 2) {
      visionTable.getEntry("camMode").setNumber(cameraMode);
    }
    if(ledMode > -1 && ledMode < 4) {
      visionTable.getEntry("ledMode").setNumber(ledMode);
    }
    if(pipeline > -1 && pipeline < 10) {
      visionTable.getEntry("pipeline").setNumber(pipeline);
    }
  }

  //returns horizontal offset from Crosshair to target (-27 degrees to 27 degrees)
  public double getXOffset() {
    return tx.getDouble(-1);
  }

  //vertical offset from crosshair to target (-20.5 degrees to 20.5 degrees)
  public double getYOffset() {
    return ty.getDouble(-1);
  }

  //returns the rotation (-90 degrees to 0 degrees)
  public double getSkew()//{
  {
    return ts.getDouble(-1);
  }

  //get an estimated distance to the target
  public double getDistance() {

      double distance = 0;
      double angleOftarget = getYOffset();
      distance = (TARGET_HEIGHT - CAMERA_HEIGHT) / Math.tan((CAMERA_ANGLE + angleOftarget) * Math.PI/180.0);
      return distance;
  }
}
