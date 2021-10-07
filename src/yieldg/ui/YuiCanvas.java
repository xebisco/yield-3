package yieldg.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import yield.engine.YieldApp;
import yield.engine.systems.InputSystem;
import yield.main.Script;
import yield.util.Mouse;
import yieldg.ui.main.YuiPalet;

public final class YuiCanvas extends Script {

	@Override
	public String tag() {
		return "Canvas";
	}

	private List<YuiGraphical> graphicalList;
	public String state;

	public YuiPalet palet;

	private int canvasWidth = 1280, canvasHeight = 720;
	private double mWidth, mHeight;
	private BufferedImage canvasImage;
	private Graphics canvasGraphics;

	public YuiCanvas() {
		palet = (new YuiPalet());
		graphicalList = new ArrayList<>();
		Mouse.canvas.add(this);
		InputSystem.canvas.add(this);
	}

	@Override
	public void render(Graphics g) {
		getBody().setLayer(0);
		canvasImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TRANSLUCENT);
		setCanvasGraphics(canvasImage.getGraphics());
		
		for (int i = 0; i < graphicalList.size(); i++) {
			YuiGraphical graphical = graphicalList.get(i);
			if (graphical.frames > 1 && graphical.show) {
				graphical.render(getCanvasGraphics());
			}
		}

		g.drawImage(canvasImage, 0, 0, YieldApp.app().getWidth(), YieldApp.app().getHeight(), null);
		getCanvasGraphics().dispose();
	}
	
	@Override
	public void tick() {
		mWidth = (double) YieldApp.app().getWidth() / (double) canvasWidth;
		mHeight = (double) YieldApp.app().getHeight() / (double) canvasHeight;
		
		for (int i = 0; i < graphicalList.size(); i++) {
			YuiGraphical graphical = graphicalList.get(i);
			if (graphical.frames > 1 && graphical.show) {
				graphical.tick();
			}
			graphical.update();
		}
	}

	public int getCanvasWidth() {
		return canvasWidth;
	}

	public void setCanvasWidth(int canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	public void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	public Graphics getCanvasGraphics() {
		return canvasGraphics;
	}

	public void setCanvasGraphics(Graphics canvasGraphics) {
		this.canvasGraphics = canvasGraphics;
	}

	public List<YuiGraphical> getGraphicalList() {
		return graphicalList;
	}

	public void setGraphicalList(List<YuiGraphical> graphicalList) {
		this.graphicalList = graphicalList;
	}

	public void addGraphical(YuiGraphical graphicalObj) {
		graphicalObj.canvas = this;
		this.graphicalList.add(graphicalObj);
		graphicalObj.awake();

	}

	public YuiPalet getPalet() {
		return palet;
	}

	public void setPalet(YuiPalet palet) {
		this.palet = palet;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BufferedImage getCanvasImage() {
		return canvasImage;
	}

	public void setCanvasImage(BufferedImage canvasImage) {
		this.canvasImage = canvasImage;
	}

	public double getmWidth() {
		return mWidth;
	}

	public void setmWidth(double mWidth) {
		this.mWidth = mWidth;
	}

	public double getmHeight() {
		return mHeight;
	}

	public void setmHeight(double mHeight) {
		this.mHeight = mHeight;
	}

}
