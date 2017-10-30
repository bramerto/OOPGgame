package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Knife extends Weapon {
	private int damage;
	private boolean isEquipped;
	
<<<<<<< HEAD
	public Knife() {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_dagger.png"));
=======
	public Knife(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_dagger.png"), world);
>>>>>>> a99251467a1a8295395f47ac75ca7d47f8a42616
		damage = 30;
	}

	@Override
	public void doAction(GameObject from, float targetX, float targetY) {
		
	}
}
