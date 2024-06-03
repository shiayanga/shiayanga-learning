package com.github.shiayanga;

import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.element.TextElement;
import com.freewayso.image.combiner.enums.OutputFormat;
import com.freewayso.image.combiner.enums.ZoomMode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

public class SpecialShapeLabel {
    private static final Color bgColor = Color.DARK_GRAY;
    private static final String QRCODE_FORMAT = "png";

    public static void main(String[] args) throws Exception {
        //合成器和背景图（整个图片的宽高和相关计算依赖于背景图，所以背景图的大小是个基准）
        ImageCombiner combiner = new ImageCombiner(900, 600, bgColor, OutputFormat.JPG);

        //加入矩形元素
        combiner.addRectangleElement(
                        50,
                        100,
                        600,
                        450)
                .setColor(Color.WHITE).setRoundCorner(40);
        //加入矩形元素
        combiner.addRectangleElement(
                        600,
                        100,
                        250,
                        250)
                .setColor(Color.WHITE).setRoundCorner(40);
        //加入矩形元素
        combiner.addRectangleElement(
                        650,
                        350,
                        100,
                        100)
                .setColor(Color.WHITE);
        //加入矩形元素
        combiner.addRectangleElement(
                        650,
                        350,
                        200,
                        200)
                .setColor(bgColor).setRoundCorner(40);
        //加入矩形元素
        combiner.addRectangleElement(
                        670,
                        370,
                        180,
                        180)
                .setColor(Color.WHITE).setRoundCorner(40);

        combiner.addTextElement("姓名：王强王强","STFangsong",100,100,200);
        /*TextElement textElement = new TextElement();
        textElement.setAutoFitWidth(0);*/

        /*//加入矩形元素（圆角）-二维码位置
        combiner.addRectangleElement(
                        (combiner.getCanvasWidth() - combiner.getCanvasHeight() / 3)-100,
                        combiner.getCanvasHeight() / 4,
                        (combiner.getCanvasHeight() / 3),
                        (combiner.getCanvasHeight() / 3)+150
                )
                .setColor(bgColor)
                .setRoundCorner(50)
                .setBorderSize(1)
                .setAlpha(.8f);


        //加入矩形元素-底部左侧横线
        int bottomCenterY = combiner.getCanvasHeight() - 150;
        int lineHeight = 20;
        int lineWidth = combiner.getCanvasWidth() / 3 - 100;
        int lineCorner = 10;
        combiner.addRectangleElement(100, bottomCenterY, lineWidth, lineHeight)
                .setColor(bgColor).setRoundCorner(lineCorner);
        //加入矩形元素-底部右侧横线
        combiner.addRectangleElement((combiner.getCanvasWidth() / 3) * 2 , bottomCenterY, lineWidth, lineHeight)
                .setColor(bgColor).setRoundCorner(lineCorner);

        // 底部logo
        BufferedImage textToPicture = ImageUtil.loadFromUrl("https://meihua.oss-cn-hangzhou.aliyuncs.com/cli/images/beautify/new/logo/%E7%9F%A5%E4%B9%8E-0210.png");
        combiner.addImageElement(textToPicture, 0, bottomCenterY - 75,150,150,ZoomMode.WidthHeight).setCenter(true);

        // 二维码
        BufferedImage bufferedImage = generateQrCode(combiner.getCanvasHeight() / 3 - 50);
        combiner.addImageElement(bufferedImage,(combiner.getCanvasWidth() - combiner.getCanvasHeight() / 3)-77,
                 combiner.getCanvasHeight() / 4+35, bufferedImage.getWidth() ,bufferedImage.getHeight(),ZoomMode.WidthHeight);

        combiner.addTextElement("扫码查看人员信息",
                "STFangsong",
                50,
                (combiner.getCanvasWidth() - combiner.getCanvasHeight() / 3),
                combiner.getCanvasHeight() / 2+80 + combiner.getCanvasHeight() / 8/2);

        combiner.addTextElement("设备作业人员实名信息卡","STFangsong",150,10,combiner.getCanvasWidth() / 30 + combiner.getCanvasHeight() / 8/2-75).setCenter(true)
                .setColor(Color.WHITE);

        // 证件照
        BufferedImage pic = ImageUtil.loadFromUrl("https://img2.baidu.com/it/u=2871468305,2102417358&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=1200");
        combiner.addImageElement(pic, 100, combiner.getCanvasHeight()/3-100,600,900,ZoomMode.WidthHeight);

        // 人员信息
        combiner.addTextElement("姓名：王强","STFangsong",100,pic.getWidth()+50,combiner.getCanvasHeight()/3);
        combiner.addTextElement("年龄：29","STFangsong",100,pic.getWidth()+50,combiner.getCanvasHeight()/3+150);
        combiner.addTextElement("班组：水泥机械班组","STFangsong",100,pic.getWidth()+50,combiner.getCanvasHeight()/3+300);
        combiner.addTextElement("工种：设备操作员","STFangsong",100,pic.getWidth()+50,combiner.getCanvasHeight()/3+450);
        combiner.addTextElement("进场时间：2022-08-07","STFangsong",100,pic.getWidth()+50,combiner.getCanvasHeight()/3+600);
*/
        //执行图片合并
        BufferedImage combine = combiner.combine();

        ImageUtil.writeToFile(combine,QRCODE_FORMAT,"/Users/liyang/Projects/github/shiayanga-learning/qrCodeImages/" + new Date().getTime() + "." + QRCODE_FORMAT);

    }

    public static BufferedImage generateQrCode(Integer size) {
        String url = "https://www.baidu.com/";
        HashMap<EncodeHintType, Object> hints = new HashMap<>();
        // 指定字符集
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        // 指定边缘的宽度
        hints.put(EncodeHintType.MARGIN, 1);
        // 指定纠错级别。纠错级别越高，可以修正的错误就越多，但二维码的密度也越大。可选值包括L，M，Q和H，表示从7%到30%的纠错能力
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, size, size, hints);
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(Color.green.getRGB(), Color.WHITE.getRGB());
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
            Graphics2D graphics = bufferedImage.createGraphics();
            BufferedImage logo = ImageUtil.loadFromUrl("https://meihua.oss-cn-hangzhou.aliyuncs.com/cli/images/beautify/new/logo/%E7%9F%A5%E4%B9%8E-0210.png");
            Image logoImg = logo.getScaledInstance(logo.getWidth() / 5, logo.getHeight() / 5, Image.SCALE_AREA_AVERAGING);
            int x = (bufferedImage.getWidth() - logoImg.getWidth(null))/2;
            int y = (bufferedImage.getHeight() - logoImg.getHeight(null))/2;
            graphics.drawImage(logoImg,x,y,logoImg.getWidth(null),logoImg.getHeight(null),null);
            graphics.setStroke(new BasicStroke(1));
            graphics.dispose();
            logoImg.flush();
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}

