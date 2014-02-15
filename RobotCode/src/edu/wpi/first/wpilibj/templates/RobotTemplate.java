/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.RobotDrive; 
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick; 
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Timer;

// used to read and write values into the robot code with out recompiling,
//download available at https://code.google.com/p/json-simple/downloads/list

/*import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
*/



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    DriverStationLCD lcd = DriverStationLCD.getInstance();
    Joystick driveStick = new Joystick(1);
    RobotDrive driveM = new RobotDrive(1, 2); // 
    Joystick assistStick = new Joystick(2);
    //Intake intakeSystem = new Intake(3, assistStick, lcd);
    RobotDrive driveA = new RobotDrive(3,10);
    Victor launchMotor = new Victor(5);
    Talon liftMotor = new Talon(4);
    Timer timeKeeper = new Timer();
    //declare RobotDrive object
    
    double dir;
    double dirM;
    double dirX;
    double dirY;
    double t = timeKeeper.get();
    
    /*public class JsonSimpleExample {
     public void readFile (String[] args) {
 
	JSONParser parser = new JSONParser();
 
	try {
 
		Object obj = parser.parse(new FileReader("c:\\test.json"));
 
		JSONObject jsonObject = (JSONObject) obj;
 
		String name = (String) jsonObject.get("name");
		System.out.println(name);
                lcd.println(Line.kUser6, 1,name );
                
		String age = (String) jsonObject.get("age");
		System.out.println(age);
                lcd.println(Line.kUser6, 1,age );
                
		// loop array
		//JSONArray msg = (JSONArray) jsonObject.get("messages");
		//Iterator<String> iterator = msg.iterator();
		//while (iterator.hasNext()) {
		//	System.out.println(iterator.next());
		//}
 
	} catch (FileNotFoundException e)  {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
        } catch (ParseException e) {
		e.printStackTrace();
	}
 
     }
 
}
    
    public void robotInit() {
        lcd.println(Line.kUser6, 1, "Robot Initialized");    
    }*/

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
       
        
        
        liftMotor.set(-.30);
        Timer.delay(2.50);
        liftMotor.set(0.00);
        driveM.drive(0.35, 0.0);
        Timer.delay(2.00);
        driveM.drive(0.0, 0.0);
        driveA.drive(0.30,0.0);
        Timer.delay(1.00);
        driveA.drive(0.0,0.0);
        driveM.drive(-0.35, 0.0);
        Timer.delay(1.00);
        driveM.drive(0.0, 0.0);
        Timer.delay(3.50);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        lcd.println(Line.kUser1, 1, "Operator Control Enabled");
        lcd.updateLCD();
        
        timeKeeper.start();
           
           dirX = driveStick.getX();
           dirY =driveStick.getY();
           dirM = driveStick.getDirectionRadians();
            
            //if(0<dirM<) 
            driveM.arcadeDrive(driveStick, true); //Enabling Drive with Joystick
            
            if(assistStick.getTrigger())
                driveA.drive(1.00,0.0);
            else
                driveA.arcadeDrive(driveStick, true);
            
            dir = Math.toDegrees(assistStick.getDirectionRadians());
            
            
            lcd.println(Line.kUser2, 2, Double.toString(dir));
            lcd.println(Line.kUser3, 3, Double.toString(dirM));
            lcd.println(Line.kUser4, 4, Double.toString(dirX));
            lcd.println(Line.kUser5, 5, Double.toString(dirY));
            lcd.updateLCD();
            Timer.delay(0.005);
            lcd.clear();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
            timeKeeper.start();
        liftMotor.set(0.25);
        Timer.delay(1.00);
        liftMotor.set(0.00);
    }
    private double getSpeedByJoystick(double direction) {
        Math.abs(direction);
        double temp_dir = 90.0 - direction;
        if(temp_dir > 0)
            return 0.35;
        else
            return -0.35;
    }
}
