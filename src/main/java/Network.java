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
	
	private int circleDiameter;
	private int circleX;
	private int circleY;
	private int circleNum;
	private Character firstCh;
	
	// Getter Setter
	public void setCircleDiameter(int r){circleDiameter = r;}
	public void setCircleX(int x){circleX = x;}
	public void setCircleY(int y){circleY = y;}
	public void setCircleNum(int n){circleNum = n;}
	public void addCharacters(Character ch) { characters.add(ch); }
	
	public int getCircleDiameter(){return circleDiameter;}
	public int getCircleX(){return circleX;}
	public int getCircleY(){return circleY;}
	public int getCircleNum(){return circleNum;}
	public ArrayList<Character> getCharacters(){return characters;}
	public ArrayList<Character> getCharactersInCircle(){return charactersInCircle;}
	
	public Network(PApplet parent){

		this.parent = parent;
		this.characters = new ArrayList<Character>();
		this.charactersInCircle = new ArrayList<Character>();
		
		this.circleX = 600;
		this.circleY = 350;
		this.circleDiameter = 450;
		this.circleNum = 0;
	}
	
	
	
	public void display(){
		parent.stroke(204,250,0);
		parent.strokeWeight(4);
		parent.fill(255);
		parent.ellipse(circleX,circleY,circleDiameter,circleDiameter);
		
		// Show characters
		long max = 0;
		// to compare every characters' theFirst, only the one be clicked first have the biggest theFirst
		for(Character ch : characters){
			if(ch.getFirst() >= max){
				max = ch.getFirst();
				firstCh = ch;
			}
		}
		// the one have the biggest theFirst can be move, so let the argument be true
		for(Character charac : characters){
			if(charac == firstCh){
				charac.display(true,this);
			}
			else
				charac.display(false,this);
		}
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
