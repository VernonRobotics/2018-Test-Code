package org.usfirst.frc.team1989.robot.Deprecated;
import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team1989.robot.CANTalon1989;
import org.usfirst.frc.team1989.robot.JsScaled;
import org.usfirst.frc.team1989.robot.cmd;

import com.ctre.*;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.NeutralMode;
public class OldMecDriveCmd implements cmd {
	
	// Instantiate Basic Components
	CANTalon1989 driveFrontLeft;
	CANTalon1989 driveFrontRight;
	CANTalon1989 driveBackLeft;//encoder motor
	CANTalon1989 driveBackRight;// encoder motor
	MecanumDrive mecDrive;
	JsScaled driveStick;
	int encPulseRatio = 73;
	int breakDistance = 756;
	int remainingDistance;
	double currentPower;
	double minPower = 0.2;
	double speedRamp = 0.05;
	boolean driveFlag = false;
	Timer rampTimer = new Timer();
	
	
	
	// Part of the Encoder Test (needs class scope, or it would go in test init)
	Integer encoderLeftCount = 0;
	Integer encoderRightCount= 0;
	Integer driveStateTemp = 0;
	Integer cycleDelay = 0;
	Integer deltaEncValue;
	Double driveTempAdjustment;
	Double driveTwistAdjustment = 0.0;
	int temp = 0;
	
	public OldMecDriveCmd(CANTalon1989 driveFrontLeft, CANTalon1989 driveBackLeft, CANTalon1989 driveFrontRight, CANTalon1989 driveBackRight, JsScaled driveStick){
		this.driveFrontLeft = driveFrontLeft;
		this.driveBackLeft = driveBackLeft;
		this.driveFrontRight = driveFrontRight;
		this.driveBackRight = driveBackRight;
		mecDrive = new MecanumDrive(this.driveFrontLeft, this.driveBackLeft, this.driveFrontRight, this.driveBackRight);
		this.driveStick = driveStick;
		this.driveFrontRight.setInverted(true);
		this.driveBackRight.setInverted(true);
	}
	
	// Check the values of each encorder and display it to the smart dashboard
	// This is part of the test code for the encoder based drive
	
	
	/*
	public void encoderCheck(){
		// If going straight get encoder values.  Otherwise set them to 0.
		if(Math.abs(driveStick.sgetTwist()) < 0.1 && Math.abs(driveStick.sgetX()) < 0.1 && Math.abs(driveStick.sgetY()) > 0.1){
			encoderLeftCount = driveBackLeft.getEncPosition();
			encoderRightCount = driveBackRight.getEncPosition();
		} 
		if(Math.abs(driveStick.sgetTwist()) > 0.1 || Math.abs(driveStick.sgetX()) > 0.1){
			driveBackLeft.setEncPosition(0);
			driveBackRight.setEncPosition(0);
		}
		// Output data to the dash
		Components.writemessage.setmessage(0, encoderLeftCount.toString());
		Components.writemessage.setmessage(1, encoderRightCount.toString());
	}
	
	// Convert distance to encoder values
	public void driveFoward(int inches, double startPower){//assumes encoder reset to 0, must be driven backwards
		int pulseCount = inches * encPulseRatio;
		remainingDistance = pulseCount - Math.abs(driveBackLeft.getEncPosition());
		if (startPower > 0){
			speedRamp = -speedRamp;
		}
		if (inches == 0){
			
		}
		else{
			if (Math.abs(driveBackLeft.getEncPosition()) <= pulseCount){
				if(remainingDistance < breakDistance ){
					if (minPower < currentPower){	
						rampTimer.start();
						if (rampTimer.get() > 0.2){
							currentPower += speedRamp;
							rampTimer.stop();
							rampTimer.reset();	
						}
						else{
							currentPower = minPower;
						}
					}
				} else{
					currentPower = startPower;
				}
			
			
			
				
				
				
				driveStick.pY = currentPower;
				driveFlag = true;
			} else {
				driveStick.pY = 0.0;
				driveFlag = false;
			}
		}
	}
	
	// Make the robot drive straight
	public void encoderDrive(double jsX, double jsY, double jsTwist){
		if(jsX == 0 && jsTwist == 0 && jsY > 0){
			if(driveStateTemp == 0){
				encoderLeftCount = driveBackLeft.getEncPosition();
				encoderRightCount = driveBackRight.getEncPosition();
				deltaEncValue = encoderRightCount - encoderLeftCount;
					if(deltaEncValue != 0){
						driveStateTemp = 1;
						driveTempAdjustment = (double) (deltaEncValue/200);
					} else {
						driveTrain.mecanumDrive_Cartesian(jsX, jsY, jsTwist + driveTwistAdjustment, 0);
					}
			    } else if (driveStateTemp == 1){
				
				driveTrain.mecanumDrive_Cartesian(jsX, jsY, jsTwist + driveTempAdjustment + driveTwistAdjustment, 0);
				cycleDelay+=1;
				     if (cycleDelay == 2){
					      driveStateTemp = 2;
					      cycleDelay = 0;
				     }				
			    } 
			    else if(driveStateTemp == 2){
				 driveTwistAdjustment+= driveTempAdjustment/2;
				 driveTrain.mecanumDrive_Cartesian(jsX, jsY, jsTwist + driveTwistAdjustment, 0);
				 driveStateTemp = 0;
			     }
			} else{
				driveStateTemp = 0;
				driveTrain.mecanumDrive_Cartesian(jsX, jsY, jsTwist, 0);
				driveBackLeft.setEncPosition(0);
				driveBackRight.setEncPosition(0);
		}	
	}
*/
	public void autonomousInit(){
		driveBackLeft.setNeutralMode(NeutralMode.Brake);
		driveBackRight.setNeutralMode(NeutralMode.Brake);
		driveFrontLeft.setNeutralMode(NeutralMode.Brake);
		driveFrontRight.setNeutralMode(NeutralMode.Brake);
		driveBackLeft.getSensorCollection(). setQuadraturePosition (0, 10);
		driveBackRight.getSensorCollection(). setQuadraturePosition (0, 10);
	}
	public void autonomousPeriodic() {
		//driveFoward(200, 0.4);
		//Components.writemessage.setmessage(5, driveStick.pY.toString());
		//Components.writemessage.updatedash();
		mecDrive.driveCartesian(driveStick.pX, driveStick.pY, driveStick.pTwist);//Last 0 is gyro angle needs to be checked if we get one
		
	}
	public void teleopInit(){
		driveBackLeft.setNeutralMode(NeutralMode.Brake);
		driveBackRight.setNeutralMode(NeutralMode.Brake);
		driveFrontLeft.setNeutralMode(NeutralMode.Brake);
		driveFrontRight.setNeutralMode(NeutralMode.Brake);
	}
	public void teleopPeriodic(){
		encoderLeftCount = driveBackLeft.getSensorCollection().getQuadraturePosition ();
		encoderRightCount = driveBackRight.getSensorCollection().getQuadraturePosition ();
		mecDrive.driveCartesian(driveStick.sgetX(), driveStick.sgetY(), driveStick.sgetTwist());
		//Components.writemessage.setmessage(0, encoderLeftCount.toString());
		//Components.writemessage.setmessage(1, encoderRightCount.toString());
			
		//Components.writemessage.updatedash();
	}
	public void testInit(){}
	
	
	
	public void testPeriodic(){
		//driveTrain.mecanumDrive_Cartesian(driveStick.pX, driveStick.pY, driveStick.pTwist,0);//Last 0 is gyro angle needs to be checked if we get one
		// Display encoder values
		//encoderCheck();
		//driveTrain.mecanumDrive_Cartesian(driveStick.sgetX(), driveStick.sgetY(), driveStick.sgetTwist(), 0);
		//Components.writemessage.updatedash();
	}
	

	    
}
