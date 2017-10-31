package ArcadaShooter;

import java.util.List;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Bullet extends SpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects{
	private ArcadaShooter world;
	private GameObject shotFrom;
	private int damage;
	/**
     * Constructor
     * @param World where the bullet spawns
     * @param Damage done with bullet
     * @param shotFrom Location where player is
     * @author Chris Buter
     */
	public Bullet(ArcadaShooter world, int damage, GameObject shotFrom) {
		super(new Sprite("src/main/java/ArcadaShooter/media/bullet.png"));
		this.shotFrom = shotFrom;
		this.world = world;
		setDamage(damage);
	}
	/**
     * Returns damage
     * @author Chris Buter
     */
	public int getDamage() {
		return damage;
	}
	/**
     * Sets Damage
     * @param Damage that bullet will do
     * @author Chris Buter
     */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	/**
     * Check for collisions with game objects
     * @param List of all game objects
     * @author Chris Buter
     */
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Enemy && !(shotFrom instanceof Enemy)) {
	        		((Enemy)g).receiveDamage(damage);
	        		world.deleteGameObject(this);
            }
            
            if (g instanceof Player && !(shotFrom instanceof Player)) {
	            	((Player)g).receiveDamage(damage);
	        		world.deleteGameObject(this);
            }
        }
    }
	/**
     * Check for collisions with tiles
     * @param List of all tiles
     * @author Chris Buter
     */
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		for (CollidedTile ct : collidedTiles) {
			if (ct.theTile instanceof NormalTile) {
        		if (ct.collisionSide == ct.TOP || ct.collisionSide == ct.INSIDE) {
	                try {
	                		world.deleteGameObject(this);
	                    
	                } catch (TileNotFoundException e) {
	                    e.printStackTrace();
	                }
        		}
            
        		if (ct.collisionSide == ct.BOTTOM) {
                    try {
                    		world.deleteGameObject(this);
		                
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
        		}
            
        		if (ct.collisionSide == ct.LEFT) {
                    try {
                    		world.deleteGameObject(this);
                    		
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
        		}
            
        		if (ct.collisionSide == ct.RIGHT) {
                    try {
                    		world.deleteGameObject(this);
                    		
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
        		}
            }
        }
	}
	@Override
	public void update() {
	}
}
