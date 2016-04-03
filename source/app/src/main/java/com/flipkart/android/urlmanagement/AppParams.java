// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement;


// Referenced classes of package com.flipkart.android.urlmanagement:
//            AppAction

public class AppParams
{

    private AppAction a;
    private String b;

    public AppParams()
    {
        b = "";
    }

    public AppAction getAction()
    {
        return a;
    }

    public String getParams()
    {
        return b;
    }

    public void setAction(AppAction appaction)
    {
        a = appaction;
    }

    public void setParams(String s)
    {
        b = s;
    }
}
