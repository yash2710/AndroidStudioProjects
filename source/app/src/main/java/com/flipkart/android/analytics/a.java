// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;

import com.flipkart.android.login.LoginType;

final class a
{

    static final int a[];

    static 
    {
        a = new int[LoginType.values().length];
        try
        {
            a[LoginType.Google.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[LoginType.Facebook.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1)
        {
            return;
        }
    }
}
