// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import java.util.LinkedHashMap;
import java.util.Map;

public class AppExtras
{

    private Map androidExtraMessages;
    private transient Object ios;

    public AppExtras()
    {
        androidExtraMessages = new LinkedHashMap();
    }

    public Map getAndroidExtraMessages()
    {
        if (androidExtraMessages == null)
        {
            androidExtraMessages = new LinkedHashMap();
        }
        return androidExtraMessages;
    }

    public Object getIos()
    {
        return ios;
    }

    public void setAndroidExtraMessages(Map map)
    {
        androidExtraMessages = map;
    }

    public void setIos(Object obj)
    {
        ios = obj;
    }
}
