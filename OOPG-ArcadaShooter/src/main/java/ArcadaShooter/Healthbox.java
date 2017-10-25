package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

public class Healthbox extends Pickup{
	private boolean isActive;
	private Player player;
	
	public Healthbox() {
		//x and y for the spawning coordinates
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
	}
	@Override
	public void setActive() {
		isActive = true;
	}
	@Override
	public void deactivate() {
		isActive = false;
	}
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(PGraphics g) {
		// TODO Auto-generated method stub
		
	}
}
