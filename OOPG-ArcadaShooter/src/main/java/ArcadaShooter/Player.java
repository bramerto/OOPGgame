package ArcadaShooter;

import java.util.List;

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
	private boolean jumped, turned;
    private int health, ammo;
    private float aimX, aimY;
    public Weapon selectedWeapon;
    public boolean isImmortal;
    
    /**
     * Constructor of Player
     * @param world
     */
	public Player(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"), 2);
		this.selectedWeapon = new Knife(world);
        this.health = 100;
        this.jumped = false;
        this.turned = false;
        this.ammo = 0;
        setGravity(0.8f);
        setFriction(0.1f);
		this.world = world;
		this.world.addGameObject(selectedWeapon, getX() + 35, getY() + 25);
	}
	
	/**
	 * checks collision with the tiles
	 * @author Bram van der Beek
	 * @param List<CollidedTile> collidedTiles
	 */
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
	
	/**
	 * switches current weapon and adds it to the world
	 * @author Bram van der Beek
	 */
	private void switchWeapon() {
		if (selectedWeapon instanceof Knife) {
			world.deleteGameObject(selectedWeapon);
			selectedWeapon = new Gun(world);
			world.addGameObject(selectedWeapon);
			
		} else {
			world.deleteGameObject(selectedWeapon);
			selectedWeapon = new Knife(world);
			world.addGameObject(selectedWeapon);
		}
	}
	
	/**
	 * lets the player receive damage
	 * @param damage
	 * @author Bram van der Beek
	 */
	public void receiveDamage(int damage) {
		if(!isImmortal) {
			health -= damage;
		}
		world.refreshDashboard();
	}

	/**
	 * checks collision with pickups and applies buff to player
	 * @author Bram van der Beek
	 * @param List<GameObject> collidedGameObjects
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g:collidedGameObjects) {
        	if (g instanceof Pickup) {
        		((Pickup)g).doAction(this);
            }
        }
	}
	
	/**
	 * check collision with world borders and weaponframe position
	 * @author Bram van der Beek
	 */
	@Override
	public void update() {
		if (turned) {
			selectedWeapon.setX(getX() - 20);
			selectedWeapon.setY(getY() + 25);
		} else {
			selectedWeapon.setX(getX() + 35);
			selectedWeapon.setY(getY() + 25);
		}
		
		if (health<=0) {
			world.exit();
			
		} else if (health > 100) {
			health = 100;
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
	
	/**
	 * checks if key is pressed and registers players action
	 * @param int keyCode
	 * @param char key
	 * @author Bram van der Beek
	 */
	@Override
	public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (key == 'a' || keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(1);
            selectedWeapon.setCurrentFrameIndex(1);
            turned = true;
        }
        if ((key == 'w' || keyCode == world.UP || key == ' ') && !jumped) {
            setDirectionSpeed(0, 50);
            jumped = true;
        }
        if (key == 'd' || keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(0);
            selectedWeapon.setCurrentFrameIndex(0);
            turned = false;
        }
        if (key == 'e') {
        	switchWeapon();
        }
    }
	
	/**
	 * checks if mouse is moved and binds them to a player aimx and aimy position
	 * @author Bram van der Beek
	 */
	@Override
	public void mouseMoved(int x, int y) {
		setAimX(x);
		setAimY(y);
    }
	
	/**
	 * returns player ammo 
	 * @return int ammo
	 * @author Bram van der Beek
	 */
	public int getAmmo() {
		return ammo;
	}
	
	/**
	 * sets player ammo 
	 * @param int ammo
	 * @author Bram van der Beek
	 */
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	/**
	 * returns player health
	 * @return int health
	 * @author Bram van der Beek
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * sets player health
	 * @param int health
	 * @author Bram van der Beek
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * returns player aimx position
	 * @return float aimX
	 * @author Bram van der Beek
	 */
	public float getAimX() {
		return aimX;
	}

	/**
	 * sets player aimx position
	 * @param float aimX
	 * @author Bram van der Beek
	 */
	public void setAimX(float aimX) {
		this.aimX = aimX;
	}
	
	/**
	 * returns player aimy position
	 * @return float aimY
	 * @author Bram van der Beek
	 */
	public float getAimY() {
		return aimY;
	}
	
	/**
	 * sets player aimy position
	 * @param float aimY
	 * @author Bram van der Beek
	 */
	public void setAimY(float aimY) {
		this.aimY = aimY;
	}
	
	/**
	 * sets player immortality to false
	 * @author Chris Buter
	 */
	public void setImortal() {
		isImmortal = false;
	}
}
