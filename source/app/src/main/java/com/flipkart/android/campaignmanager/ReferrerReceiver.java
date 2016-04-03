// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.campaignmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.adobe.adms.measurement.ADMS_ReferrerHandler;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.utils.ReferralUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.UUID;

public class ReferrerReceiver extends BroadcastReceiver
{

    public ReferrerReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        if (intent == null)
        {
            break MISSING_BLOCK_LABEL_111;
        }
        Bundle bundle;
        String s;
        UUID uuid;
        try
        {
            if (!intent.getAction().equals("com.android.vending.INSTALL_REFERRER"))
            {
                break MISSING_BLOCK_LABEL_111;
            }
            bundle = intent.getExtras();
        }
        catch (Exception exception)
        {
            return;
        }
        if (bundle == null)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        s = bundle.getString("referrer");
        if (!StringUtils.isNullOrEmpty(s))
        {
            uuid = UUID.randomUUID();
            s = (new StringBuilder()).append(s).append("&fk_uuid=").append(uuid).toString();
            FlipkartPreferenceManager.instance().saveReferrerValue(s);
        }
        FlipkartPreferenceManager.instance().saveReferrerValue(s);
        ReferralUtils.sendReferral(s, "paid");
        (new ADMS_ReferrerHandler()).processIntent(context, intent);
    }
}
