/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Developer
 */
public class Intake {
    
    DriverStationLCD lcd;
    Talon intakeMotor;
    Joystick jstick;
    double dir;
    
    public Intake(int motorPort, Joystick jstick, DriverStationLCD lcd) {
        intakeMotor = new Talon(3);
    }
    
    public void update() {
        dir = Math.toDegrees(jstick.getDirectionRadians());
        lcd.println(Line.kUser2, 2, Double.toString(dir));
        lcd.updateLCD();
        
    }
    
    public void set(double rate) {
        intakeMotor.set(rate);
    }
    
}