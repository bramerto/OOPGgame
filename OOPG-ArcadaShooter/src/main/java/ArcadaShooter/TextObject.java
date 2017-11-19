package ArcadaShooter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class TextObject extends GameObject {

    private String text;
    /**
     * Constructor for TextObject
     * @param text
     */
    public TextObject(String text) {
        this.text = text;
    }
    
    
	/**
	 * sets text of object
	 * @param text
	 */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * draws the text in red
     * @param PGraphics g
     * @author Bram van der Beek
     */
    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(50);
        g.fill(255, 0, 0);
        g.text(text,getX(),getY());
    }
    
    /**
     * updates text
     */
	@Override
	public void update() {
	} 
}
