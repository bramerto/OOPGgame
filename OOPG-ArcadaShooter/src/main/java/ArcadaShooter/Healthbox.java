package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Healthbox extends Pickup {
    /**
     * Creates a pickup that heals the player
     * @param world object to add item to
     * @author Chris Buter
     */
	public Healthbox(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_healthbox.png"),world);
        setGravity(0.3f);
	}
	/**
     * function call of pickup
     * @param player object
     * @author Chris Buter
     */
	@Override
	public void doAction(Player player) {
		player.setHealth(player.getHealth() + 10);
		world.deleteGameObject(this);
		world.refreshDashboard();
	}
	
	/**
     * Updates game object
     * @author Chris Buter
     */
	@Override
	public void update() {
	}
}
