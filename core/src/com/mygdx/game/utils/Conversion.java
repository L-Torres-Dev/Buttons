package com.mygdx.game.utils;

import java.text.DecimalFormat;

/**
 * Created by louie on 12/22/2016.
 */
public class Conversion {

    public static int convertDoubleToInt(Double num)
    {
        DecimalFormat format = new DecimalFormat("#0");

        int x;

        String xStr = Double.toString(num);

        xStr = format.format(num);

        x = Integer.parseInt(xStr);

        return x;

    }
}
