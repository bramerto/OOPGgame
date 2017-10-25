package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

public class Immunity extends Pickup implements IAlarmListener{
	public boolean isActive;
	public Player player;
	public int timer;
	
	public Immunity(boolean isActive, Player player, int timer) {
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
	}
	@Override
	public void setActive() {
		isActive = true;
		startImmunity(isActive, timer, player);
	}
	
	public void startImmunity(boolean isActive, int timer, Player player) {
		deactivate();
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
