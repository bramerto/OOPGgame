package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class Weapon extends Pickup{
	private Player player;
	
	public Weapon(){
		
	}
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Player) {
                
            }
        }
    }
	@Override
	public void setActive() {
		isActive = true;
	}
	@Override
	public void deactivate() {
		isActive = false;
	}
}

