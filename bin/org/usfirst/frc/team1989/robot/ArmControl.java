


public class ArmControl implements cmd {
	//Attributes
	CANTalon1989 leftMotor;
	CANTalon1989 rightMotor;
	JsScaled uStick;
	
	//Basic Constructor
	public ArmControl(CANTalon1989 leftMotor, CANTalon1989 rightMotor, JsScaled utilityStick) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		uStick = utilityStick;
	}
	
	//Class Methods
	public void boxIn() {
		leftMotor.set(-0.5);
		rightMotor.set(-0.5);
	}
	public void boxOut() {
		leftMotor.set(0.5);
		rightMotor.set(0.5);
	}
	public void boxStop() {
		leftMotor.set(0);
		rightMotor.set(0);
	}
	//Cmd List Methods
	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		if(uStick.getRawButton(5)==true)
			boxIn();
		else if(uStick.getRawButton(3)==true)
			boxOut();
		else boxStop();
		
	}
	
	
	

}

