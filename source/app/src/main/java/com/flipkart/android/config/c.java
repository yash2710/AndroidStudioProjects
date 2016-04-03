// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;


final class c
{

    static final int a[];

    static 
    {
        a = new int[FlipkartPropertiesReader.AppEnvironment.values().length];
        try
        {
            a[FlipkartPropertiesReader.AppEnvironment.DEVELOPMENT.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[FlipkartPropertiesReader.AppEnvironment.INTERNAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            a[FlipkartPropertiesReader.AppEnvironment.RELEASE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            a[FlipkartPropertiesReader.AppEnvironment.TESTING.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3)
        {
            return;
        }
    }
}
