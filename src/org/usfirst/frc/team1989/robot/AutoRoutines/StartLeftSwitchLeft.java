package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;

public class StartLeftSwitchLeft {


	static int autoState = 0;
	public static void run() {
		if(autoState == 0) {
			AutoCommands.autoCartesianTime(1.5,0,1);
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		} else if (autoState == 1) {
			AutoCommands.autoCartesianTime(1.5, 1, 0);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} 
	
			
			
			
		
		
		
		
		
		
		
	}
	
}
