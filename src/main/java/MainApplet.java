package main.java;

import processing.core.PApplet;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	
	private Network test;
	private Character test2 = new Character(this, "GOLD FIVE", 100,100,20,255,100);
	
	private final static int width = 1200, height = 650;
	
	public void setup() {

		size(width, height);
		smooth();
		loadData();
		
		test = new Network(this);
		
		
	}

	public void draw() {
		background(255);
		test.display();
		test2.display();
	}

	private void loadData(){

	}

}
