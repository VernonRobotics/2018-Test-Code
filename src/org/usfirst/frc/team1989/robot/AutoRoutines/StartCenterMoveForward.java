package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;


public class StartCenterMoveForward {


	static int autoState = 0;
	static int x = 0;
	
	public static void run() {
		if(autoState == 0) {
			
			AutoCommands.towerMove(0.5);
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		} else if (autoState == 1) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2) {
<<<<<<< HEAD
			//AutoCommands.boxOutputSwitch();
=======
			AutoCommands.towerMove(autoState);
>>>>>>> branch 'master' of https://github.com/VernonRobotics/2018-Test-Code.git
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
	

		
		
	}
	
}
