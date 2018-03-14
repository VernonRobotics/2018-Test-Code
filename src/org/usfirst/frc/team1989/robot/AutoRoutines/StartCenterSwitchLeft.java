package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;

public class StartCenterSwitchLeft {


	static int autoState = 0;
	public static void run() {
		if(autoState == 0) {
			AutoCommands.delay(2);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}	
		}
		
		else if (autoState == 1 ) {
			AutoCommands.towerMove(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if(autoState == 2) {
			AutoCommands.autoCartesianTime(0.5, 0, 0.4);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if(autoState == 3) {
			AutoCommands.autoCartesianTime(2.75,-.8,0);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if(autoState == 4) {
			AutoCommands.autoCartesianTime(3.2, 0, 0.4);

			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
		else if(autoState == 5) {
			AutoCommands.towerMove(3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}else if(autoState == 6) {
			AutoCommands.turnToAngle(90);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if(autoState == 7) {
			AutoCommands.autoCartesianTime(1, 0, 0.5);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if(autoState == 8) {
			AutoCommands.boxOutput();
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} 
		}
			
			
			
		
		
		
		
		
		
		
	}
	

