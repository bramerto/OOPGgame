package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class HardEnemy extends Enemy {
	
	private final static int DAMAGE = 10;
	private final static float SPEED = 0.2f;
	
	/**
	 * creates a enemy with hard difficulty
	 * @param world
	 */
	public HardEnemy(ArcadaShooter world) {
		super(world, DAMAGE, SPEED, new Gun(world, DAMAGE));
	}
	
	/**
	 * Enemy hard AI so it moves towards the player and checks if enemy is killed, also sets weapon frame of equiped weapon
	 * @author Bram van der Beek
	 */
	@Override
	public void update() {
		this.target = world.getPlayer();
		double distance = Math.hypot(target.getX()-this.x, target.getY()-this.y);
		
		if (health <= 0) {
			EnemySpawner.currentEnemiesOnLevel--;
			world.deleteGameObject(this);
			world.deleteGameObject(weapon);
		}
		
		if (distance > 500) {
			if (target.getX() > this.x) {
				setDirectionSpeed(90, speed);
				setCurrentFrameIndex(0);
				setWeaponFrame(0);
				
			} else if (target.getX() < this.x) {
				setDirectionSpeed(270, speed);
				setCurrentFrameIndex(1);
				setWeaponFrame(1);
			}
			
		} else {
			if (damageDelay == 100 || damageDelay == 0) {
				((Gun)weapon).doAction((GameObject)this, target.getX(), target.getY());
				damageDelay = 1;
			}
			damageDelay++;
			
			setDirectionSpeed(0, 0);
		}
	}
}
