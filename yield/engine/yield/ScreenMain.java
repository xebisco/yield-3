package engine.yield;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import basic.Settings;
import basic.Time;
import basic.Yield;
import util.Input;
import util.graphics.Sprite;

public class ScreenMain extends Canvas {

	private static final long serialVersionUID = 1L;

	// a versão da engine
	public static String engine_VERSION = "1.0 test1";

	// A imagem da janela
	private BufferedImage image;

	public static Input mainInput;

	// variáveis para efeitos na tela >
	public double ofx;
	public double ofy;

	public double outch;
	public double outchforce;

	private Graphics mainGraphics;

	// variáveis para efeitos na tela <

	// logo da yield
	private static Sprite yieldLogo;

	private Sprite bad, good;

	// Getters e setters >

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public double getOfx() {
		return ofx;
	}

	public void setOfx(double ofx) {
		this.ofx = ofx;
	}

	public double getOfy() {
		return ofy;
	}

	public void setOfy(double ofy) {
		this.ofy = ofy;
	}

	public double getOutch() {
		return outch;
	}

	public void setOutch(double outch) {
		this.outch = outch;
	}

	public double getOutchforce() {
		return outchforce;
	}

	public void setOutchforce(double outchforce) {
		this.outchforce = outchforce;
	}

	public static String getEngine_VERSION() {
		return engine_VERSION;
	}

	public static Sprite getYieldLogo() {
		return yieldLogo;
	}

	public Graphics getMainGraphics() {
		return mainGraphics;
	}

	public void setMainGraphics(Graphics mainGraphics) {
		this.mainGraphics = mainGraphics;
	}

	// Getters e setters <

	public ScreenMain() {
		// Adicionar o suporte para teclas

		mainInput = new Input(69696969);

		addKeyListener(mainInput);

		addMouseListener(mainInput);

		addMouseWheelListener(mainInput);

		yieldLogo = new Sprite("/engine/resourses/logo300x300.png");

		// yieldLogowText = new Sprite("/engine/resourses/logoctext.png");

		good = new Sprite("/engine/resourses/emojis/good.png");

		bad = new Sprite("/engine/resourses/emojis/bad.png");

		requestFocus();

	}

	// Fechar a janela do jogo
	public static void closeWindow() {
		System.out.println("Window Closed in: " + Loop.getTotalFrames() + " Frame");
		System.exit(1);
	}

	void panelRender() {
		if (YieldMain.getYieldPanel()) {
			Graphics g = YieldMain.getScreenMain().getMainGraphics();
			Graphics g2 = (Graphics2D) g;

			g2.setColor(new Color(0, 0, 0, 200));
			g.fillRect(0, 0, Settings.getGame_WIDTH(), 80);

			/*
			 * g2.setColor(new Color(150, 150, 150, 100)); g.fillRect(0, 0, 800, 600);
			 */

			// g.setFont(new Font("century gothic", 0, 30));
			g.setFont(new Font("arial", 0, 8));

			g2.setColor(new Color(50, 50, 50, 200));
			g.fillRect(0, 0, Settings.getGame_WIDTH(), 15);

			yieldLogo.draw(2, 2, 10, 10);

			g.setColor(Color.white);

			g.drawString("yield V" + YieldMain.getVersion(), 20, 12);

			g.drawString(String.format("%.0f", Time.totalMilliSeconds() / 1000), Settings.getGame_WIDTH() - 10
					- ((String.format("%.0f", Time.totalMilliSeconds() / 1000).length() * 5)), 12);

			if (YieldMain.getErrors().size() == 0) {
				int width = 55;
				int height = 50;
				g2.setColor(new Color(50, 50, 50, 200));
				g.fillRect(0, 20, width, height);

				good.draw(5, 25, width - 10, height - 10);
			} else {

				int widthe = 55;
				int heighte = 50;
				int width = 0;
				g.setFont(new Font("arial", Font.BOLD, 20));
				for (int i = 0; i < YieldMain.getErrors().size(); i++) {
					int s = g2.getFontMetrics().stringWidth(YieldMain.getErrors().get(i));
					if (s > width - 20) {
						width = s + 20;
					}
				}
				int height = 170;
				g2.setColor(new Color(50, 50, 50, 200));
				g.fillRect(0, 20, width, height);

				g2.setColor(new Color(50, 50, 50, 200));

				bad.draw(5, 25, widthe - 10, heighte - 10);

				int ox = 0;
				int oy = 80;
				g.setColor(new Color(255, 100, 100));
				if (YieldMain.getErrors().size() > 0)
					g.drawString(YieldMain.getErrors().get(0), 10 + ox, 25 + oy);
				if (YieldMain.getErrors().size() > 1)
					g.drawString(YieldMain.getErrors().get(1), 10 + ox, 25 * 2 + oy);
				if (YieldMain.getErrors().size() > 2)
					g.drawString(YieldMain.getErrors().get(2), 10 + ox, 25 * 3 + oy);
				if (YieldMain.getErrors().size() > 3)
					g.drawString("...", 10 + ox, 25 * 4 + oy);
			}

			// FPS COUNTER

			g.setColor(Color.white);
			g.setFont(new Font("century gothic", 0, 30));
			g.drawString(String.valueOf((int) Time.FPS),
					Settings.getGame_WIDTH() - 5 - (String.format("%.0f", Time.FPS).length() * 20), 65);
			g.setFont(new Font("century gothic", 0, 15));
			g.drawString((String.format("%.1f", Time.deltaTime())).replace(",", "."),
					Settings.getGame_WIDTH() - 18 - (String.format("%.0f", Time.deltaTime()).length() * 20), 35);

			// yieldLogowText.draw(10, 10, 115, 50, g);
		}
	}

	public void render() {
		if (outch != 0) {
			double v = ((outch - 0) / 100);
			if (v > 0.001 || v < -0.001)
				outch -= outchforce * v * Time.deltaTime() * (120 / Time.FPS);
		}

		// A imagem da janela
		image = new BufferedImage(Settings.getGame_WIDTH(), Settings.getGame_HEIGHT(), BufferedImage.TYPE_INT_RGB);

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = YieldMain.getScreenMain().getImage().getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, Settings.getGame_WIDTH(), Settings.getGame_HEIGHT());

		/* Rederezacao do jogo */
		Graphics2D g2 = (Graphics2D) g;

		AffineTransform oldXForm = g2.getTransform();

		mainGraphics = g;

		Yield.g = mainGraphics;

		for (int i = 0; i < YieldMain.getObjects().size(); i++) {
			Yield obj = YieldMain.getObjects().get(i);
			if (obj.render == true) {

				obj.Render1();
				obj.Render2();
				obj.Render3();
				obj.Render4();
				obj.Render5();
				obj.Render6();
				obj.Render7();
				obj.Render8();
				obj.Render9();
				obj.Render10();
				obj.Render11();
				obj.Render12();
				obj.Render13();
				obj.Render14();
				obj.Render();

				g2.setTransform(oldXForm);
			}
		}

		requestFocus();
		
		panelRender();

		/**/
		g.dispose();
		g = bs.getDrawGraphics();
		// g2.setColor(new Color(0,0,0,255));
		
		if(YieldMain.getWindow().isFullscreen()) {
			g.drawImage(YieldMain.getScreenMain().getImage(),
					0 - (int) ((YieldMain.getScreenMain().getOutch() / 2) + YieldMain.getScreenMain().getOfx()),
					0 - (int) ((YieldMain.getScreenMain().getOutch() / 2) + YieldMain.getScreenMain().getOfy()),
					(int) ((int) (Toolkit.getDefaultToolkit().getScreenSize().width) + YieldMain.getScreenMain().getOutch()),
					(int) ((int) (Toolkit.getDefaultToolkit().getScreenSize().height) + YieldMain.getScreenMain().getOutch()), null);
		} else {
			g.drawImage(YieldMain.getScreenMain().getImage(),
					0 - (int) ((YieldMain.getScreenMain().getOutch() / 2) + YieldMain.getScreenMain().getOfx()),
					0 - (int) ((YieldMain.getScreenMain().getOutch() / 2) + YieldMain.getScreenMain().getOfy()),
					(int) ((int) (Settings.getWindow_WIDTH()) + YieldMain.getScreenMain().getOutch()),
					(int) ((int) (Settings.getWindow_HEIGHT()) + YieldMain.getScreenMain().getOutch()), null);
		}

		bs.show();
	}

}
