package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.CenterFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import ArcadaShooter.tiles.NormalTile;
import processing.core.PApplet;
import processing.core.PImage;

public class ArcadaShooter extends GameEngine {
	
	private TextObject healthText;
	private TextObject ammoText;
	private TextObject waveText;
	private Sound pickupSound;
	private Player player;
	private PickupSpawner pickupSpawner;
	private EnemySpawner enemySpawner;
	public final int WORLDWIDTH = 3000;
	public final int WORLDHEIGHT = 750;
	private PImage img;

	public static void main(String[] args) {
		PApplet.main(new String[]{ArcadaShooter.class.getName()});
	}
	
	public void setupWorldVariables() {
		img = loadImage("src/main/java/ArcadaShooter/media/crosshair.png");
	}
	
	@Override
	public void setupGame() {
		setupWorldVariables();
//        initializeSound();
        initializeTileMap();
        createSpawners();
        createObjects();
        

        createViewWithViewport(WORLDWIDTH, WORLDHEIGHT, 1280, 720, 1);
	}
	
	private void createSpawners() {
		pickupSpawner = new PickupSpawner(this,pickupSound,(float) 1.1);
		enemySpawner = new EnemySpawner(this);
	}
	

	/**
     * Creates a view with a viewport
     * @param worldWidth Total width of world
     * @param worldHeight Total height of the world
     * @param screenWidth Width of the screen
     * @param screenHeight Height of the screen
     * @param zoomFactor Factor intensity of the screenzoom
     */
    private void createViewWithViewport(int worldWidth,int worldHeight,int screenWidth,int screenHeight,float zoomFactor) {
    	CenterFollowingViewport viewPort = new CenterFollowingViewport(player, (int)Math.ceil(screenWidth/zoomFactor),(int)Math.ceil(screenHeight/zoomFactor));
        viewPort.setTolerance(50, 50, 50, 50);
        View view = new View(viewPort, worldWidth,worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
        view.setBackground(loadImage("src/main/java/ArcadaShooter/media/level.png"));
    }
    
    /**
     * Creates a dashboard
     * @param dashboardWidth Width of dashboard
     * @param dashboardHeight Height of dashboard
     */
    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard healthDashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        Dashboard ammoDashboard = new Dashboard(0,25, dashboardWidth, dashboardHeight);
        Dashboard waveDashboard = new Dashboard(0,50, dashboardWidth, dashboardHeight);
        
        healthText = new TextObject("Health: " + player.getHealth());
        ammoText = new TextObject("Ammo: " + player.getAmmo());
        waveText = new TextObject("Wave: " + enemySpawner.getWave());
        
        healthDashboard.addGameObject(healthText);
        ammoDashboard.addGameObject(ammoText);
        waveDashboard.addGameObject(waveText);
        
        addDashboard(healthDashboard);
        addDashboard(ammoDashboard);
        addDashboard(waveDashboard);
    }
    
    private void createObjects() {
	    	player = new Player(this);
	    	addGameObject(player, 100, 620);	    	
	    	createDashboard(WORLDWIDTH, 100);
    }
    
    private void initializeTileMap() {
        Sprite tileSprite = new Sprite("src/main/java/ArcadaShooter/media/tile.jpg");

        TileType<NormalTile> normalTileType = new TileType<>(NormalTile.class, tileSprite);

        TileType[] tileTypes = { normalTileType };
        int tileSize = 50;
        int tilesMap[][] = {
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }
    
    @Override
    public void mouseClicked() {
    	if (player.getAmmo() > 0 && player.selectedWeapon instanceof Gun) {
    		player.selectedWeapon.doAction(player, player.getAimX(), player.getAimY());
    	}else {
    		player.selectedWeapon.doAction(player, player.getAimX(), player.getAimY());
    	}
	}
    
    public Player getPlayer() {
		return player;
    }
    
    public void refreshDashboard() {
	    	healthText.setText("Health: " + player.getHealth());
	    	ammoText.setText("Ammo: " + player.getAmmo());
	    	waveText.setText("Wave: " + enemySpawner.getWave());
    }

	@Override
	public void update() {
		cursor(img);
	}
}
