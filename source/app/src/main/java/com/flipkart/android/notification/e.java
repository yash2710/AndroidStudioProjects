// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification;

import android.graphics.Bitmap;

final class e
{

    private Bitmap a;
    private String b;
    private String c;

    private e()
    {
    }

    e(byte byte0)
    {
        this();
    }

    public final Bitmap getBitmap()
    {
        return a;
    }

    public final String getTag()
    {
        return c;
    }

    public final String getUrl()
    {
        return b;
    }

    public final void setBitmap(Bitmap bitmap)
    {
        a = bitmap;
    }

    public final void setTag(String s)
    {
        c = s;
    }

    public final void setUrl(String s)
    {
        b = s;
    }
}
