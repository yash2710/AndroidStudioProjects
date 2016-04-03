// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;

import java.util.Map;

// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable

public class SMUValue extends Renderable
{

    private Map image;
    private String textLinePrimary;
    private String textLineSecondary;
    private String textLineTernary;

    public SMUValue()
    {
        image = null;
    }

    public Map getImage()
    {
        return image;
    }

    public String getTextLinePrimary()
    {
        return textLinePrimary;
    }

    public String getTextLineSecondary()
    {
        return textLineSecondary;
    }

    public String getTextLineTernary()
    {
        return textLineTernary;
    }

    public void setImage(Map map)
    {
        image = map;
    }

    public void setTextLinePrimary(String s)
    {
        textLinePrimary = s;
    }

    public void setTextLineSecondary(String s)
    {
        textLineSecondary = s;
    }

    public void setTextLineTernary(String s)
    {
        textLineTernary = s;
    }
}
