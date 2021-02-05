
import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Area implements AreaOrMonster {
	Point origin;
	ArrayList<AreaOrMonster> children;
	int size;
	Monster monster;
	
	ArrayList<ImageView> childImages = new ArrayList<ImageView>();
	
	public Area(Point origin) {
		this.origin = origin;
		children = new ArrayList<AreaOrMonster>();
		size = 3;
		
		monster = new Monster(origin);
		addChildren(monster);
	}
	
	
	public String toString() {
		return (origin.toString());
	}
//	moves all monster children in area
	@Override
	public void move() {
		for (AreaOrMonster child : children) {
			child.move();
		}

	}
	// adds children to the area
	@Override
	public void addChildren(AreaOrMonster obj) {
		children.add(obj);

	}
	// removes children
	@Override
	public void removeChildren(AreaOrMonster obj) {
		// TODO Auto-generated method stub
		children.remove(obj);

	}
	//returns x coordinate of child
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		for (AreaOrMonster child : children) {
			return child.getX();
		}
		return 0;
	}
	//returns y coordinate
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return origin.y;
	}
	//returns the image view of child
	public ImageView getImageView() {
		return children.get(0).getImageView();
	}
	//ImageView list for children
	public ArrayList<ImageView> getImageList(){
		return childImages;
	}
	//list of different areas
	public ArrayList<AreaOrMonster> getChildren(){
		return children;
	}

	@Override
	//compares ship points
	public boolean checkShip(Point ship) {
		// TODO Auto-generated method stub
		return ship.equals(origin);
	}
	
	@Override
	public boolean getLeftRight() {
		return false;
	}

	@Override
	public int getOffset() {
		return 0;
	}
}
