// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.ReferrerVDataHandler;

final class w extends ReferrerVDataHandler
{

    w()
    {
    }

    public final void errorReceived(int i, int j, String s)
    {
        super.errorReceived(i, j, s);
        FlipkartPreferenceManager.instance().setReferralSent(false);
    }

    public final void resultReceived(Object obj, boolean flag)
    {
        FlipkartPreferenceManager.instance().setReferralSent(true);
    }
}
