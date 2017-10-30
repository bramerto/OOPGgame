package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Weapon extends Pickup implements ICollidableWithTiles{
	
	public Weapon(Sprite sprite){
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
	}
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
	}

	@Override
	public void doAction(Player player) {
		Gun gun = new Gun(world, null);
		gun.shoot(player);
	}

	@Override
	public void update() {
		
	}
}

