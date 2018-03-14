/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1989.robot;

import org.usfirst.frc.team1989.robot.AutoRoutines.*;
import org.usfirst.frc.team1989.robot.AutoRoutines.StartCenterMoveForward;
import org.usfirst.frc.team1989.robot.AutoRoutines.StartCenterSwitchLeft;
import org.usfirst.frc.team1989.robot.AutoRoutines.StartLeftSwitchLeft;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

//Front Left:6
//Front Right:3
//Back Left:7
//Back Right: 9


import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Double angle;
	Double inches1;
	Double inches2;
	Double inches3;
	Boolean flimit;
	Boolean rlimit;

	
	

	String gameData;
	

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
		Components.towerLeft.setNeutralMode(NeutralMode.Brake);
		Components.towerRight.setNeutralMode(NeutralMode.Brake);
		Components.towerLeft.set(ControlMode.Follower, 5);
		CameraServer.getInstance().startAutomaticCapture();
		
		SharedStuff.cmdlist.add(Components.mDrive);
		SharedStuff.cmdlist.add(Components.arms);
		SharedStuff.cmdlist.add(Components.tower);
		SharedStuff.cmdlist.add(Components.write);
		SharedStuff.cmdlist.add(Components.cam);
		Components.r1.setAutomaticMode(true);
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
		AutoCommands.actionState = 0;
		AutoCommands.actionFlag = false;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		Components.r1.setAutomaticMode(true);

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
	int startState = 0;
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
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if(gameData.charAt(0) == 'L') {
			StartCenterSwitchLeft.run(0.5);
		} else if (gameData.charAt(0) == 'R') {
			StartCenterSwitchRight.run(0.5);
		}
		
		//DriveForward.run();
			
			
			
			
			
		
	
			
		
		
	
	}
		
		
	
	public void teleopInit() {
		Components.towerLeft.setNeutralMode(NeutralMode.Coast);
		Components.towerRight.setNeutralMode(NeutralMode.Coast);
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
		
		// Down
		if(Components.driveStick.getRawButton(12)) {
			Components.towerLeft.set(-0.2);
			Components.towerRight.set(-0.2);
		} else {
			Components.towerLeft.set(0);
			Components.towerRight.set(0);
		}
	
		
		// Up
		if(Components.driveStick.getRawButton(10)) {
			Components.towerLeft.set(0.6);
			Components.towerRight.set(0.6);
		} else {
			Components.towerLeft.set(0);
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
