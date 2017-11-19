package ArcadaShooter;

public class MediumEnemy extends Enemy {
	
	private static final int DAMAGE = 10;
	private static final float SPEED = 1;
	
	/**
	 * creates a enemy with medium difficulty
	 * @param world
	 */
	public MediumEnemy(ArcadaShooter world) {
		super(world, DAMAGE, SPEED, new Knife(world, DAMAGE));
	}
}
