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

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

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
	int autoState;

	String gameData;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	int autoChoice = 0;
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
		double buttonPick = SmartDashboard.getNumber("DB/Slider 1", 0);
		if (buttonPick >= 1) {
			autoChoice=1;}
		// SharedStuff.cmdlist.add(Components.cam);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	

	public void autoChoice() {
		if (SmartDashboard.getBoolean("DB/Button 0", false)) {
			autoChoice += 1;
		}
		if (SmartDashboard.getBoolean("DB/Button 1", false)) {
			autoChoice += 2;
		}
		if (SmartDashboard.getBoolean("DB/Button 2", false)) {
			autoChoice += 4;
		}
		if (SmartDashboard.getBoolean("DB/Button 4", false)) {
			autoChoice += 8;
		}

	}

	@Override
	public void autonomousInit() {
		Components.timer.stop();
		Components.timer.reset();
		Components.timer.start();
		AutoCommands.actionState = 0;
		AutoCommands.actionFlag = false;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		autoState = 0;
		//autoChoice();
		double button = SmartDashboard.getNumber("DB/Slider 1", 0);
		if (button >= 1) {
			autoChoice=1;
			
		}
		// System.out.println("Button is Checked: " + button);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	int startState = 0;

	@Override
	public void autonomousPeriodic() {

		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).autonomousPeriodic();

		}
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if (autoChoice == 0) {
			if (gameData.charAt(0) == 'L') {
				StartCenterSwitchLeft.run(0.5);
				//System.out.println("test switch left");
			} else if (gameData.charAt(0) == 'R') {
				StartCenterSwitchRight.run(0.5);
				//System.out.println("test switch right");
			}
		} else if (autoChoice == 1) {
			DriveForward.run();
			//System.out.println("test drive forward");
		}
		
		
		
		
		/*
		
		switch (autoChoice) {
		case 0:
			if (gameData.charAt(0) == 'L') {
				StartCenterSwitchLeft.run(0.5);
			} else if (gameData.charAt(0) == 'R') {
				StartCenterSwitchRight.run(0.5);
			}
			break;
		// Robot Starts Left
		case 1:
			if (gameData.charAt(1) == 'L') {
				// StartLeftScaleLeft.run(0.5);
			} else if (gameData.charAt(1) == 'R') {
				// StartLeftScaleRight.run(0.5);
			}
			break;
		// Robot Starts Right
		case 2:
			if (gameData.charAt(1) == 'L') {
				// StartRightScaleLeft.run(0.5);
			} else if (gameData.charAt(1) == 'R') {
				//StartRightScaleRight.run(0.5);
			}
			break;
		// Drive Forward Only
		case 3:
			DriveForward.run();
			break;
		// Likely only used in rare cases below here
		case 4:
			if (gameData.charAt(0) == 'L') {
				// StartLeftSwitchLeft.run(0);
			} else if (gameData.charAt(0) == 'R') {
				DriveForward.run();
			}
			break;
		case 5:
			if (gameData.charAt(0) == 'R') {
				// StartRightSwitchRight.run(0);
			} else if (gameData.charAt(0) == 'L') {
				DriveForward.run();
			}
			break;
		// If all else fails, drive forward
		default:
			DriveForward.run();
		}

		/*
		 * if(SmartDashboard.getString("DB/String 0",
		 * "").equals("StartCenterSwitchLeft")){ System.out.println("working"); }
		 */

		/*
		 * if(gameData.charAt(0) == 'L') { StartCenterSwitchLeft.run(0.5); } else if
		 * (gameData.charAt(0) == 'R') { StartCenterSwitchRight.run(0.5); }
		 * 
		 * 
		 * if(SmartDashboard.getBoolean("DB/Button 0", false)) { if
		 * (SmartDashboard.getBoolean("DB/Button 3", false)) { if(gameData.charAt(1) ==
		 * 'L') { //StartLeftScaleLeft.run(0.5);
		 * System.out.println("StartLeftScaleLeft"); } else if (gameData.charAt(1) ==
		 * 'R') { //StartLeftScaleRight.run(0.5);
		 * System.out.println("StartLeftScaleRight");
		 * 
		 * } } else if(SmartDashboard.getBoolean("DB/Button 3", false)) {
		 * //DriveForward.run(); System.out.println("DriveForward");
		 * 
		 * } }else if (SmartDashboard.getBoolean("DB/Button 1", false)) {
		 * if(gameData.charAt(0) == 'L') { //StartCenterSwitchLeft.run(0.5);
		 * System.out.println("StartCenterSwitchLeft");
		 * 
		 * } else if (gameData.charAt(0) == 'R') { //StartCenterSwitchRight.run(0.5);
		 * System.out.println("StartCenterSwitchRight");
		 * 
		 * } } else if(SmartDashboard.getBoolean("DB/Button 2", false)) { if
		 * (SmartDashboard.getBoolean("DB/Button 3", false)) { if(gameData.charAt(1) ==
		 * 'L') { //StartRightScaleLeft.run(0.5);
		 * System.out.println("StartRightScaleLeft");
		 * 
		 * } else if (gameData.charAt(1) == 'R') { //StartRightScaleRight.run(0.5);
		 * System.out.println("StartRightScaleRight"); } } else
		 * if(SmartDashboard.getBoolean("DB/Button 3", false)) { //DriveForward.run();
		 * System.out.println("DriveForward"); } }
		 * 
		 * 
		 * 
		 */

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopInit() {
		Components.frontLeft.setNeutralMode(NeutralMode.Brake);
		Components.frontRight.setNeutralMode(NeutralMode.Brake);
		Components.backLeft.setNeutralMode(NeutralMode.Brake);
		Components.backRight.setNeutralMode(NeutralMode.Brake);
		Components.towerLeft.setNeutralMode(NeutralMode.Brake);
		Components.towerRight.setNeutralMode(NeutralMode.Brake);
		CameraServer.getInstance().startAutomaticCapture();

	}

	@Override
	public void teleopPeriodic() {
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).teleopPeriodic();
		}

		angle = Components.gyro.getAngle();

		rlimit = Components.towerRight.getSensorCollection().isRevLimitSwitchClosed();
		flimit = Components.towerRight.getSensorCollection().isFwdLimitSwitchClosed();
		Components.write.setmessage(0, angle.toString());
		Components.write.updatedash();

	}

	/*
	 * Disable all RangeFinders
	 */

	public void testInit() {
		Components.timer.stop();
		Components.timer.reset();
		/* Components.timer.start(); */

		Components.timer.stop();
		Components.timer.reset();
		AutoCommands.actionState = 0;
		AutoCommands.actionFlag = false;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		StartCenterMoveForward.run(1, true);
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).teleopPeriodic();
		}
		// AutoCommands.autoCartesianRange(60, 0, 0.5, Components.r1);

	}
}
