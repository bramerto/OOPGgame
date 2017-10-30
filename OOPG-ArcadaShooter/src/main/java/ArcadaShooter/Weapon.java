package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

<<<<<<< HEAD
public class Weapon extends Pickup implements ICollidableWithTiles{
	public Weapon(Sprite sprite){
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_pistol.png"));
	}
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
	}

	@Override
	public void doAction(Player player) {
		
	}
=======
public abstract class Weapon extends AnimatedSpriteObject {
	protected ArcadaShooter world;
	
	public Weapon(Sprite sprite, ArcadaShooter world){
		super(sprite, 2);
		this.world = world;
		setGravity(0);
	}

	public abstract void doAction(GameObject from, float targetX, float targetY);
>>>>>>> a99251467a1a8295395f47ac75ca7d47f8a42616

	public void update() {
	}
}
