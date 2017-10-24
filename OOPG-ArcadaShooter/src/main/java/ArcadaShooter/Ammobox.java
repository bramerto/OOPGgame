package ArcadaShooter;

public class Ammobox extends Pickup{
	public boolean isActive;
	public int ammoUp = 60;
	
	public Ammobox(int x, int y, boolean isActive, Player player, int ammoUp) {
		//x and y for the spawning coordinates
	}
	public void addAmmo(Player player) {
		// add ammo and deactivate
		deactivate();
	}
	@Override
	public boolean collidedWithPlayer() {
		return false;
	}
	@Override
	public void setActive() {
		isActive = true;
	}
	@Override
	public void deactivate() {
		isActive = false;
	}
}
