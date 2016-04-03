// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import com.AdX.tag.AdXConnect;
import com.crittercism.app.Crittercism;
import com.facebook.AppEventsLogger;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.activity.LoginActivity;
import com.flipkart.android.analytics.EntryChannel;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.ConfigHelper;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.config.FlipkartPropertiesReader;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.WishListVDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.login.FacebookConstants;
import com.flipkart.android.notification.GcmUtils;
import com.flipkart.android.register.RegistrationHelper;
import com.flipkart.android.services.LocationService;
import com.flipkart.android.utils.CustomUrlUtils;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ReferralUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.TwoStageImageCache;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.jakewharton.disklrucache.DiskLruImageCache;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.File;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.flipkart.android:
//            a, c, b, d

public class SplashActivity extends Activity
    implements TraceFieldInterface
{

    public static final int RESULT_CLOSE_ALL = 34;
    private GoogleCloudMessaging a;
    private boolean b;
    private boolean c;
    private FkLoadingDialog d;
    private ProgressBar e;
    private boolean f;
    private IsLoggedInReceiver g;

    public SplashActivity()
    {
        a = null;
        b = false;
        c = false;
        d = null;
        e = null;
        f = false;
    }

    static GoogleCloudMessaging a(SplashActivity splashactivity, GoogleCloudMessaging googlecloudmessaging)
    {
        splashactivity.a = googlecloudmessaging;
        return googlecloudmessaging;
    }

    private void a()
    {
        Intent intent = new Intent(getApplicationContext(), com/flipkart/android/activity/HomeFragmentHolderActivity);
        intent.setData(getIntent().getData());
        intent.setAction(getIntent().getAction());
        FlipkartPreferenceManager.instance().saveAppLaunchCounts(1 + FlipkartPreferenceManager.instance().getAppLaunchCounts());
        FlipkartPreferenceManager.instance().saveIsShowPullOut(true);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    static void a(SplashActivity splashactivity)
    {
        splashactivity.getApplicationContext();
        File file;
        if (Environment.getExternalStorageState().equals("mounted"))
        {
            file = new File(Environment.getExternalStorageDirectory(), "data/fka/largeimages");
        } else
        {
            file = null;
        }
        if (file != null && file.isDirectory())
        {
            String as[] = file.list();
            for (int i = 0; i < as.length; i++)
            {
                (new File(file, as[i])).delete();
            }

        }
        splashactivity.removeTemporaryFiles();
    }

    private void b()
    {
        if (!FlipkartPreferenceManager.instance().isLoginShownOnFirstLoad().booleanValue())
        {
            if (getIntent().getData() == null)
            {
                Intent intent = new Intent(getApplicationContext(), com/flipkart/android/activity/LoginActivity);
                intent.putExtra("EXTRA_IS_FIRST_TIME_LOAD", true);
                intent.putExtra("EXTRA_IS_FROM_FK_ACTIVITY", true);
                intent.setData(getIntent().getData());
                intent.setAction(getIntent().getAction());
                f = true;
                startActivityForResult(intent, 1);
            } else
            {
                a();
            }
            FlipkartPreferenceManager.instance().setLoginShownOnFirstLoad(true);
            return;
        } else
        {
            a();
            return;
        }
    }

    static boolean b(SplashActivity splashactivity)
    {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(splashactivity) == 0;
    }

    static String c(SplashActivity splashactivity)
    {
        String s = FlipkartPreferenceManager.instance().getNotificationRegId();
        int i = FlipkartPreferenceManager.instance().getAppVersionForGcm();
        int j = FlipkartDeviceInfoProvider.getAppVersionNumber();
        FlipkartPreferenceManager.instance().saveAppVersionForGcm(FlipkartDeviceInfoProvider.getAppVersionNumber());
        if (i != 0x80000000 && i != j)
        {
            AdXConnect.getAdXConnectInstance(splashactivity.getApplicationContext(), true, 0);
            s = "";
        } else
        if (i == 0x80000000)
        {
            AdXConnect.getAdXConnectInstance(splashactivity.getApplicationContext(), false, 0);
            return s;
        }
        return s;
    }

    static void d(SplashActivity splashactivity)
    {
        a a1 = new a(splashactivity);
        Object aobj[] = {
            null, null, null
        };
        if (!(a1 instanceof AsyncTask))
        {
            a1.execute(aobj);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)a1, aobj);
            return;
        }
    }

    static FkLoadingDialog e(SplashActivity splashactivity)
    {
        return splashactivity.d;
    }

    static void f(SplashActivity splashactivity)
    {
        splashactivity.b();
    }

    static GoogleCloudMessaging g(SplashActivity splashactivity)
    {
        return splashactivity.a;
    }

    static void h(SplashActivity splashactivity)
    {
        java.util.ArrayList arraylist = (new WishListDao(FlipkartApplication.getAppContext())).getAllWishListPids();
        if (arraylist.size() > 0)
        {
            Collections.reverse(arraylist);
            String as[] = new String[arraylist.size()];
            for (int i = 0; i < arraylist.size(); i++)
            {
                as[i] = (String)arraylist.get(i);
            }

            (new c(splashactivity)).addToWishList(as, new AnalyticData(null, null, PageTypeUtils.SplashPage));
        }
    }

    public void doPostSubmitTask(boolean flag, boolean flag1, boolean flag2)
    {
        if (flag2)
        {
            d = new FkLoadingDialog(this);
            d.showDlg("", "Loading...Please Wait.", null, false);
        }
        (new Handler(Looper.getMainLooper())).post(new b(this));
        if (Crittercism.didCrashOnLastAppLoad())
        {
            FlipkartPreferenceManager.instance().saveLastTimeAppCrash(Boolean.valueOf(true));
            flag = true;
        }
        d d1 = new d(this, flag, flag1);
        Void avoid[] = new Void[0];
        if (!(d1 instanceof AsyncTask))
        {
            d1.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)d1, avoid);
            return;
        }
    }

    public void init()
    {
        com.flipkart.android.config.FlipkartPropertiesReader.ConfigOEM configoem;
        StringBuilder stringbuilder;
        startService(new Intent(this, com/flipkart/android/services/LocationService));
        int i = FlipkartPreferenceManager.instance().getAppVersionForGcm();
        int j = FlipkartDeviceInfoProvider.getAppVersionNumber();
        if (i != 0x80000000 && i != j)
        {
            FlipkartPreferenceManager.instance().saveRegisterKey("");
            FlipkartPreferenceManager.instance().saveIsShowAppUpgradePopUp(Boolean.valueOf(true));
            FlipkartPreferenceManager.instance().saveIsShowAppUpgradeNotification(Boolean.valueOf(true));
            FlipkartPreferenceManager.instance().saveIsAppUpgradeNotificationShown(Boolean.valueOf(false));
            FlipkartPreferenceManager.instance().saveAppRatePromptShownCount(0);
            FlipkartPreferenceManager.instance().saveAppUpgradePromptShownCount(0);
            FlipkartPreferenceManager.instance().saveIsShowRateTheAppPopUp(Boolean.valueOf(true));
            FlipkartPreferenceManager.instance().setLoginShownOnFirstLoad(true);
            FlipkartPreferenceManager.instance().saveAppLaunchCounts(0);
            FlipkartPreferenceManager.instance().saveFirstTimeProdListLoad(Boolean.valueOf(true));
        }
        if (!FlipkartDeviceInfoProvider.getOsVersion().equals(FlipkartPreferenceManager.instance().getLastOsVersion(this)))
        {
            FlipkartPreferenceManager.instance().saveRegisterKey("");
            FlipkartPreferenceManager.instance().saveCurrentOsVersion(this);
        }
        RegistrationHelper.doRegister(Long.toString(System.currentTimeMillis() / 1000L));
        ConfigHelper confighelper = new ConfigHelper(FlipkartApplication.getAppContext());
        confighelper.readConfig();
        confighelper.readImageMatrixConfig();
        TrackingHelper.sendEntryChannel(EntryChannel.Direct);
        if (!FlipkartPreferenceManager.instance().isReferralSent() && !StringUtils.isNullOrEmpty(FlipkartPreferenceManager.instance().getReferrerValue()))
        {
            String s7 = FlipkartPreferenceManager.instance().getReferrerValue();
            String s;
            String s1;
            String s2;
            String s6;
            String s8;
            if (s7.contains("pre-burn"))
            {
                s8 = "pre-burn";
            } else
            if (s7.contains("organic"))
            {
                s8 = "organic";
            } else
            {
                s8 = "paid";
            }
            ReferralUtils.sendReferral(FlipkartPreferenceManager.instance().getReferrerValue(), s8);
        }
        if (!FlipkartPreferenceManager.instance().isAdxReferralReceived().booleanValue())
        {
            s6 = AdXConnect.getAdXReferral(getApplicationContext(), 0);
            if (!StringUtils.isNullOrEmpty(s6))
            {
                TrackingHelper.sendAdxReferral(s6);
                FlipkartPreferenceManager.instance().saveIsAdxReferralReceived(Boolean.valueOf(true));
            }
        }
        if (!FlipkartPreferenceManager.instance().getGcmIdSentToServerStatus())
        {
            GcmUtils.sendRegistrationIdToBackend("launch");
        }
        if (!FlipkartPreferenceManager.instance().isFirstTimeLoad().booleanValue()) goto _L2; else goto _L1
_L1:
        FlipkartPreferenceManager.instance().setFirstLoadTime(ScreenMathUtils.getCurrentLinuxTimeStamp());
        configoem = (new FlipkartPropertiesReader()).getAppOEM(FlipkartApplication.getAppContext());
        if (configoem != com.flipkart.android.config.FlipkartPropertiesReader.ConfigOEM.PREBURN && !FlipkartPreferenceManager.instance().isPreburnApp().booleanValue()) goto _L4; else goto _L3
_L3:
        FlipkartPreferenceManager.instance().saveIsPreburnApp(Boolean.valueOf(true));
        TrackingHelper.sendMakeAndModel(FlipkartDeviceInfoProvider.getMakeAndModel());
        s = FlipkartDeviceInfoProvider.getManufacturer();
        s1 = FlipkartDeviceInfoProvider.getModel();
        stringbuilder = new StringBuilder();
        stringbuilder.append("utm_source%3D");
        if (StringUtils.isNullOrEmpty(s)) goto _L6; else goto _L5
_L5:
        stringbuilder.append(s);
        if (!StringUtils.isNullOrEmpty(s1))
        {
            stringbuilder.append("_").append(s1);
        }
_L7:
        stringbuilder.append("&utm_campaign%3Dpre-burn");
        s2 = stringbuilder.toString();
        FlipkartPreferenceManager.instance().saveReferrerValue(s2);
        ReferralUtils.sendReferral(s2, "pre-burn");
_L9:
        FlipkartPreferenceManager.instance().saveIsFirstTimeLoad(Boolean.valueOf(false));
        return;
_L6:
        stringbuilder.append("none");
          goto _L7
_L4:
        if (configoem != com.flipkart.android.config.FlipkartPropertiesReader.ConfigOEM.SELFHOST) goto _L9; else goto _L8
_L8:
        StringBuilder stringbuilder1;
        ApplicationInfo applicationinfo;
        stringbuilder1 = new StringBuilder();
        stringbuilder1.append("&utm_campaign%3D");
        applicationinfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
        if (applicationinfo == null)
        {
            break MISSING_BLOCK_LABEL_722;
        }
        String s3;
        String s4;
        Bundle bundle = applicationinfo.metaData;
        s3 = bundle.getString("OTHERSTORE");
        s4 = bundle.getString("APP_NAME");
        if (StringUtils.isNullOrEmpty(s3) || StringUtils.isNullOrEmpty(s4)) goto _L11; else goto _L10
_L10:
        String as[] = s4.split("\\.");
        if (as.length != 4 || StringUtils.isNullOrEmpty(as[3])) goto _L13; else goto _L12
_L12:
        stringbuilder1.append(as[3].toLowerCase());
_L14:
        stringbuilder1.append("&utm_campaign%3Dselfhost");
        String s5 = stringbuilder1.toString();
        FlipkartPreferenceManager.instance().saveReferrerValue(s5);
        ReferralUtils.sendReferral(s5, "paid");
          goto _L9
_L13:
        stringbuilder1.append("none");
          goto _L14
_L11:
        stringbuilder1.append("none");
          goto _L14
        stringbuilder1.append("none");
          goto _L14
_L2:
        if (!FlipkartPreferenceManager.instance().isReferralSent() && StringUtils.isNullOrEmpty(FlipkartPreferenceManager.instance().getReferrerValue()))
        {
            FlipkartPreferenceManager.instance().saveReferrerValue("utm_source%3D=organic");
            ReferralUtils.sendReferral("utm_source%3D=organic", "organic");
        }
          goto _L9
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
          goto _L9
        NullPointerException nullpointerexception;
        nullpointerexception;
          goto _L9
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (i != 1) goto _L2; else goto _L1
_L1:
        if (j != -1) goto _L4; else goto _L3
_L3:
        a();
_L6:
        return;
_L4:
        if (j == 34)
        {
            finish();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (i == 2 && j == -1)
        {
            b();
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("SplashActivity");
        TraceMachine.enterMethod(_nr_trace, "SplashActivity#onCreate", null);
_L2:
        requestWindowFeature(1);
        super.onCreate(bundle);
        b = true;
        f = false;
        FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
        FlipkartPreferenceManager.instance().saveBackPressed(Boolean.valueOf(false));
        FlipkartApplication.setShowBallonAnimation(true);
        if ((new FlipkartPropertiesReader()).getAppEnvironment(getApplicationContext()).ordinal() == com.flipkart.android.config.FlipkartPropertiesReader.AppEnvironment.TESTING.ordinal())
        {
            c = true;
        }
        g = new IsLoggedInReceiver();
        registerReceiver(g, new IntentFilter(HomeFragmentHolderActivity.LOGGED_IN_ACTION_COMMAND));
        if (c)
        {
            setContentView(0x7f03003c);
            CustomUrlUtils.handleCustomUrlActions(getWindow().getDecorView(), this);
            TraceMachine.exitMethod();
            return;
        }
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SplashActivity#onCreate", null);
        if (true) goto _L2; else goto _L1
_L1:
        setContentView(0x7f03001c);
        e = (ProgressBar)findViewById(0x7f0a0071);
        if (e != null)
        {
            e.setVisibility(0);
        }
        doPostSubmitTask(false, false, false);
        TraceMachine.exitMethod();
        return;
    }

    public void onDestroy()
    {
        super.onDestroy();
        if (d != null)
        {
            d.dismissDlg();
        }
        d = null;
        unregisterReceiver(g);
        View view = findViewById(0x7f0a0069);
        if (view != null)
        {
            view.setBackgroundDrawable(null);
        }
    }

    public void onResume()
    {
        super.onResume();
        ((FlipkartApplication)getApplicationContext()).setCurrentActivity(this);
        try
        {
            if (b)
            {
                AdXConnect.getAdXConnectEventInstance(getApplicationContext(), "Launch", "", "", URLEncoder.encode(FlipkartDeviceInfoProvider.getMakeModelAndPreburn(), "UTF-8"));
                b = false;
            }
            AppEventsLogger.activateApp(getApplicationContext(), FacebookConstants.AppId);
            if (f && e != null)
            {
                e.setVisibility(8);
            }
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void removeTemporaryFiles()
    {
        File file = new File((new StringBuilder()).append(TwoStageImageCache.getInstance().getDiskCache().getFilePath()).append("/temporary_file.jpg").toString());
        try
        {
            file.delete();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    static 
    {
        com/flipkart/android/SplashActivity.getSimpleName();
    }

    private class IsLoggedInReceiver extends BroadcastReceiver
    {

        private SplashActivity a;

        public void onReceive(Context context, Intent intent)
        {
            boolean flag1 = intent.getBooleanExtra("updateLogo", false);
            boolean flag = flag1;
_L2:
            if (!flag && FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
            {
                SplashActivity.h(a);
            }
            return;
            Exception exception;
            exception;
            flag = false;
            if (true) goto _L2; else goto _L1
_L1:
        }

        public IsLoggedInReceiver()
        {
            a = SplashActivity.this;
            super();
        }
    }

}
