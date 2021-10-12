package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;



public class TextColorChange implements TextColorSchema {
    public char[] symbol = {'#', '$', '@', '%', '*', '+', '-', ','};


    @Override
    public char convert(int color) {
        return symbol[color / 32];
    }
}
