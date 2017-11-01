package ArcadaShooter;

import java.util.List;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import processing.core.PVector;

public abstract class Pickup extends SpriteObject implements ICollidableWithTiles {
	protected ArcadaShooter world;
	
	/**
     * Constructor
     * @param Sprite PickupSprite
     * @author Chris Buter
     */
	public Pickup(Sprite sprite, ArcadaShooter world) {
		super(sprite);
		this.world = world;
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
     * Blueprint for pickup action method
     * @param Sprite PickupSprite
     * @author Chris Buter
     */
	public abstract void doAction(Player player);
}
