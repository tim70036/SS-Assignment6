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
		parent.background(255);
		parent.fill(r, g, b, 20);
		parent.ellipse(x, y, 40, 40);
		// determine whether in circle 
		if(showLink){
			for(Character key : targets.keySet()){
				parent.stroke(0);
				parent.strokeWeight(targets.get(key));
				parent.curve(x, y, x, y, key.getX(), key.getY(), key.getX(), key.getY());
			}
			parent.strokeWeight(1);
		}
		// the vertex will become bigger when mouse is on the field of it
		if((parent.mouseX <= x+20 && parent.mouseX >= x-20) && (parent.mouseY <= y+20 && parent.mouseY >= y-20)){
			parent.fill(r, g, b, 20);
			parent.ellipse(x, y, 50, 50);
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
