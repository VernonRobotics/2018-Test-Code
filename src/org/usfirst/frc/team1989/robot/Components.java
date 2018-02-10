package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
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
	static Servo servo = new Servo(0);
	static JsScaled driveStick = new JsScaled(0);
	static JsScaled uStick = new JsScaled(1);
	static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	static NewMecDriveCmd mDrive = new NewMecDriveCmd(frontLeft,frontRight,backLeft,backRight,driveStick,gyro, false);
	static Timer timer = new Timer();
	//static Ultrasonic r1 = new Ultrasonic(0,1);
	//static Ultrasonic r2 = new Ultrasonic(0,2);
	//static Ultrasonic r3 = new Ultrasonic(0,3);
	public static void killRangeFinders() {
		//Components.r1.setEnabled(false);
		//Components.r2.setEnabled(false);
		//Components.r3.setEnabled(false);
	}
	
}
