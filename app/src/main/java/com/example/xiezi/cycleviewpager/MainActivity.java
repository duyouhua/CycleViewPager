package com.example.xiezi.cycleviewpager;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected ViewPager viewPager;
    protected ImageHandler handler=new ImageHandler(new WeakReference<MainActivity>(this));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.viewpager);

        try {
            Field mScroller=null;
            mScroller=ViewPager.class.getDeclaredField("mScroller");//获得viewpager里面的成员mScroller
            mScroller.setAccessible(true);//原来是private成员，要将其设置为可修改
            ViewPagerScroller scroller=new ViewPagerScroller(this);
            mScroller.set(viewPager,scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.item, null);
        View view2 = inflater.inflate(R.layout.item2, null);
        View view3 = inflater.inflate(R.layout.item3, null);

        ArrayList<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);

        viewPager.setAdapter(new ImageAdapter(views));
        viewPager.setPageTransformer(true, new Transform1());

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position, 0));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENCE);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);

                        break;
                }

            }
        });
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        handler.sendEmptyMessage(ImageHandler.MSG_BREAK_SILENCE);


    }

}
