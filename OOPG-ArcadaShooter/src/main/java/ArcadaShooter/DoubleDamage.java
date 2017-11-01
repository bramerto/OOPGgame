package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class DoubleDamage extends Pickup implements IAlarmListener {
    /**
     * Constructor
     * @param World The world where knife is created
     */
	public DoubleDamage(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_doubledamage.png"), world);
        setGravity(0.3f);
	}
	
	/**
     * Alarm triggered
     * @param Alarm name
     */
	@Override
	public void triggerAlarm(String alarmName) {
		world.getPlayer().selectedWeapon.resetDamage();
	}
	
	/**
     * Activate pickup
     * @param Player element
     */
	@Override
	public void doAction(Player player) {
		Alarm alarm=new Alarm("Start Double Damage",1/0.1);
        alarm.addTarget(this);
        alarm.start();
        
        world.getPlayer().selectedWeapon.doubleDamage();
		world.deleteGameObject(this);
	}
	
	@Override
	public void update() {
	}
}
