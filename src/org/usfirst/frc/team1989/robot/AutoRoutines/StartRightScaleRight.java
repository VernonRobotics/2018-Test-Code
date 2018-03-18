package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;

public class StartRightScaleRight {


	static int autoState = 0;
	static int x = 0;
	
	public static void run(double delay) {
		if(autoState == 0) {
			AutoCommands.delay(delay);
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		} else if (autoState == 1) {
			AutoCommands.towerMove(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2) {
			AutoCommands.autoCartesianTime(3.85, 0, 0.6);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 3) {
			
				autoState++;
			
			
		} else if (autoState == 4) {
			AutoCommands.turnToAngle(-40);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}   else if (autoState == 5) {
			AutoCommands.towerMove(2);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}else if (autoState == 7) {
			AutoCommands.autoCartesianTime(0.5, 0, 0.3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}else if (autoState == 8) {
			AutoCommands.boxOutput();
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}  else if (autoState == 12) {
			AutoCommands.autoCartesianTime(0.5, 0, -0.3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 13) {
			AutoCommands.towerMove(-2.5);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
		
	}
	
}
