// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import com.google.zxing.client.result.ParsedResultType;

final class d
{

    static final int a[];

    static 
    {
        a = new int[ParsedResultType.values().length];
        try
        {
            a[ParsedResultType.ADDRESSBOOK.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[ParsedResultType.EMAIL_ADDRESS.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            a[ParsedResultType.PRODUCT.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            a[ParsedResultType.URI.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            a[ParsedResultType.WIFI.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            a[ParsedResultType.GEO.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            a[ParsedResultType.TEL.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            a[ParsedResultType.SMS.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            a[ParsedResultType.CALENDAR.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            a[ParsedResultType.ISBN.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror9)
        {
            return;
        }
    }
}
