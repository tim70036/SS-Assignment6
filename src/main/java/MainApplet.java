package main.java;

import java.util.ArrayList;

import processing.core.PApplet;
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
	private Character test2 = new Character(this, "GOLD FIVE", 100,100,20,255,100);
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		
		networks = new ArrayList<Network>();
		files = new ArrayList<String>();
		for(int i=1 ; i<=7 ; i++)	files.add("starwars-episode-" + i + "-interactions.json");
		for(int i=0 ; i<7 ; i++)	networks.add(new Network(this));

		size(width, height);
		smooth();
		loadData();
		
		
		
		
	}

	public void draw() {
		background(255);
		networks.get(0).display();
		//test2.display();
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
		}
	}

}
