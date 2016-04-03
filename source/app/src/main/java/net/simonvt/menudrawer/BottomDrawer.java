// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Interpolator;

// Referenced classes of package net.simonvt.menudrawer:
//            VerticalDrawer, BuildLayerFrameLayout, Scroller

public class BottomDrawer extends VerticalDrawer
{

    private int b;

    BottomDrawer(Activity activity, int i)
    {
        super(activity, i);
    }

    public BottomDrawer(Context context)
    {
        super(context);
    }

    public BottomDrawer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public BottomDrawer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    private void a(int i)
    {
        int j;
        int k;
        float f;
label0:
        {
label1:
            {
                if (mOffsetMenu && mMenuSize != 0)
                {
                    j = getHeight();
                    k = mMenuSize;
                    f = ((float)k + (float)i) / (float)k;
                    if (!a)
                    {
                        break label0;
                    }
                    if (i == 0)
                    {
                        break label1;
                    }
                    int j1 = (int)(0.25F * (f * (float)k));
                    mMenuContainer.setTranslationY(j1);
                }
                return;
            }
            mMenuContainer.setTranslationY(j + k);
            return;
        }
        int l = mMenuContainer.getTop();
        int i1 = ((int)(0.25F * (f * (float)k)) + (j - mMenuSize)) - l;
        mMenuContainer.offsetTopAndBottom(i1);
        BuildLayerFrameLayout buildlayerframelayout = mMenuContainer;
        byte byte0;
        if (i == 0)
        {
            byte0 = 4;
        } else
        {
            byte0 = 0;
        }
        buildlayerframelayout.setVisibility(byte0);
    }

    public void closeMenu(boolean flag)
    {
        animateOffsetTo(0, 0, flag);
    }

    protected void drawContentOverlay(Canvas canvas, int i)
    {
    }

    protected void drawDropShadow(Canvas canvas, int i)
    {
        int j = getWidth();
        int k = getHeight();
        mDropShadowDrawable.setBounds(0, k + i, j, k + i + mDropShadowSize);
        mDropShadowDrawable.draw(canvas);
    }

    protected void drawIndicator(Canvas canvas, int i)
    {
        if (mActiveView != null && isViewDescendant(mActiveView))
        {
            Integer integer = (Integer)mActiveView.getTag(R.id.mdActiveViewPosition);
            int j;
            if (integer == null)
            {
                j = 0;
            } else
            {
                j = integer.intValue();
            }
            if (j == mActivePosition)
            {
                int k = getHeight();
                int l = mMenuSize;
                int i1 = mActiveIndicator.getHeight();
                float f = (float)Math.abs(i) / (float)l;
                mActiveView.getDrawingRect(mActiveRect);
                offsetDescendantRectToMyCoords(mActiveView, mActiveRect);
                int j1 = mActiveIndicator.getWidth();
                int k1 = (int)((1.0F - INDICATOR_INTERPOLATOR.getInterpolation(1.0F - f)) * (float)i1) + (k + i);
                int l1 = k1 - i1;
                if (mIndicatorAnimating)
                {
                    int i2 = mActiveRect.left + (mActiveRect.width() - j1) / 2;
                    int j2 = mIndicatorStartPos;
                    b = j2 + (int)((float)(i2 - j2) * mIndicatorOffset);
                } else
                {
                    b = mActiveRect.left + (mActiveRect.width() - j1) / 2;
                }
                canvas.save();
                canvas.clipRect(b, k + i, j1 + b, k1);
                canvas.drawBitmap(mActiveIndicator, b, l1, null);
                canvas.restore();
            }
        }
    }

    protected void drawMenuOverlay(Canvas canvas, int i)
    {
        int j = getWidth();
        int k = getHeight();
        float f = (float)Math.abs(i) / (float)mMenuSize;
        mMenuOverlay.setBounds(0, k + i, j, k);
        mMenuOverlay.setAlpha((int)(185F * (1.0F - f)));
        mMenuOverlay.draw(canvas);
    }

    protected int getIndicatorStartPos()
    {
        return b;
    }

    protected void initPeekScroller()
    {
        int i = -mMenuSize / 3;
        mPeekScroller.startScroll(0, 0, i, 0, 5000);
    }

    protected boolean isContentTouch(MotionEvent motionevent)
    {
        return motionevent.getY() < (float)getHeight() + mOffsetPixels;
    }

    protected boolean onDownAllowDrag(MotionEvent motionevent)
    {
        int i = getHeight();
        return !mMenuVisible && mInitialMotionY >= (float)(i - mTouchSize) || mMenuVisible && mInitialMotionY <= (float)i + mOffsetPixels;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = k - i;
        int j1 = l - j;
        int k1 = (int)mOffsetPixels;
        int l1 = mMenuSize;
        mMenuContainer.layout(0, j1 - l1, i1, j1);
        a(k1);
        if (a)
        {
            mContentContainer.layout(0, 0, i1, j1);
            return;
        } else
        {
            mContentContainer.layout(0, k1, i1, j1 + k1);
            return;
        }
    }

    protected boolean onMoveAllowDrag(MotionEvent motionevent, float f)
    {
        int i = getHeight();
        return !mMenuVisible && mInitialMotionY >= (float)(i - mTouchSize) && f < 0.0F || mMenuVisible && mInitialMotionY <= (float)i + mOffsetPixels;
    }

    protected void onMoveEvent(float f)
    {
        setOffsetPixels(Math.max(Math.min(f + mOffsetPixels, 0.0F), -mMenuSize));
    }

    protected void onOffsetPixelsChanged(int i)
    {
        if (a)
        {
            mContentContainer.setTranslationY(i);
            a(i);
            invalidate();
            return;
        } else
        {
            mContentContainer.offsetTopAndBottom(i - mContentContainer.getTop());
            a(i);
            invalidate();
            return;
        }
    }

    protected void onUpEvent(MotionEvent motionevent)
    {
        int i = (int)mOffsetPixels;
        if (mIsDragging)
        {
            mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
            int j = (int)mVelocityTracker.getXVelocity();
            mLastMotionY = motionevent.getY();
            int k;
            if (mVelocityTracker.getYVelocity() < 0.0F)
            {
                k = -mMenuSize;
            } else
            {
                k = 0;
            }
            animateOffsetTo(k, j, true);
        } else
        if (mMenuVisible && motionevent.getY() < (float)(i + getHeight()))
        {
            closeMenu();
            return;
        }
    }

    public void openMenu(boolean flag)
    {
        animateOffsetTo(-mMenuSize, 0, flag);
    }

    public void setDropShadowColor(int i)
    {
        int j = 0xffffff & i;
        mDropShadowDrawable = new GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
            i, j
        });
        invalidate();
    }
}
