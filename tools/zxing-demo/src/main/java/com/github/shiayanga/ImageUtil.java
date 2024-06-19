package com.github.shiayanga;

import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

/**
 * _____ _     _
 * /  ___| |   (_)
 * \ `--.| |__  _  __ _ _   _  __ _ _ __   __ _  __ _
 * `--. \ '_ \| |/ _` | | | |/ _` | '_ \ / _` |/ _` |
 * /\__/ / | | | | (_| | |_| | (_| | | | | (_| | (_| |
 * \____/|_| |_|_|\__,_|\__, |\__,_|_| |_|\__, |\__,_|
 * __/ |             __/ |
 * |___/             |___/
 */
public class ImageUtil {
    private static final MatrixToImageConfig DEFAULT_CONFIG = new MatrixToImageConfig();

    /**
     * 生成图片
     *
     * @param matrix 矩阵
     * @return 生成的图片
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        return toBufferedImage(matrix, DEFAULT_CONFIG);
    }

    /**
     * 生成图片
     *
     * @param matrix 矩阵
     * @param config 配置信息
     * @return 生成的图片
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix, MatrixToImageConfig config) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, config.getBufferedImageColorModel());
        int onColor = config.getPixelOnColor();
        int offColor = config.getPixelOffColor();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
            }
        }
        return image;
    }

    /**
     * 写出图片到流
     *
     * @param matrix       矩阵
     * @param format       图片格式
     * @param outputStream 输出流
     * @param config       配置信息
     * @throws IOException IO异常
     */
    public static void writeToStream(BitMatrix matrix, String format, OutputStream outputStream, MatrixToImageConfig config) throws IOException {
        BufferedImage image = toBufferedImage(matrix, config);
        if (!ImageIO.write(image, format, outputStream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    /**
     * 写出图片到流
     *
     * @param format       图片格式
     * @param outputStream 输出流
     * @throws IOException IO异常
     */
    public static void writeToStream(BufferedImage image, String format, OutputStream outputStream) throws IOException {
        if (!ImageIO.write(image, format, outputStream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    /**
     * 叠加图片
     *
     * @param source   源图片
     * @param target   目标图片
     * @param x        X坐标
     * @param y        Y坐标
     * @param observer
     * @return 叠加后的图片
     */
    public static BufferedImage overlayPicture(BufferedImage source, BufferedImage target, int x, int y, ImageObserver observer) {
        int width = target.getWidth(null);
        int height = target.getHeight(null);
        // 创建空白图片
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // 获取画布？
        Graphics graphics = resultImage.getGraphics();
        // 先把目标图像画上
        graphics.drawImage(target, 0, 0, observer);
        // 再把源图像画上
        graphics.drawImage(source, x, y, observer);
        graphics.dispose();
        return resultImage;
    }

    /**
     * 叠加图片
     *
     * @param source 源图片
     * @param target 目标图片
     * @return 叠加后的图片
     */
    public static BufferedImage overlayLogo(BufferedImage source, BufferedImage target, ImageObserver observer) {
        int width = target.getWidth(null);
        int height = target.getHeight(null);
        int logH = (int) (height * 0.2);
        int logW = (int) (width * 0.2);
        int x = (width - logW) / 2;
        int y = (height - logH) / 2;

        // 创建空白图片
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = resultImage.createGraphics();
        // 先把目标图像画上
        graphics.drawImage(target, 0, 0, observer);
        // 开启抗锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 再把源图像画上
        graphics.drawImage(source, x, y, logW, logH, observer);
        // 二维码描边
        Shape shape = new RoundRectangle2D.Float(x, y, logW, logH, 0, 0);
        // graphics.setStroke(new BasicStroke(1.5f));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.draw(shape);
        graphics.dispose();
        return resultImage;
    }


    /**
     * 叠加图片
     *
     * @param source    源图片
     * @param target    目标图片
     * @param roundLogo 是否为圆形Logo
     * @return 叠加后的图片
     */
    public static BufferedImage overlayLogo(BufferedImage source, BufferedImage target, ImageObserver observer, Boolean roundLogo) {
        if (!roundLogo) {
            return overlayLogo(source, target, observer);
        }
        int width = target.getWidth(null);
        int height = target.getHeight(null);
        int logH = (int) (height * 0.2);
        int logW = (int) (width * 0.2);
        int x = (width - logW) / 2;
        int y = (height - logH) / 2;
        // 创建空白图片
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = resultImage.createGraphics();
        // 先把目标图像画上
        graphics.drawImage(target, 0, 0, observer);
        // 开启抗锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 再把源图像画上
        graphics.drawImage(source, x, y, logW, logH, observer);
        // 二维码描边
        Shape shape = new RoundRectangle2D.Float(x, y, logW, logH, 300, 300);
        // graphics.setStroke(new BasicStroke(1.5f));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // graphics.draw(shape);
        graphics.dispose();
        return resultImage;
    }

    /**
     * 叠加图片
     *
     * @param source 源图片
     * @param target 目标图片
     * @param x      X坐标
     * @param y      Y坐标
     * @return 叠加后的图片
     */
    public static BufferedImage overlayPicture(BufferedImage source, BufferedImage target, int x, int y, int width, int height, ImageObserver observer) {
        int targetWidth = target.getWidth(null);
        int targetHeight = target.getHeight(null);
        // 创建空白图片
        BufferedImage resultImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        // 获取画布？
        Graphics graphics = resultImage.getGraphics();
        // 先把目标图像画上
        graphics.drawImage(target, 0, 0, observer);
        // 再把源图像画上
        graphics.drawImage(source, x, y, width, height, observer);
        graphics.dispose();
        return resultImage;
    }

    /**
     * 从url加载图片
     *
     * @param url url
     * @return 加载的图片
     * @throws MalformedURLException URL 格式错误异常
     */
    public static BufferedImage loadFromUrl(String url) throws IOException {
        return ImageIO.read(new URL(url));
    }

    /**
     * 从文字生成图片
     *
     * @param text      文字
     * @param font      字体
     * @param textColor 字体颜色
     * @param observer
     * @return 生成的图片
     */
    public static BufferedImage textToPicture(String text, Font font, Color textColor, ImageObserver observer) {
        // 创建一个临时的Graphics2D对象，以便在BufferedImage上绘制文本
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        // 设置字体
        g2d.setFont(font);
        // 获取文本的宽度和高度
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        // 释放Graphics2D对象
        g2d.dispose();
        // 创建一个新的BufferedImage对象
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();

        // 开启抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置字体和颜色
        g2d.setFont(font);
        g2d.setColor(textColor);
        // 将文本绘制到BufferedImage上
        g2d.drawString(text, 0, fm.getAscent());
        // 释放Graphics2D对象
        g2d.dispose();
        return img;
    }

    /**
     * 从文字生成图片
     *
     * @param text      文字
     * @param font      字体
     * @param textColor 字体颜色
     * @param observer
     * @return 生成的图片
     */
    public static BufferedImage textToPicture(String text, Font font, Color textColor, ImageObserver observer,String spliter) {
        // 创建一个临时的Graphics2D对象，以便在BufferedImage上绘制文本
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        // 设置字体
        g2d.setFont(font);
        // 获取文本的宽度和高度
        FontMetrics fm = g2d.getFontMetrics();
        String[] split = text.split(spliter);
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        // 释放Graphics2D对象
        g2d.dispose();
        // 创建一个新的BufferedImage对象
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();

        // 开启抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置字体和颜色
        g2d.setFont(font);
        g2d.setColor(textColor);
        // 将文本绘制到BufferedImage上
        g2d.drawString(text, 0, fm.getAscent());
        // 释放Graphics2D对象
        g2d.dispose();
        return img;
    }

    /**
     * 从文字生成 图片
     *
     * @param text      文字
     * @param font      字体
     * @param textColor 字体颜色
     * @param width     宽度
     * @param height    高度
     * @param x         X坐标
     * @param y         Y坐标
     * @param observer
     * @return 生成的图片
     */
    public static BufferedImage generateFromText(String text, Font font, Color textColor, int width, int height, int x, int y, ImageObserver observer) {
        // 创建空白图片
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // 获取画布？
        Graphics graphics = resultImage.getGraphics();
        // 再把源图像画上
        graphics.setColor(textColor);
        graphics.setFont(font);
        graphics.drawString(text, x, y);
        graphics.dispose();
        return resultImage;
    }

    /**
     * 获取带base64头的图片Base64值
     *
     * @param image 图片
     * @return java.lang.String
     * @throws IOException io异常
     */
    public static String getBase64FromImageWithHeader(BufferedImage image) throws IOException {
        return "data:image/jpeg;base64," + ImageUtil.getBase64FromImage(image);
    }

    /**
     * 获取不带base64头的图片Base64值
     *
     * @param image 图片
     * @return java.lang.String
     * @throws IOException io异常
     */
    public static String getBase64FromImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", stream);
        return Base64.getEncoder().encodeToString(stream.toByteArray());
    }

    /**
     * 获取图片输入流
     *
     * @param image 图片
     * @return java.lang.String
     * @throws IOException io异常
     */
    public static InputStream getInputStreamFromImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", stream);
        return new ByteArrayInputStream(stream.toByteArray());
    }

    /**
     * 将图片剪切为圆形图片
     *
     * @param filePath 图片地址
     * @return java.lang.String
     * @throws Exception io异常
     */
    public static BufferedImage generateRoundness(String filePath) throws Exception {
        BufferedImage bi1 = ImageIO.read(new File(filePath));
        return generateRoundness(bi1);
    }


    /**
     * 将图片剪切为圆形图片
     *
     * @param url 图片地址
     * @return java.lang.String
     * @throws Exception io异常
     */
    public static BufferedImage generateRoundness(URL url) throws Exception {
        BufferedImage bi1 = ImageIO.read(url);
        return generateRoundness(bi1);
    }


    /**
     * 将图片剪切为圆形图片
     *
     * @param bufferedImage 图片
     * @return java.lang.String
     */
    public static BufferedImage generateRoundness(BufferedImage bufferedImage) {
        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
        BufferedImage image = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(bufferedImage.getWidth(), bufferedImage.getHeight(), Transparency.TRANSLUCENT);
        g2 = image.createGraphics();
        g2.setComposite(AlphaComposite.Clear);
        g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bufferedImage, 0, 0, null);
        g2.dispose();
        return image;
    }


    /**
     * 保存图片到文件
     *
     * @param image      图片
     * @param fileFormat 文件格式
     * @param filePath   保存路径
     * @return boolean 保存结果
     */
    public static boolean writeToFile(BufferedImage image, String fileFormat, String filePath) {
        try {
            return ImageIO.write(image, fileFormat, new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String QRCODE_FORMAT = "png";

    public static void main(String[] args) {
        BufferedImage bufferedImage = ImageUtil.textToPicture("aaa\nbbb", null, Color.BLACK, null);
        ImageUtil.writeToFile(bufferedImage,QRCODE_FORMAT,"/Users/liyang/Projects/github/shiayanga-learning/qrCodeImages/" + new Date().getTime() + "." + QRCODE_FORMAT);
    }
}
