package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Knife extends Weapon implements ICollidableWithGameObjects {
	private int damageDelay;
    /**
     * Constructor
     * @param World The world where knife is created
     * @param damage damage the knife has
     */
	public Knife(ArcadaShooter world, int damage) {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_dagger.png"), world);
		setDamage(damage);
	}
	
	/**
     * Constructor
     * @param World The world where knife is created
     */
	public Knife(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_dagger.png"), world);
		int defaultDamage = 20;
		setDamage(defaultDamage);
	}
	
	/**
     * Attacks with knife
     * @param Gameobject from Player element
     * @param TargetX x coordinates for target
     * @param TargetY y coordinates for target
     * @author Chris Buter
     */
	@Override
	public void doAction(GameObject from, float targetX, float targetY) {
		this.setX(this.getX() + 20);
	}
	
	/**
     * Check for collisions with game objects
     * @param List of all game objects
     * @author Chris Buter
     */
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Enemy) {
            	if (damageDelay == 100 || damageDelay == 0) {
    				((Enemy)g).receiveDamage(damage);
    				damageDelay = 1;
    			}
    			damageDelay++;
            }
        }
    }
}
