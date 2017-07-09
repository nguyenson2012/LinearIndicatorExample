package nguyenson.com.linear_indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 09/07/2017.
 */

public class LinearIndicator extends View implements IndicatorInterface,ViewPager.OnPageChangeListener {
    private static final long DEFAULT_ANIMATE_DURATION = 200;

    private static final int DEFAULT_WIDTH = 28;

    private static final int DEFAULT_HEIGHT = 7;

    private static final int DEFAULT_DISTANCE = 40;

    private ViewPager viewPager;

    private RectDot[] dots;

    private int distance = DEFAULT_DISTANCE;

    private int widthRectDot;

    private int hieghtRectDot;

    private int colorSelected;

    private int colorUnselected;

    private int currentPosition;

    private int beforePosition;

    public LinearIndicator(Context context) {
        super(context);
    }

    public LinearIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LinearIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);

        this.distance = typedArray.getInt(R.styleable.IndicatorView_nguyenson_distance, DEFAULT_DISTANCE);
        this.widthRectDot=typedArray.getDimensionPixelSize(R.styleable.IndicatorView_nguyenson_width,DEFAULT_WIDTH);
        this.hieghtRectDot=typedArray.getDimensionPixelSize(R.styleable.IndicatorView_nguyenson_hieght,DEFAULT_HEIGHT);
        this.colorSelected = typedArray.getColor(R.styleable.IndicatorView_nguyenson_color_selected, Color.parseColor("#ffffff"));
        this.colorUnselected = typedArray.getColor(R.styleable.IndicatorView_nguyenson_color_unselected, Color.parseColor("#ffffff"));

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredHeight = this.hieghtRectDot+4;

        int width;
        int height;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = widthSize;
        } else {
            width = 0;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        float yCenter = getHeight() / 2;

        int d = distance + widthRectDot;

        float firstXCenter = (getWidth() / 2) - ((dots.length - 1) * d / 2+widthRectDot/2);

        for (int i = 0; i < dots.length; i++) {
            dots[i].setTopLeftPoint(i == 0 ? firstXCenter : firstXCenter + d * i, yCenter);
            dots[i].setWidth(widthRectDot);
            dots[i].setHeight(hieghtRectDot);
            dots[i].setColor(i == currentPosition ? colorSelected : colorUnselected);
            dots[i].setAlpha(i == currentPosition ? 255 : 100);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (RectDot dot : dots) {
            dot.draw(canvas);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        beforePosition = currentPosition;
        currentPosition = position;

        if (beforePosition == currentPosition) {
            beforePosition = currentPosition + 1;
        }

        dots[currentPosition].setColor(colorSelected);
        dots[beforePosition].setColor(colorUnselected);

        dots[currentPosition].setAlpha(255);
        dots[beforePosition].setAlpha(100);

        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void setViewPager(ViewPager viewPager) throws PagesLessException {
        this.viewPager = viewPager;
        currentPosition = viewPager.getCurrentItem();
        viewPager.addOnPageChangeListener(this);
        initDot(viewPager.getAdapter().getCount());
        onPageSelected(currentPosition);
    }

    private void initDot(int count) throws PagesLessException {
        if (count < 2) throw new PagesLessException();

        dots = new RectDot[count];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new RectDot();
        }
    }

    @Override
    public void setWidthIndicatorItem(int widthIndicatorItem) {
        this.widthRectDot=widthIndicatorItem;
    }

    @Override
    public void setHieghtIndicatorItem(int heightIndicatorItem) {
        this.hieghtRectDot=heightIndicatorItem;
    }

    @Override
    public void setColorSelected(int colorSelected) {
        this.colorSelected=colorSelected;
    }

    @Override
    public void setColorUnselected(int colorUnselected) {
        this.colorUnselected=colorUnselected;
    }

    @Override
    public void setDistanceLinearRect(int distanceLinearRect) {
        this.distance=distanceLinearRect;
    }
}
