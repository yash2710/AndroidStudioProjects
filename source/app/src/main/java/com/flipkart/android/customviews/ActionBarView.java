// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.cart.Cart;
import com.flipkart.android.cart.CartHandler;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.utils.TabContextCache;

// Referenced classes of package com.flipkart.android.customviews:
//            f, b, c, d, 
//            e, a

public class ActionBarView
{

    public static final String CURRENT_ACTIONBAR_STATE = "current_actionbar_state";
    private static ImageView a;
    private static ImageView b;
    private static RelativeLayout c;
    private static RelativeLayout d;
    private static HomeFragmentHolderActivity e;
    private static View f = null;
    private static ActionBar g;

    public ActionBarView()
    {
    }

    static HomeFragmentHolderActivity a()
    {
        return e;
    }

    private static void a(boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        if (flag)
        {
            b.setVisibility(0);
        } else
        {
            b.setVisibility(8);
        }
        if (flag1)
        {
            a.setVisibility(0);
        } else
        {
            a.setVisibility(8);
        }
        if (flag2)
        {
            c.setVisibility(0);
        } else
        {
            c.setVisibility(8);
        }
        if (flag3)
        {
            d.setVisibility(0);
            return;
        } else
        {
            d.setVisibility(8);
            return;
        }
    }

    public static View setActionBarCustomView(Activity activity, ActionBarState actionbarstate)
    {
        LayoutInflater layoutinflater;
        ActionBarState actionbarstate1 = (ActionBarState)TabContextCache.getInstance().getResponse("current_actionbar_state");
        HomeFragmentHolderActivity homefragmentholderactivity = (HomeFragmentHolderActivity)activity;
        e = homefragmentholderactivity;
        homefragmentholderactivity.setActionBarState(actionbarstate);
        if (actionbarstate == actionbarstate1)
        {
            break MISSING_BLOCK_LABEL_730;
        }
        g = e.getSupportActionBar();
        layoutinflater = LayoutInflater.from(e);
        View view = layoutinflater.inflate(0x7f030019, null);
        f = view;
        b = (ImageView)view.findViewById(0x7f0a0065);
        a = (ImageView)f.findViewById(0x7f0a0068);
        c = (RelativeLayout)f.findViewById(0x7f0a0066);
        d = (RelativeLayout)f.findViewById(0x7f0a0067);
        updateFlipkartLogo();
        b.invalidate();
        e.showMenu();
        f.a[actionbarstate.ordinal()];
        JVM INSTR tableswitch 1 8: default 188
    //                   1 473
    //                   2 489
    //                   3 505
    //                   4 557
    //                   5 597
    //                   6 607
    //                   7 647
    //                   8 689;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        if (g != null)
        {
            if (c.getVisibility() == 0)
            {
                if (CartHandler.getCart().getCartItemCount() != 0)
                {
                    TextView textview1 = (TextView)c.findViewById(0x7f0a009e);
                    if (textview1 != null)
                    {
                        textview1.setVisibility(0);
                        textview1.setText((new StringBuilder()).append(CartHandler.getCart().getCartItemCount()).toString());
                    }
                }
                c.setOnClickListener(new b());
            }
            if (d.getVisibility() == 0)
            {
                if (FlipkartPreferenceManager.instance().getInAppUnreadCount() != 0)
                {
                    TextView textview = (TextView)d.findViewById(0x7f0a0123);
                    if (textview != null)
                    {
                        textview.setVisibility(0);
                        textview.setText((new StringBuilder()).append(FlipkartPreferenceManager.instance().getInAppUnreadCount()).toString());
                    }
                }
                d.setOnClickListener(new c());
            }
            if (a.getVisibility() == 0)
            {
                a.setOnClickListener(new d());
            }
            if (b.getVisibility() == 0)
            {
                b.setOnClickListener(new e());
            }
            g.setCustomView(f);
            g.setDisplayOptions(16);
            g.setDisplayShowCustomEnabled(true);
            g.setDisplayHomeAsUpEnabled(true);
            e.setActionBarBgAlpha(255);
            if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 18)
            {
                e.getSupportActionBar().setDisplayShowHomeEnabled(false);
            } else
            {
                e.getSupportActionBar().setDisplayShowHomeEnabled(true);
                e.getSupportActionBar().setIcon(new ColorDrawable(0xff000000));
            }
            g.show();
        }
_L10:
        TabContextCache.getInstance().putResponse("current_actionbar_state", actionbarstate);
        return f;
_L2:
        e.hideBackCarrotAndShowMenu();
        a(true, false, true, true);
          goto _L1
_L3:
        e.showBackCarrot();
        a(true, true, true, true);
          goto _L1
_L4:
        f = layoutinflater.inflate(0x7f0300ae, null);
        g.setTitle("custom title");
        e.showBackCarrot();
        e.hideMainMenu();
        e.getWindow().setSoftInputMode(16);
        a(true, false, false, false);
          goto _L1
_L5:
        f = layoutinflater.inflate(0x7f03004d, null);
        g.setTitle("custom title");
        e.getWindow().setSoftInputMode(16);
        a(true, false, true, false);
          goto _L1
_L6:
        a(true, false, false, false);
          goto _L1
_L7:
        e.getWindow().setSoftInputMode(16);
        f = layoutinflater.inflate(0x7f030083, null);
        g.setTitle("custom title");
        a(true, false, false, false);
          goto _L1
_L8:
        e.getWindow().setSoftInputMode(16);
        f = layoutinflater.inflate(0x7f030047, null);
        g.setTitle("custom title");
        e.hideMainMenu();
        e.showBackCarrot();
_L9:
        a(false, false, false, false);
          goto _L1
        if (e.getSupportActionBar() != null)
        {
            f = e.getSupportActionBar().getCustomView();
        }
        e.setActionBarBgAlpha(255);
          goto _L10
    }

    public static void showSearchIcon()
    {
        if (a != null)
        {
            a.setVisibility(0);
            a.setOnClickListener(new a());
        }
    }

    public static void updateFlipkartLogo()
    {
        if (b != null)
        {
            if (FlipkartPreferenceManager.instance().getUsersFfStatus().booleanValue())
            {
                b.setImageResource(0x7f0200eb);
            } else
            {
                b.setImageResource(0x7f020110);
            }
            b.invalidate();
        }
    }

}
