package tambowskip.com.free.remoteapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;



public class TouchEventView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();
    Context context;
    GestureDetector gestureDetector;
    private int initX, initY, disX, disY;
    boolean mouseMoved = false, moultiTouch = false;

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gestureDetector =new GestureDetector(context, new GestureListener());
        this.context=context;
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setColor(int r, int g, int b) {
        int rgb = Color.rgb(r, g, b);
        paint.setColor(rgb);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            float x=e.getX();
            float y=e.getY();
            path.reset();
            return true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final float eventX=event.getX();
        final float eventY=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX,eventY);
                initX = (int) event.getX();
                initY = (int) event.getY();
                mouseMoved = false;
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX,eventY);
                if(moultiTouch == false) {
                    disX = (int) event.getX()- initX; //Mouse movement in x direction
                    disY = (int) event.getY()- initY; //Mouse movement in y direction
                                /*set init to new position so that continuous mouse movement
                                is captured*/
                    initX = (int) event.getX();
                    initY = (int) event.getY();
                    if (disX != 0 || disY != 0) {
                        //send mouse movement to server
                        AppExecutors.getInstance().getNetWorkCall().execute(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.printWriter.println(disX+","+disY);
                                MainActivity.printWriter.flush();
                                mouseMoved=true;
                            }
                        });
                    }
                }
                else {
                    disY = (int) event.getY()- initY; //Mouse movement in y direction
                    disY = (int) disY / 2;//to scroll by less amount
                    initY = (int) event.getY();
                    if(disY != 0) {
                        AppExecutors.getInstance().getNetWorkCall().execute(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.printWriter.println(disY+","+0);
                                mouseMoved=true;
                            }
                        });
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(!mouseMoved){
                    AppExecutors.getInstance().getNetWorkCall().execute(new Runnable() {
                        @Override
                        public void run() {
                            if(!mouseMoved){
                                MainActivity.printWriter.println("LEFT_CLICK");
                                MainActivity.printWriter.flush();
                            }
                        }
                    });
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                initY = (int) event.getY();
                mouseMoved = false;
                moultiTouch = true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if(!mouseMoved) {
                    AppExecutors.getInstance().getNetWorkCall().execute(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.printWriter.println("LEFT_CLICK");
                            MainActivity.printWriter.flush();
                        }
                    });
                }
                moultiTouch = false;
                break;
        }

        gestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }
}
