package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Weapon extends AnimatedSpriteObject {
	protected ArcadaShooter world;
	protected int damage;
	protected int previousDamage;
	
	/**
     * Constructor
     * @param Sprite object of weapon
     * @param World where the weapon is created
     * @author Chris Buter
     */
	public Weapon(Sprite sprite, ArcadaShooter world){
		super(sprite, 2);
		this.world = world;
	}
	/**
     * Check for collisions with game objects
     * @param List of all game objects
     * @author Chris Buter
     */
	public abstract void doAction(GameObject from, float targetX, float targetY);
	public void update() {}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
     * Double damage from pickup
     * @author Chris Buter
     */
	public void doubleDamage() {
		previousDamage = damage;
		damage = damage *2;
	}
	/**
	 * Resets damage of weapon initial damage value
	 * @author Bram van der Beek
	 */
	public void resetDamage() {
		damage = previousDamage;
	}
}
