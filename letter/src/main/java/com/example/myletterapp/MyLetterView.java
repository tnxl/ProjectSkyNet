package com.example.myletterapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyLetterView extends View {

    OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    //右侧导航显示的数据
    String[] b = {"A","B","C","D","E","F","G","H","J","K","L"
            ,"M","N","P","Q","R","S","T","W","X","Y","Z"};
    private Paint myPaint = new Paint();
    private boolean myBkgFlag = false;
    //选择的字母
    private int choose = -1;
    public MyLetterView(Context context) {
        super(context);
    }

    public MyLetterView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public MyLetterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(myBkgFlag)
            canvas.drawColor(Color.parseColor("#40000000"));
        //设置画笔
        myPaint.setTypeface(Typeface.DEFAULT_BOLD);
        myPaint.setTextSize(30);
        myPaint.setAntiAlias(true);

        int height = getHeight();
        int width = getWidth();
        int FlagHeight = height / b.length;

        for(int i = 0;i < b.length;i ++){
            //设置画笔颜色
            if(i == choose){
                myPaint.setColor(Color.BLACK);
            }else{
                myPaint.setColor(Color.WHITE);
            }
            //计算字符所在位置
            int TxtHeight = FlagHeight*i + FlagHeight;
            int TxtWidth = (int) (width/2 - myPaint.measureText(b[i])/2);
            //绘制字符
            canvas.drawText(b[i],TxtWidth,TxtHeight,myPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //获取到我们手指滑动到第几个值
        int c = (int) (event.getY()/getHeight()*b.length);
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            myBkgFlag = true;

            if(c > 0 && c < b.length){
                listener.onTouchingLetterChanged(b[c]);
                choose = c;
                invalidate();
            }
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            myBkgFlag = true;
            if(c > 0 && c < b.length){
                listener.onTouchingLetterChanged(b[c]);
                choose = c;
                invalidate();
            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            myBkgFlag = false;
            choose = -1;
            invalidate();
        }

        return true;
    }


    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
    public interface OnTouchingLetterChangedListener{
        public void onTouchingLetterChanged(String s);
    }
}
