// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import java.util.ArrayList;

public class OmuListCache
{

    private ArrayList a;
    private int b;
    private int c;

    public OmuListCache()
    {
        a = new ArrayList();
        b = 0;
        c = 0;
    }

    public int getCurrentDataListIndex()
    {
        return b;
    }

    public ArrayList getDataList()
    {
        return a;
    }

    public int getDy()
    {
        return c;
    }

    public void setCurrentDataListIndex(int i)
    {
        b = i;
    }

    public void setDataList(ArrayList arraylist)
    {
        if (a == null)
        {
            a = new ArrayList();
        }
        a.clear();
        for (int i = 0; i < arraylist.size(); i++)
        {
            a.add(arraylist.get(i));
        }

    }

    public void setDy(int i)
    {
        c = i;
    }
}
