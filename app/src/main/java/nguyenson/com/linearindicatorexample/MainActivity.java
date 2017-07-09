package nguyenson.com.linearindicatorexample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import nguyenson.com.linear_indicator.LinearIndicator;
import nguyenson.com.linear_indicator.PagesLessException;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;

    LinearIndicator indicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        indicatorView = (LinearIndicator) findViewById(R.id.indicator);
        PagerAdapter adapter = new PagerAdapter(this);
        viewPager.setAdapter(adapter);
        try {
            indicatorView.setViewPager(viewPager);
        } catch (PagesLessException e) {
            e.printStackTrace();
        }
    }
}
