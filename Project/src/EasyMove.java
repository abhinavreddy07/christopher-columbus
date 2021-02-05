
import java.awt.Point;

public class EasyMove implements MoveStrategy{

	Point og;
	Point change;
	
	private boolean checkTreasure(Point change) {
		if(TreasureChest.coords.x != change.x || TreasureChest.coords.y != change.y) {
			return true;
		}
		return false;
	}
	
	private boolean checkPirateShipX(Point currentPirate, Point change) {
		PirateShip pirate;
		
		if(currentPirate == OceanExplorer.pirate1.currentLocation) {
			pirate = OceanExplorer.pirate2;
			
		}
		else {
			pirate = OceanExplorer.pirate1;
		}
		
		if(change.x != pirate.currentLocation.x)
			return true;
		
		return false;
	}
	
	private boolean checkPirateShipY(Point currentPirate, Point change) {
		PirateShip pirate;
		
		if(currentPirate == OceanExplorer.pirate1.currentLocation) {
			pirate = OceanExplorer.pirate2;
			
		}
		else {
			pirate = OceanExplorer.pirate1;
		}
		
		if(change.y != pirate.currentLocation.y)
			return true;
		
		return false;
	}
	
	private boolean checkMonsters(Point change) {
		for (AreaOrMonster obj : OceanExplorer.areas) {			
			if(obj.getX() == change.x && obj.getY() == change.y)
				return false;
		}
		return true;
	}
	
	@Override
	//moves pirate ships slowly one in five chance that pirate ships will move
	public Point movePirateShip(Point pirate, Point shipLocation) {
			og = pirate;
			change = og;
			
			 if(rand.nextInt(5) == 1){ 
				 try{
					 if (change.x - shipLocation.x < 0 && oceanMap.isOcean(change.x+1, change.y) && checkTreasure(new Point(change.x+1, change.y)) && checkPirateShipX(pirate, new Point(change.x+1, change.y)) && checkMonsters(new Point(change.x+1, change.y))) {
						 change.x++;				 
					 }
					 else if(oceanMap.isOcean(change.x-1,change.y) && checkTreasure(new Point(change.x-1, change.y)) && checkPirateShipX(pirate, new Point(change.x-1, change.y)) && checkMonsters(new Point(change.x-1, change.y))) {
						 change.x--;
					 }
					 
					 if (change.y - shipLocation.y < 0 && oceanMap.isOcean(change.x,change.y+1) && checkTreasure(new Point(change.x, change.y+1)) && checkPirateShipY(pirate, new Point(change.x, change.y+1)) && checkMonsters(new Point(change.x, change.y+1))) {
						 change.y++;
					 }
					 else if(oceanMap.isOcean(change.x, change.y-1) && checkTreasure(new Point(change.x, change.y-1)) && checkPirateShipY(pirate, new Point(change.x, change.y-1)) && checkMonsters(new Point(change.x, change.y-1))) {
						 change.y--;
					 }
				 }
				 catch(ArrayIndexOutOfBoundsException e){
					 change = og;
				 }
			 
			 }
			 return change;
	}
	@Override
	//returns type of strategy
	public String getStrategy() {
		return "Easy Strategy";
	}
	

}
