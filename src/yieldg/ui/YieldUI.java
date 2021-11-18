package yieldg.ui;

import java.awt.Color;

import jdk.jfr.Experimental;
import yield.YldApp;
import yield.YldBody;
import yield.YldScript;
import yield.util.YldAudio;
import yield.util.input.YldInput;
import yieldg.YldSprite;
import yieldg.ui.canvas.YldCanvas;
import yieldg.ui.image.YldCanvasImage;

@Experimental
/**NOT FINISHED*/
public class YieldUI {

    private YldCanvas uiCanvas = new YldCanvas();
    private YldPalette uiPalette = new YldPalette();
    private YldSlider volumeSlider = new YldSlider();
    private boolean masterVolume = true;
    private YldCanvasImage volumeSliderImage = new YldCanvasImage(new YldSprite("yieldui/VolumeSlider.png")), menuImage = new YldCanvasImage(new YldSprite("yieldui/YieldMenu.png"));
    private String keyVolumeUp = "Equals", keyVolumeDown = "Minus", menuShortcut = "Alt:M;";
    private int volumeFramesMax = (int) (120 * (YldApp.yld.getYldLogicEngine().getTPS() / 60)), volumeFrames = volumeFramesMax;

    YldBody uiBody = new YldBody() {
        @Override
        public void awake() {
            uiPalette.auxColor2 = Color.magenta;
            volumeSlider.setPalette(uiPalette);
            addScript(volumeSliderImage);
            addScript(volumeSlider);
            volumeSlider.setX(YldApp.yld.width / 2 - volumeSlider.getWidth() / 2);
            volumeSliderImage.setX(YldApp.yld.width / 2 - volumeSliderImage.getSprite().width / 2);
            volumeSlider.setY(volumeSliderImage.getSprite().height / 2.2);
            volumeSlider.setCanvasLayer(3);
            volumeSliderImage.setCanvasLayer(0);
            menuImage.setX(YldApp.yld.width - menuImage.getSprite().width);
            menuImage.setDraw(false);
            //volumeSlider.setClickerX(volumeSlider.getX() + volumeSlider.getWidth() / 2);

            uiCanvas.addScript(volumeSlider);
            uiCanvas.addScript(volumeSliderImage);
            uiCanvas.addScript(menuImage);

            addScript(new YldScript() {

                private boolean canPressUp, up, canPressDown, down, canMenuShortcut, menu;

                @Override
                public String tag() {
                    return "YieldUIScript";
                }

                @Override
                public void start() {
                    setLayer(0);
                }

                @Override
                public void tick() {

                    if (masterVolume) {
                        YldAudio.setMasterVolume((float) (volumeSlider.getValue() * 100.0));
                        volumeFrames++;
                        if (volumeFrames > volumeFramesMax) {
                            volumeSlider.setDraw(false);
                            volumeSliderImage.setDraw(false);
                        } else {
                            volumeSlider.setDraw(true);
                            volumeSliderImage.setDraw(true);
                        }
                    } else {
                        volumeSlider.setDraw(false);
                        volumeSliderImage.setDraw(false);
                    }

                    if (YldInput.isKeyPressed(keyVolumeUp)) {
                        if (canPressUp)
                            up = true;
                        canPressUp = false;
                    } else {
                        canPressUp = true;
                    }

                    if (YldInput.isKeyPressed(keyVolumeDown)) {
                        if (canPressDown)
                            down = true;
                        canPressDown = false;
                    } else {
                        canPressDown = true;
                    }

                    if (YldInput.isKeyPressed(menuShortcut)) {
                        if (canMenuShortcut)
                            menu = true;
                        canMenuShortcut = false;
                    } else {
                        canMenuShortcut = true;
                    }

                    if (volumeSlider.isPressed() && masterVolume && volumeSlider.isDraw()) {
                        volumeFrames = 0;
                    }

                    if(menu) {
                        menu = false;
                        if(menuImage.isDraw()) {
                            menuImage.setDraw(false);
                        } else {
                            menuImage.setDraw(true);
                        }
                    }

                    if (up) {
                        up = false;
                        volumeFrames = 0;
                        volumeSlider.setClickerX(volumeSlider.getClickerX() + 20);
                    }

                    if (down) {
                        down = false;
                        volumeFrames = 0;
                        volumeSlider.setClickerX(volumeSlider.getClickerX() - 20);
                    }

                }
            });
        }
    };


}
