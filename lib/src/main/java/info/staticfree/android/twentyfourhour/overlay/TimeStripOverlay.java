package info.staticfree.android.twentyfourhour.overlay;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by artem on 3/26/17.
 */

public class TimeStripOverlay implements DialOverlay {

    private class TimeStrip {
        int start;
        int end;
    }

    private TimeStrip timeStrip = new TimeStrip();

    private float ratio = 0.5f;
    private final Paint p;
    private final Paint p2;

    public TimeStripOverlay(int start, int end) {
        timeStrip.start = start - 3;
        timeStrip.end = end - 3;

        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.CYAN);
        p.setAlpha(127);

        p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p2.setColor(Color.RED);
        p2.setAlpha(127);
        p2.setStrokeWidth(2);
        p2.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar,
                       boolean sizeChanged) {

        // canvas.drawCircle(cX, cY, 50, p);

        float r = (float) (Math.min(w, h) / 2.0);
        r *= ratio;
        RectF circle = new RectF(cX - r, cY - r, cX + r, cY + r);
        float angle1 = getHourHandAngle(timeStrip.start, 0);
        float angle2 = getHourHandAngle(timeStrip.end, 0);
        float sweepAngle = angle1 - angle2;
        canvas.drawArc(circle, angle1, sweepAngle, false, p2);

        // canvas.drawRect(cX - w / 2, cY - h / 2, cX + w / 2, cY + h / 2, p);
    }

    private float getAngleForHour(Calendar cal) {
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);
        return getHourHandAngle(h, m);
    }

    public static float getHourHandAngle(int h, int m) {
        return ((12 + h) / 24.0f * 360) % 360 + (m / 60.0f) * 360 / 24.0f;
    }

}
