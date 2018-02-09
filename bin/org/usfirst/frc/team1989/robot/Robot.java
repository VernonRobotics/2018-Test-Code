/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


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
	Timer timer = new Timer();
	Double angle;
	Double inches;
	writemessage write= new writemessage();
	static MecDriveCmd mDrive = new MecDriveCmd(Components.frontLeft,Components.frontRight,Components.backLeft,Components.backRight,Components.driveStick,Components.gyro, false);
	
	// Used for autonomous Motion methods
	boolean motionActive;
	double startDistance;
	
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
		
		ExecuteCmdList.cmdlist.add(mDrive);
		ExecuteCmdList.cmdlist.add(write);
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
		timer.stop();
		timer.reset();
		timer.start();
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
	
		for (int i = 0; i < ExecuteCmdList.cmdlist.size(); i++) {
			ExecuteCmdList.cmdlist.get(i).autonomousPeriodic();
		}
		
		
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		for (int i = 0; i < ExecuteCmdList.cmdlist.size(); i++) {
			ExecuteCmdList.cmdlist.get(i).teleopPeriodic();
		}
		
		angle = Components.gyro.getAngle();
		//inches = r1.getRangeInches();
		
		write.setmessage(0,angle.toString());
	//	write.setmessage(1, inches.toString());
		write.updatedash();
	}

	/*
	 * Disable all RangeFinders
	 */
	public void killRangeFinders() {
	//	r1.setEnabled(false);
	//	r2.setEnabled(false);
	//	r3.setEnabled(false);
	}
	
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
	public void moveVertical(double distance, double speed, Ultrasonic rangeFinder) {
				
		if(motionActive == false) {
			killRangeFinders();
			rangeFinder.setEnabled(true);
			startDistance = rangeFinder.getRangeInches();
			motionActive = true;
		}
		
		double currentDistance = rangeFinder.getRangeInches();
		if(currentDistance - startDistance < distance) {
			Components.frontLeft.set(-speed);
			Components.backLeft.set(-speed);
			Components.frontRight.set(speed);
			Components.backRight.set(speed);
		} else {
			killDriveMotors();
			rangeFinder.setEnabled(false);
			motionActive = false;
		}
	}
	
	public void moveHorizontal(double distance, double speed, Ultrasonic rangeFinder) {
		
		if(motionActive == false) {
			killRangeFinders();
			rangeFinder.setEnabled(true);
			startDistance = rangeFinder.getRangeInches();
			motionActive = true;
		}
		
		double currentDistance = rangeFinder.getRangeInches();
		if(currentDistance - startDistance < distance) {
			Components.frontLeft.set(-speed);
			Components.backLeft.set(speed);
			Components.frontRight.set(speed);
			Components.backRight.set(-speed);
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
		for (int i = 0; i < ExecuteCmdList.cmdlist.size(); i++) {
			ExecuteCmdList.cmdlist.get(i).teleopPeriodic();
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

		if (timer.get() < 10) {
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
		
		
		if(timer.get() % 0.25 == 0) {
			write.updatedash();
		}
		
	}
}