package ArcadaShooter;

import java.awt.MouseInfo;
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
	
	public Bullet(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/bullet.png"));
		this.world = world;
		this.damage = 50;
	}
	
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Enemy) {
				((Enemy) g).receiveDamage(damage);
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
	}
	
	public float getAngle() {
		java.awt.Point target = MouseInfo.getPointerInfo().getLocation();
	    float angle = (float) Math.toDegrees(Math.atan2(target.y - y, target.x - x));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
}
