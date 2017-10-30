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
	private int health;
	private boolean jumped;
	
	public Enemy(ArcadaShooter world, int damage, float speed, Weapon weapon) {
		this(new Sprite("src/main/java/ArcadaShooter/media/enemy.png"));
		this.world = world;
		this.damage = damage;
		this.speed = speed;
		this.weapon = weapon;
		this.health = 100;
		this.jumped = false;
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
		
		if (health <= 0) {
			EnemySpawner.currentEnemiesOnLevel--;
			world.deleteGameObject(this);
		}
		
		this.target = world.getPlayer();
		
		if (target.getY() > this.y && target.getX() > this.x && !jumped) {
			setDirectionSpeed(45, speed);
			setCurrentFrameIndex(0);
			jumped = true;
			
		} else if (target.getY() > this.y && target.getX() < this.x && !jumped) {
			setDirectionSpeed(315, speed);
			setCurrentFrameIndex(1);
			jumped = true;
			
		} else if (target.getX() > this.x) {
			setDirectionSpeed(90, speed);
			setCurrentFrameIndex(0);
			
		} else if (target.getX() < this.x) {
			setDirectionSpeed(270, speed);
			setCurrentFrameIndex(1);
		}
	}
	
	public void receiveDamage(int damage) {
		health -= damage;
	}
}
