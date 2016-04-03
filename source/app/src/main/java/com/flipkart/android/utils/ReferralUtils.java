// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.ReferrerVDataHandler;

// Referenced classes of package com.flipkart.android.utils:
//            w

public class ReferralUtils
{

    public ReferralUtils()
    {
    }

    public static void sendReferral(String s, String s1)
    {
        w w1 = new w();
        TrackingHelper.sendReferralInfo(s);
        w1.sendReferrerString(s, FlipkartPreferenceManager.instance().getFirstLoadTime(), s1);
    }
}
