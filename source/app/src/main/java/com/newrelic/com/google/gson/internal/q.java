// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal;

import java.util.Comparator;

final class q
    implements Comparator
{

    q()
    {
    }

    public final int compare(Comparable comparable, Comparable comparable1)
    {
        return comparable.compareTo(comparable1);
    }

    public final volatile int compare(Object obj, Object obj1)
    {
        return compare((Comparable)obj, (Comparable)obj1);
    }
}
