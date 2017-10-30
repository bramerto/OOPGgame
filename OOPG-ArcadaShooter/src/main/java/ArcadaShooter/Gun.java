package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class Gun extends Weapon {
	private Bullet[] bullets;
	private Sound pickupSound;
	boolean isEquipped;
	
	public Gun(ArcadaShooter world, Sound pickupSound) {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_pistol.png"));
		this.pickupSound = pickupSound;
        this.world = world;
        setGravity(0.3f);
		int damage = 10;
	}
	
	public void shoot(Player player) {
		Bullet p = new Bullet();
		world.addGameObject(p, player.getX(),  player.getY());
	}
	
	public boolean isEquipped() {
		return isEquipped;
	}
	
	public void unEquip() {
		isEquipped = false;
	}
}
