package org.usfirst.frc.team1989.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class NewMecDriveCmd implements cmd{
	//attributes
	CANTalon1989 driveFrontLeft;
	CANTalon1989 driveFrontRight;
	CANTalon1989 driveBackLeft;
	CANTalon1989 driveBackRight;
	MecanumDrive mcDrive;
	JsScaled driveStick;
	
	
	
	

	

	//constructor
	public NewMecDriveCmd(CANTalon1989 driveFrontLeft, CANTalon1989 driveBackLeft, CANTalon1989 driveFrontRight, CANTalon1989 driveBackRight,
			JsScaled driveStick) {
		this.driveFrontLeft = driveFrontLeft;
		this.driveFrontRight = driveFrontRight;
		this.driveBackLeft = driveBackLeft;
		this.driveBackRight = driveBackRight;
		mcDrive = new MecanumDrive(driveFrontLeft,driveBackLeft,driveFrontRight, driveBackRight);
		this.driveStick = driveStick;
		//this.driveFrontRight.setInverted(true);
		//this.driveBackRight.setInverted(true);
	}




//Robotics Methods

	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		driveBackLeft.setNeutralMode(NeutralMode.Brake);
		driveBackRight.setNeutralMode(NeutralMode.Brake);
		driveFrontLeft.setNeutralMode(NeutralMode.Brake);
		driveFrontRight.setNeutralMode(NeutralMode.Brake);
		
	}



	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		mcDrive.driveCartesian(driveStick.pX, driveStick.pY, driveStick.pTwist);
	}



	@Override
	public void testInit() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		mcDrive.driveCartesian(driveStick.sgetX(), 0-driveStick.sgetY(), driveStick.sgetTwist());
	}

	
	
	
}
