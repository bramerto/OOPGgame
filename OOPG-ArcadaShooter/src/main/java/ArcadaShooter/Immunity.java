package ArcadaShooter;

import java.util.List;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PVector;

public class Immunity extends Pickup implements IAlarmListener, ICollidableWithTiles {
    private ArcadaShooter world;
    public boolean isActive;
    /**
     * Constructor
     * @param World where pickup spawns
     * @author Chris Buter
     */
	public Immunity(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_immunity.png"));
        this.world=world;
        setGravity(0.3f);
	}
	/**
     * Called when alarm is triggered
     * @param Alarm name
     * @author Chris Buter
     */
	@Override
	public void triggerAlarm(String alarmName) {	
		world.getPlayer().setImortal();
	}
	/**
     * Activate and remove pickup
     * @param Player that picks up the pickup
     * @author Chris Buter
     */
	@Override
	public void doAction(Player player) {
			Alarm alarm=new Alarm("Start Immortal",1/0.1);
	        alarm.addTarget(this);
	        alarm.start();
			player.isImmortal = true;

		world.deleteGameObject(this);
	}
	/**
     * Updates game object
     * @author Chris Buter
     */
	@Override
	public void update() {
	}
	/**
     * Check for tile collisions
     * @param List of collidable tiles
     * @author Chris Buter
     */
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile ct : collidedTiles) {
			if (ct.theTile instanceof NormalTile) {
				try {
					setGravity(0f);
                    vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                    setY(vector.y - getHeight());
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
			}
        }
	}
}
