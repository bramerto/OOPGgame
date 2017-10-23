package ArcadaShooter;

public abstract class Pickup {
	boolean isActive;
	
	public abstract void setActive();
	
	public abstract void deactivate();
	
	public abstract boolean collidedWithPlayer();
}
