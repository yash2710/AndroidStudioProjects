// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.AddCartLocation;
import com.flipkart.android.analytics.PageName;
import com.flipkart.android.analytics.PageType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.NullResultViewWidget;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.WishListVDataHandler;
import com.flipkart.android.datahandler.param.FooterParams;
import com.flipkart.android.datahandler.param.HeaderParams;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.fragments.model.ProductListItemModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.NullResultWidgetState;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.android.utils.WishListUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductListFragment, bI, bJ, bG, 
//            bH

public class WishListFragment extends ProductListFragment
{

    private View b;
    private TextView c;
    private LinearLayout d;
    private Button e;
    private Button f;

    public WishListFragment()
    {
    }

    private void a()
    {
        ProductsListParam productslistparam = (ProductsListParam)fkContext.getParam();
        if (productslistparam.getPageType() == PageTypeUtils.WishList && productslistparam.getProductIds() != null && productslistparam.getProductIds().size() > 0)
        {
            d.setVisibility(0);
            if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
            {
                e.setVisibility(8);
            } else
            {
                e.setVisibility(0);
            }
            productListLayoutParams.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(53, activity.getApplicationContext()));
            return;
        } else
        {
            d.setVisibility(8);
            productListLayoutParams.setMargins(0, 0, 0, 0);
            return;
        }
    }

    static void a(WishListFragment wishlistfragment, boolean flag, boolean flag1, String s, View view, String s1)
    {
        wishlistfragment.a(flag, flag1, s, view, s1);
    }

    private void a(String s)
    {
        if (s != null)
        {
            c.setText(s);
            if (context != null)
            {
                c.setPadding(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context));
            } else
            {
                context = FlipkartApplication.getAppContext();
                c.setPadding(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context));
            }
            c.setSingleLine(true);
            c.setEllipsize(android.text.TextUtils.TruncateAt.END);
            b.setVisibility(0);
            fkContext.setTitleViewText(c.getText().toString());
        }
    }

    private void a(boolean flag, boolean flag1, String s, View view, String s1)
    {
        if (fkContext == null)
        {
            return;
        }
        if (flag)
        {
            if (flag1)
            {
                (new WishListDao(activity)).deleteAll();
                fkContext.clearAllProductIds();
                updateListAdapterVars();
                ProductsListParam productslistparam1 = (ProductsListParam)fkContext.getParam();
                productslistparam1.setProductIds(null);
                a(productslistparam1.getTitle());
                d.setVisibility(8);
                buildErrorMessage(200, -1, "", "There are no items in your WishList");
                Toast.makeText(FlipkartApplication.getAppContext(), "Deleted All from WishList", 0).show();
                TrackingHelper.sendDeleteFromWishList("", null);
                return;
            }
            if (StringUtils.isNullOrEmpty(s1))
            {
                s1 = "Delete All from WishList Failed.Please try again";
            }
            ToastMessageUtils.showErrorToastMessage(s1, activity, true);
            return;
        }
        if (flag1)
        {
            fkContext.getProductIds().remove(s);
            updateListAdapterVars();
            ProductsListParam productslistparam = (ProductsListParam)fkContext.getParam();
            productslistparam.removeProdId(s);
            a(productslistparam.getTitle());
            if ((new WishListDao(activity)).totalEntries() == 0)
            {
                d.setVisibility(8);
                buildErrorMessage(200, -1, "", "There are no items in your WishList");
            }
            Toast.makeText(FlipkartApplication.getAppContext(), "Item deleted from WishList", 0).show();
            ProductListItemModel productlistitemmodel = (ProductListItemModel)getModelMap().get(s);
            String s2;
            if (productlistitemmodel != null)
            {
                s2 = productlistitemmodel.getCategory();
            } else
            {
                s2 = "";
            }
            TrackingHelper.sendDeleteFromWishList(s, s2);
            return;
        } else
        {
            ToastMessageUtils.showErrorToastMessage(s1, activity, false);
            WishListUtils.setWishListTagOnButtons(view, (new StringBuilder("on_click_delete_from_wishlist/")).append(s).toString(), PageTypeUtils.ProductList);
            return;
        }
    }

    public void buildErrorMessage(int i, int j, String s, String s1)
    {
        super.buildErrorMessage(i, j, s, s1);
        if (StringUtils.isNullOrEmpty(s1)) goto _L2; else goto _L1
_L1:
        nullResultViewWidget.setState(NullResultWidgetState.ShowContinueShopping, s1, "productListPage");
_L4:
        if (getToggleView() != null)
        {
            getToggleView().setVisibility(8);
        }
        nullResultViewWidget.setVisibility(0);
        return;
_L2:
        if (i != 200)
        {
            String s2 = getErrorMessage(i);
            if (!StringUtils.isNullOrEmpty(s2))
            {
                nullResultViewWidget.setState(NullResultWidgetState.ShowContinueShopping, s2, "productListPage");
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void deleteFromWishList(boolean flag, String s, View view, ProductInfo productinfo)
    {
        if (flag)
        {
            if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
            {
                (new bI(this, view)).deleteFromWishList(null, true, analyticData);
                return;
            } else
            {
                a(true, true, "", view, "");
                return;
            }
        }
        if (!FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            if (WishListUtils.deleteEntryFromWishListDB(s) != 0)
            {
                a(false, true, s, view, "");
                return;
            } else
            {
                a(false, false, s, view, "Delete from WishList Failed.Please try again");
                return;
            }
        } else
        {
            (new bJ(this, s, view)).deleteFromWishList(new String[] {
                s
            }, false, analyticData);
            WishListUtils.setWishListTagOnButtons(view, "deleting_from_wishlist", PageTypeUtils.ProductList);
            return;
        }
    }

    public volatile View getFooterView()
    {
        return getFooterView();
    }

    public ViewGroup getFooterView()
    {
        d = (LinearLayout)layoutInflater.inflate(0x7f0300c4, null);
        e = (Button)d.findViewById(0x7f0a023a);
        f = (Button)d.findViewById(0x7f0a023b);
        ProductsListParam productslistparam = (ProductsListParam)fkContext.getParam();
        f.setOnClickListener(this);
        f.setTag("on_click_clear_wishlist");
        e.setOnClickListener(this);
        e.setTag("on_click_save_to_wishlist");
        if (productslistparam.getPageType() == PageTypeUtils.WishList && productslistparam.getProductIds() != null && productslistparam.getProductIds().size() > 0)
        {
            d.setVisibility(0);
            if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
            {
                e.setVisibility(8);
            } else
            {
                e.setVisibility(0);
            }
            productListLayoutParams.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(53, activity.getApplicationContext()));
        } else
        {
            d.setVisibility(8);
            productListLayoutParams.setMargins(0, 0, 0, 0);
        }
        return d;
    }

    protected View getHeader()
    {
        b = layoutInflater.inflate(0x7f03005c, null);
        c = (TextView)b.findViewById(0x7f0a0125);
        if (fkContext != null && (fkContext.getParam() instanceof ProductsListParam))
        {
            a(((ProductsListParam)fkContext.getParam()).getTitle());
        }
        nullResultViewWidget = (NullResultViewWidget)layoutInflater.inflate(0x7f030064, null);
        nullResultViewWidget.setOnClickOnViews(nullResultViewWidgetOnclickListener);
        LinearLayout linearlayout = (LinearLayout)layoutInflater.inflate(0x7f03005b, null);
        linearlayout.addView(b);
        linearlayout.addView(nullResultViewWidget);
        return linearlayout;
    }

    public void onClick(View view)
    {
        if (isDisableClick) goto _L2; else goto _L1
_L1:
        Object obj = view.getTag();
        if (!(obj instanceof String)) goto _L2; else goto _L3
_L3:
        String s = (String)obj;
        if (!s.contains("on_click_save_to_wishlist")) goto _L5; else goto _L4
_L4:
        boolean flag;
        ((HomeFragmentHolderActivity)activity).doLogin();
        flag = true;
_L7:
        if (!flag)
        {
            super.onClick(view);
        }
        return;
_L5:
        if (s.contains("on_click_clear_wishlist"))
        {
            Dialog dialog = new Dialog(activity);
            dialog.setContentView(0x7f03002a);
            dialog.setTitle("Clear WishList");
            ((Button)dialog.findViewById(0x7f0a009f)).setOnClickListener(new bG(this, dialog));
            ((Button)dialog.findViewById(0x7f0a00a0)).setOnClickListener(new bH(this, dialog));
            dialog.show();
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
        if (s.contains("on_click_delete_from_wishlist"))
        {
            String as[] = s.split("/");
            if (as.length > 1)
            {
                String s1 = as[1];
                if (!StringUtils.isNullOrEmpty(s1) && !StringUtils.isNullOrEmpty(s1))
                {
                    deleteFromWishList(false, s1, view, fkContext.getProductForId(s1));
                    flag = true;
                    continue; /* Loop/switch isn't completed */
                }
            }
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = super.onCreateView(layoutinflater, viewgroup, bundle);
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.WishList);
        return view;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        analyticData.setPageTypeUtils(PageTypeUtils.WishList);
    }

    protected void performPreDataCallTasks()
    {
        if (!FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            refreshWishlistData((new WishListDao(FlipkartApplication.getAppContext())).getAllWishListPids());
        } else
        {
            refreshWishlistData(new ArrayList());
        }
        a();
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
            ArrayList arraylist = bundle.getStringArrayList("PRODUCT_LIST_EXTRAS_PRODUCTS");
            String s = bundle.getString("PRODUCT_LIST_EXTRAS_CUR_PRD");
            String s1 = bundle.getString("PRODUCT_LIST_EXTRAS_TITLE");
            String s2 = bundle.getString("PRODUCT_LIST_EXTRAS_PAGE_TYPE");
            TrackingHelper.sendPageView(PageName.Wishlist.name(), PageType.UserPage);
            addToCartLocation = AddCartLocation.WishListPage;
            dataParam = new ProductsListParam(arraylist, s, s1, PageTypeUtils.valueOf(s2));
            fkContext.setParam(dataParam);
        }
    }

    public void refreshWishlistData(ArrayList arraylist)
    {
        if (fkContext != null && fkContext.getParam() != null && (fkContext.getParam() instanceof ProductsListParam))
        {
            ProductsListParam productslistparam = (ProductsListParam)fkContext.getParam();
            if (productslistparam.getPageType() == PageTypeUtils.WishList)
            {
                if (nullResultViewWidget != null)
                {
                    nullResultViewWidget.removeAllViews();
                }
                fkContext.clearProducts();
                getModelMap().clear();
                updateListAdapterVars();
                a();
                setNoMoreDataToDownload(false);
                productslistparam.setProductIds(arraylist);
                a(productslistparam.getTitle());
            }
        }
    }

    public void updateFooter(FooterParams footerparams)
    {
        d.setVisibility(0);
        productListLayoutParams.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(53, activity.getApplicationContext()));
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            e.setVisibility(8);
            return;
        } else
        {
            e.setVisibility(0);
            showCueTips("WishListPage");
            return;
        }
    }

    public void updateHeader(HeaderParams headerparams)
    {
        if (headerparams.isActionUpdateTitle())
        {
            a(headerparams.getProductsListParam().getTitle());
        }
    }
}
