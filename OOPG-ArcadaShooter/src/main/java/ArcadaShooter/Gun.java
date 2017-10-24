package ArcadaShooter;

public class Gun extends Weapon{
	private Bullet[] bullets;
	boolean isEquipped;
	
	public Gun() {
		
	}
	
	public void shoot(Bullet[] bullets) {
		
	}
	
	public void doDamage() {
		int damage = 10;
	}
	
	public boolean isEquipped() {
		return isEquipped;
	}
	
	public void unEquip() {
		isEquipped = false;
	}
}
