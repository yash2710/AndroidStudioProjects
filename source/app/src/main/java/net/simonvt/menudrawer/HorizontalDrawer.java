// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;

// Referenced classes of package net.simonvt.menudrawer:
//            DraggableDrawer, BuildLayerFrameLayout

public abstract class HorizontalDrawer extends DraggableDrawer
{

    HorizontalDrawer(Activity activity, int i)
    {
        super(activity, i);
    }

    public HorizontalDrawer(Context context)
    {
        super(context);
    }

    public HorizontalDrawer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public HorizontalDrawer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        i = 0xff & motionevent.getAction();
        if (i == 0 && mMenuVisible && isCloseEnough())
        {
            setOffsetPixels(0.0F);
            stopAnimation();
            endPeek();
            setDrawerState(0);
        }
        if (!mMenuVisible || !isContentTouch(motionevent)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        int j;
        j = mTouchMode;
        flag = false;
        if (j == 0) goto _L4; else goto _L3
_L3:
        if (i != 0 && mIsDragging)
        {
            return true;
        }
        i;
        JVM INSTR tableswitch 0 3: default 124
    //                   0 151
    //                   1 370
    //                   2 234
    //                   3 370;
           goto _L5 _L6 _L7 _L8 _L7
_L5:
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        return mIsDragging;
_L6:
        float f5 = motionevent.getX();
        mInitialMotionX = f5;
        mLastMotionX = f5;
        float f6 = motionevent.getY();
        mInitialMotionY = f6;
        mLastMotionY = f6;
        if (onDownAllowDrag(motionevent))
        {
            byte byte0;
            if (mMenuVisible)
            {
                byte0 = 8;
            } else
            {
                byte0 = 0;
            }
            setDrawerState(byte0);
            stopAnimation();
            endPeek();
            mIsDragging = false;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        float f = motionevent.getX();
        float f1 = f - mLastMotionX;
        float f2 = Math.abs(f1);
        float f3 = motionevent.getY();
        float f4 = Math.abs(f3 - mLastMotionY);
        if (f2 > (float)mTouchSlop && f2 > f4)
        {
            if (mOnInterceptMoveEventListener != null && mTouchMode == 2 && canChildScrollHorizontally(mContentContainer, false, (int)f1, (int)f, (int)f3))
            {
                endDrag();
                return false;
            }
            if (onMoveAllowDrag(motionevent, f1))
            {
                setDrawerState(2);
                mIsDragging = true;
                mLastMotionX = f;
                mLastMotionY = f3;
            }
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (Math.abs(mOffsetPixels) > (float)(mMenuSize / 2))
        {
            openMenu();
        } else
        {
            closeMenu();
        }
        if (true) goto _L5; else goto _L9
_L9:
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getMode(j);
        if (k != 0x40000000 || l != 0x40000000)
        {
            throw new IllegalStateException("Must measure with an exact size");
        }
        int i1 = android.view.View.MeasureSpec.getSize(i);
        int j1 = android.view.View.MeasureSpec.getSize(j);
        if (!mMenuSizeSet)
        {
            mMenuSize = (int)(0.8F * (float)i1);
        }
        if (mOffsetPixels == -1F)
        {
            openMenu(false);
        }
        int k1 = getChildMeasureSpec(i, 0, mMenuSize);
        int l1 = getChildMeasureSpec(i, 0, j1);
        mMenuContainer.measure(k1, l1);
        int i2 = getChildMeasureSpec(i, 0, i1);
        int j2 = getChildMeasureSpec(i, 0, j1);
        mContentContainer.measure(i2, j2);
        setMeasuredDimension(i1, j1);
        updateTouchAreaSize();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        if (!mMenuVisible && !mIsDragging && mTouchMode == 0)
        {
            return false;
        }
        i = 0xff & motionevent.getAction();
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        i;
        JVM INSTR tableswitch 0 3: default 84
    //                   0 86
    //                   1 310
    //                   2 145
    //                   3 310;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        return true;
_L2:
        float f7 = motionevent.getX();
        mInitialMotionX = f7;
        mLastMotionX = f7;
        float f8 = motionevent.getY();
        mInitialMotionY = f8;
        mLastMotionY = f8;
        if (onDownAllowDrag(motionevent))
        {
            stopAnimation();
            endPeek();
            startLayerTranslation();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (!mIsDragging)
        {
            float f2 = motionevent.getX();
            float f3 = f2 - mLastMotionX;
            float f4 = Math.abs(f3);
            float f5 = Math.abs(motionevent.getY() - mLastMotionY);
            if (f4 > (float)mTouchSlop && f4 > f5 && onMoveAllowDrag(motionevent, f3))
            {
                setDrawerState(2);
                mIsDragging = true;
                float f;
                float f1;
                float f6;
                if (f2 - mInitialMotionX > 0.0F)
                {
                    f6 = mInitialMotionX + (float)mTouchSlop;
                } else
                {
                    f6 = mInitialMotionX - (float)mTouchSlop;
                }
                mLastMotionX = f6;
            }
        }
        if (mIsDragging)
        {
            startLayerTranslation();
            f = motionevent.getX();
            f1 = f - mLastMotionX;
            mLastMotionX = f;
            onMoveEvent(f1);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        onUpEvent(motionevent);
        if (true) goto _L1; else goto _L5
_L5:
    }
}