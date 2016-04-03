// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.AndroidWidgetVDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package com.flipkart.android.widget:
//            c, d, b, a

public class FlipkartAppWidget extends AppWidgetProvider
{

    public static String ACTION_WIDGET_BANNER = "ActionReceiverBanner";
    public static String ACTION_WIDGET_BAR_CODE = "ActionReceiverBarCode";
    public static String ACTION_WIDGET_LEFT = "ActionReceiverLeft";
    public static String ACTION_WIDGET_RIGHT = "ActionReceiverRight";
    public static String ACTION_WIDGET_SEARCH = "ActionReceiverSearch";
    public static final String APP_WIDGET = "APP_WIDGET";
    public static final String APP_WIDGET_BANNER_INDEX = "APP_WIDGET_BANNER_INDEX";
    public static final String BANNER_CLICK_ON_APP_WIDGET = "BANNER_CLICK_ON_APP_WIDGET";
    public static final String SEARCH_FROM_APP_WIDGET = "SEARCH_FROM_APP_WIDGET";
    RemoteViews a;
    private int b;
    private AndroidWidgetVDataHandler c;
    public Context context;
    public JSONObject jsonObject;

    public FlipkartAppWidget()
    {
        b = 240;
        jsonObject = null;
    }

    static void a(FlipkartAppWidget flipkartappwidget, RemoteViews remoteviews)
    {
        Intent intent = new Intent(flipkartappwidget.context, com/flipkart/android/activity/HomeFragmentHolderActivity);
        intent.putExtra("HOME_ACTIVITY_EXTRAS_FROM_SCREEN", "APP_WIDGET");
        intent.setAction(ACTION_WIDGET_SEARCH);
        intent.putExtra("source", "SEARCH_FROM_APP_WIDGET");
        remoteviews.setOnClickPendingIntent(0x7f0a0082, PendingIntent.getActivity(flipkartappwidget.context, 0, intent, 0));
        Intent intent1 = new Intent(flipkartappwidget.context, com/flipkart/android/widget/FlipkartAppWidget);
        intent1.setAction(ACTION_WIDGET_LEFT);
        remoteviews.setOnClickPendingIntent(0x7f0a0084, PendingIntent.getBroadcast(flipkartappwidget.context, 0, intent1, 0));
        Intent intent2 = new Intent(flipkartappwidget.context, com/flipkart/android/widget/FlipkartAppWidget);
        intent2.setAction(ACTION_WIDGET_RIGHT);
        remoteviews.setOnClickPendingIntent(0x7f0a0085, PendingIntent.getBroadcast(flipkartappwidget.context, 0, intent2, 0));
        Intent intent3 = new Intent(flipkartappwidget.context, com/flipkart/android/widget/FlipkartAppWidget);
        intent3.setAction(ACTION_WIDGET_BANNER);
        remoteviews.setOnClickPendingIntent(0x7f0a0083, PendingIntent.getBroadcast(flipkartappwidget.context, 0, intent3, 0));
    }

    public static void pushWidgetUpdate(Context context1, RemoteViews remoteviews)
    {
        ComponentName componentname;
        AppWidgetManager appwidgetmanager;
        componentname = new ComponentName(context1, com/flipkart/android/widget/FlipkartAppWidget);
        appwidgetmanager = AppWidgetManager.getInstance(context1);
        if (remoteviews == null)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        appwidgetmanager.updateAppWidget(componentname, remoteviews);
        return;
        NullPointerException nullpointerexception;
        nullpointerexception;
    }

    public void createBannerIntent(Context context1, String s)
    {
        JSONObject jsonobject2;
        String s2;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_249;
        }
        Intent intent = new Intent(context1, com/flipkart/android/activity/HomeFragmentHolderActivity);
        JSONObject jsonobject;
        JSONArray jsonarray;
        int i;
        JSONObject jsonobject1;
        try
        {
            JVM INSTR new #121 <Class JSONObject>;
            jsonObject = JSONObjectInstrumentation.init(s);
            jsonobject = jsonObject.getJSONObject("response");
        }
        catch (Exception exception)
        {
            FkLogger.debug("widget", (new StringBuilder("errrrrr response =")).append(s).append("  ..").append(exception).toString());
            return;
        }
        if (jsonobject == null)
        {
            return;
        }
        jsonarray = jsonobject.getJSONArray("items");
        if (jsonarray == null)
        {
            break MISSING_BLOCK_LABEL_249;
        }
        i = FlipkartPreferenceManager.instance().getAppWidgetCurrentImagePosition();
        jsonobject1 = jsonarray.getJSONObject(i);
        FkLogger.debug("widget", (new StringBuilder("banner click pref man. ")).append(i).toString());
        if (jsonobject1 == null)
        {
            break MISSING_BLOCK_LABEL_249;
        }
        jsonobject2 = jsonobject1.optJSONObject("action");
        if (jsonobject2 == null)
        {
            break MISSING_BLOCK_LABEL_249;
        }
        intent.putExtra("HOME_ACTIVITY_EXTRAS_FROM_SCREEN", "APP_WIDGET");
        intent.putExtra("APP_WIDGET_BANNER_INDEX", i);
        intent.putExtra("source", "BANNER_CLICK_ON_APP_WIDGET");
        intent.setAction(ACTION_WIDGET_SEARCH);
        intent.setFlags(0x10020000);
        if (jsonobject2 instanceof JSONObject) goto _L2; else goto _L1
_L1:
        s2 = jsonobject2.toString();
_L3:
        intent.putExtra("action", s2);
        context1.startActivity(intent);
        FkLogger.debug("widget", "starting new task");
        return;
_L2:
        String s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject2);
        s2 = s1;
          goto _L3
    }

    public void createImageUrlList()
    {
        b = ScreenMathUtils.getScreenDpi(context);
        ArrayList arraylist = getBannerImageUrlList();
        FkLogger.debug("widget", "setting 1st downloaded banner by default");
        ImageLoader imageloader = FlipkartApplication.getImageLoader();
        Context context1;
        for (context1 = context; arraylist == null || imageloader == null || arraylist.size() <= 0 || arraylist.get(0) == null;)
        {
            return;
        }

        imageloader.get((String)arraylist.get(0), new c(this, context1));
    }

    public ArrayList getBannerImageUrlList()
    {
        ArrayList arraylist;
        String s;
        arraylist = new ArrayList();
        s = FlipkartPreferenceManager.instance().getAppWidgetResponse();
        FkLogger.debug("widget", (new StringBuilder()).append(s).append(",l,l").toString());
        if (!s.equalsIgnoreCase("") && s != null) goto _L2; else goto _L1
_L1:
        arraylist = null;
_L6:
        return arraylist;
_L2:
        JSONArray jsonarray;
        int i;
        JSONObject jsonobject;
        String s1;
        try
        {
            JVM INSTR new #121 <Class JSONObject>;
            jsonObject = JSONObjectInstrumentation.init(s);
            jsonarray = jsonObject.getJSONObject("response").getJSONArray("items");
        }
        catch (Exception exception)
        {
            FkLogger.debug("widget", (new StringBuilder("errrrrr ")).append(exception).toString());
            return null;
        }
        i = 0;
        if (i >= jsonarray.length())
        {
            continue; /* Loop/switch isn't completed */
        }
        jsonobject = jsonarray.optJSONObject(i).optJSONObject("value").optJSONObject("image");
        s1 = jsonobject.optString((new StringBuilder()).append(b).toString());
        if (b > 720 && StringUtils.isNullOrEmpty(s1))
        {
            s1 = jsonobject.optString("720");
        }
        FkLogger.debug("widget", (new StringBuilder("image320=")).append(s1).toString());
        arraylist.add(s1);
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        break MISSING_BLOCK_LABEL_206;
_L4:
        break MISSING_BLOCK_LABEL_90;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public String getImageUrlAtPosition(int i)
    {
        String s;
        s = FlipkartPreferenceManager.instance().getAppWidgetResponse();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_115;
        }
        String s1;
        String s2;
        JVM INSTR new #121 <Class JSONObject>;
        jsonObject = JSONObjectInstrumentation.init(s);
        JSONObject jsonobject = jsonObject.optJSONObject("response").optJSONArray("items").optJSONObject(i).optJSONObject("value").optJSONObject("image");
        s1 = jsonobject.optString((new StringBuilder()).append(b).toString());
        if (b <= 720 || !StringUtils.isNullOrEmpty(s1))
        {
            break MISSING_BLOCK_LABEL_111;
        }
        s2 = jsonobject.optString("720");
        s1 = s2;
        return s1;
        Exception exception;
        exception;
        return null;
    }

    public int getUrlListLength()
    {
        String s;
        s = FlipkartPreferenceManager.instance().getAppWidgetResponse();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        int i;
        JVM INSTR new #121 <Class JSONObject>;
        jsonObject = JSONObjectInstrumentation.init(s);
        i = jsonObject.getJSONObject("response").getJSONArray("items").length();
        return i;
        Exception exception;
        exception;
        return 0;
    }

    public void onEnabled(Context context1)
    {
        FkLogger.debug("widget", "onenabled called setting update count to 0");
        super.onEnabled(context1);
    }

    public void onReceive(Context context1, Intent intent)
    {
        if (FlipkartPreferenceManager.instance().getAppWidgetResponse().equalsIgnoreCase(""))
        {
            super.onReceive(context1, intent);
            FkLogger.debug("widget", "preference  is null");
        }
        if (intent.getAction().equals(ACTION_WIDGET_LEFT))
        {
            FkLogger.debug("widget", "left clicked");
            showPreviousImage(context1);
            return;
        }
        if (intent.getAction().equals(ACTION_WIDGET_RIGHT))
        {
            FkLogger.debug("widget", "right clicked");
            showNextImage(context1);
            return;
        }
        if (intent.getAction().equals(ACTION_WIDGET_BANNER))
        {
            FkLogger.debug("widget", "banner clicked");
            openHomePage(context1);
            return;
        } else
        {
            FkLogger.debug("widget", "unknown clicked or system update");
            super.onReceive(context1, intent);
            return;
        }
    }

    public void onUpdate(Context context1, AppWidgetManager appwidgetmanager, int ai[])
    {
        int i = 1 + FlipkartPreferenceManager.instance().getAppWidgetUpadateCount();
        FlipkartPreferenceManager.instance().saveAppWidgetUpadateCount(i);
        context = context1;
        c = new d(this);
        c.getJson();
        a = new RemoteViews(context1.getPackageName(), 0x7f030023);
        RemoteViews remoteviews = a;
        Intent intent = new Intent(context, com/flipkart/android/activity/HomeFragmentHolderActivity);
        intent.putExtra("HOME_ACTIVITY_EXTRAS_FROM_SCREEN", "APP_WIDGET");
        intent.setAction(ACTION_WIDGET_SEARCH);
        intent.putExtra("source", "SEARCH_FROM_APP_WIDGET");
        remoteviews.setOnClickPendingIntent(0x7f0a0082, PendingIntent.getActivity(context, 0, intent, 0));
        pushWidgetUpdate(context1, a);
        updatewithCachedBanners();
    }

    public void openHomePage(Context context1)
    {
        String s = FlipkartPreferenceManager.instance().getAppWidgetResponse();
        if (s != null && !s.equalsIgnoreCase(""))
        {
            createBannerIntent(context1, s);
        }
    }

    public void showNextImage(Context context1)
    {
        String s = FlipkartPreferenceManager.instance().getAppWidgetResponse();
        if (s == null || s.equalsIgnoreCase(""))
        {
            onUpdate(context1, null, null);
        } else
        {
            int i = FlipkartPreferenceManager.instance().getAppWidgetCurrentImagePosition();
            int j = getUrlListLength();
            if (j > 0)
            {
                int k = i + 1;
                if (k >= j)
                {
                    k = 0;
                }
                String s1 = getImageUrlAtPosition(k);
                if (s1 != null)
                {
                    a = new RemoteViews(context1.getPackageName(), 0x7f030022);
                    FlipkartApplication.getImageLoader().get(s1, new b(this, k));
                    pushWidgetUpdate(context1, a);
                    return;
                }
            }
        }
    }

    public void showPreviousImage(Context context1)
    {
        String s = FlipkartPreferenceManager.instance().getAppWidgetResponse();
        if (s == null || s.equalsIgnoreCase(""))
        {
            onUpdate(context1, null, null);
        } else
        {
            int i = FlipkartPreferenceManager.instance().getAppWidgetCurrentImagePosition();
            FkLogger.debug("widget", (new StringBuilder("pos recvd =")).append(i).toString());
            int j = getUrlListLength();
            if (j > 0)
            {
                int k = i - 1;
                if (k < 0)
                {
                    k = j - 1;
                }
                String s1 = getImageUrlAtPosition(k);
                if (s1 != null)
                {
                    a = new RemoteViews(context1.getPackageName(), 0x7f030022);
                    FlipkartApplication.getImageLoader().get(s1, new a(this, k));
                    pushWidgetUpdate(context1, a);
                    return;
                }
            }
        }
    }

    public void updatewithCachedBanners()
    {
        createImageUrlList();
    }

}
