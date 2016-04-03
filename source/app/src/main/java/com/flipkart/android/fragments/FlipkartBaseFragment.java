// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.BaseVDataHandler;
import com.flipkart.android.datahandler.NotifyMeVDataHandler;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ProductUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.flipkart.android.fragments:
//            E

public abstract class FlipkartBaseFragment extends Fragment
    implements TraceFieldInterface
{

    protected static final String TAG = (new StringBuilder("ASIMO.")).append(com/flipkart/android/fragments/FlipkartBaseFragment.getSimpleName()).toString();
    public static final String addToCart = "+Cart";
    public static final String goToCart = "GoTo Cart";
    protected Activity activity;
    protected AnalyticData analyticData;
    protected BaseVDataHandler baseVDataHandler;
    protected boolean isRefreshing;
    protected NotifyMeVDataHandler notifyMeDatahandler;
    protected String requestId;

    public FlipkartBaseFragment()
    {
        activity = null;
        requestId = null;
        baseVDataHandler = null;
        isRefreshing = false;
    }

    private void a()
    {
        View view;
        try
        {
            CrashLoggerUtils.pushAndUpdate("removing cue tips");
            view = ((ViewGroup)activity.getWindow().getDecorView()).findViewWithTag("cue_tips_overlay");
        }
        catch (Exception exception)
        {
            return;
        }
        if (view == null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        ((ViewGroup)activity.getWindow().getDecorView()).removeView(view);
    }

    static void a(FlipkartBaseFragment flipkartbasefragment)
    {
        flipkartbasefragment.a();
    }

    protected void closeRefresing()
    {
        if (activity != null && (activity instanceof HomeFragmentHolderActivity))
        {
            ((HomeFragmentHolderActivity)activity).closeRefreshing();
        }
    }

    public boolean handleBackPress()
    {
        return false;
    }

    public abstract boolean handleOnClick();

    protected void initRefresing()
    {
        if (activity != null && (activity instanceof HomeFragmentHolderActivity))
        {
            ((HomeFragmentHolderActivity)activity).initRefreshing();
        }
    }

    protected void loadCueTips(int i)
    {
        try
        {
            if (((ViewGroup)activity.getWindow().getDecorView()).findViewWithTag("cue_tips_overlay") == null)
            {
                View view = ((LayoutInflater)activity.getApplicationContext().getSystemService("layout_inflater")).inflate(i, null);
                view.setTag("cue_tips_overlay");
                ((ViewGroup)activity.getWindow().getDecorView()).addView(view);
                view.setVisibility(0);
                view.setOnClickListener(new E(this));
            }
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public void onAttach(Activity activity1)
    {
        super.onAttach(activity1);
        try
        {
            activity = activity1;
            return;
        }
        catch (Exception exception)
        {
            FkLogger.debug(TAG, (new StringBuilder("Exception: ")).append(exception.toString()).toString());
        }
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "FlipkartBaseFragment#onCreateView", null);
_L1:
        analyticData = new AnalyticData();
        View view = super.onCreateView(layoutinflater, viewgroup, bundle);
        TraceMachine.exitMethod();
        return view;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "FlipkartBaseFragment#onCreateView", null);
          goto _L1
    }

    public void onDestroy()
    {
        CrashLoggerUtils.pushAndUpdate("ondestroying: Fk BaseFrag");
        super.onDestroy();
        activity = null;
        FkLogger.debug(TAG, (new StringBuilder("Ondestroy ")).append(getClass().getCanonicalName()).toString());
    }

    public void onDestroyView()
    {
        CrashLoggerUtils.pushAndUpdate("ondestroying view Fk BaseFrag");
        a();
        super.onDestroyView();
        if (baseVDataHandler != null)
        {
            baseVDataHandler.cancelRequests();
        }
    }

    public void onDetach()
    {
        super.onDetach();
        System.gc();
    }

    public void onFragmentPopped()
    {
    }

    public void onFragmentPushed()
    {
        if (baseVDataHandler != null)
        {
            baseVDataHandler.cancelRequests();
        }
    }

    public void onResume()
    {
        super.onResume();
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public void onStop()
    {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
    }

    protected void performNotifyMe(FkProductListContext fkproductlistcontext, int i, View view)
    {
        View view1 = (View)view.getParent();
        LinearLayout linearlayout = (LinearLayout)view1;
        TextView textview = (TextView)((View)linearlayout.getParent()).findViewById(0x7f0a017e);
        EditText edittext = (EditText)view1.findViewById(0x7f0a017c);
        ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        String s = edittext.getText().toString();
        edittext.setText("");
        String s1 = (String)fkproductlistcontext.getProductIds().get(i);
        com.flipkart.android.response.productInfo.ProductInfo productinfo = fkproductlistcontext.getProductForId(s1);
        if (s.contains(".") && s.contains("@"))
        {
            notifyMeDatahandler.notifyMe(s, s1, ProductUtils.getCategory(productinfo), new AnalyticData(null, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
            linearlayout.setVisibility(8);
            textview.setVisibility(0);
            return;
        } else
        {
            ToastMessageUtils.showErrorToastMessage("Please specify a valid Email address", activity, true);
            return;
        }
    }

    protected void popFragmentStack()
    {
        if (activity != null && (activity instanceof HomeFragmentHolderActivity))
        {
            ((HomeFragmentHolderActivity)activity).popFragmentStack();
        }
    }

    protected void share(String s, String s1)
    {
        if (activity == null || activity.isFinishing()) goto _L2; else goto _L1
_L1:
        String s2 = "";
        if (!StringUtils.isNullOrEmpty(s1)) goto _L4; else goto _L3
_L3:
        if (StringUtils.isNullOrEmpty(s)) goto _L6; else goto _L5
_L5:
        String as[] = s.split(":");
        String s5;
        String s6;
        s5 = "";
        s6 = "";
        if (as.length <= 2) goto _L8; else goto _L7
_L7:
        s5 = as[1].trim();
        s6 = as[2].trim();
_L20:
        if (StringUtils.isNullOrEmpty(s5)) goto _L10; else goto _L9
_L9:
        s1 = (new StringBuilder("http://www.flipkart.com/item/")).append(s5).toString();
        if (!StringUtils.isNullOrEmpty(s6)) goto _L12; else goto _L11
_L11:
        String s3 = "Check out this product on Flipkart!";
_L22:
        ArrayList arraylist;
        Iterator iterator;
        TrackingHelper.sendSocialSharing();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", s3);
        intent.putExtra("android.intent.extra.TEXT", s1);
        arraylist = new ArrayList();
        iterator = activity.getApplicationContext().getPackageManager().queryIntentActivities(intent, 0).iterator();
_L16:
        if (!iterator.hasNext()) goto _L14; else goto _L13
_L13:
        String s4 = ((ResolveInfo)iterator.next()).activityInfo.packageName;
        if (s4.equalsIgnoreCase(activity.getPackageName())) goto _L16; else goto _L15
_L15:
        Intent intent2;
        intent2 = new Intent("android.intent.action.SEND");
        intent2.setType("text/plain");
        if (!s4.equalsIgnoreCase("com.android.mms") && !s4.equalsIgnoreCase("com.facebook.orca") && !s4.equalsIgnoreCase("com.google.android.talk")) goto _L18; else goto _L17
_L17:
        intent2.putExtra("android.intent.extra.TEXT", (new StringBuilder()).append(s3).append("\n").append(s1).toString());
_L21:
        intent2.setPackage(s4);
        arraylist.add(intent2);
          goto _L16
_L8:
        if (as.length <= 1) goto _L20; else goto _L19
_L19:
        s5 = as[1].trim();
          goto _L20
_L12:
        s2 = (new StringBuilder("Check out this ")).append(s6).append(" on Flipkart!").toString();
          goto _L10
_L4:
        if (!s1.startsWith("http"))
        {
            s1 = (new StringBuilder("http://www.flipkart.com")).append(s1).toString();
        }
        break MISSING_BLOCK_LABEL_537;
_L18:
        intent2.putExtra("android.intent.extra.SUBJECT", s3);
        intent2.putExtra("android.intent.extra.TEXT", s1);
          goto _L21
_L14:
        try
        {
            Intent intent1 = Intent.createChooser((Intent)arraylist.remove(0), "Share on ..");
            intent1.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])arraylist.toArray(new Parcelable[0]));
            startActivity(intent1);
            CrashLoggerUtils.pushAndUpdate((new StringBuilder("sharing product : ")).append(s1).toString());
            return;
        }
        catch (Exception exception) { }
          goto _L2
_L6:
        s3 = s2;
          goto _L22
_L2:
        return;
_L10:
        s3 = s2;
          goto _L22
        s3 = "Check this out on Flipkart!";
          goto _L22
    }

    protected void showError(View view, int i, android.view.View.OnClickListener onclicklistener, boolean flag)
    {
        boolean flag1 = false;
        if (view == null) goto _L2; else goto _L1
_L1:
        View view1 = view.findViewById(0x7f0a00d6);
        if (view1 != null) goto _L4; else goto _L3
_L3:
        boolean flag2;
        View view2;
        flag1 = true;
        if (activity == null || !(activity instanceof HomeFragmentHolderActivity))
        {
            break MISSING_BLOCK_LABEL_245;
        }
        View view3 = activity.getLayoutInflater().inflate(0x7f030041, null);
        flag2 = flag1;
        view2 = view3;
_L5:
        if (view2 != null)
        {
            ImageView imageview = (ImageView)view2.findViewById(0x7f0a00d7);
            TextView textview = (TextView)view2.findViewById(0x7f0a00d8);
            TextView textview1 = (TextView)view2.findViewById(0x7f0a00d9);
            ((Button)view2.findViewById(0x7f0a00da)).setOnClickListener(onclicklistener);
            if (i == 900 || i == -1)
            {
                imageview.setImageResource(0x7f0200cb);
                textview.setText("No Connection");
                textview1.setText("Please check your internet connection");
            } else
            if (i == 504)
            {
                imageview.setImageResource(0x7f020179);
                textview.setText("Request Timed Out.");
                textview1.setText("Please try after sometime");
            } else
            {
                imageview.setImageResource(0x7f020179);
                textview.setText("Unknown Error");
                textview1.setText("Please try after sometime.");
            }
            if (flag)
            {
                imageview.setColorFilter(getResources().getColor(0x7f090070));
                textview.setTextColor(getResources().getColor(0x7f090070));
                textview1.setTextColor(getResources().getColor(0x7f090070));
            }
            if (flag2)
            {
                CustomDialog.dismissDialog();
                ((ViewGroup)view).addView(view2);
            }
            ActionBarView.setActionBarCustomView(activity, ActionBarState.Home);
        }
_L2:
        return;
_L4:
        view1.setVisibility(0);
        flag2 = flag1;
        view2 = view1;
          goto _L5
    }

}
