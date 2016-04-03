// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.DB.BrowseHistoryDao;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.AddCartLocation;
import com.flipkart.android.analytics.ProductFindingMethod;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.cart.CartHandler;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.customviews.HorizontalScrollViewPager;
import com.flipkart.android.customviews.NewEditText;
import com.flipkart.android.customviews.NullResultViewWidget;
import com.flipkart.android.customviews.ViewPagerFixed;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.customwidget.WidgetAction;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.NotifyMeVDataHandler;
import com.flipkart.android.datahandler.OfferTermsVDataHandler;
import com.flipkart.android.datahandler.SearchAndProdInfoDataHandler;
import com.flipkart.android.datahandler.param.BaseDataHandlerParam;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.response.customwidgetitemvalue.Action;
import com.flipkart.android.response.discovery.OmnitureData;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.productInfo.SantaOffers;
import com.flipkart.android.response.ugc.UGCRating;
import com.flipkart.android.response.ugc.UGCRatingObj;
import com.flipkart.android.response.ugc.UGCReviewObj;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.NullResultWidgetState;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ProductPageBuilder;
import com.flipkart.android.utils.ProductSpecificSellerTypes;
import com.flipkart.android.utils.ProductUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.SellerTypeUtils;
import com.flipkart.android.utils.SellerTypes;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.android.utils.WishListUtils;
import com.flipkart.android.webview.WebviewLauncher;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.simonvt.menudrawer.LeftDrawer;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, ad, ae, at, 
//            aq, as, ab, ac, 
//            au, aj, ar, ak, 
//            al, ai, ag, ah, 
//            af, am, an, ao, 
//            ap, aa

public class ProductPageFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener, android.widget.AdapterView.OnItemSelectedListener
{

    public static final int PRODUCT_DETAIL_INFO_LEVEL = 0;
    public static final int PRODUCT_PAGE_DIALOG_CREATE = 1;
    public static final int PRODUCT_PAGE_DIALOG_UPDATE = 2;
    public static final String PRODUCT_PAGE_IMAGE_SELECTED_INDEX = "PRODUCT_PAGE_IMAGE_SELECTED_INDEX";
    public static final String PRODUCT_PAGE_IS_SIZE_SELECTED = "PRODUCT_PAGE_IS_SIZE_SELECTED";
    public static final String PRODUCT_PAGE_PATH_BAR_CODE = "PRODUCT_PAGE_PATH_BAR_CODE";
    public static final String PRODUCT_PAGE_SELECTED_INDEX = "PRODUCT_PAGE_SELECTED_INDEX";
    public static final String PRODUCT_PAGE_SELECTED_PRODUCT = "PRODUCT_PAGE_SELECTED_PRODUCT";
    public static final String PRODUCT_PAGE_SELECTED_PRODUCT_LISTING_ID = "PRODUCT_PAGE_SELECTED_PRODUCT_LISTING_ID";
    public static final String PRODUCT_PAGE_SELECTED_SIZE = "PRODUCT_PAGE_SELECTED_SIZE";
    public static final String PRODUCT_PAGE_SLIDER_PRODUCT_LIST_TITLE = "PRODUCT_PAGE_SLIDER_PRODUCT_LIST_TITLE";
    public static final String PRODUCT_PAGE_TOTAL_REVIEW_COUNT = "PRODUCT_PAGE_TOTAL_REVIEW_COUNT";
    public static final String PRODUCT_PAGE_UUID = "PRODUCT_PAGE_UUID";
    public static final int PRODUCT_SUMMARY_INFO_LEVEL = 1;
    public static final String SHOW_FBF = "FBF";
    public static final String addToCart = "+CART";
    public static final String addToWishListTag = "on_click_add_to_wishlist";
    public static final String addingToWishListTag = "adding_to_wishlist";
    public static final String addingToWishListText = "Adding";
    public static final String clearWishListTag = "on_click_clear_wishlist";
    public static final String deleteFromWishListTag = "on_click_delete_from_wishlist";
    public static final String deletingFromWishListTag = "deleting_from_wishlist";
    public static final String deletingFromWishListText = "Deleting";
    public static final String goToCart = "GoTo Cart";
    public static final int marginBottom = 31;
    public static final String removeFromWishListTag = "on_click_remove_from_wishlist";
    public static final String removingFromWishListTag = "removing_from_wishlist";
    public static final String removingFromWishListText = "Removing";
    public static final String saveToWishListTag = "on_click_save_to_wishlist";
    private String A;
    private BrowseHistoryDao B;
    private OfferTermsVDataHandler C;
    private Map D;
    private String E;
    private Dialog F;
    private LayoutInflater G;
    private NullResultViewWidget H;
    private ImageView I;
    private View J;
    private CartUpdation K;
    private android.view.View.OnClickListener L;
    private String a;
    private int b;
    private String c;
    private String d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private TextView i;
    private ViewPagerFixed j;
    private ListView k;
    private as l;
    private ar m;
    private View n;
    private FkProductListContext o;
    private SearchAndProdInfoDataHandler p;
    private SearchAndProdInfoDataHandler q;
    private NotifyMeVDataHandler r;
    private Map s;
    private View t;
    private int u;
    private boolean v;
    private boolean w;
    private Handler x;
    private ImageLoader y;
    private BaseDataHandlerParam z;

    public ProductPageFragment()
    {
        a = null;
        b = 0;
        c = null;
        d = null;
        e = false;
        f = false;
        g = false;
        h = true;
        i = null;
        j = null;
        k = null;
        l = null;
        m = null;
        n = null;
        o = null;
        t = null;
        u = 0;
        v = false;
        w = false;
        x = new Handler();
        z = null;
        A = "";
        E = null;
        H = null;
        I = null;
        J = null;
        L = new ad(this);
    }

    static int a(ProductPageFragment productpagefragment, int i1)
    {
        productpagefragment.u = 0;
        return 0;
    }

    static BrowseHistoryDao a(ProductPageFragment productpagefragment, BrowseHistoryDao browsehistorydao)
    {
        productpagefragment.B = browsehistorydao;
        return browsehistorydao;
    }

    private String a()
    {
        String s1;
        try
        {
            s1 = ((ProductPageModel)s.get(o.getProductIds().get(b))).getRequestId();
        }
        catch (Exception exception)
        {
            return "";
        }
        return s1;
    }

    static String a(ProductPageFragment productpagefragment, String s1)
    {
        productpagefragment.c = s1;
        return s1;
    }

    private String a(String s1)
    {
        ArrayList arraylist = o.getProductForId((String)o.getProductIds().get(b)).getMarketPlace();
        MarketPlaceSeller amarketplaceseller[] = new MarketPlaceSeller[arraylist.size()];
        arraylist.toArray(amarketplaceseller);
        int i1 = amarketplaceseller.length;
        for (int j1 = 0; j1 < i1; j1++)
        {
            MarketPlaceSeller marketplaceseller = amarketplaceseller[j1];
            if (s1.equals(marketplaceseller.getSellerDisplayName()))
            {
                return marketplaceseller.getSellerId();
            }
        }

        return null;
    }

    static Map a(ProductPageFragment productpagefragment, Map map)
    {
        productpagefragment.D = map;
        return map;
    }

    private void a(int i1)
    {
        if (g || f || o == null || z == null) goto _L2; else goto _L1
_L1:
        if (!(z instanceof ProductsListParam)) goto _L4; else goto _L3
_L3:
        ArrayList arraylist;
        ProductsListParam productslistparam;
        arraylist = ((ProductsListParam)z).getProductIds();
        productslistparam = (ProductsListParam)z;
        if (arraylist == null) goto _L2; else goto _L5
_L5:
        if (o.getTotalProductCount() != 0 && o.getProductsCount() >= productslistparam.getProductIds().size()) goto _L7; else goto _L6
_L6:
        b(i1);
_L2:
        return;
_L7:
        f = true;
        return;
_L4:
        if (z instanceof BrowseParam)
        {
            if (o.getTotalProductCount() == 0 || o.getProductsCount() < o.getTotalProductCount())
            {
                b(i1);
                return;
            } else
            {
                f = true;
                return;
            }
        }
        if (true) goto _L2; else goto _L8
_L8:
    }

    private void a(int i1, int j1)
    {
        int ai1[] = new int[3];
        if (isRefreshing)
        {
            return;
        }
        isRefreshing = true;
        initRefresing();
        boolean flag;
        int k1;
        int l1;
        if (!a(o.getProductForId((String)o.getProductIds().get(i1))))
        {
            ai1[0] = i1;
            flag = true;
        } else
        {
            flag = false;
        }
        k1 = i1 + 1;
        l1 = ((flag) ? 1 : 0);
        while (k1 <= i1 + 1 && k1 < o.getProductsCount()) 
        {
            int i2;
            int j2;
            int k2;
            ArrayList arraylist;
            int l2;
            HashMap hashmap;
            HashMap hashmap1;
            int i3;
            String as1[];
            String s1;
            String s2;
            Exception exception;
            Exception exception1;
            String s3;
            String s4;
            String s5;
            int j3;
            int k3;
            if (!a(o.getProductForId((String)o.getProductIds().get(k1))))
            {
                k3 = l1 + 1;
                ai1[l1] = k1;
            } else
            {
                k3 = l1;
            }
            k1++;
            l1 = k3;
        }
        i2 = i1 - 1;
        j2 = l1;
        k2 = i2;
        while (k2 >= i1 - 1 && k2 >= 0) 
        {
            if (!a(o.getProductForId((String)o.getProductIds().get(k2))))
            {
                j3 = j2 + 1;
                ai1[j2] = k2;
            } else
            {
                j3 = j2;
            }
            k2--;
            j2 = j3;
        }
        arraylist = new ArrayList();
        for (l2 = 0; l2 < j2; l2++)
        {
            arraylist.add(o.getProductIds().get(ai1[l2]));
        }

        hashmap = new HashMap();
        hashmap1 = new HashMap();
        i3 = 0;
        if (i3 >= j2) goto _L2; else goto _L1
_L1:
        s1 = (String)o.getProductIds().get(ai1[i3]);
        if (StringUtils.isNullOrEmpty(s1))
        {
            continue; /* Loop/switch isn't completed */
        }
        s2 = ",";
        s5 = (String)o.getProdIdListingIdMap().get(s1);
        s2 = s5;
_L3:
        try
        {
            s3 = (String)o.getProdIdOfferIdMap().get(s1);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception1)
        {
            s3 = ",";
        }
        if (StringUtils.isNullOrEmpty(s2))
        {
            s4 = ",";
        } else
        {
            s4 = s2;
        }
        if (StringUtils.isNullOrEmpty(s3))
        {
            s3 = ",";
        }
        hashmap.put(s1, s4);
        hashmap1.put(s1, s3);
        i3++;
        break MISSING_BLOCK_LABEL_279;
_L2:
        as1 = new String[j2];
        if (j2 == 0)
        {
            isRefreshing = false;
            closeRefresing();
            return;
        }
        x.post(new ae(this));
        if (j1 == 0)
        {
            p.getProductInfo((String[])arraylist.toArray(as1), hashmap, hashmap1, A, analyticData);
            return;
        } else
        {
            q.getProductInfo((String[])arraylist.toArray(as1), hashmap, hashmap1, A, analyticData);
            return;
        }
        exception;
          goto _L3
    }

    private static void a(View view, at at1)
    {
        View view1 = (View)view.getParent();
_L4:
        View view2 = view1.findViewById(0x7f0a017f);
        if (view2 != null) goto _L2; else goto _L1
_L1:
        View view3;
        view1 = (View)view1.getParent();
        view3 = null;
        if (view1 != null) goto _L4; else goto _L3
_L3:
        LinearLayout linearlayout;
        LinearLayout linearlayout1;
        if (view3 == null)
        {
            break MISSING_BLOCK_LABEL_116;
        }
        view3.setVisibility(0);
        linearlayout = (LinearLayout)view3.findViewById(0x7f0a0180);
        linearlayout1 = (LinearLayout)view3;
        if (at1 != at.a)
        {
            break MISSING_BLOCK_LABEL_117;
        }
        for (int j1 = 0; j1 < linearlayout1.getChildCount(); j1++)
        {
            linearlayout1.getChildAt(j1).setVisibility(8);
        }

        break; /* Loop/switch isn't completed */
_L2:
        view3 = view2;
        if (true) goto _L3; else goto _L5
_L5:
        linearlayout.setVisibility(0);
        return;
        for (int i1 = 0; i1 < linearlayout1.getChildCount(); i1++)
        {
            linearlayout1.getChildAt(i1).setVisibility(0);
        }

        linearlayout.setVisibility(8);
        return;
    }

    static void a(ProductPageFragment productpagefragment, int i1, int j1)
    {
        productpagefragment.a(i1, j1);
    }

    static void a(ProductPageFragment productpagefragment, int i1, int j1, String s1, List list, Map map, String s2, boolean flag, 
            boolean flag1, String s3)
    {
        if (productpagefragment.o != null && productpagefragment.t != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!flag || i1 != 200)
        {
            productpagefragment.g = false;
            productpagefragment.x.post(new aq(productpagefragment));
        }
        if (i1 != 200) goto _L4; else goto _L3
_L3:
        boolean flag2;
        if (list != null && list.size() > 0)
        {
            productpagefragment.H.removeAllViews();
            if (productpagefragment.o.getProductsCount() == 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            productpagefragment.o.addProductIds(new ArrayList(list));
            if (productpagefragment.l != null)
            {
                productpagefragment.l.notifyDataSetChanged();
                productpagefragment.e();
            }
            if (list != null && map != null && map.size() != 0)
            {
                HashMap hashmap = new HashMap();
                int l1 = 0;
                while (l1 < list.size()) 
                {
                    Exception exception;
                    String s4;
                    ProductPageModel productpagemodel;
                    int k1;
                    try
                    {
                        if (((ProductInfo)map.get(list.get(l1))).getPreferredListingId() != null)
                        {
                            hashmap.put(list.get(l1), ((ProductInfo)map.get(list.get(l1))).getPreferredListingId());
                        }
                    }
                    catch (Exception exception1) { }
                    l1++;
                }
                productpagefragment.o.saveListingIdMap(hashmap);
            }
            if (productpagefragment.o.getTotalProductCount() != 0 && productpagefragment.o.getProductsCount() >= productpagefragment.o.getTotalProductCount() || productpagefragment.z != null && (productpagefragment.z instanceof ProductsListParam) && productpagefragment.o.getProductsCount() >= ((ProductsListParam)productpagefragment.z).getProductIds().size())
            {
                productpagefragment.f = true;
            }
        } else
        {
            flag2 = false;
        }
        if (map == null || map.size() == 0) goto _L1; else goto _L5
_L5:
        productpagefragment.a(s3, map);
        productpagefragment.o.addProductMap(map);
        productpagefragment.b("ProductPage");
        if (productpagefragment.o.getProductIds().size() != 1 || productpagefragment.g) goto _L7; else goto _L6
_L6:
        if (productpagefragment.I != null)
        {
            productpagefragment.I.setVisibility(8);
        }
        if (productpagefragment.t instanceof LeftDrawer)
        {
            ((LeftDrawer)productpagefragment.t).setTouchMode(0);
        }
_L8:
        productpagefragment.x.post(new ab(productpagefragment, flag1, map, flag2));
        return;
_L7:
        if (productpagefragment.I != null)
        {
            productpagefragment.I.setVisibility(0);
        }
        if (true) goto _L8; else goto _L4
_L4:
        if (flag1 || productpagefragment.o.getProductsCount() != 0)
        {
            break MISSING_BLOCK_LABEL_500;
        }
        productpagefragment.showError(productpagefragment.H, i1, productpagefragment, false);
        if (productpagefragment.I == null) goto _L1; else goto _L9
_L9:
        productpagefragment.I.setVisibility(8);
        return;
        s4 = (String)productpagefragment.o.getProductIds().get(productpagefragment.b);
        productpagemodel = (ProductPageModel)productpagefragment.s.get(s4);
        if (flag1)
        {
            break; /* Loop/switch isn't completed */
        }
        k1 = productpagemodel.getInfoLevel();
        if (k1 == 0) goto _L1; else goto _L10
_L10:
        CustomDialog.showErrorMessage(i1, 200, s1, productpagefragment.activity);
        return;
        exception;
        if (true) goto _L10; else goto _L11
_L11:
    }

    static void a(ProductPageFragment productpagefragment, int i1, String s1)
    {
        if (productpagefragment.o != null && productpagefragment.t != null)
        {
            productpagefragment.g = false;
            productpagefragment.x.post(new ac(productpagefragment));
            if (productpagefragment.t != null && (productpagefragment.o.getParam() instanceof BrowseParam))
            {
                String s2 = ((BrowseParam)productpagefragment.o.getParam()).getPath();
                if (s2 != null && s2.equals("BarCode"))
                {
                    TrackingHelper.sendBarCodeFailed();
                    if (productpagefragment.H == null)
                    {
                        productpagefragment.H = (NullResultViewWidget)productpagefragment.t.findViewById(0x7f0a013b);
                    }
                    if (productpagefragment.H != null)
                    {
                        productpagefragment.H.setOnClickOnViews(productpagefragment.L);
                        productpagefragment.H.setState(NullResultWidgetState.ShowBarCodeError, "", "productPage");
                        if (productpagefragment.I != null)
                        {
                            productpagefragment.I.setVisibility(8);
                            return;
                        }
                    }
                }
            }
        }
    }

    static void a(ProductPageFragment productpagefragment, View view)
    {
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 14)
        {
            break MISSING_BLOCK_LABEL_411;
        }
        productpagefragment.j = new HorizontalScrollViewPager(FlipkartApplication.getAppContext());
        if (view == null) goto _L2; else goto _L1
_L1:
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0a01e3);
        if (linearlayout == null) goto _L2; else goto _L3
_L3:
        linearlayout.addView(productpagefragment.j);
_L4:
        if (productpagefragment.j != null)
        {
            productpagefragment.j.setOffscreenPageLimit(1);
            productpagefragment.l = new as(productpagefragment);
            productpagefragment.j.setAdapter(productpagefragment.l);
            if (productpagefragment.o != null && productpagefragment.o.getProductsCount() > 0 && productpagefragment.b < productpagefragment.o.getProductIds().size())
            {
                productpagefragment.j.setCurrentItem(productpagefragment.b);
                au au1 = new au(productpagefragment, (String)productpagefragment.o.getProductIds().get(productpagefragment.b));
                Void avoid[] = new Void[0];
                ProductInfo productinfo;
                if (!(au1 instanceof AsyncTask))
                {
                    au1.execute(avoid);
                } else
                {
                    AsyncTaskInstrumentation.execute((AsyncTask)au1, avoid);
                }
                productinfo = productpagefragment.o.getProductForId((String)productpagefragment.o.getProductIds().get(productpagefragment.b));
                if (productinfo != null && productpagefragment.w)
                {
                    productpagefragment.w = false;
                    TrackingHelper.sendProductView(productinfo, (ProductPageModel)productpagefragment.s.get(productpagefragment.o.getProductIds().get(productpagefragment.b)));
                    productpagefragment.v = true;
                } else
                {
                    productpagefragment.a(productpagefragment.b, 0);
                }
                ProductPageBuilder.buildBottomBar(productinfo, productpagefragment.n, productpagefragment);
                if (productpagefragment.n.getVisibility() == 8)
                {
                    android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)productpagefragment.j.getLayoutParams();
                    layoutparams1.setMargins(0, 0, 0, 0);
                    productpagefragment.j.setLayoutParams(layoutparams1);
                } else
                {
                    android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)productpagefragment.j.getLayoutParams();
                    layoutparams.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(31, productpagefragment.activity.getApplicationContext()));
                    productpagefragment.j.setLayoutParams(layoutparams);
                }
            }
            productpagefragment.j.setOnPageChangeListener(new aj(productpagefragment));
            if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 14)
            {
                productpagefragment.k = (ListView)view.findViewById(0x7f0a01e0);
                productpagefragment.m = new ar(productpagefragment);
                productpagefragment.k.setAdapter(productpagefragment.m);
                productpagefragment.k.setOnItemClickListener(new ak(productpagefragment));
                if (productpagefragment.t != null)
                {
                    ((LeftDrawer)productpagefragment.t).setOnDrawerStateChangeListener(new al(productpagefragment));
                }
            }
        }
_L2:
        return;
        productpagefragment.j = (ViewPagerFixed)view.findViewById(0x7f0a01e1);
          goto _L4
    }

    static void a(ProductPageFragment productpagefragment, View view, ProductInfo productinfo)
    {
        String s1 = (String)productpagefragment.o.getProductIds().get(productpagefragment.b);
        ProductPageModel productpagemodel;
        if (productinfo != null)
        {
            productpagemodel = ProductPageModel.getModel(productinfo, productpagefragment.activity);
            productpagefragment.s.put(productinfo.getProductId(), productpagemodel);
        } else
        {
            productpagemodel = null;
        }
        if (productpagemodel != null)
        {
            String s2 = productpagemodel.getRequestId();
            if (s2 == null)
            {
                s2 = "";
            }
            if (!productpagefragment.v)
            {
                TrackingHelper.sendProductView(productinfo, productpagemodel);
                productpagefragment.v = true;
            }
            view.setVisibility(0);
            if (productpagemodel.getProductId().equals(productpagefragment.o.getProductIds().get(productpagefragment.b)))
            {
                ProductPageBuilder.buildProductPage(productpagemodel, view, productpagefragment.y, productpagefragment, productpagefragment, productpagefragment.e, productpagefragment.u, productpagefragment.activity, s2, productpagefragment.o);
            } else
            {
                ProductPageBuilder.buildProductPage(productpagemodel, view, productpagefragment.y, productpagefragment, productpagefragment, false, productpagefragment.u, productpagefragment.activity, s2, productpagefragment.o);
            }
            if (productpagemodel.getProductId().equals(s1))
            {
                ProductPageBuilder.buildBottomBar(productinfo, productpagefragment.n, productpagefragment);
            }
            if (productinfo.getInfoLevel() != 0)
            {
                productpagefragment.a(productpagefragment.b, 0);
            }
        }
    }

    private void a(String s1, Map map)
    {
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); analyticData.setRequestId(s1))
        {
            ((ProductInfo)map.get((String)iterator.next())).setRequestId(s1);
        }

    }

    static boolean a(ProductPageFragment productpagefragment)
    {
        return productpagefragment.e;
    }

    static boolean a(ProductPageFragment productpagefragment, boolean flag)
    {
        productpagefragment.e = false;
        return false;
    }

    private boolean a(ProductInfo productinfo)
    {
        while (productinfo == null || productinfo.getInfoLevel() != 0 || productinfo.getPin() == null || !productinfo.getPin().equals(A)) 
        {
            return false;
        }
        return true;
    }

    static int b(ProductPageFragment productpagefragment)
    {
        return productpagefragment.b;
    }

    static int b(ProductPageFragment productpagefragment, int i1)
    {
        productpagefragment.b = i1;
        return i1;
    }

    static View b(ProductPageFragment productpagefragment, View view)
    {
        productpagefragment.J = null;
        return null;
    }

    static String b(ProductPageFragment productpagefragment, String s1)
    {
        productpagefragment.E = s1;
        return s1;
    }

    private void b()
    {
        if (J != null)
        {
            J.setTag("cue_tips_overlay");
            ((ViewGroup)activity.getWindow().getDecorView()).addView(J);
            J.setVisibility(0);
            J.setOnClickListener(new ai(this));
        }
    }

    private void b(int i1)
    {
        if (i1 == 0)
        {
            analyticData.setIsPageFirstLanding(false);
            if (p.getNextSetFrom(o.getProductsCount(), A, analyticData))
            {
                g = true;
                isRefreshing = true;
                initRefresing();
            }
        } else
        {
            analyticData.setIsPageFirstLanding(false);
            if (q.getNextSetFrom(o.getProductsCount(), A, analyticData))
            {
                g = true;
                isRefreshing = true;
                initRefresing();
                return;
            }
        }
    }

    private void b(String s1)
    {
        J = ((ViewGroup)activity.getWindow().getDecorView()).findViewWithTag("cue_tips_overlay");
        if (J == null)
        {
            LayoutInflater layoutinflater = (LayoutInflater)activity.getApplicationContext().getSystemService("layout_inflater");
            if (s1.equals("ProductPage"))
            {
                if (FlipkartPreferenceManager.instance().isFirstTimeProdPageLoad().booleanValue())
                {
                    FlipkartPreferenceManager.instance().saveFirstTimeProdPageLoad(Boolean.valueOf(false));
                    J = (FrameLayout)layoutinflater.inflate(0x7f03008f, null);
                    b();
                }
            } else
            if (s1.equals("AddToWishList") && FlipkartPreferenceManager.instance().isFirstTimeAddToWishList().booleanValue())
            {
                FlipkartPreferenceManager.instance().saveFirstTimeAddToWishList(Boolean.valueOf(false));
                J = (FrameLayout)layoutinflater.inflate(0x7f03001e, null);
                b();
                return;
            }
        }
    }

    static boolean b(ProductPageFragment productpagefragment, boolean flag)
    {
        productpagefragment.h = flag;
        return flag;
    }

    static FkProductListContext c(ProductPageFragment productpagefragment)
    {
        return productpagefragment.o;
    }

    private void c()
    {
        String s1 = (String)o.getProductIds().get(b);
        o.setProductSpecificSellerType(ProductSpecificSellerTypes.NONE);
        if (!StringUtils.isNullOrEmpty(s1))
        {
            ProductPageModel productpagemodel = (ProductPageModel)s.get(s1);
            if (productpagemodel != null)
            {
                ProductSpecificSellerTypes productspecificsellertypes = SellerTypeUtils.getSellerType(productpagemodel, productpagemodel.getPreferredSeller());
                o.setProductSpecificSellerType(productspecificsellertypes);
                o.setProductModel(productpagemodel);
            }
        }
    }

    static void c(ProductPageFragment productpagefragment, int i1)
    {
        productpagefragment.a(i1);
    }

    static void c(ProductPageFragment productpagefragment, String s1)
    {
        productpagefragment.b(s1);
    }

    static Map d(ProductPageFragment productpagefragment)
    {
        return productpagefragment.s;
    }

    private void d()
    {
        if (J != null)
        {
            if (activity != null)
            {
                ((ViewGroup)activity.getWindow().getDecorView()).removeView(J);
            }
            J = null;
        }
    }

    static as e(ProductPageFragment productpagefragment)
    {
        return productpagefragment.l;
    }

    private void e()
    {
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 14 && m != null)
        {
            m.notifyDataSetChanged();
        }
    }

    private void f()
    {
        ToastMessageUtils.showErrorToastMessage("Loading data. Please try in some time", activity, false);
    }

    static void f(ProductPageFragment productpagefragment)
    {
        productpagefragment.e();
    }

    static String g(ProductPageFragment productpagefragment)
    {
        return productpagefragment.c;
    }

    static View h(ProductPageFragment productpagefragment)
    {
        return productpagefragment.n;
    }

    static ViewPagerFixed i(ProductPageFragment productpagefragment)
    {
        return productpagefragment.j;
    }

    public static boolean isValidIndianPin(String s1)
    {
        while (s1 == null || s1.length() != 6 || !s1.matches("^[1-9][0-9]+")) 
        {
            return false;
        }
        return true;
    }

    static boolean j(ProductPageFragment productpagefragment)
    {
        return productpagefragment.h;
    }

    static View k(ProductPageFragment productpagefragment)
    {
        return productpagefragment.t;
    }

    static ListView l(ProductPageFragment productpagefragment)
    {
        return productpagefragment.k;
    }

    static Map m(ProductPageFragment productpagefragment)
    {
        return productpagefragment.D;
    }

    static Dialog n(ProductPageFragment productpagefragment)
    {
        return productpagefragment.F;
    }

    static ImageLoader o(ProductPageFragment productpagefragment)
    {
        return productpagefragment.y;
    }

    static int p(ProductPageFragment productpagefragment)
    {
        return productpagefragment.u;
    }

    static BrowseHistoryDao q(ProductPageFragment productpagefragment)
    {
        return productpagefragment.B;
    }

    static View r(ProductPageFragment productpagefragment)
    {
        return productpagefragment.J;
    }

    public void createOfferTermsDialog(int i1)
    {
        LinearLayout linearlayout;
        if (i1 == 2)
        {
            linearlayout = (LinearLayout)G.inflate(0x7f030066, null);
            ((TextView)linearlayout.findViewById(0x7f0a0053)).setText("Offer Terms & Conditions");
            WebView webview = (WebView)linearlayout.findViewById(0x7f0a00b9);
            String s1 = StringUtils.getHtmlTextWithCss(E);
            if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() < 11)
            {
                s1 = E;
            }
            webview.setVisibility(0);
            if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 14)
            {
                webview.getSettings().setTextZoom(95);
            } else
            {
                webview.getSettings().setTextSize(android.webkit.WebSettings.TextSize.NORMAL);
            }
            webview.loadData(s1, "text/html", "UTF-8");
        } else
        {
            linearlayout = null;
        }
        if (i1 == 1)
        {
            linearlayout = (LinearLayout)G.inflate(0x7f030066, null);
            linearlayout.removeViewAt(1);
            linearlayout.addView(G.inflate(0x7f03005d, null), 1);
            if (F == null)
            {
                F = new Dialog(activity);
                F.requestWindowFeature(1);
            }
            F.show();
        }
        F.setContentView(linearlayout);
        ((CustomRobotoRegularTextView)linearlayout.findViewById(0x7f0a0139)).setOnClickListener(new ag(this));
    }

    public boolean handleBackPress()
    {
        d();
        CustomDialog.dismissDialog();
        if (activity != null && activity.findViewById(0x7f0a01de) != null)
        {
            ToastMessageUtils.dismissToast(activity);
            LeftDrawer leftdrawer = (LeftDrawer)activity.findViewById(0x7f0a01de);
            if (leftdrawer.isMenuVisible())
            {
                leftdrawer.closeMenu();
                return true;
            }
        }
        if (o != null)
        {
            o = null;
        }
        if (m != null)
        {
            m = null;
        }
        if (l != null)
        {
            l = null;
        }
        g = false;
        if (x != null)
        {
            x.post(new ah(this));
        }
        CrashLoggerUtils.pushAndUpdate("pressing back from productPageFragment.");
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    public void onClick(View view)
    {
        if (view.getTag() instanceof String) goto _L2; else goto _L1
_L1:
        if (!(view.getTag() instanceof Action)) goto _L4; else goto _L3
_L3:
        Action action = (Action)view.getTag();
        if (!action.getScreenType().contains("togglePincodeWidget")) goto _L6; else goto _L5
_L5:
        a(view, at.a);
_L12:
        return;
_L6:
        WidgetAction.performAction(action, activity, PageTypeUtils.ProductPage);
        return;
_L4:
        if (view.getTag() instanceof com.flipkart.android.response.component.data.customvalues.Action)
        {
            WidgetAction.performAction((com.flipkart.android.response.component.data.customvalues.Action)view.getTag(), activity, PageTypeUtils.ProductPage);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        String s1;
        s1 = (String)view.getTag();
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("clicking_on_product_page with view tag ")).append(s1).toString());
        if (StringUtils.isNullOrEmpty(s1))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (s1.contains("load_home_fragment"))
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragment();
            return;
        }
        if (s1.contains("try_again"))
        {
            View view11 = H.findViewById(0x7f0a00d6);
            if (view11 != null)
            {
                view11.setVisibility(8);
            }
            a(0);
            return;
        }
        if (o.getProductForId((String)o.getProductIds().get(b)) == null)
        {
            a(b, 0);
        }
        if (o.getProductForId((String)o.getProductIds().get(b)) == null) goto _L8; else goto _L7
_L7:
        int j3 = o.getProductForId((String)o.getProductIds().get(b)).getInfoLevel();
        int i1 = j3;
_L10:
        if (s1.contains("on_click_go_back"))
        {
            popFragmentStack();
            return;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
_L8:
        i1 = 1;
        if (true) goto _L10; else goto _L9
_L9:
        if (!s1.contains("on_click_size_chart"))
        {
            break; /* Loop/switch isn't completed */
        }
        String s28 = s1.replace("on_click_size_chart", "");
        if (!StringUtils.isNullOrEmpty(s28))
        {
            ((HomeFragmentHolderActivity)activity).openWebView(s28, false);
            return;
        }
        if (true) goto _L12; else goto _L11
_L11:
        if (!s1.contains("on_click_emi_text"))
        {
            break; /* Loop/switch isn't completed */
        }
        String s27 = s1.replace("on_click_emi_text", "");
        if (!StringUtils.isNullOrEmpty(s27))
        {
            ((HomeFragmentHolderActivity)activity).openWebView(s27, false);
            return;
        }
        if (true) goto _L12; else goto _L13
_L13:
        if (!s1.contains("on_click_change_product_image"))
        {
            break MISSING_BLOCK_LABEL_736;
        }
        if (isRefreshing)
        {
            f();
            return;
        }
        String as3[] = s1.split("/");
        if (as3.length <= 1 || StringUtils.isNullOrEmpty(as3[1]))
        {
            continue; /* Loop/switch isn't completed */
        }
        int k2 = Integer.parseInt(as3[1]);
        String s24 = (String)o.getProductIds().get(b);
        if (StringUtils.isNullOrEmpty(s24))
        {
            continue; /* Loop/switch isn't completed */
        }
        ProductPageModel productpagemodel4 = (ProductPageModel)s.get(s24);
        if (productpagemodel4 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        View view8 = j.findViewWithTag(new com.flipkart.android.utils.ProductPageBuilder.ProductViewTag(s24));
        Exception exception1;
        String s2;
        String s3;
        View view1;
        LinearLayout linearlayout;
        TextView textview;
        EditText edittext;
        String s4;
        String s5;
        ProductInfo productinfo;
        String s6;
        ProductPageModel productpagemodel;
        ProductInfo productinfo1;
        String s7;
        String s8;
        SellerTypes sellertypes;
        ProductSpecificSellerTypes productspecificsellertypes;
        ProductSpecificSellerTypes productspecificsellertypes1;
        String s9;
        com.flipkart.android.fragments.model.ProductPageModel.SwatchModel swatchmodel;
        View view2;
        ScrollView scrollview;
        LinearLayout linearlayout1;
        LinearLayout linearlayout2;
        EditText edittext1;
        String s10;
        String s11;
        au au1;
        Void avoid[];
        String s12;
        ProductPageModel productpagemodel1;
        String s13;
        ProductInfo productinfo2;
        String s14;
        String s15;
        SellerTypes sellertypes1;
        ProductSpecificSellerTypes productspecificsellertypes2;
        String s16;
        CustomRobotoRegularTextView customrobotoregulartextview;
        View view3;
        ScrollView scrollview1;
        LinearLayout linearlayout3;
        LinearLayout linearlayout4;
        String s17;
        String s18;
        ProductInfo productinfo3;
        String s19;
        String as1[];
        String as2[];
        Exception exception2;
        View view4;
        String s20;
        ProductPageModel productpagemodel2;
        Map map;
        com.flipkart.android.fragments.model.ProductPageModel.SwatchModel swatchmodel1;
        int j1;
        LinearLayout linearlayout5;
        View view5;
        Iterator iterator;
        int k1;
        View view6;
        Iterator iterator1;
        int l1;
        int i2;
        int j2;
        String s21;
        String s22;
        ProductPageModel productpagemodel3;
        ProductInfo productinfo4;
        long l2;
        UGCRating ugcrating;
        String s23;
        View view7;
        ScrollView scrollview2;
        String s25;
        NetworkImageView networkimageview;
        View view9;
        int i3;
        View view10;
        String s26;
        if (productpagemodel4 != null && productpagemodel4.getOriginalUrls().size() > k2)
        {
            s25 = (String)productpagemodel4.getOriginalUrls().get(k2);
        } else
        {
            s25 = "";
        }
        if (view8 == null || !(view8 instanceof ScrollView))
        {
            continue; /* Loop/switch isn't completed */
        }
        networkimageview = (NetworkImageView)view8.findViewById(0x7f0a018f);
        if (networkimageview == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        networkimageview.setImageUrl(s25, y);
        view9 = (View)view.getParent().getParent();
        if (view9 == null || !(view9 instanceof LinearLayout))
        {
            continue; /* Loop/switch isn't completed */
        }
        i3 = 0;
        if (i3 >= ((LinearLayout)view9).getChildCount())
        {
            continue; /* Loop/switch isn't completed */
        }
        view10 = ((LinearLayout)view9).getChildAt(i3);
        if (view10 == null || !(view10 instanceof LinearLayout))
        {
            break MISSING_BLOCK_LABEL_719;
        }
        if (i3 != 8)
        {
            break; /* Loop/switch isn't completed */
        }
        s26 = (String)view10.getTag();
        if (!StringUtils.isNullOrEmpty(s26) && s26.equals("open_gallery_view")) goto _L12; else goto _L14
_L14:
        if (i3 == k2 << 1)
        {
            u = k2;
            view10.setBackgroundResource(0x7f02012b);
        } else
        {
            view10.setBackgroundResource(0x7f02012c);
        }
        i3++;
        break MISSING_BLOCK_LABEL_622;
        if (!s1.contains("on_click_go_to_product_page_offers_layout"))
        {
            break; /* Loop/switch isn't completed */
        }
        if (isRefreshing)
        {
            f();
            return;
        }
        s23 = (String)o.getProductIds().get(b);
        if (!StringUtils.isNullOrEmpty(s23) && (ProductPageModel)s.get(s23) != null)
        {
            view7 = j.findViewWithTag(new com.flipkart.android.utils.ProductPageBuilder.ProductViewTag(s23));
            if (view7 != null && (view7 instanceof ScrollView))
            {
                scrollview2 = (ScrollView)view7;
                scrollview2.scrollTo(0, ((LinearLayout)scrollview2.findViewById(0x7f0a01ae)).getTop() - ScreenMathUtils.dpToPx(60, activity.getApplicationContext()));
                return;
            }
        }
        if (true) goto _L12; else goto _L15
_L15:
        if (!s1.contains("open_more_sellers_page"))
        {
            break; /* Loop/switch isn't completed */
        }
        c();
        ContextCache.getInstance().putResponse(d, o);
        if (activity instanceof HomeFragmentHolderActivity)
        {
            if (i1 == 0)
            {
                ((HomeFragmentHolderActivity)activity).openProductPageMultipleSellers(b, d, a());
                return;
            }
            if (isRefreshing)
            {
                f();
                return;
            }
        }
        if (true) goto _L12; else goto _L16
_L16:
        if (!s1.contains("open_summary_page"))
        {
            break; /* Loop/switch isn't completed */
        }
        c();
        ContextCache.getInstance().putResponse(d, o);
        if (activity instanceof HomeFragmentHolderActivity)
        {
            if (i1 == 0)
            {
                ((HomeFragmentHolderActivity)activity).openProductPageSumary(b, d, e, a());
                return;
            }
            if (isRefreshing)
            {
                f();
                return;
            }
        }
        if (true) goto _L12; else goto _L17
_L17:
        if (!s1.contains("open_specifications_page"))
        {
            break; /* Loop/switch isn't completed */
        }
        c();
        ContextCache.getInstance().putResponse(d, o);
        if (activity instanceof HomeFragmentHolderActivity)
        {
            if (i1 == 0)
            {
                ((HomeFragmentHolderActivity)activity).openProductPageSpecification(b, d, e, a());
                return;
            }
            if (isRefreshing)
            {
                f();
                return;
            }
        }
        if (true) goto _L12; else goto _L18
_L18:
        if (!s1.contains("open_review_page"))
        {
            break; /* Loop/switch isn't completed */
        }
        c();
        productinfo4 = o.getProductForId((String)o.getProductIds().get(b));
        if (productinfo4 != null)
        {
            l2 = 0L;
            ugcrating = productinfo4.getUgc();
            if (ugcrating != null && ugcrating.getRatingObj() != null)
            {
                l2 = ugcrating.getRatingObj().getTotalRatingCount();
            }
            if (l2 != 0L)
            {
                ContextCache.getInstance().putResponse(d, o);
                if (activity instanceof HomeFragmentHolderActivity)
                {
                    if (i1 == 0)
                    {
                        ((HomeFragmentHolderActivity)activity).openProductPageReviewsAndRating(b, d, e, a(), ugcrating.getReviewObj().getTotalNoReviews());
                        return;
                    }
                    if (isRefreshing)
                    {
                        f();
                        return;
                    }
                }
            }
        }
        if (true) goto _L12; else goto _L19
_L19:
        if (s1.contains("open_gallery_view"))
        {
            if (i1 != 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            ContextCache.getInstance().putResponse(d, o);
            s22 = (String)o.getProductIds().get(b);
            if (!StringUtils.isNullOrEmpty(s22))
            {
                productpagemodel3 = (ProductPageModel)s.get(s22);
                if (productpagemodel3 != null)
                {
                    o.setProductModel(productpagemodel3);
                    if (productpagemodel3.getOriginalUrls() == null || productpagemodel3.getOriginalUrls().size() == 0)
                    {
                        ToastMessageUtils.showErrorToastMessage("No image available.", activity, true);
                        return;
                    }
                }
            }
            if (activity instanceof HomeFragmentHolderActivity)
            {
                ((HomeFragmentHolderActivity)activity).openProductPageImageGallary(requestId, u, b, d);
                return;
            }
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_1443;
        if (!isRefreshing) goto _L12; else goto _L20
_L20:
        f();
        return;
        if (!s1.contains("open_seller_info"))
        {
            break; /* Loop/switch isn't completed */
        }
        s21 = (String)((TextView)view.findViewById(0x7f0a01e6)).getText();
        if (activity instanceof HomeFragmentHolderActivity)
        {
            if (i1 == 0)
            {
                ((HomeFragmentHolderActivity)activity).openSellerInfoPage(requestId, a(s21));
                return;
            }
            if (isRefreshing)
            {
                f();
                return;
            }
        }
        if (true) goto _L12; else goto _L21
_L21:
        if (s1.contains("on_click_swatch"))
        {
            as2 = s1.split("/");
            if (as2.length > 1)
            {
                try
                {
                    view4 = (View)view.getParent().getParent();
                }
                // Misplaced declaration of an exception variable
                catch (Exception exception2)
                {
                    return;
                }
                if (view4 != null)
                {
                    s20 = as2[1];
                    productpagemodel2 = (ProductPageModel)s.get(o.getProductIds().get(b));
                    if (productpagemodel2 != null)
                    {
                        map = productpagemodel2.getSwatchesMap();
                        if (map != null)
                        {
                            swatchmodel1 = (com.flipkart.android.fragments.model.ProductPageModel.SwatchModel)map.get(s20);
                            j1 = ((LinearLayout)view.getParent()).indexOfChild(view);
                            if (view4.getId() == 0x7f0a01fb)
                            {
                                linearlayout5 = (LinearLayout)view4;
                                linearlayout5.setContentDescription("product_page_swatch_parent_layout");
                                if (linearlayout5.getChildCount() <= 1 || !linearlayout5.getChildAt(1).getTag().equals(s20))
                                {
                                    view5 = linearlayout5.getChildAt(0);
                                    linearlayout5.removeAllViews();
                                    linearlayout5.addView(view5);
                                    iterator = map.keySet().iterator();
                                    k1 = 0;
                                    while (iterator.hasNext()) 
                                    {
                                        iterator1 = ((com.flipkart.android.fragments.model.ProductPageModel.SwatchModel)map.get((String)iterator.next())).getSwaatchValues().iterator();
                                        l1 = 0;
                                        while (iterator1.hasNext()) 
                                        {
                                            if (((com.flipkart.android.fragments.model.ProductPageModel.SwatchValueModel)iterator1.next()).isAvailable())
                                            {
                                                j2 = l1 + 1;
                                            } else
                                            {
                                                j2 = l1;
                                            }
                                            l1 = j2;
                                        }
                                        if (l1 > 0)
                                        {
                                            i2 = k1 + 1;
                                        } else
                                        {
                                            i2 = k1;
                                        }
                                        k1 = i2;
                                    }
                                    view6 = ProductPageBuilder.buildSwatchArrowView(activity, k1, j1, ScreenMathUtils.dpToPx(290, activity) / k1);
                                    view6.setTag(s20);
                                    linearlayout5.addView(view6);
                                    o.getProductMap().get(o.getProductIds().get(b));
                                    linearlayout5.addView(ProductPageBuilder.buildSwatchItemsView(activity, swatchmodel1, this, y, this, e, productpagemodel2.getSizeChartUrl(), o.getSelectedSizes(), o.getProductIds(), new AnalyticData(productpagemodel2.getRequestId(), null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil())));
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        while (false) 
        {
            if (s1.contains("on_click_item_swatch"))
            {
                as1 = s1.split("/");
                if (as1.length > 2)
                {
                    if (as1[1].equals("size"))
                    {
                        e = true;
                    } else
                    {
                        e = false;
                    }
                    o.getProductIds().set(b, as1[2]);
                    a(b, 0);
                    l.notifyDataSetChanged();
                    e();
                    return;
                }
                continue;
            }
            if (s1.contains("on_click_share_product"))
            {
                share();
                return;
            }
            if (s1.contains("on_click_add_to_wishlist"))
            {
                s18 = (String)o.getProductIds().get(b);
                if (!StringUtils.isNullOrEmpty(s18))
                {
                    productinfo3 = o.getProductForId(s18);
                    s19 = "";
                    if (productinfo3 != null)
                    {
                        s19 = productinfo3.getOmnitureData().getCategory();
                    }
                    WishListUtils.addToWishList(s18, s19, view, PageTypeUtils.ProductPage, activity);
                    b("AddToWishList");
                    return;
                }
                continue;
            }
            if (s1.contains("on_click_remove_from_wishlist"))
            {
                s17 = (String)o.getProductIds().get(b);
                if (!StringUtils.isNullOrEmpty(s17))
                {
                    WishListUtils.removeFromWishList(s17, view, PageTypeUtils.ProductPage, activity);
                    return;
                }
                continue;
            }
            if (s1.contains("add_to_cart"))
            {
                s12 = (String)o.getProductIds().get(b);
                if (CartHandler.isCartItem(s12))
                {
                    ((HomeFragmentHolderActivity)activity).doShowCart();
                    return;
                }
                productpagemodel1 = (ProductPageModel)s.get(s12);
                if (productpagemodel1 != null && productpagemodel1.isSizeToBeSelected() && !e)
                {
                    if ((com.flipkart.android.fragments.model.ProductPageModel.SwatchModel)productpagemodel1.getSwatchesMap().get("size") != null)
                    {
                        view3 = j.findViewWithTag(new com.flipkart.android.utils.ProductPageBuilder.ProductViewTag(s12));
                        if (view3 != null && (view3 instanceof ScrollView))
                        {
                            scrollview1 = (ScrollView)view3;
                            linearlayout3 = (LinearLayout)scrollview1.findViewById(0x7f0a01fb);
                            linearlayout4 = (LinearLayout)linearlayout3.findViewWithTag("on_click_swatch/size");
                            if (linearlayout4 != null)
                            {
                                linearlayout4.performClick();
                            }
                            scrollview1.scrollTo(0, linearlayout3.getBottom());
                        }
                    }
                    ToastMessageUtils.showErrorToastMessage("Please select a size first", activity, true);
                    return;
                }
                s13 = (String)o.getProductIds().get(b);
                productinfo2 = o.getProductForId(s13);
                if (productinfo2 != null)
                {
                    s14 = productinfo2.getPreferredListingId();
                    s15 = "";
                    if (productinfo2.getPreferredSeller() != null)
                    {
                        s15 = productinfo2.getPreferredSeller().getSellerDisplayName();
                    }
                    sellertypes1 = SellerTypes.NONE;
                    productspecificsellertypes2 = ProductSpecificSellerTypes.NONE;
                    if (productpagemodel1 != null)
                    {
                        sellertypes1 = productpagemodel1.getSellerType();
                        productspecificsellertypes2 = SellerTypeUtils.getSellerType(productpagemodel1, productinfo2.getPreferredSeller());
                    }
                    s16 = "";
                    if (productinfo2.getOmnitureData() != null)
                    {
                        s16 = productinfo2.getOmnitureData().getCategory();
                    }
                    ((HomeFragmentHolderActivity)activity).addToCart(s13, s14, s15, s16, productspecificsellertypes2, sellertypes1, new af(this, view), AddCartLocation.ProductPage, null, new AnalyticData(((ProductPageModel)s.get(s13)).getRequestId(), null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
                    customrobotoregulartextview = (CustomRobotoRegularTextView)view;
                    customrobotoregulartextview.setText("Adding..");
                    customrobotoregulartextview.setEnabled(false);
                    return;
                }
                continue;
            }
            if (s1.contains("on_click_other_variants"))
            {
                TrackingHelper.setProductFindingMethod(ProductFindingMethod.SimilarProducts.name());
                s11 = s1.substring(1 + s1.indexOf("/"));
                au1 = new au(this, s11);
                avoid = new Void[0];
                if (!(au1 instanceof AsyncTask))
                {
                    au1.execute(avoid);
                } else
                {
                    AsyncTaskInstrumentation.execute((AsyncTask)au1, avoid);
                }
                o.getProductIds().set(b, s11);
                a(b, 0);
                l.notifyDataSetChanged();
                u = 0;
                e();
                return;
            }
            if (s1.contains("on_click_check_pincode"))
            {
                edittext1 = ((NewEditText)((View)view.getParent()).findViewById(0x7f0a0182)).getEditText();
                ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(edittext1.getWindowToken(), 0);
                s10 = edittext1.getText().toString();
                if (s10 == null)
                {
                    s10 = "";
                }
                if (isValidIndianPin(s10))
                {
                    TrackingHelper.sendPincodeCheck(s10);
                    FlipkartPreferenceManager.instance().saveUserPinCode(s10);
                    if (A != null && A.equals(s10))
                    {
                        a(view, at.b);
                        return;
                    } else
                    {
                        A = s10;
                        a(b, 0);
                        return;
                    }
                } else
                {
                    ToastMessageUtils.showErrorToastMessage("Pin Code you have entered is invalid. Please retry.", activity, true);
                    return;
                }
            }
            if (s1.contains("on_click_change_pin"))
            {
                a(view, at.a);
                return;
            }
            if (s1.contains("buy_now"))
            {
                s6 = (String)o.getProductIds().get(b);
                productpagemodel = (ProductPageModel)s.get(s6);
                if (productpagemodel != null && productpagemodel.isSizeToBeSelected() && !e)
                {
                    swatchmodel = (com.flipkart.android.fragments.model.ProductPageModel.SwatchModel)productpagemodel.getSwatchesMap().get("size");
                    if (swatchmodel != null && swatchmodel.getType() == 2)
                    {
                        view2 = j.findViewWithTag(new com.flipkart.android.utils.ProductPageBuilder.ProductViewTag(s6));
                        if (view2 != null && (view2 instanceof ScrollView))
                        {
                            scrollview = (ScrollView)view2;
                            linearlayout1 = (LinearLayout)scrollview.findViewById(0x7f0a01fb);
                            if (linearlayout1 != null)
                            {
                                linearlayout2 = (LinearLayout)linearlayout1.findViewWithTag("on_click_swatch/size");
                                if (linearlayout2 != null)
                                {
                                    linearlayout2.performClick();
                                }
                                scrollview.scrollTo(0, linearlayout1.getBottom());
                            }
                        }
                    }
                    ToastMessageUtils.showErrorToastMessage("Please select a size first", activity, true);
                    return;
                }
                productinfo1 = o.getProductForId(s6);
                if (productinfo1 == null)
                {
                    continue;
                }
                s7 = productinfo1.getPreferredListingId();
                s8 = "";
                if (productinfo1.getPreferredSeller() != null)
                {
                    s8 = productinfo1.getPreferredSeller().getSellerDisplayName();
                }
                sellertypes = SellerTypes.NONE;
                productspecificsellertypes = ProductSpecificSellerTypes.NONE;
                if (productpagemodel != null)
                {
                    sellertypes = productpagemodel.getSellerType();
                    productspecificsellertypes1 = SellerTypeUtils.getSellerType(productpagemodel, productinfo1.getPreferredSeller());
                } else
                {
                    productspecificsellertypes1 = productspecificsellertypes;
                }
                s9 = "";
                if (productinfo1.getOmnitureData() != null)
                {
                    s9 = productinfo1.getOmnitureData().getCategory();
                }
                if (!StringUtils.isNullOrEmpty(s7))
                {
                    WebviewLauncher.launchBuyNow(productinfo1.getProductId(), s7, s8, productspecificsellertypes1, sellertypes, s9, view.getContext());
                    return;
                }
                continue;
            }
            if (s1.contains("on_click_notifyme"))
            {
                view1 = (View)view.getParent();
                linearlayout = (LinearLayout)view1;
                textview = (TextView)((View)linearlayout.getParent()).findViewById(0x7f0a017e);
                edittext = (EditText)view1.findViewById(0x7f0a017c);
                ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
                s4 = edittext.getText().toString();
                edittext.setText("");
                s5 = (String)o.getProductIds().get(b);
                productinfo = o.getProductForId(s5);
                if (s4.contains(".") && s4.contains("@"))
                {
                    r.notifyMe(s4, s5, ProductUtils.getCategory(productinfo), new AnalyticData(a(), null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
                    linearlayout.setVisibility(8);
                    textview.setVisibility(0);
                    return;
                } else
                {
                    ToastMessageUtils.showErrorToastMessage("Please specify a valid Email address", activity, true);
                    return;
                }
            }
            if (s1.contains("load_home_fragment"))
            {
                ((HomeFragmentHolderActivity)activity).loadHomeFragment();
                return;
            }
            if (s1.contains("offer_terms"))
            {
                try
                {
                    s2 = (String)o.getProductIds().get(b);
                    s3 = ((SantaOffers)o.getProductForId(s2).getOffers().get(view.getId())).getOfferId();
                    createOfferTermsDialog(1);
                    showOfferTermsAndConditions(s3, ((ProductPageModel)s.get(s2)).getRequestId());
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (Exception exception1)
                {
                    return;
                }
            }
        }
        if (true) goto _L12; else goto _L22
_L22:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view;
        super.onCreateView(layoutinflater, viewgroup, bundle);
        ((HomeFragmentHolderActivity)activity).lockDrawer();
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() < 14)
        {
            view = layoutinflater.inflate(0x7f030097, viewgroup, false);
        } else
        {
            view = layoutinflater.inflate(0x7f030096, viewgroup, false);
        }
        t = view;
        G = layoutinflater;
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return t;
        }
        if (o != null) goto _L2; else goto _L1
_L1:
        Bundle bundle1;
        w = true;
        bundle1 = getArguments();
        if (bundle1 == null) goto _L2; else goto _L3
_L3:
        a = bundle1.getString("PRODUCT_PAGE_SLIDER_PRODUCT_LIST_TITLE");
        b = bundle1.getInt("PRODUCT_PAGE_SELECTED_INDEX");
        c = bundle1.getString("PRODUCT_PAGE_SELECTED_PRODUCT");
        bundle1.getString("PRODUCT_PAGE_SELECTED_PRODUCT_LISTING_ID");
        d = bundle1.getString("PRODUCT_PAGE_UUID");
        if (!StringUtils.isNullOrEmpty(d)) goto _L5; else goto _L4
_L4:
        ArrayList arraylist = bundle1.getStringArrayList("PRODUCT_LIST_EXTRAS_PRODUCTS");
        String s2 = bundle1.getString("PRODUCT_LIST_EXTRAS_CUR_PRD");
        String s3 = bundle1.getString("PRODUCT_LIST_EXTRAS_TITLE");
        String s4 = bundle1.getString("PRODUCT_LIST_EXTRAS_PAGE_TYPE");
        if (StringUtils.isNullOrEmpty(s4))
        {
            s4 = PageTypeUtils.None.name();
        }
        if (arraylist != null)
        {
            z = new ProductsListParam(arraylist, s2, s3, PageTypeUtils.valueOf(s4));
            b = ((ProductsListParam)z).getSelectedIndex();
            c = s2;
            a = s3;
        } else
        {
            BrowseParam browseparam = new BrowseParam();
            browseparam.setQuery(bundle1.getString("SEARCH_EXTRAS_QUERY"));
            if (StringUtils.isNullOrEmpty(bundle1.getString("SEARCH_EXTRAS_STORE")))
            {
                browseparam.setStoreId("search.flipkart.com");
            } else
            {
                browseparam.setStoreId(bundle1.getString("SEARCH_EXTRAS_STORE"));
            }
            browseparam.setFilters(bundle1.getStringArray("PRODUCT_LIST_EXTRAS_FILTERS"));
            browseparam.setTags(bundle1.getStringArray("PRODUCT_LIST_EXTRAS_TAGS"));
            browseparam.setTitle(bundle1.getString("PRODUCT_LIST_EXTRAS_TITLE"));
            browseparam.setSortOption(bundle1.getString("PRODUCT_LIST_EXTRAS_SORT"));
            browseparam.setPath(bundle1.getString("PRODUCT_PAGE_PATH_BAR_CODE"));
            browseparam.setEnableAugmentSearch(bundle1.getBoolean("PRODUCT_LIST_EXTRAS_AUGMENT_SEARCH"));
            browseparam.setViews(bundle1.getStringArray("PRODUCT_LIST_EXTRAS_VIEWS"));
            a = bundle1.getString("PRODUCT_LIST_EXTRAS_TITLE");
            z = browseparam;
        }
        o = new FkProductListContext();
        o.setParam(z);
_L2:
        if (o == null || x == null)
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragmentNotImmediate();
            return view;
        }
        break; /* Loop/switch isn't completed */
_L5:
        o = (FkProductListContext)ContextCache.getInstance().getResponse(d);
        if (o != null)
        {
            z = o.getParam();
            analyticData.setPageTypeUtils(FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil());
        }
        if (true) goto _L2; else goto _L6
_L6:
        ActionBarView.setActionBarCustomView(activity, ActionBarState.Default_Back);
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.ProductPage);
        H = (NullResultViewWidget)view.findViewById(0x7f0a013b);
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 14)
        {
            I = (ImageView)view.findViewById(0x7f0a01e2);
        }
        B = new BrowseHistoryDao(activity.getApplicationContext());
        if (s == null)
        {
            s = new HashMap();
        }
        String s1 = FlipkartPreferenceManager.instance().getUserPinCode();
        if (isValidIndianPin(s1))
        {
            A = s1;
        } else
        {
            A = null;
        }
        y = FlipkartApplication.getImageLoader();
        n = view.findViewById(0x7f0a0177);
        n.setVisibility(4);
        p = new am(this, z, 0);
        q = new an(this, z, 1);
        r = new ao(this);
        C = new ap(this);
        if (o != null && o.getProductIds().size() == 0)
        {
            a(0);
        }
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 14)
        {
            i = (TextView)view.findViewById(0x7f0a01df);
            ProductPageModel productpagemodel;
            if (!StringUtils.isNullOrEmpty(a))
            {
                i.setText(a);
            } else
            {
                i.setVisibility(8);
                view.findViewById(0x7f0a00cb).setVisibility(8);
            }
        }
        if (o != null && o.getProductsCount() > 0 && o.getProductIds().contains(c))
        {
            productpagemodel = ProductPageModel.getModel(o.getProductForId((String)o.getProductIds().get(b)), activity);
            if (productpagemodel != null)
            {
                s.put(productpagemodel.getProductId(), productpagemodel);
            }
        }
        K = new CartUpdation();
        activity.registerReceiver(K, new IntentFilter(HomeFragmentHolderActivity.CART_UPDATED));
        x.postDelayed(new aa(this, view), 200L);
        return view;
    }

    public void onDestroy()
    {
        d();
        if (activity != null && K != null)
        {
            activity.unregisterReceiver(K);
        }
        super.onDestroy();
        i = null;
        j = null;
        k = null;
        l = null;
        m = null;
        n = null;
        o = null;
        p = null;
        q = null;
        r = null;
        if (s != null)
        {
            s.clear();
            s = null;
        }
        x = null;
        y = null;
        z = null;
        t = null;
        B = null;
        H = null;
        I = null;
        System.gc();
    }

    public void onDestroyView()
    {
        ((HomeFragmentHolderActivity)activity).unlockDrawer();
        d();
        super.onDestroyView();
        analyticData.setPageTypeUtils(FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil());
        if (t != null)
        {
            ScreenMathUtils.unbindDrawables(t);
        }
        if (l != null)
        {
            l.clearAllViews();
        }
    }

    public void onFragmentPopped()
    {
        super.onFragmentPopped();
    }

    public void onFragmentPushed()
    {
        d();
        if (activity != null)
        {
            ToastMessageUtils.dismissToast(activity);
        }
        CustomDialog.dismissDialog();
        super.onFragmentPushed();
        closeRefresing();
    }

    public void onItemSelected(AdapterView adapterview, View view, int i1, long l1)
    {
        if (view.getTag() != null && (view.getTag() instanceof String))
        {
            String s1 = (String)view.getTag();
            if (s1.contains("on_click_item_swatch"))
            {
                String as1[] = s1.split("/");
                if (as1.length > 2)
                {
                    if (as1[1].equals("size"))
                    {
                        e = true;
                    }
                    if (!((String)o.getProductIds().get(b)).equals(as1[2]))
                    {
                        o.getProductIds().set(b, as1[2]);
                        a(b, 0);
                        l.notifyDataSetChanged();
                        e();
                    }
                }
            }
        }
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    public void share()
    {
        TrackingHelper.sendSocialSharing();
        ProductInfo productinfo = o.getProductForId((String)o.getProductIds().get(b));
        if (productinfo != null)
        {
            String s1 = (new StringBuilder("http://www.flipkart.com/item/")).append(productinfo.getProductId()).toString();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            String s2 = productinfo.getOmnitureData().getVertical();
            if (s2 == null || s2.equalsIgnoreCase(""))
            {
                intent.putExtra("android.intent.extra.SUBJECT", "Check out this product on Flipkart!");
            } else
            {
                intent.putExtra("android.intent.extra.SUBJECT", (new StringBuilder("Check out this ")).append(s2).append(" on Flipkart!").toString());
            }
            intent.putExtra("android.intent.extra.TEXT", s1);
            startActivity(Intent.createChooser(intent, "Choose..."));
        }
    }

    public void showOfferTermsAndConditions(String s1, String s2)
    {
        C.getTermsAndCondition(s1, new AnalyticData(s2, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
    }

    private class CartUpdation extends BroadcastReceiver
    {

        private ProductPageFragment a;

        public void onReceive(Context context, Intent intent)
        {
            if (ProductPageFragment.c(a) != null && ProductPageFragment.c(a).getProductIds() != null && ProductPageFragment.b(a) < ProductPageFragment.c(a).getProductIds().size())
            {
                ProductInfo productinfo = ProductPageFragment.c(a).getProductForId((String)ProductPageFragment.c(a).getProductIds().get(ProductPageFragment.b(a)));
                if (productinfo != null)
                {
                    ProductPageBuilder.updateBottomBarCartIcon(productinfo.getProductId(), a.activity.getCurrentFocus());
                }
            }
        }

        public CartUpdation()
        {
            a = ProductPageFragment.this;
            super();
        }
    }

}
