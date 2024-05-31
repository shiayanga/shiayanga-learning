package com.github.shiayanga;

import com.alibaba.fastjson2.JSON;
import com.github.shiayanga.pojo.Label;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *  _____ _     _
 * /  ___| |   (_)
 * \ `--.| |__  _  __ _ _   _  __ _ _ __   __ _  __ _
 *  `--. \ '_ \| |/ _` | | | |/ _` | '_ \ / _` |/ _` |
 * /\__/ / | | | | (_| | |_| | (_| | | | | (_| | (_| |
 * \____/|_| |_|_|\__,_|\__, |\__,_|_| |_|\__, |\__,_|
 *                       __/ |             __/ |
 *                      |___/             |___/
 *
 */
public class Main {
    private static final int QRCODE_WIDTH = 1600;
    private static final int QRCODE_HEIGHT = 1600;
    private static final int QRCODE_PADDING = 20;
    private static final String QRCODE_LOGO = "http://gips3.baidu.com/it/u=3886271102,3123389489&fm=3028&app=3028&f=JPEG&fmt=auto?w=1280&h=960";
    private static final String QRCODE_FORMAT = "png";

    public static final String LABEL = "{\"background_color\":\"000000\",\"background_image\":\"https://img0.baidu.com/it/u=539050338,270456708&fm=253&fmt=auto&app=138&f=PNG?w=796&h=500\",\"label_width\":8560,\"label_height\":5398,\"label_text\":[{\"name\":\"建筑工程人员信息\",\"font_size\":12,\"font\":\"default\",\"x\":100,\"y\":50},{\"name\":\"姓名\",\"value\":\"张艳丽\",\"font_size\":12,\"font\":\"default\",\"x\":100,\"y\":200}],\"label_qrcode\":{\"size\":200,\"text\":\"https://blog.51cto.com/u_16175471/8666546\",\"x\":500,\"y\":300}}\n";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ArrayList<BufferedImage> images = new ArrayList<>();
        System.out.println(start);
        for (int i = 0; i < 1; i++) {
            System.out.print(i + " ");
            generateLabel(LABEL);
            /*BufferedImage bufferedImage = generateQrCode();
            images.add(bufferedImage);*/
            // ImageUtil.writeToFile(bufferedImage,QRCODE_FORMAT,"/Users/liyang/Projects/github/shiayanga-learning/qrCodeImages/"+ new Date().getTime() + "." + QRCODE_FORMAT);
        }
        System.out.println();
        long end = System.currentTimeMillis();
        System.out.println(end);

        System.out.print(new BigDecimal(end-start).divide(new BigDecimal(1000)) + "秒");
    }

    public static BufferedImage generateQrCode() {
        String url = "https://www.baidu.com/";
        HashMap<EncodeHintType, Object> hints = new HashMap<>();
        // 指定字符集
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        // 指定边缘的宽度
        hints.put(EncodeHintType.MARGIN, 1);
        // 指定纠错级别。纠错级别越高，可以修正的错误就越多，但二维码的密度也越大。可选值包括L，M，Q和H，表示从7%到30%的纠错能力
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT, hints);
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();
            matrixToImageConfig.setOnColor(0x7690A6);
            matrixToImageConfig.setOffColor(0xFFFFFFFF);
            BufferedImage qrcode = ImageUtil.toBufferedImage(matrix, matrixToImageConfig);
            // 加载logo
            // BufferedImage logo = ImageUtil.loadFromUrl(QRCODE_LOGO);
            BufferedImage roundness = ImageUtil.generateRoundness(new URL(QRCODE_LOGO));
            return ImageUtil.overlayLogo(roundness, qrcode, null,true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Image generateLabel(String l){
        Label label = JSON.parseObject(l, Label.class);
        String backgroundColor = label.getBackgroundColor();
        int bgColor = Color.decode(backgroundColor).getRGB();

return null;
    }


}