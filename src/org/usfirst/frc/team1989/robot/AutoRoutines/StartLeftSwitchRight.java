package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;

public class StartLeftSwitchRight {


	static int autoState = 0;
	static int x = 0;
	public static void run(int delay) {
		switch (autoState) {
		case 0:
			AutoCommands.delay(delay);
			if(AutoCommands.actionFlag = false)
				autoState++;
		break;
		case 1:
			AutoCommands.towerMove(1);
			if(AutoCommands.actionFlag = false)
				autoState++;
		break;
		case 2:
			AutoCommands.autoCartesianTime(x, 0, 0.5);
			if(AutoCommands.actionFlag = false)
				autoState++;
		break;
		case 3:
			AutoCommands.turnToAngle(90);
			if(AutoCommands.actionFlag = false)
				autoState++;
		break;
		case 4:
			AutoCommands.autoCartesianTime(x, 0, 0.5);
			if(AutoCommands.actionFlag = false)
				autoState++;
		break;
		case 5: 
			AutoCommands.towerMove(3);
			if(AutoCommands.actionFlag = false)
				autoState++;
		break;
		case 6:
			AutoCommands.turnToAngle(180);
			if(AutoCommands.actionFlag = false)
				autoState++;
		break;
		case 7: 
			AutoCommands.autoCartesianTime(x, 0, x);
			if(AutoCommands.actionFlag = false)
				autoState++;
		break;
		case 8: 
			AutoCommands.boxOutput();
			if(AutoCommands.actionFlag = false)
				autoState++;
		break;
			
		
		
		
		
		
		}
	
			
			
			
		
		
		
		
		
		
		
	}
	
}
