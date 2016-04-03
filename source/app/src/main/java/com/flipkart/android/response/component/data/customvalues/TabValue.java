// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable

public class TabValue extends Renderable
{

    String displayText;
    String tabKey;

    public TabValue()
    {
    }

    public String getDisplayText()
    {
        return displayText;
    }

    public String getTabKey()
    {
        return tabKey;
    }

    public void setDisplayText(String s)
    {
        displayText = s;
    }

    public void setTabKey(String s)
    {
        tabKey = s;
    }
}
