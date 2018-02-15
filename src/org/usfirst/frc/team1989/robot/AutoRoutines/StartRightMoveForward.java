package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;


public class StartRightMoveForward {


	static int autoState = 0;
	static int x = 0;
	
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
			AutoCommands.boxOutputSwitch();
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}		
	} 
}
	
