// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;


final class y
    implements java.util.Map.Entry
{

    y a;
    y b;
    y c;
    y d;
    y e;
    final Object f;
    final int g;
    Object h;
    int i;

    y()
    {
        f = null;
        g = -1;
        e = this;
        d = this;
    }

    y(y y1, Object obj, int j, y y2, y y3)
    {
        a = y1;
        f = obj;
        g = j;
        i = 1;
        d = y2;
        e = y3;
        y3.d = this;
        y2.e = this;
    }

    public final boolean equals(Object obj)
    {
        boolean flag;
        boolean flag1;
        flag = obj instanceof java.util.Map.Entry;
        flag1 = false;
        if (!flag) goto _L2; else goto _L1
_L1:
        java.util.Map.Entry entry = (java.util.Map.Entry)obj;
        if (f != null) goto _L4; else goto _L3
_L3:
        Object obj2;
        obj2 = entry.getKey();
        flag1 = false;
        if (obj2 != null) goto _L2; else goto _L5
_L5:
        if (h != null) goto _L7; else goto _L6
_L6:
        Object obj1;
        obj1 = entry.getValue();
        flag1 = false;
        if (obj1 != null) goto _L2; else goto _L8
_L8:
        flag1 = true;
_L2:
        return flag1;
_L4:
        boolean flag2;
        flag2 = f.equals(entry.getKey());
        flag1 = false;
        if (!flag2) goto _L2; else goto _L5
_L7:
        boolean flag3;
        flag3 = h.equals(entry.getValue());
        flag1 = false;
        if (!flag3) goto _L2; else goto _L8
    }

    public final y first()
    {
        y y2;
        for (y y1 = b; y1 != null; y1 = y2)
        {
            y2 = y1.b;
            this = y1;
        }

        return this;
    }

    public final Object getKey()
    {
        return f;
    }

    public final Object getValue()
    {
        return h;
    }

    public final int hashCode()
    {
        int j;
        Object obj;
        int k;
        if (f == null)
        {
            j = 0;
        } else
        {
            j = f.hashCode();
        }
        obj = h;
        k = 0;
        if (obj != null)
        {
            k = h.hashCode();
        }
        return j ^ k;
    }

    public final y last()
    {
        y y2;
        for (y y1 = c; y1 != null; y1 = y2)
        {
            y2 = y1.c;
            this = y1;
        }

        return this;
    }

    public final Object setValue(Object obj)
    {
        Object obj1 = h;
        h = obj;
        return obj1;
    }

    public final String toString()
    {
        return (new StringBuilder()).append(f).append("=").append(h).toString();
    }
}
