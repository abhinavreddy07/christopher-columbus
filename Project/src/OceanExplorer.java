import java.awt.Point;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application {

	boolean[][] islandMap;
	boolean done;
	Pane root;
	final int scalingFactor = 50;

	ArrayList<ImageView> monsterImageViews;

	Image shipImage;
	ImageView shipImageView;
	ImageView shiponeImageView;
	ImageView shiptwoImageView;

	ImageView winIV;
	ImageView islandIV;

	singletonMap map;
	Scene scene;
	Ship ship;

	static PirateShip pirate1;
	static PirateShip pirate2;

	static TreasureChest chest;

	ArrayList<PirateShip> pirates = new ArrayList<PirateShip>();
	static ArrayList<Area> areas;

	Button button;
	Button b1;
	Button b2;
	Button b3;
	static String diff;

	PirateFactory factory;

	//Main Method
	public static void main(String[] args) {
		launch(args); //launching  javafx
	}

	// overriding start method 
	@Override
	public void start(Stage mapStage) throws Exception {
		map = singletonMap.getInstance(); //singleton design 
		islandMap = map.getMap();	//getting the map from singleton class

		root = new AnchorPane();
		done = false;
		drawMap(); //calling the method to draw the map

		ship = new Ship(); //ship class object

		factory = new PirateFactory();	// pirate ship factory  class object

		pirates.add(pirate1 = factory.createPirate("normal", map.giveCoordinates())); // creating pirate ship1 using factory class
		pirates.add(pirate2 = factory.createPirate("abnormal", map.giveCoordinates())); // creating pirate ship2 using factory class

		observerStuff(); //calling method to add observers
		loadPirates();	//loading the pirate ship images
		loadTreasure();	//loading the treasure chest image 
		loadAreas();	//loading the monster images
		loadShipImage(); //loading the columbus ship

		mapStage.setTitle("Columbus Game");
		scene = new Scene(root, 1000, 800);
		mapStage.setScene(scene);
		mapStage.show();

		button = new Button("Reset"); //creating the Reset button
		
		button.setLayoutY(750); //adding the button to the layout

		button.setOnAction(new EventHandler<ActionEvent>() {	//Action to be performed when the button is clicked
			@Override
			public void handle(ActionEvent ke) {
				try {
					singletonMap.destroy();
					chest.destroy();
					ship.clearObservers();
					nuclear();
					start(mapStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		addButtons();	//calling methos to adding the buttons easy, normal and hard
		startSailing();	//calling method to stat the ship sailing

	}

	// Add buttons to JavaFX
	private void addButtons() {
		root.getChildren().add(button); // creating the Easy button 
		b1 = new Button("Easy");
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ke) {
				diff = "Easy";
			}
		});
		root.getChildren().add(b1);  // creating the Normal button
		b2 = new Button("Normal");
		b2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ke) {
				diff = "Normal";
			}
		});
		b2.setLayoutX(50);
		root.getChildren().add(b2);  // creating the Hard button
		b3 = new Button("Hard");
		b3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ke) {
				diff = "Hard";
			}
		});
		b3.setLayoutX(115);
		root.getChildren().add(b3);
	}

	// Clears pirate Statistics
	private void nuclear() {
		pirates.clear();
		pirate1 = null;
		pirate2 = null;
		map = null;
		singletonMap.destroy();
	}

	// Registers all pirate as Observers
	private void observerStuff() {
		for (PirateShip pirate : pirates) {
			ship.registerObserver(pirate);
		}
	}

	//Loads the ship images 
	private void loadShipImage() {
		shipImageView = Ship.getImage();
		shipImageView.setX(ship.getCurrentLocation().x * scalingFactor);
		shipImageView.setY(ship.getCurrentLocation().y * scalingFactor);

		root.getChildren().add(shipImageView);

	}
	//Loads the pirate ships
	private void loadPirates() {
		shiponeImageView = pirate1.getPirateImageView();
		shiponeImageView.setX(pirate1.getCurrentLocation().x * scalingFactor);
		shiponeImageView.setY(pirate1.getCurrentLocation().y * scalingFactor);
		root.getChildren().add(shiponeImageView);

		shiptwoImageView = pirate2.getPirateImageView();
		shiptwoImageView.setX(pirate2.getCurrentLocation().x * scalingFactor);
		shiptwoImageView.setY(pirate2.getCurrentLocation().y * scalingFactor);
		root.getChildren().add(shiptwoImageView);

	}
	//Loads the treasure chest
	private void loadTreasure() {
		chest = TreasureChest.getInstance();
		ImageView chestView = chest.getImage();
		chestView.setX(chest.getX() * scalingFactor);
		chestView.setY(chest.getY() * scalingFactor);

		root.getChildren().add(chestView);
	}

	// adds the imageViews of the children in the area
	private void loadAreas() {
		areas = map.getAreas();
		for (Area area : areas) {
			for (AreaOrMonster monster : area.getChildren()) {
				monster.getImageView().setX(monster.getX() * scalingFactor);
				monster.getImageView().setY(monster.getY() * scalingFactor);
				root.getChildren().add(monster.getImageView());
			}
		}
	}

	public void nuclearOption() {
		pirate1 = null;
		pirate2 = null;
	}

	// win condition and imageView
	private void youWin() {
		Image win = new Image("win.png", 1000, 0, true, true);
		ImageView winIV = new ImageView(win);
		winIV.setX(0);
		winIV.setY(500);
		root.getChildren().add(winIV);
	}
	
	// lose condition and imageView
	private void youLose() {
		Image lose = new Image("lose.png", 1000, 0, true, true);
		ImageView loseIV = new ImageView(lose);
		loseIV.setX(0);
		loseIV.setY(300);
		root.getChildren().add(loseIV);
	}
	
	// updates locations of pirate ImageViews
	private void movePirates() {
		shiponeImageView.setX(pirate1.getCurrentLocation().x * scalingFactor);
		shiponeImageView.setY(pirate1.getCurrentLocation().y * scalingFactor);

		shiptwoImageView.setX(pirate2.getCurrentLocation().x * scalingFactor);
		shiptwoImageView.setY(pirate2.getCurrentLocation().y * scalingFactor);
	}

	// updates location of ship ImageView
	private void movePlayer() {
		shipImageView.setX(ship.getCurrentLocation().x * scalingFactor);
		shipImageView.setY(ship.getCurrentLocation().y * scalingFactor);
		ship.notifyObservers();
	}
	
	// updates location of monster ImageView
	private void moveMonsters() {
		for (AreaOrMonster obj : areas) {
			if(obj.getLeftRight()) {
				if(!pirate1.currentLocation.equals(new Point(obj.getX()+1, obj.getY())) && !pirate2.currentLocation.equals(new Point(obj.getX()+1, obj.getY()))) {
					obj.move();
					obj.getImageView().setX(obj.getX() * scalingFactor);
				}
			}
			else {
				if(!pirate1.currentLocation.equals(new Point(obj.getX()-1, obj.getY())) && !pirate2.currentLocation.equals(new Point(obj.getX()-1, obj.getY()))) {
					obj.move();
					obj.getImageView().setX(obj.getX() * scalingFactor);
				}
			}
		}
	}

	private void startSailing() {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
				if (!done) {
					switch (ke.getCode()) {
					case D:
						ship.goEast();
						moveMonsters(); // calling the method to update the imageview
						map.setShipLocation(ship.getCurrentLocation()); // updates the location in the singleton class
						movePlayer();	// calling the method to update the imageview
						movePirates();  // calling the method to update the imageview
						break;
					case A:
						ship.goWest();
						moveMonsters(); // calling the method to update the imageview
						map.setShipLocation(ship.getCurrentLocation()); // updates the location in the singleton class
						movePlayer(); // calling the method to update the imageview
						movePirates(); // calling the method to update the imageview
						break;
					case W:
						ship.goNorth();
						moveMonsters();// calling the method to update the imageview
						map.setShipLocation(ship.getCurrentLocation()); // updates the location in the singleton class
						movePlayer();// calling the method to update the imageview
						movePirates();// calling the method to update the imageview
						break;
					case S:
						ship.goSouth();
						moveMonsters(); // calling the method to update the imageview
						movePlayer(); // calling the method to update the imageview
						map.setShipLocation(ship.getCurrentLocation()); // updates the location in the singleton class
						movePirates(); // calling the method to update the imageview
						break;
					default:
						break;
					}
				}
				if (!done) {
					if (pirate1.getCurrentLocation().equals(ship.getCurrentLocation()) //checking if the pirate ship and columbus ship locations are same
							|| pirate2.getCurrentLocation().equals(ship.getCurrentLocation())) {
						youLose(); //if same lose method is called
						done = true;
					}

					if (chest.checkChest(ship.getCurrentLocation())) {
						youWin(); //if columbus ship and treasure chest location are same win method is called
						done = true;
					}

					for (Area area : areas) { //checking if the columbus ship is a child of monsters 
						for (AreaOrMonster monster : area.getChildren()) {
							if (monster.checkShip(ship.getCurrentLocation())) {
								youLose(); // if the columbus ship is the child lose method is called
								done = true;
							}
						}
					}
				}
			}
		});
	}
	
	//Draws the map on the ocean
	public void drawMap() {
		for (int x = 0; x < map.dimensionsx; x++) {
			for (int y = 0; y < map.dimensionsy; y++) {
				Rectangle rect = new Rectangle(x * scalingFactor, y * scalingFactor, scalingFactor, scalingFactor);
				rect.setStroke(Color.BLACK);
				if (islandMap[x][y]) {
					Image isl = new Image("island.jpg", 50, 50, true, true);
					islandIV = new ImageView(isl);
					islandIV.setX(x * scalingFactor);
					islandIV.setY(y * scalingFactor);
					root.getChildren().add(islandIV);
				} else {
					rect.setFill(Color.PALETURQUOISE);
					root.getChildren().add(rect);
				}
			}
		}
	}

	// Get Difficulty for game(Easy, Normal, Hard)
	public static String getDifficulty() {
		return diff;
	}
}
