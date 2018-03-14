package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;

public class StartLeftSwitchLeft {


	static int autoState = 0;
	public static void run(double delay) {
		
		if (autoState == 0) {
			AutoCommands.delay(delay);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}	
		}
		else if (autoState == 1) {
			AutoCommands.towerMove(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if(autoState == 2) {
			AutoCommands.autoCartesianTime(3.2, 0, 0.4);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if(autoState == 3) {
			AutoCommands.towerMove(3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if(autoState == 4) {
			AutoCommands.turnToAngle(90);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
		else if(autoState == 5) {
			AutoCommands.autoCartesianTime(0.75,0,0.3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}else if(autoState == 6) {
			AutoCommands.boxOutput();
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
	}
			
			
			
		
		
		
		
		
		
		
	}
	

