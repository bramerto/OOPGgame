package ArcadaShooter;

import java.util.List;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PVector;

public class Ammobox extends Pickup implements ICollidableWithTiles {
    private ArcadaShooter world;
    /**
     * Constructor
     * @param World where the pickup spawns
     * @author Chris Buter
     */
	public Ammobox(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_ammobox.png"));
        this.world=world;
        setGravity(0.3f);
	}
	/**
     * Activate pickup
     * @param Player element
     * @author Chris Buter
     */
	@Override
	public void doAction(Player player) {
		player.setAmmo(player.getAmmo() + 10);
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
