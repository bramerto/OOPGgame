package ArcadaShooter;

import java.util.List;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PVector;

public class Gun extends Weapon implements ICollidableWithGameObjects, ICollidableWithTiles{
	private Bullet[] bullets;
	private Sound pickupSound;
	boolean isEquipped;
	Gun gun;
	
	public Gun(ArcadaShooter world, Sound pickupSound) {
		super(new Sprite("src/main/java/ArcadaShooter/media/weapon_pistol.png"));
		this.pickupSound = pickupSound;
        this.world = world;
        setGravity(0.3f);
		int damage = 10;
	}
	@Override
	public void doAction(Player player) {
		player.setSelectedWeapon(this);
		world.deleteGameObject(this);
		if(player.getSelectedWeapon() == this && player.getAmmo() > 0) {
			Bullet p = new Bullet(world);
			player.setAmmo(player.getAmmo() - 1);
			world.refreshDashboard();
			world.addGameObject(p, player.getX(),  player.getY());
			
		}
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

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}
}
