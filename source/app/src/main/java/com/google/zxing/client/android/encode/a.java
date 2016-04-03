// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.encode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

// Referenced classes of package com.google.zxing.client.android.encode:
//            b

abstract class a
{

    a()
    {
    }

    static String a(String s)
    {
        String s1;
        if (s != null)
        {
            if ((s1 = s.trim()).length() != 0)
            {
                return s1;
            }
        }
        return null;
    }

    static void a(StringBuilder stringbuilder, StringBuilder stringbuilder1, String s, Iterable iterable, int i, b b1, b b2, char c)
    {
        if (iterable != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        HashSet hashset;
        Iterator iterator;
        int j;
        hashset = new HashSet(2);
        iterator = iterable.iterator();
        j = 0;
_L5:
        if (!iterator.hasNext()) goto _L1; else goto _L3
_L3:
        String s1 = a((String)iterator.next());
        if (s1 == null || s1.length() <= 0 || hashset.contains(s1)) goto _L5; else goto _L4
_L4:
        stringbuilder.append(s).append(':').append(b2.format(s1)).append(c);
        String s2;
        if (b1 == null)
        {
            s2 = s1;
        } else
        {
            s2 = b1.format(s1);
        }
        stringbuilder1.append(s2).append('\n');
        if (++j == i) goto _L1; else goto _L6
_L6:
        hashset.add(s1);
          goto _L5
    }

    static void a(StringBuilder stringbuilder, StringBuilder stringbuilder1, String s, String s1, b b1, char c)
    {
        String s2 = a(s1);
        if (s2 != null)
        {
            stringbuilder.append(s).append(':').append(b1.format(s2)).append(c);
            stringbuilder1.append(s2).append('\n');
        }
    }

    abstract String[] encode(Iterable iterable, String s, Iterable iterable1, Iterable iterable2, Iterable iterable3, Iterable iterable4, String s1);
}
