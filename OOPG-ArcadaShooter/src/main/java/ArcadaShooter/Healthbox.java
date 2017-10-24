package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class Healthbox extends Pickup{
	private boolean isActive;
	private Player player;
	
	public Healthbox(int x, int y, boolean isActive, Player player, int healthUp) {
		//x and y for the spawning coordinates
	}
	
	public void addHealth(Player player) {
		int health = 30;
	}
	@Override
	public void setActive() {
		isActive = true;
	}
	@Override
	public void deactivate() {
		isActive = false;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}
}
