package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.waterworld.Player;
import nl.han.ica.waterworld.Swordfish;

public class Bullet extends SpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects{
	private boolean collidedWithEnemy;
	private Bullet[] bullets;
	
	public Bullet() {
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
	}
	public void shoot(ArcadaShooter world, Player player) {
		
	}
	public boolean collidedWithEnemy(int x, int y, Enemy enemy) {
		return false;
	}
	
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Enemy) {
            	//do zombie.health =- 10 or something like this.
            	System.out.println("Zombie Hit!");
                Player.score++;
            }
        }
    }

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
