package ArcadaShooter;

public class Gun extends Weapon {
	private Bullet[] bullets;
	boolean isEquipped;
	
	public Gun() {
		int damage = 10;
	}
	
	public void shoot(Bullet[] bullets) {
		
	}
	
	public boolean isEquipped() {
		return isEquipped;
	}
	
	public void unEquip() {
		isEquipped = false;
	}
}
