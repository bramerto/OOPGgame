package ArcadaShooter;

import java.util.ArrayList;
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
	private int enemiesSpawnedThisWave;
	public static int currentEnemiesOnLevel;
	
	/**
	 * Constructor for enemy spawner
	 * @param world
	 */
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

	/**
	 * spawns enemies in waves with different tyypes
	 * @param String alarmName
	 * @author Bram van der Beek
	 */
	@Override
	public void triggerAlarm(String alarmName) {
		
		if (currentEnemiesOnLevel < enemiesPerWave && !waveIsSpawned) {
			
			ArrayList<Enemy> enemies = new ArrayList<>();
			enemies.add(new EasyEnemy(world));
			
			if (wave > 1) enemies.add(new MediumEnemy(world));
			if (wave > 2) enemies.add(new HardEnemy(world));
			
			int randomIndex = r.nextInt(enemies.size());
	        world.addGameObject(enemies.get(randomIndex), world.WORLDWIDTH - enemies.get(randomIndex).getWidth(), world.spawnY);
	        
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
	 /**
	  * starts alarm
	  * @author Bram van der Beek
	  */
	private void startAlarm() {
        Alarm alarm = new Alarm("New enemy", 1 / enemiesPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }
	
	/**
	 * returns current enemy wave
	 * @return wave
	 * @author Bram van der Beek
	 */
	public int getWave() {
		return wave;
	}
}
