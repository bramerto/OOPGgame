package ArcadaShooter;

public class Weapon extends Pickup{
	int damage;
	public boolean isActive;
	
	public Weapon(){
		
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

