using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using Leap;


public class SendCoord : MonoBehaviour {

	// Use this for initialization
	void Start () {
	}

		Controller controller = new Controller();

	// Update is called once per frame
	void Update () {
		Frame frame = controller.Frame (); // controller is a Controller object
		if(frame.Hands.Count > 0){
		Hand hand = frame.Hands[0];
		Vector position = hand.PalmPosition;
		// Vector velocity = hand.PalmVelocity;
		Vector direction = hand.Direction;
		Console.WriteLine(position);
}
	}
}
