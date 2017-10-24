package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Immunity extends Pickup implements IAlarmListener{
	public boolean isActive;
	public Player player;
	public int timer;
	
	public Immunity(boolean isActive, Player player, int timer) {
		
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
	public boolean collidedWithPlayer() {
		return false;
	}
	@Override
	public void triggerAlarm(String alarmName) {
		// TODO Auto-generated method stub
		
	}
}
