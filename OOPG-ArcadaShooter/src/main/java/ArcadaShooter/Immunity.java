package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;


public class Immunity extends Pickup implements IAlarmListener {
    /**
     * Constructor
     * @param World where pickup spawns
     * @author Chris Buter
     */
	public Immunity(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_immunity.png"), world);
        setGravity(0.3f);
	}
	/**
     * Called when alarm is triggered
     * @param Alarm name
     * @author Chris Buter
     */
	@Override
	public void triggerAlarm(String alarmName) {	
		world.getPlayer().setImortal();
	}
	/**
     * Activate and remove pickup
     * @param Player that picks up the pickup
     * @author Chris Buter
     */
	@Override
	public void doAction(Player player) {
			Alarm alarm=new Alarm("Start Immortal",1/0.1);
	        alarm.addTarget(this);
	        alarm.start();
			player.isImmortal = true;

		world.deleteGameObject(this);
	}
	/**
     * Updates game object
     * @author Chris Buter
     */
	@Override
	public void update() {
	}
}
