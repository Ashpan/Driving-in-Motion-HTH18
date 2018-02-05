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
	private static double initY = 0;
	private static boolean calibrated = false;
	private static boolean moving = false;

	public void onFrame (Controller controller){
		Frame frame = controller.frame();
		leftHand = frame.hands().leftmost();
		rightHand = frame.hands().rightmost();
		double x = leftHand.wristPosition().getX();
		double y = Math.abs(leftHand.wristPosition().getY());
		double dist = (Math.max(x, rightHand.wristPosition().getX()) - Math.min(x, rightHand.wristPosition().getX()));
		double rad = dist / 2;
		double startTime = System.currentTimeMillis();

        while(frame.hands().isEmpty()){
            moving = false;
        }

		while(!frame.hands().isEmpty() && !calibrated) {
//			System.out.println("x: "+leftHand.wristPosition().getX()+"\ny: "+leftHand.wristPosition().getY()+"\nz: "
//					+leftHand.wristPosition().getZ()+"\n");
			calibrate(x, y, dist, startTime);
			calibrated = true;
			System.out.println("while loop running");
		}
//		System.out.println("initY "+ initY);
//		System.out.println("finalY "+ y);
//		System.out.println("deltaY " + (y - initY));
//		System.out.println("dist " + dist);
		System.out.println(LeapMotionController.calcTurn(y - initY, dist));

		try {
			FileWriterFU.write(LeapMotionController.calcTurn(y - initY, dist));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileWriterFU.writespeed(moving);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		try {
//			FileWriterFU.delete();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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
	    moving = false;
	}

	public void onFocusGained(Controller controller){
	    moving = true;
	}

	public void onFocusLost(Controller controller){
        moving = false;
    }


	private boolean thresh(double startVal, double currentVal, double range) {
		return (Math.max(startVal, currentVal) - Math.min(startVal, currentVal)) <= range;
	}

	private boolean calibrate(double xVal, double yVal, double dist, double initialTime) {
		System.out.println("calibrating");
		double x = xVal, y = yVal, distance = dist;
		while(System.currentTimeMillis() - initialTime <= 3000) {
			if(!thresh(xVal, leftHand.wristPosition().getX(), 10)
					|| !thresh(yVal, leftHand.wristPosition().getY(), 10) 
					||!thresh(dist, (Math.max(x, rightHand.wristPosition().getX()) - Math.min(x, rightHand.wristPosition().getX())) * 2, 10)) {
//				System.out.println(xVal + " " + leftHand.wristPosition().getX());
			}
		}
		initY = yVal;
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
