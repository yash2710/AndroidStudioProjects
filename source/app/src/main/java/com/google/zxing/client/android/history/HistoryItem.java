// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.history;

import com.google.zxing.Result;

public final class HistoryItem
{

    private final Result a;
    private final String b;
    private final String c;

    HistoryItem(Result result, String s, String s1)
    {
        a = result;
        b = s;
        c = s1;
    }

    public final String getDisplayAndDetails()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (b == null || b.length() == 0)
        {
            stringbuilder.append(a.getText());
        } else
        {
            stringbuilder.append(b);
        }
        if (c != null && c.length() > 0)
        {
            stringbuilder.append(" : ").append(c);
        }
        return stringbuilder.toString();
    }

    public final Result getResult()
    {
        return a;
    }
}
