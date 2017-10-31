package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public abstract class Pickup extends SpriteObject {
	ArcadaShooter world;
	
	/**
     * Constructor
     * @param Sprite PickupSprite
     * @author Chris Buter
     */
	public Pickup(Sprite sprite) {
		super(sprite);
	}
	
	/**
     * Delete game object
     * @param World where pickup is created
     * @author Chris Buter
     */
	public void delete(ArcadaShooter world) {
		world.deleteGameObject(this);
	}
	
	/**
     * Blueprint for pickup action method
     * @param Sprite PickupSprite
     * @author Chris Buter
     */
	public abstract void doAction(Player player);
}
