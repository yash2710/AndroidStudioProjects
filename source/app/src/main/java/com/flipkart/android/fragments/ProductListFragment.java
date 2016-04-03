// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.AddCartLocation;
import com.flipkart.android.analytics.ImageOrientation;
import com.flipkart.android.analytics.ProductFindingMethod;
import com.flipkart.android.analytics.ProductListViewType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.cart.CartHandler;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.NullResultViewWidget;
import com.flipkart.android.customviews.PinCodeViewWidget;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SearchAndProdInfoDataHandler;
import com.flipkart.android.datahandler.SearchAndProdInfoDataHandlerImpl;
import com.flipkart.android.datahandler.param.BaseDataHandlerParam;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.datahandler.param.FooterParams;
import com.flipkart.android.datahandler.param.HeaderParams;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.fragments.model.ProductListItemModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.response.discovery.StoreMetaInfo;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;
import com.flipkart.android.response.productInfo.ProductAvailabilityDetails;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.PinCodeWidgetState;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.android.utils.WishListUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, ProductListFragmentUpdateListener, O, Q, 
//            W, R, WishListFragment, U, 
//            V, Y, T, X, 
//            P

public class ProductListFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener, ProductListFragmentUpdateListener
{

    public static final String PRODUCT_LIST_EXTRAS_AUGMENT_SEARCH = "PRODUCT_LIST_EXTRAS_AUGMENT_SEARCH";
    public static final String PRODUCT_LIST_EXTRAS_COUNT = "PRODUCT_LIST_EXTRAS_COUNT";
    public static final String PRODUCT_LIST_EXTRAS_CUR_PRD = "PRODUCT_LIST_EXTRAS_CUR_PRD";
    public static final String PRODUCT_LIST_EXTRAS_FILTERS = "PRODUCT_LIST_EXTRAS_FILTERS";
    public static final String PRODUCT_LIST_EXTRAS_PAGE_TYPE = "PRODUCT_LIST_EXTRAS_PAGE_TYPE";
    public static final String PRODUCT_LIST_EXTRAS_PATH = "PRODUCT_LIST_EXTRAS_PATH";
    public static final String PRODUCT_LIST_EXTRAS_PRODUCTS = "PRODUCT_LIST_EXTRAS_PRODUCTS";
    public static final String PRODUCT_LIST_EXTRAS_SCREEN_TYPE = "PRODUCT_LIST_EXTRAS_SCREEN_TYPE";
    public static final String PRODUCT_LIST_EXTRAS_SORT = "PRODUCT_LIST_EXTRAS_SORT";
    public static final String PRODUCT_LIST_EXTRAS_START = "PRODUCT_LIST_EXTRAS_START";
    public static final String PRODUCT_LIST_EXTRAS_TAGS = "PRODUCT_LIST_EXTRAS_TAGS";
    public static final String PRODUCT_LIST_EXTRAS_TITLE = "PRODUCT_LIST_EXTRAS_TITLE";
    public static final String PRODUCT_LIST_EXTRAS_VIEWS = "PRODUCT_LIST_EXTRAS_VIEWS";
    public static final String PRODUCT_LIST_SEARCH_PATH = "Search";
    public static final String SEARCH_EXTRAS_PINCODE = "SEARCH_EXTRAS_PINCODE";
    public static final String SEARCH_EXTRAS_QUERY = "SEARCH_EXTRAS_QUERY";
    public static final String SEARCH_EXTRAS_STORE = "SEARCH_EXTRAS_STORE";
    public static final String SEARCH_EXTRAS_STORE_NAME = "SEARCH_EXTRAS_STORE_NAME";
    public static final String SEARCH_TYPE = "X-SEARCH-TYPE";
    private static boolean r = false;
    private LinearLayout A;
    private Runnable B;
    private float C;
    private android.view.View.OnTouchListener D;
    Handler a;
    protected AddCartLocation addToCartLocation;
    private boolean b;
    protected boolean browseAllProduct;
    private ProductListViewType c;
    protected Context context;
    protected String currPinCode;
    private boolean d;
    protected BaseDataHandlerParam dataParam;
    private boolean e;
    private Y f;
    protected boolean firstTime;
    protected FkProductListContext fkContext;
    private View g;
    private View h;
    private PopupWindow i;
    protected boolean isDisableClick;
    public boolean isFirstLanding;
    protected boolean isNullSearchPage;
    private ImageLoader j;
    private View k;
    private Map l;
    protected LayoutInflater layoutInflater;
    private float m;
    private boolean n;
    public boolean noConnection;
    public NullResultViewWidget nullResultViewWidget;
    protected android.view.View.OnClickListener nullResultViewWidgetOnclickListener;
    private int o;
    protected int offerLimits;
    private ImageOrientation p;
    protected PinCodeViewWidget pincodeViewWidget;
    protected ListView productList;
    protected android.widget.FrameLayout.LayoutParams productListLayoutParams;
    private boolean q;
    private TextView s;
    protected SearchAndProdInfoDataHandler searchAndProdDataHandler;
    private View t;
    private RelativeLayout u;
    private int v;
    private int w;
    private boolean x;
    private int y;
    private boolean z;

    public ProductListFragment()
    {
        noConnection = false;
        isDisableClick = false;
        b = false;
        c = ProductListViewType.List;
        d = false;
        e = false;
        addToCartLocation = AddCartLocation.ProductListPage;
        isNullSearchPage = false;
        k = null;
        firstTime = true;
        m = 150F;
        n = false;
        browseAllProduct = false;
        currPinCode = "";
        o = 0;
        q = false;
        offerLimits = 7;
        v = 0;
        w = 0;
        x = false;
        y = 0;
        z = false;
        isFirstLanding = true;
        B = new O(this);
        C = 0.0F;
        D = new Q(this);
        nullResultViewWidgetOnclickListener = new W(this);
    }

    static float a(ProductListFragment productlistfragment, float f1)
    {
        productlistfragment.C = f1;
        return f1;
    }

    static int a(ProductListFragment productlistfragment, int i1)
    {
        productlistfragment.w = i1;
        return i1;
    }

    static TextView a(ProductListFragment productlistfragment)
    {
        return productlistfragment.s;
    }

    static ImageOrientation a(ProductListFragment productlistfragment, ImageOrientation imageorientation)
    {
        productlistfragment.p = imageorientation;
        return imageorientation;
    }

    private void a()
    {
        if (r)
        {
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, -g.getMeasuredHeight(), 0.0F);
            translateanimation.setDuration(550L);
            translateanimation.setInterpolator(new LinearInterpolator());
            translateanimation.setFillAfter(true);
            g.setVisibility(0);
            g.bringToFront();
            g.startAnimation(translateanimation);
            r = false;
        }
    }

    static boolean a(ProductListFragment productlistfragment, boolean flag)
    {
        productlistfragment.z = true;
        return true;
    }

    static boolean a(boolean flag)
    {
        r = false;
        return false;
    }

    static float b(ProductListFragment productlistfragment, float f1)
    {
        productlistfragment.m = 110F;
        return 110F;
    }

    static View b(ProductListFragment productlistfragment)
    {
        return productlistfragment.g;
    }

    private void b()
    {
        if (i != null)
        {
            i.setFocusable(false);
            i.dismiss();
            i = null;
        }
    }

    static void b(ProductListFragment productlistfragment, int i1)
    {
        if (productlistfragment.fkContext == null || productlistfragment.s == null || productlistfragment.noConnection)
        {
            return;
        }
        if (i1 > productlistfragment.fkContext.getTotalProductCount())
        {
            i1 = productlistfragment.fkContext.getTotalProductCount();
        }
        try
        {
            if (productlistfragment.s.getVisibility() == 8)
            {
                productlistfragment.s.setVisibility(0);
            }
            productlistfragment.a.removeCallbacks(productlistfragment.B);
            productlistfragment.s.setText((new StringBuilder()).append(i1).append(" of ").append(productlistfragment.fkContext.getTotalProductCount()).toString());
            productlistfragment.a.postDelayed(productlistfragment.B, 1000L);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    static boolean b(ProductListFragment productlistfragment, boolean flag)
    {
        productlistfragment.x = flag;
        return flag;
    }

    static int c(ProductListFragment productlistfragment, int i1)
    {
        productlistfragment.y = i1;
        return i1;
    }

    private void c()
    {
        ImageView imageview;
label0:
        {
            imageview = (ImageView)u.findViewById(0x7f0a016b);
            if (imageview != null)
            {
                if (c != ProductListViewType.Full)
                {
                    break label0;
                }
                imageview.setImageResource(0x7f02010d);
            }
            return;
        }
        if (c == ProductListViewType.List)
        {
            imageview.setImageResource(0x7f0200f8);
            return;
        } else
        {
            imageview.setImageResource(0x7f0200f4);
            return;
        }
    }

    static boolean c(ProductListFragment productlistfragment)
    {
        return productlistfragment.x;
    }

    static boolean c(ProductListFragment productlistfragment, boolean flag)
    {
        productlistfragment.q = false;
        return false;
    }

    static float d(ProductListFragment productlistfragment)
    {
        return productlistfragment.C;
    }

    static int d(ProductListFragment productlistfragment, int i1)
    {
        productlistfragment.v = i1;
        return i1;
    }

    static boolean d(ProductListFragment productlistfragment, boolean flag)
    {
        productlistfragment.b = false;
        return false;
    }

    static int e(ProductListFragment productlistfragment, int i1)
    {
        int j1 = 1 + productlistfragment.v;
        productlistfragment.v = j1;
        return j1;
    }

    static void e(ProductListFragment productlistfragment)
    {
        if (!r)
        {
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, -productlistfragment.g.getMeasuredHeight());
            translateanimation.setDuration(550L);
            translateanimation.setInterpolator(new LinearInterpolator());
            translateanimation.setFillAfter(true);
            translateanimation.setAnimationListener(new R(productlistfragment));
            productlistfragment.g.startAnimation(translateanimation);
            r = true;
        }
    }

    static void f(ProductListFragment productlistfragment)
    {
        productlistfragment.a();
    }

    static RelativeLayout g(ProductListFragment productlistfragment)
    {
        return productlistfragment.u;
    }

    static View h(ProductListFragment productlistfragment)
    {
        return productlistfragment.h;
    }

    static LinearLayout i(ProductListFragment productlistfragment)
    {
        return productlistfragment.A;
    }

    static Y j(ProductListFragment productlistfragment)
    {
        return productlistfragment.f;
    }

    static ProductListViewType k(ProductListFragment productlistfragment)
    {
        return productlistfragment.c;
    }

    static boolean l(ProductListFragment productlistfragment)
    {
        return productlistfragment.b;
    }

    static int m(ProductListFragment productlistfragment)
    {
        return productlistfragment.v;
    }

    static boolean n(ProductListFragment productlistfragment)
    {
        return productlistfragment.z;
    }

    static void o(ProductListFragment productlistfragment)
    {
        productlistfragment.b();
    }

    static Map p(ProductListFragment productlistfragment)
    {
        return productlistfragment.l;
    }

    static ImageLoader q(ProductListFragment productlistfragment)
    {
        return productlistfragment.j;
    }

    static PopupWindow r(ProductListFragment productlistfragment)
    {
        return productlistfragment.i;
    }

    public static void removeOnGlobalLayoutListener(View view, android.view.ViewTreeObserver.OnGlobalLayoutListener ongloballayoutlistener)
    {
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() < 16)
        {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(ongloballayoutlistener);
            return;
        } else
        {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(ongloballayoutlistener);
            return;
        }
    }

    static int s(ProductListFragment productlistfragment)
    {
        return productlistfragment.y;
    }

    static void t(ProductListFragment productlistfragment)
    {
        productlistfragment.s = (TextView)productlistfragment.k.findViewById(0x7f0a016c);
        if (productlistfragment.s != null)
        {
            productlistfragment.s.bringToFront();
        }
        if (productlistfragment.fkContext != null && productlistfragment.fkContext.isShowPin())
        {
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)productlistfragment.s.getLayoutParams();
            layoutparams.bottomMargin = ScreenMathUtils.dpToPx(55, productlistfragment.context);
            productlistfragment.s.setLayoutParams(layoutparams);
        }
    }

    static float u(ProductListFragment productlistfragment)
    {
        return productlistfragment.m;
    }

    static boolean v(ProductListFragment productlistfragment)
    {
        return productlistfragment.q;
    }

    static ImageOrientation w(ProductListFragment productlistfragment)
    {
        return productlistfragment.p;
    }

    public void buildErrorMessage(int i1, int j1, String s1, String s2)
    {
        if (productList != null)
        {
            productList.setVisibility(8);
        }
    }

    public int getAnimate()
    {
        return o;
    }

    public String getErrorMessage(int i1)
    {
        return CustomDialog.getErrorMessage(i1);
    }

    public FkProductListContext getFkContext()
    {
        return fkContext;
    }

    protected View getFooterView()
    {
        return h;
    }

    protected View getHeader()
    {
        return null;
    }

    protected View getListViewHeader()
    {
        return t;
    }

    public Map getModelMap()
    {
        return l;
    }

    public boolean getNullSearchPageValue()
    {
        return isNullSearchPage;
    }

    public Y getProductListAdapter()
    {
        return f;
    }

    protected View getToggleView()
    {
        return u;
    }

    public boolean handleBackPress()
    {
        if (searchAndProdDataHandler != null)
        {
            searchAndProdDataHandler.cancelFetch();
        }
        if (fkContext != null)
        {
            fkContext.clearFilterMaps();
            fkContext.clearProducts();
            fkContext.setPinCodeWidgetState(PinCodeWidgetState.None);
            fkContext.setShowPin(false);
            fkContext = null;
        }
        b();
        currPinCode = "";
        CustomDialog.dismissDialog();
        ToastMessageUtils.dismissToast(activity);
        CrashLoggerUtils.pushAndUpdate("pressing back from productListFragment.");
        super.handleBackPress();
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    protected void initData()
    {
        if (fkContext != null && fkContext.getProductsCount() == 0)
        {
            setRefreshFiltersView(true);
            if (this instanceof WishListFragment)
            {
                analyticData.setIsUserClick(true);
            }
            triggerRequest();
        }
    }

    protected void initDataHandler()
    {
        searchAndProdDataHandler = new SearchAndProdInfoDataHandlerImpl(dataParam, 1, this);
        super.baseVDataHandler = searchAndProdDataHandler;
    }

    public boolean isGridLayout()
    {
        return c == ProductListViewType.Grid;
    }

    public boolean isNoMoreDataToDownload()
    {
        return b;
    }

    public boolean isOnSamePage()
    {
        return d;
    }

    public boolean isRefreshFiltersView()
    {
        return e;
    }

    public boolean isToNotify()
    {
        return q;
    }

    public void onClick(View view)
    {
        if (isDisableClick) goto _L2; else goto _L1
_L1:
        Object obj = view.getTag();
        if (!(obj instanceof String)) goto _L2; else goto _L3
_L3:
        String s1;
        s1 = (String)obj;
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("clicking_on_product_list with view tag =")).append(s1).toString());
        if (!s1.contains("on_click_product_list_item")) goto _L5; else goto _L4
_L4:
        String as6[];
        TrackingHelper.sendTotalProductViewed(y);
        isDisableClick = true;
        currPinCode = FlipkartPreferenceManager.instance().getUserPinCode();
        as6 = s1.split("/");
        if (as6.length <= 1) goto _L2; else goto _L6
_L6:
        String s13;
        String s14;
        UUID uuid;
        s13 = as6[1];
        s14 = as6[2];
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("feteching product info for : ")).append(s13).toString());
        uuid = UUID.randomUUID();
        FkProductListContext fkproductlistcontext1 = fkContext.clone();
        FkProductListContext fkproductlistcontext = fkproductlistcontext1;
_L7:
        ContextCache.getInstance().putResponse(uuid.toString(), fkproductlistcontext);
        if ((activity instanceof HomeFragmentHolderActivity) && fkContext != null)
        {
            Exception exception;
            if (fkContext.getParam() instanceof BrowseParam)
            {
                BrowseParam browseparam1 = (BrowseParam)fkContext.getParam();
                if (browseparam1 != null)
                {
                    TrackingHelper.setProductFindingMethod(browseparam1.getFindingMethod());
                    if (!StringUtils.isNullOrEmpty(browseparam1.getPath()) && browseparam1.getPath().equals("Search"))
                    {
                        TrackingHelper.sendProductClickedOnSearchPage();
                        if (!n)
                        {
                            TrackingHelper.sendSearchSuccessful();
                            n = true;
                        }
                    }
                }
            } else
            {
                ProductsListParam productslistparam = (ProductsListParam)fkContext.getParam();
                if (productslistparam != null)
                {
                    if (productslistparam.getPageType() == PageTypeUtils.WishList)
                    {
                        TrackingHelper.setProductFindingMethod(ProductFindingMethod.Wishlist.name());
                    } else
                    if (productslistparam.getPageType() == PageTypeUtils.HomePageRecommendation)
                    {
                        TrackingHelper.setProductFindingMethod(ProductFindingMethod.Recommendation.name());
                        TrackingHelper.sendRecommendationInfo(1 + productslistparam.getProductIds().indexOf(s13), "hp");
                        TrackingHelper.sendRecommendationInfoEvent(s13, 1 + productslistparam.getProductIds().indexOf(s13), "hp");
                    } else
                    if (productslistparam.getPageType() == PageTypeUtils.ProductPageRecommendation)
                    {
                        TrackingHelper.setProductFindingMethod(ProductFindingMethod.Recommendation.name());
                        TrackingHelper.sendRecommendationInfo(1 + productslistparam.getProductIds().indexOf(s13), "pp");
                        TrackingHelper.sendRecommendationInfoEvent(s13, 1 + productslistparam.getProductIds().indexOf(s13), "pp");
                    } else
                    if (productslistparam.getPageType() == PageTypeUtils.ProductListRecommendation)
                    {
                        TrackingHelper.setProductFindingMethod(ProductFindingMethod.Recommendation.name());
                        TrackingHelper.sendRecommendationInfo(1 + productslistparam.getProductIds().indexOf(s13), "pl");
                        TrackingHelper.sendRecommendationInfoEvent(s13, 1 + productslistparam.getProductIds().indexOf(s13), "pl");
                    }
                }
            }
            if (s13 != null && fkContext.getProductIds().contains(s13))
            {
                TrackingHelper.sendProductClickedOnBrowsePage(Integer.valueOf(fkContext.getProductIds().indexOf(s13)));
                ((HomeFragmentHolderActivity)activity).openProductPage(fkContext.getSelectedSizes(), s13, s14, fkContext.getProductIds().indexOf(s13), uuid.toString(), fkContext.getTitleViewText(), ((ProductListItemModel)l.get(s13)).getRequestId(), null);
            }
        }
_L2:
        return;
        exception;
        FkLogger.printStackTrace(exception);
        fkproductlistcontext = null;
          goto _L7
_L5:
        if (s1.contains("on_click_product_item_more_options"))
        {
            String as5[] = s1.split("/");
            if (as5.length > 1)
            {
                String s11 = as5[1];
                String s12 = as5[2];
                ProductListItemModel productlistitemmodel2 = (ProductListItemModel)l.get(s11);
                if (productlistitemmodel2 != null && !productlistitemmodel2.isEbook())
                {
                    b();
                    LinearLayout linearlayout = (LinearLayout)View.inflate(view.getContext(), 0x7f03007d, null);
                    i = new PopupWindow(linearlayout, -1, -2);
                    ScreenMathUtils.fixForPopupBelowICS(i);
                    View view1 = linearlayout.findViewById(0x7f0a0175);
                    view1.setTag((new StringBuilder("on_click_share_product:")).append(s11).toString());
                    view1.setOnClickListener(new U(this));
                    View view2 = linearlayout.findViewById(0x7f0a0173);
                    view2.setContentDescription("add_to_cart");
                    ProductInfo productinfo1 = fkContext.getProductForId(s11);
                    ((TextView)view2.findViewById(0x7f0a0174)).setContentDescription("add_to_cart_text");
                    if (productinfo1 != null && !productinfo1.getPreferredSeller().getAvailiabilityDetails().isEnableCheckout())
                    {
                        view2.setEnabled(false);
                    } else
                    if (!CartHandler.isCartItem(s11))
                    {
                        view2.setEnabled(true);
                    } else
                    {
                        TextView textview = (TextView)view2.findViewById(0x7f0a0174);
                        textview.setContentDescription("add_to_cart_text");
                        textview.setText("In Cart");
                    }
                    view2.setTag((new StringBuilder()).append(s11).append("/").append(s12).toString());
                    view2.setOnClickListener(new V(this));
                    i.setOutsideTouchable(true);
                    i.setFocusable(true);
                    i.setBackgroundDrawable(new BitmapDrawable());
                    i.showAsDropDown(view, 0, -ScreenMathUtils.dpToPx(90, view.getContext()));
                    return;
                }
            }
        } else
        {
            if (s1.contains("on_back_click"))
            {
                popFragmentStack();
                return;
            }
            if (s1.contains("on_click_delete_from_wishlist"))
            {
                String as4[] = s1.split("/");
                if (as4.length > 1)
                {
                    String s10 = as4[1];
                    if (!StringUtils.isNullOrEmpty(s10) && !StringUtils.isNullOrEmpty(s10))
                    {
                        ProductInfo productinfo = fkContext.getProductForId(s10);
                        ((WishListFragment)this).deleteFromWishList(false, s10, view, productinfo);
                        return;
                    }
                }
            } else
            if (s1.contains("on_click_add_to_wishlist"))
            {
                String as3[] = s1.split("/");
                if (as3.length > 1)
                {
                    String s8 = as3[1];
                    if (!StringUtils.isNullOrEmpty(s8))
                    {
                        ProductListItemModel productlistitemmodel1 = (ProductListItemModel)l.get(s8);
                        BrowseParam browseparam;
                        String as[];
                        int i1;
                        String s2;
                        String as1[];
                        String s3;
                        String s4;
                        String s5;
                        ProductListItemModel productlistitemmodel;
                        String s6;
                        String as2[];
                        String s7;
                        String s9;
                        if (productlistitemmodel1 != null)
                        {
                            s9 = productlistitemmodel1.getCategory();
                        } else
                        {
                            s9 = "";
                        }
                        if (c == ProductListViewType.Grid)
                        {
                            WishListUtils.addToWishList(s8, s9, view, PageTypeUtils.ProductGrid, activity);
                        } else
                        {
                            WishListUtils.addToWishList(s8, s9, view, PageTypeUtils.ProductList, activity);
                        }
                        showCueTips("AddToWishList");
                        return;
                    }
                }
            } else
            {
label0:
                {
                    if (!s1.contains("on_click_remove_from_wishlist"))
                    {
                        break label0;
                    }
                    as2 = s1.split("/");
                    if (as2.length > 1)
                    {
                        s7 = as2[1];
                        if (!StringUtils.isNullOrEmpty(s7))
                        {
                            if (c == ProductListViewType.Grid)
                            {
                                WishListUtils.removeFromWishList(s7, view, PageTypeUtils.ProductGrid, activity);
                                return;
                            } else
                            {
                                WishListUtils.removeFromWishList(s7, view, PageTypeUtils.ProductList, activity);
                                return;
                            }
                        }
                    }
                }
            }
        }
          goto _L2
        if (!s1.contains("on_click_item_swatch")) goto _L9; else goto _L8
_L8:
        as1 = s1.split("/");
        b();
        if (as1.length <= 2) goto _L2; else goto _L10
_L10:
        s3 = as1[2];
        s4 = "";
        if (StringUtils.isNullOrEmpty(s3))
        {
            break MISSING_BLOCK_LABEL_1720;
        }
        productlistitemmodel = (ProductListItemModel)l.get(s3);
        if (productlistitemmodel == null)
        {
            break MISSING_BLOCK_LABEL_1720;
        }
        s6 = productlistitemmodel.getRequestId();
        s4 = productlistitemmodel.getCategory();
        s5 = s6;
_L11:
        if (activity != null)
        {
            ((HomeFragmentHolderActivity)activity).addToCart(s3, null, null, s4, null, null, null, addToCartLocation, null, new AnalyticData(s5, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
            return;
        }
          goto _L2
_L9:
        if (s1.contains("on_click_size_chart"))
        {
            s2 = s1.replace("on_click_size_chart", "");
            if (!StringUtils.isNullOrEmpty(s2))
            {
                b();
                if (activity != null)
                {
                    ((HomeFragmentHolderActivity)activity).openWebView(s2, false);
                    return;
                }
            }
        } else
        {
            if (s1.contains("load_home_fragment"))
            {
                ((HomeFragmentHolderActivity)activity).loadHomeFragment();
                return;
            }
            if (s1.equals("browse_all_tvs"))
            {
                browseAllProduct = true;
                pushAndChangeContext("", null, null, null, null, null);
                return;
            }
            if (s1.contains("toggleview"))
            {
                if (c == ProductListViewType.List)
                {
                    toggleListAdapter(ProductListViewType.Grid);
                } else
                if (c == ProductListViewType.Grid)
                {
                    toggleListAdapter(ProductListViewType.Full);
                } else
                {
                    toggleListAdapter(ProductListViewType.List);
                }
                TrackingHelper.sendBrowsePageToggleView(c.toString());
                return;
            }
            if (s1.contains("offers_between_list"))
            {
                searchAndProdDataHandler.cancelFetch();
                browseparam = (BrowseParam)fkContext.getParam();
                if (browseparam.getFilters() == null)
                {
                    as = new String[1];
                    as[0] = s1.split("/")[1];
                } else
                {
                    as = new String[1 + browseparam.getFilters().length];
                    for (i1 = 0; i1 < browseparam.getFilters().length; i1++)
                    {
                        as[i1] = browseparam.getFilters()[i1];
                    }

                    as[browseparam.getFilters().length] = s1.split("/")[1];
                }
                pushAndChangeContext(null, fkContext.getStoreMetaInfo().getId(), fkContext.getStoreMetaInfo().getTitle(), null, as, null);
                return;
            }
            if (s1.contains("open_filter_page"))
            {
                onClickOfAllFilters(true);
                return;
            }
        }
          goto _L2
        s5 = "";
          goto _L11
    }

    protected void onClickOfAllFilters(boolean flag)
    {
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        ProductListViewType productlistviewtype;
        super.onCreateView(layoutinflater, viewgroup, bundle);
        a = new Handler();
        isDisableClick = false;
        p = ImageOrientation.None;
        k = layoutinflater.inflate(0x7f03007b, viewgroup, false);
        layoutInflater = layoutinflater;
        y = 0;
        x = false;
        if (fkContext != null && fkContext.getProductsCount() < fkContext.getTotalProductCount())
        {
            b = false;
        }
        context = activity.getApplicationContext();
        u = (RelativeLayout)k.findViewById(0x7f0a016a);
        A = (LinearLayout)k.findViewById(0x7f0a016d);
        A.setVisibility(8);
        if (j == null)
        {
            j = FlipkartApplication.getImageLoader();
        }
        if (FlipkartPreferenceManager.instance().isPoppingRefineByFragment().booleanValue())
        {
            return k;
        }
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return k;
        }
        if (FlipkartPreferenceManager.instance().isPoppingProductPageFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingProductPageFragment(Boolean.valueOf(false));
        }
        ListView listview;
        FrameLayout framelayout;
        android.widget.FrameLayout.LayoutParams layoutparams;
        if (fkContext == null)
        {
            processExtras();
        } else
        {
            firstTime = false;
        }
        g = getHeader();
        t = getListViewHeader();
        productList = (ListView)k.findViewById(0x7f0a0170);
        productList.setOnTouchListener(D);
        productListLayoutParams = (android.widget.FrameLayout.LayoutParams)productList.getLayoutParams();
        if (t != null)
        {
            productList.addHeaderView(t);
        }
        listview = productList;
        productlistviewtype = fkContext.getViewType();
        if (productlistviewtype != ProductListViewType.List) goto _L2; else goto _L1
_L1:
        c = ProductListViewType.List;
_L4:
        c();
        f = new Y(this, c);
        updateListAdapterVars();
        listview.setAdapter(f);
        if (g.getVisibility() == 0)
        {
            g.bringToFront();
        }
        if (u != null)
        {
            u.bringToFront();
            if (s != null)
            {
                s.bringToFront();
            }
        }
        productList.setOnScrollListener(new T(this));
        h = getFooterView();
        System.currentTimeMillis();
        framelayout = (FrameLayout)k.findViewById(0x7f0a0169);
        if (g != null)
        {
            framelayout.addView(g, new android.widget.LinearLayout.LayoutParams(-1, -2));
        }
        if (h != null)
        {
            layoutparams = new android.widget.FrameLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(53, context), 80);
            ((FrameLayout)k).addView(h, layoutparams);
        }
        A.setOnClickListener(new X(this));
        if (l == null)
        {
            l = new HashMap();
        }
        initDataHandler();
        ActionBarView.setActionBarCustomView(activity, ActionBarState.Default_Back);
        performPreDataCallTasks();
        if (!FlipkartPreferenceManager.instance().isPoppingAllRefineFragment().booleanValue())
        {
            initData();
        }
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.ProductList);
        u.setOnClickListener(this);
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("loading PL ")).append(fkContext.getParam().toString()).toString());
        return k;
_L2:
        if (productlistviewtype == ProductListViewType.Grid)
        {
            c = ProductListViewType.Grid;
        } else
        if (productlistviewtype == ProductListViewType.Full)
        {
            c = ProductListViewType.Full;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onDestroy()
    {
        super.onDestroy();
        if (fkContext != null)
        {
            fkContext = null;
        }
        if (searchAndProdDataHandler != null)
        {
            searchAndProdDataHandler.cancelFetch();
            searchAndProdDataHandler = null;
        }
        f = null;
        productList = null;
        b();
        layoutInflater = null;
        k = null;
        currPinCode = "";
        nullResultViewWidget = null;
        pincodeViewWidget = null;
        u = null;
        s = null;
        CustomDialog.dismissDialog();
    }

    public void onDestroyView()
    {
        ToastMessageUtils.dismissToast(activity);
        super.onDestroyView();
        v = 0;
        analyticData.setPageTypeUtils(FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil());
        b();
        if (k != null)
        {
            ScreenMathUtils.unbindDrawables(k);
        }
    }

    public void onFragmentPopped()
    {
        super.onFragmentPopped();
    }

    public void onFragmentPushed()
    {
        if (fkContext != null)
        {
            fkContext.setViewType(c);
        }
        x = false;
        z = false;
        isRefreshing = false;
        if (searchAndProdDataHandler != null)
        {
            searchAndProdDataHandler.cancelFetch();
        }
        CustomDialog.dismissDialog();
        ToastMessageUtils.dismissToast(activity);
        b();
        super.onFragmentPushed();
    }

    public void onPause()
    {
        super.onPause();
    }

    protected void performPreDataCallTasks()
    {
    }

    protected void processExtras()
    {
    }

    protected void pushAndChangeContext(String s1, String s2, String s3, String s4, String as[], String s5)
    {
    }

    public void refreshListView()
    {
        productList.setVisibility(0);
        if (f != null)
        {
            f.notifyDataSetChanged();
        }
        if (u != null)
        {
            u.bringToFront();
        }
    }

    public void setAnimate(int i1)
    {
        o = i1;
    }

    public void setGridLayout(boolean flag)
    {
        c = ProductListViewType.Grid;
    }

    public void setModelMap(Map map)
    {
        l = map;
    }

    public void setNoMoreDataToDownload(boolean flag)
    {
        b = flag;
    }

    public void setOnSamePage(boolean flag)
    {
        d = flag;
    }

    public void setRefreshFiltersView(boolean flag)
    {
        e = flag;
    }

    public void setRefreshing(boolean flag)
    {
        isRefreshing = flag;
    }

    public void setToNotify(boolean flag)
    {
        q = flag;
    }

    public void setVisibilityNoConnectionBar(boolean flag, String s1)
    {
        if (A == null)
        {
            return;
        }
        if (flag)
        {
            A.setVisibility(0);
            TextView textview = (TextView)A.findViewById(0x7f0a016e);
            if (textview != null)
            {
                textview.setText(s1);
            }
            A.bringToFront();
            return;
        } else
        {
            A.setVisibility(8);
            return;
        }
    }

    protected void showCueTips(String s1)
    {
        if (!s1.equals("WishListPage")) goto _L2; else goto _L1
_L1:
        if (FlipkartPreferenceManager.instance().isFirstTimeWishListLoad().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveFirstTimeWishListLoad(Boolean.valueOf(false));
            loadCueTips(0x7f0300c5);
        }
_L4:
        return;
_L2:
        if (!s1.equals("AddToWishList"))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!FlipkartPreferenceManager.instance().isFirstTimeAddToWishList().booleanValue()) goto _L4; else goto _L3
_L3:
        FlipkartPreferenceManager.instance().saveFirstTimeAddToWishList(Boolean.valueOf(false));
        loadCueTips(0x7f03001e);
        return;
        if (!s1.equals("ProdListPage") || !FlipkartPreferenceManager.instance().isFirstTimeProdListLoad().booleanValue()) goto _L4; else goto _L5
_L5:
        FlipkartPreferenceManager.instance().saveFirstTimeProdListLoad(Boolean.valueOf(false));
        loadCueTips(0x7f030070);
        return;
    }

    public void showError(int i1, int j1, String s1)
    {
        CustomDialog.showErrorMessage(i1, j1, s1, activity);
    }

    public void toggleListAdapter(ProductListViewType productlistviewtype)
    {
        c = productlistviewtype;
        z = false;
        ProductListViewType productlistviewtype1 = c;
        String s1;
        ArrayList arraylist;
        if (productlistviewtype1 == ProductListViewType.Full)
        {
            s1 = "Picture";
        } else
        if (productlistviewtype1 == ProductListViewType.List)
        {
            s1 = "List";
        } else
        {
            s1 = "Grid";
        }
        TrackingHelper.sendBrowsePageViewChange(s1);
        a();
        c();
        if (u != null)
        {
            u.bringToFront();
        }
        v = 0;
        arraylist = Y.d(f);
        f = new Y(this, c);
        Y.a(f, arraylist);
        productList.setAdapter(f);
        if (c == ProductListViewType.Grid)
        {
            productList.setSelection((1 + w) / 2);
            return;
        }
        if (c == ProductListViewType.Full)
        {
            productList.setSelection(-1 + (w << 1));
            return;
        } else
        {
            productList.setSelection(w);
            return;
        }
    }

    protected void triggerRequest()
    {
        isRefreshing = true;
        if (f != null)
        {
            if (o == 0)
            {
                refreshListView();
            } else
            {
                q = true;
            }
        }
        if (searchAndProdDataHandler != null && fkContext != null)
        {
            analyticData.setIsPageFirstLanding(isFirstLanding);
            searchAndProdDataHandler.getNextSetFrom(fkContext.getProductsCount(), null, analyticData);
        }
        if (isFirstLanding)
        {
            isFirstLanding = false;
        }
    }

    public void updateFooter(FooterParams footerparams)
    {
    }

    public void updateHeader(HeaderParams headerparams)
    {
    }

    public void updateListAdapterVars()
    {
        if (fkContext != null && fkContext.getProductIds() != null)
        {
            Y.a(f, new ArrayList(fkContext.getProductIds()));
        }
        refreshListView();
    }

    protected void updateListHeight()
    {
        g.getViewTreeObserver().addOnGlobalLayoutListener(new P(this));
    }

    public void updateListView()
    {
    }

    static 
    {
        (new StringBuilder("ASIMO.")).append(com/flipkart/android/fragments/ProductListFragment.getSimpleName());
    }
}
