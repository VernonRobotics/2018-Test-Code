package org.usfirst.frc.team1989.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class NewMecDriveCmd implements cmd{
	//attributes
	CANTalon1989 driveFrontLeft;
	CANTalon1989 driveFrontRight;
	CANTalon1989 driveBackLeft;
	CANTalon1989 driveBackRight;
	ADXRS450_Gyro gyro;
	
	double kP = 0.00975;
	double kI = 0.0;
	double kD = 0.0;
	
	//PIDController pidFrontLeft = new PIDController(kP, kI, kD, gyro, driveFrontLeft);
	//PIDController pidFrontRight = new PIDController(kP, kI, kD, gyro, driveFrontRight);
	//PIDController pidBackLeft = new PIDController(kP, kI, kD, gyro, driveBackLeft);
	//PIDController pidBackRight = new PIDController(kP, kI, kD, gyro, driveBackRight);



	Timer timer = new Timer();
	
	double gyroAngle;
	
	MecanumDrive mecDrive;
	JsScaled driveStick;

	boolean isPID;
	
	

	//constructor for regular CANTalon1989
	public NewMecDriveCmd(CANTalon1989 driveFrontLeft, CANTalon1989 driveBackLeft, CANTalon1989 driveFrontRight, CANTalon1989 driveBackRight,
			JsScaled driveStick, ADXRS450_Gyro gyro) {
		this.driveFrontLeft = driveFrontLeft;
		this.driveFrontRight = driveFrontRight;
		this.driveBackLeft = driveBackLeft;
		this.driveBackRight = driveBackRight;
		mecDrive = new MecanumDrive(driveFrontLeft,driveBackLeft,driveFrontRight, driveBackRight);
		this.driveStick = driveStick;
		this.gyro = gyro;
		
		}
	
	




//Robotics Methods

	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		driveBackLeft.setNeutralMode(NeutralMode.Brake);
		driveBackRight.setNeutralMode(NeutralMode.Brake);
		driveFrontLeft.setNeutralMode(NeutralMode.Brake);
		driveFrontRight.setNeutralMode(NeutralMode.Brake);
		
		if(isPID) {
		//	pidFrontLeft.enable();
			//pidFrontRight.enable();
			//pidBackLeft.enable();
			//pidBackRight.enable();
		}
		
		
		timer.stop();
		timer.reset();
		timer.start();
		
		
	}



	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
	
		
		
		mecDrive.driveCartesian(-driveStick.pX, -driveStick.pY, -driveStick.pTwist);
		
		
		
	
	
	
	}




	public void testInit() {
		// TODO Auto-generated method stub
		
	}




	public void testPeriodic() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		gyroAngle = gyro.getAngle();
		driveBackLeft.setNeutralMode(NeutralMode.Brake);
		driveBackRight.setNeutralMode(NeutralMode.Brake);
		driveFrontLeft.setNeutralMode(NeutralMode.Brake);
		driveFrontRight.setNeutralMode(NeutralMode.Brake);
	}



	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		
		if(driveStick.getRawButton(1) == true) {
			mecDrive.driveCartesian(-driveStick.sgetX()/2,driveStick.sgetY()/2, -driveStick.sgetTwist()/2);
	
		}else if(driveStick.getRawButton(9) == true) {
			mecDrive.driveCartesian(-0.5,driveStick.sgetY()/2, -driveStick.sgetTwist()/2);

		}else if(driveStick.getRawButton(10) == true) {
			mecDrive.driveCartesian(0.5,driveStick.sgetY()/2, -driveStick.sgetTwist()/2);

		}
		else {
			mecDrive.driveCartesian(-driveStick.getX(),driveStick.getY(), -driveStick.sgetTwist());

		}
			
		
		
		
		
	
		
		
	
	}

	
	
	
}
