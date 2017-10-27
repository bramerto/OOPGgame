package ArcadaShooter;

public class Knife extends Weapon {
	private int damage;
	private boolean isEquipped;
	
	public Knife() {
		damage = 30;
	}

	public void doDamage(Player player) {
		
	}
	
	public boolean isEquipped() {
		return false;
	}

}
