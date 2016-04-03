// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417;


public final class PDF417ResultMetadata
{

    private int a;
    private String b;
    private int c[];
    private boolean d;

    public PDF417ResultMetadata()
    {
    }

    public final String getFileId()
    {
        return b;
    }

    public final int[] getOptionalData()
    {
        return c;
    }

    public final int getSegmentIndex()
    {
        return a;
    }

    public final boolean isLastSegment()
    {
        return d;
    }

    public final void setFileId(String s)
    {
        b = s;
    }

    public final void setLastSegment(boolean flag)
    {
        d = flag;
    }

    public final void setOptionalData(int ai[])
    {
        c = ai;
    }

    public final void setSegmentIndex(int i)
    {
        a = i;
    }
}
