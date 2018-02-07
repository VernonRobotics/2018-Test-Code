

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * Note it expects to have indexes passed in the proper range
 * 
 *Usage : add to cmdlist in robot init
 *
 *in a class where you use it you can use the findcmd("writemessage") and assign it to a variable in any of the init methods that
 *get called after robot init like teleopinit 
 *andd then use setmessage and and setboolean to write your messages.
 *
 *or you can directly write to Robot.sharedStuff.msg etc
 */
public class writemessage implements cmd {
	public Timer t1 = new Timer();

	String type;
	public writemessage() {
		this.type = "writemessage"; // so we can find it
		for (int i = 0; i < 10; i++) {
			ExecuteCmdList.msg[i] = "";
		
			ExecuteCmdList.lastmsg[i] = "";
			SmartDashboard.putString("DB/String " + i, "");
			if (i < 5) {
				ExecuteCmdList.led[i] = false;
				ExecuteCmdList.lastled[i] = false;
				SmartDashboard.putBoolean("DB/LED " + i, false);
			}
		}
		t1.start();
	}

	public void setmessage(int index, String msg) {
		ExecuteCmdList.msg[index] = msg;
	}

	public void setboolean(int index, Boolean b) {
		ExecuteCmdList.led[index] = b;
	}

	public void updatedash() {
		String curr ;
		String last;
		boolean lastled;
		boolean currled;
		if (t1.get() > .25) {
			t1.reset();
			t1.start();

			for (int i = 0; i < 10; i++) {
				curr =ExecuteCmdList.msg[i].toString();
				last =ExecuteCmdList.lastmsg[i].toString();
				
				if (!(curr.equals(last.toString()))) {
					SmartDashboard.putString("DB/String " + i, curr);
					ExecuteCmdList.lastmsg[i]= curr;
				}
				if (i < 5) {
					lastled = ExecuteCmdList.lastled[i].booleanValue();
					currled = ExecuteCmdList.led[i].booleanValue();
					if (currled != lastled) {
						SmartDashboard.putBoolean("DB/LED " + i, currled);
						ExecuteCmdList.lastled[i] = currled;
					}
				}
			}
		}

	}
	public void autionomousInit(){
    	t1.reset();
    	t1.start();

	}
    public void autonomousPeriodic() {
    	updatedash();
    }
    public void DisabledPeriodic(){
    	updatedash();
    }
    public void testInit(){
    	t1.reset();
    	t1.start();
    	updatedash();
    }
    public void teleopInit(){
    	t1.reset();
    	t1.start();
    	updatedash();
    }
    public void teleopPeriodic() {
    	updatedash();
    }
    public void testPeriodic() {
    	updatedash();
    }
    public void autonomousInit(){
    	
    }

}
