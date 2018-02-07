

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class MecDriveCmd implements cmd{
	//attributes
	CANTalon1989 driveFrontLeft;
	CANTalon1989 driveFrontRight;
	CANTalon1989 driveBackLeft;
	CANTalon1989 driveBackRight;
	ADXRS450_Gyro gyro;
	
	double kP = 0.03;
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
	public MecDriveCmd(CANTalon1989 driveFrontLeft, CANTalon1989 driveBackLeft, CANTalon1989 driveFrontRight, CANTalon1989 driveBackRight,
			JsScaled driveStick, ADXRS450_Gyro gyro, boolean isPID) {
		this.driveFrontLeft = driveFrontLeft;
		this.driveFrontRight = driveFrontRight;
		this.driveBackLeft = driveBackLeft;
		this.driveBackRight = driveBackRight;
		mecDrive = new MecanumDrive(driveFrontLeft,driveBackLeft,driveFrontRight, driveBackRight);
		this.driveStick = driveStick;
		this.gyro = gyro;
		this.isPID = isPID;
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
	
		
		
		mecDrive.driveCartesian(driveStick.pX, driveStick.pY, driveStick.pTwist);
		
		
		
	
	
	
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
		
		
			mecDrive.driveCartesian(driveStick.sgetX(),driveStick.sgetY(), driveStick.sgetTwist());
	
		
		
	
	}

	
	
	
}
