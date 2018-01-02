package com.haarishaq.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.haarishaq.game.TileRow;

/**
 * Created by Haaris Haq on 28/09/2017.
 */

public class GameCanvasView extends View {
    Paint whitePaint;
    Paint canvasBackground;
    Paint textPaint;
    Paint blackPaint;

    TileRowView[] rows = new TileRowView[6];

    public GameCanvasView(Context context) {
        super(context);
        init();
    }

    private void init() {
        int strokeWidth = 1;

        whitePaint = new Paint();
        whitePaint.setStrokeWidth(strokeWidth);
        whitePaint.setColor(Color.BLACK);
        whitePaint.setStyle(Paint.Style.STROKE);

        blackPaint = new Paint();
        blackPaint.setStrokeWidth(strokeWidth);
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvasBackground = new Paint();
        canvasBackground.setColor(Color.WHITE);
        canvasBackground.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setTextSize(40);
        textPaint.setColor(Color.BLACK);
    }

    void Draw(Canvas canvas) {
        String message = "Not currently implemented";
        canvas.drawText(message, this.getWidth() / 2 - textPaint.measureText(message) / 2, this.getBottom() / 3, textPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        canvas.drawRect(0, 0, this.getRight(), this.getBottom(), canvasBackground);
        Draw(canvas);
    }

    public class TileRowView extends TileRow {
        public Boolean isAlive = null;

        public TileRowView(int blackTile, Canvas canvas, View view) {
            super(blackTile, canvas, view);
            isAlive = true;
        }

        public void CheckLiving() {
            if (bottom <= 0) {
                isAlive = false;
            }
        }

    }
}
