// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.view.MotionEvent;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            EclairMotionEvent

public class WrapMotionEvent
{

    protected MotionEvent event;

    protected WrapMotionEvent(MotionEvent motionevent)
    {
        event = motionevent;
    }

    private static void a(int i)
    {
        if (i > 0)
        {
            throw new IllegalArgumentException("Invalid pointer index for Donut/Cupcake");
        } else
        {
            return;
        }
    }

    public static WrapMotionEvent wrap(MotionEvent motionevent)
    {
        EclairMotionEvent eclairmotionevent;
        try
        {
            eclairmotionevent = new EclairMotionEvent(motionevent);
        }
        catch (VerifyError verifyerror)
        {
            return new WrapMotionEvent(motionevent);
        }
        return eclairmotionevent;
    }

    public int getAction()
    {
        return event.getAction();
    }

    public int getPointerCount()
    {
        return 1;
    }

    public int getPointerId(int i)
    {
        a(i);
        return 0;
    }

    public float getX()
    {
        return event.getX();
    }

    public float getX(int i)
    {
        a(i);
        return getX();
    }

    public float getY()
    {
        return event.getY();
    }

    public float getY(int i)
    {
        a(i);
        return getY();
    }
}
