// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import java.util.HashMap;

public class ContextCache
{

    private static ContextCache b = null;
    private static Object c = new Object();
    private HashMap a;

    private ContextCache()
    {
        a = null;
        a = new HashMap();
    }

    public static ContextCache getInstance()
    {
        synchronized (c)
        {
            if (b == null)
            {
                b = new ContextCache();
            }
        }
        return b;
    }

    public Object getResponse(String s)
    {
        Object obj = a.get(s);
        a.clear();
        return obj;
    }

    public void putResponse(String s, Object obj)
    {
        a.put(s, obj);
    }

}
