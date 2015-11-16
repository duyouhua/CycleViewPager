package com.example.xiezi.cycleviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;

/**
 * Created by 蝎子莱莱123 on 2015/11/15.
 */
public class ImageAdapter extends PagerAdapter {


    private ArrayList<View>viewlist;
    public ImageAdapter(ArrayList<View>viewlist){
            this.viewlist=viewlist;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position%=viewlist.size();
        if(position<0){
            position=viewlist.size()+position;
        }
        View view=viewlist.get(position);
        ViewParent vp=view.getParent();
        if(vp!=null){
            ViewGroup parent =(ViewGroup)vp;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

}
