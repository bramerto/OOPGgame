package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Weapon extends AnimatedSpriteObject {
	protected ArcadaShooter world;
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
	public abstract void setDamage();
	public abstract void doubleDamage();
}
