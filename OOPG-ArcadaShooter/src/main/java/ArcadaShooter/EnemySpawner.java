package ArcadaShooter;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class EnemySpawner implements IAlarmListener {
	
	private ArcadaShooter world;
	private float enemiesPerSecond;
	private int enemiesPerWave;
	private int wave;
	private boolean waveIsSpawned;
	private Random r;
	public static int currentEnemiesOnLevel;
	private int enemiesSpawnedThisWave;
	
	public EnemySpawner(ArcadaShooter world) {
		this.world = world;
		this.enemiesPerSecond = 0.5f;
		this.enemiesPerWave = 5;
		this.wave = 1;
		this.r = new Random();
		this.waveIsSpawned = false;
		this.enemiesSpawnedThisWave = 0;
		currentEnemiesOnLevel = 0;
		startAlarm();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		
		if (currentEnemiesOnLevel < enemiesPerWave && !waveIsSpawned) {
			Enemy[] enemies = { new EasyEnemy(world) };
		
			if (wave > 5) enemies[1] = new MediumEnemy(world);
			if (wave > 10) enemies[2] = new HardEnemy(world);
			
			int randomIndex = r.nextInt(enemies.length);
	        world.addGameObject(enemies[randomIndex], 1000 - 50,  620);
	        
	        if (enemiesSpawnedThisWave == enemiesPerWave - 1) {
        		waveIsSpawned = true;
	        } else {
        		currentEnemiesOnLevel++;
        		enemiesSpawnedThisWave++;
	        }
	        
		} else if (currentEnemiesOnLevel <= 0) {
			enemiesPerWave += 5;
			waveIsSpawned = false;
			enemiesSpawnedThisWave = 0;
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
