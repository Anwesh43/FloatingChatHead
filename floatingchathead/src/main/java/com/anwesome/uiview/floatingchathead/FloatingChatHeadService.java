package com.anwesome.uiview.floatingchathead;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.MotionEvent;

/**
 * Created by anweshmishra on 01/12/16.
 */
public class FloatingChatHeadService extends Service{
    private FloatingChatHead floatingChatHead;
    public void onCreate() {
        floatingChatHead = new FloatingChatHead(this);
        floatingChatHead.show();
    }
    public void onDestroy() {
        super.onDestroy();
        floatingChatHead.destroy();
    }
    public IBinder onBind(Intent intent) {
        return  null;
    }

}
