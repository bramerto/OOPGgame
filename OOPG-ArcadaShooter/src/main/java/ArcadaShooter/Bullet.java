package ArcadaShooter;

import java.awt.MouseInfo;
import java.util.List;

import com.sun.javafx.scene.paint.GradientUtils.Point;

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
	private ArcadaShooter world;
	private Bullet[] bullets;
	
	
	public Bullet(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/bullet.png"));
		this.world = world;
		setGravity(0.8f);
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
		int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;

		System.out.println(x);
		setDirectionSpeed(100, 1);
		
	}
	public float getAngle() {
		java.awt.Point target = MouseInfo.getPointerInfo().getLocation();
	    float angle = (float) Math.toDegrees(Math.atan2(target.y - y, target.x - x));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
}
