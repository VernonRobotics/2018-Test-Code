package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;


public class StartCenterSwitchLeft {


	static int autoState = 0;
	static int x = 0;
	
	public static void run() {
		if(autoState == 0) {
			
			AutoCommands.autoCartesianRange(AutoDistances.startToClosePath, 0, 0.5, Components.r1);
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		} else if (autoState == 1) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2) {
			AutoCommands.autoCartesianTime(x, -0.5, 0);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 3) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
			
		} else if (autoState == 4) {
			AutoCommands.autoCartesianRange(AutoDistances.closePathToLeftScale, 0, 0.5, Components.r1);
			
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 5) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 6) {
<<<<<<< HEAD
		//	AutoCommands.boxOutputSwitch();
=======
			AutoCommands.towerMove(AutoCommands.scaleTime);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 7) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 8) {
			AutoCommands.autoCartesianTime(1,0,0.3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}  else if (autoState == 9) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 10) {
			AutoCommands.boxOutput();
>>>>>>> branch 'master' of https://github.com/VernonRobotics/2018-Test-Code.git
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
	
			
			
			
		
		
		
		
		
		
		
	}
	
}
