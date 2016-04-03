// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.view.MotionEvent;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            WrapMotionEvent

public class EclairMotionEvent extends WrapMotionEvent
{

    protected EclairMotionEvent(MotionEvent motionevent)
    {
        super(motionevent);
    }

    public int getPointerCount()
    {
        return event.getPointerCount();
    }

    public int getPointerId(int i)
    {
        return event.getPointerId(i);
    }

    public float getX(int i)
    {
        return event.getX(i);
    }

    public float getY(int i)
    {
        return event.getY(i);
    }
}
