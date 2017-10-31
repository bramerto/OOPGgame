package ArcadaShooter;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class PickupSpawner implements IAlarmListener {
	private float pickupsPerSecond;
    private Random random;
    private ArcadaShooter world;
    /**
     * Constructor
     * @param world object to add item to
     * @param number of how fast the timer goes
     */
    public PickupSpawner(ArcadaShooter world,Sound pickupSound,float pickupsPerSecond) {
        this.pickupsPerSecond=pickupsPerSecond;
        this.world=world;
        random=new Random();
        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm=new Alarm("New pickup",1/pickupsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

    /**
     * Method call when alarm is triggered
     * @param Alarm name
     */
    @Override
    public void triggerAlarm(String alarmName) {
    	Pickup[] pickups = {new Healthbox(world), new Immunity(world), new Ammobox(world), new DoubleDamage(world)};
    	Random generator = new Random();
    	int randomIndex = generator.nextInt(pickups.length);
		Random rand = new Random();

		int  x = rand.nextInt(world.WORLDWIDTH) + 1;
		int y = rand.nextInt(world.WORLDHEIGHT) + 1;
        world.addGameObject(pickups[randomIndex] ,x, y);
        startAlarm();
    }
}
