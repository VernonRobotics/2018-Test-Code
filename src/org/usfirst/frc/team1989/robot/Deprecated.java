package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Ultrasonic;

/*
 * This class is specifically for functions that were written, but are not being used at the moment.
 */

public class Deprecated {
	
	// Class variables created simply for compatibility
	boolean motionActive = false;
	double startDistance = 0;
	

	/*
	 * verticalMotionActive defined at top.
	 */
	public void moveVertical(double distance, double speed, Ultrasonic rangeFinder) {
				
		if(motionActive == false) {
			Components.killRangeFinders();
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
			Components.killDriveMotors();
			rangeFinder.setEnabled(false);
			motionActive = false;
		}
	}
	
	public void moveHorizontal(double distance, double speed, Ultrasonic rangeFinder) {
		
		if(motionActive == false) {
			Components.killRangeFinders();
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
			Components.killDriveMotors();
			rangeFinder.setEnabled(false);
			motionActive = false;
		}
	}
}
