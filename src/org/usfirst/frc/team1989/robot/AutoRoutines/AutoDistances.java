package org.usfirst.frc.team1989.robot.AutoRoutines;

public class AutoDistances
{
 	//All values in inches, All negatives are moving backwards
	//subtract 35 from FWD, subtract 6 from left or right because of range finder positions
	public final static double startingDistanceToEdge = 12.56;
	public final static double startToClosePath = 55;
	public final static double startToSwitch = 140;
	public final static double startToMidPath = 245.735;
	public final static double midPathCrossSwitch = 204.88; //left or right
	public final static double midPathToSwitch = -49.735; //negative = backwards
	public final static double adjustForScale = 13.68; //left or right
	public final static double midPathCrossScale = 218.56;
	public final static double midPathToScale = 88.275;
	public final static double startToSwitchSide = 97;
	public final static double adjustForSwitch = 16.44;
	public final static double closePathCrossLeftSwitch = 102.44;
	public final static double closePathToSwitch = 42;
	public final static double closePathCrossLeftScale = 125.12;
	public final static double closePathToLeftScale = 244.65;
	public final static double closePathToRightScale = 73.12; //from right switch to line up
	public final static double switchToRightScale = 202.65;

	/*public static double getStartingDistanceToEdge{ return startingDistanceToEdge; }
	public static double getStartToClosePath{ return startToClosePath; }
	public static double getStartToSwitch{ return startToSwitch; }
	public static double getStartToMidPath{ return startToMidPath; }
	public static double getMidPathCrossSwitch{ return midPathCrossSwitch; }
	public static double getMidPathToSwitch{ return midPathToSwitch; } //returns a negative because the movement is made backwards only
	public static double getAdjustForScale{ return adjustForScale; }
	public static double getMidPathCrossScale{ return midPathCrossScale; }
	public static double getMidPathToScale{ return midPathToScale; }
	public static double getStartToSwitchSide{ return startToSwitchSide; }
	public static double getAdjustForSwitch{ return adjustForSwitch; }
	public static double getClosePathCrossLeftSwitch{ return closePathCrossLeftSwitch; }
	public static double getClosePathToSwitch{ return closePathToSwitch; }
	public static double getClosePathCrossLeftScale{ return closePathCrossLeftScale; }
	public static double getClosePathToLeftScale{ return closePathToLeftScale; }
	public static double getClosePathToRightScale{ return closePathToRightScale; }
	public static double getSwitchToRightScale{ return switchToRightScale; }*/
}