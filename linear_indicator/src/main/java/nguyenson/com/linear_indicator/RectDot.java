package nguyenson.com.linear_indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Administrator on 09/07/2017.
 */

public class RectDot {
    private Paint paint;
    private PointF topLeftPoint;
    private int width;
    private int height;

    public RectDot(){
        paint = new Paint();
        paint.setAntiAlias(true);
        topLeftPoint = new PointF();
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    public void setTopLeftPoint(float x, float y) {
        topLeftPoint.set(x, y);
    }

    public void setWidth(int width){
        this.width=width;}

    public void setHeight(int height) {
        this.height = height;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(topLeftPoint.x,topLeftPoint.y,topLeftPoint.x+width,topLeftPoint.y+height,paint);
    }
}
