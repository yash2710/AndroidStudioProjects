// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;


public class Location
{

    private final String a;
    private final String b;

    public Location(String s, String s1)
    {
        if (s == null || s1 == null)
        {
            throw new IllegalArgumentException("Country code and region must not be null.");
        } else
        {
            a = s;
            b = s1;
            return;
        }
    }

    public String getCountryCode()
    {
        return a;
    }

    public String getRegion()
    {
        return b;
    }
}
