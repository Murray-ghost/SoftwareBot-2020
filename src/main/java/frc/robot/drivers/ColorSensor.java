package frc.robot.drivers;
//edit by Murray-Ghost
//import java.util.HashMap;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensor extends TimedRobot {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

 public void init() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);    
  }

  public void periodic() {
    Color detectedColor = m_colorSensor.getColor();
    String colorString;

    /*
    ColorMatchResult myColor = m_colorMatcher.matchClosestColor(detectedColor);
    HashMap <Color, String> outputColor = new HashMap<Color,String>();
    outputColor.put(kBlueTarget,"Blue");
    outputColor.put(kRedTarget,"Red");
    outputColor.put(kGreenTarget,"Green");
    outputColor.put(kYellowTarget,"yellow")
    colorString = outputColor.get(myColor);*/
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    System.out.println(colorString);

    if(colorString !=null)
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putNumber("Confidence",match.confidence);
  }
}
