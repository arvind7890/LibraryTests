package dnivra.droid.arvind.coloredlinepatternpicker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


public class DashDrawable extends Drawable {
    private Paint mPaint;
    private Path mPath;

    private int lineColor;
    private int lineAlpha;
    private int lineWidth;
    private float dashLength;
    private float gapLength;

    public DashDrawable() {
        mPaint=new Paint();
        mPath=new Path();
        mPaint.setAntiAlias(true);
    }

    public DashDrawable(int lineColor, int lineWidth, float dashLength, float gapLength) {

        this.lineColor = lineColor;
        this.lineAlpha = Color.alpha(lineColor);
        this.lineWidth = lineWidth;
        this.dashLength = dashLength;
        this.gapLength = gapLength;
        mPaint=new Paint();
        mPath=new Path();
        mPaint.setAntiAlias(true);



    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mPaint.setColor(this.lineColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAlpha(lineAlpha);
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setPathEffect(new DashPathEffect(new float[]{this.dashLength,this.gapLength},0));
        mPaint.setAntiAlias(true);
        int height = getBounds().height();
        int width = getBounds().width();
        Log.e("DRAW","height:"+height+"Width:"+width);
        mPath.moveTo(0,height/4);
        mPath.lineTo(width,height/4);
        //canvas.drawLine(0,height/2,width,height/2,mPaint);
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return lineAlpha;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;

    }

    public int getLineAlpha() {
        return lineAlpha;
    }

    public void setLineAlpha(int lineAlpha) {
        this.lineAlpha = lineAlpha;
        setAlpha(lineAlpha);
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public float getDashLength() {
        return dashLength;
    }

    public void setDashLength(float dashLength) {
        this.dashLength = dashLength;
    }

    public float getGapLength() {
        return gapLength;
    }

    public void setGapLength(float gapLength) {
        this.gapLength = gapLength;
    }
}

