package com.github.shiayanga.pojo;

import java.awt.*;

public class LabelText {
    private String name;
    private String value;
    private Integer fontSize;
    private Font font;

    private Integer x;
    private Integer y;

    public LabelText(String name, String value, Integer fontSize, Font font, Integer x, Integer y) {
        this.name = name;
        this.value = value;
        this.fontSize = fontSize;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
