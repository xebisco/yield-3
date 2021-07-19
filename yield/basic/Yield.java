package basic;

import java.awt.Graphics;
import java.util.Random;

import engine.yield.YieldMain;

// Essa classe é feita para ser "extend" em todas as classes do jogo
public class Yield {

	// A classe que a engine usa para o classpath
	private static Yield MainClass = new Yield();

	// Random for classes
	protected static Random random = new Random();

	// variavel Graphics para desenhar objetos na tela
	public static Graphics g;

	// a camada de renderização do objeto
	private int layer;

	// Coordenadas do objeto
	protected double x, y;

	protected int width, height;

	public boolean render = true;

	// Getters e setters >

	public static Yield getMainClass() {
		return MainClass;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLayer() {
		return layer;
	}

	// Getters e setters <

	// Métodos Principais >

	public void Start() {

	}

	public void Update() {

	}

	public void Render() {

	}

	/*
	 * public void Update2() { }
	 */

	public void BPMUpdate() {
	}

	public String Command(String comm) {
		return null;
	}

	public void receiveValue(double value, String comm) {
	}

	// Métodos Principais <

	// Utils >

	// Carregar classes para a engine
	public static void load(Yield obj) {
		YieldMain.getObjects().add(obj);
	}

	// Descarregar classes da engine
	public static void remove(Yield obj) {
		obj = null;
		YieldMain.getObjects().remove(obj);
	}

	// ver se já está carregado
	public static boolean verificate(Yield obj) {
		return YieldMain.getObjects().contains(obj);
	}

	public Yield get(Yield obj) {
		for (int i = 0; i <= YieldMain.getObjects().size(); i++) {
			if (YieldMain.getObjects().get(i).equals(obj)) {
				return YieldMain.getObjects().get(i);
			}
		}
		return null;
	}

	public static void throwError(String error) {
		boolean a = true;
		for (int i = 0; i < YieldMain.getErrors().size(); i++) {
			if (YieldMain.getErrors().get(i) == error)
				a = false;
		}
		if (a) {
			YieldMain.getErrors().add(error);

			// YieldMain.playErrorSound();
		}

	}

	public static void setLayer(int layer, Yield obj) {
		YieldMain.getObjects().remove(obj);
		YieldMain.getObjects().add(layer, obj);
	}

	// Utils <

}
