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
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.waterworld.tiles.BoardsTile;
import processing.core.PVector;

public class Player extends SpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {

	private ArcadaShooter world;
	final int size = 25;
    private Random r;
    
	public Player(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
		this.world = world;
		setFriction(0.03f);
		setGravity(0.10f);
        this.r = new Random();
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
		// TODO Auto-generated method stub

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
        if (getX()>=world.getWidth()-size) {
            setxSpeed(0);
            setX(world.getWidth() - size);
        }
        if (getY()>=world.getHeight()-size) {
            setySpeed(0);
            setY(world.getHeight() - size);
        }
	}
	
	public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
        }
        if (keyCode == world.UP) {
            setDirectionSpeed(0, speed);
        }
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
        }
        if (keyCode == world.DOWN) {
            setDirectionSpeed(180, speed);
        }
        
        
        if (key == ' ') {
            System.out.println("Spatie!");
        }
    }
}
