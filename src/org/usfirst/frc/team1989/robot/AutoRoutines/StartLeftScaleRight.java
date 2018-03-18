package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;

public class StartLeftScaleRight {


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
			AutoCommands.turnToAngle(90);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
			
		} else if (autoState == 4) {
			AutoCommands.autoCartesianTime(2, 0, 0.5);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 5) {
			AutoCommands.towerMove(2);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}  else if (autoState == 6) {
			AutoCommands.turnToAngle(0);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 7) {
			AutoCommands.autoCartesianTime(0.5, 0, 0.3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 8) {
			AutoCommands.boxOutput();
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 9) {
			AutoCommands.autoCartesianTime(0.5,0,-0.3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 10) {
			AutoCommands.towerMove(-2.5);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} 
	
			
			
			
		
		
		
		
		
		
		
	}
	
}
