package com.github.shiayanga;

public class MatrixToImageConfig {
    public static final int BLACK = -16777216;
    public static final int WHITE = -1;
    private int onColor;
    private int offColor;


    public MatrixToImageConfig(){
        this(-16777216,-1);
    }

    public MatrixToImageConfig(int onColor, int offColor){
        this.offColor = offColor;
        this.onColor = onColor;
    }

    public int getPixelOnColor(){
        return this.onColor;
    }
    public void setOnColor(int onColor){
        this.onColor = onColor;
    }
    public void setOffColor(int offColor){
        this.offColor = offColor;
    }

    public int getPixelOffColor(){
        return this.offColor;
    }

    public int getBufferedImageColorModel(){
        if (this.onColor == BLACK && this.offColor == WHITE){
            return 12;
        }
        if (hasTransparency(this.onColor) || hasTransparency(this.offColor)){
            return 2;
        }
        return 1;
    }

    private static boolean hasTransparency(int argb){
        return (argb & 0xFF000000) != -16777216;
    }
}
