package com.example.mysensor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BallView extends View {

    private Bitmap ball = null; //球的图片
    private Point point = null; //球显示的位置
    private float[] values = null;//传感器方位变化的数据
    private int xSpeed = 0;             //球在屏幕上X轴的方向移动的变化量
    private int ySpeed = 0;             //球在屏幕上Y轴的方向移动的变化量


    public BallView(Context context,
                    AttributeSet attrs) {
        super(context, attrs);

        ball = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        point = new Point();

        SensorManager sm = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                values = event.values;
                postInvalidate();
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected  void onDraw(Canvas canvas){
        Paint paint = new Paint();
        if (values!=null){
            xSpeed = -(int)values[2];
            ySpeed = -(int)values[1];
        }
        point.x += xSpeed;
        point.y += ySpeed;

        if (point.x < 0){
            point.x = 0;
        }
        if (point.y < 0){
            point.y = 0;
        }
        if (point.x > this.getWidth() - ball.getWidth()){
            point.x = this.getWidth() - ball.getWidth();
        }
        if (point.y > this.getHeight() - ball.getHeight()){
            point.y = this.getHeight() - ball.getHeight();
        }

        canvas.drawBitmap(ball,point.x,point.y,paint);

    }
}
