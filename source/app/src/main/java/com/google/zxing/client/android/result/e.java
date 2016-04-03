// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import android.widget.Toast;

// Referenced classes of package com.google.zxing.client.android.result:
//            WifiResultHandler

final class e
    implements Runnable
{

    private Activity a;

    e(WifiResultHandler wifiresulthandler, Activity activity)
    {
        a = activity;
        super();
    }

    public final void run()
    {
        Toast.makeText(a.getApplicationContext(), com.google.zxing.client.android.R.string.wifi_changing_network, 0).show();
    }
}
