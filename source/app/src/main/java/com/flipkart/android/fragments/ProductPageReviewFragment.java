// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.flipkart.android.datahandler.ProductUgcVDataHandler;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.fragments.model.ProductPageUgcModel;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.response.discovery.OmnitureData;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.ugc.UGCResponse;
import com.flipkart.android.response.ugc.UGCReview;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ProductPageBuilder;
import com.flipkart.android.utils.ProductPageReviewContext;
import com.flipkart.android.utils.ProductPageUgcBuilder;
import com.flipkart.android.utils.ProductSpecificSellerTypes;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.SellerTypes;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.android.webview.WebviewLauncher;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, aJ, aG, aH, 
//            aI

public class ProductPageReviewFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener
{

    HomeFragmentHolderActivity a;
    FkProductListContext b;
    int c;
    private UGCResponse d;
    private String e;
    private FrameLayout f;
    private View g;
    private ListView h;
    private String i;
    private Spinner j;
    private RelativeLayout k;
    private String l;
    private ProductInfo m;
    private Context n;
    private ProductUgcVDataHandler o;
    private boolean p;
    protected android.widget.FrameLayout.LayoutParams productListLayoutParams;
    private long q;
    private String r;
    private FkLoadingDialog s;
    private View t;
    private ProductPageReviewContext u;
    private ProductPageModel v;

    public ProductPageReviewFragment()
    {
        d = null;
        e = null;
        i = null;
        l = "MOST_USEFUL";
        m = null;
        a = null;
        b = null;
        n = null;
        o = null;
        p = false;
        q = 0L;
        c = 0;
        r = null;
    }

    static UGCResponse a(ProductPageReviewFragment productpagereviewfragment, UGCResponse ugcresponse)
    {
        productpagereviewfragment.d = ugcresponse;
        return ugcresponse;
    }

    static String a(ProductPageReviewFragment productpagereviewfragment, String s1)
    {
        productpagereviewfragment.i = s1;
        return s1;
    }

    static void a(ProductPageReviewFragment productpagereviewfragment)
    {
        if (productpagereviewfragment.f != null)
        {
            ProgressBar progressbar = (ProgressBar)productpagereviewfragment.f.findViewById(0x7f0a008b);
            if (progressbar != null)
            {
                progressbar.setVisibility(8);
            }
        }
    }

    private boolean a()
    {
        if (v != null)
        {
            Map map = v.getSwatchesMap();
            if (map != null && map.containsKey("size"))
            {
                return true;
            }
        }
        return false;
    }

    static FkLoadingDialog b(ProductPageReviewFragment productpagereviewfragment)
    {
        return productpagereviewfragment.s;
    }

    static String b(ProductPageReviewFragment productpagereviewfragment, String s1)
    {
        productpagereviewfragment.l = s1;
        return s1;
    }

    static UGCResponse c(ProductPageReviewFragment productpagereviewfragment)
    {
        return productpagereviewfragment.d;
    }

    static void d(ProductPageReviewFragment productpagereviewfragment)
    {
        if (productpagereviewfragment.d != null && productpagereviewfragment.m != null && productpagereviewfragment.m.getProductId() != null)
        {
            UGCReview ugcreview = (UGCReview)productpagereviewfragment.d.getReview().get(productpagereviewfragment.m.getProductId());
            if (ugcreview != null)
            {
                ArrayList arraylist = ugcreview.getReviewLst();
                productpagereviewfragment.u.getReviewList().clear();
                productpagereviewfragment.u.getReviewList().addAll(arraylist);
                productpagereviewfragment.u.setOption(productpagereviewfragment.i);
                productpagereviewfragment.u.setProductId(productpagereviewfragment.m.getProductId());
                productpagereviewfragment.u.setTotalReviewCount(productpagereviewfragment.q);
                if (arraylist.size() > 0)
                {
                    productpagereviewfragment.k.setVisibility(0);
                    ProductPageUgcModel productpageugcmodel = ProductPageUgcModel.getModel(arraylist, productpagereviewfragment.n);
                    productpageugcmodel.setProductPageReviewContext(productpagereviewfragment.u);
                    ProductPageUgcBuilder.updateVars();
                    ProductPageUgcBuilder.buildProductPageUgc(productpageugcmodel, productpagereviewfragment.f);
                    return;
                }
            }
        }
        productpagereviewfragment.k.setVisibility(8);
    }

    static Spinner e(ProductPageReviewFragment productpagereviewfragment)
    {
        return productpagereviewfragment.j;
    }

    static String f(ProductPageReviewFragment productpagereviewfragment)
    {
        return productpagereviewfragment.i;
    }

    static String g(ProductPageReviewFragment productpagereviewfragment)
    {
        return productpagereviewfragment.l;
    }

    static String h(ProductPageReviewFragment productpagereviewfragment)
    {
        return null;
    }

    static FrameLayout i(ProductPageReviewFragment productpagereviewfragment)
    {
        return productpagereviewfragment.f;
    }

    static ProductInfo j(ProductPageReviewFragment productpagereviewfragment)
    {
        return productpagereviewfragment.m;
    }

    static ProductUgcVDataHandler k(ProductPageReviewFragment productpagereviewfragment)
    {
        return productpagereviewfragment.o;
    }

    public boolean handleBackPress()
    {
        CustomDialog.dismissDialog();
        ToastMessageUtils.dismissToast(a);
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    public void onClick(View view)
    {
        String s1 = (String)view.getTag();
        if (s1 == null) goto _L2; else goto _L1
_L1:
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("clicked PPReviewFragment with tag :")).append(s1).toString());
        if (!s1.contains("on_click_go_back")) goto _L4; else goto _L3
_L3:
        a.popFragmentStack();
_L2:
        return;
_L4:
        if (!s1.contains("add_to_cart"))
        {
            break; /* Loop/switch isn't completed */
        }
        String s5 = (String)b.getProductIds().get(c);
        if (CartHandler.isCartItem(s5))
        {
            a.doShowCart();
            return;
        }
        if (a() && !p)
        {
            ToastMessageUtils.showErrorToastMessage("Please select a size first", a, true);
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
            a.addToCart(s5, s6, s7, s8, productspecificsellertypes1, sellertypes1, new aJ(this, view), AddCartLocation.ProductReviewPage, null, new AnalyticData(((ProductInfo)b.getProductMap().get(s5)).getRequestId(), null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
            CustomRobotoRegularTextView customrobotoregulartextview = (CustomRobotoRegularTextView)view;
            customrobotoregulartextview.setText("Adding..");
            customrobotoregulartextview.setEnabled(false);
            return;
        }
        if (true) goto _L2; else goto _L5
_L5:
        if (s1.contains("on_click_share_product"))
        {
            share(s1, null);
            return;
        }
        if (s1.contains("on_click_notifyme"))
        {
            performNotifyMe(b, c, view);
            return;
        }
        if (s1.contains("buy_now"))
        {
            if (a() && !p)
            {
                ToastMessageUtils.showErrorToastMessage("Please select a size first", a, true);
                popFragmentStack();
                return;
            }
            ProductInfo productinfo = b.getProductForId((String)b.getProductIds().get(c));
            if (productinfo != null)
            {
                String s2 = productinfo.getPreferredListingId();
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
                if (!StringUtils.isNullOrEmpty(s2))
                {
                    WebviewLauncher.launchBuyNow(productinfo.getProductId(), s2, s3, productspecificsellertypes, sellertypes, s4, view.getContext());
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
        n = getActivity().getApplicationContext();
        a = (HomeFragmentHolderActivity)getActivity();
        u = new ProductPageReviewContext();
        if (b == null)
        {
            Bundle bundle1 = getArguments();
            if (bundle1 != null)
            {
                c = bundle1.getInt("PRODUCT_PAGE_SELECTED_INDEX");
                bundle1.getString("PRODUCT_PAGE_SELECTED_PRODUCT");
                r = bundle1.getString("PRODUCT_PAGE_UUID");
                b = (FkProductListContext)ContextCache.getInstance().getResponse(r);
                p = bundle1.getBoolean("PRODUCT_PAGE_IS_SIZE_SELECTED");
                q = bundle1.getLong("PRODUCT_PAGE_TOTAL_REVIEW_COUNT");
            }
        }
        ProductPageUgcBuilder.updateVars();
        f = (FrameLayout)layoutinflater.inflate(0x7f030094, viewgroup, false);
        h = (ListView)f.findViewById(0x7f0a01d7);
        g = layoutinflater.inflate(0x7f030095, h, false);
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return f;
        }
        if (b == null || b.getProductModel() == null)
        {
            a.loadHomeFragmentNotImmediate();
            return f;
        }
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.ProductReviewPage);
        v = b.getProductModel();
        m = b.getProductForId((String)b.getProductIds().get(c));
        j = (Spinner)g.findViewById(0x7f0a01dd);
        k = (RelativeLayout)g.findViewById(0x7f0a01dc);
        ArrayAdapter arrayadapter = ArrayAdapter.createFromResource(f.getContext(), 0x7f070004, 0x7f0300bd);
        arrayadapter.setDropDownViewResource(0x1090009);
        j.setAdapter(arrayadapter);
        t = f.findViewById(0x7f0a0177);
        int i1;
        ListView listview;
        RelativeLayout relativelayout;
        if (m != null)
        {
            TrackingHelper.sendPageView((new StringBuilder()).append(PageName.ProductReviewPage.name()).append(":").append(m.getMainTitle()).toString(), PageType.ProductReview);
            ProductPageBuilder.buildProductPageUgc(m, g, this);
            ProductPageBuilder.buildBottomBar(m, t, this);
            android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(53, n), 80);
            t.setLayoutParams(layoutparams);
        } else
        {
            j.setVisibility(8);
        }
        notifyMeDatahandler = new aG(this);
        if (i == null)
        {
            i = "MOST_USEFUL";
        }
        i1 = Math.min(10, (int)q);
        if (i1 <= 0) goto _L2; else goto _L1
_L1:
        h.addHeaderView(g);
        listview = h;
        relativelayout = (RelativeLayout)LayoutInflater.from(f.getContext()).inflate(0x7f0300ad, null);
        relativelayout.setVisibility(8);
        listview.addFooterView(relativelayout);
        s = new FkLoadingDialog(a);
        if (s != null && !s.isShowing())
        {
            s.showDlg("", "Loading...", null, true);
        }
        o = new aH(this, m.getProductId(), i, 0, i1);
_L3:
        j.setOnItemSelectedListener(new aI(this));
_L4:
        ActionBarView.setActionBarCustomView(a, ActionBarState.Default_Back);
        return f;
_L2:
        f.addView(g);
        k.setVisibility(8);
          goto _L3
        Exception exception;
        exception;
          goto _L4
    }

    public void onDestroy()
    {
        ToastMessageUtils.dismissToast(a);
        super.onDestroy();
        if (analyticData != null)
        {
            analyticData.setPageTypeUtils(PageTypeUtils.ProductReviewPage);
        }
        d = null;
        f = null;
        i = null;
        j = null;
        m = null;
        v = null;
        b = null;
        n = null;
        notifyMeDatahandler = null;
        u = null;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        if (o != null)
        {
            o.cancelRequests();
        }
        ProductPageUgcBuilder.destroyAdapter();
    }

    public void onFragmentPopped()
    {
    }

    public void onFragmentPushed()
    {
    }

    public void onPause()
    {
        FkLogger.debug("ProductUgcFragment", "on pause called");
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
    }
}
