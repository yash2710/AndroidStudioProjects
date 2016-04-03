// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.widget.Spinner;

final class n
    implements Runnable
{

    private Spinner a;
    private android.widget.AdapterView.OnItemSelectedListener b;

    n(Spinner spinner, android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener)
    {
        a = spinner;
        b = onitemselectedlistener;
        super();
    }

    public final void run()
    {
        a.setOnItemSelectedListener(b);
    }
}
