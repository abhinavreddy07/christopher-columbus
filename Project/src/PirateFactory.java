
import java.awt.Point;

public class PirateFactory {
	//create pirate based on pirate type and location
	public PirateShip createPirate(String type, Point location) {
		PirateShip pirate = null;
		if (type.equals("normal")) {
			pirate = new PirateShip("pirateship.png", location);
		}else if (type.equals("abnormal")) {
			pirate = new PirateShip("pirateship1.png", location);
		}
		return pirate;
	}
}
