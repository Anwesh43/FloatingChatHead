package com.anwesome.uiview.floatingchathead;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by anweshmishra on 01/12/16.
 */
public class FloatingChatHead {
    private WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.TYPE_PHONE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
    private FloatingHeadView floatingHeadView;
    private WindowManager windowManager;
    private Context context;
    private float initialX = 0,initialY = 0;
    private int h  = 0;
    public FloatingChatHead(Context context) {
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        windowManager.getDefaultDisplay().getRealSize(size);
        params.x = size.x/2;
        params.y = size.y/2;
        params.width = size.x/10;
        params.height = size.x/10;
        floatingHeadView = new FloatingHeadView(context);
        initialX = params.x;
        initialY = params.y;
        h = size.y;
    }
    public void show() {
        if(floatingHeadView != null) {
            floatingHeadView = new FloatingHeadView(context);
        }
        windowManager.addView(floatingHeadView,params);
    }
    private class FloatingHeadView extends View {
        private boolean isDown = false;
        public FloatingHeadView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.parseColor("#80CBC4"));
            canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,canvas.getWidth()/2,paint);
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if(!isDown) {
                    isDown = true;
                }
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE) {
                if(isDown) {
                    params.x = (int)event.getRawX()-(int)initialX;
                    params.y = (int)event.getRawY()-(int)initialY;
                    windowManager.updateViewLayout(this,params);
                }
            }
            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(isDown) {
                    isDown = false;
                    if(params.y>8*h/10-h/2) {
                        windowManager.removeView(this);
                        floatingHeadView = null;
                    }
                }
            }
            return true;
        }
    }
    public void destroy() {
        windowManager.removeView(floatingHeadView);
    }

}
