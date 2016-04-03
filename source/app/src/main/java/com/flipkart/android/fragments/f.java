// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SearchVDataHander;
import com.flipkart.android.response.discovery.DiscoveryResponse;
import com.flipkart.android.response.discovery.MetaDataResponse;
import com.flipkart.android.response.discovery.SearchResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FilterPagePreCallBackCache;
import com.flipkart.android.utils.FkLoadingDialog;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            AllFiltersFragment

final class f extends SearchVDataHander
{

    private AllFiltersFragment a;

    f(AllFiltersFragment allfiltersfragment)
    {
        a = allfiltersfragment;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        AllFiltersFragment.d(a, false);
        AllFiltersFragment.A(a).dismissDlg();
        LinearLayout linearlayout = (LinearLayout)AllFiltersFragment.e(a).findViewById(0x7f0a0074);
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
        {
            AllFiltersFragment.q(a).setAlpha(1.0F);
            linearlayout.setAlpha(1.0F);
            AllFiltersFragment.r(a).setAlpha(1.0F);
        }
        CustomDialog.showErrorMessage(i, j, s, AllFiltersFragment.l(a));
    }

    public final void resultReceived(DiscoveryResponse discoveryresponse, boolean flag)
    {
        if (discoveryresponse != null)
        {
            a.analyticData.setRequestId(discoveryresponse.getRequestId());
        }
        if (AllFiltersFragment.z(a) == null)
        {
            return;
        }
        AllFiltersFragment.b(a, -1);
        AllFiltersFragment.b(a, true);
        LinearLayout linearlayout = (LinearLayout)AllFiltersFragment.e(a).findViewById(0x7f0a0074);
        if (a.c)
        {
            linearlayout.removeViews(2, -2 + linearlayout.getChildCount());
        } else
        {
            linearlayout.removeViews(1, -1 + linearlayout.getChildCount());
        }
        AllFiltersFragment.c(a, false);
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
        {
            AllFiltersFragment.q(a).setAlpha(1.0F);
            linearlayout.setAlpha(1.0F);
            AllFiltersFragment.r(a).setAlpha(1.0F);
        }
        AllFiltersFragment.n(a).clear();
        AllFiltersFragment.p(a).setSortOptions(discoveryresponse.getSearchResponse().getSortOptions());
        AllFiltersFragment.p(a).clearFilterMaps();
        AllFiltersFragment.p(a).setFilterMaps(discoveryresponse.getSearchResponse().getFacetResponseList());
        AllFiltersFragment.d(a, discoveryresponse.getSearchResponse().getMetadata().getTotalProduct());
        AllFiltersFragment.d(a, false);
        if (AllFiltersFragment.A(a) != null)
        {
            AllFiltersFragment.A(a).dismissDlg();
        }
        AllFiltersFragment.c(a, true);
        AllFiltersFragment.e(a, false);
        AllFiltersFragment.B(a);
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((DiscoveryResponse)obj, flag);
    }
}
