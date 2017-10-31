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
	private int damage;
	
	public Bullet(ArcadaShooter world, int damage) {
		super(new Sprite("src/main/java/ArcadaShooter/media/bullet.png"));
		this.world = world;
		setDamage(damage);
	}
	/**
     * Getter and Setter for damage
     */
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Enemy) {
            	//TODO: When enemy shoots it collides with itself
        		((Enemy)g).receiveDamage(damage);
        		world.deleteGameObject(this);
            }
        }
    }

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
		// TODO Auto-generated method stub
		
	}
}
