package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class HardEnemy extends Enemy {
	
	public HardEnemy(ArcadaShooter world) {
		super(world, 20, 0.2f, new Gun(world));
	}
	
	@Override
	public void update() {
		this.target = world.getPlayer();
		double distance = Math.hypot(target.getX()-this.x, target.getY()-this.y);
		
		if (health <= 0) {
			EnemySpawner.currentEnemiesOnLevel--;
			world.deleteGameObject(this);
		}
		
		if (distance > 500) {
			if (target.getX() > this.x) {
				setDirectionSpeed(90, speed);
				setCurrentFrameIndex(0);
				
			} else if (target.getX() < this.x) {
				setDirectionSpeed(270, speed);
				setCurrentFrameIndex(1);
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
