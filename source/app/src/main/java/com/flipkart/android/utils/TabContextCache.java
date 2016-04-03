// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import java.util.HashMap;

public class TabContextCache
{

    private static TabContextCache b = null;
    private static Object c = new Object();
    private HashMap a;

    private TabContextCache()
    {
        a = null;
        a = new HashMap();
    }

    public static TabContextCache getInstance()
    {
        synchronized (c)
        {
            if (b == null)
            {
                b = new TabContextCache();
            }
        }
        return b;
    }

    public boolean checkIfAvailable(String s)
    {
        return a.containsKey(s);
    }

    public void clearCache()
    {
        a.clear();
    }

    public Object getResponse(String s)
    {
        Object obj = a.get(s);
        a.remove(s);
        return obj;
    }

    public void putResponse(String s, Object obj)
    {
        a.put(s, obj);
    }

}
