package main;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;

class LeapEventListener extends Listener {	
	private static Hand leftHand = new Hand();
	private static Hand rightHand = new Hand();

	public void onFrame (Controller controller){
		Frame frame = controller.frame();
		leftHand = frame.hands().leftmost();
		rightHand = frame.hands().rightmost();
		double x = leftHand.wristPosition().getX();
		double y = Math.abs(leftHand.wristPosition().getY());
		double dist = (Math.max(x, rightHand.wristPosition().getX()) - Math.min(x, rightHand.wristPosition().getX()));
		double rad = dist / 2;
		double startTime = System.currentTimeMillis();

		System.out.println(calibrate(x, y, dist, startTime));
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onInit(Controller controller){
		System.out.println("Initialized");
	}

	public void onConnect(Controller controller){
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
	}

	public void onDisconnect(Controller controller){
		System.out.println("Disconnected");
	}

	public void onFocusGained(Controller controller){
	}

	public void onFocusLost(Controller controller){
		System.out.println("LAWL FK U");
	}


	private boolean thresh(double startVal, double currentVal, double range) {
		return (Math.max(startVal, currentVal) - Math.min(startVal, currentVal)) <= range;
	}

	// && !thresh(yVal, leftHand.wristPosition().getY(), 50) 
	//	&& !thresh(dist, (Math.max(x, rightHand.wristPosition().getX()) - Math.min(x, rightHand.wristPosition().getX())) * 2, 50)) {

	private boolean calibrate(double xVal, double yVal, double dist, double initialTime) {
		double x = xVal, y = yVal, distance = dist;
		while(System.currentTimeMillis() - initialTime <= 3000) {
			System.out.println(System.currentTimeMillis() - initialTime);
			if(!thresh(xVal, leftHand.wristPosition().getX(), 50)) {
				System.out.println(xVal + " " + leftHand.wristPosition().getX());
				calibrate(x, y, distance, initialTime);
			}
		}
		return true;
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

	public static double calcTurn(double y, double dist) {
		return (Math.asin(y / dist) * (180 / Math.PI));
	}
}
