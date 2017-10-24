package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class DoubleDamage extends Pickup implements IAlarmListener{
	private Weapon weapon;
	
	public DoubleDamage(int duration, Weapon weapon){
		
	}
	@Override
	public void setActive() {
		isActive = true;
	}
	
	public void addDamage() {
		int damage = 10*2;
		
	}
	@Override
	public void deactivate() {
		isActive = false;
	}
	@Override
	public void triggerAlarm(String alarmName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}
}
