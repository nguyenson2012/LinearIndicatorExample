package nguyenson.com.linear_indicator;

import android.support.v4.view.ViewPager;

/**
 * Created by Administrator on 09/07/2017.
 */

public interface IndicatorInterface {
    public void setViewPager(ViewPager viewPager) throws PagesLessException;
    public void setWidthIndicatorItem(int widthIndicatorItem);
    public void setHieghtIndicatorItem(int heHieghtIndicatorItem);
    public void setColorSelected(int colorSelected);
    public void setColorUnselected(int colorUnselected);
    public void setDistanceLinearRect(int distanceLinearRect);

}
