package org.usfirst.frc.team1989.robot.Deprecated;

import java.util.ArrayList;

import org.usfirst.frc.team1989.robot.JsScaled;
import org.usfirst.frc.team1989.robot.cmd;

import edu.wpi.first.wpilibj.Timer;

public class AutoWriter implements cmd {
	
	Timer timer = new Timer();
	double timeCycle = 0.1;
	JsScaled driveStick;
	JsScaled uStick;
	ArrayList list = new ArrayList();
	String outputString;
	Double jsX= 0.0;
	Double jsY= 0.0;
	Double jsZ=0.0; 
	Boolean js1 = false;
	Boolean js2= false;
	Boolean js3= false; 
	
	
	
	
	public AutoWriter(JsScaled driveStick, JsScaled uStick) {
		this.driveStick = driveStick;
		this.uStick = uStick;
	}
	
	
	
	
	public void record() {
		if(timer.get() % timeCycle == 0) {
			
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
		list.add(jsX);
		list.add(jsY);
		list.add(jsZ);
		list.add(js1);
		list.add(js2);
		list.add(js3);
		timer.stop();
		timer.reset();
		timer.start();
	
	}

	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		jsY = driveStick.sgetY();
		jsX = driveStick.sgetX();
		jsZ = driveStick.sgetTwist();
		
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
