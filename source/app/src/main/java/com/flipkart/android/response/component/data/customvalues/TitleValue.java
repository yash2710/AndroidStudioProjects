// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable

public class TitleValue extends Renderable
{

    String text;

    public TitleValue(String s)
    {
        text = s;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String s)
    {
        text = s;
    }
}
