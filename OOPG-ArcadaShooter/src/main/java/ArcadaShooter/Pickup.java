package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.waterworld.Bubble;

public abstract class Pickup extends SpriteObject implements IAlarmListener {
	public Pickup(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	boolean isActive;
	ArcadaShooter world;
	
	public void spawn() {
		
	}
	private void startAlarm() {
        Alarm alarm=new Alarm("New pickup",1/1.1);
        alarm.addTarget(this);
        alarm.start();
    }
	@Override
    public void triggerAlarm(String alarmName) {
		Pickup b=new Healthbox();
        world.addGameObject(b);
        startAlarm();
    }
	public abstract void setActive();
	
	public void doAction(Player player) {
		
	}
	
	public void delete(ArcadaShooter world) {
		world.deleteGameObject(this);
	}
	
	public abstract void deactivate();
}
