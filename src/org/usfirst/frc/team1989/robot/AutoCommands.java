package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;

public class AutoCommands{
	JsScaled driveStick;
	JsScaled uStick;
	Timer timer;
	ADXRS450_Gyro gyro;
	NewMecDriveCmd mDrive;
	ArmControl arms;
	TowerControl tower;
	
	
	int actionState = 0;//to be used to differentiate between different states in an individual command
	double integral=0;
	double error=0;
	boolean actionFlag=false;
	int autoState=0;//to be used to differentiate between different commands in a preset auto
	
	
	
	public AutoCommands(JsScaled driveStick, JsScaled uStick, NewMecDriveCmd mDrive, ArmControl arms,
			TowerControl tower,  ADXRS450_Gyro gyro, Timer timer) {
		this.driveStick = driveStick;
		this.uStick =  uStick;
		this.mDrive = mDrive;
		this.arms = arms;
		this.tower = tower;
		this.gyro = gyro;
		this.timer = timer;
		
	}
	
	
	
	// test here
		public void autoCartesianTime(double time,double speedX, double speedY ) {
			if(actionState == 0) {
				//actionFlag = true;
				timer.stop();
				timer.reset();
				timer.start();
				actionState = 1;
			}else if(actionState ==1) {
				if(timer.get() < time) {
					driveStick.setpY(speedY);
					driveStick.setpX(speedX);
					integral += gyro.getAngle()*0.02;
					driveStick.setpTwist(-gyro.getAngle()*mDrive.kP+integral*mDrive.kI);
				}else {
					actionState =2;
				} 
			}else if(actionState ==2) {				
				driveStick.killVStick();
				timer.stop();
				timer.reset();
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
						driveStick.setpY(speedY);
						driveStick.setpX(speedX);
						integral += Components.gyro.getAngle()*0.02;
						driveStick.setpTwist(-gyro.getAngle()*mDrive.kP+integral*mDrive.kI);
					}else {
						actionState =2;
					}
				}else if(actionState ==2) {
					driveStick.killVStick();
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
				if (Math.abs(gyro.getAngle()) < Math.abs(angle) ) {
					error = angle - gyro.getAngle();
					integral += error * 0.02;
					driveStick.setpTwist(error*mDrive.kP+integral*mDrive.kI);
				} else {
					actionState = 2;
				}
			} else if(actionState ==2) {
				error = 0;
				integral = 0;
				driveStick.killVStick();
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
					timer.stop();
					timer.reset();
					timer.start();
				}
			} else if(actionState == 2) {
				if(timer.get() < 2) {
					arms.boxOut();
				}else {
					arms.boxStop();
					timer.stop();
					timer.reset();
					actionState = 0;
				}
			}
		}
		
		
		
		
}
