// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.PageName;
import com.flipkart.android.analytics.PageType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SellerVDataHandler;
import com.flipkart.android.fragments.model.SellerPageModel;
import com.flipkart.android.response.seller.SellerResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.SellerPageBuilder;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, bq

public class SellerFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener
{

    ProgressBar a;
    View b;
    private View c;
    private SellerResponse d;
    private String e;
    private Context f;
    private SellerVDataHandler g;
    private int h;
    private int i;

    public SellerFragment()
    {
        d = null;
        e = null;
        f = null;
        g = null;
        h = 0;
        i = 10;
    }

    static View a(SellerFragment sellerfragment)
    {
        return sellerfragment.c;
    }

    static SellerResponse a(SellerFragment sellerfragment, SellerResponse sellerresponse)
    {
        sellerfragment.d = sellerresponse;
        return sellerresponse;
    }

    private void a(int j, int k)
    {
        if (c != null)
        {
            a.setVisibility(0);
            b.setVisibility(8);
            View view = c.findViewById(0x7f0a00d6);
            if (view != null)
            {
                view.setVisibility(8);
            }
            if (g != null)
            {
                g.fetchSellerInfo(j, k);
            }
        }
    }

    static void b(SellerFragment sellerfragment)
    {
        if (sellerfragment.d != null)
        {
            SellerPageModel sellerpagemodel = SellerPageModel.getModel(sellerfragment.d, sellerfragment.f, sellerfragment.e);
            SellerPageBuilder.buildSellerPage(sellerpagemodel, sellerfragment.c, sellerfragment);
            if (sellerpagemodel != null)
            {
                TrackingHelper.sendPageView((new StringBuilder()).append(PageName.SellerInfoPage.name()).append(":").append(sellerpagemodel.getBusinessName()).toString(), PageType.SellerInfo);
            }
        }
    }

    public boolean handleBackPress()
    {
        CustomDialog.dismissDialog();
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    public void onClick(View view)
    {
        String s = (String)view.getTag();
        if (s != null)
        {
            if (s.equals("sticky-on_click_go_back"))
            {
                ((HomeFragmentHolderActivity)activity).popFragmentStack();
            } else
            {
                if (s.contains("load_home_fragment"))
                {
                    ((HomeFragmentHolderActivity)activity).loadHomeFragment();
                    return;
                }
                if (s.contains("try_again"))
                {
                    a(0, i);
                    return;
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        f = getActivity().getApplicationContext();
        activity = (HomeFragmentHolderActivity)getActivity();
        c = layoutinflater.inflate(0x7f0300b4, viewgroup, false);
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return c;
        }
        Bundle bundle1 = getArguments();
        if (bundle1 != null)
        {
            e = bundle1.getString("SELLER_ID_QUERY");
        }
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.SellerPage);
        a = (ProgressBar)c.findViewById(0x7f0a008b);
        b = c.findViewById(0x7f0a021a);
        g = new bq(this, e);
        a(0, i);
        ActionBarView.setActionBarCustomView(activity, ActionBarState.Default_Back);
        return c;
    }

    public void onDestroy()
    {
        super.onDestroy();
        analyticData.setPageTypeUtils(PageTypeUtils.SellerPage);
        c = null;
        e = null;
        f = null;
    }

    public void onFragmentPopped()
    {
    }

    public void onFragmentPushed()
    {
    }

    public void onPause()
    {
        FkLogger.debug("SellerInfoFragment", "on pause called");
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
    }
}
