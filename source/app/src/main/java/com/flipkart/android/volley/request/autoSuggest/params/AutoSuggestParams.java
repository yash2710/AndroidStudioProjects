// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.autoSuggest.params;

import com.flipkart.logging.FkLogger;

public class AutoSuggestParams
{

    private String a;
    private Boolean b;

    public AutoSuggestParams(String s, boolean flag)
    {
        a = s;
        b = Boolean.valueOf(flag);
    }

    public String generateURI()
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        stringbuilder.append("?");
        a = a.replace(" ", "%20");
        if (a == null || a.length() <= 0) goto _L2; else goto _L1
_L1:
        try
        {
            stringbuilder.append("q=").append(a).append("&prefetchOn=false");
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
_L4:
        return stringbuilder.toString();
_L2:
        if (b.booleanValue())
        {
            stringbuilder.append("q=&prefetchOn=false");
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public String getQuery()
    {
        return a;
    }

    public void setQuery(String s)
    {
        a = s;
    }
}
