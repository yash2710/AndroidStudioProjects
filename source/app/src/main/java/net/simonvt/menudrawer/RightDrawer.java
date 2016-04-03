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
//            HorizontalDrawer, BuildLayerFrameLayout, Scroller

public class RightDrawer extends HorizontalDrawer
{

    private int b;

    RightDrawer(Activity activity, int i)
    {
        super(activity, i);
    }

    public RightDrawer(Context context)
    {
        super(context);
    }

    public RightDrawer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public RightDrawer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    private void a(int i)
    {
        int j;
        float f;
label0:
        {
label1:
            {
                if (mOffsetMenu && mMenuSize != 0)
                {
                    j = mMenuSize;
                    f = ((float)j + (float)i) / (float)j;
                    if (!a)
                    {
                        break label0;
                    }
                    if (i == 0)
                    {
                        break label1;
                    }
                    int j1 = (int)(0.25F * (f * (float)j));
                    mMenuContainer.setTranslationX(j1);
                }
                return;
            }
            mMenuContainer.setTranslationX(-j);
            return;
        }
        int k = getWidth();
        int l = mMenuContainer.getRight();
        int i1 = (k + (int)(0.25F * (f * (float)j))) - l;
        mMenuContainer.offsetLeftAndRight(i1);
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
        int j = getHeight();
        int k = i + getWidth();
        int l = k + mDropShadowSize;
        mDropShadowDrawable.setBounds(k, 0, l, j);
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
                int k = getWidth();
                int l = mMenuSize;
                int i1 = mActiveIndicator.getWidth();
                int j1 = k + i;
                float f = (float)Math.abs(i) / (float)l;
                mActiveView.getDrawingRect(mActiveRect);
                offsetDescendantRectToMyCoords(mActiveView, mActiveRect);
                int k1 = j1 + (int)((1.0F - INDICATOR_INTERPOLATOR.getInterpolation(1.0F - f)) * (float)i1);
                int l1 = k1 - i1;
                if (mIndicatorAnimating)
                {
                    int i2 = mActiveRect.top + (mActiveRect.height() - mActiveIndicator.getHeight()) / 2;
                    int j2 = mIndicatorStartPos;
                    b = j2 + (int)((float)(i2 - j2) * mIndicatorOffset);
                } else
                {
                    b = mActiveRect.top + (mActiveRect.height() - mActiveIndicator.getHeight()) / 2;
                }
                canvas.save();
                canvas.clipRect(j1, 0, k1, getHeight());
                canvas.drawBitmap(mActiveIndicator, l1, b, null);
                canvas.restore();
            }
        }
    }

    protected void drawMenuOverlay(Canvas canvas, int i)
    {
        int j = getHeight();
        int k = getWidth();
        int l = k + i;
        float f = (float)Math.abs(i) / (float)mMenuSize;
        mMenuOverlay.setBounds(l, 0, k, j);
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
        return motionevent.getX() < (float)getWidth() + mOffsetPixels;
    }

    protected boolean onDownAllowDrag(MotionEvent motionevent)
    {
        int i = getWidth();
        int j = (int)mInitialMotionX;
        return !mMenuVisible && j >= i - mTouchSize || mMenuVisible && (float)j <= (float)i + mOffsetPixels;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = k - i;
        int j1 = l - j;
        int k1 = (int)mOffsetPixels;
        mMenuContainer.layout(i1 - mMenuSize, 0, i1, j1);
        a(k1);
        if (a)
        {
            mContentContainer.layout(0, 0, i1, j1);
            return;
        } else
        {
            mContentContainer.layout(k1, 0, i1 + k1, j1);
            return;
        }
    }

    protected boolean onMoveAllowDrag(MotionEvent motionevent, float f)
    {
        int i = getWidth();
        int j = (int)mInitialMotionX;
        return !mMenuVisible && j >= i - mTouchSize && f < 0.0F || mMenuVisible && (float)j <= (float)i + mOffsetPixels;
    }

    protected void onMoveEvent(float f)
    {
        setOffsetPixels(Math.max(Math.min(f + mOffsetPixels, 0.0F), -mMenuSize));
    }

    protected void onOffsetPixelsChanged(int i)
    {
        if (a)
        {
            mContentContainer.setTranslationX(i);
            a(i);
            invalidate();
            return;
        } else
        {
            mContentContainer.offsetLeftAndRight(i - mContentContainer.getLeft());
            a(i);
            invalidate();
            return;
        }
    }

    protected void onUpEvent(MotionEvent motionevent)
    {
        int i = (int)mOffsetPixels;
        int j = getWidth();
        if (mIsDragging)
        {
            mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
            int k = (int)mVelocityTracker.getXVelocity();
            mLastMotionX = motionevent.getX();
            int l;
            if (mVelocityTracker.getXVelocity() > 0.0F)
            {
                l = 0;
            } else
            {
                l = -mMenuSize;
            }
            animateOffsetTo(l, k, true);
        } else
        if (mMenuVisible && motionevent.getX() < (float)(i + j))
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
        mDropShadowDrawable = new GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.LEFT_RIGHT, new int[] {
            i, j
        });
        invalidate();
    }
}
