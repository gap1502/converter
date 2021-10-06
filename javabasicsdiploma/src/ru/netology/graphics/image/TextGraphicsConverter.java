package ru.netology.graphics.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface TextGraphicsConverter {
    /**
     * Конвертация изображения, переданного как урл, в текстовую графику.
     * @param url урл изображения
     * @return текст, представляющий собой текстовую графику переданного изображения
     * @throws IOException
     * @throws BadImageSizeException Если соотношение сторон изображения слишком большое
     */
    String convert(String url) throws IOException, BadImageSizeException;

    /**
     * Устанавливает максимальную ширину результирующего изображения в "текстовых пикселях".
     * Если исходное изображение имеет характеристики, превышающие максимальные, оно сжимается
     * с сохранением пропорций.
     * @param width максимальная ширина текстовых картинок
     * @return
     */
    void setMaxWidth(int width) throws IOException;

    /**
     * Устанавливает максимальную высоту результирующего изображения в "текстовых пикселях".
     * Если исходное изображение имеет характеристики, превышающие максимальные, оно сжимается
     * с сохранением пропорций.
     * @param height максимальная высоту текстовых картинок
     */
    void setMaxHeight(int height);

    /**
     * Устанавливает максимально допустимое соотношение сторон исходного изображения.
     * Если исходное изображение имеет характеристики, превышающие максимальные,
     * при конвертации выбрасывается исключение.
     * @param maxRatio
     */
    void setMaxRatio(double maxRatio);

    /**
     * Устанавливает символьную цветовую схему, которую будет использовать конвертер
     * @param schema
     */
    void setTextColorSchema(TextColorSchema schema);
}
