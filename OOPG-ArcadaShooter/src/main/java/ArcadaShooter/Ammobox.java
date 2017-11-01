package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Ammobox extends Pickup {
    /**
     * Constructor
     * @param World where the pickup spawns
     * @author Chris Buter
     */
	public Ammobox(ArcadaShooter world) {
		super(new Sprite("src/main/java/ArcadaShooter/media/pickup_ammobox.png"), world);
        setGravity(0.3f);
	}
	/**
     * Activate pickup
     * @param Player element
     * @author Chris Buter
     */
	@Override
	public void doAction(Player player) {
		player.setAmmo(player.getAmmo() + 10);
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
