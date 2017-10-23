package ArcadaShooter;

public class Gun extends Weapon{
	int x; 	//random coordinates voor spawn
	int y;
	Player player;
	int damage = 50;
	boolean isEquipped;
	
	public Gun() {
		
	}
	
	public void shoot(Bullet[] bullets) {
		
	}
	
	public void doDamage(int damage) {
		
	}
	
	public boolean isEquipped() {
		return isEquipped;
	}
	
	public void unEquip() {
		isEquipped = false;
	}
}
