package com.github.shiayanga.pojo;

public class LabelQrcode {
    private Integer size;
    private String text;
    private Integer x;
    private Integer y;

    public LabelQrcode(Integer size, String text, Integer x, Integer y) {
        this.size = size;
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
