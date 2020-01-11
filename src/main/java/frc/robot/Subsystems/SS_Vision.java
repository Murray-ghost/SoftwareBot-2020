/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Code from limelight example: http://docs.limelightvision.io/en/latest/getting_started.html#basic-programming

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SS_Vision extends SubsystemBase 
{
  private NetworkTable table;
  private NetworkTableEntry tx; //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
  private NetworkTableEntry ty; //Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
  private NetworkTableEntry tv; //Whether the limelight has any valid targets (0 or 1)
  private NetworkTableEntry ta; //Target Area (0% of image to 100% of image)
  
  public SS_Vision() {}

  @Override
  public void periodic() {}

  public void updateTelemetry()
  {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    tv = table.getEntry("tv");
    ta = table.getEntry("ta");

    //read values periodically
    final double x = tx.getDouble(0.0);
    final double y = ty.getDouble(0.0);
    final double area = ta.getDouble(0.0);
    final boolean target = tv.getDouble(0) == 1;

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putBoolean("Valid Target", target);
  }

  public double getX()
  {
    tx = table.getEntry("tx");
    return tx.getDouble(0.0);
  }

  public double getY()
  {
    ty = table.getEntry("ty");
    return tx.getDouble(0.0);
  }
}
