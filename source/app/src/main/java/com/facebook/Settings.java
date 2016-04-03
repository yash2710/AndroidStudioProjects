// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook:
//            LoggingBehavior, Response, FacebookRequestError, AppEventsLogger, 
//            Request, RequestBatch, FacebookException

public final class Settings
{

    private static final String ANALYTICS_EVENT = "event";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    private static final String ATTRIBUTION_KEY = "attribution";
    private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
    private static final String AUTO_PUBLISH = "auto_publish";
    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_KEEP_ALIVE = 1;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new _cls1();
    private static final BlockingQueue DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
    private static final String FACEBOOK_COM = "facebook.com";
    private static final Object LOCK = new Object();
    private static final String MOBILE_INSTALL_EVENT = "MOBILE_APP_INSTALL";
    private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
    private static final String TAG = com/facebook/Settings.getCanonicalName();
    private static volatile String appVersion;
    private static volatile Executor executor;
    private static volatile String facebookDomain = "facebook.com";
    private static final HashSet loggingBehaviors;
    private static volatile boolean shouldAutoPublishInstall;

    public Settings()
    {
    }

    public static final void addLoggingBehavior(LoggingBehavior loggingbehavior)
    {
        synchronized (loggingBehaviors)
        {
            loggingBehaviors.add(loggingbehavior);
        }
    }

    public static final void clearLoggingBehaviors()
    {
        synchronized (loggingBehaviors)
        {
            loggingBehaviors.clear();
        }
    }

    public static String getAppVersion()
    {
        return appVersion;
    }

    private static Executor getAsyncTaskExecutor()
    {
        Field field;
        Object obj;
        try
        {
            field = android/os/AsyncTask.getField("THREAD_POOL_EXECUTOR");
        }
        catch (NoSuchFieldException nosuchfieldexception)
        {
            return null;
        }
        try
        {
            obj = field.get(null);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            return null;
        }
        if (obj == null)
        {
            return null;
        }
        if (!(obj instanceof Executor))
        {
            return null;
        } else
        {
            return (Executor)obj;
        }
    }

    public static String getAttributionId(ContentResolver contentresolver)
    {
        Cursor cursor;
        String s;
        try
        {
            String as[] = {
                "aid"
            };
            cursor = contentresolver.query(ATTRIBUTION_ID_CONTENT_URI, as, null, null, null);
        }
        catch (Exception exception)
        {
            FkLogger.debug(TAG, (new StringBuilder("Caught unexpected exception in getAttributionId(): ")).append(exception.toString()).toString());
            return null;
        }
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        if (!cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_98;
        }
        s = cursor.getString(cursor.getColumnIndex("aid"));
        cursor.close();
        return s;
        return null;
    }

    public static Executor getExecutor()
    {
        Object obj = LOCK;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        if (executor != null)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        obj1 = getAsyncTaskExecutor();
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        obj1 = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, DEFAULT_WORK_QUEUE, DEFAULT_THREAD_FACTORY);
        executor = ((Executor) (obj1));
        obj;
        JVM INSTR monitorexit ;
        return executor;
        Exception exception;
        exception;
        throw exception;
    }

    public static String getFacebookDomain()
    {
        return facebookDomain;
    }

    public static final Set getLoggingBehaviors()
    {
        Set set;
        synchronized (loggingBehaviors)
        {
            set = Collections.unmodifiableSet(new HashSet(loggingBehaviors));
        }
        return set;
    }

    public static String getMigrationBundle()
    {
        return "fbsdk:20130708";
    }

    public static String getSdkVersion()
    {
        return "3.5.2";
    }

    public static boolean getShouldAutoPublishInstall()
    {
        return shouldAutoPublishInstall;
    }

    public static final boolean isLoggingBehaviorEnabled(LoggingBehavior loggingbehavior)
    {
        HashSet hashset = loggingBehaviors;
        hashset;
        JVM INSTR monitorenter ;
        return false;
    }

    public static boolean publishInstallAndWait(Context context, String s)
    {
        Response response = publishInstallAndWaitForResponse(context, s);
        return response != null && response.getError() == null;
    }

    public static Response publishInstallAndWaitForResponse(Context context, String s)
    {
        return publishInstallAndWaitForResponse(context, s, false);
    }

    static Response publishInstallAndWaitForResponse(Context context, String s, boolean flag)
    {
        if (context == null || s == null)
        {
            try
            {
                throw new IllegalArgumentException("Both context and applicationId must be non-null");
            }
            catch (Exception exception)
            {
                Utility.logd("Facebook-publish", exception);
                return new Response(null, null, new FacebookRequestError(null, exception));
            }
        }
        String s1;
        SharedPreferences sharedpreferences;
        String s2;
        String s3;
        long l;
        String s4;
        s1 = getAttributionId(context.getContentResolver());
        sharedpreferences = context.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
        s2 = (new StringBuilder()).append(s).append("ping").toString();
        s3 = (new StringBuilder()).append(s).append("json").toString();
        l = sharedpreferences.getLong(s2, 0L);
        s4 = sharedpreferences.getString(s3, null);
        if (flag)
        {
            break MISSING_BLOCK_LABEL_140;
        }
        setShouldAutoPublishInstall(false);
        GraphObject graphobject;
        graphobject = com.facebook.model.GraphObject.Factory.create();
        graphobject.setProperty("event", "MOBILE_APP_INSTALL");
        graphobject.setProperty("attribution", s1);
        graphobject.setProperty("auto_publish", Boolean.valueOf(flag));
        boolean flag1;
        Request request;
        GraphObject graphobject1;
        GraphObject graphobject2;
        GraphObject graphobject3;
        Response response;
        android.content.SharedPreferences.Editor editor;
        JSONObject jsonobject;
        String s5;
        String s6;
        if (!AppEventsLogger.getLimitEventUsage(context))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        graphobject.setProperty("application_tracking_enabled", Boolean.valueOf(flag1));
        graphobject.setProperty("application_package_name", context.getPackageName());
        request = Request.newPostRequest(null, String.format("%s/activities", new Object[] {
            s
        }), graphobject, null);
        if (l == 0L) goto _L2; else goto _L1
_L1:
        graphobject1 = null;
        if (s4 == null)
        {
            break MISSING_BLOCK_LABEL_274;
        }
        JVM INSTR new #346 <Class JSONObject>;
        graphobject3 = com.facebook.model.GraphObject.Factory.create(JSONObjectInstrumentation.init(s4));
        graphobject1 = graphobject3;
        graphobject2 = graphobject1;
_L9:
        if (graphobject2 != null)
        {
            break MISSING_BLOCK_LABEL_317;
        }
        return (Response)Response.createResponsesFromString("true", null, new RequestBatch(new Request[] {
            request
        }), true).get(0);
        return new Response(null, null, graphobject2, true);
_L2:
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_346;
        }
        throw new FacebookException("No attribution id returned from the Facebook application");
        if (!Utility.queryAppSettings(s, false).supportsAttribution())
        {
            throw new FacebookException("Install attribution has been disabled on the server.");
        }
        response = request.executeAndWait();
        editor = sharedpreferences.edit();
        editor.putLong(s2, System.currentTimeMillis());
        if (response.getGraphObject() == null || response.getGraphObject().getInnerJSONObject() == null) goto _L4; else goto _L3
_L3:
        jsonobject = response.getGraphObject().getInnerJSONObject();
        if (jsonobject instanceof JSONObject) goto _L6; else goto _L5
_L5:
        s6 = jsonobject.toString();
_L7:
        editor.putString(s3, s6);
_L4:
        editor.commit();
        return response;
_L6:
        s5 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        s6 = s5;
          goto _L7
        JSONException jsonexception;
        jsonexception;
        graphobject2 = null;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public static void publishInstallAsync(Context context, String s)
    {
        publishInstallAsync(context, s, null);
    }

    public static void publishInstallAsync(Context context, final String applicationId, final Request.Callback callback)
    {
        final Context applicationContext = context.getApplicationContext();
        getExecutor().execute(new _cls2());
    }

    public static final void removeLoggingBehavior(LoggingBehavior loggingbehavior)
    {
        synchronized (loggingBehaviors)
        {
            loggingBehaviors.remove(loggingbehavior);
        }
    }

    public static void setAppVersion(String s)
    {
        appVersion = s;
    }

    public static void setExecutor(Executor executor1)
    {
        Validate.notNull(executor1, "executor");
        synchronized (LOCK)
        {
            executor = executor1;
        }
    }

    public static void setFacebookDomain(String s)
    {
        FkLogger.warn(TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        facebookDomain = s;
    }

    public static void setShouldAutoPublishInstall(boolean flag)
    {
        shouldAutoPublishInstall = flag;
    }

    static 
    {
        LoggingBehavior aloggingbehavior[] = new LoggingBehavior[1];
        aloggingbehavior[0] = LoggingBehavior.DEVELOPER_ERRORS;
        loggingBehaviors = new HashSet(Arrays.asList(aloggingbehavior));
    }

    private class _cls2
        implements Runnable
    {

        final Context val$applicationContext;
        final String val$applicationId;
        final Request.Callback val$callback;

        public final void run()
        {
            final Response response = Settings.publishInstallAndWaitForResponse(applicationContext, applicationId);
            class _cls1
                implements Runnable
            {

                final _cls2 this$0;
                final Response val$response;

                public void run()
                {
                    callback.onCompleted(response);
                }

                _cls1()
                {
                    this$0 = _cls2.this;
                    response = response1;
                    super();
                }
            }

            if (callback != null)
            {
                (new Handler(Looper.getMainLooper())).post(new _cls1());
            }
        }

        _cls2()
        {
            applicationContext = context;
            applicationId = s;
            callback = callback1;
            super();
        }
    }


    private class _cls1
        implements ThreadFactory
    {

        private final AtomicInteger counter = new AtomicInteger(0);

        public final Thread newThread(Runnable runnable)
        {
            return new Thread(runnable, (new StringBuilder("FacebookSdk #")).append(counter.incrementAndGet()).toString());
        }

        _cls1()
        {
        }
    }

}
