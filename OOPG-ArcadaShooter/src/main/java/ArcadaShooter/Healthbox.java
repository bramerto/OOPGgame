package ArcadaShooter;

public class Healthbox extends Pickup{
	public boolean isActive;
	public Player player;
	public int healthUp = 60;
	
	public Healthbox(int x, int y, boolean isActive, Player player, int healthUp) {
		//x and y for the spawning coordinates
	}
	
	public void addHealth(Player player) {
		
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
