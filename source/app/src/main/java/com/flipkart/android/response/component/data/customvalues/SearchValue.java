// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable

public class SearchValue extends Renderable
{

    String searchHint;
    boolean showBarCode;
    boolean showVoice;
    boolean sticky;

    public SearchValue()
    {
    }

    public String getSearchHint()
    {
        return searchHint;
    }

    public boolean isShowBarCode()
    {
        return showBarCode;
    }

    public boolean isShowVoice()
    {
        return showVoice;
    }

    public boolean isSticky()
    {
        return sticky;
    }

    public void setSearchHint(String s)
    {
        searchHint = s;
    }

    public void setShowBarCode(boolean flag)
    {
        showBarCode = flag;
    }

    public void setShowVoice(boolean flag)
    {
        showVoice = flag;
    }

    public void setSticky(boolean flag)
    {
        sticky = flag;
    }
}
