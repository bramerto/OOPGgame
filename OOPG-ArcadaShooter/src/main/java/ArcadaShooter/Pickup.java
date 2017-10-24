package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public abstract class Pickup {
	boolean isActive;
	
	public abstract void setActive();
	
	public abstract void deactivate();
	
	public abstract void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects);
}
