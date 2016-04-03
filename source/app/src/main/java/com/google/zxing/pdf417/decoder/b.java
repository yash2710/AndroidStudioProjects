// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class b
{

    private final Map a = new HashMap();

    b()
    {
    }

    final void a(int i)
    {
        Integer integer = (Integer)a.get(Integer.valueOf(i));
        if (integer == null)
        {
            integer = Integer.valueOf(0);
        }
        Integer integer1 = Integer.valueOf(1 + integer.intValue());
        a.put(Integer.valueOf(i), integer1);
    }

    final int[] a()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = a.entrySet().iterator();
        int i = -1;
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if (((Integer)entry.getValue()).intValue() > i)
            {
                int j = ((Integer)entry.getValue()).intValue();
                arraylist.clear();
                arraylist.add(entry.getKey());
                i = j;
            } else
            if (((Integer)entry.getValue()).intValue() == i)
            {
                arraylist.add(entry.getKey());
            }
        } while (true);
        return PDF417Common.toIntArray(arraylist);
    }

    public final Integer getConfidence(int i)
    {
        return (Integer)a.get(Integer.valueOf(i));
    }
}
