// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


class behavior
{

    private tBehavior behavior;
    private int timeoutPeriod;

    tBehavior getBehavior()
    {
        return behavior;
    }

    int getTimeoutPeriod()
    {
        return timeoutPeriod;
    }

    tBehavior(int i, tBehavior tbehavior)
    {
        timeoutPeriod = i;
        behavior = tbehavior;
    }
}
