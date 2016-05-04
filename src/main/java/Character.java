package main.java;

import java.util.HashMap;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	
	
	private static boolean dragging = false;
	
	// state 1 : dragged , 2 : inCircle , 3 : inOrigin , 4: inAni
	private int state;
	private boolean showName;
	
	private MainApplet parent;
	private int r, g, b;
	
	private float ogx,ogy; // original position
	private float cx,cy;
	public float x, y;
	private String name;
	private HashMap<Character,Integer> targets; // save the data of the intensity of line between target and source
	
	
	public void setCX(float f){cx = f;}
	public void setCY(float f){cy = f;}
	public void setState(int s){ state = s;}
	public void setShowName(boolean b){showName = b;}
	
	
	public float getCX(){return cx;}
	public float getCY(){return cy;}
	public int getState(){return state;}
	public boolean getShowName(){return showName;}

	public Character(MainApplet parent,  String name, float x, float y, int r, int g, int b){
		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
		this.r = r;
		this.g = g;
		this.b = b;
		targets = new HashMap<Character,Integer>();
	}

	public void display(){
		
		parent.stroke(255);
		parent.strokeWeight(1);
		
		if(state == 1)// Dragged
		{
			x = parent.mouseX;
			y = parent.mouseY;
		}
		else if(state == 2) // inCircle
		{
			x = cx;
			y = cy;
		}
		else if(state == 3)// inOrigin
		{
			x = ogx;
			y = ogy;
		}
		else if(state == 4)// inAni
		{
			if(x == ogx && y == ogy)
				state = 3;
			else if(x == cx && y == cy)
				state = 2;
		}
		
		
		if(showName)
		{
			// Bigger ellipse
			parent.fill(r, g, b, 80);
			parent.ellipse(x, y, 50, 50);
			
			// Show name
			parent.fill(30,144,255,150);
			float textWidth = (name.length() > 5) ? name.length() * 14.5f : 80;
			parent.rect(parent.mouseX, parent.mouseY - 17, textWidth,35,90);
			parent.fill(255);
			parent.textSize(20);
			parent.text(name, parent.mouseX + 10, parent.mouseY + 7.5f);
		}
		
		// Draw the smaller ellipse
		parent.fill(r, g, b, 80);
		parent.ellipse(x, y, 40, 40);
		parent.fill(255);
	}
	
	
	// getter & setter //
	
	public void setOGX(float x){
		this.ogx = x;
	}
	
	public float getOGX(){
		return this.ogx;
	}
	
	public void setOGY(float y){
		this.ogy = y;
	}
	
	public float getOGY(){
		return this.ogy;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public float getX(){
		return this.x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getY(){
		return this.y;
	}
	
	public void addTarget(Character ch, Integer i)
	{
		targets.put(ch, i);
	}
	
	public HashMap<Character,Integer> getTarget(){
		return this.targets;
	}
}
