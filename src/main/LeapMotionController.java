package main;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

class LeapEventListener extends Listener {	
    public void onFrame (Controller controller){
    	Frame frame = controller.frame();
    	System.out.println(frame.hands().count());
    }

    public void onInit (Controller controller){
        System.out.println("Initialized");
    }

    public void onConnect (Controller controller){
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
    }

    public void onDisconnect (Controller controller){
        System.out.println("Disconnected");
    }

    public void onFocusGained (Controller controller){
        System.out.println("Focus gained");
    }

    public void  onFocusLost (Controller controller){
        System.out.println("Focus lost");        
    }
}

public class LeapMotionController {
	public static void main(String[] args) {
		Controller controller = new Controller();
		LeapEventListener ll = new LeapEventListener();
		
		controller.addListener(ll);
		
		System.out.println("Press enter to exit");
		
		try {
			System.in.read();
		} catch(IOException ioe) {
			System.out.println("Error");
		}
	}
}
