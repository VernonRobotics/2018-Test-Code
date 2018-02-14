package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;

public class StartCenterScaleRight {

	static int x = 0;
	static int autoState = 0;
	
	public static void run() {
		if(autoState == 0) {
			
			AutoCommands.autoCartesianRange(AutoDistances.startToSwitchSide, 0, 0.5, Components.r1);
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		} else if (autoState == 1) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2) {
			AutoCommands.autoCartesianRange(AutoDistances.closePathToRightScale, 0.5, 0, Components.r3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 3) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
			
		} else if (autoState == 4) {
			AutoCommands.autoCartesianRange(AutoDistances.switchToRightScale, 0, 0.5, Components.r1);
			
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 5) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}  else if (autoState == 6) {
			AutoCommands.turnToAngle(-90);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 7) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 8) {
			AutoCommands.boxOutputSwitch();
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
	
			
			
			
		
		
		
		
		
		
		
	}
	
}
