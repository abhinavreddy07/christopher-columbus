
import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TreasureChest {
	private static TreasureChest chest;
	Image chestPic;
	ImageView chestView;
	singletonMap map;
	static Point coords;
	
	private TreasureChest() {
		chestPic = new Image("treasure.png",50,50,true,true);
		chestView = new ImageView(chestPic);
		map = singletonMap.getInstance();
		coords = map.getTreasureLocation();
	}
	//returns an instance of the treasure chest
	public static TreasureChest getInstance() {
		if (chest == null) {
			chest = new TreasureChest();
		}
		return chest;
	}
	//returns ImageView of treasure chest
	public ImageView getImage() {
		return chestView;
	}
	//returns X coordinate of treasure chest
	public int getX() {
		return coords.x;
	}
	//returns Y coordinate of treasure chest
	public int getY() {
		return coords.y;
	}
	//checks if a ship and the treasure chest are at the same point
	public boolean checkChest(Point ship) {
		//System.out.println("chest " + coords);
		return ship.equals(coords);
	}
	//sets chest to equal null
	public void destroy() {
		chest = null;
		map = null;
		coords = null;
	}
}
