package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Knife extends Weapon {
	private int damage;
	private boolean isEquipped;
	
	public Knife() {
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
		damage = 30;
	}

	public void doDamage(Player player) {
		
	}
	
	public boolean isEquipped() {
		return false;
	}

}
