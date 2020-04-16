# Driving in Motion
## This project was accomplished using Java, C# (within Unity), and a Leap Motion hand sensor
Run both the main function in "LeapMotionController.java" as well as the Unity Project to begin.  

Using Unity Version: 2017.3.0f3

The java program will be detecting hands and writing the data to a file, which will be read by C#.  

If you need to test it out but do not have a Leap Motion accessible to you, open the Unity Project, and also navigate to `\Unity Stuff\HackHammerNU\Assets\Resources`.  
 
Here you can find 2 relevant files, `moving.txt` and `stream.txt`
When the moving file contains the string `true` the car will forward, and `false` will stop the car

The stream file contains a value between -1 to 1. This value represents the amount the car wheels turn.

To test without the leap motion, when the Unity Project is running, simply change the contents of the text files, when you go back to the game it should propagate the changes and the car should move accordingly.