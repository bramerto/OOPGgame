package ArcadaShooter;

public class EasyEnemy extends Enemy {

	private final static int DAMAGE = 5;
	private final static float SPEED = 2;
	
	/**
	 * creates a enemy with easy difficulty
	 * @param world
	 */
	public EasyEnemy(ArcadaShooter world) {
		super(world, DAMAGE, SPEED, null);
	}
}
