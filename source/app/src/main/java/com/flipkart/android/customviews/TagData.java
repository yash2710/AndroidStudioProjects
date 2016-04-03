// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;


public class TagData
{

    private String a;
    private int b;
    private boolean c;

    public TagData(String s, int i, boolean flag)
    {
        a = s;
        b = i;
        c = flag;
    }

    public int getTagIndex()
    {
        return b;
    }

    public String getTagText()
    {
        return a;
    }

    public boolean isCrossButton()
    {
        return c;
    }

    public void setCrossButton(boolean flag)
    {
        c = flag;
    }
}
