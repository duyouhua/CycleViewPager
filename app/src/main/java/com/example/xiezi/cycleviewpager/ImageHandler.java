package com.example.xiezi.cycleviewpager;

import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by 蝎子莱莱123 on 2015/11/15.
 */
public  class ImageHandler extends android.os.Handler {


    protected static final int MSG_UPDATE_IMAGE=1;
    protected static final int MSG_KEEP_SILENCE=2;
    protected static final int MSG_BREAK_SILENCE=3;
    protected static final int MSG_PAGE_CHANGED=4;
    protected static final long MSG_DELAY  =1000;

    private WeakReference<MainActivity>weakReference;
    private int currentItem=0;
    protected ImageHandler(WeakReference<MainActivity>wk){
        weakReference=wk;
    }

    public void handleMessage(Message msg){
        super.handleMessage(msg);
        MainActivity activity=weakReference.get();
        if(activity==null){
            return;
        }
        if(activity.handler.hasMessages(MSG_UPDATE_IMAGE)){
            activity.handler.removeMessages(MSG_UPDATE_IMAGE);
        }
        switch (msg.what){
            case MSG_UPDATE_IMAGE:
                currentItem++;
                activity.viewPager.setCurrentItem(currentItem);
                activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,MSG_DELAY);
                break;
            case MSG_KEEP_SILENCE:

                break;
            case MSG_BREAK_SILENCE:
                activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,MSG_DELAY);
                break;
            case MSG_PAGE_CHANGED:
               currentItem=msg.arg1;
                break;
        }

    }
}
