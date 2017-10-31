package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public abstract class Pickup extends SpriteObject implements IAlarmListener, ICollidableWithTiles {
	
	boolean isActive;
	ArcadaShooter world;
	
	
	public Pickup(Sprite sprite) {
		super(sprite);
	}
	
	public void spawn() {
	}
	
	private void startAlarm() {
        Alarm alarm = new Alarm("New pickup",1/1.1);
        alarm.addTarget(this);
        alarm.start();
    }
	
	@Override
    public void triggerAlarm(String alarmName) {

    }

	public void delete(ArcadaShooter world) {
		world.deleteGameObject(this);
	}
	
	public abstract void doAction(Player player);
	
}
