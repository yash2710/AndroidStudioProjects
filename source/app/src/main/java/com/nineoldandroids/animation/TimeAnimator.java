// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;


// Referenced classes of package com.nineoldandroids.animation:
//            ValueAnimator

public class TimeAnimator extends ValueAnimator
{

    private TimeListener h;
    private long i;

    public TimeAnimator()
    {
        i = -1L;
    }

    final void a()
    {
    }

    final void a(float f)
    {
    }

    final boolean a(long l)
    {
        long l1 = 0L;
        if (d == 0)
        {
            d = 1;
            long l2;
            if (c < l1)
            {
                b = l;
            } else
            {
                b = l - c;
                c = -1L;
            }
        }
        if (h != null)
        {
            l2 = l - b;
            if (i >= l1)
            {
                l1 = l - i;
            }
            i = l;
            h.onTimeUpdate(this, l2, l1);
        }
        return false;
    }

    public void setTimeListener(TimeListener timelistener)
    {
        h = timelistener;
    }

    private class TimeListener
    {

        public abstract void onTimeUpdate(TimeAnimator timeanimator, long l, long l1);
    }

}
