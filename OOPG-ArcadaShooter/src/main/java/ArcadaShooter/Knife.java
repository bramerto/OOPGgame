package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Knife extends Weapon implements ICollidableWithGameObjects {
	private int damage;
	private int damageDelay;
    /**
     * Constructor
     * @param World The world where knife is created
     */
	public Knife(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_dagger.png"), world);
		setDamage();
	}
	/**
     * Attacks with knife
     * @param Gameobject from Player element
     * @param TargetX x coordinates for target
     * @param TargetY y coordinates for target
     */
	@Override
	public void doAction(GameObject from, float targetX, float targetY) {
		this.setX(this.getX() + 20);
	}
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Enemy) {
            	//TODO: When enemy shoots it collides with itself
            	if (damageDelay == 100 || damageDelay == 0) {
    				((Enemy)g).receiveDamage(damage);
    				damageDelay = 1;
    			}
    			damageDelay++;
            }
        }
    }
	/**
     * Resets damage to default
     */
	@Override
	public void setDamage() {
		this.damage = 10;
	}
	/**
     * Double damage from pickup
     */
	public void doubleDamage() {
		this.damage = damage *2;
	}
	
}
