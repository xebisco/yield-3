package yieldg.ui;

import java.awt.*;

public class YldPalette {

    private static YldPalette globalPallete;

    private Color bgColor = Color.black, auxColor1 = Color.white, auxColor2 = Color.green, auxColor3 = Color.red,
            auxColor4 = Color.gray, auxColor5 = Color.darkGray;
    private Font mainFont = new Font("arial", Font.BOLD, 50), auxFont1 = new Font("arial", 0, 20),
            auxFont2 = new Font("arial", Font.ITALIC, 20);

    public YldPalette() {
        if (globalPallete == null)
            globalPallete = this;
    }

    public static YldPalette getGlobalPallete() {
        return globalPallete;
    }

    public static void setGlobalPallete(YldPalette globalPallete) {
        YldPalette.globalPallete = globalPallete;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Color getAuxColor1() {
        return auxColor1;
    }

    public void setAuxColor1(Color auxColor1) {
        this.auxColor1 = auxColor1;
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

    public Color getAuxColor5() {
        return auxColor5;
    }

    public void setAuxColor5(Color auxColor5) {
        this.auxColor5 = auxColor5;
    }

    public Font getMainFont() {
        return mainFont;
    }

    public void setMainFont(Font mainFont) {
        this.mainFont = mainFont;
    }

    public Font getAuxFont1() {
        return auxFont1;
    }

    public void setAuxFont1(Font auxFont1) {
        this.auxFont1 = auxFont1;
    }

    public Font getAuxFont2() {
        return auxFont2;
    }

    public void setAuxFont2(Font auxFont2) {
        this.auxFont2 = auxFont2;
    }

}
