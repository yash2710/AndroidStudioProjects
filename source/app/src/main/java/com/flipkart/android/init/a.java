// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.init;


final class a
{

    static final int a[];

    static 
    {
        a = new int[com.flipkart.android.config.FlipkartPropertiesReader.AppEnvironment.values().length];
        try
        {
            a[com.flipkart.android.config.FlipkartPropertiesReader.AppEnvironment.DEVELOPMENT.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[com.flipkart.android.config.FlipkartPropertiesReader.AppEnvironment.INTERNAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            a[com.flipkart.android.config.FlipkartPropertiesReader.AppEnvironment.RELEASE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2)
        {
            return;
        }
    }
}
