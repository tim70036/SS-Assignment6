package main.java;

import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private ArrayList<Character> characters;
	private ArrayList<Character> charactersInCircle;
	
	private int circleRadius;
	private int circleX;
	private int circleY;
	
	// Getter Setter
	public void setCircleRadius(int r){circleRadius = r;}
	public void setCircleX(int x){circleX = x;}
	public void setCircleY(int y){circleY = y;}
	public void addCharacters(Character ch) { characters.add(ch); }
	
	public int getCircleRadius(){return circleRadius;}
	public int getCircleX(){return circleX;}
	public int getCircleY(){return circleY;}
	public ArrayList<Character> getCharacters(){return characters;}
	public ArrayList<Character> getCharactersInCircle(){return charactersInCircle;}
	
	public Network(PApplet parent){

		this.parent = parent;
		this.characters = new ArrayList<Character>();
		this.charactersInCircle = new ArrayList<Character>();
		
		this.circleX = 600;
		this.circleY = 350;
		this.circleRadius = 450;
		
	}
	
	
	
	public void display(){
		parent.stroke(204,250,0);
		parent.strokeWeight(4);
		parent.fill(255);
		parent.ellipse(circleX,circleY,circleRadius,circleRadius);
		
		// Show characters
		for(Character ch : characters)	ch.display();
		//parent.curve(100, 100, 200, 200,200,300,100,400);
	}
	
	public void setCharactersOGXY()
	{
		int ogx = 30,ogy = 50;
		for(Character ch : characters)
		{
			ch.setOGX(ogx);
			ch.setOGY(ogy);
			ogx += 80;
			if(ogx > 320)
			{
				ogy += 50;
				ogx = 30;
			}
		}
	}
}
