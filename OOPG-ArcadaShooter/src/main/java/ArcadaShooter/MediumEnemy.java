package ArcadaShooter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class MediumEnemy extends Enemy {
	
	public MediumEnemy(ArcadaShooter world) {
		super(world, 10, 1, new Knife(world));
	}
}
