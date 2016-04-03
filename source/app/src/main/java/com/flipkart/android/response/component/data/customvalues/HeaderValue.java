// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ImageValue, TimerValue, TitleValue

public class HeaderValue extends Renderable
{

    ImageValue imageValue;
    TimerValue timerValue;
    TitleValue titleValue;

    public HeaderValue(TitleValue titlevalue, ImageValue imagevalue, TimerValue timervalue)
    {
        titleValue = titlevalue;
        imageValue = imagevalue;
        timerValue = timervalue;
    }

    public ImageValue getImageValue()
    {
        return imageValue;
    }

    public TimerValue getTimerValue()
    {
        return timerValue;
    }

    public TitleValue getTitleValue()
    {
        return titleValue;
    }

    public void setImageValue(ImageValue imagevalue)
    {
        imageValue = imagevalue;
    }

    public void setTimerValue(TimerValue timervalue)
    {
        timerValue = timervalue;
    }

    public void setTitleValue(TitleValue titlevalue)
    {
        titleValue = titlevalue;
    }
}
