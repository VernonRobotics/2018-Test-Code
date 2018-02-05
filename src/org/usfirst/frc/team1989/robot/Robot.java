/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1989.robot;

//Front Left:8 
//Front Right:7
//Back Left:3
//Back Right: 5


import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Ultrasonic;
 public class Robot extends IterativeRobot {

	CANTalon1989 frontLeft = new CANTalon1989(8);
	CANTalon1989 backLeft = new CANTalon1989(7);
	CANTalon1989 frontRight = new CANTalon1989(3);
	CANTalon1989 backRight = new CANTalon1989(5);
	Servo servo = new Servo(0);
	JsScaled driveStick = new JsScaled(0);
	JsScaled uStick = new JsScaled(1);
	NewMecDriveCmd mDrive = new NewMecDriveCmd(frontLeft,backLeft,frontRight,backRight,driveStick);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	Ultrasonic r1 = new Ultrasonic(0,1);
	Ultrasonic r2 = new Ultrasonic(0,2);
	Ultrasonic r3 = new Ultrasonic(0,3);
	Double angle;
	Double inches;
	writemessage write= new writemessage();
	
	// Used for vertical Motion method
	boolean motionActive;
	double startDistance;
	
	//CameraControl cam = new CameraControl()
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		frontLeft.setInverted(true);
		frontRight.setInverted(true);
		backLeft.setInverted(true);
		
		SharedStuff.cmdlist.add(mDrive);
		SharedStuff.cmdlist.add(write);
		r1.setAutomaticMode(true);
	}
	
	//Ben Ben is a dumb dumb

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
	
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
	
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).autonomousPeriodic();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).teleopPeriodic();
		}
		
		angle = gyro.getAngle();
		inches = r1.getRangeInches();
		
		write.setmessage(0,angle.toString());
		write.setmessage(1, inches.toString());
		write.updatedash();
	}

	/*
	 * Disable all RangeFinders
	 */
	public void killRangeFinders() {
		r1.setEnabled(false);
		r2.setEnabled(false);
		r3.setEnabled(false);
	}
	
	/*
	 * Disable all Drive Motors
	 */
	public void killDriveMotors() {
		frontLeft.set(0);
		backLeft.set(0);
		frontRight.set(0);
		backRight.set(0);
	}
	/*
	 * verticalMotionActive defined at top.
	 */
	public void moveVertical(double distance, double speed, Ultrasonic rangeFinder) {
		killRangeFinders();
		rangeFinder.setEnabled(true);
		
		if(motionActive == false) {
			startDistance = rangeFinder.getRangeInches();
			motionActive = true;
		}
		
		double currentDistance = rangeFinder.getRangeInches();
		if(currentDistance - startDistance < distance) {
			frontLeft.set(-speed);
			backLeft.set(-speed);
			frontRight.set(speed);
			backRight.set(speed);
		} else {
			killDriveMotors();
			rangeFinder.setEnabled(false);
			motionActive = false;
		}
	}
	
	public void moveHorizontal(double distance, double speed, Ultrasonic rangeFinder) {
		killRangeFinders();
		rangeFinder.setEnabled(true);
		
		if(motionActive == false) {
			startDistance = rangeFinder.getRangeInches();
			motionActive = true;
		}
		
		double currentDistance = rangeFinder.getRangeInches();
		if(currentDistance - startDistance < distance) {
			frontLeft.set(-speed);
			backLeft.set(speed);
			frontRight.set(speed);
			backRight.set(-speed);
		} else {
			killDriveMotors();
			rangeFinder.setEnabled(false);
			motionActive = false;
		}
	}
	
	public void turnAround(double angle) {
		
	}
	
	public void testInit() {
		motionActive = false;
		startDistance = 0;
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).teleopPeriodic();
		}
		
		
		
		
		
		
		
		
		
	}
}
