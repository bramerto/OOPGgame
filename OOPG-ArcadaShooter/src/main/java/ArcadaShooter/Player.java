package ArcadaShooter;

import java.util.List;
import java.util.Random;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PVector;

public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {

	private ArcadaShooter world;
	private final int size = 50;
    private Random r;
    private Crosshair crosshair;
    private int health;
    
	public Player(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"), 2);
		this.world = world;
		setGravity(0.8f);
        this.r = new Random();
        this.health = 100;
        this.crosshair = new Crosshair(new Sprite("src/main/java/ArcadaShooter/media/crosshair.png"));
	}
	
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;

        for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof NormalTile) {
            	
                if (ct.collisionSide == ct.TOP) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setY(vector.y - getHeight());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                
                if (ct.collisionSide == ct.BOTTOM) {
                    try {
						vector = world.getTileMap().getTilePixelLocation(ct.theTile);
	    					setySpeed(0);
	    					setY(vector.y + 50);
		                
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
                }
                
                if (ct.collisionSide == ct.LEFT) {
                    try {
						vector = world.getTileMap().getTilePixelLocation(ct.theTile);
	    					setxSpeed(0);
	    					setX(vector.x - 50);
		                
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
                }
                
                if (ct.collisionSide == ct.RIGHT) {
                    try {
						vector = world.getTileMap().getTilePixelLocation(ct.theTile);
	    					setxSpeed(0);
	    					setX(vector.x + 50);
		                
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
                }
            }
        }
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g:collidedGameObjects) {
            if (g instanceof EasyEnemy) {
            	
            }
        }
	}

	@Override
	public void update() {
		if (getX()<=0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY()<=0) {
            setySpeed(0);
            setY(0);
        }
        if (getX()>=ArcadaShooter.WORLDWIDTH-size) {
            setxSpeed(0);
            setX(ArcadaShooter.WORLDWIDTH - size);
        }
        if (getY()>=ArcadaShooter.WORLDHEIGHT-size) {
            setySpeed(0);
            setY(ArcadaShooter.WORLDHEIGHT - size);
        }
	}
	
	public void keyPressed(int keyCode, char key) { //TODO: registering two keys
        final int speed = 5;
        if (key == 'a' || keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(1);
        }
        if (key == 'w' || keyCode == world.UP) {
            setDirectionSpeed(0, 20);
        }
        if (key == 'd' || keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(0);
        }
        if (key == ' ') {
            System.out.println("Spatie!");
        }
    }
	
	@Override
	public void mouseMoved(int x, int y) {
        world.addGameObject(crosshair, x, y);
        //set direction of weapon and bullet
    }
	
	public void mouseClicked() {
		// do action of equiped weapon
	}
}
