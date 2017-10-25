package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

public class DoubleDamage extends Pickup implements IAlarmListener{
	private Weapon weapon;
	
	public DoubleDamage(int duration, Weapon weapon){
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
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
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(PGraphics g) {
		// TODO Auto-generated method stub
		
	}
}
