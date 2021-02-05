import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

class ShipTest extends Application {

	ArrayList<ImageView> monsterImageViews;
	Image shipImage;
	ImageView shipImageView;
	ImageView shiponeImageView;
	ImageView shiptwoImageView;
	ImageView winIV;
	ImageView islandIV;
	Image monsPic;
    ImageView monsView;
	
	@SuppressWarnings("static-access")
	@Test
	void testGetImage() {
		singletonMap oceanMap = singletonMap.getInstance();
		oceanMap.setDimensionX(20);
		oceanMap.setDimensionY(50);
		ImageView shipImageView = new ImageView();

		Ship ship = new Ship();
		if(ship.getImage().equals(shipImageView))
			fail("Ship.getView() not returning correct view");
	}

	@Test
	void testGetLocation() {
		singletonMap oceanMap = singletonMap.getInstance();
		oceanMap.setDimensionX(20);
		oceanMap.setDimensionY(50);
		
		Ship ship = new Ship();
		if(oceanMap.getShipLocation() != ship.getCurrentLocation())
			fail("Location of Ships aren't equalling up");
	}

	@Test
	void testGoWest() {
		singletonMap oceanMap = singletonMap.getInstance();
		oceanMap.setDimensionX(20);
		oceanMap.setDimensionY(50);
		
		Ship ship = new Ship();
		Point shipPoint = ship.getCurrentLocation();
		
		int newStateX = oceanMap.getDimensionsX() - 1;
		
		ship.goWest();
		if(newStateX==0){
			if(!ship.getCurrentLocation().equals(new Point((int)shipPoint.getX()-1, (int)shipPoint.getY()))){
				fail("Ship Should move West");
			}
		}else{
			if(!ship.getCurrentLocation().equals(shipPoint)){
				fail("Ship Shouldn't move West");
			}
		}

	}

	@Test
	void testGoEast() {
		singletonMap oceanMap = singletonMap.getInstance();
		oceanMap.setDimensionX(20);
		oceanMap.setDimensionY(50);
		
		Ship ship = new Ship();
		Point shipPoint = ship.getCurrentLocation();
		
		int newStateX = oceanMap.getDimensionsX() + 1;
	
		ship.goEast();
		if(newStateX < 20){
			if(!ship.getCurrentLocation().equals(new Point((int)shipPoint.getX()+1, (int)shipPoint.getY()))){
				fail("Ship Should move East");
			}
		}else{
			if(!ship.getCurrentLocation().equals(shipPoint)){
				fail("Ship Shouldn't move East");
			}
		}
	}

	@Test
	void testGoNorth() {
		singletonMap oceanMap = singletonMap.getInstance();
		oceanMap.setDimensionX(20);
		oceanMap.setDimensionY(50);
		
		Ship ship = new Ship();
		Point shipPoint = ship.getCurrentLocation();
		
		int newStateY = oceanMap.getDimensionsY() - 1;
		
		if(newStateY == 0){
			if(!ship.getCurrentLocation().equals(new Point((int)shipPoint.getX(), (int)shipPoint.getY()-1))){
				fail("Ship Should move North");
			}
		}else{
			if(!ship.getCurrentLocation().equals(shipPoint)){
				fail("Ship Shouldn't move North");
			}
		}
	}

	@Test
	void testGoSouth() {
		singletonMap oceanMap = singletonMap.getInstance();
		oceanMap.setDimensionX(20);
		oceanMap.setDimensionY(50);
		
		Ship ship = new Ship();
		Point shipPoint = ship.getCurrentLocation();
		
		int newStateY = oceanMap.getDimensionsY() + 1;
		
		if(newStateY < 20){
			if(!ship.getCurrentLocation().equals(new Point((int)shipPoint.getX(), (int)shipPoint.getY()+1))){
				fail("Ship Should move South");
			}
		}else{
			if(!ship.getCurrentLocation().equals(shipPoint)){
				fail("Ship Shouldn't move South");
			}
		}	
	}

	@Override
	public void start(Stage arg0) throws Exception {
	    monsPic = new Image("monster.png", 55, 55, true, true);
        monsView = new ImageView(monsPic);
		
	}

}
