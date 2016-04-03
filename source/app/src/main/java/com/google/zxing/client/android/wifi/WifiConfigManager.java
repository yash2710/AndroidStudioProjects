// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.wifi;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import com.flipkart.logging.FkLogger;
import com.google.zxing.client.result.WifiParsedResult;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.android.wifi:
//            a

public final class WifiConfigManager extends AsyncTask
    implements TraceFieldInterface
{

    private static final String a = com/google/zxing/client/android/wifi/WifiConfigManager.getSimpleName();
    private static final Pattern b = Pattern.compile("[0-9A-Fa-f]+");
    public Trace _nr_trace;
    private final WifiManager c;

    public WifiConfigManager(WifiManager wifimanager)
    {
        c = wifimanager;
    }

    private static WifiConfiguration a(WifiParsedResult wifiparsedresult)
    {
        WifiConfiguration wificonfiguration = new WifiConfiguration();
        wificonfiguration.allowedAuthAlgorithms.clear();
        wificonfiguration.allowedGroupCiphers.clear();
        wificonfiguration.allowedKeyManagement.clear();
        wificonfiguration.allowedPairwiseCiphers.clear();
        wificonfiguration.allowedProtocols.clear();
        wificonfiguration.SSID = a(wifiparsedresult.getSsid(), new int[0]);
        wificonfiguration.hiddenSSID = wifiparsedresult.isHidden();
        return wificonfiguration;
    }

    private static transient String a(String s, int ai[])
    {
        boolean flag = true;
        if (s != null && b.matcher(s).matches()) goto _L2; else goto _L1
_L1:
        flag = false;
_L7:
        if (!flag) goto _L4; else goto _L3
_L3:
        return s;
_L2:
        if (ai.length == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i = ai.length;
        for (int j = 0; j < i; j++)
        {
            int k = ai[j];
            if (s.length() == k)
            {
                continue; /* Loop/switch isn't completed */
            }
        }

        flag = false;
        continue; /* Loop/switch isn't completed */
_L4:
        if (s == null || s.length() == 0)
        {
            return null;
        }
        if (s.charAt(0) == '"' && s.charAt(-1 + s.length()) == '"') goto _L3; else goto _L5
_L5:
        return (new StringBuilder("\"")).append(s).append('"').toString();
        if (true) goto _L7; else goto _L6
_L6:
    }

    private static void a(WifiManager wifimanager, WifiConfiguration wificonfiguration)
    {
        Integer integer;
        String s = wificonfiguration.SSID;
        Iterator iterator = wifimanager.getConfiguredNetworks().iterator();
        WifiConfiguration wificonfiguration1;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_157;
            }
            wificonfiguration1 = (WifiConfiguration)iterator.next();
        } while (!wificonfiguration1.SSID.equals(s));
        integer = Integer.valueOf(wificonfiguration1.networkId);
_L1:
        if (integer != null)
        {
            FkLogger.info(a, (new StringBuilder("Removing old configuration for network ")).append(wificonfiguration.SSID).toString());
            wifimanager.removeNetwork(integer.intValue());
            wifimanager.saveConfiguration();
        }
        int i = wifimanager.addNetwork(wificonfiguration);
        if (i >= 0)
        {
            if (wifimanager.enableNetwork(i, true))
            {
                FkLogger.info(a, (new StringBuilder("Associating to network ")).append(wificonfiguration.SSID).toString());
                wifimanager.saveConfiguration();
                return;
            } else
            {
                FkLogger.warn(a, (new StringBuilder("Failed to enable network ")).append(wificonfiguration.SSID).toString());
                return;
            }
        } else
        {
            FkLogger.warn(a, (new StringBuilder("Unable to add network ")).append(wificonfiguration.SSID).toString());
            return;
        }
        integer = null;
          goto _L1
    }

    public void _nr_setTrace(Trace trace)
    {
        try
        {
            _nr_trace = trace;
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    protected final transient Object doInBackground(WifiParsedResult awifiparsedresult[])
    {
        WifiParsedResult wifiparsedresult = awifiparsedresult[0];
        if (c.isWifiEnabled()) goto _L2; else goto _L1
_L1:
        FkLogger.info(a, "Enabling wi-fi...");
        if (!c.setWifiEnabled(true)) goto _L4; else goto _L3
_L3:
        int i;
        FkLogger.info(a, "Wi-fi enabled");
        i = 0;
_L12:
        if (c.isWifiEnabled()) goto _L2; else goto _L5
_L5:
        if (i < 10) goto _L7; else goto _L6
_L6:
        FkLogger.info(a, "Took too long to enable wi-fi, quitting");
_L9:
        return null;
_L4:
        FkLogger.warn(a, "Wi-fi could not be enabled!");
        return null;
_L7:
        FkLogger.info(a, "Still waiting for wi-fi to enable...");
        String s;
        IllegalArgumentException illegalargumentexception;
        a a1;
        String s1;
        WifiManager wifimanager;
        WifiConfiguration wificonfiguration;
        WifiManager wifimanager1;
        WifiConfiguration wificonfiguration1;
        WifiManager wifimanager2;
        WifiConfiguration wificonfiguration2;
        try
        {
            Thread.sleep(1000L);
        }
        catch (InterruptedException interruptedexception) { }
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        s = wifiparsedresult.getNetworkEncryption();
        try
        {
            a1 = com.google.zxing.client.android.wifi.a.a(s);
        }
        // Misplaced declaration of an exception variable
        catch (IllegalArgumentException illegalargumentexception)
        {
            FkLogger.warn(a, (new StringBuilder("Bad network type; see NetworkType values: ")).append(s).toString());
            return null;
        }
        if (a1 == a.c)
        {
            wifimanager2 = c;
            wificonfiguration2 = a(wifiparsedresult);
            wificonfiguration2.allowedKeyManagement.set(0);
            a(wifimanager2, wificonfiguration2);
            return null;
        }
        s1 = wifiparsedresult.getPassword();
        if (s1 == null || s1.length() == 0) goto _L9; else goto _L8
_L8:
        if (a1 == a.a)
        {
            wifimanager1 = c;
            wificonfiguration1 = a(wifiparsedresult);
            wificonfiguration1.wepKeys[0] = a(wifiparsedresult.getPassword(), new int[] {
                10, 26, 58
            });
            wificonfiguration1.wepTxKeyIndex = 0;
            wificonfiguration1.allowedAuthAlgorithms.set(1);
            wificonfiguration1.allowedKeyManagement.set(0);
            wificonfiguration1.allowedGroupCiphers.set(2);
            wificonfiguration1.allowedGroupCiphers.set(3);
            wificonfiguration1.allowedGroupCiphers.set(0);
            wificonfiguration1.allowedGroupCiphers.set(1);
            a(wifimanager1, wificonfiguration1);
            return null;
        }
        if (a1 != a.b) goto _L9; else goto _L10
_L10:
        wifimanager = c;
        wificonfiguration = a(wifiparsedresult);
        wificonfiguration.preSharedKey = a(wifiparsedresult.getPassword(), new int[] {
            64
        });
        wificonfiguration.allowedAuthAlgorithms.set(0);
        wificonfiguration.allowedProtocols.set(0);
        wificonfiguration.allowedProtocols.set(1);
        wificonfiguration.allowedKeyManagement.set(1);
        wificonfiguration.allowedKeyManagement.set(2);
        wificonfiguration.allowedPairwiseCiphers.set(1);
        wificonfiguration.allowedPairwiseCiphers.set(2);
        wificonfiguration.allowedGroupCiphers.set(2);
        wificonfiguration.allowedGroupCiphers.set(3);
        a(wifimanager, wificonfiguration);
        return null;
        if (true) goto _L12; else goto _L11
_L11:
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "WifiConfigManager#doInBackground", null);
_L1:
        Object obj = doInBackground((WifiParsedResult[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return obj;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "WifiConfigManager#doInBackground", null);
          goto _L1
    }

}
