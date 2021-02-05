import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship implements Subject{
	Point currentLocation;
	singletonMap map;
	Image shipPic;
	static ImageView shipview;
	
	List<Observer> observers = new LinkedList<Observer>();

	/* constructor take ocean map as constructor*/
	public Ship() {
		map = singletonMap.getInstance();
		currentLocation = map.getShipLocation();
		shipPic = new Image("ship.png",50,50,true,true);
		shipview = new ImageView(shipPic);
	}
	
	public static ImageView getImage(){
		return shipview;
	}
	
	/* returns point of ships location*/
	public Point getCurrentLocation(){
		//System.out.println(currentLocation);
		return currentLocation;
	}
	
	/* if the coordinate east of location is free, then move ship east
	 * notifies observers*/
	public void goEast(){
		if (currentLocation.x<map.getDimensionsX()-1 && map.isOcean(currentLocation.x+1, currentLocation.y)){
			currentLocation.x++;
			//notifyObservers();
		}
	}
	/* if the coordinate west of location is free, then move ship west
	 * notifies observers*/
	public void goWest(){
		if(currentLocation.x >0 && map.isOcean(currentLocation.x-1, currentLocation.y)){
	    		currentLocation.x--;
	    		//notifyObservers();
	    	}  		
		
	}
	/*if the coordinate north of location is free, then move ship north
	 * notifies observers*/
	public void goNorth(){
		if(currentLocation.y>0 && map.isOcean(currentLocation.x, currentLocation.y-1)){
    		currentLocation.y--;
    		//notifyObservers();
    	} 
	}
	/* if the coordinate south of location is free, then move ship south
	 * notifies observers*/
	public void goSouth(){
		if(currentLocation.y<map.getDimensionsY()-1 && map.isOcean(currentLocation.x, currentLocation.y+1)){
    		currentLocation.y++;
    		//notifyObservers();
    	}
	}
	
	// registers observer to subject => pirate ship to observingShip.
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	// removes observer from subject => removes pirateShip from observingShip
	public void removeObserver(Observer o) {
		if(observers.contains(o)){
			observers.remove(o);
		}
		
	}
	
	//removes all Observers of ship
	public void clearObservers() {
		for (Observer observer : observers) {
			removeObserver(observer);
		}
	}

	@Override
	/*notifies observers of subject movement => notifies pirate ship that ship moved*/
	public void notifyObservers() {
		for(Observer shipObserver:observers){
			shipObserver.update(this);
		}
	}
}
