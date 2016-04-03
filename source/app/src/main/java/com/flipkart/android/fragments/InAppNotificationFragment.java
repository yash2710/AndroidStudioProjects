// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.EnhancedListView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.InAppNotificationDataHandler;
import com.flipkart.android.fragments.model.InAppNotificationModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.appconfig.AppUpgradeData;
import com.flipkart.android.response.customwidgetitemvalue.Action;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.InAppNotificationUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, K, F, G, 
//            I, J

public class InAppNotificationFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener
{

    private View a;
    private View b;
    private View c;
    private EnhancedListView d;
    private View e;
    private K f;
    private ImageLoader g;
    private Map h;
    private boolean i;
    private InAppNotificationDataHandler j;
    private Button k;
    private Button l;
    private Long m;
    private int n;
    private boolean o;
    private boolean p;
    private ImageView q;

    public InAppNotificationFragment()
    {
        f = null;
        i = false;
        m = Long.valueOf(0L);
        n = 10;
        o = false;
        p = true;
    }

    static Long a(InAppNotificationFragment inappnotificationfragment, Long long1)
    {
        inappnotificationfragment.m = long1;
        return long1;
    }

    static String a(InAppNotificationFragment inappnotificationfragment, InAppNotificationModel inappnotificationmodel)
    {
        return a(inappnotificationmodel);
    }

    private static String a(InAppNotificationModel inappnotificationmodel)
    {
        return (new StringBuilder()).append(inappnotificationmodel.getNotificationType()).append(inappnotificationmodel.getNotificationId()).toString();
    }

    private void a()
    {
        if (!isRefreshing && !i && FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            isRefreshing = true;
            analyticData.setIsPageFirstLanding(p);
            if (p)
            {
                p = false;
            }
            analyticData.setIsUserClick(true);
            j.getInappNotification(n, m.longValue(), analyticData);
        }
    }

    static void a(InAppNotificationFragment inappnotificationfragment, ArrayList arraylist)
    {
        if (c())
        {
            AppUpgradeData appupgradedata = AppConfigUtils.getInstance().getAppUpgradeData();
            if (appupgradedata != null)
            {
                InAppNotificationModel inappnotificationmodel = new InAppNotificationModel();
                inappnotificationmodel.setTitle(appupgradedata.getAppUpgradeTitle());
                inappnotificationmodel.setSubTitle(appupgradedata.getAppUpgradeNotificationHtml());
                inappnotificationmodel.setImageUrl(ImageUtils.getImageUrl(appupgradedata.getAppIcon()));
                inappnotificationmodel.setShareable(false);
                boolean flag;
                Action action;
                if (!FlipkartPreferenceManager.instance().isAppUpgradeNotificationShown().booleanValue())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                inappnotificationmodel.setNew(flag);
                inappnotificationmodel.setNotificationType("UPGRADE_APP");
                inappnotificationmodel.setTimeStamp(System.currentTimeMillis());
                action = new Action();
                action.setScreenType("upgradeApp");
                inappnotificationmodel.setAction(action);
                TrackingHelper.sendRateAndUpgradeAppEvent("Upgrade InAppNotification shown");
                inappnotificationfragment.h.put(a(inappnotificationmodel), inappnotificationmodel);
                arraylist.add(0, a(inappnotificationmodel));
                FlipkartPreferenceManager.instance().saveIsAppUpgradeNotificationShown(Boolean.valueOf(true));
            }
        }
    }

    static boolean a(InAppNotificationFragment inappnotificationfragment)
    {
        return c();
    }

    static boolean a(InAppNotificationFragment inappnotificationfragment, String s)
    {
        return a(s);
    }

    static boolean a(InAppNotificationFragment inappnotificationfragment, boolean flag)
    {
        inappnotificationfragment.o = true;
        return true;
    }

    private static boolean a(String s)
    {
        try
        {
            NotificationTypes.valueOf(s);
        }
        catch (Exception exception)
        {
            return false;
        }
        return true;
    }

    static View b(InAppNotificationFragment inappnotificationfragment)
    {
        return inappnotificationfragment.c;
    }

    private void b()
    {
        if (a != null)
        {
            View view = a.findViewById(0x7f0a00d6);
            if (view != null)
            {
                ((ViewGroup)a).removeView(view);
            }
        }
    }

    static void b(InAppNotificationFragment inappnotificationfragment, boolean flag)
    {
        if (inappnotificationfragment.f != null)
        {
            inappnotificationfragment.f.notifyDataSetChanged(false);
            if (flag && FlipkartPreferenceManager.instance().isFirstTimeInAppNotificationPageLoad().booleanValue() && inappnotificationfragment.activity != null)
            {
                FlipkartPreferenceManager.instance().saveFirstTimeInAppNotificationPageLoad(Boolean.valueOf(false));
                inappnotificationfragment.loadCueTips(0x7f030058);
            }
        }
    }

    static View c(InAppNotificationFragment inappnotificationfragment)
    {
        return inappnotificationfragment.e;
    }

    private static boolean c()
    {
        AppUpgradeData appupgradedata = AppConfigUtils.getInstance().getAppUpgradeData();
        return appupgradedata != null && appupgradedata.isShowAppUpgradeNotification() && FlipkartPreferenceManager.instance().getAppVersionNumber() < appupgradedata.getLatestAppVersion() && FlipkartPreferenceManager.instance().isShowAppUpgradeNotification().booleanValue();
    }

    static boolean c(InAppNotificationFragment inappnotificationfragment, boolean flag)
    {
        inappnotificationfragment.i = true;
        return true;
    }

    static Map d(InAppNotificationFragment inappnotificationfragment)
    {
        return inappnotificationfragment.h;
    }

    static boolean e(InAppNotificationFragment inappnotificationfragment)
    {
        return inappnotificationfragment.o;
    }

    static void f(InAppNotificationFragment inappnotificationfragment)
    {
        InAppNotificationUtils.markAllRead(inappnotificationfragment.activity);
    }

    static K g(InAppNotificationFragment inappnotificationfragment)
    {
        return inappnotificationfragment.f;
    }

    static int h(InAppNotificationFragment inappnotificationfragment)
    {
        return inappnotificationfragment.n;
    }

    static View i(InAppNotificationFragment inappnotificationfragment)
    {
        return inappnotificationfragment.a;
    }

    static EnhancedListView j(InAppNotificationFragment inappnotificationfragment)
    {
        return inappnotificationfragment.d;
    }

    static void k(InAppNotificationFragment inappnotificationfragment)
    {
        inappnotificationfragment.a();
    }

    static ImageLoader l(InAppNotificationFragment inappnotificationfragment)
    {
        return inappnotificationfragment.g;
    }

    public boolean handleBackPress()
    {
        super.handleBackPress();
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    public void onClick(View view)
    {
        Object obj = view.getTag();
        if (obj != null && (obj instanceof String))
        {
            String s = (String)obj;
            if (s.equals("try_again"))
            {
                refreshView();
            } else
            {
                if (s.equals("login"))
                {
                    ((HomeFragmentHolderActivity)activity).doLogin();
                    return;
                }
                if (s.equals("continue_shopping"))
                {
                    ((HomeFragmentHolderActivity)activity).loadHomeFragment();
                    return;
                }
                if (s.contains("share"))
                {
                    String as[] = s.split(":", 3);
                    if (as.length == 3)
                    {
                        TrackingHelper.sendInAppNotificationVariables((new StringBuilder("nf_share_")).append(as[1]).toString());
                        if (!StringUtils.isNullOrEmpty(as[2]))
                        {
                            share("", as[2]);
                            return;
                        }
                    }
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        a = layoutinflater.inflate(0x7f030056, viewgroup, false);
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return a;
        }
        b = a.findViewById(0x7f0a0121);
        c = a.findViewById(0x7f0a0113);
        q = (ImageView)c.findViewById(0x7f0a0114);
        q.setColorFilter(Color.parseColor("#E6E6E6"));
        e = a.findViewById(0x7f0a011f);
        d = (EnhancedListView)a.findViewById(0x7f0a0120);
        k = (Button)b.findViewById(0x7f0a0122);
        l = (Button)c.findViewById(0x7f0a0115);
        k.setOnClickListener(this);
        l.setOnClickListener(this);
        if (g == null)
        {
            g = FlipkartApplication.getImageLoader();
        }
        h = new LinkedHashMap();
        m = Long.valueOf(0L);
        i = false;
        o = false;
        isRefreshing = false;
        b();
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            b.setVisibility(8);
            c.setVisibility(8);
            e.setVisibility(0);
        } else
        {
            TrackingHelper.sendInAppNotificationVariables("nf_click_logout");
            TrackingHelper.sendInAppNotificationPage("Notification:Logout");
            b.setVisibility(0);
            e.setVisibility(8);
            c.setVisibility(8);
        }
        j = new F(this);
        f = new K(this);
        d.setAdapter(f);
        d.setDismissCallback(new G(this));
        d.setOnItemClickListener(new I(this));
        d.setOnScrollListener(new J(this));
        d.enableSwipeToDismiss();
        d.setSwipeDirection(com.flipkart.android.customviews.EnhancedListView.SwipeDirection.BOTH);
        d.setSwipingLayout(0x7f030059);
        a();
        ActionBarView.setActionBarCustomView(activity, ActionBarState.Default_Back);
        return a;
    }

    public void onDestroy()
    {
        if (h != null)
        {
            h.clear();
            h = null;
        }
        j = null;
        super.onDestroy();
    }

    public void onFragmentPopped()
    {
        super.onFragmentPopped();
    }

    public void onFragmentPushed()
    {
        super.onFragmentPushed();
    }

    public void onStop()
    {
        if (d != null)
        {
            d.discardUndo();
        }
        super.onStop();
    }

    public void refreshView()
    {
        b();
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            if (b != null)
            {
                b.setVisibility(8);
            }
            e.setVisibility(0);
            i = false;
            isRefreshing = false;
            a();
            return;
        }
        if (b != null)
        {
            b.setVisibility(0);
        }
        e.setVisibility(8);
    }

    private class NotificationTypes extends Enum
    {

        public static final NotificationTypes LAUNCH;
        public static final NotificationTypes ORDER_STATUS;
        public static final NotificationTypes PRICE_DROP;
        public static final NotificationTypes SANTA_OFFERS;
        public static final NotificationTypes TARGETED_OFFERS;
        public static final NotificationTypes WELCOME;
        private static final NotificationTypes a[];

        public static NotificationTypes valueOf(String s)
        {
            return (NotificationTypes)Enum.valueOf(com/flipkart/android/fragments/InAppNotificationFragment$NotificationTypes, s);
        }

        public static NotificationTypes[] values()
        {
            return (NotificationTypes[])a.clone();
        }

        static 
        {
            PRICE_DROP = new NotificationTypes("PRICE_DROP", 0);
            ORDER_STATUS = new NotificationTypes("ORDER_STATUS", 1);
            TARGETED_OFFERS = new NotificationTypes("TARGETED_OFFERS", 2);
            WELCOME = new NotificationTypes("WELCOME", 3);
            SANTA_OFFERS = new NotificationTypes("SANTA_OFFERS", 4);
            LAUNCH = new NotificationTypes("LAUNCH", 5);
            NotificationTypes anotificationtypes[] = new NotificationTypes[6];
            anotificationtypes[0] = PRICE_DROP;
            anotificationtypes[1] = ORDER_STATUS;
            anotificationtypes[2] = TARGETED_OFFERS;
            anotificationtypes[3] = WELCOME;
            anotificationtypes[4] = SANTA_OFFERS;
            anotificationtypes[5] = LAUNCH;
            a = anotificationtypes;
        }

        private NotificationTypes(String s, int i1)
        {
            super(s, i1);
        }
    }

}
