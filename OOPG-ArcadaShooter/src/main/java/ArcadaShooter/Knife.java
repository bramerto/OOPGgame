package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Knife extends Weapon {
	private int damage;
	private boolean isEquipped;
	
	public Knife(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_dagger.png"), world);
		damage = 30;
	}

	@Override
	public void doAction(GameObject from, float targetX, float targetY) {
		
	}
}
