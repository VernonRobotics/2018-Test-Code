package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;

public class StartCenterScaleRight {

	static int x = 0;
	static int autoState = 0;
	
	public static void run() {
		if(autoState == 0) {
			
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		} else if (autoState == 1) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2) {
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 3) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
			
		} else if (autoState == 4) {
			
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 5) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}  else if (autoState == 6) {
			AutoCommands.turnToAngle(-90);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 7) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}

		} else if (autoState == 8) {
		//	AutoCommands.boxOutputSwitch();

		}else if (autoState == 8) {
			AutoCommands.towerMove(AutoCommands.scaleTime);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 9) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 10) {
			AutoCommands.autoCartesianTime(1,0,0.3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}  else if (autoState == 11) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 12) {
			AutoCommands.boxOutput();

			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
	
			
			
			
		
		
		
		
		
		
		
	}
	
}
