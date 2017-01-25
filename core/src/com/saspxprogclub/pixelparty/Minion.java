package com.saspxprogclub.pixelparty;

/**
 * Created by Brandon on 1/25/17.
 */

public class Minion extends GameObject {
    Minion(int width, int height) {
        super(width, height);
    }
    Minion(int x, int y, int width, int height) {
        super(width, height);
        move(x, y);
    }
}
