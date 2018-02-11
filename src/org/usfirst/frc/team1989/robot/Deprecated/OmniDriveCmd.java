package org.usfirst.frc.team1989.robot.Deprecated;

import org.usfirst.frc.team1989.robot.CANTalon1989;
import org.usfirst.frc.team1989.robot.JsScaled;
import org.usfirst.frc.team1989.robot.cmd;

import edu.wpi.first.wpilibj.RobotDrive;

public class OmniDriveCmd implements cmd {

	
	CANTalon1989 driveFrontStraight;
	CANTalon1989 driveFrontSide;
	CANTalon1989 driveBackStraight;//encoder motor
	CANTalon1989 driveBackSide;// encoder motor
	JsScaled driveStick;
	
	
	
	
	
	public OmniDriveCmd(CANTalon1989 driveFrontStraight, CANTalon1989 driveFrontSide, CANTalon1989 driveBackStraight, CANTalon1989 driveBackSide, JsScaled driveStick){
		this.driveFrontStraight = driveFrontStraight;
		this.driveFrontSide = driveFrontSide;
		this.driveBackStraight = driveBackStraight;
		this.driveBackSide = driveBackSide;
		this.driveStick = driveStick;
		
	}
	
	public void omniDrive(int jsX, int jsY, int jsZ){
		CANTalon1989[] motors = {driveFrontStraight, driveFrontSide, driveBackStraight, driveBackSide};
		int setX;
		int setY;
		for(int i = 0; i < motors.length; i++){
			
			
			if(Math.abs(jsZ) >=.15){
				
				
				
				
				
				
				
			}/* else{
				if(motors[i].getBackStatus()){
					setX = -jsX;
					setY = -jsY;	
				}else{
					setX=jsX;
					setY=jsY;
				}
				
				if(motors[i].getSideStatus()){
					motors[i].set(setX);
				}else{
					motors[i].set(setY);
				}
			}
			
			
			*/
			
			
			
			
			
			
		}
		
		
	}
	
	
	
	
	
	
	
	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		
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
		
	}

}
