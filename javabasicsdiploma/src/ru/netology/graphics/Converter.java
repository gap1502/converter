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

    int maxWidth;
    int maxHeight;
    double maxRatio;


    public Converter(int maxWidth, int maxHeight, double maxRatio) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.maxRatio = maxRatio;
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));

        int pixelWidth = img.getWidth();
        int pixelHeight = img.getHeight();

        int newWidth;
        int newHeight;


//        if (((double)pixelWidth / (double)pixelHeight) > maxRatio) {
//            double pixelRatio = (double)pixelWidth / (double)pixelHeight;
//            throw new BadImageSizeException(pixelRatio, maxRatio);
//        }
        if (maxRatio < ((double) pixelWidth / (double) pixelHeight)) {
            newWidth = (int) (pixelWidth * maxRatio);
            newHeight = (int) (pixelHeight * maxRatio);

        } else {
            newWidth = pixelWidth;
            newHeight = pixelHeight;

        }

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
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                out.append(array[j][i]);
            }
            out.append("\n");
        }

        return out.toString();
    }

    @Override
    public void setMaxWidth(int width) {

    }

    @Override
    public void setMaxHeight(int height) {

    }

    @Override
    public void setMaxRatio(double maxRatio) {

    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}
