package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

public class Ammobox extends Pickup{
	public boolean isActive;
	public int ammoUp = 60;
	
	public Ammobox(int x, int y, boolean isActive, Player player, int ammoUp) {
		//x and y for the spawning coordinates
		super(new Sprite("src/main/java/ArcadaShooter/media/player.png"));
	}
	public void addAmmo(Player player) {
		// add ammo and deactivate
		deactivate();
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
	@Override
	public void setActive() {
		// TODO Auto-generated method stub
		
	}
}
