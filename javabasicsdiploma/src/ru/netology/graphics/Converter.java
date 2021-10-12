package ru.netology.graphics;


import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    public int wight;
    public int height;
    public int newWidth;
    public int newHeight;
    public double maxRatio;
    public TextColorSchema schema;


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        ratio(img);
        sizeImg(img);
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();
        char[][] array = new char[newWidth][newHeight];
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                int color = bwRaster.getPixel(i, j, new int[3])[0];
                TextColorSchema schema = new TextColorChange();
                char c = schema.convert(color);
                array[i][j] = c;
            }
        }
        StringBuilder out = new StringBuilder();
        printImg(array, out);
        return out.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.wight = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

    public void ratio(BufferedImage img) throws BadImageSizeException {
        double ratio;
        int pixelWidth = img.getWidth();
        int pixelHeight = img.getHeight();
        if (pixelWidth / pixelHeight > pixelHeight / pixelWidth) {
            ratio = (double) pixelWidth / (double) pixelHeight;
        } else {
            ratio = (double) pixelHeight / (double) pixelWidth;
        }
        if (ratio > maxRatio && maxRatio != 0) throw new BadImageSizeException(ratio, maxRatio);
    }

    public void sizeImg(BufferedImage img) {
        int pixelWidth = img.getWidth();
        int pixelHeight = img.getHeight();
        if (maxRatio < ((double) pixelWidth / (double) pixelHeight)) {
            newWidth = (int) (pixelWidth * maxRatio);
            newHeight = (int) (pixelHeight * maxRatio);
        } else {
            newWidth = pixelWidth;
            newHeight = pixelHeight;
        }
    }

    public void printImg(char[][] array, StringBuilder out) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                out.append(array[i][j]);
            }
            out.append("\n");
        }
    }
}
