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
	private final Sound pickupSound;
    private ArcadaShooter world;
	
	public Ammobox(ArcadaShooter world, Sound pickupSound) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_ammobox.png"));
		this.pickupSound=pickupSound;
        this.world=world;
        setGravity(0.3f);
	}
	
	@Override
	public void doAction(Player player) {
		System.out.println(player.getAmmo()); //TODO: remove if done
		player.setAmmo(player.getAmmo() + 10);
		world.deleteGameObject(this);
		world.refreshDashboard();
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
