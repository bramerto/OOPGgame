package ArcadaShooter;

import java.util.List;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PVector;

public class DoubleDamage extends Pickup implements IAlarmListener, ICollidableWithTiles{
    private ArcadaShooter world;
    public boolean isActive;
    /**
     * Constructor
     * @param World The world where knife is created
     */
	public DoubleDamage(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_doubledamage.png"));
        this.world=world;
        setGravity(0.3f);
	}
	/**
     * Alarm triggered
     * @param Alarm name
     */
	@Override
	public void triggerAlarm(String alarmName) {
		world.getPlayer().selectedWeapon.resetDamage();
	}
	/**
     * Activate pickup
     * @param Player element
     */
	@Override
	public void doAction(Player player) {
		Alarm alarm=new Alarm("Start Double Damage",1/0.1);
        alarm.addTarget(this);
        alarm.start();
        
        world.getPlayer().selectedWeapon.doubleDamage();
		world.deleteGameObject(this);
	}
	/**
     * Check for collisions with tiles
     * @param List of all tiles
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
	@Override
	public void update() {
	}

}
