/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1989.robot;

//Front Left:6
//Front Right:3
//Back Left:7
//Back Right: 9


import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
 public class Robot extends IterativeRobot {
	Double angle;
	Double inches;
	writemessage write= new writemessage();
	NewMecDriveCmd mDrive = new NewMecDriveCmd(Components.frontLeft,Components.backLeft,Components.frontRight,Components.backRight,Components.driveStick,Components.gyro);
//	ArmControl arms = new ArmControl(Components.armsLeft,Components.armsRight,Components.uStick);
	//TowerControl tower = new TowerControl(Components.towerLeft, Components.towerRight, Components.uStick);
	//AutoCommands auto = new AutoCommands(Components.driveStick,Components.uStick,mDrive,arms,tower,Components.gyro,
	//		Components.timer);
	// Used for vertical Motion method
	boolean motionActive; 
	double startDistance;
	boolean actionFlag = false;
	double integral = 0;
	double error = 0;
	int autoState = 0;
	//CameraControl cam = new CameraControl()
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Components.frontLeft.setInverted(true);
		Components.frontRight.setInverted(true);
		Components.backLeft.setInverted(true);
		Components.armsRight.setInverted(true);
		
		
		SharedStuff.cmdlist.add(mDrive);
	//	SharedStuff.cmdlist.add(arms);
		SharedStuff.cmdlist.add(write);
		//r1.setAutomaticMode(true);
	}
	
	

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
		Components.timer.stop();
		Components.timer.reset();
		Components.timer.start();
		
		
	}

	
	
	/*Autonomous Methods
	 * all of these methods are used by checking an action which is turned on by the different functions
	 * when the function starts the flag is checked which doesn't uncheck until the end of the function
	
	*/
	int state = 0;
	
	
	
	
	// test here
	
	
	
	
	
	
	/**
	 * This function is called periodically during autonomous.
	 */
	
	@Override
	public void autonomousPeriodic() {
	
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).autonomousPeriodic();
		}
		/*if (autoState == 0) {
			if(actionFlag == true) {
			//	autoCartesianTime(2.5,0,.5);
			}else {
				autoState =1;
				timer.stop();
				timer.reset();
				timer.start();
			}
			
		}else if(autoState == 1) {
			if (timer.get()>1) {
				timer.stop();
				timer.reset();
				autoState = 2;
				actionFlag = true;
			}
		} else if(autoState == 2) {
			if(actionFlag ==true) {
				//turnToAngle(90);
			}else {
				
			}
		}
		
		
		*/
	}
		
		
	

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).teleopPeriodic();
		}
		
		angle = Components.gyro.getAngle();
		//inches = r1.getRangeInches();
		
		write.setmessage(0,angle.toString());
	//	write.setmessage(1, inches.toString());
		write.updatedash();
		
		if(Components.driveStick.getRawButton(7)) {
			Components.towerLeft.set(0.8);
		} else {
			Components.towerLeft.set(0);
		}
		
		if(Components.driveStick.getRawButton(8)) {
			Components.towerRight.set(0.8);
		} else {
			Components.towerRight.set(0);
		}
		
		if(Components.driveStick.getRawButton(1)) {
			Components.gyro.reset();
		}
		
		
	}

	/*
	 * Disable all RangeFinders
	 */

	
	/*
	 * Disable all Drive Motors
	 */
	public void killDriveMotors() {
		Components.frontLeft.set(0);
		Components.backLeft.set(0);
		Components.frontRight.set(0);
		Components.backRight.set(0);
	}
	/*
	 * verticalMotionActive defined at top.
	 */
	
	
	
	
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
