// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android;

import android.os.AsyncTask;
import com.flipkart.android.DB.BrowseHistoryDao;
import com.flipkart.android.DB.ComponentWidgetDataDao;
import com.flipkart.android.DB.ComponentWidgetLayoutDao;
import com.flipkart.android.DB.FlipkartProductInfoDao;
import com.flipkart.android.DB.ProductDiscoveryDao;
import com.flipkart.android.DB.SellerDao;
import com.flipkart.android.DB.UGCDao;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.config.ConfigHelper;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.ApiLogger;
import com.flipkart.android.log.LoggerTag;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.StringUtils;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// Referenced classes of package com.flipkart.android:
//            SplashActivity

final class d extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private UGCDao a;
    private SellerDao b;
    private ProductDiscoveryDao c;
    private FlipkartProductInfoDao d;
    private BrowseHistoryDao e;
    private WishListDao f;
    private ComponentWidgetDataDao g;
    private ComponentWidgetLayoutDao h;
    private ConfigHelper i;
    private boolean j;
    private boolean k;
    private SplashActivity l;

    public d(SplashActivity splashactivity, boolean flag, boolean flag1)
    {
        l = splashactivity;
        super();
        j = false;
        k = false;
        a = new UGCDao(FlipkartApplication.getAppContext());
        b = new SellerDao(FlipkartApplication.getAppContext());
        c = new ProductDiscoveryDao(FlipkartApplication.getAppContext());
        d = new FlipkartProductInfoDao(FlipkartApplication.getAppContext());
        e = new BrowseHistoryDao(FlipkartApplication.getAppContext());
        f = new WishListDao(FlipkartApplication.getAppContext());
        g = new ComponentWidgetDataDao(FlipkartApplication.getAppContext());
        h = new ComponentWidgetLayoutDao(FlipkartApplication.getAppContext());
        i = new ConfigHelper(FlipkartApplication.getAppContext());
        j = flag;
        k = flag1;
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

    protected final transient Boolean doInBackground(Void avoid[])
    {
        a.deleteAll();
        b.deleteAll();
        c.deleteAll();
        if (!k && !j && !AppConfigUtils.getInstance().isClearComponentDataDb() && !AppConfigUtils.getInstance().isClearProductInfoDb()) goto _L2; else goto _L1
_L1:
        if (!k) goto _L4; else goto _L3
_L3:
        g.deleteAll();
        h.deleteAll();
        i.readConfig();
        d.deleteAll();
        f.deleteAll();
        e.deleteAll();
_L6:
        l.init();
        SplashActivity.a(l);
        return Boolean.valueOf(true);
_L4:
        if (j)
        {
            e.deleteAll();
            ArrayList arraylist1 = new ArrayList();
            arraylist1.addAll(f.getHundredWishListPids());
            d.deleteFlipkartProductInfoNotInArgumentsPids(arraylist1);
        } else
        {
            if (AppConfigUtils.getInstance().isClearComponentDataDb())
            {
                g.deleteAll();
                h.deleteAll();
            }
            if (AppConfigUtils.getInstance().isClearProductInfoDb())
            {
                e.deleteAll();
                ArrayList arraylist = new ArrayList();
                arraylist.addAll(f.getHundredWishListPids());
                d.deleteFlipkartProductInfoNotInArgumentsPids(arraylist);
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        new ArrayList();
        ArrayList arraylist2 = e.getAllBrowseHistoryPids();
        arraylist2.addAll(f.getHundredWishListPids());
        TreeSet treeset = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        treeset.addAll(arraylist2);
        ArrayList arraylist3 = new ArrayList(treeset);
        d.deleteFlipkartProductInfoNotInArgumentsPids(arraylist3);
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "d#doInBackground", null);
_L1:
        Boolean boolean1 = doInBackground((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return boolean1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "d#doInBackground", null);
          goto _L1
    }

    protected final void onPostExecute(Boolean boolean1)
    {
        if (SplashActivity.b(l))
        {
            FlipkartPreferenceManager.instance().saveGCMAvailable(true);
            SplashActivity.a(l, GoogleCloudMessaging.getInstance(FlipkartApplication.getAppContext()));
            if (StringUtils.isNullOrEmpty(SplashActivity.c(l)))
            {
                SplashActivity.d(l);
            }
        } else
        {
            HashMap hashmap = new HashMap();
            hashmap.put("device_type", FlipkartDeviceInfoProvider.getReadableDeviceId());
            ApiLogger.log(LoggerTag.Widget, "Play services not found", hashmap);
            FlipkartPreferenceManager.instance().saveGCMAvailable(false);
        }
        if (SplashActivity.e(l) != null)
        {
            SplashActivity.e(l).dismissDlg();
        }
        SplashActivity.f(l);
    }

    protected final volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "d#onPostExecute", null);
_L1:
        onPostExecute((Boolean)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "d#onPostExecute", null);
          goto _L1
    }
}
