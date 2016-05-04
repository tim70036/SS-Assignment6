package main.java;

import java.util.ArrayList;

import controlP5.ControlP5;
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
	
	private boolean dragging = false;
	
	// Getter Setter
	public void setCircleDiameter(int r){circleDiameter = r;}
	public void setCircleX(int x){circleX = x;}
	public void setCircleY(int y){circleY = y;}
	public void addCharacters(Character ch) { characters.add(ch); }
	
	public int getCircleDiameter(){return circleDiameter;}
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
		this.circleDiameter = 450;
	}
	
	
	
	public void display(){
		parent.stroke(204,250,0);
		parent.strokeWeight(4);
		parent.fill(255);
		parent.ellipse(circleX,circleY,circleDiameter,circleDiameter);
		
		// Check and Change the state of character
		for(Character ch : characters)
		{
			if((parent.mouseX <= ch.getX()+20 && parent.mouseX >= ch.getX()-20) && (parent.mouseY <= ch.getY()+20 && parent.mouseY >= ch.getY()-20))
			{
				ch.setShowName(true);
				
				//  Mouse pressed
				if(parent.mousePressed == true)
				{
					// If it is the first click
					if(!dragging)
					{
						dragging = true;// Now the mouse is dragging , no one can be dragged
						ch.setBeDragged(true);// Only this character is being dragged
					}
				}
				// Mouse not pressed
				else
				{
					// This character is the one being dragged
					if(ch.getBeDragged())
					{
						// Been dragged to circle ---> add into circle
						if((ch.getX()-getCircleX())*(ch.getX()-getCircleX()) + (ch.getY()-getCircleY())*(ch.getY()-getCircleY())- getCircleDiameter()*getCircleDiameter()/4 < 0.01)
						{
							if(!getCharactersInCircle().contains(ch))
								addCharactersInCircle(ch);
						}
						// Been drag out of circle
						else
						{
							// Remove character
							removeCharactersInCircle(ch);
						}
						rearrangeCharactersIncricle();
					}
				
					// Reset
					dragging = false;// Now someone can be dragged
					ch.setBeDragged(false);;
				}
			}
			else
			{
				ch.setShowName(false);
			}
			
			ch.display(this);
		}
		

		// Show link
		drawLink();
	}
	
	public void setCharactersOGXY()
	{
		int ogx = 30,ogy = 50;
		for(Character ch : characters)
		{
			ch.setOGX(ogx);
			ch.setOGY(ogy);
			ch.setInOrigin(true);
			ogx += 80;
			if(ogx > 320)
			{
				ogy += 50;
				ogx = 30;
			}
		}
	}
	public void addCharactersInCircle(Character ch)
	{
		ch.setBeDragged(false);
		ch.setInOrigin(false);
		ch.setInCircle(true);
		
		charactersInCircle.add(ch);
		rearrangeCharactersIncricle();
	}
	
	public void addAllCharactersInCircle()
	{
		for(Character ch : characters)
			if(!this.charactersInCircle.contains(ch))
				this.addCharactersInCircle(ch);
		
	}
	
	public void removeCharactersInCircle(Character ch)
	{
		ch.setBeDragged(false);
		ch.setInOrigin(true);
		ch.setInCircle(false);
		
		charactersInCircle.remove(ch);
		rearrangeCharactersIncricle();
	}
	
	public void removeAllCharactersInCircle()
	{
		for(Character ch : characters)
			if(this.charactersInCircle.contains(ch))
				this.removeCharactersInCircle(ch);
	}
	
	public void rearrangeCharactersIncricle()
	{
		double pos = 0;
		for(Character cha : getCharactersInCircle()){
			cha.setCX((float)(getCircleX() + getCircleDiameter()/2*Math.cos(Math.toRadians(pos))));
			cha.setCY((float)(getCircleY() + getCircleDiameter()/2*Math.sin(Math.toRadians(pos))));
			pos += 360 / getCharactersInCircle().size();
		}
	}
	
	public void drawLink()
	{
		for(Character ch : charactersInCircle)
		{
			
		}
	}
}
