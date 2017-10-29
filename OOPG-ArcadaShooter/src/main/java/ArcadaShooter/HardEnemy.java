package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class HardEnemy extends Enemy {
	
	private Weapon weapon;
	
	public HardEnemy(ArcadaShooter world) {
		super(world, 20, 0.2f, new Gun());
	}
	
	@Override
	public void update() {
		this.target = world.getPlayer();
		double distance = Math.hypot(target.getX()-this.x, target.getY()-this.y);
		
		if (distance > 500) {
			if (target.getX() > this.x) {
				setDirectionSpeed(90, speed);
				setCurrentFrameIndex(0);
				
			} else if (target.getX() < this.x) {
				setDirectionSpeed(270, speed);
				setCurrentFrameIndex(1);
			}
		} else {
			setDirectionSpeed(0, 0);
			weapon.doAction(target);
		}
	}
}
