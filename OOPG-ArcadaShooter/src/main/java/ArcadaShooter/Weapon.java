package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Weapon extends Pickup implements ICollidableWithTiles{
	
	public Weapon(){
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
	}

	@Override
	public void doAction(Player player) {
		
	}

	@Override
	public void update() {
		
	}
}

