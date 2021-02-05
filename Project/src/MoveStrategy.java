
import java.awt.Point;
import java.util.Random;

public interface MoveStrategy {
	public Point movePirateShip(Point pirateLocation, Point shipLocation);
	public String getStrategy();
	
	singletonMap oceanMap = singletonMap.getInstance();
	Random rand = new Random();
}
