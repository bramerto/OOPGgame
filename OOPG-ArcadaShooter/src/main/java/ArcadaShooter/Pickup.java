package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.waterworld.Bubble;
import nl.han.ica.waterworld.WaterWorld;

public abstract class Pickup extends SpriteObject {
	
	public Pickup(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	public void delete(ArcadaShooter world) {
		world.deleteGameObject(this);
	}
	public void doAction(Player player) {
		// TODO Auto-generated method stub
	}
	
}
