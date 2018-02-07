

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class TowerControl implements cmd {
	
	CANTalon1989 rightMotor;
	CANTalon1989 leftMotor;
	ArmControl arms;
	JsScaled uStick;
	DigitalInput switchSwitch = new DigitalInput(1);
	DigitalInput lowSwitch = new DigitalInput(2);
	Counter counter = new Counter(switchSwitch);
	int towerState = 0;
	double towerScaleTime = 0.75;
	boolean moveSwitch = false;
	boolean moveScale = false;
	boolean towerScale = false;
	Timer timer = new Timer();
	int state = 0;
	double minSpeed = 0.25;
	//state 1 = motors up
	//state 2 = motors down
	
	
	//constructor
	public TowerControl(CANTalon1989 rightMotor, CANTalon1989 leftMotor, JsScaled uStick) {
		this.rightMotor = rightMotor;
		this.leftMotor = leftMotor;
	
		this.uStick = uStick;
	}
	//class methods
	
	
	public void towerPresetControl() {
		if (moveSwitch) {
			if (!switchSwitch.get()) {
				if (counter.get() % 2 == 0) {
					if(lowSwitch.get()) {
						towerControl(minSpeed);
					}else {
						towerControl(0.5);
					}		
				}else {
					towerControl(-0.5);
				}
			}
			else {
				towerStop();
				moveSwitch = false;
			}
		}
		else if(moveScale) {
			if(!rightMotor.getSensorCollection().isFwdLimitSwitchClosed()) {
				if(lowSwitch.get()) {
					towerControl(minSpeed);
				}
				else {
					towerControl(0.5);
				}
			}else {
				towerStop();
				moveSwitch = false;
			}
		}
	}
	
	
	
	
	/*
	public void moveStart() {
		moveSwitch = true;
	}
	
	public void 9motortowerControl() {
		if (moveSwitch || moveScale) {
			if(state == 0 ) {
				state =1;
				//boxArmUp();
				
			}
			else if(state == 1) {
				if (moveSwitch) {
					if(switchSwitch.get()) {
						//boxStop();
					}
				}
				if(moveScale) {
					towerToScale();
					if(scaleSwitch.get()) {
						//boxStop();
					}
				}
			}
		}
	}
	
	
	public void towerToScale() {
		if (towerState == 0) {
			towerState = 1;
			timer.stop();
			timer.reset();
			timer.start();
			towerUp();
			
		}
		else if (towerState ==1 ){
			if(timer.get() > towerScaleTime ) {
				towerStop();
				timer.stop();
				timer.reset();
			}
		}
		
	}*/
	
	public void towerControl(double speed){
		rightMotor.set(speed);
		leftMotor.set(speed);
	}
	
	
	public void towerStop() {
		rightMotor.set(0);
		leftMotor.set(0);
	}
	
	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		counter.reset();
	}

	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		counter.reset();
	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		
		if(uStick.getRawButton(12)==true)
			moveScale = true;
			moveSwitch = false;
		
		if(uStick.getRawButton(11) == true)
			moveSwitch = true;
			moveScale = false;
		
		towerPresetControl();
	
		
		if(lowSwitch.get()) {
			if(uStick.getY() > .15) {
				towerControl(minSpeed);
			} else if(uStick.getY() < -.15) {
				towerControl(-minSpeed);
			} else {
				towerStop();
			}
		}else {
			if(uStick.getY() > .15) {
				towerControl(0.5);
			} else if(uStick.getY() < -.15) {
				towerControl(-0.5);
			} else {
				towerStop();
			}
		}
		
	}
	
}
