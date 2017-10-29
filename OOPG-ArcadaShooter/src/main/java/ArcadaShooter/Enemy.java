package ArcadaShooter;

import java.util.List;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PVector;

public class Enemy extends AnimatedSpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {

	protected ArcadaShooter world;
	private int damage;
	protected Player target;
	protected float speed;
	private int damageDelay;
	protected Weapon weapon;
	
	public Enemy(ArcadaShooter world, int damage, float speed, Weapon weapon) {
		this(new Sprite("src/main/java/ArcadaShooter/media/enemy.png"));
		this.world = world;
		this.damage = damage;
		this.speed = speed;
		this.weapon = weapon;
		damageDelay = 0;
		setGravity(0.8f);
	}
	
	
	private Enemy(Sprite sprite) {
		super(sprite, 2);
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		
		for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof NormalTile) {
            	
                if (ct.collisionSide == ct.TOP || ct.collisionSide == ct.INSIDE) {
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
	
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g:collidedGameObjects) {
			if (g instanceof Player) {
				
    			if (damageDelay == 100 || damageDelay == 0) {
    				((Player)g).receiveDamage(damage);
    				damageDelay = 1;
    			}
    			damageDelay++;
			}
		}
	}

	@Override
	public void update() {
		this.target = world.getPlayer();
		
		if (target.getY() > this.y && target.getX() > this.x) { //TODO: make enemies jump??
			setDirectionSpeed(45, speed);
			setCurrentFrameIndex(0);
			
		} else if (target.getY() > this.y && target.getX() < this.x) { //TODO: make enemies jump??
			setDirectionSpeed(315, speed);
			setCurrentFrameIndex(1);
			
		} else if (target.getX() > this.x) {
			setDirectionSpeed(90, speed);
			setCurrentFrameIndex(0);
			
		} else if (target.getX() < this.x) {
			setDirectionSpeed(270, speed);
			setCurrentFrameIndex(1);
		}
	}
}
