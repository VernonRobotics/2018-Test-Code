package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
/*
 * public class AutoCommands{}
 * 
 * This class includes commands to be used in autonomous.
 * All commands use actionState in order to keep track of what step they are on.
 * All commands must manipulate actionFlag in order for the autonomouse routines to function
 * 
 * @author Team 1989 Programmers
 * @version 1.0
 * @since February 20th, 2018
 * 
 */
public class AutoCommands {
	static int actionState = 0;// to be used to differentiate between different states in an individual command
	static double integral = 0;
	static double error = 0;
	static double currentAngle = 0;
	
	public static boolean actionFlag = false;
	int autoState = 0;// to be used to differentiate between different commands in a preset auto
	static Timer rampTime = new Timer();
	public static double switchTime = 0.5;
	public static double scaleTime = 2.5;

	/*
	 * boxOutputSwitch()
	 * 
	 * Method is currently here for compliance within the autonomous routines.
	 * When complete, method will output the power cube to the switch game piece
	 * 
	 */
	
	public static void towerMove(double liftTime) {
		switch (actionState) {
		case 0:
			actionFlag = true;
			Components.timer.stop();
			Components.timer.reset();
			Components.timer.start();
			actionState = 1;
			break;
		case 1:
			if (Components.timer.get() < liftTime) {
				Components.tower.towerControl(0.5);
			} else {
				actionState = 2;
			}
			break;
		case 2:
			Components.tower.towerStop();
			Components.timer.stop();
			Components.timer.reset();
			actionState = 0;
			actionFlag = false;
			break;
		}
	}
	
	public static void boxOutput() {
		switch (actionState) {
		case 0: 
			actionFlag = true;
			Components.timer.stop();
			Components.timer.reset();
			Components.timer.start();
			actionState = 1;
			break;
		case 1: 
			if (Components.timer.get() < 1) {
				Components.arms.boxOut();
			} else {
				actionState = 2;
			}
			break;
		case 2:
			Components.tower.towerStop();
			Components.timer.stop();
			Components.timer.reset();
			actionState = 0;
			actionFlag = false;
			break; 
			
		}
		
	}
	
	/*
	 * autoCartesianTime(double time, double speedX, double speedY) {
	 * 
	 * Autonomous method which uses time and speed to navigate during autonomous.
	 * 
	 * @param time The time to run the robot in a specific direction.
	 * @param speedX The speed at which the robot should move in the x direction.
	 * @param speedY The speed at which the robot should move in the y direction.
	 */
	public static void autoCartesianTime(double time, double speedX, double speedY) {
		switch (actionState) {
		case 0:
			actionFlag = true;
			Components.timer.stop();
			Components.timer.reset();
			Components.timer.start();
			actionState = 1;
			break;
		case 1:
			double realTime = Components.timer.get();	
			if (realTime < time) {
				Components.driveStick.setpY(speedY);
				Components.driveStick.setpX(speedX);
				integral += Components.gyro.getAngle() * 0.02;
				Components.driveStick.setpTwist(
						(-Components.gyro.getAngle() + currentAngle) * Components.mDrive.kP + integral * Components.mDrive.kI);
			} else {
				actionState = 2;
			}
			break;
		case 2:
			Components.driveStick.killVStick();
			Components.timer.stop();
			Components.timer.reset();
			actionState = 0;
			actionFlag = false;
			integral = 0;
			break;
		}

	}

	/*
	 * autoCartesiaRange(double inches, double speedX,double speedY, Ultrasonic rf)
	 * 
	 * This method is used to move in one direction (x or y) a set distance based on
	 * input from one of three rangefinders. Rangefinder used should determine which
	 * of the directions you are moving. Variable actionFlag is used by autonomous
	 * routines. Variable actionState is used to maintain which step method is
	 * currently executing
	 * 
	 * @param inches The amount of distance you wish to travel
	 * 
	 * @param speedX The speed you want to move in the x direction
	 * 
	 * @param speedY The speed you want to move in the y direction
	 * 
	 * @param rf The rangefinder you wish to use to determine your distance from the
	 * start location
	 */
	public static void autoCartesianRange(double inches, double speedX, double speedY, Ultrasonic rf) {

		// Begin autonomous
		switch (actionState) {

		case 0:
			actionFlag = true;

			// automatic mode should allow rangefinder to pull values without conflicting
			// with each other.
			actionState++;
			break;
		case 1:
			// If you have not yet gone as far as you want, move.
			if (rf.getRangeInches() < inches || rf.getRangeInches() > 600  ) {
				Components.driveStick.setpY(speedY);
				Components.driveStick.setpX(speedX);
				integral += Components.gyro.getAngle() * 0.02;
				Components.driveStick.setpTwist(
						(-Components.gyro.getAngle() + currentAngle) * Components.mDrive.kP + integral * Components.mDrive.kI);
			} else {
				actionState++;
			}
			break;
		case 2:
			Components.driveStick.setpY(0);
			Components.driveStick.setpX(0);

			actionState = 0;
			actionFlag = false;
			integral = 0;
			break;
		}
	}

	/*
	 * turnToAngle(double angle)
	 * 
	 * This method makes use of a gyro to turn the robot to a specific angle.
	 * 
	 * @param angle The angle to turn the robot during autonomous.
	 */
	public static void turnToAngle(double angle) {
		switch (actionState) {
		case 0:
			actionFlag = true;
			actionState = 1;
			break;
		case 1:
			if (Math.abs(Components.gyro.getAngle()) < Math.abs(angle)) {
				error = angle - Components.gyro.getAngle();
				integral += error * 0.02;
				Components.driveStick.setpTwist(error * Components.mDrive.kP + integral * Components.mDrive.kI);
			} else {
				actionState = 2;
			}
			break;
		case 2:
			error = 0;
			integral = 0;
			currentAngle = angle;
			Components.driveStick.killVStick();
			actionState = 0;
			actionFlag = false;
			break;
		}
	}

	
	
	public static void delay(double time) {
		switch (actionState) {
		case 0:
			Components.timer.stop();
			Components.timer.reset();
			Components.timer.start();
			actionState++;
			break;
		case 1:
			if (Components.timer.get() < time) {
				actionState++;
			}
			break;
		case 2:
			Components.timer.stop();
			Components.timer.reset();
			actionState=0;
			actionFlag = false;
			break;
		}
	}
	
	

	/*
	 * double rampSpeedTime(double time, double speed)
	 * 
	 * @param time Amount of time to ramp.
	 * @param speed How fast you want to move at the end of the ramp.
	 * 
	 */
	
	public static double rampSpeedTime(double time, double speed) {
		
		return speed;
	}

}
