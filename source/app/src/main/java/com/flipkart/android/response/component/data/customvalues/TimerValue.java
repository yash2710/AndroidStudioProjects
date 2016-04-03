// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable

public class TimerValue extends Renderable
{

    long currentSystemTime;
    long timeRemaining;

    public TimerValue()
    {
        setCurrentSystemTime(System.currentTimeMillis());
    }

    public long getCurrentSystemTime()
    {
        return currentSystemTime;
    }

    public long getTimeRemaining()
    {
        return timeRemaining;
    }

    public void setCurrentSystemTime(long l)
    {
        currentSystemTime = l;
    }

    public void setTimeRemaining(long l)
    {
        timeRemaining = l;
    }
}
