package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;


public class StartCenterMoveForward {


	static int autoState = 0;
	static int x = 0;
	
	public static void run(int delay, boolean right) {
		if(autoState == 0) {
			
			AutoCommands.delay(delay);
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		}  else if(autoState == 1) {
			if(right) {
				AutoCommands.autoCartesianTime(x, x, 0);
			} else {
				AutoCommands.autoCartesianTime(x, -x, 0);
			}
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		}  else if(autoState == 2) {
			AutoCommands.autoCartesianTime(x,0,x);
		}
	

		
		
	}
	
}
