package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Gun extends Weapon {
	private float aimAngle;
	private int bulletDamage;
	
	public Gun(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_pistol.png"), world);
		setDamage();
	}
	/**
     * Attacks with Gun
     * @param Gameobject from Player element
     */
	private void shoot(GameObject from) {
		Bullet p = new Bullet(world, bulletDamage, from);
		world.addGameObject(p, from.getX() + 25,  from.getY() + 25);
		p.setDirectionSpeed(aimAngle, 20);
		
		if (from instanceof Player) {
        		((Player)from).setAmmo(((Player)from).getAmmo() - 1);
        }
		
		world.refreshDashboard();
	}
	
	/**
     * Trigger attack method
     * @param Gameobject from Player element
     * @param TargetX x coordinates for target
     * @param TargetY y coordinates for target
     */
	@Override
	public void doAction(GameObject from, float targetX, float targetY) {
		float dx = targetX - from.getX();
        float dy = targetY - from.getY();

        aimAngle = (dx >= 0 || dy >= 0) ? (float)Math.toDegrees(Math.atan2(dy, dx)) + 90 : (float)Math.toDegrees(Math.atan2(dy, dx)) + 450;
        shoot(from);
	}
	/**
     * Sets bullet damage
     */
	@Override
	public void setDamage() {
		this.bulletDamage = 10;
	}
	/**
     * Doubles bullet damage
     */
	@Override
	public void doubleDamage() {
		this.bulletDamage = bulletDamage*2;
		
	}
}
