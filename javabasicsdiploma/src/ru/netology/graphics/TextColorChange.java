package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;



public class TextColorChange implements TextColorSchema {

    char symbol;

    @Override
    public char convert(int color) {

        if ((color >= 231) && (color <= 255)) {
            symbol = ' ';
        }
        if ((color >= 201) && (color <= 230)) {
            symbol = ',';
        }
        if ((color >= 181) && (color <= 200)) {
            symbol = '-';
        }
        if ((color >= 161) && (color <= 180)) {
            symbol = '+';
        }
        if ((color >= 131) && (color <= 160)) {
            symbol = '*';
        }
        if ((color >= 101) && (color <= 130)) {
            symbol = '%';
        }
        if ((color >= 71) && (color <= 100)) {
            symbol = '@';
        }
        if ((color >= 51) && (color <= 70)) {
            symbol = '$';
        }
        if ((color >= 0) && (color <= 50)) {
            symbol = '#';
        }
        return symbol;
    }
}
