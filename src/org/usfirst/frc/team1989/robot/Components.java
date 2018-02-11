package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;

public final class Components {
	static CANTalon1989 frontLeft = new CANTalon1989(6);
	static CANTalon1989 backLeft = new CANTalon1989(7);
	static CANTalon1989 frontRight = new CANTalon1989(3);
	static CANTalon1989 backRight = new CANTalon1989(9);
	static CANTalon1989 armsLeft = new CANTalon1989(2);
	static CANTalon1989 armsRight = new CANTalon1989(1);
	static CANTalon1989 towerLeft = new CANTalon1989(4);
	static CANTalon1989 towerRight = new CANTalon1989(5);
	static Servo servo = new Servo(0);
	static JsScaled driveStick = new JsScaled(0);
	static JsScaled uStick = new JsScaled(1);
	static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	//static DigitalInput switchSwitch = new DigitalInput(0);
	//static DigitalInput lowSwitch = new DigitalInput(1);
	static Timer timer = new Timer();
	//static Ultrasonic r1 = new Ultrasonic(0,1);
	//static Ultrasonic r2 = new Ultrasonic(0,2);
	//static Ultrasonic r3 = new Ultrasonic(0,3);
	
	
	/*
	 * Disable all Drive Motors
	 */
	public static void killDriveMotors() {
		frontLeft.set(0);
		backLeft.set(0);
		frontRight.set(0);
		backRight.set(0);
	}
	
	/*
	 * Disable all rangefinders.
	 */
	
	public static void killRangeFinders() {
		//Components.r1.setEnabled(false);
		//Components.r2.setEnabled(false);
		//Components.r3.setEnabled(false);
	}
	
}
