package com.github.shiayanga;

import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.Direction;
import com.freewayso.image.combiner.enums.OutputFormat;
import com.freewayso.image.combiner.enums.ZoomMode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

public class DeviceLabel {
    private static final Color bgColor = Color.BLUE;
    private static final String QRCODE_FORMAT = "png";

    public static void main(String[] args) throws Exception {
        //合成器和背景图（整个图片的宽高和相关计算依赖于背景图，所以背景图的大小是个基准）
        ImageCombiner combiner = new ImageCombiner(400, 600, Color.BLUE, OutputFormat.JPG);

        //加入圆形元素-头部留白
        BufferedImage circle = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = circle.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillOval(0,0,800,400);
        graphics.dispose();
        combiner.addImageElement(circle, 0, -230).setCenter(true);

        // 顶部logo
        BufferedImage topLogo = ImageUtil.loadFromUrl("https://mhimg.clewm.net/cli/images/beautify/new/logo/06%E4%B8%AD%E5%9B%BD%E4%B8%AD%E8%BD%A6_500.png");
        combiner.addImageElement(topLogo, 0, 5,80,80,ZoomMode.WidthHeight).setCenter(true).setAlpha(.1f);

        // 标题
        combiner.addTextElement("设备巡检系统","FZLTZHK--GBK1-0",35,0,100).setCenter(true).setDirection(Direction.RightLeft)
                .setColor(Color.RED);

        //加入矩形元素（圆角）-二维码位置
        combiner.addRectangleElement(
                        0,
                        210,
                        combiner.getCanvasWidth()/3*2,
                        combiner.getCanvasWidth()/3*2
                ).setCenter(true)
                .setColor(Color.WHITE)
                .setRoundCorner(50);

        combiner.addTextElement("①设备名称：主通风机\n②设备型号：TF-01HT","FZLTZHK--GBK1-0",30,0,combiner.getCanvasHeight()-90)
                .setColor(Color.WHITE).setDirection(Direction.LeftRight).setAutoBreakLine("\n");
        // combiner.addTextElement("设备型号：TF-01HT","FZLTZHK--GBK1-0",30,0,combiner.getCanvasHeight()-50).setColor(Color.WHITE).setCenter(true);
        // 二维码
        BufferedImage bufferedImage = generateQrCode(combiner.getCanvasWidth()/3*2-20);
        combiner.addImageElement(bufferedImage,0,
                 225, bufferedImage.getWidth() ,bufferedImage.getHeight(),ZoomMode.WidthHeight).setCenter(true);


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
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}

