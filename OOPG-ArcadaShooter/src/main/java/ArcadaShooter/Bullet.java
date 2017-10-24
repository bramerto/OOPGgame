package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.waterworld.Player;
import nl.han.ica.waterworld.Swordfish;

public class Bullet {
	private boolean collidedWithEnemy;
	private Bullet[] bullets;
	private boolean hasAmmo;
	
	public Bullet() {
		
	}
	
	public boolean hasAmmo() {
		return false;
	}
	
	public boolean collidedWithEnemy(int x, int y, Enemy enemy) {
		return false;
	}
	
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Enemy) {
            	//do zombie.health =- 10 or something like this.
            	System.out.println("Zombie Hit!");
                Player.score++;
            }
        }
    }
	
}
