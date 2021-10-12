package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;



public class TextColorChange implements TextColorSchema {

    char symbol = ' ';

    @Override
    public char convert(int color) {

        if ((color >= 201) && (color <= 255)) {
            symbol = '-';
        }
        if ((color >= 151) && (color <= 200)) {
            symbol = '+';
        }
        if ((color >= 101) && (color <= 150)) {
            symbol = '%';
        }
        if ((color >= 51) && (color <= 100)) {
            symbol = '@';
        }
        if ((color >= 0) && (color <= 50)) {
            symbol = '$';
        }
        return symbol;
    }
}
