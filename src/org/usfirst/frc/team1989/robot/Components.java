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
	
	static writemessage write= new writemessage();
	
	
	static Timer timer = new Timer();
	public static Ultrasonic r1 = new Ultrasonic(9,8);
	public static Ultrasonic r2 = new Ultrasonic(7,6);
	public static Ultrasonic r3 = new Ultrasonic(5,4);
	
	static NewMecDriveCmd mDrive = new NewMecDriveCmd(Components.frontLeft,Components.backLeft,Components.frontRight,Components.backRight,Components.driveStick,Components.gyro);
	static ArmControl arms = new ArmControl(Components.armsLeft,Components.armsRight,Components.uStick);
	static TowerControl tower = new TowerControl(Components.towerLeft, Components.towerRight, Components.uStick);
	static CameraControl cam = new CameraControl(servo, uStick);
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
	
public void motorOutputTest() {
		
		Double fl = Components.frontLeft.getOutputCurrent();
		Double fr = Components.frontRight.getOutputCurrent();
		Double bl = Components.backLeft.getOutputCurrent();
		Double br = Components.backRight.getOutputCurrent();
		
		Double flv = Components.frontLeft.getMotorOutputVoltage();
		Double frv = Components.frontRight.getMotorOutputVoltage();
		Double blv = Components.backLeft.getMotorOutputVoltage();
		Double brv = Components.backRight.getMotorOutputVoltage();

		if (Components.timer.get() < 10) {
			Components.driveStick.setpY(1);
		}else {
			Components.driveStick.setpY(0);
		}
		
		
		write.setmessage(0,fl.toString() );
		write.setmessage(1,fr.toString() );
		write.setmessage(2,bl.toString() );
		write.setmessage(3,br.toString() );
		write.setmessage(5,flv.toString() );
		write.setmessage(6,frv.toString() );
		write.setmessage(7,blv.toString() );
		write.setmessage(8,brv.toString() );
		
		
		if(Components.timer.get() % 0.25 == 0) {
			write.updatedash();
		}
		
	}
	
}
