// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.drawable;

import java.util.HashMap;
import java.util.Map;

public class FilterDrawableMap
{

    private static FilterDrawableMap a;
    private Map b;

    public FilterDrawableMap()
    {
        b = new HashMap();
    }

    public static FilterDrawableMap getInstance()
    {
        com/flipkart/android/utils/drawable/FilterDrawableMap;
        JVM INSTR monitorenter ;
        FilterDrawableMap filterdrawablemap;
        if (a == null)
        {
            a = new FilterDrawableMap();
        }
        filterdrawablemap = a;
        com/flipkart/android/utils/drawable/FilterDrawableMap;
        JVM INSTR monitorexit ;
        return filterdrawablemap;
        Exception exception;
        exception;
        throw exception;
    }

    public Integer getDrawableForFilter(String s)
    {
        if (b.containsKey(s))
        {
            return (Integer)b.get(s);
        } else
        {
            return Integer.valueOf(0);
        }
    }

    public void initFilterDrawableMap()
    {
        b.put("Brand", Integer.valueOf(0x7f020063));
        b.put("Color", Integer.valueOf(0x7f020092));
        b.put("Offers", Integer.valueOf(0x7f02011f));
        b.put("Price", Integer.valueOf(0x7f02014b));
        b.put("Size", Integer.valueOf(0x7f020161));
        b.put("Brand", Integer.valueOf(0x7f020063));
    }
}
