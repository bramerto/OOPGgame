package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Knife extends Weapon {
	private static int damage;
	private boolean isEquipped;
	
	public Knife(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_dagger.png"), world);
		damage = 30;
	}

	@Override
	public void doAction(GameObject from, float targetX, float targetY) {
		this.setDirection(100);
	}
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Enemy) {
            		((Enemy) g).receiveDamage(damage);
            		world.deleteGameObject(this);
            }
        }
    }
	public static void resetDamage() {
		damage = 10;
	}
	public static void doDoubleDamage() {
		damage = damage *2;
	}
}
