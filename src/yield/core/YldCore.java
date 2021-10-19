package yield.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yield.YldGScript;
import yield.YldRoom;
import yield.YldScript;
import yield.core.engines.YldGraphicsEngine;
import yield.core.engines.YldLogicEngine;
import yield.core.engines.interfaces.YldGraphical;
import yield.core.engines.interfaces.YldLogical;
import yield.util.YldReader;
import yield.util.YldWriter;
import yieldg.YldSprite;

public class YldCore implements YldLogical, YldGraphical {

	public static String core_version = "1.0.1";

	public static boolean yieldpresentation;

	private List<YldScript> yldScripts;
	private YldLogicEngine yldLogicEngine;
	private YldGraphicsEngine yldGraphicsEngine;
	public int TPS, FPS;
	private YldSprite yieldlogo = new YldSprite("/YieldP.png");

	public YldCore() {
		yldScripts = new ArrayList<YldScript>();
		YldWriter.writer = new YldWriter();
		YldReader.reader = new YldReader();
	}

	public void startEngines() {
		yldLogicEngine = new YldLogicEngine(this);
		yldGraphicsEngine = new YldGraphicsEngine(this);
	}

	@Override
	public void update() {
		TPS = (int) yldLogicEngine.getTPS();
		if (yldScripts != null) {
			for (int i = 0; i < yldScripts.size(); i++) {
				YldScript script = yldScripts.get(i);
				if (script.getLayer() >= 0) {
					if (script.getLayer() < yldScripts.size()) {
						Collections.swap(yldScripts, script.getLayer(), i);
					} else {
						Collections.swap(yldScripts, yldScripts.size() - 1, i);
					}
					script.setLayer(-1);
				}
				if (script.getBody() != null) {
					if (script.getBody().getRoom() != null) {
						if (script.getBody().getRoom() == YldRoom.getActRoom()) {
							script.update();
						}
					} else {
						script.update();
					}
				} else {
					script.update();
				}
			}

		}
	}

	@Override
	public void render(Graphics2D g) {
		TPS = (int) yldGraphicsEngine.getFPS();
		int n = 0;
		if (yldScripts != null) {
			for (int i = 0; i < yldScripts.size(); i++) {
				YldScript script = yldScripts.get(i);
				if (script instanceof YldGScript) {
					n++;
					if (script.getBody() != null) {
						if (script.getBody().getRoom() != null) {
							if (script.getBody().getRoom() == YldRoom.getActRoom()) {
								((YldGScript) script).updateRender(g);
							}
						} else {
							((YldGScript) script).updateRender(g);
						}
					} else {
						((YldGScript) script).updateRender(g);
					}
					;
				}
			}
		}

		if (n <= 1 || yieldpresentation) {
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, getYldGraphicsEngine().getWWidth(), getYldGraphicsEngine().getWHeight());
			g.drawImage(yieldlogo.getBufferedImage(),
					(int) (yldGraphicsEngine.getWWidth() / 2
							- (int) ((double) yldGraphicsEngine.getWWidth() / 1.706666666666667) / 1.9),
					(int) (yldGraphicsEngine.getWHeight() / 2
							- (int) ((double) yldGraphicsEngine.getWHeight() / 1.706666666666667) / 2.6), (int) ((double) yldGraphicsEngine.getWWidth() / 1.706666666666667),
					(int) ((double) yldGraphicsEngine.getWHeight() / 2.4), null);
		}
	}

	/**
	 * @return the yldScripts
	 */
	public List<YldScript> getYldScripts() {
		return yldScripts;
	}

	/**
	 * @param yldScripts the yldScripts to set
	 */
	public void setYldScripts(List<YldScript> yldScripts) {
		this.yldScripts = yldScripts;
	}

	/**
	 * @param FPS the FPS to set
	 */
	public boolean changeFPS(double FPS) {
		boolean success = true;
		try {
			yldGraphicsEngine.setTargetFPS(FPS);
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	/**
	 * @param TPS the TPS to set
	 */
	public boolean changeTPS(double TPS) {
		boolean success = true;
		try {
			yldLogicEngine.setTargetTPS(TPS);
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	/**
	 * @param yldScripts the yldScripts to set
	 */
	public boolean loadYldScript(YldScript yldScript) {
		boolean success = true;
		try {
			yldScripts.add(yldScript);
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	/**
	 * @return the yldLogicEngine
	 */
	public YldLogicEngine getYldLogicEngine() {
		return yldLogicEngine;
	}

	/**
	 * @param yldLogicEngine the yldLogicEngine to set
	 */
	public void setYldLogicEngine(YldLogicEngine yldLogicEngine) {
		this.yldLogicEngine = yldLogicEngine;
	}

	/**
	 * @return the yldGraphicsEngine
	 */
	public YldGraphicsEngine getYldGraphicsEngine() {
		return yldGraphicsEngine;
	}

	/**
	 * @param yldGraphicsEngine the yldGraphicsEngine to set
	 */
	public void setYldGraphicsEngine(YldGraphicsEngine yldGraphicsEngine) {
		this.yldGraphicsEngine = yldGraphicsEngine;
	}

	/**
	 * @return the yieldlogo
	 */
	public YldSprite getYieldlogo() {
		return yieldlogo;
	}

}
