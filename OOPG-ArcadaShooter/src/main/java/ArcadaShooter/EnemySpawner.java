package ArcadaShooter;

import java.util.Random;
import java.util.Vector;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class EnemySpawner implements IAlarmListener {
	
	private ArcadaShooter world;
	private float enemiesPerSecond;
	private int enemiesPerWave;
	private int wave;
	private boolean waveIsSpawned;
	private Random r;
	public static int currentEnemiesOnLevel;
	
	public EnemySpawner(ArcadaShooter world) {
		this.world = world;
		this.enemiesPerSecond = 0.5f;
		this.enemiesPerWave = 5;
		this.wave = 1;
		this.r = new Random();
		this.waveIsSpawned = false;
		currentEnemiesOnLevel = 0;
		startAlarm();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		
		if (currentEnemiesOnLevel < enemiesPerWave) {
			Enemy[] enemies = { new EasyEnemy(world), new MediumEnemy(world), new HardEnemy(world) };
			int randomIndex = r.nextInt(enemies.length);
			int randomXPos = r.nextInt();
	        world.addGameObject(enemies[randomIndex], 3000 - 50,  620);
	        
	        System.out.println("zombie spawned"); //remove when done debugging
	        
	        if (currentEnemiesOnLevel == enemiesPerWave) {
	        		waveIsSpawned = true;
	        } else {
	        		currentEnemiesOnLevel++;
	        }
	        
		} else if (currentEnemiesOnLevel == 0 && waveIsSpawned) {
			System.out.println("new wave!"); //remove when done debugging
			enemiesPerWave += 5;
			waveIsSpawned = false;
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
