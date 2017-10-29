package ArcadaShooter;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.waterworld.Bubble;
import nl.han.ica.waterworld.WaterWorld;

public class PickupSpawner implements IAlarmListener {
	private float pickupsPerSecond;
    private Random random;
    private ArcadaShooter world;
    private Sound pickupSound;
    
    public PickupSpawner(ArcadaShooter world,Sound pickupSound,float pickupsPerSecond) {
        this.pickupsPerSecond=pickupsPerSecond;
        this.world=world;
        this.pickupSound=pickupSound;
        random=new Random();
        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm=new Alarm("New bubble",1/pickupsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

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
