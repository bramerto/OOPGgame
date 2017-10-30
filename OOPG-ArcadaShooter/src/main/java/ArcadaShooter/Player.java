package ArcadaShooter;

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
	private boolean jumped, turned;
    private int health, ammo;
    private float aimX, aimY;
    private Weapon[] weapons;
    public Weapon selectedWeapon;
    private Random r;
    
	public Player(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"), 2);
		//this.setSelectedWeapon(weapons[0]);
		//this.weapons[0] = new Knife(world);
		//this.weapons[1] = new Gun(world);
		this.selectedWeapon = new Gun(world);
        this.health = 100;
        this.r = new Random();
        this.jumped = false;
        this.turned = false;
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
		selectedWeapon = (selectedWeapon instanceof Knife) ? weapons[1] : weapons[0];
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
		if (turned) { //TODO: gravity on gun
			selectedWeapon.setX(getX() - 20);
			selectedWeapon.setY(getY() + 25);
		} else {
			selectedWeapon.setX(getX() + 35);
			selectedWeapon.setY(getY() + 25);
		}
		
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
            selectedWeapon.setCurrentFrameIndex(1);
            turned = true;
        }
        if ((key == 'w' || keyCode == world.UP) && !jumped) {
            setDirectionSpeed(0, 25);
            jumped = true;
        }
        if (key == 'd' || keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(0);
            selectedWeapon.setCurrentFrameIndex(0);
            turned = false;
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
	}
}
