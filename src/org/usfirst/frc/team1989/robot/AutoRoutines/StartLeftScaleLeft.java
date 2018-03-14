package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;

public class StartLeftScaleLeft {


	static int autoState = 0;
	static int x = 0;
	
	public static void run(double delay) {
		if(autoState == 0) {
			AutoCommands.delay(delay);
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		} else if (autoState == 1) {
			AutoCommands.towerMove(0.5);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2) {
			AutoCommands.autoCartesianTime(x, 0, 0.4);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 3) {
			
				autoState++;
			
			
		} else if (autoState == 4) {
			AutoCommands.turnToAngle(45);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 5) {
				autoState++;
			
		}  else if (autoState == 6) {
			AutoCommands.towerMove(2);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 7) {
				autoState++;
			
		} else if (autoState == 8) {
			AutoCommands.autoCartesianTime(x, 0.4, 0);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 9) {
			
				autoState++;
			
		} else if (autoState == 10) {
			AutoCommands.boxOutput();
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}  else if (autoState == 11) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 12) {
			AutoCommands.autoCartesianTime(x, -0.4, 0);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} 
		
	}
	
}
