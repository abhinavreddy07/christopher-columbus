
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PirateShip implements Observer {

	Point currentLocation;
	static Point shipLocation;
	static ArrayList<Point> locations;
	ArrayList<Point> updated;
	Point og;
	Random rand = new Random();
	singletonMap oceanMap = singletonMap.getInstance();
	
	MoveStrategy moveStrategy = new EasyMove();
	String level;
	
	Image pirateImage;
	ImageView pirateIV;
	
		/* constructor takes an OceanMap as a parameter*/
	public PirateShip(String image, Point location){
		currentLocation = location;
		
		pirateImage = new Image(image,50,50,true,true);
		pirateIV = new ImageView(pirateImage);
	}
	//returns pirate imageView
	public ImageView getPirateImageView() {
		return pirateIV;

	}
	//sets the move strategy of pirates
	public void setStrategy(MoveStrategy strategy){
		moveStrategy = strategy;
	}
	/* updates pirateShip when ship moves*/
	public void update(Ship ship) {
		  shipLocation = ship.getCurrentLocation();
		 // System.out.println(shipLocation + " PirateShip update method");
		  movePirateShip();
	}
	
	/* moves pirate ship closer to ships location */
	public void movePirateShip(){
		//have if/if-else statements to check if user selected easy/normal/hard and then set the strategy accordingly
		level = OceanExplorer.getDifficulty();
		if (level=="Easy"){
			setStrategy(new EasyMove());	
		}
		else if(level=="Hard"){
			setStrategy(new HardMove());
		}
		else{
			setStrategy(new NormalMove());
		}
		 currentLocation = moveStrategy.movePirateShip(currentLocation, shipLocation);
	}
	 
	/*returns list of pirate locations*/
	public Point getCurrentLocation(){
		return currentLocation;
	}
	//set location of pirate
	public void setLocation(Point location) {
		currentLocation = location;
	}

}
	
	
	
	


