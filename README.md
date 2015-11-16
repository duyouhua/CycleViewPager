# MyFile
任意视图无限轮播（包括图片）

package com.example.xiezi.cycleviewpager;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected ViewPager viewPager;
    protected ImageHandler handler=new ImageHandler(new WeakReference<MainActivity>(this));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.item, null);//
        View view2 = inflater.inflate(R.layout.item, null);//定义将要轮播的布局文件
        View view3 = inflater.inflate(R.layout.item, null);//

        ArrayList<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);//将其添加上去
        
        
        
       
       
        

public  class ImageHandler extends android.os.Handler {
    protected static final int MSG_UPDATE_IMAGE=1;
    protected static final int MSG_KEEP_SILENCE=2;
    protected static final int MSG_BREAK_SILENCE=3;
    protected static final int MSG_PAGE_CHANGED=4;
    protected static final long MSG_DELAY  =1000;//设置间隔时间


