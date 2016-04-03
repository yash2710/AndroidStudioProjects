// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;


final class k
{

    static final int a[];

    static 
    {
        a = new int[TitlePageIndicator.IndicatorStyle.values().length];
        try
        {
            a[TitlePageIndicator.IndicatorStyle.Triangle.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[TitlePageIndicator.IndicatorStyle.Underline.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1)
        {
            return;
        }
    }
}
