package info.staticfree.android.twentyfourhour.overlay;

import android.view.MotionEvent;

/**
 * Created by artem on 3/28/17.
 */

public interface TouchOverlay {

    /**
     * Subclasses should implement this to process touch events on the overlay
     * @param motion
     *            the motion event created by user touching control
     * @param w
     *            the width of the canvas
     * @param h
     *            the height of the canvas
     */
    public abstract void onTouch(MotionEvent motion, int w, int h);
}
