package org.usfirst.frc.team1989.robot;


import edu.wpi.first.wpilibj.Ultrasonic;

public class AutoCommands{
	
	
	
	static int actionState = 0;//to be used to differentiate between different states in an individual command
	static double integral=0;
	double error=0;
	static boolean actionFlag=false;
	int autoState=0;//to be used to differentiate between different commands in a preset auto
	
	TowerControl tower;//to be moved to components after physical completion
	ArmControl arms;//to be moved to components after physical completion
	
	
	
	
	// test here
		public static void autoCartesianTime(double time,double speedX, double speedY ) {
			if(actionState == 0) {
				//actionFlag = true;
				Components.timer.stop();
				Components.timer.reset();
				Components.timer.start();
				actionState = 1;
			}else if(actionState ==1) {
				if(Components.timer.get() < time) {
					Components.driveStick.setpY(speedY);
					Components.driveStick.setpX(speedX);
					integral += Components.gyro.getAngle()*0.02;
					Components.driveStick.setpTwist(-Components.gyro.getAngle()*Components.mDrive.kP+integral*Components.mDrive.kI);
				}else {
					actionState =2;
				} 
			}else if(actionState ==2) {				
				Components.driveStick.killVStick();
				Components.timer.stop();
				Components.timer.reset();
				actionState = 0;
				actionFlag = false;
				integral = 0;
			}
		}
		
		
		//test here
		public void autoCartesianRange(double inches,  double speedX,double speedY, Ultrasonic rf) {
				if (actionState == 0) {
					actionFlag = false;
					Components.killRangeFinders();
					rf.setEnabled(true);
					actionState = 1;
					
				} else if(actionState == 1) {
					if(rf.getRangeInches()< inches) {
						Components.driveStick.setpY(speedY);
						Components.driveStick.setpX(speedX);
						integral += Components.gyro.getAngle()*0.02;
						Components.driveStick.setpTwist(-Components.gyro.getAngle()*Components.mDrive.kP+integral*Components.mDrive.kI);
					}else {
						actionState =2;
					}
				}else if(actionState ==2) {
					Components.driveStick.killVStick();
					Components.killRangeFinders();
					actionState = 0;
					actionFlag = false;
					integral = 0;
				}
		}
		//test here
		public void turnToAngle(double angle) {
			if (actionState == 0 ) {
				//actionFlag = true;
				actionState =1;
			}else if(actionState ==1) {
				if (Math.abs(Components.gyro.getAngle()) < Math.abs(angle) ) {
					error = angle - Components.gyro.getAngle();
					integral += error * 0.02;
					Components.driveStick.setpTwist(error*Components.mDrive.kP+integral*Components.mDrive.kI);
				} else {
					actionState = 2;
				}
			} else if(actionState ==2) {
				error = 0;
				integral = 0;
				Components.driveStick.killVStick();
				actionState = 0;
				actionFlag = false;
			}
		}
		public void boxOutputSwitch() {
			if(actionState == 0) {
				actionState = 1;
				tower.setMoveSwitch(true);
			} else if(actionState == 1) {
				if(tower.getMoveSwitch()) {
					tower.towerPresetControl();
				}else {
					actionState = 2;
					Components.timer.stop();
					Components.timer.reset();
					Components.timer.start();
				}
			} else if(actionState == 2) {
				if(Components.timer.get() < 2) {
					arms.boxOut();
				}else {
					arms.boxStop();
					Components.timer.stop();
					Components.timer.reset();
					actionState = 0;
					actionFlag = false;
				}
			}
		}
		
		public void delay(double time) {
			if(actionState == 0) {
				Components.timer.stop();
				Components.timer.reset();
				Components.timer.start();
				actionState = 1;
			}else if(actionState == 1) {
				if(Components.timer.get() < time) {
					actionState = 2;
				}
			} else if(actionState == 2) {
				Components.timer.stop();
				Components.timer.reset();
				actionState = 0;
				actionFlag = false;
			}
		}
		
		
		
		
}
