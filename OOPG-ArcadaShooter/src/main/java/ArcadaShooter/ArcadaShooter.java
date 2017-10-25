package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import ArcadaShooter.Player;
import ArcadaShooter.tiles.NormalTile;
import nl.han.ica.waterworld.TextObject;
import processing.core.PApplet;

public class ArcadaShooter extends GameEngine {
	
	private TextObject dashboardText;
	private Player player;

	public static void main(String[] args) {
		PApplet.main(new String[]{ArcadaShooter.class.getName()});
	}
	
	@Override
	public void setupGame() {
		int worldWidth=3012;
		int worldHeight=772;
        	
//        initializeSound();
        createDashboard(worldWidth, 100);
        initializeTileMap();
//        initializePersistence();
        createObjects();

        createViewWithViewport(worldWidth, worldHeight, 800, 800, 1.1f);
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
        EdgeFollowingViewport viewPort = new EdgeFollowingViewport(player, (int)Math.ceil(screenWidth/zoomFactor),(int)Math.ceil(screenHeight/zoomFactor),0,0);
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
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        dashboardText=new TextObject("");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }
    
    private void createObjects() {
	    	player = new Player(this);
	    	addGameObject(player, 100, 640);
    }
    
    private void initializeTileMap() {
        /* TILES */
        Sprite tileSprite = new Sprite("src/main/java/nl/han/ica/waterworld/media/boards.jpg");

        TileType<NormalTile> normalTileType = new TileType<>(NormalTile.class, tileSprite);

        TileType[] tileTypes = { normalTileType };
        int tileSize=50;
        int tilesMap[][]={
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        };
        
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }


	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
