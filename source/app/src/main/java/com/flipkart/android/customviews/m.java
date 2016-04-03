// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;

// Referenced classes of package com.flipkart.android.customviews:
//            EnhancedListView

final class m
    implements Comparable
{

    public int a;
    public View b;
    public View c;

    m(EnhancedListView enhancedlistview, int i, View view, View view1)
    {
        a = i;
        b = view;
        c = view1;
    }

    public final int compareTo(m m1)
    {
        return m1.a - a;
    }

    public final volatile int compareTo(Object obj)
    {
        return compareTo((m)obj);
    }
}
