package com.haarishaq.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Haaris Haq on 01/01/2018.
 */

public class TileRow {
    final int TILES = 4;
    int blackTile;
    public int bottom;
    Canvas canvas;
    Tile[] tiles = new Tile[TILES];
    int viewBottom;

    public TileRow(int blackTile, Canvas canvas, View v) {
        this.blackTile = blackTile;
        this.canvas = canvas;
        int width = v.getRight() / TILES;
        for (int i = 0; i < TILES; i++) {
            tiles[i] = new Tile(v.getBottom(), 1 + width * i, width); //drawn just below canvas
        }
        bottom = tiles[0].bottom;
        viewBottom = v.getBottom();
    }

    public Boolean bottomAtBottom() {
        return bottom == viewBottom;
    }

    public void moveUp(){
        for(Tile t : tiles){
            t.top -= 5;
            t.bottom -=5;
        }
    }

    public void Draw(Paint whitePaint, Paint blackPaint) {
        Paint p;
        for (int i = 0; i < TILES; i++) {
            p = whitePaint;
            if (i == blackTile) {
                p = blackPaint;
            }
            tiles[i].Draw(canvas, p);
        }
    }


    public class Tile {
        int left;
        int right;
        int top;
        int bottom;

        public Tile(int top, int left, int width) {
            this.left = left;
            this.top = top;
            this.bottom = top + 350;
            this.right = left + width;
        }

        public Rect getTileDimensions() {
            return new Rect(left, top, right, bottom);
        }

        public void Draw(Canvas canvas, Paint paint) {
            canvas.drawRect(getTileDimensions(), paint);
        }
    }
}
