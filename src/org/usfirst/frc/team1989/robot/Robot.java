/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1989.robot;

import org.usfirst.frc.team1989.robot.AutoRoutines.StartLeftSwitchLeft;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DriverStation;

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

	// ArmControl arms = new
	// ArmControl(Components.armsLeft,Components.armsRight,Components.uStick);
	// TowerControl tower = new TowerControl(Components.towerLeft,
	// Components.towerRight, Components.uStick);
	// AutoCommands auto = new
	// AutoCommands(Components.driveStick,Components.uStick,mDrive,arms,tower,Components.gyro,
	// Components.timer);
	// Used for vertical Motion method
	boolean motionActive;
	double startDistance;
	boolean actionFlag = false;
	double integral = 0;
	double error = 0;
	int autoState = 0;
	String gameData;
	
	// CameraControl cam = new CameraControl()
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
		
		Components.towerLeft.setNeutralMode(NeutralMode.Coast);
		Components.towerRight.setNeutralMode(NeutralMode.Coast);
		SharedStuff.cmdlist.add(Components.mDrive);
		// SharedStuff.cmdlist.add(arms);
		SharedStuff.cmdlist.add(Components.write);
		// r1.setAutomaticMode(true);
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
		gameData = DriverStation.getInstance().getGameSpecificMessage();

	}

	/*
	 * Autonomous Methods all of these methods are used by checking an action which
	 * is turned on by the different functions when the function starts the flag is
	 * checked which doesn't uncheck until the end of the function
	 * 
	 */
	int state = 0;

	/**
	 * This function is called periodically during autonomous.
	 */

	@Override
	public void autonomousPeriodic() {

		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).autonomousPeriodic();
		}
		
		
		StartLeftSwitchLeft.run();
		
		/*
		 * if (autoState == 0) { if(actionFlag == true) { //
		 * autoCartesianTime(2.5,0,.5); }else { autoState =1; timer.stop();
		 * timer.reset(); timer.start(); }
		 * 
		 * }else if(autoState == 1) { if (timer.get()>1) { timer.stop(); timer.reset();
		 * autoState = 2; actionFlag = true; } } else if(autoState == 2) { if(actionFlag
		 * ==true) { //turnToAngle(90); }else {
		 * 
		 * } }
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
		Components.frontLeft.setNeutralMode(NeutralMode.Coast);
		Components.frontRight.setNeutralMode(NeutralMode.Coast);
		Components.backLeft.setNeutralMode(NeutralMode.Coast);
		Components.backRight.setNeutralMode(NeutralMode.Coast);
		Components.towerLeft.setNeutralMode(NeutralMode.Coast);
		Components.towerRight.setNeutralMode(NeutralMode.Coast);

		
		
		
		Components.r1.setAutomaticMode(true);
	}
	
	
	@Override
	public void teleopPeriodic() {
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).teleopPeriodic();
		}

		angle = Components.gyro.getAngle();
		inches1 = Components.r1.getRangeInches();
		inches2 = Components.r2.getRangeInches();
		inches3 = Components.r3.getRangeInches();
	
		
		Components.write.setmessage(0, inches1.toString());
		Components.write.setmessage(1, inches2.toString());
		Components.write.setmessage(2, inches3.toString());
		Components.write.setmessage(5, angle.toString());
		Components.write.updatedash();


/*
		Components.write.setmessage(0, angle.toString());
		// write.setmessage(1, inches.toString());
		Components.write.updatedash();
*/
		if (Components.driveStick.getRawButton(7)) {
			Components.towerLeft.set(0.5);
			Components.towerRight.set(0.5);
		} else {
			Components.towerLeft.set(0);
			Components.towerRight.set(0);
		}

		if (Components.driveStick.getRawButton(8)) {
			Components.towerLeft.set(-1);
			Components.towerRight.set(-1);
		} else {
			Components.towerLeft.set(0);
			Components.towerRight.set(0);
		}
		if (Components.driveStick.getRawButton(9)) {
			Components.towerLeft.set(-0.3);
			Components.towerRight.set(-0.3);
		} else {
			Components.towerLeft.set(0);
			Components.towerRight.set(0);
		}

		if (Components.driveStick.getRawButton(1)) {
			Components.gyro.reset();
		}
	}
		
		
		
	

	/*
	 * Disable all RangeFinders
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
		//StartLeftSwitchLeft.run();
		//System.out.println("test");
		/*
		 * Button 0: Left Button 1: Center Button 2: Right Button 3: True = Scale, False
		 * = Switch
		 * 
		 * None of the above = drive straight
		 */
		if (SmartDashboard.getBoolean("DB/Button 0", true)) {
			if (SmartDashboard.getBoolean("DB/Button 3", true)) {
				if (gameData.charAt(1) == 'L') {
					// StartLeftScaleLeft.run();
				} else {
					// StartLeftScaleRight.run();
				}
			} else {
				if (gameData.charAt(0) == 'L') {
					//StartLeftSwitchLeft.run();
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
		} else {
			// DriveForward.run(); today
		}
	}
}
