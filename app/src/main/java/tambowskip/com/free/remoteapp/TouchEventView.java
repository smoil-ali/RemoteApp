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

        float eventX=event.getX();
        float eventY=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX,eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX,eventY);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                path.lineTo(eventX,eventY);
                break;
            default:
                return false;
        }

        gestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }
}
