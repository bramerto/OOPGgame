package ArcadaShooter;

import java.util.List;

import ArcadaShooter.tiles.NormalTile;
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
	protected Player target;
	protected float speed;
	protected int damageDelay, health;
	protected Weapon weapon;
	private int damage;
	private boolean jumped;
	 /**
	  * Creates an Array
	  * @param world
	  * @param damage
	  * @param speed
	  * @param weapon
	  * @author Bram van der Beek
	  */
	public Enemy(ArcadaShooter world, int damage, float speed, Weapon weapon) {
		super(new Sprite("src/main/java/ArcadaShooter/media/enemy.png"), 2);
		this.world = world;
		this.damage = damage;
		this.speed = speed;
		this.weapon = weapon;
		this.health = 100;
		this.jumped = false;
		damageDelay = 0;
		setGravity(0.8f);
		if (this.weapon != null) this.world.addGameObject(this.weapon, getX() + 35, getY() + 25);
	}
	
	/**
	 * checks collision with the tiles
	 * @author Bram van der Beek
	 * @param List<CollidedTile> collidedTiles
	 */
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		
		for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof NormalTile && ct.collisionSide == ct.TOP) {
                try {
                    vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                    setY(vector.y - getHeight());
                    jumped = false;
                    
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	/**
	 * checks collision with pickups and applies buff to player
	 * @author Bram van der Beek
	 * @param List<GameObject> collidedGameObjects
	 */
	@Override
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

	/**
	 * Enemy AI so it moves towards the player and checks if enemy is killed, also sets weapon frame of equiped weapon
	 * @author Bram van der Beek
	 */
	@Override
	public void update() {
		
		if (health <= 0) {
			EnemySpawner.currentEnemiesOnLevel--;
			world.deleteGameObject(this);
			if (weapon != null) world.deleteGameObject(weapon);
		}
		
		this.target = world.getPlayer();
		
		if (target.getY() < this.y && target.getX() > this.x && !jumped) {
			setDirectionSpeed(45, speed*4);
			setCurrentFrameIndex(0);
			setWeaponFrame(0);
			jumped = true;
			
		} else if (target.getY() < this.y && target.getX() < this.x && !jumped) {
			setDirectionSpeed(315, speed*4);
			setCurrentFrameIndex(1);
			setWeaponFrame(1);
			jumped = true;
			
		} else if (target.getX() > this.x && !jumped) {
			setDirectionSpeed(90, speed);
			setCurrentFrameIndex(0);
			setWeaponFrame(0);
			
		} else if (target.getX() < this.x && !jumped) {
			setDirectionSpeed(270, speed);
			setCurrentFrameIndex(1);
			setWeaponFrame(1);
		}
	}
	
	/**
	 * sets the right weapon frame index based on if the enemy is turned or not
	 * @param direction
	 * @author Bram van der Beek
	 */
	protected void setWeaponFrame(int direction) {
		if (weapon != null) {
			if (direction == 0) {
				weapon.setCurrentFrameIndex(0);
				weapon.setX(getX() + 35);
				weapon.setY(getY() + 25);
			} else {
				weapon.setCurrentFrameIndex(1);
				weapon.setX(getX() - 20);
				weapon.setY(getY() + 25);
			}
			
		}
	}
	/**
	 * lets the enemy receive damage
	 * @param damage
	 * @author Bram van der Beek
	 */
	public void receiveDamage(int damage) {
		health -= damage;
	}
}
