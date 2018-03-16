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
	@Override
	public void robotInit() {
		Components.frontLeft.setInverted(true);
		Components.frontRight.setInverted(true);
		Components.backLeft.setInverted(true);
		Components.armsRight.setInverted(true);
		Components.towerLeft.setNeutralMode(NeutralMode.Brake);
		Components.towerRight.setNeutralMode(NeutralMode.Brake);
		CameraServer.getInstance().startAutomaticCapture();
		
		SharedStuff.cmdlist.add(Components.mDrive);
		SharedStuff.cmdlist.add(Components.arms);
		SharedStuff.cmdlist.add(Components.tower);
		SharedStuff.cmdlist.add(Components.write);
	//	SharedStuff.cmdlist.add(Components.cam);
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
	@Override
	public void autonomousInit() {
		Components.timer.stop();
		Components.timer.reset();
		Components.timer.start();
		AutoCommands.actionState = 0;
		AutoCommands.actionFlag = false;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		autoState = 0;

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
		
		/*if(gameData.charAt(0) == 'L') {
			StartCenterSwitchLeft.run(0.5);
		} else if (gameData.charAt(0) == 'R') {
			StartCenterSwitchRight.run(0.5);
		}*/
		StartLeftScaleLeft.run(0.5);
		//DriveForward.run();
			
			
			
			
			
		
	
			
		
		
	
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
		/*Components.timer.start();*/
		
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
			SharedStuff.cmdlist.get(i).testPeriodic();
		}
		//AutoCommands.autoCartesianRange(60, 0, 0.5, Components.r1);
		
		

	}
}	/*


if(startState == 1) {
	if(gameData.length() > 0) {
	
		if (SmartDashboard.getBoolean("DB/Button 0", true)) {
					if (SmartDashboard.getBoolean("DB/Button 3", true)) {
						if (gameData.charAt(1) == 'L') {
							// StartLeftScaleLeft.run();
						} else {
							// StartLeftScaleRight.run();
						}
					} else {
						if (gameData.charAt(0) == 'L') {
							StartLeftSwitchLeft.run();
						} else {
							// StartLeftSwitchRight.run();
						}
					}
		
				} else if (SmartDashboard.getBoolean("DB/Button 1", true)) {
					if (SmartDashboard.getBoolean("DB/Button 3", true)) {
						if (gameData.charAt(1) == 'L') {
							// StartCenterScaleLeft.run();
						} else {
						// StartCenterScaleRight.run();
						}
					} else {
						if (gameData.charAt(0) == 'L') {
							// StartCenterSwitchLeft.run();
						} else {
							// StartCenterSwitchRight.run();
						}
					}
				} else if (SmartDashboard.getBoolean("DB/Button 2", true)) {
					if (SmartDashboard.getBoolean("DB/Button 3", true)) {
						if (gameData.charAt(1) == 'L') {
							// StartRightScaleLeft.run();
						} else {
							// StartRightScaleRight.run();
						}
					} else {
					if (gameData.charAt(0) == 'L') {
							// StartRightSwitchLeft.run();
					} else {
							// StartRightSwitchRight.run();
						}
					}
				} 
				else {
					 //StartCenterMoveForward.run();
		 		}
		}		
	} */
