package ArcadaShooter;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class PickupSpawner implements IAlarmListener {
	private float pickupsPerSecond;
    private Random random;
    private ArcadaShooter world;
    private Sound pickupSound;
    /**
     * Creates a pickup spawner that adds them to the game
     * @param world object to add item to
     * @param Sound for pick up
     * @param number of how fast the timer goes
     */
    public PickupSpawner(ArcadaShooter world,Sound pickupSound,float pickupsPerSecond) {
        this.pickupsPerSecond=pickupsPerSecond;
        this.world=world;
        this.pickupSound=pickupSound;
        random=new Random();
        startAlarm();
    }

    /**
     * Creates a new alarm element
     */
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
    	Pickup[] pickups = {new Healthbox(world, pickupSound), new Immunity(world, pickupSound), new Ammobox(world, pickupSound), new DoubleDamage(world, pickupSound)};
    	Random generator = new Random();
    	int randomIndex = generator.nextInt(pickups.length);
		Pickup healthbox=new Healthbox(world, pickupSound);
		Random rand = new Random();

		int  x = rand.nextInt(world.width) + 1;
		int y = rand.nextInt(world.height) + 1;
        world.addGameObject(pickups[randomIndex] ,x, y);
        startAlarm();
    }
}
