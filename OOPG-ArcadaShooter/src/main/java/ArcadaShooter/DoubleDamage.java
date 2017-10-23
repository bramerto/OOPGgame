package ArcadaShooter;

public class DoubleDamage extends Pickup{
	public boolean isActive;
	public Weapon weapon;
	public int Duration = 30;
	
	public DoubleDamage(int duration, Weapon weapon){
		
	}
	@Override
	public void setActive() {
		isActive = true;
	}
	
	public void addDamage() {
		
	}
	@Override
	public void deactivate() {
		isActive = false;
	}
	@Override
	public boolean collidedWithPlayer() {
		return false;
	}
}
