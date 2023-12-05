package cn.edu.jnu.supershopper.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import cn.edu.jnu.supershopper.GameFragment;
import cn.edu.jnu.supershopper.MainActivity;
import cn.edu.jnu.supershopper.R;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private float touchedX;
    private float touchedY;
    private boolean isTouched=false;

    public GameView(Context context) {
        super(context);
        initView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_UP==event.getAction())
        {
            touchedX = event.getRawX();
            touchedY = event.getRawY();
            isTouched = true;
        }
        return true;
    }

    private void initView()
    {
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
    }

    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread=null;

    private ArrayList<Spriter> spriterArrayList=new ArrayList<>();
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        for(int i=0;i<4;++i)
        {
            Spriter spriter=new Spriter(this.getContext());
            spriter.setX(i*50);
            spriter.setY(i*50);
            spriter.setDirection((float) (Math.random()*2*Math.PI));
            spriterArrayList.add(spriter);
        }

        drawThread=new DrawThread();
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        drawThread.stopThread();
    }


    class DrawThread extends Thread {
        private boolean isDrawing=true;

        public void stopThread()
        {
            isDrawing=false;

            try {
                join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            int hitCount=0;
            while(isDrawing)
            {
                Canvas canvas =null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.GREEN);

                    Bitmap bitmap = BitmapFactory.decodeResource(GameView.this.getContext().getResources(),R.drawable.glass);  //设置背景
                    canvas.drawBitmap(bitmap, new Matrix(), new Paint());

                    if(isTouched) {
                        float tempX = touchedX;
                        float tempY = touchedY;
                        isTouched = false;
                        for (Spriter spriter : spriterArrayList) {
                            if(spriter.isTouched(tempX, tempY))
                            {
                                hitCount++;
                                spriterArrayList.remove(spriter);
                            }
                        }
                    }
                    Paint textPaint = new Paint();
                    textPaint.setColor(Color.BLACK);
                    textPaint.setTextSize(40);
                    canvas.drawText("**************You have hit "+hitCount+" objects**************",60,60,textPaint);
                    if(hitCount==4)
                    {
                        Paint textPaint2 = new Paint();
                        textPaint2.setColor(Color.RED);
                        textPaint2.setTextSize(40);
                        canvas.drawText("Mission Completed!!",getWidth()/2-180,getHeight()/2,textPaint2);

                    }
                    for (Spriter spriter: spriterArrayList) {
                        spriter.move(canvas.getHeight(), canvas.getWidth());
                    }
                    for (Spriter spriter: spriterArrayList) {
                        spriter.draw(canvas);
                    }
                }
                catch(Exception e)
                {

                }
                finally {
                    if(null!=canvas)surfaceHolder.unlockCanvasAndPost(canvas);
                }


                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
