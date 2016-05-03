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
	private boolean portable = false;
	private long theFirst = 0;
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

	public void display(boolean can, Network net){
		portable = can;
		parent.stroke(255);
		parent.strokeWeight(1);
		// When mouse is on , show the name and bigger ellipse, if pressed move the ellipse
		if((parent.mouseX <= x+20 && parent.mouseX >= x-20) && (parent.mouseY <= y+20 && parent.mouseY >= y-20)){
			// Bigger ellipse
			parent.fill(r, g, b, 80);
			parent.ellipse(x, y, 50, 50);
			
			// Show name
			parent.fill(30,144,255,150);
			float textWidth = (name.length() > 5) ? name.length() * 14.5f : 80;
			parent.rect(parent.mouseX, parent.mouseY - 17, textWidth,35,40);
			parent.fill(255);
			parent.textSize(20);
			parent.text(name, parent.mouseX + 10, parent.mouseY + 7.5f);
			
			//  Mouse pressed ? move with mouse
			if(parent.mousePressed == true){
				// the first one be clicked can start to add first, and can have the biggest theFirst
				theFirst++;
				if(portable){
					x = parent.mouseX;
					y = parent.mouseY;
				}
			}
			// mouse is not pressed, go back to the initial position
			else{
				// drag to circle
				if((x-net.getCircleX())*(x-net.getCircleX()) + (y-net.getCircleY())*(y-net.getCircleY())
					- net.getCircleDiameter()*net.getCircleDiameter()/4 < 0.1){
					theFirst = 0;
					if(!net.getCharactersInCircle().contains(this)){
						net.addCharactersInCircle(this);
					}
				}
				else{
					theFirst = 0;
					x = ogx;
					y = ogy;
				}
			}
		}
		// the mouse is out of the ellipse, go back to the initial position
		else{
			if(!net.getCharactersInCircle().contains(this)){
				theFirst = 0;
				x = ogx;
				y = ogy;
			}
		}
		
		
		// Draw the smaller ellipse
		parent.fill(r, g, b, 80);
		parent.ellipse(x, y, 40, 40);
		parent.fill(255);
		// determine whether in circle 
//		if(showLink){
//			for(Character key : targets.keySet()){
//				parent.stroke(0);
//				parent.strokeWeight(targets.get(key));
//				parent.curve(x, y, (600+x)/2, (350+y)/2, (600+key.getX())/2, (350+key.getY())/2, key.getX(), key.getY());
//			}
//			parent.strokeWeight(1);
//		}
	}
	
	// getter & setter //
	public long getFirst(){
		return this.theFirst;
	}
	
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
