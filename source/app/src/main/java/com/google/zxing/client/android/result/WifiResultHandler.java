// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import android.net.wifi.WifiManager;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;
import com.google.zxing.client.android.wifi.WifiConfigManager;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.WifiParsedResult;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler, e

public final class WifiResultHandler extends ResultHandler
{

    private final CaptureActivity a;
    private final AsyncTaskExecInterface b = (AsyncTaskExecInterface)(new AsyncTaskExecManager()).build();

    public WifiResultHandler(CaptureActivity captureactivity, ParsedResult parsedresult)
    {
        super(captureactivity, parsedresult);
        a = captureactivity;
    }

    public final int getButtonCount()
    {
        return 1;
    }

    public final int getButtonText(int i)
    {
        return com.google.zxing.client.android.R.string.button_wifi;
    }

    public final CharSequence getDisplayContents()
    {
        WifiParsedResult wifiparsedresult = (WifiParsedResult)getResult();
        StringBuilder stringbuilder = new StringBuilder(50);
        String s = a.getString(com.google.zxing.client.android.R.string.wifi_ssid_label);
        ParsedResult.maybeAppend((new StringBuilder()).append(s).append('\n').append(wifiparsedresult.getSsid()).toString(), stringbuilder);
        String s1 = a.getString(com.google.zxing.client.android.R.string.wifi_type_label);
        ParsedResult.maybeAppend((new StringBuilder()).append(s1).append('\n').append(wifiparsedresult.getNetworkEncryption()).toString(), stringbuilder);
        return stringbuilder.toString();
    }

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_wifi;
    }

    public final void handleButtonPress(int i)
    {
        if (i == 0)
        {
            WifiParsedResult wifiparsedresult = (WifiParsedResult)getResult();
            WifiManager wifimanager = (WifiManager)b().getSystemService("wifi");
            Activity activity = b();
            activity.runOnUiThread(new e(this, activity));
            b.execute(new WifiConfigManager(wifimanager), new WifiParsedResult[] {
                wifiparsedresult
            });
            a.restartPreviewAfterDelay(0L);
        }
    }
}
