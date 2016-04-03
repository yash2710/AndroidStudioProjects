// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkData
{

    private static final String APPLINK_BRIDGE_ARGS_KEY = "bridge_args";
    private static final String APPLINK_METHOD_ARGS_KEY = "method_args";
    private static final String APPLINK_VERSION_KEY = "version";
    public static final String ARGUMENTS_TAPTIME_KEY = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
    private static final String BRIDGE_ARGS_METHOD_KEY = "method";
    private static final String BUNDLE_APPLINK_ARGS_KEY = "com.facebook.platform.APPLINK_ARGS";
    private static final String METHOD_ARGS_REF_KEY = "ref";
    private static final String METHOD_ARGS_TARGET_URL_KEY = "target_url";
    private static final String TAG = com/facebook/AppLinkData.getCanonicalName();
    private JSONObject arguments;
    private String ref[];
    private Uri targetUri;
    private String version;

    private AppLinkData()
    {
    }

    public static AppLinkData createFromActivity(Activity activity)
    {
        Validate.notNull(activity, "activity");
        Intent intent = activity.getIntent();
        AppLinkData applinkdata;
        if (intent == null)
        {
            applinkdata = null;
        } else
        {
            applinkdata = createFromJson(intent.getStringExtra("com.facebook.platform.APPLINK_ARGS"));
            if (applinkdata == null)
            {
                return createFromUri(intent.getData());
            }
        }
        return applinkdata;
    }

    private static AppLinkData createFromJson(String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        JSONObject jsonobject;
        String s1;
        JVM INSTR new #88  <Class JSONObject>;
        jsonobject = JSONObjectInstrumentation.init(s);
        s1 = jsonobject.getString("version");
        if (!jsonobject.getJSONObject("bridge_args").getString("method").equals("applink") || !s1.equals("2")) goto _L1; else goto _L3
_L3:
        AppLinkData applinkdata;
        String s2;
        applinkdata = new AppLinkData();
        applinkdata.version = s1;
        applinkdata.arguments = jsonobject.getJSONObject("method_args");
        if (!applinkdata.arguments.has("ref"))
        {
            break MISSING_BLOCK_LABEL_125;
        }
        s2 = applinkdata.arguments.getString("ref");
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        applinkdata.ref = s2.split(",");
        if (applinkdata.arguments.has("target_url"))
        {
            applinkdata.targetUri = Uri.parse(applinkdata.arguments.getString("target_url"));
        }
        return applinkdata;
        JSONException jsonexception;
        jsonexception;
        FkLogger.debug(TAG, "Unable to parse AppLink JSON");
        return null;
    }

    private static AppLinkData createFromUri(Uri uri)
    {
        if (uri != null);
        return null;
    }

    public static void fetchDeferredAppLinkData(Context context, CompletionHandler completionhandler)
    {
        fetchDeferredAppLinkData(context, null, completionhandler);
    }

    public static void fetchDeferredAppLinkData(Context context, String s, final CompletionHandler completionHandler)
    {
        Validate.notNull(context, "context");
        Validate.notNull(completionHandler, "completionHandler");
        if (s == null)
        {
            s = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(s, "applicationId");
        DeferredAppLinkDataClient deferredapplinkdataclient = new DeferredAppLinkDataClient(context, s);
        deferredapplinkdataclient.setCompletedListener(new _cls1());
        if (!deferredapplinkdataclient.start())
        {
            (new Handler(Looper.getMainLooper())).post(new _cls2());
        }
    }

    public JSONObject getArguments()
    {
        return arguments;
    }

    public String[] getRef()
    {
        return ref;
    }

    public Uri getTargetUri()
    {
        return targetUri;
    }




    private class DeferredAppLinkDataClient extends PlatformServiceClient
    {

        protected final void populateRequestBundle(Bundle bundle)
        {
            bundle.putString("com.facebook.platform.extra.INSTALLDATA_PACKAGE", getContext().getPackageName());
        }

        DeferredAppLinkDataClient(Context context, String s)
        {
            super(context, 0x10004, 0x10005, 0x1332b3a, s);
        }
    }


    private class _cls1
        implements com.facebook.internal.PlatformServiceClient.CompletedListener
    {

        final CompletionHandler val$completionHandler;

        public final void completed(Bundle bundle)
        {
            AppLinkData applinkdata = null;
            if (bundle != null)
            {
                String s = bundle.getString("com.facebook.platform.APPLINK_ARGS");
                long l = bundle.getLong("com.facebook.platform.APPLINK_TAP_TIME_UTC", -1L);
                applinkdata = AppLinkData.createFromJson(s);
                if (l != -1L)
                {
                    try
                    {
                        applinkdata.getArguments().put("com.facebook.platform.APPLINK_TAP_TIME_UTC", l);
                    }
                    catch (JSONException jsonexception)
                    {
                        FkLogger.debug(AppLinkData.TAG, "Unable to put tap time in AppLinkData.arguments");
                    }
                }
            }
            completionHandler.onDeferredAppLinkDataFetched(applinkdata);
        }

        _cls1()
        {
            completionHandler = completionhandler;
            super();
        }

        private class CompletionHandler
        {

            public abstract void onDeferredAppLinkDataFetched(AppLinkData applinkdata);
        }

    }


    private class _cls2
        implements Runnable
    {

        final CompletionHandler val$completionHandler;

        public final void run()
        {
            completionHandler.onDeferredAppLinkDataFetched(null);
        }

        _cls2()
        {
            completionHandler = completionhandler;
            super();
        }
    }

}
