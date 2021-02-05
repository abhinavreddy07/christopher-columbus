import java.awt.Point;

public class OceanMap {
	private static OceanMap oceanMapInstance;
	static boolean[][] oceanMap = new boolean[10][10];	//Initializing the grid size
	int xCell;
	int yCell;
	
	public void initiate(int a, int b) {
		xCell = a;
		yCell = b;
	}

	public int getDimensionX() {
		return xCell;
	}
	
	public int getDimensionY() {
		return yCell;
	}
	
	public static OceanMap getInstance(){
		if(oceanMapInstance == null){
			oceanMapInstance = new OceanMap();
		}
		return oceanMapInstance;
	}
	
	public boolean[][] getMap(){
		return oceanMap;
	}
	
	public Point getShipLocation() {
		return new Point(xCell,yCell);
	}
}