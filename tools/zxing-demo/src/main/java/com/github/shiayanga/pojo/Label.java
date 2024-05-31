package com.github.shiayanga.pojo;


import java.util.List;

public class Label {
    private String backgroundColor;
    private String backgroundImage;

    private Integer labelWidth;

    private Integer labelHeight;
    private List<LabelText> labelText;
    private LabelQrcode labelQrcode;

    public Label(String backgroundColor, String backgroundImage, Integer labelWidth, Integer labelHeight, List<LabelText> labelText, LabelQrcode labelQrcode) {
        this.backgroundColor = backgroundColor;
        this.backgroundImage = backgroundImage;
        this.labelWidth = labelWidth;
        this.labelHeight = labelHeight;
        this.labelText = labelText;
        this.labelQrcode = labelQrcode;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Integer getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(Integer labelWidth) {
        this.labelWidth = labelWidth;
    }

    public Integer getLabelHeight() {
        return labelHeight;
    }

    public void setLabelHeight(Integer labelHeight) {
        this.labelHeight = labelHeight;
    }

    public List<LabelText> getLabelText() {
        return labelText;
    }

    public void setLabelText(List<LabelText> labelText) {
        this.labelText = labelText;
    }

    public LabelQrcode getLabelQrcode() {
        return labelQrcode;
    }

    public void setLabelQrcode(LabelQrcode labelQrcode) {
        this.labelQrcode = labelQrcode;
    }
}
