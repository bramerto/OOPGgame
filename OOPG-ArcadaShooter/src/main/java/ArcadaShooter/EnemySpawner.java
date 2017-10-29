package ArcadaShooter;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class EnemySpawner implements IAlarmListener {
	
	private ArcadaShooter world;
	private float enemiesPerSecond;
	private int enemiesPerWave;
	private int wave;
	private int enemiesSpawned;
	private Random r;
	
	public EnemySpawner(ArcadaShooter world) {
		this.world = world;
		this.enemiesPerSecond = 0.5f;
		this.enemiesPerWave = 5;
		this.wave = 1;
		this.r = new Random();
		this.enemiesSpawned = 0;
		
		startAlarm();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		
		if (enemiesSpawned <= enemiesPerWave) {
			Enemy[] enemies = { new EasyEnemy(world), new MediumEnemy(world), new HardEnemy(world) };
			
	    	int randomIndex = r.nextInt(enemies.length);
	    	
	        world.addGameObject(enemies[randomIndex], 3000 - 50,  620);
	        
	        System.out.println("zombie spawned"); //debug
	        enemiesSpawned++;
	        
		} else { //TODO: check if enemies are not in the world
			System.out.println("new wave!"); //debug
			enemiesSpawned = 0;
			enemiesPerWave += 5;
			wave++;
			world.refreshDashboard();
		}
        
        startAlarm();
	}
	
	private void startAlarm() {
        Alarm alarm = new Alarm("New enemy", 1 / enemiesPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }
	
	public int getWave() {
		return wave;
	}
}
