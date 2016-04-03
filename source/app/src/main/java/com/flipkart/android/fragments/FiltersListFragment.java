// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.flipkart.android.DB.AutoSuggest;
import com.flipkart.android.DB.AutoSuggestDao;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoMediumTextView;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.customviews.GuidedSearchWidget;
import com.flipkart.android.customviews.NullResultViewWidget;
import com.flipkart.android.customviews.PinCodeViewWidget;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.OfferTermsVDataHandler;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.datahandler.param.HeaderParams;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.response.GuidedSearchTag;
import com.flipkart.android.response.discovery.ResourceResponse;
import com.flipkart.android.response.discovery.SortOptionsResponse;
import com.flipkart.android.response.discovery.StoreMetaInfo;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.FacetData;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.NullResultWidgetState;
import com.flipkart.android.utils.OfferBackgroundUtils;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.PinCodeWidgetState;
import com.flipkart.android.utils.RefineByCategoryResponse;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductListFragment, u, z, A, 
//            D, C, v, w, 
//            x, y, B, SearchListFragment

public class FiltersListFragment extends ProductListFragment
{

    public static String IS_FROM_OFFERS = "fromOffers";
    public static final int REFINE_BY_CAT_FRAGMENT = 2;
    public static final int SUB_CATEGORY_FRAGMENT = 3;
    public static final int SUB_FILTER_FRAGMENT = 1;
    protected ImageView augmentedSearchCloseButton;
    private LinearLayout b;
    private int c;
    private OfferTermsVDataHandler d;
    private int e;
    private String f;
    private int g;
    private Dialog h;
    protected HomeFragmentHolderActivity homeFragmentHolderActivity;
    private ArrayList i;
    private ArrayList j;
    private LinearLayout k;
    private LinearLayout l;
    private LinearLayout m;
    private GuidedSearchWidget n;
    private int o;
    private android.view.View.OnClickListener p;
    protected String searchQuery;
    protected RelativeLayout titleLayout;
    protected TextView titleView;

    public FiltersListFragment()
    {
        c = 0;
        e = 0;
        f = null;
        g = 0;
        h = null;
        i = null;
        j = null;
        k = null;
        l = null;
        m = null;
        o = 0;
        p = new u(this);
    }

    static int a(FiltersListFragment filterslistfragment, int i1)
    {
        filterslistfragment.g = i1;
        return i1;
    }

    static LinearLayout a(FiltersListFragment filterslistfragment)
    {
        return filterslistfragment.l;
    }

    static String a(FiltersListFragment filterslistfragment, String s)
    {
        filterslistfragment.f = s;
        return s;
    }

    private void a()
    {
        boolean flag;
        ArrayList arraylist = (ArrayList)fkContext.getSortOptions();
        for (int i1 = 0; i1 < arraylist.size(); i1++)
        {
            if (((SortOptionsResponse)arraylist.get(i1)).getResource().isSelected())
            {
                c = i1;
            }
        }

        if (fkContext.getSortOptions() != null && fkContext.getSortOptions().size() > 0)
        {
            CustomRobotoLightTextView customrobotolighttextview1 = (CustomRobotoLightTextView)m.findViewById(0x7f0a00e8);
            if (customrobotolighttextview1 != null)
            {
                customrobotolighttextview1.setVisibility(0);
                customrobotolighttextview1.setTypeface(null, 2);
                Iterator iterator;
                String s;
                CustomRobotoMediumTextView customrobotomediumtextview;
                android.widget.RelativeLayout.LayoutParams layoutparams;
                android.widget.RelativeLayout.LayoutParams layoutparams1;
                Iterator iterator1;
                String s5;
                String s6;
                try
                {
                    customrobotolighttextview1.setText(((SortOptionsResponse)fkContext.getSortOptions().get(c)).getTitle());
                }
                catch (Exception exception)
                {
                    customrobotolighttextview1.setText(((SortOptionsResponse)fkContext.getSortOptions().get(-1 + fkContext.getSortOptions().size())).getTitle());
                }
            }
        }
        if (fkContext.getSelectedFilterMap().size() <= 0) goto _L2; else goto _L1
_L1:
        iterator = fkContext.getSelectedFilterMap().keySet().iterator();
_L6:
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        s6 = (String)iterator.next();
        if (((ArrayList)fkContext.getSelectedFilterMap().get(s6)).size() <= 0) goto _L6; else goto _L5
_L5:
        flag = true;
_L24:
        s = ((BrowseParam)fkContext.getParam()).getPincode();
        RelativeLayout relativelayout;
        boolean flag1;
        CustomRobotoLightTextView customrobotolighttextview;
        ImageView imageview;
        android.widget.RelativeLayout.LayoutParams layoutparams2;
        String s1;
        String s2;
        RelativeLayout relativelayout1;
        String s3;
        String s4;
        if (fkContext.isShowPin() && !StringUtils.isNullOrEmpty(s))
        {
            flag1 = true;
        } else
        {
            flag1 = flag;
        }
        customrobotomediumtextview = (CustomRobotoMediumTextView)m.findViewById(0x7f0a00df);
        customrobotolighttextview = (CustomRobotoLightTextView)m.findViewById(0x7f0a00e4);
        imageview = (ImageView)m.findViewById(0x7f0a00e3);
        if (!flag1) goto _L8; else goto _L7
_L7:
        layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams.setMargins(ScreenMathUtils.dpToPx(15, homeFragmentHolderActivity), ScreenMathUtils.dpToPx(7, homeFragmentHolderActivity), ScreenMathUtils.dpToPx(7, homeFragmentHolderActivity), 0);
        if (imageview != null)
        {
            imageview.setLayoutParams(layoutparams);
            imageview.setImageResource(0x7f0200ef);
            imageview.setColorFilter(Color.parseColor("#e9b200"));
        }
        layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams1.setMargins(0, ScreenMathUtils.dpToPx(6, homeFragmentHolderActivity), 0, 0);
        layoutparams1.addRule(1, 0x7f0a00e3);
        if (customrobotomediumtextview != null)
        {
            customrobotomediumtextview.setLayoutParams(layoutparams1);
            customrobotomediumtextview.setTextSize(2, 14F);
            customrobotomediumtextview.setText("FILTERS");
        }
        layoutparams2 = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams2.addRule(3, 0x7f0a00df);
        layoutparams2.addRule(1, 0x7f0a00e3);
        if (!fkContext.isShowPin()) goto _L10; else goto _L9
_L9:
        s5 = ((BrowseParam)fkContext.getParam()).getPincode();
        if (StringUtils.isNullOrEmpty(s5)) goto _L10; else goto _L11
_L11:
        s1 = (new StringBuilder("Pincode(")).append(s5).append(")").toString();
_L22:
        iterator1 = fkContext.getSelectedFilterMap().keySet().iterator();
        s2 = s1;
_L16:
        if (!iterator1.hasNext()) goto _L13; else goto _L12
_L12:
        s3 = (String)iterator1.next();
        if (((ArrayList)fkContext.getSelectedFilterMap().get(s3)).size() <= 0) goto _L15; else goto _L14
_L14:
label0:
        {
            if (s2.length() > 0)
            {
                break label0;
            }
            s2 = s3;
        }
          goto _L16
        s4 = (new StringBuilder()).append(s2).append(",").append(s3).toString();
_L21:
        s2 = s4;
          goto _L16
_L13:
        if (customrobotolighttextview != null)
        {
            customrobotolighttextview.setVisibility(0);
            customrobotolighttextview.setLayoutParams(layoutparams2);
            customrobotolighttextview.setTypeface(null, 2);
            customrobotolighttextview.setText(s2);
        }
_L17:
        relativelayout1 = (RelativeLayout)m.findViewById(0x7f0a00e2);
        if (relativelayout1 != null)
        {
            relativelayout1.setOnClickListener(new z(this));
        }
_L19:
        return;
_L8:
        if (customrobotolighttextview != null)
        {
            customrobotolighttextview.setVisibility(8);
        }
        if (imageview != null)
        {
            imageview.setImageResource(0x7f0200ef);
        }
          goto _L17
_L2:
        if (StringUtils.isNullOrEmpty(getStoreList()) && StringUtils.isNullOrEmpty(getParentStoreList())) goto _L19; else goto _L18
_L18:
        relativelayout = (RelativeLayout)m.findViewById(0x7f0a00e2);
        if (relativelayout == null) goto _L19; else goto _L20
_L20:
        relativelayout.setOnClickListener(new A(this));
        return;
_L15:
        s4 = s2;
          goto _L21
_L10:
        s1 = "";
        if (true) goto _L22; else goto _L4
_L4:
        flag = false;
        if (true) goto _L24; else goto _L23
_L23:
    }

    private void a(LinearLayout linearlayout)
    {
        int i1 = ScreenMathUtils.dpToPx(10, activity);
        ScreenMathUtils.dpToPx(15, activity);
        ArrayList arraylist = (ArrayList)fkContext.getSortOptions();
        for (int j1 = 0; j1 < arraylist.size(); j1++)
        {
            CustomRobotoLightTextView customrobotolighttextview = new CustomRobotoLightTextView(context, null);
            if (c == j1)
            {
                customrobotolighttextview.setTypeface(Typeface.DEFAULT_BOLD);
                customrobotolighttextview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020155, 0);
            }
            customrobotolighttextview.setTextSize(16F);
            customrobotolighttextview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
            customrobotolighttextview.setText(((SortOptionsResponse)arraylist.get(j1)).getTitle());
            customrobotolighttextview.setPadding(i1, i1, i1, i1);
            customrobotolighttextview.setTextColor(0xff000000);
            customrobotolighttextview.setTag(Integer.valueOf(j1));
            customrobotolighttextview.setOnClickListener(new D(this));
            linearlayout.addView(customrobotolighttextview);
            View view = new View(context);
            view.setBackgroundColor(context.getResources().getColor(0x7f09002a));
            view.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(1, context)));
            linearlayout.addView(view);
        }

    }

    private void a(BrowseParam browseparam, String s)
    {
        browseparam.setPincode(s);
        fkContext.clearProducts();
        updateListAdapterVars();
        fkContext.setShowPin(false);
        setNoMoreDataToDownload(false);
        setOnSamePage(true);
        pincodeViewWidget.removeAllViews();
        nullResultViewWidget.removeAllViews();
    }

    static int b(FiltersListFragment filterslistfragment, int i1)
    {
        filterslistfragment.e = i1;
        return i1;
    }

    private void b()
    {
        if (b.getChildCount() > 0 && b.getVisibility() == 8)
        {
            b.setVisibility(0);
            View view = b.findViewById(0x7f0a015f);
            if (view != null)
            {
                view.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(44, context) + o));
            }
            OfferBackgroundUtils.setOffersBackground(b, AppConfigUtils.getInstance().getBrowsePageTheme());
            productList.setSelection(0);
            String s = ((FacetData)((Map)fkContext.getFilterMap().get("Offers")).get(((ArrayList)fkContext.getSelectedFilterMap().get("Offers")).get(0))).getOfferId();
            String s1 = ((FacetData)((Map)fkContext.getFilterMap().get("Offers")).get(((ArrayList)fkContext.getSelectedFilterMap().get("Offers")).get(0))).getOfferDescription();
            String s2 = (String)((ArrayList)fkContext.getSelectedFilterMap().get("Offers")).get(0);
            CustomRobotoMediumTextView customrobotomediumtextview = (CustomRobotoMediumTextView)b.findViewById(0x7f0a0160);
            FlipkartPreferenceManager.instance().saveOfferText("");
            d.getTermsAndCondition(s, analyticData);
            customrobotomediumtextview.setText(s2);
            ((CustomRobotoLightTextView)b.findViewById(0x7f0a0161)).setText(s1);
            TextView textview = (TextView)b.findViewById(0x7f0a0104);
            if (s != null)
            {
                if (textview != null)
                {
                    textview.setText(StringUtils.getHyperLinkedText("View Terms & Conditions"));
                    textview.setLinkTextColor(-1);
                    textview.setPadding(0, 0, 0, 0);
                }
            } else
            {
                textview.setVisibility(8);
            }
            b.setOnClickListener(new C(this, s));
        }
    }

    static void b(FiltersListFragment filterslistfragment)
    {
        filterslistfragment.h = new Dialog(filterslistfragment.activity);
        filterslistfragment.h.requestWindowFeature(1);
        LinearLayout linearlayout = (LinearLayout)filterslistfragment.layoutInflater.inflate(0x7f0300bc, null);
        filterslistfragment.a((LinearLayout)linearlayout.findViewById(0x7f0a00e6));
        filterslistfragment.h.setContentView(linearlayout);
        filterslistfragment.h.show();
    }

    static void c(FiltersListFragment filterslistfragment)
    {
        filterslistfragment.o = filterslistfragment.titleView.getMeasuredHeight();
    }

    static void c(FiltersListFragment filterslistfragment, int i1)
    {
        if (filterslistfragment.fkContext != null && (filterslistfragment.fkContext.getParam() instanceof BrowseParam))
        {
            BrowseParam browseparam = (BrowseParam)filterslistfragment.fkContext.getParam();
            String s = ((SortOptionsResponse)filterslistfragment.fkContext.getSortOptions().get(i1)).getResource().getParams();
            TrackingHelper.sendSortSelected(((SortOptionsResponse)filterslistfragment.fkContext.getSortOptions().get(i1)).getTitle());
            filterslistfragment.c = i1;
            filterslistfragment.pushAndChangeContext(null, browseparam.getStoreId(), null, s, null, null);
        }
    }

    static void d(FiltersListFragment filterslistfragment)
    {
        CrashLoggerUtils.pushAndUpdate("opening refine by frag");
        RefineByCategoryResponse refinebycategoryresponse = new RefineByCategoryResponse();
        refinebycategoryresponse.setParentStoreList(filterslistfragment.getParentStoreList());
        refinebycategoryresponse.setStoreList(filterslistfragment.getStoreList());
        refinebycategoryresponse.setFkContext(filterslistfragment.fkContext);
        filterslistfragment.homeFragmentHolderActivity.openRefineByOptionsPage(refinebycategoryresponse, filterslistfragment.analyticData.getRequestId());
    }

    static Dialog e(FiltersListFragment filterslistfragment)
    {
        return filterslistfragment.h;
    }

    static int f(FiltersListFragment filterslistfragment)
    {
        return filterslistfragment.g;
    }

    static int g(FiltersListFragment filterslistfragment)
    {
        return filterslistfragment.e;
    }

    static String h(FiltersListFragment filterslistfragment)
    {
        return filterslistfragment.f;
    }

    static int i(FiltersListFragment filterslistfragment)
    {
        return filterslistfragment.c;
    }

    public void buildErrorMessage(int i1, int j1, String s, String s1)
    {
        super.buildErrorMessage(i1, j1, s, s1);
        if (pincodeViewWidget != null)
        {
            pincodeViewWidget.removeAllViews();
        }
        if (b != null)
        {
            b.setVisibility(8);
        }
        if (m != null)
        {
            m.setVisibility(8);
        }
        if (getToggleView() != null)
        {
            getToggleView().setVisibility(8);
        }
        if (nullResultViewWidget == null) goto _L2; else goto _L1
_L1:
        BrowseParam browseparam;
        String s2;
        nullResultViewWidget.removeAllViews();
        browseparam = (BrowseParam)fkContext.getParam();
        setBrowseParamTitle(browseparam, true);
        s2 = browseparam.getQuery();
        if (j1 != 2014 && StringUtils.isNullOrEmpty(browseparam.getPincode())) goto _L4; else goto _L3
_L3:
        if (!FlipkartPreferenceManager.instance().isPincodeWidgetDismissed())
        {
            pincodeViewWidget.setState(PinCodeWidgetState.NotFound, null);
        }
        if (nullResultViewWidget != null)
        {
            nullResultViewWidget.setState(NullResultWidgetState.ShowLargeError, "", "productListPage");
        }
_L2:
        return;
_L4:
        if (StringUtils.isNullOrEmpty(s1))
        {
            break; /* Loop/switch isn't completed */
        }
        if (nullResultViewWidget != null)
        {
            nullResultViewWidget.setState(NullResultWidgetState.ShowContinueShopping, s1, "productListPage");
            return;
        }
        if (true) goto _L2; else goto _L5
_L5:
        if (i1 != 200)
        {
            String s3 = getErrorMessage(i1);
            if (!StringUtils.isNullOrEmpty(s3) && nullResultViewWidget != null)
            {
                nullResultViewWidget.setState(NullResultWidgetState.ShowContinueShopping, s3, "productListPage");
                return;
            }
        } else
        {
            isNullSearchPage = true;
            if (!StringUtils.isNullOrEmpty(s2))
            {
                (new AutoSuggestDao(context)).delete(new AutoSuggest(HashUtils.md5(s2.toLowerCase()), 0L, s2, null, null));
                titleView.setVisibility(8);
                nullResultViewWidget.setState(NullResultWidgetState.ShowWrongQuery, s2, "productListPage");
                isNullSearchPage = true;
                return;
            } else
            {
                nullResultViewWidget.setState(NullResultWidgetState.ShowContinueShopping, "Sorry! No Matching Products Found", "productListPage");
                return;
            }
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    public void createOfferTermsDialog(String s, String s1)
    {
        h = new Dialog(activity);
        h.requestWindowFeature(1);
        LinearLayout linearlayout = (LinearLayout)layoutInflater.inflate(0x7f030066, null);
        ((TextView)linearlayout.findViewById(0x7f0a0053)).setText("Offer Terms & Conditions");
        WebView webview = (WebView)linearlayout.findViewById(0x7f0a00b9);
        String s2 = StringUtils.getHtmlTextWithCss(s1);
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 11)
        {
            s1 = s2;
        }
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 14)
        {
            webview.getSettings().setTextZoom(95);
        } else
        {
            webview.getSettings().setTextSize(android.webkit.WebSettings.TextSize.NORMAL);
        }
        webview.loadData(s1, "text/html", "UTF-8");
        h.setContentView(linearlayout);
        h.show();
        ((CustomRobotoRegularTextView)linearlayout.findViewById(0x7f0a0139)).setOnClickListener(new v(this));
    }

    protected View getFooterView()
    {
        BrowseParam browseparam;
        l = new LinearLayout(context);
        l.setOrientation(1);
        if (FlipkartPreferenceManager.instance().isPincodeWidgetDismissed())
        {
            return l;
        }
        pincodeViewWidget = (PinCodeViewWidget)getPinCodeView();
        browseparam = (BrowseParam)fkContext.getParam();
        if ((fkContext == null || fkContext.isShowPin()) && !firstTime) goto _L2; else goto _L1
_L1:
        l.setVisibility(8);
_L4:
        l.addView(pincodeViewWidget);
        return l;
_L2:
        if (browseparam.getPincode() != null)
        {
            updatePincodeView(browseparam, browseparam.getPincode());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected View getHeader()
    {
        n = new GuidedSearchWidget(activity, this);
        if (fkContext != null && fkContext.getGuidedSearchResponse() != null)
        {
            n.updateViews(fkContext.getGuidedSearchResponse());
        } else
        {
            n.setVisibility(8);
        }
        titleLayout = (RelativeLayout)layoutInflater.inflate(0x7f0300b3, null);
        augmentedSearchCloseButton = (ImageView)titleLayout.findViewById(0x7f0a0219);
        augmentedSearchCloseButton.setVisibility(8);
        m = (LinearLayout)layoutInflater.inflate(0x7f030045, null);
        ((LinearLayout)m.findViewById(0x7f0a00e6)).setOnClickListener(new w(this));
        ((ImageView)m.findViewById(0x7f0a00e5)).setOnClickListener(new x(this));
        titleView = (TextView)titleLayout.findViewById(0x7f0a0125);
        titleView.getViewTreeObserver().addOnGlobalLayoutListener(new y(this));
        setBrowseParamTitle((BrowseParam)fkContext.getParam(), false);
        fkContext.setTitleViewText(titleView.getText().toString());
        titleLayout.setOnClickListener(null);
        nullResultViewWidget = (NullResultViewWidget)layoutInflater.inflate(0x7f030064, null);
        nullResultViewWidget.setOnClickOnViews(nullResultViewWidgetOnclickListener);
        k = (LinearLayout)layoutInflater.inflate(0x7f03005b, null);
        k.addView(n);
        k.addView(titleLayout);
        k.addView(m);
        k.addView(nullResultViewWidget);
        if (fkContext != null && !firstTime)
        {
            a();
            getToggleView().setVisibility(0);
        } else
        {
            getToggleView().setVisibility(8);
            m.setVisibility(8);
        }
        return k;
    }

    protected View getListViewHeader()
    {
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setOrientation(1);
        b = (LinearLayout)layoutInflater.inflate(0x7f030073, null);
        if (fkContext != null && !firstTime && !FlipkartPreferenceManager.instance().isPoppingAllRefineFragment().booleanValue())
        {
            if (fkContext.getSelectedFilterMap().get("Offers") != null && ((ArrayList)fkContext.getSelectedFilterMap().get("Offers")).size() != 0)
            {
                b();
            } else
            {
                b.setVisibility(8);
            }
        }
        linearlayout.addView(b);
        return linearlayout;
    }

    public ArrayList getParentStoreList()
    {
        return j;
    }

    public View getPinCodeView()
    {
        pincodeViewWidget = (PinCodeViewWidget)layoutInflater.inflate(0x7f03006a, null);
        pincodeViewWidget.setOnClickOnViews(p);
        return pincodeViewWidget;
    }

    public ArrayList getStoreList()
    {
        return i;
    }

    protected View getToggleView()
    {
        return super.getToggleView();
    }

    protected void initActionBar()
    {
        ActionBarView.setActionBarCustomView(activity, ActionBarState.Default_Back);
    }

    protected void initDataHandler()
    {
        super.initDataHandler();
        d = new B(this);
    }

    public void onClick(View view)
    {
        if (pincodeViewWidget != null && fkContext != null)
        {
            fkContext.setPinCodeWidgetState(pincodeViewWidget.getState());
        }
        boolean flag = view.getTag() instanceof GuidedSearchTag;
        boolean flag1 = false;
        if (flag)
        {
            GuidedSearchTag guidedsearchtag = (GuidedSearchTag)view.getTag();
            ArrayList arraylist = guidedsearchtag.getSearchKeywords();
            if (!StringUtils.isNullOrEmpty(arraylist))
            {
                for (Iterator iterator = arraylist.iterator(); iterator.hasNext();)
                {
                    String s = (String)iterator.next();
                    searchQuery = (new StringBuilder()).append(searchQuery).append(" ").append(s).toString();
                }

            }
            pushAndChangeContext(null, guidedsearchtag.getStorePath(), null, null, guidedsearchtag.getFilterParams(), searchQuery);
            flag1 = true;
        }
        if (!flag1)
        {
            super.onClick(view);
        }
    }

    protected void onClickOfAllFilters(boolean flag)
    {
        RefineByCategoryResponse refinebycategoryresponse = new RefineByCategoryResponse();
        refinebycategoryresponse.setParentStoreList(getParentStoreList());
        refinebycategoryresponse.setStoreList(getStoreList());
        refinebycategoryresponse.setFkContext(fkContext);
        homeFragmentHolderActivity.openFilterOptionsPage(refinebycategoryresponse, false, analyticData.getRequestId(), flag);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = super.onCreateView(layoutinflater, viewgroup, bundle);
        offerLimits = AppConfigUtils.getInstance().getShowOffersAtPosition();
        homeFragmentHolderActivity = (HomeFragmentHolderActivity)getActivity();
        initActionBar();
        if (FlipkartPreferenceManager.instance().isPoppingAllRefineFragment().booleanValue())
        {
            if (fkContext != null)
            {
                FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.ProductList);
                ArrayList arraylist = new ArrayList();
                Map map = fkContext.getFilterMap();
                if (fkContext.getSelectedFilterMap() != null)
                {
                    for (Iterator iterator = fkContext.getSelectedFilterMap().keySet().iterator(); iterator.hasNext();)
                    {
                        String s = (String)iterator.next();
                        String as[];
                        BrowseParam browseparam;
                        Map map1;
                        int i1;
                        if (map != null)
                        {
                            map1 = (Map)map.get(s);
                        } else
                        {
                            map1 = null;
                        }
                        i1 = 0;
                        while (i1 < ((ArrayList)fkContext.getSelectedFilterMap().get(s)).size()) 
                        {
                            String s1 = (String)((ArrayList)fkContext.getSelectedFilterMap().get(s)).get(i1);
                            if (map1 != null)
                            {
                                FacetData facetdata = (FacetData)map1.get(s1);
                                if (facetdata != null)
                                {
                                    arraylist.add(facetdata.getParams());
                                }
                            }
                            i1++;
                        }
                    }

                    as = new String[arraylist.size()];
                    if (fkContext.getParam() instanceof BrowseParam)
                    {
                        browseparam = (BrowseParam)fkContext.getParam();
                        pushAndChangeContext(fkContext.getPincode(), fkContext.getStoreID(), null, browseparam.getSortOption(), (String[])arraylist.toArray(as), null);
                    }
                }
            }
            FlipkartPreferenceManager.instance().saveIsPoppingAllRefineFragment(Boolean.valueOf(false));
        }
        return view;
    }

    public void onDestroy()
    {
        super.onDestroy();
        m = null;
        if (h != null)
        {
            h.dismiss();
        }
        h = null;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        analyticData.setPageTypeUtils(FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil());
    }

    protected void performPreDataCallTasks()
    {
        BrowseParam browseparam;
label0:
        {
            String s;
label1:
            {
                if ((fkContext.getParam() instanceof BrowseParam) && !FlipkartPreferenceManager.instance().isPincodeWidgetDismissed())
                {
                    browseparam = (BrowseParam)fkContext.getParam();
                    if (fkContext.isShowPin())
                    {
                        s = FlipkartPreferenceManager.instance().getUserPinCode();
                        if (StringUtils.isNullOrEmpty(s))
                        {
                            break label0;
                        }
                        String s1 = browseparam.getPincode();
                        if (StringUtils.isNullOrEmpty(s1))
                        {
                            break label1;
                        }
                        if (s1.equals(s))
                        {
                            break label0;
                        }
                        a(browseparam, s);
                    }
                }
                return;
            }
            if (!StringUtils.isNullOrEmpty(currPinCode))
            {
                if (!currPinCode.equals(s))
                {
                    a(browseparam, s);
                    return;
                }
            } else
            {
                a(browseparam, s);
                return;
            }
        }
        pincodeViewWidget.setState(fkContext.getPinCodeWidgetState(), browseparam);
    }

    protected void processExtras()
    {
        Bundle bundle = getArguments();
        if (fkContext == null)
        {
            fkContext = new FkProductListContext();
        }
        if (bundle != null)
        {
            BrowseParam browseparam = new BrowseParam();
            analyticData.setSearchType(bundle.getString("X-SEARCH-TYPE"));
            browseparam.setQuery(bundle.getString("SEARCH_EXTRAS_QUERY"));
            if (StringUtils.isNullOrEmpty(bundle.getString("SEARCH_EXTRAS_STORE")))
            {
                browseparam.setStoreId("search.flipkart.com");
                browseparam.setStoreName("");
            } else
            {
                browseparam.setStoreId(bundle.getString("SEARCH_EXTRAS_STORE"));
                browseparam.setStoreName(bundle.getString("SEARCH_EXTRAS_STORE_NAME"));
            }
            browseparam.setPincode(bundle.getString("SEARCH_EXTRAS_PINCODE"));
            browseparam.setFilters(bundle.getStringArray("PRODUCT_LIST_EXTRAS_FILTERS"));
            browseparam.setTags(bundle.getStringArray("PRODUCT_LIST_EXTRAS_TAGS"));
            browseparam.setViews(bundle.getStringArray("PRODUCT_LIST_EXTRAS_VIEWS"));
            browseparam.setTitle(bundle.getString("PRODUCT_LIST_EXTRAS_TITLE"));
            browseparam.setPath(bundle.getString("PRODUCT_LIST_EXTRAS_PATH"));
            browseparam.setSortOption(bundle.getString("PRODUCT_LIST_EXTRAS_SORT"));
            browseparam.setFindingMethod(TrackingHelper.getProductFindingMethod());
            browseparam.setEnableAugmentSearch(bundle.getBoolean("PRODUCT_LIST_EXTRAS_AUGMENT_SEARCH"));
            dataParam = browseparam;
            fkContext.setParam(dataParam);
        }
    }

    protected void pushAndChangeContext(String s, String s1, String s2, String s3, String as[], String s4)
    {
        if (fkContext.getParam() instanceof BrowseParam)
        {
            BrowseParam browseparam = (BrowseParam)fkContext.getParam();
            if (s1 != null)
            {
                browseparam.setStoreId(s1);
            }
            if (s != null)
            {
                browseparam.setPincode(s);
            }
            if (s3 != null)
            {
                browseparam.setSortOption(s3);
            }
            if (as != null)
            {
                browseparam.setFilters(as);
            }
            if (s4 != null)
            {
                browseparam.setQuery(s4);
            }
            fkContext.setParam(browseparam);
            dataParam = fkContext.getParam();
            fkContext.setGuidedSearchResponse(null);
            CrashLoggerUtils.pushAndUpdate((new StringBuilder("pushAndChangeContext with browseparams: ")).append(browseparam.toString()).toString());
        }
        if (this instanceof SearchListFragment)
        {
            FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.SearchListPage);
        } else
        {
            FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.ProductList);
        }
        fkContext.clearProducts();
        updateListAdapterVars();
        fkContext.setShowPin(false);
        setNoMoreDataToDownload(false);
        setOnSamePage(true);
        if (n != null)
        {
            n.setVisibility(8);
            n.clearAllViews();
        }
        if (!(fkContext.getParam() instanceof ProductsListParam))
        {
            titleView.setText("");
        }
        if (m != null)
        {
            m.setVisibility(8);
        }
        if (l != null)
        {
            l.setVisibility(8);
        }
        if (b != null)
        {
            b.setVisibility(8);
        }
        if (augmentedSearchCloseButton != null)
        {
            augmentedSearchCloseButton.setVisibility(8);
        }
        if (nullResultViewWidget != null)
        {
            nullResultViewWidget.removeAllViews();
        }
        if (getToggleView() != null)
        {
            getToggleView().setVisibility(8);
        }
        initActionBar();
        initData();
    }

    protected void setBrowseParamTitle(BrowseParam browseparam, boolean flag)
    {
        if (fkContext == null) goto _L2; else goto _L1
_L1:
        String s;
        setTitlePadding(false);
        s = "";
        if (fkContext.getStoreMetaInfo() != null)
        {
            s = fkContext.getStoreMetaInfo().getTitle();
        }
        if (StringUtils.isNullOrEmpty(fkContext.getTagTitle())) goto _L4; else goto _L3
_L3:
        if (fkContext.getTotalProductCount() == 0 && !flag)
        {
            titleView.setText((new StringBuilder()).append(fkContext.getTagTitle()).append(" ( Searching.. )").toString());
        } else
        if (StringUtils.isNullOrEmpty(s))
        {
            titleView.setText((new StringBuilder()).append(fkContext.getTagTitle()).append(" ( ").append(fkContext.getTotalProductCount()).append(" Results )").toString());
        } else
        {
            titleView.setText((new StringBuilder()).append(fkContext.getTagTitle()).append(" ( ").append(fkContext.getTotalProductCount()).append(" Results in ").append(s).append(" )").toString());
        }
_L6:
        titleView.setVisibility(0);
        fkContext.setTitleViewText(titleView.getText().toString());
_L2:
        return;
_L4:
        if (!StringUtils.isNullOrEmpty(browseparam.getTitle()))
        {
            titleView.setText(browseparam.getTitle());
        } else
        if (!StringUtils.isNullOrEmpty(browseparam.getQuery()))
        {
            if (fkContext.getTotalProductCount() == 0 && !flag)
            {
                titleView.setText((new StringBuilder()).append(browseparam.getQuery()).append(" ( Searching.. )").toString());
            } else
            if (StringUtils.isNullOrEmpty(s))
            {
                titleView.setText((new StringBuilder()).append(browseparam.getQuery()).append(" ( ").append(fkContext.getTotalProductCount()).append(" Results )").toString());
            } else
            {
                titleView.setText((new StringBuilder()).append(browseparam.getQuery()).append(" ( ").append(fkContext.getTotalProductCount()).append(" Results in ").append(s).append(" )").toString());
            }
        } else
        if (!StringUtils.isNullOrEmpty(s))
        {
            if (fkContext.getTotalProductCount() == 0 && !flag)
            {
                titleView.setText((new StringBuilder()).append(s).append(" ( Searching.. )").toString());
            } else
            {
                titleView.setText((new StringBuilder()).append(s).append(" ( ").append(fkContext.getTotalProductCount()).append(" Results )").toString());
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void setParentStoreList(ArrayList arraylist)
    {
        j = arraylist;
    }

    public void setStoreList(ArrayList arraylist)
    {
        i = arraylist;
    }

    protected void setTitlePadding(boolean flag)
    {
        if (flag)
        {
            if (context != null)
            {
                titleView.setPadding(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(18, context), ScreenMathUtils.dpToPx(10, context));
            } else
            {
                context = FlipkartApplication.getAppContext();
                titleView.setPadding(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(18, context), ScreenMathUtils.dpToPx(10, context));
            }
            titleView.setSingleLine(false);
            titleView.setMaxLines(10);
            return;
        }
        if (context != null)
        {
            titleView.setPadding(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context));
        } else
        {
            context = FlipkartApplication.getAppContext();
            titleView.setPadding(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context));
        }
        titleView.setSingleLine(true);
        titleView.setEllipsize(android.text.TextUtils.TruncateAt.END);
    }

    protected void updateFilters(ArrayList arraylist, ArrayList arraylist1)
    {
        if (fkContext == null)
        {
            return;
        }
        fkContext.setSortOptions(arraylist);
        fkContext.clearFilterMaps();
        fkContext.setFilterMaps(arraylist1);
        if (fkContext.getStoreMetaInfo() != null)
        {
            fkContext.setStoreID(fkContext.getStoreMetaInfo().getId());
        }
        if (fkContext.getSelectedFilterMap().get("Offers") != null && ((ArrayList)fkContext.getSelectedFilterMap().get("Offers")).size() != 0)
        {
            b();
            return;
        } else
        {
            b.setVisibility(8);
            return;
        }
    }

    public void updateHeader(HeaderParams headerparams)
    {
        if (FlipkartPreferenceManager.instance().isPincodeWidgetDismissed()) goto _L2; else goto _L1
_L1:
        if (!headerparams.isActionShowPin()) goto _L4; else goto _L3
_L3:
        l.setVisibility(0);
        updatePincodeView(headerparams.getBrowseParam(), headerparams.getPinCode());
        if (StringUtils.isNullOrEmpty(headerparams.getBrowseParam().getPincode()) && !firstTime)
        {
            pincodeViewWidget.setState(PinCodeWidgetState.EnterPin, null);
        }
_L2:
        if (getToggleView() != null && fkContext != null && fkContext.getProductIds() != null && fkContext.getProductIds().size() > 0)
        {
            getToggleView().setVisibility(0);
            showCueTips("ProdListPage");
        }
        if (headerparams.isActionUpdateTitle())
        {
            setBrowseParamTitle(headerparams.getBrowseParam(), headerparams.isResultReceived());
        }
        if (headerparams.isActionShowGuides())
        {
            n.clearAllViews();
            n.updateViews(headerparams.getGuidedSearchResponse());
        }
        if (headerparams.isActionUpdateSortAndFiltersLayout())
        {
            updateFilters(headerparams.getSortOptions(), headerparams.getFiltersInfo());
        }
        if (m != null && fkContext != null)
        {
            m.setVisibility(0);
            a();
        }
        return;
_L4:
        if (fkContext != null && !fkContext.isShowPin())
        {
            l.setVisibility(8);
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    protected void updatePincodeView(BrowseParam browseparam, String s)
    {
        if (pincodeViewWidget == null)
        {
            return;
        }
        if (!StringUtils.isNullOrEmpty(s))
        {
            if (browseAllProduct)
            {
                pincodeViewWidget.setState(PinCodeWidgetState.NotFoundShowAll, browseparam);
                browseAllProduct = false;
                return;
            } else
            {
                pincodeViewWidget.setState(PinCodeWidgetState.AvailableAtPin, browseparam);
                return;
            }
        } else
        {
            pincodeViewWidget.setState(PinCodeWidgetState.EnterPin, browseparam);
            return;
        }
    }

}
