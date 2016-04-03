// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


// Referenced classes of package com.flipkart.android.utils:
//            FkProductListContext

public class FilterResponse
{

    private String a;
    private FkProductListContext b;
    private boolean c;

    public FilterResponse()
    {
    }

    public String getFilterKey()
    {
        return a;
    }

    public FkProductListContext getFkContext()
    {
        return b;
    }

    public boolean isSaveCheckedItemInFkContext()
    {
        return c;
    }

    public void setFilterKey(String s)
    {
        a = s;
    }

    public void setFkContext(FkProductListContext fkproductlistcontext)
    {
        b = fkproductlistcontext;
    }

    public void setSaveCheckedItemInFkContext(boolean flag)
    {
        c = flag;
    }
}
