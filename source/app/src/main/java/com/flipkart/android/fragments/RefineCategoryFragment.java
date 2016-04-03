// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.flipkart.android.activity.FilterActivity;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoMediumTextView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SearchVDataHander;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.RefineByCategoryResponse;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, aS, aQ, aR, 
//            aT, aO, aP

public class RefineCategoryFragment extends FlipkartBaseFragment
{

    public static String REFINE_BY_FRAGMENT = "REFINE_BY_FRAGMENT";
    private static final String a = (new StringBuilder("ASIMO.")).append(com/flipkart/android/fragments/RefineCategoryFragment.getSimpleName()).toString();
    private FkProductListContext b;
    private ArrayList c;
    private ArrayList d;
    private aT e;
    private SearchVDataHander f;
    private BrowseParam g;
    private RefineByCategoryResponse h;
    private Activity i;
    private LayoutInflater j;
    private String k;
    private RelativeLayout l;
    private FkLoadingDialog m;
    private View n;

    public RefineCategoryFragment()
    {
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
        g = null;
        k = null;
        n = null;
    }

    static View a(RefineCategoryFragment refinecategoryfragment, View view)
    {
        refinecategoryfragment.n = null;
        return null;
    }

    static BrowseParam a(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.g;
    }

    static ArrayList a(RefineCategoryFragment refinecategoryfragment, ArrayList arraylist)
    {
        refinecategoryfragment.c = arraylist;
        return arraylist;
    }

    private void a()
    {
        f = new aS(this);
    }

    static void a(RefineCategoryFragment refinecategoryfragment, String s)
    {
        refinecategoryfragment.a();
        refinecategoryfragment.k = s;
        String s1;
        boolean flag;
        if (refinecategoryfragment.b.getParam() instanceof BrowseParam)
        {
            s1 = ((BrowseParam)refinecategoryfragment.b.getParam()).getQuery();
            flag = ((BrowseParam)refinecategoryfragment.b.getParam()).isEnableAugmentSearch();
        } else
        {
            s1 = null;
            flag = false;
        }
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
        {
            refinecategoryfragment.l.setAlpha(0.4F);
        }
        refinecategoryfragment.m = new FkLoadingDialog(refinecategoryfragment.i);
        if (refinecategoryfragment.m != null)
        {
            refinecategoryfragment.m.showDlg("", "Loading...", null, true);
        }
        refinecategoryfragment.b.clearProducts();
        refinecategoryfragment.analyticData.setIsPageFirstLanding(false);
        refinecategoryfragment.f.doSearch(null, s1, s, null, null, null, null, 0, 0, 0, flag, refinecategoryfragment.analyticData, null);
    }

    static ArrayList b(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.c;
    }

    static ArrayList b(RefineCategoryFragment refinecategoryfragment, ArrayList arraylist)
    {
        refinecategoryfragment.d = arraylist;
        return arraylist;
    }

    static ArrayList c(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.d;
    }

    static aT d(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.e;
    }

    static View e(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.n;
    }

    static Activity f(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.i;
    }

    static FkLoadingDialog g(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.m;
    }

    static FkProductListContext h(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.b;
    }

    static String i(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.k;
    }

    static RelativeLayout j(RefineCategoryFragment refinecategoryfragment)
    {
        return refinecategoryfragment.l;
    }

    protected void drawCueTips()
    {
        if (n != null)
        {
            n.setTag("cue_tips_overlay");
            ((ViewGroup)i.getWindow().getDecorView()).addView(n);
            n.setVisibility(0);
            n.setOnClickListener(new aQ(this));
        }
    }

    public boolean handleBackPress()
    {
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    protected void initActionBar()
    {
        View view = ActionBarView.setActionBarCustomView((HomeFragmentHolderActivity)getActivity(), ActionBarState.FiltersPage);
        CustomRobotoLightTextView customrobotolighttextview = (CustomRobotoLightTextView)view.findViewById(0x7f0a00eb);
        if (customrobotolighttextview != null && b != null)
        {
            customrobotolighttextview.setText((new StringBuilder()).append(b.getTotalProductCount()).append(" products").toString());
        }
        ((CustomRobotoMediumTextView)view.findViewById(0x7f0a00ea)).setOnClickListener(new aR(this));
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            i = (FilterActivity)activity;
            return;
        }
        catch (Exception exception)
        {
            FkLogger.debug(a, (new StringBuilder("Exception: ")).append(exception.toString()).toString());
        }
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        j = layoutinflater;
        Bundle bundle1 = getArguments();
        if (bundle1 != null)
        {
            String s = bundle1.getString(REFINE_BY_FRAGMENT);
            h = (RefineByCategoryResponse)ContextCache.getInstance().getResponse(s);
        }
        i = (HomeFragmentHolderActivity)getActivity();
        if (h != null)
        {
            c = h.getStoreList();
            d = h.getStoreList();
            b = h.getFkContext();
            if (b != null && (b.getParam() instanceof BrowseParam))
            {
                g = (BrowseParam)b.getParam();
            }
        }
        a();
        initActionBar();
        showCueTips("RefineByCategoryPage");
        if (g == null)
        {
            popFragmentStack();
            return null;
        } else
        {
            FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.RefineByCategoryPage);
            l = (RelativeLayout)layoutinflater.inflate(0x7f030044, viewgroup, false);
            LinearLayout linearlayout = (LinearLayout)l.findViewById(0x7f0a00e0);
            LinearLayout linearlayout1 = (LinearLayout)j.inflate(0x7f0300bb, null);
            linearlayout1.findViewById(0x7f0a022d);
            TextView textview = (TextView)linearlayout1.findViewById(0x7f0a00df);
            linearlayout1.setBackgroundColor(Color.parseColor("#f6f4ee"));
            textview.setText("Sub-category");
            textview.setTag("onclick_subcategory");
            textview.setTextColor(0xff000000);
            textview.setCompoundDrawablesWithIntrinsicBounds(0, 0x7f020169, 0, 0);
            linearlayout.addView(linearlayout1);
            ExpandableListView expandablelistview = (ExpandableListView)l.findViewById(0x7f0a00e1);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -1);
            e = new aT(this, (byte)0);
            expandablelistview.setAdapter(e);
            expandablelistview.setBackgroundColor(i.getResources().getColor(0x7f090070));
            expandablelistview.setDividerHeight(ScreenMathUtils.dpToPx(2, i));
            expandablelistview.setChildIndicator(null);
            expandablelistview.setGroupIndicator(null);
            expandablelistview.setDivider(new ColorDrawable(i.getResources().getColor(0x7f090036)));
            expandablelistview.setChildDivider(new ColorDrawable(i.getResources().getColor(0x7f090036)));
            expandablelistview.setLayoutParams(layoutparams);
            expandablelistview.setOnChildClickListener(new aO(this));
            expandablelistview.setOnGroupClickListener(new aP(this));
            return l;
        }
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        analyticData.setPageTypeUtils(PageTypeUtils.RefineByCategoryPage);
        HomeFragmentHolderActivity homefragmentholderactivity = (HomeFragmentHolderActivity)getActivity();
        homefragmentholderactivity.showMenu();
        homefragmentholderactivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void showCueTips(String s)
    {
        n = ((ViewGroup)i.getWindow().getDecorView()).findViewWithTag("cue_tips_overlay");
        if (n == null)
        {
            LayoutInflater layoutinflater = (LayoutInflater)i.getApplicationContext().getSystemService("layout_inflater");
            if (s.equals("RefineByCategoryPage"))
            {
                n = layoutinflater.inflate(0x7f0300ac, null);
                drawCueTips();
            }
        }
    }

}
