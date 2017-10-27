package ArcadaShooter;

import java.util.List;
import java.util.Random;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PVector;

public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {

	private ArcadaShooter world;
	private final int size = 50;
    private Random r;
    private int health, ammo;
    private float aimx, aimy, aimdx, aimdy;
    private Weapon[] weapons = { new Knife(), new Gun()};
    private Weapon selectedWeapon;
    
	public Player(ArcadaShooter world) {
		this(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
		this.world = world;
	}
	
	private Player(Sprite sprite) {
		super(sprite, 2);
		this.selectedWeapon = weapons[0];
        this.health = 100;
        this.r = new Random();
        setGravity(0.8f);
	}
	
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		
		if (health<=0) {
			world.exit();
		}

        for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof NormalTile) {
            	
                if (ct.collisionSide == ct.TOP || ct.collisionSide == ct.INSIDE) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setY(vector.y - getHeight());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                
                if (ct.collisionSide == ct.BOTTOM) {
                    try {
						vector = world.getTileMap().getTilePixelLocation(ct.theTile);
	    					setySpeed(0);
	    					setY(vector.y + 50);
		                
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
                }
                
                if (ct.collisionSide == ct.LEFT) {
                    try {
						vector = world.getTileMap().getTilePixelLocation(ct.theTile);
	    					setxSpeed(0);
	    					setX(vector.x - 50);
		                
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
                }
                
                if (ct.collisionSide == ct.RIGHT) {
                    try {
						vector = world.getTileMap().getTilePixelLocation(ct.theTile);
	    					setxSpeed(0);
	    					setX(vector.x + 50);
		                
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
                }
            }
        }
	}
	
	public void switchWeapon() {
		selectedWeapon = (selectedWeapon instanceof Knife) ? weapons[1] : weapons[0];
	}
	
	public void receiveDamage(int damage) {
		health -= damage;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g:collidedGameObjects) {
            	if (g instanceof Pickup) {
            		((Pickup)g).doAction(this);
            }
            if (g instanceof Pickup) {
            	((Pickup) g).doAction(this);
            }
        }
	}
	
	@Override
	public void update() {
		if (getX()<=0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY()<=0) {
            setySpeed(0);
            setY(0);
        }
        if (getX()>=ArcadaShooter.WORLDWIDTH-size) {
            setxSpeed(0);
            setX(ArcadaShooter.WORLDWIDTH - size);
        }
        if (getY()>=ArcadaShooter.WORLDHEIGHT-size) {
            setySpeed(0);
            setY(ArcadaShooter.WORLDHEIGHT - size);
        }
	}
	
	public void keyPressed(int keyCode, char key) { //TODO: registering two keys with keyReleased();
        final int speed = 5;
        if (key == 'a' || keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(1);
        }
        if (key == 'w' || keyCode == world.UP) {
            setDirectionSpeed(0, 20);
        }
        if (key == 'd' || keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(0);
        }
        if (key == ' ') {
            System.out.println("Spatie!");
        }
    }
	
	@Override
	public void mouseMoved(int x, int y) {
        aimx = x;
        aimy = y;
    }
	
	public void mouseClicked() {
		selectedWeapon.doAction(this);
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
