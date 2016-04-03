// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.init;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.flipkart.android.config.ConfigHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.log.LoggerStack;
import com.flipkart.android.response.appconfig.ConfigResponseData;
import com.flipkart.android.response.appconfig.ImageProfileMatrix;
import com.flipkart.android.response.component.data.RenderableDeserializer;
import com.flipkart.android.response.component.data.RenderableSerializer;
import com.flipkart.android.response.component.data.customvalues.Renderable;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.DiskImageCache;
import com.flipkart.android.utils.MiscUtils;
import com.flipkart.android.utils.NetworkMonitor;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.TwoStageImageCache;
import com.flipkart.android.utils.drawable.FilterDrawableMap;
import com.flipkart.logging.FkLogger;
import com.google.mygson.Gson;
import com.google.mygson.GsonBuilder;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.init:
//            AppStartup, BuildSetup

public class FlipkartApplication extends Application
{

    public static final String APP_NAME = "ASIMO";
    private static Context a;
    private static RequestQueue b;
    private static ImageLoader c;
    private static Gson d;
    private static FilterDrawableMap e;
    private static ImageProfileMatrix f;
    private static Activity g = null;
    private static boolean h = true;
    private static ArrayList i = null;

    public FlipkartApplication()
    {
    }

    public static Context getAppContext()
    {
        return a;
    }

    public static Activity getCurrentActivity()
    {
        return g;
    }

    public static FilterDrawableMap getFilterDrawableMap()
    {
        return e;
    }

    public static Gson getGsonInstance()
    {
        return d;
    }

    public static ImageLoader getImageLoader()
    {
        return c;
    }

    public static ImageProfileMatrix getImageProfileMatrix()
    {
        return f;
    }

    public static ArrayList getJsonDataHandlers()
    {
        return i;
    }

    public static LoggerStack getLoggerStack()
    {
        return null;
    }

    public static RequestQueue getRequestQueue()
    {
        return b;
    }

    public static boolean isShowBallonAnimation()
    {
        return h;
    }

    public static void setImageProfileMatrix(ImageProfileMatrix imageprofilematrix)
    {
        f = imageprofilematrix;
    }

    public static void setJsonDataHandlers(ArrayList arraylist)
    {
        i = arraylist;
    }

    public static void setShowBallonAnimation(boolean flag)
    {
        h = flag;
    }

    public void onCreate()
    {
        super.onCreate();
        a = getApplicationContext();
        if (i == null)
        {
            i = new ArrayList();
        }
        AppStartup.getInstance().performInitTasks(getApplicationContext());
        NetworkMonitor.initialize(getApplicationContext());
        FilterDrawableMap filterdrawablemap = FilterDrawableMap.getInstance();
        e = filterdrawablemap;
        filterdrawablemap.initFilterDrawableMap();
        GsonBuilder gsonbuilder;
        String s;
        String s1;
        boolean flag;
        if (NetworkMonitor.isNetworkFast() == 3)
        {
            b = Volley.newRequestQueue(getApplicationContext(), 2);
        } else
        {
            b = Volley.newRequestQueue(getApplicationContext());
        }
        CrashLoggerUtils.init();
        c = new ImageLoader(b, TwoStageImageCache.getInstance(), DiskImageCache.getInstance(), getAppContext());
        gsonbuilder = new GsonBuilder();
        gsonbuilder.registerTypeAdapter(com/flipkart/android/response/component/data/customvalues/Renderable, new RenderableDeserializer());
        gsonbuilder.registerTypeAdapter(com/flipkart/android/response/component/data/customvalues/Renderable, new RenderableSerializer());
        d = gsonbuilder.create();
        s = FlipkartPreferenceManager.instance().getAppConfig();
        if (StringUtils.isNullOrEmpty(s))
        {
            s1 = MiscUtils.readConfigFromAssets("AppConfigJson");
            flag = true;
        } else
        {
            s1 = s;
            flag = false;
        }
        if (!StringUtils.isNullOrEmpty(s1))
        {
            ConfigResponseData configresponsedata = (ConfigResponseData)getGsonInstance().fromJson(s1, com/flipkart/android/response/appconfig/ConfigResponseData);
            if (flag)
            {
                FlipkartPreferenceManager.instance().saveAppConfig(s1);
            }
            if (configresponsedata != null)
            {
                AppConfigUtils.getInstance().putConfigData(configresponsedata);
            }
        }
        try
        {
            f = ConfigHelper.getImageMatrixfFromPreferences();
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
        BuildSetup.setupBuild(getApplicationContext());
    }

    public void setCurrentActivity(Activity activity)
    {
        g = activity;
    }

    static 
    {
        System.loadLibrary("myjni");
    }
}
