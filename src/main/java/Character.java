package main.java;

import java.util.HashMap;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private int r, g, b;
	private boolean showLink = false;
	private float ogx,ogy; // original position
	private float x, y;
	private String name;
	private HashMap<Character,Integer> targets; // save the data of the intensity of line between target and source

	public Character(MainApplet parent, String name, float x, float y, int r, int g, int b){
		this.parent = parent;
		this.name = name;
		this.ogx = x;
		this.ogy = y;
		this.x = x;
		this.y = y;
		this.r = r;
		this.g = g;
		this.b = b;
		targets = new HashMap<Character,Integer>();
	}

	public void display(){
		// When mouse is on , show the name and bigger ellipse, if pressed move the ellipse
		if((parent.mouseX <= x+20 && parent.mouseX >= x-20) && (parent.mouseY <= y+20 && parent.mouseY >= y-20)){
			//  Moused pressed ? move with mouse
			if(parent.mousePressed == true)
			{
				x = parent.mouseX;
				y = parent.mouseY;
			}
			else
			{
				x = ogx;
				y = ogy;
			}
			
			// Bigger ellipse
			parent.fill(r, g, b, 80);
			parent.ellipse(x, y, 50, 50);
			
			// Show name
			parent.fill(30,144,255,80);
			float textWidth = (name.length() > 5) ? name.length() * 14.5f : 80;
			parent.rect(parent.mouseX, parent.mouseY - 17, textWidth,35,40);
			parent.fill(255);
			parent.textSize(20);
			parent.text(name, parent.mouseX + 10, parent.mouseY + 7.5f);
		}
		
		// Draw the smaller ellipse
		parent.fill(r, g, b, 80);
		parent.ellipse(x, y, 40, 40);
		parent.fill(255);
		// determine whether in circle 
		if(showLink){
			for(Character key : targets.keySet()){
				parent.stroke(0);
				parent.strokeWeight(targets.get(key));
				parent.curve(x, y, (600+x)/2, (350+y)/2, (600+key.getX())/2, (350+key.getY())/2, key.getX(), key.getY());
			}
			parent.strokeWeight(1);
		}
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
	
	public void setShowLink(boolean b){
		this.showLink = b;
	}
	
	public HashMap<Character,Integer> getTarget(){
		return this.targets;
	}
	//////////////////////////////////////////////////////

}
