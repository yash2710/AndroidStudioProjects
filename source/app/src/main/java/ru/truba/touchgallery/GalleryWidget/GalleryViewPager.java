// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import ru.truba.touchgallery.TouchView.TouchImageView;

// Referenced classes of package ru.truba.touchgallery.GalleryWidget:
//            OnPagerTouchListner

public class GalleryViewPager extends ViewPager
{

    private OnPagerTouchListner a;
    private PointF b;
    public TouchImageView mCurrentView;

    public GalleryViewPager(Context context)
    {
        super(context);
        a = null;
    }

    public GalleryViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        a = null;
    }

    private float[] a(MotionEvent motionevent)
    {
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 2: default 36
    //                   0 38
    //                   1 62
    //                   2 62;
           goto _L1 _L2 _L3 _L3
_L1:
        return null;
_L2:
        b = new PointF(motionevent.getX(0), motionevent.getY(0));
          goto _L1
_L3:
        PointF pointf = new PointF(motionevent.getX(0), motionevent.getY(0));
        float af[] = new float[2];
        af[0] = pointf.x - b.x;
        af[1] = pointf.y - b.y;
        return af;
    }

    public OnPagerTouchListner getListner()
    {
        return a;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
label0:
        {
            {
                float af[];
                boolean flag;
                boolean flag1;
                boolean flag2;
                boolean flag3;
                boolean flag4;
                try
                {
                    if (a != null)
                    {
                        a.onPagerTouch();
                    }
                    if ((0xff & motionevent.getAction()) == 1)
                    {
                        super.onInterceptTouchEvent(motionevent);
                    }
                    af = a(motionevent);
                    flag = mCurrentView.pagerCanScroll();
                }
                catch (Exception exception)
                {
                    return false;
                }
                if (!flag)
                {
                    break label0;
                }
                try
                {
                    super.onInterceptTouchEvent(motionevent);
                    flag4 = super.onInterceptTouchEvent(motionevent);
                }
                catch (Exception exception1)
                {
                    return false;
                }
                flag1 = flag4;
            }
            return flag1;
        }
        if (af == null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        if (mCurrentView.onRightSide && af[0] < 0.0F)
        {
            return super.onInterceptTouchEvent(motionevent);
        }
        if (af == null)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        if (mCurrentView.onLeftSide && af[0] > 0.0F)
        {
            return super.onInterceptTouchEvent(motionevent);
        }
        flag1 = false;
        if (af != null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        if (mCurrentView.onLeftSide)
        {
            break; /* Loop/switch isn't completed */
        }
        flag3 = mCurrentView.onRightSide;
        flag1 = false;
        if (!flag3) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_71;
_L1:
        flag2 = super.onInterceptTouchEvent(motionevent);
        return flag2;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        float af[];
        if ((0xff & motionevent.getAction()) == 1)
        {
            super.onTouchEvent(motionevent);
        }
        af = a(motionevent);
        if (mCurrentView.pagerCanScroll())
        {
            return super.onTouchEvent(motionevent);
        }
        if (af == null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        if (mCurrentView.onRightSide && af[0] < 0.0F)
        {
            return super.onTouchEvent(motionevent);
        }
        if (af == null)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        if (mCurrentView.onLeftSide && af[0] > 0.0F)
        {
            return super.onTouchEvent(motionevent);
        }
        if (af != null)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        boolean flag;
        if (!mCurrentView.onLeftSide && !mCurrentView.onRightSide)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        flag = super.onTouchEvent(motionevent);
        return flag;
        Exception exception;
        exception;
        return false;
    }

    public void setOnPagerTouchListner(OnPagerTouchListner onpagertouchlistner)
    {
        a = onpagertouchlistner;
    }
}
