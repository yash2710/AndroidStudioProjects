// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity.base;

import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import com.flipkart.android.activity.BlockActivity;
import com.flipkart.android.analytics.TrackingUtil;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.TabContextCache;
import com.flipkart.android.utils.component.ComponentWidgetUtils;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import net.simonvt.menudrawer.ColorDrawable;

// Referenced classes of package com.flipkart.android.activity.base:
//            a

public class FlipkartBaseSherlockFragmentActivity extends ActionBarActivity
    implements TraceFieldInterface
{

    private boolean a;
    private a b;
    protected Drawable mActionBarBackgroundDrawable;

    public FlipkartBaseSherlockFragmentActivity()
    {
        a = false;
    }

    public Drawable getmActionBarBackgroundDrawable()
    {
        return mActionBarBackgroundDrawable;
    }

    public void initActionBarBg(ActionBar actionbar)
    {
        mActionBarBackgroundDrawable = getResources().getDrawable(0x7f020057);
        LayoutDetails layoutdetails = AppConfigUtils.getInstance().getActionBarTheme();
        if (layoutdetails != null)
        {
            try
            {
                if (layoutdetails.getBackgroundColor() != null)
                {
                    mActionBarBackgroundDrawable = new ColorDrawable(Color.parseColor(layoutdetails.getBackgroundColor()));
                }
                if (layoutdetails.getBackgroundImage() != null)
                {
                    String s = ImageUtils.getImageUrl(layoutdetails.getBackgroundImage().getImage());
                    if (!StringUtils.isNullOrEmpty(s))
                    {
                        ComponentWidgetUtils.setActionBarDrawable(s, this);
                    }
                }
            }
            catch (Exception exception)
            {
                FkLogger.printStackTrace(exception);
            }
        }
        actionbar.setBackgroundDrawable(mActionBarBackgroundDrawable);
    }

    public boolean isNoActionBar()
    {
        return a;
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("FlipkartBaseSherlockFragmentActivity");
        TraceMachine.enterMethod(_nr_trace, "FlipkartBaseSherlockFragmentActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        requestWindowFeature(5);
        if (!a)
        {
            ActionBar actionbar = getSupportActionBar();
            if (actionbar != null)
            {
                actionbar.setCustomView(0x7f030018);
                actionbar.setDisplayShowTitleEnabled(false);
                actionbar.setDisplayShowCustomEnabled(true);
                actionbar.setHomeButtonEnabled(true);
                setSupportProgressBarIndeterminateVisibility(false);
                initActionBarBg(actionbar);
            }
        }
        b = new a(this, (byte)0);
        registerReceiver(b, new IntentFilter(BlockActivity.BLOCK_ACTION_COMMAND));
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "FlipkartBaseSherlockFragmentActivity#onCreate", null);
          goto _L1
    }

    protected void onDestroy()
    {
        super.onDestroy();
        TabContextCache.getInstance().clearCache();
        unregisterReceiver(b);
    }

    protected void onPause()
    {
        super.onPause();
        TrackingUtil.stopActivity();
    }

    protected void onResume()
    {
        super.onResume();
        TrackingUtil.startActivity(this);
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

    public void setNoActionBar(boolean flag)
    {
        a = flag;
    }

    public void setmActionBarBackgroundDrawable(Drawable drawable)
    {
        mActionBarBackgroundDrawable = drawable;
    }
}
