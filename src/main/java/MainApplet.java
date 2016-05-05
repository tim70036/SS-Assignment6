package main.java;

import java.util.ArrayList;

import controlP5.Button;
import controlP5.CColor;
import controlP5.ControlFont;
import controlP5.ControlP5;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.*;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private ArrayList<String> files;
	
	private ArrayList<Network> networks;
	private Network curNetwork;
	private int episode;
	private PFont font;
	
	private ControlP5 cp5;
	private Button addAll;
	private Button clear;
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		
		size(width, height);
		
		// Data
		networks = new ArrayList<Network>();
		files = new ArrayList<String>();
		for(int i=1 ; i<=7 ; i++)	files.add("starwars-episode-" + i + "-interactions.json");
		for(int i=0 ; i<7 ; i++)	networks.add(new Network(this));
		loadData();
		curNetwork = networks.get(0);
		episode = 1;
		font = createFont("Arial", 50);
		
		
		// UI Setting
		cp5 = new ControlP5(this);
		
		cp5.setColorBackground( color(30,144,255) );
		addAll = cp5.addButton("addAll") .setLabel("Add All").setPosition(900, 100) .setSize(200, 50);
		addAll.getCaptionLabel().setFont(new ControlFont(createFont("Arial",16)));
		clear = cp5.addButton("clear") .setLabel("Clear").setPosition(900, 200) .setSize(200, 50);
		clear.getCaptionLabel().setFont(new ControlFont(createFont("Arial",16)));
		
		// Ani
		Ani.init(this);
		
		smooth();
		
		
	}

	public void draw() {
		background(255);
		curNetwork.display();
		
		
		fill(255,165,0);
		
		textFont(font);
		text("Star Wars " + episode,470,70);
	}
	
	public void keyPressed()
	{
		if(key >= '1' && key <= '7')	
		{
			curNetwork = networks.get(key - '1');
			episode = key - '0';
		}
	}
	
	public void addAll()
	{
		curNetwork.addAllCharactersInCircle();
	}
	
	public void clear()
	{
		curNetwork.removeAllCharactersInCircle();
	}
	
	private void loadData(){
		
		// For each episode
		for(int i=0 ; i<7 ; i++)
		{
			Network curNetwork = networks.get(i);
			// Load Data
			JSONObject data = loadJSONObject(path + files.get(i));
			JSONArray nodes = data.getJSONArray("nodes");
			JSONArray links = data.getJSONArray("links");
			//System.out.println(files.get(i));
			// Run Through nodes
			for(int j=0 ; j<nodes.size() ; j++)
			{
				JSONObject node = nodes.getJSONObject(j);
				String name = node.getString("name");
				int r = Integer.parseInt(node.getString("colour").substring(3,5) , 16);
				int g = Integer.parseInt(node.getString("colour").substring(5,7) , 16);
				int b = Integer.parseInt(node.getString("colour").substring(7,9) , 16);
				//System.out.println(r + " " + g + " " + b);
				Character ch = new Character(this,name,0,0,r,g,b);
				
				// Add into corresponding network
				curNetwork.addCharacters(ch);
			}
			// Arrange original x,y in that network
			curNetwork.setCharactersOGXY();
			
			// Run through links
			for(int j=0 ; j<links.size() ; j++)
			{
				JSONObject link = links.getJSONObject(j);
				int source = link.getInt("source");
				int target = link.getInt("target");
				Integer value = link.getInt("value");
				
				// Add link into Source char 
				Character src = curNetwork.getCharacters().get(source);
				src.addTarget(curNetwork.getCharacters().get(target), value);
			}
		}
	}

}
