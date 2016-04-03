// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;


public class I
{

    private String a;
    private String b;
    private String c;
    private float d;

    public float getRating()
    {
        return d;
    }

    public String getReview()
    {
        return b;
    }

    public String getTime()
    {
        return c;
    }

    public String getUserName()
    {
        return a;
    }

    public void setRating(float f)
    {
        d = f;
    }

    public void setReview(String s)
    {
        b = s;
    }

    public void setTime(String s)
    {
        c = s;
    }

    public void setUserName(String s)
    {
        a = s;
    }

    public I()
    {
    }
}
