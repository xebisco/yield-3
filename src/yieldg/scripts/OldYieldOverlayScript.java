package yieldg.scripts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import yield.engine.YieldApp;
import yield.engine.systems.OldOverlaySystem;
import yield.engine.systems.SecondarySpriteSystem;
import yield.main.YieldScript;
import yield.util.Audio;
import yield.util.Mouse;
import yield.util.Time;
import yieldg.main.Sprite;

public class OldYieldOverlayScript extends YieldScript {

	@Override
	public String tag() {
		return "YieldOverlayScript";
	}

	public OldOverlaySystem overlaySystem;

	private Color bgColor = new Color(0, 0, 0, 100);
	private Color auxColor1 = new Color(60, 60, 70, 230);
	private Font yieldTitleFont = new Font("arial", 0, 50);
	private Font textFont1 = new Font("arial", 0, 25);
	private Font textFont2 = new Font("arial", 0, 20);
	private String yieldTitle = "yield";
	private Color textColor = Color.white;
	private Color auxColor2 = new Color(80, 0, 150);
	private Color auxColor3 = new Color(50, 0, 100);
	private Color auxColor4 = new Color(150, 150, 150);
	// private Color auxColor3 = new Color(40, 40, 40, 230);
	private Sprite yieldLogo;
	private boolean tips;
	private int tipsF;
	private int framesToDesapear = 200;
	private String tipsString;
	private int tipsWidth, tipsHeight;
	private String exitString = " Exit ";
	private String finishString = "Close App";
	private String ffinishString = "Force Close App";
	private String fullscreenString = "Switch Fullscreen";
	private int exitButtonX = 250, exitButtonY = 370, exitButtonWidth, exitButtonHeight;
	private int finishButtonX = 50, finishButtonY = 370, finishButtonWidth, finishButtonHeight;
	private int ffinishButtonX = 50, ffinishButtonY = 420, ffinishButtonWidth, ffinishButtonHeight;
	private int fullscreenButtonX = 50, fullscreenButtonY = 470, fullscreenButtonWidth, fullscreenButtonHeight;
	private int cX = 30, cY = 200, cWidth = 325, cHeight = 140;
	private double xft;
	private double xfts = 30;
	private double sxft = -500;
	private Audio openOverlayAudio;
	// private Sprite

	@Override
	public void awake() {
		openOverlayAudio = new Audio("/pullyield.wav");
		YieldApp.app().getYieldScripts().add(this);
		yieldLogo = new Sprite("yieldlogo");
		tips = true;
	}

	@Override
	public void start() {
		setXft(getSxft());
		framesToDesapear = framesToDesapear * (YieldApp.app().getTPS() / 30);
	}

	@Override
	public void tick() {
		if(overlaySystem != null) {
			if(overlaySystem.isCanActive() == true) {
				if (getFrames() < 5) {
					setXft(getSxft());
				}
				tipsString = " Press " + KeyEvent.getKeyText(OldOverlaySystem.getDefaultKey())
						+ " or click here to open Yield Overlay!";
				if (xft != 0) {
					double v = ((xft) / 100d);
					if (xft > 0.001d || xft < -0.001d)
						xft -= (xfts * v) / (YieldApp.app().getTPS() / 30);
				}
				if (!overlaySystem.isCanActive()) {
					tips = false;
				}
				if (overlaySystem.isActive()) {
					if (Mouse.isTouching(ffinishButtonX, ffinishButtonY, ffinishButtonWidth, ffinishButtonHeight)
							&& Mouse.isClicking()) {
						try {
							Thread.sleep(1000);
							YieldApp.app().getWindow().dispose();
							YieldApp.app().setWindow(null);
							YieldApp.app().setRunning(false);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					if (Mouse.isTouching(finishButtonX, finishButtonY, finishButtonWidth, finishButtonHeight)
							&& Mouse.isClicking()) {
						YieldApp.app().getWindow().setEnabled(false);
						YieldApp.app().getWindow().setVisible(false);
						YieldApp.app().getWindow().dispose();
						YieldApp.app().setRunning(false);
					}

					if (Mouse.isTouching(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight) && Mouse.isClicking()) {
						overlaySystem.setActive(false);
						setTipsF(getFrames());
						setTips(true);
						setXft(getSxft());
					}
					
					if (Mouse.isTouching(fullscreenButtonX, fullscreenButtonY, fullscreenButtonWidth, fullscreenButtonHeight) && Mouse.isClicking()) {
						overlaySystem.setActive(false);
						setTipsF(getFrames());
						setTips(true);
						setXft(getSxft());
						if(YieldApp.app().getWindow().isFullscreen()) {
							YieldApp.app().getWindow().exitFullscreen();
						} else {
							YieldApp.app().getWindow().toFullscreen();
						}
					}
				} else {
					if (Mouse.isTouching(0, 0, tipsWidth, tipsHeight) && Mouse.isClicking() && tips) {
						overlaySystem.setActive(true);
						setXft(getSxft());
						getOpenOverlayAudio().play();
					}
				}
				if (getFrames() - tipsF > framesToDesapear) {
					tips = false;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(overlaySystem != null) {
		if(overlaySystem.isCanActive() && getFrames() > 30) {
			if (overlaySystem.isActive()) {
				g.setColor(bgColor);
				g.fillRect(0, 0, YieldApp.app().getWidth(), YieldApp.app().getHeight());
				g.setColor(auxColor1);
				g.fillRect(10 + (int) xft, 10, 370, 525);

				g.setFont(yieldTitleFont);
				g.setColor(textColor);
				g.drawString(yieldTitle, 45 + g.getFont().getSize() + (int) xft, g.getFont().getSize() + 25);
				SecondarySpriteSystem.draw(yieldLogo, 40 + (int) xft, 35, g.getFont().getSize(), g.getFont().getSize(), g);
				g.setFont(textFont1);
				g.drawString("TPS = " + YieldApp.app().getTPS() + "        RPS = " + YieldApp.app().getRPS(),
						40 + (int) xft, (yieldTitleFont.getSize() * 1) + 15 + (g.getFont().getSize() * 2));
				g.drawString("STPS = " + Time.getSlowTickPS() + "     SRPS = " + Time.getSlowRenderPS(), 40 + (int) xft,
						(yieldTitleFont.getSize() * 1) + 15 + (g.getFont().getSize() * 3));
				g.drawString("FTPS = " + Time.getTickPS() + "     FRPS = " + Time.getRenderPS(), 40 + (int) xft,
						(yieldTitleFont.getSize() * 1) + 15 + (g.getFont().getSize() * 4));

				if (Mouse.isTouching(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight)) {
					g.setColor(auxColor3);
				} else {
					g.setColor(auxColor2);
				}
				g.fillRect(exitButtonX + (int) xft, exitButtonY, (int) (g.getFontMetrics().stringWidth(exitString) * 1.5),
						g.getFont().getSize() + 10);
				g.setColor(textColor);
				g.drawString(exitString,
						exitButtonX + (int) (g.getFontMetrics().stringWidth(exitString) * 0.25) + (int) xft,
						exitButtonY + g.getFont().getSize());
				exitButtonWidth = (int) (g.getFontMetrics().stringWidth(exitString) * 1.5);
				exitButtonHeight = g.getFont().getSize() + 10;

				if (Mouse.isTouching(finishButtonX, finishButtonY, finishButtonWidth, finishButtonHeight)) {
					g.setColor(auxColor3);
				} else {
					g.setColor(auxColor2);
				}
				g.fillRect(finishButtonX + (int) xft, finishButtonY,
						(int) (g.getFontMetrics().stringWidth(finishString) * 1.5), g.getFont().getSize() + 10);
				g.setColor(textColor);
				g.drawString(finishString,
						finishButtonX + (int) (g.getFontMetrics().stringWidth(finishString) * 0.25) + (int) xft,
						finishButtonY + g.getFont().getSize());
				finishButtonWidth = (int) (g.getFontMetrics().stringWidth(finishString) * 1.5);
				finishButtonHeight = g.getFont().getSize() + 10;

				if (Mouse.isTouching(ffinishButtonX, ffinishButtonY, ffinishButtonWidth, ffinishButtonHeight)) {
					g.setColor(auxColor3);
				} else {
					g.setColor(auxColor2);
				}
				g.fillRect(ffinishButtonX + (int) xft, ffinishButtonY,
						(int) (g.getFontMetrics().stringWidth(ffinishString) * 1.5), g.getFont().getSize() + 10);
				g.setColor(textColor);
				g.drawString(ffinishString,
						ffinishButtonX + (int) (g.getFontMetrics().stringWidth(ffinishString) * 0.25) + (int) xft,
						ffinishButtonY + g.getFont().getSize());
				ffinishButtonWidth = (int) (g.getFontMetrics().stringWidth(ffinishString) * 1.5);
				ffinishButtonHeight = g.getFont().getSize() + 10;
				
				if (Mouse.isTouching(fullscreenButtonX, fullscreenButtonY, fullscreenButtonWidth, fullscreenButtonHeight)) {
					g.setColor(auxColor3);
				} else {
					g.setColor(auxColor2);
				}
				g.fillRect(fullscreenButtonX + (int) xft, fullscreenButtonY,
						(int) (g.getFontMetrics().stringWidth(fullscreenString) * 1.5), g.getFont().getSize() + 10);
				g.setColor(textColor);
				g.drawString(fullscreenString,
						fullscreenButtonX + (int) (g.getFontMetrics().stringWidth(fullscreenString) * 0.25) + (int) xft,
						fullscreenButtonY + g.getFont().getSize());
				fullscreenButtonWidth = (int) (g.getFontMetrics().stringWidth(fullscreenString) * 1.5);
				fullscreenButtonHeight = g.getFont().getSize() + 10;

				g.setColor(auxColor4);
				g.fillRect(cX + (int) xft, cY, cWidth, cHeight);

				g.setColor(auxColor3);

				for (int i = 0; i < Time.getTPSs().size(); i++) {
					if (i > 1) {
						int y = (Time.getTPSs().get(i) - YieldApp.app().getTPS()) + (cY + cHeight / 2);
						int x = cX + cWidth / (Time.getTPSs().size() - i);

						int y1 = (Time.getTPSs().get(i - 1) - YieldApp.app().getTPS()) + (cY + cHeight / 2);
						int x1 = 0;
						if (Time.getTPSs().size() - i - 1 != 0)
							x1 = cX + cWidth / (Time.getTPSs().size() - i - 1);

						if (x < cX) {
							x = cX;
						}
						if (y < cY) {
							y = cY;
						}
						if (y > cY + cHeight) {
							y = cY + cHeight;
						}

						if (x1 < cX && x1 != 0) {
							x1 = cX;
						}
						if (y1 < cY) {
							y1 = cY;
						}
						if (y1 > cY + cHeight) {
							y1 = cY + cHeight;
						}

						if (x1 != 0)
							g.drawLine(x1 + (int) xft, y1, x + (int) xft, y);
					}
				}

				g.setColor(auxColor2);

				for (int i = 0; i < Time.getRPSs().size(); i++) {
					if (i > 5) {
						int y = Time.getRPSs().get(i) - YieldApp.app().getRPS() + cY + cHeight / 2;
						int x = cX + cWidth / (Time.getRPSs().size() - i);

						int y1 = Time.getRPSs().get(i - 1) - YieldApp.app().getRPS() + cY + cHeight / 2;
						int x1 = 0;
						if (Time.getRPSs().size() - i - 2 != 0)
							x1 = cX + cWidth / (Time.getRPSs().size() - i - 2);

						if (x < cX) {
							x = cX;
						}
						if (y < cY) {
							y = cY;
						}
						if (y > cY + cHeight) {
							y = cY + cHeight;
						}

						if (x1 < cX && x1 != 0) {
							x1 = cX;
						}
						if (y1 < cY) {
							y1 = cY;
						}
						if (y1 > cY + cHeight) {
							y1 = cY + cHeight;
						}

						if (x1 != 0)
							g.drawLine(x1 + (int) xft, y1, x + (int) xft, y);
					}
				}

				g.setColor(textColor);
				g.setFont(textFont2);
				g.drawString("vto @2021", 10, YieldApp.app().getHeight() - 10);
			} else {
				if (tips) {
					g.setFont(textFont2);
					g.setColor(bgColor);
					g.fillRect((int) xft, 0, g.getFontMetrics().stringWidth(tipsString), g.getFont().getSize() + 5);
					g.setColor(auxColor2);
					g.fillRect((int) xft, 0,
							(int) (g.getFontMetrics().stringWidth(tipsString) - (((double) (getFrames() - tipsF)
									/ (double) framesToDesapear * g.getFontMetrics().stringWidth(tipsString)))),
							g.getFont().getSize() + 5);
					g.setColor(textColor);
					g.drawString(tipsString, (int) xft, g.getFont().getSize());
					tipsWidth = g.getFontMetrics().stringWidth(tipsString);
					tipsHeight = g.getFont().getSize() + 5;
				}
			}
		}
		}
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public Sprite getYieldLogo() {
		return yieldLogo;
	}

	public void setYieldLogo(Sprite yieldLogo) {
		this.yieldLogo = yieldLogo;
	}

	public OldOverlaySystem getOverlaySystem() {
		return overlaySystem;
	}

	public void setOverlaySystem(OldOverlaySystem overlaySystem) {
		this.overlaySystem = overlaySystem;
	}

	public Color getAuxColor1() {
		return auxColor1;
	}

	public void setAuxColor1(Color auxColor1) {
		this.auxColor1 = auxColor1;
	}

	public Font getYieldTitleFont() {
		return yieldTitleFont;
	}

	public void setYieldTitleFont(Font yieldTitleFont) {
		this.yieldTitleFont = yieldTitleFont;
	}

	public Font getTextFont1() {
		return textFont1;
	}

	public void setTextFont1(Font textFont1) {
		this.textFont1 = textFont1;
	}

	public Font getTextFont2() {
		return textFont2;
	}

	public void setTextFont2(Font textFont2) {
		this.textFont2 = textFont2;
	}

	public String getYieldTitle() {
		return yieldTitle;
	}

	public void setYieldTitle(String yieldTitle) {
		this.yieldTitle = yieldTitle;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getAuxColor2() {
		return auxColor2;
	}

	public void setAuxColor2(Color auxColor2) {
		this.auxColor2 = auxColor2;
	}

	public Color getAuxColor3() {
		return auxColor3;
	}

	public void setAuxColor3(Color auxColor3) {
		this.auxColor3 = auxColor3;
	}

	public Color getAuxColor4() {
		return auxColor4;
	}

	public void setAuxColor4(Color auxColor4) {
		this.auxColor4 = auxColor4;
	}

	public boolean isTips() {
		return tips;
	}

	public void setTips(boolean tips) {
		this.tips = tips;
	}

	public int getFramesToDesapear() {
		return framesToDesapear;
	}

	public void setFramesToDesapear(int framesToDesapear) {
		this.framesToDesapear = framesToDesapear;
	}

	public String getTipsString() {
		return tipsString;
	}

	public void setTipsString(String tipsString) {
		this.tipsString = tipsString;
	}

	public String getExitString() {
		return exitString;
	}

	public void setExitString(String exitString) {
		this.exitString = exitString;
	}

	public int getExitButtonX() {
		return exitButtonX;
	}

	public void setExitButtonX(int exitButtonX) {
		this.exitButtonX = exitButtonX;
	}

	public int getExitButtonY() {
		return exitButtonY;
	}

	public void setExitButtonY(int exitButtonY) {
		this.exitButtonY = exitButtonY;
	}

	public int getExitButtonWidth() {
		return exitButtonWidth;
	}

	public void setExitButtonWidth(int exitButtonWidth) {
		this.exitButtonWidth = exitButtonWidth;
	}

	public int getExitButtonHeight() {
		return exitButtonHeight;
	}

	public void setExitButtonHeight(int exitButtonHeight) {
		this.exitButtonHeight = exitButtonHeight;
	}

	public int getcX() {
		return cX;
	}

	public void setcX(int cX) {
		this.cX = cX;
	}

	public int getcY() {
		return cY;
	}

	public void setcY(int cY) {
		this.cY = cY;
	}

	public int getcWidth() {
		return cWidth;
	}

	public void setcWidth(int cWidth) {
		this.cWidth = cWidth;
	}

	public int getcHeight() {
		return cHeight;
	}

	public void setcHeight(int cHeight) {
		this.cHeight = cHeight;
	}

	public double getXft() {
		return xft;
	}

	public void setXft(double xft) {
		this.xft = xft;
	}

	public double getSxft() {
		return sxft;
	}

	public void setSxft(double sxft) {
		this.sxft = sxft;
	}

	public double getXfts() {
		return xfts;
	}

	public void setXfts(double xfts) {
		this.xfts = xfts;
	}

	public int getTipsF() {
		return tipsF;
	}

	public void setTipsF(int tipsF) {
		this.tipsF = tipsF;
	}

	public String getFinishString() {
		return finishString;
	}

	public void setFinishString(String finishString) {
		this.finishString = finishString;
	}

	public int getFinishButtonX() {
		return finishButtonX;
	}

	public void setFinishButtonX(int finishButtonX) {
		this.finishButtonX = finishButtonX;
	}

	public int getFinishButtonY() {
		return finishButtonY;
	}

	public void setFinishButtonY(int finishButtonY) {
		this.finishButtonY = finishButtonY;
	}

	public int getFinishButtonWidth() {
		return finishButtonWidth;
	}

	public void setFinishButtonWidth(int finishButtonWidth) {
		this.finishButtonWidth = finishButtonWidth;
	}

	public int getFinishButtonHeight() {
		return finishButtonHeight;
	}

	public void setFinishButtonHeight(int finishButtonHeight) {
		this.finishButtonHeight = finishButtonHeight;
	}

	public int getTipsWidth() {
		return tipsWidth;
	}

	public void setTipsWidth(int tipsWidth) {
		this.tipsWidth = tipsWidth;
	}

	public int getTipsHeight() {
		return tipsHeight;
	}

	public void setTipsHeight(int tipsHeight) {
		this.tipsHeight = tipsHeight;
	}

	public String getFfinishString() {
		return ffinishString;
	}

	public void setFfinishString(String ffinishString) {
		this.ffinishString = ffinishString;
	}

	public String getFullscreenString() {
		return fullscreenString;
	}

	public void setFullscreenString(String fullscreenString) {
		this.fullscreenString = fullscreenString;
	}

	public int getFfinishButtonX() {
		return ffinishButtonX;
	}

	public void setFfinishButtonX(int ffinishButtonX) {
		this.ffinishButtonX = ffinishButtonX;
	}

	public int getFfinishButtonY() {
		return ffinishButtonY;
	}

	public void setFfinishButtonY(int ffinishButtonY) {
		this.ffinishButtonY = ffinishButtonY;
	}

	public int getFfinishButtonWidth() {
		return ffinishButtonWidth;
	}

	public void setFfinishButtonWidth(int ffinishButtonWidth) {
		this.ffinishButtonWidth = ffinishButtonWidth;
	}

	public int getFfinishButtonHeight() {
		return ffinishButtonHeight;
	}

	public void setFfinishButtonHeight(int ffinishButtonHeight) {
		this.ffinishButtonHeight = ffinishButtonHeight;
	}

	public int getFullscreenButtonX() {
		return fullscreenButtonX;
	}

	public void setFullscreenButtonX(int fullscreenButtonX) {
		this.fullscreenButtonX = fullscreenButtonX;
	}

	public int getFullscreenButtonY() {
		return fullscreenButtonY;
	}

	public void setFullscreenButtonY(int fullscreenButtonY) {
		this.fullscreenButtonY = fullscreenButtonY;
	}

	public int getFullscreenButtonWidth() {
		return fullscreenButtonWidth;
	}

	public void setFullscreenButtonWidth(int fullscreenButtonWidth) {
		this.fullscreenButtonWidth = fullscreenButtonWidth;
	}

	public int getFullscreenButtonHeight() {
		return fullscreenButtonHeight;
	}

	public void setFullscreenButtonHeight(int fullscreenButtonHeight) {
		this.fullscreenButtonHeight = fullscreenButtonHeight;
	}

	public Audio getOpenOverlayAudio() {
		return openOverlayAudio;
	}

	public void setOpenOverlayAudio(Audio openOverlayAudio) {
		this.openOverlayAudio = openOverlayAudio;
	}

}
