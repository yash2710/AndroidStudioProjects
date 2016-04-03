// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ButtonValue

public class CallUsValue extends Renderable
{

    private ButtonValue callButton;
    private String subTitle;
    private String title;

    public CallUsValue()
    {
    }

    public ButtonValue getCallButton()
    {
        return callButton;
    }

    public String getSubTitle()
    {
        return subTitle;
    }

    public String getTitle()
    {
        return title;
    }

    public void setCallButton(ButtonValue buttonvalue)
    {
        callButton = buttonvalue;
    }

    public void setSubTitle(String s)
    {
        subTitle = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
