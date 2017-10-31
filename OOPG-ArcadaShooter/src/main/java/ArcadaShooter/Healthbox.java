package ArcadaShooter;

import java.util.List;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PVector;

public class Healthbox extends Pickup implements ICollidableWithTiles {
	final int size = 50;
    private ArcadaShooter world;
    
    /**
     * Creates a pickup that heals the player
     * @param world object to add item to
     * @author Chris Buter
     */
	public Healthbox(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_healthbox.png"));
        this.world = world;
        setGravity(0.3f);
	}
	/**
     * function call of pickup
     * @param player object
     * @author Chris Buter
     */
	@Override
	public void doAction(Player player) {
		player.setHealth(player.getHealth() + 10);
		world.deleteGameObject(this);
		world.refreshDashboard();
	}
	
	/**
     * Check for collisions with tiles
     * @param List of all tiles
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
	/**
     * Updates game object
     * @author Chris Buter
     */
	@Override
	public void update() {
	}
}
