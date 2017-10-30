package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Weapon extends AnimatedSpriteObject {
	protected ArcadaShooter world;
	
	public Weapon(Sprite sprite, ArcadaShooter world){
		super(sprite, 2);
		this.world = world;
	}

	public abstract void doAction(GameObject from, float targetX, float targetY);
	public void update() {
	}
}
