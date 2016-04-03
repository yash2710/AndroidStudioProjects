// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.PageName;
import com.flipkart.android.analytics.PageType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.customwidget.WidgetAction;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.ProductInfoVDataHandler;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.fragments.model.ProductPageMoreSellerModel;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.response.customwidgetitemvalue.Action;
import com.flipkart.android.response.discovery.OmnitureData;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ProductPageMoreSellerBuilder;
import com.flipkart.android.utils.ProductSpecificSellerTypes;
import com.flipkart.android.utils.SellerTypeUtils;
import com.flipkart.android.utils.SellerTypes;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.android.webview.WebviewLauncher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, aE, aF

public class ProductPageMultipleSellersFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener
{

    ProductInfo a;
    View b;
    FkProductListContext c;
    ProductPageMoreSellerModel d;
    private ProductInfoVDataHandler e;
    private int f;
    private String g;
    private String h;
    private String i;
    private String j;
    private boolean k;

    public ProductPageMultipleSellersFragment()
    {
        a = null;
        b = null;
        f = 0;
        g = null;
        h = null;
        i = null;
        j = "";
        k = false;
    }

    private static void a(View view)
    {
        View view1 = (View)view.getParent();
_L4:
        View view2 = view1.findViewById(0x7f0a0196);
        if (view2 != null) goto _L2; else goto _L1
_L1:
        view1 = (View)view1.getParent();
        if (view1 != null) goto _L4; else goto _L3
_L3:
        View view3;
        View view4;
        view3 = view1;
        view4 = null;
_L6:
        if (view4 != null)
        {
            view4.setVisibility(0);
            View view5 = view3.findViewById(0x7f0a019b);
            view3.findViewById(0x7f0a019c).setVisibility(8);
            view5.setVisibility(8);
        }
        return;
_L2:
        view3 = view1;
        view4 = view2;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static boolean isValidIndianPin(String s)
    {
        while (s == null || s.length() != 6 || !s.matches("[0-9]+")) 
        {
            return false;
        }
        return true;
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

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    public void onClick(View view)
    {
        if (view.getTag() instanceof String) goto _L2; else goto _L1
_L1:
        if (view.getTag() instanceof Action)
        {
            WidgetAction.performAction((Action)view.getTag(), activity, PageTypeUtils.ProductPage);
        }
_L4:
        return;
_L2:
        String s;
        s = (String)view.getTag();
        if (s == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("clicked PPMultipleSellersFragment with tag :")).append(s).toString());
        if (s.contains("on_click_go_back"))
        {
            popFragmentStack();
            return;
        }
        if (!s.contains("open_particular_seller_page"))
        {
            break; /* Loop/switch isn't completed */
        }
        String s6 = (String)((TextView)view.findViewById(0x7f0a019f)).getText();
        if ((activity instanceof HomeFragmentHolderActivity) && c != null && f < c.getProductIds().size())
        {
            ProductInfo productinfo2 = c.getProductForId((String)c.getProductIds().get(f));
            if (productinfo2 != null)
            {
                String s7 = productinfo2.getSellerId(s6);
                if (s7 != null)
                {
                    ((HomeFragmentHolderActivity)activity).openSellerInfoPage(requestId, s7);
                    return;
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (!s.contains("buy_now"))
        {
            break; /* Loop/switch isn't completed */
        }
        String as[] = s.split("/");
        if (as.length > 1)
        {
            String s3 = as[1];
            if (s3 != null && c != null && f < c.getProductIds().size())
            {
                ProductInfo productinfo1 = c.getProductForId((String)c.getProductIds().get(f));
                if (productinfo1 != null)
                {
                    String s5 = productinfo1.getSellerListingId(s3);
                    if (s5 != null)
                    {
                        i = s5;
                    }
                }
            }
            ProductPageMoreSellerModel productpagemoresellermodel = d;
            boolean flag = false;
            if (productpagemoresellermodel != null)
            {
                Map map = d.getSwatchesMap();
                flag = false;
                if (map != null)
                {
                    boolean flag1 = map.containsKey("size");
                    flag = false;
                    if (flag1)
                    {
                        flag = true;
                    }
                }
            }
            if (flag && !k)
            {
                ToastMessageUtils.showErrorToastMessage("Please select a size first", activity, true);
                popFragmentStack();
                return;
            }
            if (!StringUtils.isNullOrEmpty(i))
            {
                ProductInfo productinfo = c.getProductForId(g);
                if (productinfo != null)
                {
                    String s4 = "";
                    if (productinfo.getOmnitureData() != null)
                    {
                        s4 = productinfo.getOmnitureData().getCategory();
                    }
                    ProductSpecificSellerTypes productspecificsellertypes = ProductSpecificSellerTypes.NONE;
                    SellerTypes sellertypes = SellerTypes.NONE;
                    if (c != null && !StringUtils.isNullOrEmpty(s3))
                    {
                        productspecificsellertypes = SellerTypeUtils.getSellerType(c.getProductModel(), productinfo.getMarketPlaceSeller(s3));
                        if (c.getProductModel() != null)
                        {
                            sellertypes = c.getProductModel().getSellerType();
                        }
                    }
                    WebviewLauncher.launchBuyNow(productinfo.getProductId(), i, s3, productspecificsellertypes, sellertypes, s4, view.getContext());
                    return;
                }
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
        if (s.contains("on_click_check_pincode"))
        {
            EditText edittext1 = (EditText)((View)view.getParent()).findViewById(0x7f0a0198);
            ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(edittext1.getWindowToken(), 0);
            String s1 = edittext1.getText().toString();
            if (s1 == null)
            {
                s1 = "";
            }
            if (isValidIndianPin(s1))
            {
                TrackingHelper.sendPincodeCheck(s1);
                FlipkartPreferenceManager.instance().saveUserPinCode(s1);
                j = s1;
                ArrayList arraylist = new ArrayList();
                String s2 = a.getProductId();
                arraylist.add(s2);
                HashMap hashmap = new HashMap();
                hashmap.put(s2, a.getPreferredListingId());
                HashMap hashmap1 = new HashMap();
                initRefresing();
                isRefreshing = true;
                e.fetchProductInfoForProducts(arraylist, hashmap, hashmap1, j, 0, analyticData);
                return;
            } else
            {
                ToastMessageUtils.showErrorToastMessage("Pin Code you have entered is invalid. Please retry.", activity, true);
                return;
            }
        }
        if (s.contains("on_click_change_pin"))
        {
            a(view);
            InputMethodManager inputmethodmanager = (InputMethodManager)activity.getSystemService("input_method");
            EditText edittext = (EditText)b.findViewById(0x7f0a0198);
            edittext.requestFocus();
            inputmethodmanager.showSoftInput(edittext, 2);
            return;
        }
        if (true) goto _L4; else goto _L6
_L6:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        if (c == null)
        {
            Bundle bundle1 = getArguments();
            if (bundle1 != null)
            {
                f = bundle1.getInt("PRODUCT_PAGE_SELECTED_INDEX");
                h = bundle1.getString("PRODUCT_PAGE_UUID");
                c = (FkProductListContext)ContextCache.getInstance().getResponse(h);
                k = bundle1.getBoolean("PRODUCT_PAGE_IS_SIZE_SELECTED");
                if (c != null)
                {
                    g = (String)c.getProductIds().get(f);
                }
            }
        }
        b = layoutinflater.inflate(0x7f030089, viewgroup, false);
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return b;
        }
        if (c == null)
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragmentNotImmediate();
            return b;
        }
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.ProductMoreSellerPage);
        ((Button)b.findViewById(0x7f0a0199)).setOnClickListener(this);
        ((TextView)b.findViewById(0x7f0a019b)).setOnClickListener(this);
        ((TextView)b.findViewById(0x7f0a019c)).setOnClickListener(this);
        e = new aE(this, 0);
        a = c.getProductForId((String)c.getProductIds().get(f));
        if (a != null)
        {
            TrackingHelper.sendPageView((new StringBuilder()).append(PageName.ProductSellerPage).append(":").append(a.getMainTitle()).toString(), PageType.ProductSeller);
            d = ProductPageMoreSellerModel.getModel(a, getActivity().getApplicationContext());
            try
            {
                TrackingHelper.sendProductPageMoreSellersViewEvent(a.getPreferredListingId(), a.getProductId(), c.getProductModel().getRequestId());
            }
            catch (Exception exception) { }
            ProductPageMoreSellerBuilder.buildProductPageMoreSeller(d, b, this, activity);
        }
        new aF(this);
        ActionBarView.setActionBarCustomView(activity, ActionBarState.Default_Back);
        return b;
    }

    public void onDestroy()
    {
        ToastMessageUtils.dismissToast(activity);
        super.onDestroy();
        if (analyticData != null)
        {
            analyticData.setPageTypeUtils(PageTypeUtils.ProductMoreSellerPage);
        }
        a = null;
        b = null;
        e = null;
        c = null;
        d = null;
    }

    public void onFragmentPopped()
    {
    }

    public void onFragmentPushed()
    {
    }

    public void share()
    {
        ProductInfo productinfo = c.getProductForId((String)c.getProductIds().get(f));
        String s = (new StringBuilder("http://www.flipkart.com/item/")).append(productinfo.getProductId()).toString();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        String s1 = productinfo.getVertical();
        if (s1 == null || s1.equalsIgnoreCase(""))
        {
            intent.putExtra("android.intent.extra.SUBJECT", "Check out this product on Flipkart!");
        } else
        {
            intent.putExtra("android.intent.extra.SUBJECT", (new StringBuilder("Check out this ")).append(s1).append(" on Flipkart!").toString());
        }
        intent.putExtra("android.intent.extra.TEXT", s);
        startActivity(Intent.createChooser(intent, "Choose..."));
    }
}
