package ArcadaShooter;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
 
import javax.swing.*;
import java.util.List;
import java.util.Random;

import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PVector;

public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {

	private ArcadaShooter world;
	private final int size = 50;
	private boolean jumped;
    private int health, ammo;
    private float aimX, aimY;
    private Weapon[] weapons;
    private Weapon selectedWeapon;
    private Random r;
    
	public Player(ArcadaShooter world) {
<<<<<<< HEAD
		this(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
		this.world = world;
	}
	
	private Player(Sprite sprite) {
		super(sprite, 2);
		this.setSelectedWeapon(weapons[0]);
=======
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"), 2);
//		this.weapons[0] = new Knife(world);
//		this.weapons[1] = new Gun(world);
		this.selectedWeapon = new Gun(world);
>>>>>>> a99251467a1a8295395f47ac75ca7d47f8a42616
        this.health = 100;
        this.r = new Random();
        this.jumped = false;
        setGravity(0.8f);
		this.world = world;
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;

		for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof NormalTile) {
            		if (ct.collisionSide == ct.TOP || ct.collisionSide == ct.INSIDE) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setY(vector.y - getHeight());
                        jumped = false;
                        
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
		setSelectedWeapon((getSelectedWeapon() instanceof Knife) ? weapons[1] : weapons[0]);
	}
	
	public void receiveDamage(int damage) {
		health -= damage;
		world.refreshDashboard();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g:collidedGameObjects) {
        	if (g instanceof Pickup) {
        		((Pickup)g).doAction(this);
            }
        }
	}
	
	@Override
	public void update() {
		
		if (health<=0) {
			world.exit();
		}
		
		if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() >= world.WORLDWIDTH-size) {
            setxSpeed(0);
            setX(world.WORLDWIDTH - size);
        }
        if (getY() >= world.WORLDHEIGHT-size) {
            setySpeed(0);
            setY(world.WORLDHEIGHT - size);
        }
	}
	
	public void keyPressed(int keyCode, char key) { //TODO: detect two keys?
        final int speed = 5;
        if (key == 'a' || keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(1);
        }
        if ((key == 'w' || keyCode == world.UP) && !jumped) {
            setDirectionSpeed(0, 25);
            jumped = true;
        }
        if (key == 'd' || keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(0);
        }
        if (key == ' ') {
	        	setDirectionSpeed(0, 25);
	        	jumped = true;
        }
    }
	
	@Override
	public void mouseMoved(int x, int y) {
		setAimX(x);
		setAimY(y);
    }
<<<<<<< HEAD
	
	public void mouseClicked() {
		System.out.println("klikeed!");
		//getSelectedWeapon().doAction(this);
	}
=======
>>>>>>> a99251467a1a8295395f47ac75ca7d47f8a42616

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
<<<<<<< HEAD

=======
	
>>>>>>> a99251467a1a8295395f47ac75ca7d47f8a42616
	public Weapon getSelectedWeapon() {
		return selectedWeapon;
	}

<<<<<<< HEAD
	public void setSelectedWeapon(Weapon selectedWeapon) {
		this.selectedWeapon = selectedWeapon;
=======
	public float getAimX() {
		return aimX;
	}

	public void setAimX(float aimX) {
		this.aimX = aimX;
	}

	public float getAimY() {
		return aimY;
	}

	public void setAimY(float aimY) {
		this.aimY = aimY;
>>>>>>> a99251467a1a8295395f47ac75ca7d47f8a42616
	}
}
