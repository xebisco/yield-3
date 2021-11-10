package yield.core.engines.interfaces;

import java.awt.Graphics2D;

/**
 * Essa interface é feita para ser usada com uma YldGraphicsEngine.
 */
public interface YldGraphical {

	/**
     * Esse método é chamado pela Graphics engine a cada frame.
     */
	public void render(Graphics2D g);

}
