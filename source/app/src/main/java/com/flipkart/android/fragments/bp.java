// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;


// Referenced classes of package com.flipkart.android.fragments:
//            SearchListFragment

final class bp
{

    private int a;
    private int b;
    private String c;

    public bp(SearchListFragment searchlistfragment, int i, int j, String s)
    {
        a = i;
        b = j;
        c = s;
    }

    public final int getEnd()
    {
        return b;
    }

    public final String getQuery()
    {
        return c;
    }

    public final int getStart()
    {
        return a;
    }

    public final void setEnd(int i)
    {
        b = i;
    }

    public final void setQuery(String s)
    {
        c = s;
    }

    public final void setStart(int i)
    {
        a = i;
    }
}
