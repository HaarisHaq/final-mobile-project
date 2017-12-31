package com.haarishaq.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Haaris Haq on 28/09/2017.
 */

public class GameCanvasView extends View {
    Paint paint;
    int top;
    int bottom;

    public GameCanvasView(Context context) {
        super(context);
        init();
    }

    public GameCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    Paint canvasBackground = new Paint();
    Paint textPaint = new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasBackground.setColor(Color.WHITE);
        canvasBackground.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, this.getRight(), this.getBottom(), canvasBackground);
        top = this.getBottom() - 350;
        bottom = this.getBottom();
        int width = (1 + this.getRight()) / 4;
        canvas.drawRect(1, top, width, bottom, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(1 + width, top, 2 * width, bottom, paint);
        canvas.drawRect(1 + width * 2, top, 3 * width, bottom, paint);
        canvas.drawRect(1 + width * 3, top, this.getRight(), bottom, paint);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        String message = "Not currently implemented";
        canvas.drawText(message, this.getWidth() / 2 - textPaint.measureText(message) / 2, this.getBottom() / 3, textPaint);
    }
}
