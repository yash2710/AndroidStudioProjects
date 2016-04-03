// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.AdX.tag;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Referenced classes of package com.AdX.tag:
//            d, e, f, g, 
//            c, DeviceSerial, AdXURLConnection, a, 
//            b, i, h, j

public final class AdXConnect
{

    public static final int ADX_CUSTOMERID = 1;
    public static final int ADX_DESTINATION_ID = 12;
    public static final int ADX_END_DATE = 8;
    public static final int ADX_EVENT_CONFIRMATION = 5;
    public static final int ADX_EVENT_HOMEPAGE = 0;
    public static final int ADX_EVENT_LEVEL = 6;
    public static final int ADX_EVENT_LISTINGVIEW = 3;
    public static final int ADX_EVENT_PRODUCTVIEW = 2;
    public static final int ADX_EVENT_SEARCH = 1;
    public static final int ADX_EVENT_VIEWCART = 4;
    public static final int ADX_KEYWORD = 3;
    public static final int ADX_LEVEL = 13;
    public static final int ADX_NEWCUSTOMER = 9;
    public static final int ADX_PARAMETER_DATE = 17;
    public static final int ADX_PARAMETER_FLOAT = 16;
    public static final int ADX_PARAMETER_INT = 14;
    public static final int ADX_PARAMETER_STRING = 15;
    public static final int ADX_PRODUCT = 2;
    public static final int ADX_SOURCE_ID = 11;
    public static final int ADX_START_DATE = 7;
    public static final int ADX_TRANSACTION_ID = 10;
    public static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    public static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    public static boolean DEBUG;
    private static String E = "";
    private static String G = "AdXPrefrences";
    private static String I = "AdXReferral";
    private static String J = "AdXDLReferral";
    private static String K = "AdXSeencount";
    private static String L = "ReceiverDone";
    public static int LOGLEVEL = 1;
    private static String M = "AdXUpdate";
    private static String N = "InstallReferral";
    private static JSONObject O = null;
    private static JSONArray P = null;
    public static boolean WARN;
    private static AdXConnect e = null;
    private static AdXConnect f = null;
    private static AdXURLConnection g = null;
    private static final ScheduledExecutorService h = Executors.newScheduledThreadPool(1);
    public static int sendDelay = 3;
    private String A;
    private String B;
    private String C;
    private boolean D;
    private String F;
    private String H;
    private i a;
    private h b;
    private j c;
    private Context d;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    private AdXConnect(Context context)
    {
        a = null;
        b = null;
        c = null;
        d = null;
        i = "";
        j = "";
        k = "";
        l = "";
        m = "";
        n = "";
        o = "";
        p = "";
        q = "";
        r = "";
        s = "";
        t = "";
        u = "";
        v = "";
        w = "3.2";
        x = "";
        y = "";
        z = "";
        A = "";
        B = "";
        C = "";
        D = false;
        F = "";
        H = "SendTag";
        d = context;
    }

    private AdXConnect(Context context, int i1, String s1)
    {
        a = null;
        b = null;
        c = null;
        d = null;
        i = "";
        j = "";
        k = "";
        l = "";
        m = "";
        n = "";
        o = "";
        p = "";
        q = "";
        r = "";
        s = "";
        t = "";
        u = "";
        v = "";
        w = "3.2";
        x = "";
        y = "";
        z = "";
        A = "";
        B = "";
        C = "";
        D = false;
        F = "";
        H = "SendTag";
        d = context;
        String s2 = d.getSharedPreferences(G, 0).getString(H, null);
        if (s2 != null && s2.equals("stop"))
        {
            if (DEBUG)
            {
                Log.i("AdXAppTracker", "SendTag is set to stop ");
            }
            return;
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Install Connection ");
        }
        c(context);
        B = a(d);
        String s3 = d.getPackageManager().getApplicationInfo(d.getPackageName(), 128).metaData.getString("USEQASERVER");
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_1083;
        }
        if (!s3.equals("ON"))
        {
            break MISSING_BLOCK_LABEL_1083;
        }
        i = "testing.ad-x.co.uk";
_L1:
        x = (new StringBuilder(String.valueOf(x))).append("udid=").append(k).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("androidID=").append(q).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("macAddress=").append(v).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("type=").append(y).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("storeAppID=").append(A).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_name=").append(l).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_type=").append(m).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("os_version=").append(n).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("country_code=").append(o).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("language=").append(p).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("app_id=").append(r).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("clientid=").append(s).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("app_version=").append(t).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("tag_version=").append(w).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("fbattribution=").append(B).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("uagent=").append(z).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("update=").append(i1).toString();
        Exception exception;
        if (!s1.equals(""))
        {
            try
            {
                x = (new StringBuilder(String.valueOf(x))).append("&instData=").append(URLEncoder.encode(s1, "UTF-8")).toString();
                if (DEBUG)
                {
                    Log.i("AdXAppTracker", (new StringBuilder("Sending extra install data: ")).append(s1).toString());
                }
            }
            catch (Exception exception1)
            {
                if (DEBUG)
                {
                    Log.e("AdXAppTracker", (new StringBuilder("Error encoding extra install data: ")).append(exception1.toString()).toString());
                }
            }
        }
        (new Handler(Looper.getMainLooper())).post(new d(this));
        return;
        try
        {
            i = "apps.ad-x.co.uk";
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            if (DEBUG)
            {
                Log.e("AdXAppTracker", (new StringBuilder("Error setting host: ")).append(exception.toString()).toString());
            }
            i = "apps.ad-x.co.uk";
        }
          goto _L1
    }

    AdXConnect(Context context, int i1, String s1, byte byte0)
    {
        this(context, i1, s1);
    }

    private AdXConnect(Context context, String s1, String s2, String s3)
    {
        a = null;
        b = null;
        c = null;
        d = null;
        i = "";
        j = "";
        k = "";
        l = "";
        m = "";
        n = "";
        o = "";
        p = "";
        q = "";
        r = "";
        s = "";
        t = "";
        u = "";
        v = "";
        w = "3.2";
        x = "";
        y = "";
        z = "";
        A = "";
        B = "";
        C = "";
        D = false;
        F = "";
        H = "SendTag";
        d = context;
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Event Connection ");
        }
        c(context);
        B = a(context);
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Got Application Data ");
        }
        x = (new StringBuilder(String.valueOf(x))).append("udid=").append(k).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("androidID=").append(q).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("macAddress=").append(v).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("type=").append(y).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("storeAppID=").append(A).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_name=").append(l).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_type=").append(m).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("os_version=").append(n).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("country_code=").append(o).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("language=").append(p).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("app_id=").append(r).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("fbattribution=").append(B).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("event=").append(s1).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("data=").append(s2).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("uagent=").append(z).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("currency=").append(s3).toString();
        if (!F.equals(""))
        {
            x = (new StringBuilder(String.valueOf(x))).append("AdXClickURL=").append(F).toString();
        }
        (new Handler(Looper.getMainLooper())).post(new e(this));
    }

    private AdXConnect(Context context, String s1, String s2, String s3, String s4)
    {
        a = null;
        b = null;
        c = null;
        d = null;
        i = "";
        j = "";
        k = "";
        l = "";
        m = "";
        n = "";
        o = "";
        p = "";
        q = "";
        r = "";
        s = "";
        t = "";
        u = "";
        v = "";
        w = "3.2";
        x = "";
        y = "";
        z = "";
        A = "";
        B = "";
        C = "";
        D = false;
        F = "";
        H = "SendTag";
        d = context;
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Event Connection ");
        }
        c(context);
        B = a(context);
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Got Application Data ");
        }
        x = (new StringBuilder(String.valueOf(x))).append("udid=").append(k).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("androidID=").append(q).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("macAddress=").append(v).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("type=").append(y).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("storeAppID=").append(A).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_name=").append(l).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_type=").append(m).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("os_version=").append(n).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("country_code=").append(o).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("language=").append(p).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("app_id=").append(r).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("fbattribution=").append(B).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("event=").append(s1).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("data=").append(s2).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("uagent=").append(z).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("currency=").append(s3).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("custom_data=").append(s4).toString();
        (new Handler(Looper.getMainLooper())).post(new f(this));
    }

    private AdXConnect(Context context, String s1, String s2, String s3, String s4, String s5, String s6)
    {
        a = null;
        b = null;
        c = null;
        d = null;
        i = "";
        j = "";
        k = "";
        l = "";
        m = "";
        n = "";
        o = "";
        p = "";
        q = "";
        r = "";
        s = "";
        t = "";
        u = "";
        v = "";
        w = "3.2";
        x = "";
        y = "";
        z = "";
        A = "";
        B = "";
        C = "";
        D = false;
        F = "";
        H = "SendTag";
        d = context;
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "In Constructor ");
        }
        c(context);
        B = a(context);
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Got Application Data ");
        }
        x = (new StringBuilder(String.valueOf(x))).append("udid=").append(k).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("androidID=").append(q).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("macAddress=").append(v).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("type=").append(y).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("storeAppID=").append(A).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_name=").append(l).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_type=").append(m).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("os_version=").append(n).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("country_code=").append(o).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("language=").append(p).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("app_id=").append(r).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("fbattribution=").append(B).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("event=").append(s1).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("data=").append(s2).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("uagent=").append(z).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("currency=").append(s3).append("&").toString();
        if (s6 == null)
        {
            break MISSING_BLOCK_LABEL_898;
        }
        x = (new StringBuilder(String.valueOf(x))).append("custom_data=").append(URLEncoder.encode(s6, "UTF-8")).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("receiptdata=").append(URLEncoder.encode(s4, "UTF-8")).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("receiptsignature=").append(URLEncoder.encode(s5, "UTF-8")).toString();
_L2:
        (new Handler(Looper.getMainLooper())).post(new g(this));
        return;
        Exception exception;
        exception;
        Log.e("AdXAppTracker", (new StringBuilder("Exception URL encoding custom_data ")).append(s6).append(" ").append(exception.getMessage()).toString());
        if (true) goto _L2; else goto _L1
_L1:
    }

    static String a()
    {
        return E;
    }

    private static String a(Context context)
    {
        String as[];
        String s1;
        as = (new String[] {
            "aid"
        });
        s1 = "";
        Cursor cursor1 = context.getContentResolver().query(ATTRIBUTION_ID_CONTENT_URI, as, null, null, null);
        if (cursor1 == null) goto _L2; else goto _L1
_L1:
        if (cursor1.moveToFirst()) goto _L3; else goto _L2
_L3:
        String s4 = cursor1.getString(cursor1.getColumnIndex("aid"));
        cursor1.close();
        return s4;
        Exception exception3;
        exception3;
        Exception exception;
        s1 = s4;
        exception = exception3;
_L7:
        if (WARN)
        {
            Log.i("AdXAppTracker", exception.getMessage());
        }
        if (WARN)
        {
            Log.i("AdXAppTracker", "Retry");
        }
        Cursor cursor = context.getContentResolver().query(ATTRIBUTION_ID_CONTENT_URI, as, null, null, null);
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_227;
        }
        if (!cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_227;
        }
        String s3 = cursor.getString(cursor.getColumnIndex("aid"));
        String s2 = s3;
        cursor.close();
        return s2;
        Exception exception2;
        exception2;
_L5:
        if (WARN)
        {
            Log.i("AdXAppTracker", exception2.getMessage());
            return s2;
        }
        break; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        exception2 = exception1;
        s2 = s1;
        if (true) goto _L5; else goto _L4
_L4:
        break; /* Loop/switch isn't completed */
        exception;
        if (true) goto _L7; else goto _L6
_L2:
        s2 = null;
_L6:
        return s2;
        return null;
    }

    static String a(AdXConnect adxconnect)
    {
        return adxconnect.x;
    }

    private static String a(NodeList nodelist)
    {
        int i1 = 0;
        Element element = (Element)nodelist.item(0);
        if (element != null)
        {
            NodeList nodelist1 = element.getChildNodes();
            int j1 = nodelist1.getLength();
            String s1 = "";
            do
            {
                Node node;
                if (i1 >= j1)
                {
                    if (s1 != null && !s1.equals(""))
                    {
                        return s1.trim();
                    } else
                    {
                        return null;
                    }
                }
                node = nodelist1.item(i1);
                if (node != null)
                {
                    s1 = (new StringBuilder(String.valueOf(s1))).append(node.getNodeValue()).toString();
                }
                i1++;
            } while (true);
        } else
        {
            return null;
        }
    }

    static void a(AdXConnect adxconnect, Context context)
    {
        adxconnect.b(context);
    }

    static void a(AdXConnect adxconnect, h h1)
    {
        adxconnect.b = h1;
    }

    static void a(AdXConnect adxconnect, i i1)
    {
        adxconnect.a = i1;
    }

    static void a(AdXConnect adxconnect, j j1)
    {
        adxconnect.c = j1;
    }

    static void a(AdXConnect adxconnect, String s1)
    {
        adxconnect.x = s1;
    }

    static void a(AdXURLConnection adxurlconnection)
    {
        g = adxurlconnection;
    }

    private void a(String s1)
    {
        JSONObject jsonobject;
        String s2;
        JSONObject jsonobject1;
        String s3;
        try
        {
            O.put("e", s1);
        }
        catch (Exception exception)
        {
            Log.e("AdXAppTracker", (new StringBuilder("Exception ")).append(exception.getMessage()).toString());
        }
        jsonobject = O;
        if (!(jsonobject instanceof JSONObject))
        {
            s2 = jsonobject.toString();
        } else
        {
            s2 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        }
        Log.i("AdXTracker", s2);
        try
        {
            if (P.length() > 0)
            {
                O.put("p", P);
            }
        }
        catch (Exception exception1)
        {
            Log.e("AdXAppTracker", (new StringBuilder("Exception ")).append(exception1.getMessage()).toString());
        }
        jsonobject1 = O;
        if (!(jsonobject1 instanceof JSONObject))
        {
            s3 = jsonobject1.toString();
        } else
        {
            s3 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        }
        c(d);
        B = a(d);
        x = "";
        x = (new StringBuilder(String.valueOf(x))).append("udid=").append(k).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_name=").append(l).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("device_type=").append(m).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("os_version=").append(n).append("&").toString();
        x = (new StringBuilder(String.valueOf(x))).append("fbattribution=").append(B).append("&").toString();
        (new Handler(Looper.getMainLooper())).post(new c(this, s3));
    }

    public static void addProductToList(String s1)
    {
        P.put(s1);
    }

    public static void addProductToList(String s1, double d1, int i1)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("i", s1);
            jsonobject.put("pr", d1);
            jsonobject.put("q", i1);
            P.put(jsonobject);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    static AdXURLConnection b()
    {
        return g;
    }

    static String b(AdXConnect adxconnect)
    {
        return adxconnect.C;
    }

    private static Document b(String s1)
    {
        Document document1;
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(s1.getBytes("UTF-8"));
        document1 = documentbuilderfactory.newDocumentBuilder().parse(bytearrayinputstream);
        Document document = document1;
_L2:
        return document;
        Exception exception;
        exception;
        boolean flag = DEBUG;
        document = null;
        if (flag)
        {
            Log.e("AdXAppTracker", (new StringBuilder("buildDocument exception: ")).append(exception.toString()).toString());
            return null;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    private void b(Context context)
    {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0)
        {
            try
            {
                com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(context);
                C = info.getId();
                D = info.isLimitAdTrackingEnabled();
                return;
            }
            catch (IllegalStateException illegalstateexception)
            {
                illegalstateexception.printStackTrace();
                return;
            }
            catch (GooglePlayServicesRepairableException googleplayservicesrepairableexception)
            {
                googleplayservicesrepairableexception.printStackTrace();
                return;
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                return;
            }
            catch (GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception)
            {
                googleplayservicesnotavailableexception.printStackTrace();
            }
            return;
        } else
        {
            C = "";
            D = false;
            return;
        }
    }

    static boolean b(AdXConnect adxconnect, String s1)
    {
        Document document = b(s1);
        if (document != null)
        {
            String s2 = a(document.getElementsByTagName("Referral"));
            if (s2 != null)
            {
                if (DEBUG)
                {
                    Log.i("AdXAppTracker", (new StringBuilder("Successfully get returned referral ")).append(s2).toString());
                }
                android.content.SharedPreferences.Editor editor3 = adxconnect.d.getSharedPreferences(G, 0).edit();
                editor3.putString(I, s2);
                editor3.commit();
            }
            String s3 = a(document.getElementsByTagName("DLReferral"));
            if (s3 != null)
            {
                if (DEBUG)
                {
                    Log.i("AdXAppTracker", (new StringBuilder("Successfully get returned DLreferral ")).append(s3).toString());
                }
                android.content.SharedPreferences.Editor editor2 = adxconnect.d.getSharedPreferences(G, 0).edit();
                editor2.putString(J, s3);
                editor2.commit();
            }
            String s4 = a(document.getElementsByTagName("Seencount"));
            if (s4 != null)
            {
                android.content.SharedPreferences.Editor editor1 = adxconnect.d.getSharedPreferences(G, 0).edit();
                editor1.putString(K, s4);
                editor1.commit();
            }
            String s5 = a(document.getElementsByTagName("Success"));
            if (s5 != null && s5.equals("true"))
            {
                if (DEBUG)
                {
                    Log.i("AdXAppTracker", "Successfully connected to AdX site.");
                }
                return true;
            }
            if (s5 != null && s5.equals("stop"))
            {
                if (DEBUG)
                {
                    Log.i("AdXAppTracker", "Successfully connected to AdX site - stopping tags from now on.");
                }
                android.content.SharedPreferences.Editor editor = adxconnect.d.getSharedPreferences(G, 0).edit();
                editor.putString(adxconnect.H, "stop");
                editor.commit();
                return true;
            }
            if (DEBUG)
            {
                Log.w("AdXAppTracker", "Event Connection before device attribution.");
            }
        }
        return false;
    }

    static AdXConnect c()
    {
        return e;
    }

    private void c(Context context)
    {
        PackageManager packagemanager = context.getPackageManager();
        ApplicationInfo applicationinfo = packagemanager.getApplicationInfo(d.getPackageName(), 128);
        if (applicationinfo == null) goto _L2; else goto _L1
_L1:
        if (applicationinfo.metaData == null) goto _L2; else goto _L3
_L3:
        String s1 = applicationinfo.metaData.getString("APP_NAME");
        if (s1 == null) goto _L5; else goto _L4
_L4:
        if (s1.equals("")) goto _L5; else goto _L6
_L6:
        String s2;
        r = s1.trim();
        s2 = applicationinfo.metaData.getString("ADX_CLIENT_ID");
        if (s2 == null) goto _L8; else goto _L7
_L7:
        if (s2.equals("")) goto _L8; else goto _L9
_L9:
        String s3;
        s = s2.trim();
        s3 = applicationinfo.metaData.getString("SENDFINGERPRINT");
        if (s3 == null) goto _L11; else goto _L10
_L10:
        if (!s3.equals("ON")) goto _L11; else goto _L12
_L12:
        z = (new WebView(d)).getSettings().getUserAgentString();
_L38:
        String s4 = applicationinfo.metaData.getString("OTHERSTORE");
        if (s4 == null)
        {
            break MISSING_BLOCK_LABEL_192;
        }
        if (!s4.equals(""))
        {
            y = s4;
        }
        String s5 = applicationinfo.metaData.getString("STOREAPPID");
        if (s5 == null)
        {
            break MISSING_BLOCK_LABEL_226;
        }
        if (!s5.equals(""))
        {
            A = s5;
        }
        SharedPreferences sharedpreferences;
        String s6;
        t = packagemanager.getPackageInfo(d.getPackageName(), 0).versionName;
        m = "android";
        l = Build.MODEL;
        n = android.os.Build.VERSION.RELEASE;
        o = Locale.getDefault().getCountry();
        p = Locale.getDefault().getLanguage();
        u = w;
        sharedpreferences = d.getSharedPreferences(G, 0);
        s6 = applicationinfo.metaData.getString("DEVICE_ID");
        if (s6 == null) goto _L14; else goto _L13
_L13:
        if (s6.equals("")) goto _L14; else goto _L15
_L15:
        k = s6;
_L31:
        String s7 = sharedpreferences.getString(N, null);
        if (s7 == null)
        {
            break MISSING_BLOCK_LABEL_374;
        }
        if (!s7.equals(""))
        {
            E = s7;
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Metadata successfully loaded");
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", (new StringBuilder("APP_ID = [")).append(r).append("]").toString());
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", (new StringBuilder("deviceName: [")).append(l).append("]").toString());
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", (new StringBuilder("deviceType: [")).append(m).append("]").toString());
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", (new StringBuilder("libraryVersion: [")).append(u).append("]").toString());
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", (new StringBuilder("deviceOSVersion: [")).append(n).append("]").toString());
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", (new StringBuilder("COUNTRY_CODE: [")).append(o).append("]").toString());
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", (new StringBuilder("LANGUAGE_CODE: [")).append(p).append("]").toString());
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", (new StringBuilder("referralURL: [")).append(E).append("]").toString());
            return;
        }
          goto _L16
_L5:
        try
        {
            if (WARN)
            {
                Log.e("AdXAppTracker", "APP_NAME can't be empty.");
                return;
            }
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            if (WARN)
            {
                Log.e("AdXAppTracker", "Check your AndroidManifest.xml file. For more details view integration document.");
                return;
            }
        }
          goto _L16
_L8:
        if (WARN)
        {
            Log.e("AdXAppTracker", "ADX_CLIENT_ID can't be empty.");
            return;
        }
          goto _L16
_L11:
        z = "";
        break MISSING_BLOCK_LABEL_1341;
_L14:
        String s8 = applicationinfo.metaData.getString("ANDROIDID");
        if (s8 == null) goto _L18; else goto _L17
_L17:
        if (!s8.equals("ON")) goto _L18; else goto _L19
_L19:
        q = android.provider.Settings.Secure.getString(d.getContentResolver(), "android_id");
_L32:
        String s9 = applicationinfo.metaData.getString("MACADDRESS");
        if (s9 == null) goto _L21; else goto _L20
_L20:
        if (!s9.equals("ON")) goto _L21; else goto _L22
_L22:
        v = ((WifiManager)d.getSystemService("wifi")).getConnectionInfo().getMacAddress();
_L33:
        String s10 = applicationinfo.metaData.getString("IMEI");
        if (s10 == null) goto _L24; else goto _L23
_L23:
        if (!s10.equals("ON")) goto _L24; else goto _L25
_L25:
        TelephonyManager telephonymanager = (TelephonyManager)d.getSystemService("phone");
        if (telephonymanager == null)
        {
            break MISSING_BLOCK_LABEL_919;
        }
        k = telephonymanager.getDeviceId();
_L34:
        String s11 = applicationinfo.metaData.getString("SERIALID");
        if (s11 == null) goto _L27; else goto _L26
_L26:
        if (!s11.equals("ON")) goto _L27; else goto _L28
_L28:
        int j1 = android.os.Build.VERSION.SDK_INT;
        if (k != null || j1 < 9) goto _L30; else goto _L29
_L29:
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Using Serial ID.");
        }
        DeviceSerial.getInstance();
        k = DeviceSerial.getDeviceSerial();
_L27:
        StringBuffer stringbuffer;
        String s12;
        if (k != null && k.length() != 0 && !k.equals("000000000000000") && !k.equals("0"))
        {
            break MISSING_BLOCK_LABEL_1307;
        }
        stringbuffer = new StringBuffer();
        stringbuffer.append(q);
        if (stringbuffer.length() == 0)
        {
            stringbuffer.append("ADXID");
        }
        s12 = sharedpreferences.getString("emulatorDeviceId", null);
        if (s12 == null)
        {
            break MISSING_BLOCK_LABEL_1341;
        }
        if (s12.equals(""))
        {
            break MISSING_BLOCK_LABEL_1341;
        }
        k = s12;
          goto _L31
        Exception exception;
        exception;
        if (DEBUG)
        {
            Log.e("AdXAppTracker", (new StringBuilder("Error getting a deviceID. e: ")).append(exception.toString()).toString());
        }
        k = null;
          goto _L31
_L18:
        q = "";
          goto _L32
_L21:
        v = "";
          goto _L33
_L24:
        k = null;
          goto _L34
_L30:
        if (k != null || !DEBUG) goto _L27; else goto _L35
_L35:
        Log.e("AdXAppTracker", (new StringBuilder("Serial ID not available on SDK ")).append(j1).toString());
          goto _L27
_L36:
        i1++;
        int i1;
        android.content.SharedPreferences.Editor editor;
        for (; i1 < 16; i1 = 0)
        {
            break MISSING_BLOCK_LABEL_1278;
        }

        k = stringbuffer.toString().toLowerCase(Locale.ENGLISH);
        editor = sharedpreferences.edit();
        editor.putString("emulatorDeviceId", k);
        editor.commit();
          goto _L31
        stringbuffer.append("1234567890abcdefghijklmnopqrstuvw".charAt((int)(100D * Math.random()) % 30));
          goto _L36
        k = k.toLowerCase(Locale.ENGLISH);
          goto _L31
_L2:
        if (WARN)
        {
            Log.e("AdXAppTracker", "Check your AndroidManifest.xml file. For more details view integration document.");
        }
_L16:
        return;
        if (true) goto _L38; else goto _L37
_L37:
    }

    static boolean c(AdXConnect adxconnect)
    {
        return adxconnect.D;
    }

    private void d()
    {
        ApplicationInfo applicationinfo;
        String s1;
        String s2;
        try
        {
            applicationinfo = d.getPackageManager().getApplicationInfo(d.getPackageName(), 128);
            s1 = applicationinfo.metaData.getString("USEQASERVER");
        }
        catch (Exception exception)
        {
            if (DEBUG)
            {
                Log.e("AdXAppTracker", (new StringBuilder("Error setting host: ")).append(exception.toString()).toString());
            }
            i = "apps.ad-x.co.uk";
            return;
        }
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        if (!s1.equals("ON"))
        {
            break MISSING_BLOCK_LABEL_124;
        }
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Host Set to QA SERVER");
        }
        i = "testing.ad-x.co.uk";
_L1:
        s2 = applicationinfo.metaData.getString("USEHTTPS");
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_176;
        }
        if (s2.equals("ON"))
        {
            if (DEBUG)
            {
                Log.i("AdXAppTracker", "Set to HTTPS");
            }
            j = "https://";
            return;
        }
        break MISSING_BLOCK_LABEL_176;
        i = "apps.ad-x.co.uk";
          goto _L1
        j = "http://";
        return;
    }

    static void d(AdXConnect adxconnect)
    {
        adxconnect.d();
    }

    public static void doBroadcastConnectInstance(Context context)
    {
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Broadcast Receiver - sending to AdX.");
        }
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences(G, 0).edit();
        editor.putString(L, "done");
        editor.commit();
    }

    static String e(AdXConnect adxconnect)
    {
        return adxconnect.j;
    }

    static String f(AdXConnect adxconnect)
    {
        return adxconnect.i;
    }

    static String g(AdXConnect adxconnect)
    {
        return adxconnect.s;
    }

    public static void getAdXConnectEventInstance(Context context, String s1, String s2, String s3)
    {
        if (g == null)
        {
            g = new AdXURLConnection();
        }
        if (f != null)
        {
            f = null;
        }
        f = new AdXConnect(context, s1, s2, s3);
    }

    public static void getAdXConnectEventInstance(Context context, String s1, String s2, String s3, String s4)
    {
        if (g == null)
        {
            g = new AdXURLConnection();
        }
        if (f != null)
        {
            f = null;
        }
        f = new AdXConnect(context, s1, s2, s3, s4);
    }

    public static void getAdXConnectEventInstanceWithReceipt(Context context, String s1, String s2, String s3, String s4, String s5)
    {
        if (g == null)
        {
            g = new AdXURLConnection();
        }
        if (f != null)
        {
            f = null;
        }
        f = new AdXConnect(context, s1, s2, s3, s4, s5, null);
    }

    public static void getAdXConnectEventInstanceWithReceipt(Context context, String s1, String s2, String s3, String s4, String s5, String s6)
    {
        if (g == null)
        {
            g = new AdXURLConnection();
        }
        if (f != null)
        {
            f = null;
        }
        f = new AdXConnect(context, s1, s2, s3, s4, s5, s6);
    }

    public static AdXConnect getAdXConnectInstance(Context context, boolean flag, int i1)
    {
        return getAdXConnectInstance(context, flag, i1, "");
    }

    public static AdXConnect getAdXConnectInstance(Context context, boolean flag, int i1, String s1)
    {
        LOGLEVEL = i1;
        boolean flag1;
        boolean flag2;
        SharedPreferences sharedpreferences;
        android.content.SharedPreferences.Editor editor;
        int j1;
        int k1;
        if (i1 > 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        WARN = flag1;
        if (LOGLEVEL > 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        DEBUG = flag2;
        if (i1 == 2)
        {
            sendDelay = 3;
        }
        sharedpreferences = context.getSharedPreferences(G, 0);
        editor = sharedpreferences.edit();
        j1 = sharedpreferences.getInt(M, -1);
        Exception exception;
        Exception exception1;
        a a1;
        Long long1;
        if (j1 < 0)
        {
            String s2;
            ApplicationInfo applicationinfo;
            String s3;
            String s4;
            String s5;
            String s6;
            String s7;
            if (flag)
            {
                editor.putInt(M, 1);
                k1 = 1;
            } else
            {
                editor.putInt(M, 0);
                k1 = 0;
            }
            if (DEBUG)
            {
                Log.i("AdXAppTracker", (new StringBuilder("Update flag set to ")).append(k1).toString());
            }
        } else
        {
            k1 = j1;
        }
        applicationinfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        s3 = applicationinfo.metaData.getString("OTHERSTORE");
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_209;
        }
        if (!s3.equals(""))
        {
            editor.putString(L, "done");
            editor.commit();
            if (DEBUG)
            {
                Log.i("AdXAppTracker", "Otherstore set in manifest.");
            }
        }
        s4 = applicationinfo.metaData.getString("NETWORK");
        if (s4 == null)
        {
            break MISSING_BLOCK_LABEL_326;
        }
        if (!s4.equals(""))
        {
            s5 = s4.trim();
            s6 = applicationinfo.metaData.getString("REFERENCE").trim();
            s7 = (new StringBuilder("referrer=utm_source%3D")).append(s5).append("%26utm_medium%3Dcpc%26utm_campaign%3D").append(s6).toString();
            editor.putString(N, s7);
            editor.putString(L, "done");
            editor.commit();
        }
_L2:
        s2 = sharedpreferences.getString(L, null);
        if (k1 == 1 || s2 != null && s2.equals("done"))
        {
            if (DEBUG)
            {
                Log.i("AdXAppTracker", "Sending to AdX");
            }
            if (g == null)
            {
                g = new AdXURLConnection();
            }
            if (e == null)
            {
                e = new AdXConnect(context, k1, s1);
            }
            return e;
        }
        break; /* Loop/switch isn't completed */
        exception;
        if (WARN)
        {
            Log.i("AdXAppTracker", (new StringBuilder("Exception ")).append(exception.getMessage()).toString());
        }
        if (true) goto _L2; else goto _L1
_L1:
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Re Referral yet - deferring..");
        }
        editor.putString(L, "done");
        editor.commit();
label0:
        {
            if (Looper.myLooper() != Looper.getMainLooper() || Looper.myLooper() == null)
            {
                a1 = new a(context, k1, s1);
                long1 = Long.valueOf(sendDelay);
                h.schedule(a1, long1.longValue(), TimeUnit.SECONDS);
                break label0;
            }
            try
            {
                (new Handler()).postDelayed(new b(context, k1, s1), 1000 * sendDelay);
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception1)
            {
                if (WARN)
                {
                    Log.i("AdXAppTracker", (new StringBuilder("Exception in delayed send to Ad-X: ")).append(exception1.getMessage()).toString());
                }
            }
        }
        return null;
    }

    public static String getAdXDLReferral(Context context, int i1)
    {
        SharedPreferences sharedpreferences;
        String s1;
        int j1;
        sharedpreferences = context.getSharedPreferences(G, 0);
        s1 = sharedpreferences.getString(J, null);
        j1 = 0;
_L5:
        if (j1 < i1 * 5 && (s1 == null || s1.equals(""))) goto _L2; else goto _L1
_L1:
        return s1;
_L2:
        j1++;
        s1 = sharedpreferences.getString(J, null);
        if (s1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (s1.length() > 0) goto _L1; else goto _L3
_L3:
        Thread.sleep(200L);
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static String getAdXDeviceID(Context context)
    {
        if (e != null)
        {
            AdXConnect adxconnect = e;
            adxconnect.c(context);
            return adxconnect.k;
        } else
        {
            return null;
        }
    }

    public static String getAdXReferral(Context context, int i1)
    {
        SharedPreferences sharedpreferences;
        String s1;
        int j1;
        sharedpreferences = context.getSharedPreferences(G, 0);
        s1 = sharedpreferences.getString(I, null);
        j1 = 0;
_L5:
        if (j1 < i1 * 5 && (s1 == null || s1.equals(""))) goto _L2; else goto _L1
_L1:
        return s1;
_L2:
        j1++;
        s1 = sharedpreferences.getString(I, null);
        if (s1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (s1.length() > 0) goto _L1; else goto _L3
_L3:
        Thread.sleep(200L);
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static boolean getAdXSyncronousEventInstance(Context context, String s1, String s2, String s3, String s4)
    {
        if (g == null)
        {
            g = new AdXURLConnection();
        }
        if (f == null)
        {
            f = new AdXConnect(context);
        }
        AdXConnect adxconnect = f;
        adxconnect.d = context;
        adxconnect.x = "";
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Event Connection ");
        }
        adxconnect.c(context);
        adxconnect.B = a(context);
        adxconnect.b(context);
        adxconnect.d();
        if (DEBUG)
        {
            Log.i("AdXAppTracker", "Got Application Data ");
        }
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("udid=").append(adxconnect.k).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("idfa=").append(adxconnect.C).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("isLAT=").append(adxconnect.D).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("androidID=").append(adxconnect.q).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("macAddress=").append(adxconnect.v).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("type=").append(adxconnect.y).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("storeAppID=").append(adxconnect.A).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("device_name=").append(adxconnect.l).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("device_type=").append(adxconnect.m).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("os_version=").append(adxconnect.n).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("country_code=").append(adxconnect.o).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("language=").append(adxconnect.p).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("app_id=").append(adxconnect.r).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("fbattribution=").append(adxconnect.B).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("event=").append(s1).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("data=").append(s2).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("uagent=").append(adxconnect.z).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("currency=").append(s3).append("&").toString();
        adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("custom_data=").append(s4).toString();
        if (!adxconnect.F.equals(""))
        {
            adxconnect.x = (new StringBuilder(String.valueOf(adxconnect.x))).append("AdXClickURL=").append(adxconnect.F).toString();
        }
        String s5 = adxconnect.x;
        return AdXURLConnection.connectToURL((new StringBuilder(String.valueOf(adxconnect.j))).append(adxconnect.i).append("/API/androidevent.php?oursecret=").append(adxconnect.s).append("&").toString(), s5) != null && AdXURLConnection.a == 200;
    }

    public static String getSeencount(Context context, int i1)
    {
        SharedPreferences sharedpreferences;
        String s1;
        int j1;
        sharedpreferences = context.getSharedPreferences(G, 0);
        s1 = sharedpreferences.getString(K, null);
        j1 = 0;
_L5:
        if (j1 < i1 * 5 && (s1 == null || s1.equals(""))) goto _L2; else goto _L1
_L1:
        return s1;
_L2:
        j1++;
        s1 = sharedpreferences.getString(K, null);
        if (s1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (s1.length() > 0) goto _L1; else goto _L3
_L3:
        Thread.sleep(200L);
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        if (true) goto _L5; else goto _L4
_L4:
    }

    static String h(AdXConnect adxconnect)
    {
        return adxconnect.q;
    }

    static String i(AdXConnect adxconnect)
    {
        return adxconnect.r;
    }

    static void j(AdXConnect adxconnect)
    {
        e = adxconnect;
    }

    static Context k(AdXConnect adxconnect)
    {
        return adxconnect.d;
    }

    static j l(AdXConnect adxconnect)
    {
        return adxconnect.c;
    }

    static i m(AdXConnect adxconnect)
    {
        return adxconnect.a;
    }

    static h n(AdXConnect adxconnect)
    {
        return adxconnect.b;
    }

    public static void sendExtendedEvent(int i1)
    {
        String as[] = {
            "vh", "vs", "vp", "vl", "vb", "vc", "lc"
        };
        f.a(as[i1]);
    }

    public static void sendExtendedEventOfName(String s1)
    {
        f.a(s1);
    }

    public static void setAttribution(Context context, String s1, String s2)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences(G, 0).edit();
        String s3 = (new StringBuilder("referrer=utm_source%3D")).append(s1).append("%26utm_medium%3Dcpc%26utm_campaign%3D").append(s2).toString();
        editor.putString(N, s3);
        editor.putString(L, "done");
        editor.commit();
    }

    public static void setEventParameter(int i1, Object obj)
    {
        String as[] = {
            "a", "ci", "p", "kw", "p", "pr", "q", "din", "dout", "nc", 
            "id", "sid", "did", "l"
        };
        try
        {
            O.put(as[i1], obj);
            return;
        }
        catch (Exception exception)
        {
            Log.e("AdXAppTracker", (new StringBuilder("Exception ")).append(exception.getMessage()).toString());
        }
    }

    public static void setEventParameterOfName(String s1, Object obj)
    {
        try
        {
            O.put(s1, obj);
            return;
        }
        catch (Exception exception)
        {
            Log.e("AdXAppTracker", (new StringBuilder("Exception ")).append(exception.getMessage()).toString());
        }
    }

    public static void startNewExtendedEvent(Context context)
    {
        if (g == null)
        {
            g = new AdXURLConnection();
        }
        if (f != null)
        {
            f = null;
        }
        f = new AdXConnect(context);
        O = new JSONObject();
        P = new JSONArray();
    }

    public final void finalize()
    {
    }

}
