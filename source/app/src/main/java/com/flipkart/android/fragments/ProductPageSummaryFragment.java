// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.AddCartLocation;
import com.flipkart.android.analytics.PageName;
import com.flipkart.android.analytics.PageType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.cart.CartHandler;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.response.discovery.OmnitureData;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ProductPageBuilder;
import com.flipkart.android.utils.ProductPageSummaryBuilder;
import com.flipkart.android.utils.ProductSpecificSellerTypes;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.SellerTypes;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.android.webview.WebviewLauncher;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, aN, aM

public class ProductPageSummaryFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener
{

    int a;
    FkProductListContext b;
    private String c;
    private boolean d;
    private ProductPageModel e;

    public ProductPageSummaryFragment()
    {
        a = 0;
        c = null;
        d = false;
    }

    private boolean a()
    {
        if (e != null)
        {
            Map map = e.getSwatchesMap();
            if (map != null && map.containsKey("size"))
            {
                return true;
            }
        }
        return false;
    }

    public boolean handleBackPress()
    {
        CustomDialog.dismissDialog();
        ToastMessageUtils.dismissToast(activity);
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    public void onClick(View view)
    {
        String s = (String)view.getTag();
        if (s == null) goto _L2; else goto _L1
_L1:
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("clicked PPSummaryFragment with tag :")).append(s).toString());
        if (!s.contains("on_click_go_back")) goto _L4; else goto _L3
_L3:
        ((HomeFragmentHolderActivity)activity).popFragmentStack();
_L2:
        return;
_L4:
        if (!s.contains("add_to_cart"))
        {
            break; /* Loop/switch isn't completed */
        }
        String s5 = (String)b.getProductIds().get(a);
        if (CartHandler.isCartItem(s5))
        {
            ((HomeFragmentHolderActivity)activity).doShowCart();
            return;
        }
        if (a() && !d)
        {
            ToastMessageUtils.showErrorToastMessage("Please select a size first", activity, true);
            popFragmentStack();
            return;
        }
        ProductInfo productinfo1 = b.getProductForId(s5);
        if (productinfo1 != null)
        {
            String s6 = productinfo1.getPreferredListingId();
            String s7 = "";
            if (productinfo1.getPreferredSeller() != null)
            {
                s7 = productinfo1.getPreferredSeller().getSellerDisplayName();
            }
            SellerTypes sellertypes1 = SellerTypes.NONE;
            ProductSpecificSellerTypes productspecificsellertypes1 = ProductSpecificSellerTypes.NONE;
            if (b != null && b.getProductModel() != null)
            {
                sellertypes1 = b.getProductModel().getSellerType();
                productspecificsellertypes1 = b.getProductSpecificSellerType();
            }
            String s8 = "";
            if (productinfo1.getOmnitureData() != null)
            {
                s8 = productinfo1.getOmnitureData().getCategory();
            }
            ((HomeFragmentHolderActivity)activity).addToCart(s5, s6, s7, s8, productspecificsellertypes1, sellertypes1, new aN(this, view), AddCartLocation.ProductSummaryPage, null, new AnalyticData(((ProductInfo)b.getProductMap().get(s5)).getRequestId(), null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
            CustomRobotoRegularTextView customrobotoregulartextview = (CustomRobotoRegularTextView)view;
            customrobotoregulartextview.setText("Adding..");
            customrobotoregulartextview.setEnabled(false);
            return;
        }
        if (true) goto _L2; else goto _L5
_L5:
        if (s.contains("on_click_share_product"))
        {
            share(s, null);
            return;
        }
        if (s.contains("on_click_notifyme"))
        {
            performNotifyMe(b, a, view);
            return;
        }
        if (s.contains("buy_now"))
        {
            if (a() && !d)
            {
                ToastMessageUtils.showErrorToastMessage("Please select a size first", activity, true);
                popFragmentStack();
                return;
            }
            ProductInfo productinfo = b.getProductForId((String)b.getProductIds().get(a));
            if (productinfo != null)
            {
                String s1 = productinfo.getPreferredListingId();
                String s2 = productinfo.getProductId();
                String s3 = "";
                if (productinfo.getPreferredSeller() != null)
                {
                    s3 = productinfo.getPreferredSeller().getSellerDisplayName();
                }
                SellerTypes sellertypes = SellerTypes.NONE;
                ProductSpecificSellerTypes productspecificsellertypes = ProductSpecificSellerTypes.NONE;
                if (b != null && b.getProductModel() != null)
                {
                    sellertypes = b.getProductModel().getSellerType();
                    productspecificsellertypes = b.getProductSpecificSellerType();
                }
                String s4 = "";
                if (productinfo.getOmnitureData() != null)
                {
                    s4 = productinfo.getOmnitureData().getCategory();
                }
                if (!StringUtils.isNullOrEmpty(s1))
                {
                    WebviewLauncher.launchBuyNow(s2, s1, s3, productspecificsellertypes, sellertypes, s4, view.getContext());
                    return;
                }
            }
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        if (b == null)
        {
            Bundle bundle1 = getArguments();
            if (bundle1 != null)
            {
                a = bundle1.getInt("PRODUCT_PAGE_SELECTED_INDEX");
                bundle1.getString("PRODUCT_PAGE_SELECTED_PRODUCT");
                c = bundle1.getString("PRODUCT_PAGE_UUID");
                d = bundle1.getBoolean("PRODUCT_PAGE_IS_SIZE_SELECTED");
                b = (FkProductListContext)ContextCache.getInstance().getResponse(c);
                bundle1.getString("REQUEST_ID");
            }
        }
        View view = layoutinflater.inflate(0x7f0300a0, viewgroup, false);
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return view;
        }
        if (b == null || b.getProductModel() == null)
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragmentNotImmediate();
            return view;
        }
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.ProductSummaryPage);
        View view1 = view.findViewById(0x7f0a0177);
        View view2 = view.findViewById(0x7f0a01f7);
        e = b.getProductModel();
        TrackingHelper.sendPageView((new StringBuilder()).append(PageName.ProductSummaryPage.name()).append(":").append(e.getMainTitle()).toString(), PageType.ProductSummary);
        ProductPageSummaryBuilder.buildProductPageSumary(e, view, this);
        ProductPageBuilder.buildBottomBar(e, view1, this);
        if (view1.getVisibility() == 8)
        {
            android.widget.RelativeLayout.LayoutParams layoutparams1 = (android.widget.RelativeLayout.LayoutParams)view2.getLayoutParams();
            layoutparams1.setMargins(0, 0, 0, 0);
            view2.setLayoutParams(layoutparams1);
        } else
        {
            android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)view2.getLayoutParams();
            layoutparams.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(40, activity.getApplicationContext()));
            view2.setLayoutParams(layoutparams);
        }
        notifyMeDatahandler = new aM(this);
        ActionBarView.setActionBarCustomView(activity, ActionBarState.Default_Back);
        return view;
    }

    public void onDestroy()
    {
        ToastMessageUtils.dismissToast(activity);
        super.onDestroy();
        if (analyticData != null)
        {
            analyticData.setPageTypeUtils(PageTypeUtils.ProductSummaryPage);
        }
        b = null;
        e = null;
        notifyMeDatahandler = null;
    }

    public void onFragmentPopped()
    {
    }

    public void onFragmentPushed()
    {
    }
}
