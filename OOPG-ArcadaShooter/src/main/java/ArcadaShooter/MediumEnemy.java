package ArcadaShooter;

public class MediumEnemy extends Enemy {
	
	public MediumEnemy(ArcadaShooter world) {
		super(world, 10, 1, new Knife(world));
	}
}
