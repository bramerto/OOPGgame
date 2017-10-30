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
	private final Sound pickupSound;
    private ArcadaShooter world;
    public boolean isActive;
	
	public Immunity(ArcadaShooter world, Sound pickupSound) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_immunity.png"));
		this.pickupSound=pickupSound;
        this.world=world;
        setGravity(0.3f);
	}
	
	@Override
	public void triggerAlarm(String alarmName) {	
		Player.setImortal();
	}
	
	@Override
	public void doAction(Player player) {
			Alarm alarm=new Alarm("Start Immortal",1/0.1);
	        alarm.addTarget(this);
	        alarm.start();
			player.isImmortal = true;

		world.deleteGameObject(this);
	}
	
	@Override
	public void update() {
	}
	
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
