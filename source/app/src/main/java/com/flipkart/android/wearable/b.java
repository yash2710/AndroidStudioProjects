// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.logging.FkLogger;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.flipkart.android.wearable:
//            WearableDataSender, c

final class b
    implements Runnable
{

    private List a;
    private WearableDataSender b;

    b(WearableDataSender wearabledatasender, List list)
    {
        b = wearabledatasender;
        a = list;
        super();
    }

    public final void run()
    {
        if (!WearableDataSender.a(b).isConnected() && !WearableDataSender.a(b).blockingConnect(30L, TimeUnit.SECONDS).isSuccess())
        {
            FkLogger.error(WearableDataSender.a(), "DataLayerListenerService failed to connect to GoogleApiClient.");
            return;
        } else
        {
            PutDataMapRequest putdatamaprequest = PutDataMapRequest.create("/wish_list");
            putdatamaprequest.getDataMap().putDataMapArrayList("wish_list", WearableDataSender.a(b, a));
            putdatamaprequest.getDataMap().putString("timestamp", String.valueOf(System.currentTimeMillis()));
            putdatamaprequest.getDataMap().putBoolean("is_logged_in", FlipkartPreferenceManager.instance().isLoggedIn().booleanValue());
            com.google.android.gms.wearable.PutDataRequest putdatarequest = putdatamaprequest.asPutDataRequest();
            Wearable.DataApi.putDataItem(WearableDataSender.a(b), putdatarequest).setResultCallback(new c(this));
            return;
        }
    }
}
