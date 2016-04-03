// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AppEventsLogger;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.flipkart.android.DB.AutoSuggestDao;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.SplashActivity;
import com.flipkart.android.activity.base.FlipkartBaseSherlockFragmentActivity;
import com.flipkart.android.analytics.AddCartLocation;
import com.flipkart.android.analytics.EntryChannel;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.cart.ICartListener;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.customwidget.WidgetAction;
import com.flipkart.android.datahandler.AddToCartVDataHandler;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.CartVDataHandler;
import com.flipkart.android.datahandler.LogoutVDataHandler;
import com.flipkart.android.datahandler.UrlDecodeDatahandler;
import com.flipkart.android.datahandler.WishListVDataHandler;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.fragments.AllFiltersFragment;
import com.flipkart.android.fragments.FiltersListFragment;
import com.flipkart.android.fragments.FlipkartBaseFragment;
import com.flipkart.android.fragments.InAppNotificationFragment;
import com.flipkart.android.fragments.MultiWidgetFragment;
import com.flipkart.android.fragments.ProductListFragment;
import com.flipkart.android.fragments.ProductPageFragment;
import com.flipkart.android.fragments.ProductPageImageGallaryFragment;
import com.flipkart.android.fragments.ProductPageMultipleSellersFragment;
import com.flipkart.android.fragments.ProductPageReviewFragment;
import com.flipkart.android.fragments.ProductPageSpecificationFragment;
import com.flipkart.android.fragments.ProductPageSummaryFragment;
import com.flipkart.android.fragments.RefineCategoryFragment;
import com.flipkart.android.fragments.SearchFragment;
import com.flipkart.android.fragments.SearchListFragment;
import com.flipkart.android.fragments.SellerFragment;
import com.flipkart.android.fragments.SimpleProductListFragment;
import com.flipkart.android.fragments.WebViewFragment;
import com.flipkart.android.fragments.WishListFragment;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.login.FacebookConstants;
import com.flipkart.android.notification.FlipkartNotificationManager;
import com.flipkart.android.response.appconfig.AppRateData;
import com.flipkart.android.response.appconfig.AppUpgradeData;
import com.flipkart.android.response.customwidgetitemvalue.Action;
import com.flipkart.android.urlmanagement.WebviewAction;
import com.flipkart.android.urlmanagement.WebviewHelper;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.AutoSuggestWord;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.InAppNotificationUtils;
import com.flipkart.android.utils.NetworkMonitor;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ProductSpecificSellerTypes;
import com.flipkart.android.utils.RefineByCategoryResponse;
import com.flipkart.android.utils.SellerTypes;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.TabContextCache;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.android.utils.share.ShareUtil;
import com.flipkart.android.utils.share.TwitterShare;
import com.flipkart.android.volley.request.GsonRequest;
import com.google.mygson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Referenced classes of package com.flipkart.android.activity:
//            q, i, n, o, 
//            f, LoginActivity, h, e, 
//            j, k, l, m, 
//            g, p

public class HomeFragmentHolderActivity extends FlipkartBaseSherlockFragmentActivity
    implements android.content.DialogInterface.OnCancelListener
{

    public static String CART_UPDATED = "cart_updation";
    public static final String HOME_ACTIVITY_EXTRAS_FROM_SCREEN = "HOME_ACTIVITY_EXTRAS_FROM_SCREEN";
    public static final String HOME_ACTIVITY_EXTRAS_OPEN_FRAGMENT = "HOME_ACTIVITY__EXTRAS_OPEN_FRAGMENT";
    public static final String HOME_ACTIVITY_EXTRA_OPEN_FORGOT_PASSWORD = "HOME_ACTIVITY_EXTRA_OPEN_FORGOT_PASSWORD";
    public static final String HOME_ACTIVITY_EXTRA_OPEN_HOME_PAGE = "HOME_ACTIVITY_EXTRA_OPEN_HOME_PAGE";
    public static String LOGGED_IN_ACTION_COMMAND = "com.flipkart.android.HomeFragmentHolderActivity.IsLoggedInActions";
    public static boolean isUpFrontSearchPresent = false;
    private boolean A;
    private IsLoggedInReceiver B;
    private Action C;
    private Boolean a;
    public ActionBarState actionBarState;
    private Boolean b;
    private boolean c;
    private boolean d;
    private int e;
    private DrawerLayout f;
    private ActionBarDrawerToggle g;
    private int h;
    private LogoutVDataHandler i;
    public boolean isFromKilledState;
    private AddToCartVDataHandler j;
    private CartVDataHandler k;
    private ICartListener l;
    private String m;
    protected Drawable mActionBarSearchDrawable;
    private FkLoadingDialog n;
    private FlipkartBaseFragment o;
    private MenuItem p;
    private MenuItem q;
    private ProgressBar r;
    private View s;
    private Menu t;
    private Action u;
    private Action v;
    private UiLifecycleHelper w;
    private String x;
    private String y;
    private boolean z;

    public HomeFragmentHolderActivity()
    {
        a = Boolean.valueOf(false);
        b = Boolean.valueOf(false);
        c = false;
        d = false;
        e = -1;
        h = -1;
        n = null;
        o = null;
        p = null;
        q = null;
        r = null;
        s = null;
        t = null;
        u = null;
        v = null;
        x = null;
        y = null;
        z = false;
        A = false;
        isFromKilledState = false;
        actionBarState = null;
        C = null;
    }

    static int a(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        return homefragmentholderactivity.h;
    }

    static int a(HomeFragmentHolderActivity homefragmentholderactivity, int i1)
    {
        homefragmentholderactivity.h = i1;
        return i1;
    }

    static FlipkartBaseFragment a(HomeFragmentHolderActivity homefragmentholderactivity, FlipkartBaseFragment flipkartbasefragment)
    {
        homefragmentholderactivity.o = flipkartbasefragment;
        return flipkartbasefragment;
    }

    static Action a(HomeFragmentHolderActivity homefragmentholderactivity, Action action)
    {
        homefragmentholderactivity.C = action;
        return action;
    }

    static String a(HomeFragmentHolderActivity homefragmentholderactivity, String s1)
    {
        homefragmentholderactivity.m = null;
        return null;
    }

    private void a()
    {
        if (!FlipkartPreferenceManager.instance().isFirstTimeLoad().booleanValue()) goto _L2; else goto _L1
_L1:
        String s8 = getIntent().getAction();
        Uri uri1 = getIntent().getData();
        Intent intent = new Intent(this, com/flipkart/android/SplashActivity);
        intent.setAction(s8);
        intent.setData(uri1);
        startActivity(intent);
        finish();
_L4:
        return;
_L2:
        Bundle bundle;
        String s1 = getIntent().getAction();
        Uri uri = getIntent().getData();
        if (!StringUtils.isNullOrEmpty(s1) && uri != null)
        {
            getIntent().setAction("");
            getIntent().setData(null);
            (new q(this)).decodeFlipkartLink(uri.getAuthority());
            n.showDlg("", "Loading...", null, false);
            return;
        }
        bundle = getIntent().getExtras();
        if (bundle == null) goto _L4; else goto _L3
_L3:
        String s2;
        a = Boolean.valueOf(bundle.getBoolean("HOME_ACTIVITY_EXTRA_OPEN_HOME_PAGE"));
        getIntent().removeExtra("HOME_ACTIVITY_EXTRA_OPEN_HOME_PAGE");
        b = Boolean.valueOf(bundle.getBoolean("HOME_ACTIVITY_EXTRA_OPEN_FORGOT_PASSWORD"));
        getIntent().removeExtra("HOME_ACTIVITY_EXTRA_OPEN_FORGOT_PASSWORD");
        s2 = bundle.getString("HOME_ACTIVITY_EXTRAS_FROM_SCREEN");
        getIntent().removeExtra("HOME_ACTIVITY_EXTRAS_FROM_SCREEN");
        if (StringUtils.isNullOrEmpty(s2)) goto _L4; else goto _L5
_L5:
        if (!s2.equalsIgnoreCase("APP_WIDGET")) goto _L7; else goto _L6
_L6:
        String s6;
        TrackingHelper.sendEntryChannel(EntryChannel.AppWidget);
        s6 = bundle.getString("source");
        getIntent().removeExtra("source");
        if (StringUtils.isNullOrEmpty(s6)) goto _L7; else goto _L8
_L8:
        if (!s6.equalsIgnoreCase("SEARCH_FROM_APP_WIDGET")) goto _L10; else goto _L9
_L9:
        c = true;
_L7:
        String s7;
        if (s2.equalsIgnoreCase("notification"))
        {
            String s3 = bundle.getString("actionJson");
            getIntent().removeExtra("actionJson");
            String s4 = bundle.getString("extras_notification_id");
            getIntent().removeExtra("extras_notification_id");
            FlipkartNotificationManager.sendNotificationReadEvent(s4);
            String s5 = bundle.getString("omniture");
            getIntent().removeExtra("omniture");
            if (!StringUtils.isNullOrEmpty(s5))
            {
                TrackingHelper.sendNotificationClicked(s5);
            }
            try
            {
                v = (Action)FlipkartApplication.getGsonInstance().fromJson(s3, com/flipkart/android/response/customwidgetitemvalue/Action);
                return;
            }
            catch (Exception exception)
            {
                return;
            }
        } else
        {
            v = null;
            return;
        }
_L10:
        if (!s6.equalsIgnoreCase("BANNER_CLICK_ON_APP_WIDGET")) goto _L7; else goto _L11
_L11:
        s7 = bundle.getString("action");
        getIntent().removeExtra("action");
        e = bundle.getInt("APP_WIDGET_BANNER_INDEX");
        getIntent().removeExtra("APP_WIDGET_BANNER_INDEX");
        if (s7 != null)
        {
            try
            {
                u = (Action)FlipkartApplication.getGsonInstance().fromJson(s7, com/flipkart/android/response/customwidgetitemvalue/Action);
            }
            catch (Exception exception1) { }
        }
          goto _L7
    }

    private void a(int i1, int j1, int k1)
    {
        i l1 = new i(this);
        AnalyticData analyticdata = new AnalyticData();
        analyticdata.setPageTypeUtils(FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil());
        analyticdata.setIsUserClick(false);
        l1.getWishList(i1, j1, k1, analyticdata);
    }

    private void a(int i1, int j1, TextView textview)
    {
        if (textview != null)
        {
            if (j1 == 0)
            {
                textview.setVisibility(4);
                return;
            }
            textview.setVisibility(0);
            if (i1 != j1)
            {
                if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 11)
                {
                    ObjectAnimator objectanimator = ObjectAnimator.ofFloat(textview, "rotationY", new float[] {
                        0.0F, 360F
                    });
                    objectanimator.setDuration(2000L);
                    objectanimator.setInterpolator(new DecelerateInterpolator());
                    objectanimator.addUpdateListener(new n(this, textview, j1));
                    objectanimator.addListener(new o(this, textview, j1));
                    objectanimator.start();
                    return;
                } else
                {
                    textview.setText((new StringBuilder()).append(j1).toString());
                    return;
                }
            }
        }
    }

    private void a(Fragment fragment, String s1)
    {
        showBackCarrot();
        if (o != null)
        {
            o.onFragmentPushed();
        }
        closeDrawerIfOpen();
        FragmentManager fragmentmanager = getSupportFragmentManager();
        if (o instanceof SearchFragment)
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(true));
            fragmentmanager.popBackStack();
        }
        if (o instanceof RefineCategoryFragment)
        {
            FlipkartPreferenceManager.instance().saveIsPoppingRefineByFragment(Boolean.valueOf(true));
            fragmentmanager.popBackStack();
        }
        removeNullSearchPage();
        FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
        fragmenttransaction.setCustomAnimations(0x7f040008, 0x7f040009, 0x7f040008, 0x7f040009);
        fragmenttransaction.addToBackStack(s1);
        fragmenttransaction.replace(0x7f0a006c, fragment, s1);
        fragmenttransaction.show(fragment);
        fragmenttransaction.commit();
        o = (FlipkartBaseFragment)fragment;
        if (!(o instanceof SearchFragment)) goto _L2; else goto _L1
_L1:
        getWindow().setSoftInputMode(16);
_L4:
        A = false;
        return;
_L2:
        try
        {
            getWindow().setSoftInputMode(32);
        }
        catch (Exception exception) { }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static void a(HomeFragmentHolderActivity homefragmentholderactivity, int i1, int j1, int k1)
    {
        homefragmentholderactivity.a(0, -1, -1);
    }

    static void a(HomeFragmentHolderActivity homefragmentholderactivity, int i1, int j1, TextView textview)
    {
        homefragmentholderactivity.a(i1, j1, textview);
    }

    static boolean a(HomeFragmentHolderActivity homefragmentholderactivity, boolean flag)
    {
        homefragmentholderactivity.A = true;
        return true;
    }

    static FlipkartBaseFragment b(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        return homefragmentholderactivity.o;
    }

    static String b(HomeFragmentHolderActivity homefragmentholderactivity, String s1)
    {
        homefragmentholderactivity.x = null;
        return null;
    }

    private void b()
    {
        InAppNotificationUtils.getUnReadCount(new f(this));
    }

    static void b(HomeFragmentHolderActivity homefragmentholderactivity, boolean flag)
    {
        FlipkartBaseFragment flipkartbasefragment = homefragmentholderactivity.getcurrentFragment();
        if (flipkartbasefragment != null && (flipkartbasefragment instanceof WishListFragment))
        {
            WishListFragment wishlistfragment = (WishListFragment)flipkartbasefragment;
            FkProductListContext fkproductlistcontext = wishlistfragment.getFkContext();
            if (fkproductlistcontext != null && (fkproductlistcontext.getParam() instanceof ProductsListParam))
            {
                ProductsListParam productslistparam = (ProductsListParam)fkproductlistcontext.getParam();
                if (productslistparam != null && productslistparam.getPageType() == PageTypeUtils.WishList)
                {
                    ArrayList arraylist = (new WishListDao(homefragmentholderactivity)).getAllWishListPids();
                    if (!flag)
                    {
                        Toast.makeText(FlipkartApplication.getAppContext(), "Error while Syncing Wishlist.Please try after sometime", 1).show();
                    }
                    wishlistfragment.refreshWishlistData(arraylist);
                }
            }
        }
    }

    static ICartListener c(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        return homefragmentholderactivity.l;
    }

    static String c(HomeFragmentHolderActivity homefragmentholderactivity, String s1)
    {
        homefragmentholderactivity.y = null;
        return null;
    }

    private void c()
    {
        TrackingHelper.sendEntryChannel(EntryChannel.DeepLinking);
        WidgetAction.performAction(C, this, PageTypeUtils.DeepLink);
        A = true;
        C = null;
    }

    static FkLoadingDialog d(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        return homefragmentholderactivity.n;
    }

    static String e(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        return homefragmentholderactivity.m;
    }

    static void f(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        TextView textview = (TextView)homefragmentholderactivity.getSupportActionBar().getCustomView().findViewById(0x7f0a009e);
        TextView textview1 = (TextView)homefragmentholderactivity.getSupportActionBar().getCustomView().findViewById(0x7f0a0123);
        FlipkartPreferenceManager.instance().saveInAppUnreadCount(0);
        homefragmentholderactivity.a(0, 0, textview);
        homefragmentholderactivity.a(0, 0, textview1);
        ActionBarView.updateFlipkartLogo();
        (new WishListDao(FlipkartApplication.getAppContext())).deleteAll();
    }

    static boolean g(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        return homefragmentholderactivity.z;
    }

    static void h(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        homefragmentholderactivity.c();
    }

    static boolean i(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        AppUpgradeData appupgradedata = AppConfigUtils.getInstance().getAppUpgradeData();
        return appupgradedata != null && FlipkartPreferenceManager.instance().getAppVersionNumber() < appupgradedata.getLatestAppVersion();
    }

    static String j(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        return homefragmentholderactivity.y;
    }

    static String k(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        return homefragmentholderactivity.x;
    }

    public void addToCart(String s1, String s2, String s3, String s4, ProductSpecificSellerTypes productspecificsellertypes, SellerTypes sellertypes, ICartListener icartlistener, 
            AddCartLocation addcartlocation, String s5, AnalyticData analyticdata)
    {
        l = icartlistener;
        j.addToCart(s1, s2, s3, s4, productspecificsellertypes, sellertypes, addcartlocation, analyticdata);
        String s6 = "Adding product to cart.";
        if (s5 != null)
        {
            s6 = (new StringBuilder()).append(s6).append(" Size : ").append(s5).toString();
        }
        Toast.makeText(this, s6, 1).show();
    }

    public void callSearch(String s1, AutoSuggestWord autosuggestword, boolean flag, String s2)
    {
        Bundle bundle = new Bundle();
        bundle.putString("SEARCH_EXTRAS_QUERY", autosuggestword.getWord());
        bundle.putString("SEARCH_EXTRAS_STORE", autosuggestword.getStoreId());
        bundle.putString("SEARCH_EXTRAS_STORE_NAME", autosuggestword.getStoreTitle());
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_FILTERS", null);
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_TAGS", null);
        bundle.putString("PRODUCT_LIST_EXTRAS_SORT", null);
        bundle.putStringArrayList("PRODUCT_LIST_EXTRAS_PRODUCTS", null);
        bundle.putString("PRODUCT_LIST_EXTRAS_TITLE", null);
        bundle.putString("PRODUCT_LIST_EXTRAS_PATH", "Search");
        bundle.putString("PRODUCT_LIST_EXTRAS_PAGE_TYPE", PageTypeUtils.ProductList.name());
        bundle.putBoolean("PRODUCT_LIST_EXTRAS_AUGMENT_SEARCH", flag);
        bundle.putString("X-SEARCH-TYPE", s2);
        openProductList(bundle, "searchListFragment");
    }

    public void clearBackStack()
    {
        FragmentManager fragmentmanager = getSupportFragmentManager();
        if (fragmentmanager != null)
        {
            if (o != null)
            {
                o.onFragmentPushed();
            }
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(true));
            fragmentmanager.popBackStack(null, 1);
        }
    }

    public void clearStackAndLoadMultiWidgetScreen(String s1, String s2)
    {
        getSupportFragmentManager().popBackStack(null, 1);
        loadMultiWidgetScreen(s1, s2);
    }

    public void closeDrawerIfOpen()
    {
        if (f != null && f.isDrawerOpen(3))
        {
            f.closeDrawer(3);
        }
    }

    public void closeRefreshing()
    {
        if (s != null)
        {
            s.setVisibility(0);
        }
        if (r != null)
        {
            r.setVisibility(4);
        }
    }

    public void doBuyNow(String s1, String s2, String s3, ProductSpecificSellerTypes productspecificsellertypes, SellerTypes sellertypes, String s4)
    {
        TrackingHelper.sendBuyNowClicked(s1, s3, productspecificsellertypes, sellertypes, s4);
        Bundle bundle = (new WebviewHelper()).getBuyNowBundle(s2);
        if (bundle == null)
        {
            return;
        } else
        {
            WebViewFragment webviewfragment = new WebViewFragment();
            webviewfragment.setArguments(bundle);
            a(webviewfragment, "web");
            return;
        }
    }

    public void doLogin()
    {
        Intent intent = new Intent(this, com/flipkart/android/activity/LoginActivity);
        intent.putExtra("EXTRA_IS_FROM_FK_ACTIVITY", true);
        startActivityForResult(intent, 0);
    }

    public void doLogout()
    {
        n.showDlg("", "Logging out. Please wait...", null, false);
        i.doLogout();
    }

    public void doSearch(String s1, String s2, String s3, String s4, String as[], String as1[], String s5, 
            ArrayList arraylist, String s6, String s7, PageTypeUtils pagetypeutils, String s8, String s9)
    {
        closeDrawerIfOpen();
        Bundle bundle = new Bundle();
        bundle.putString("OMNITURE_DATA", s9);
        bundle.putString("SEARCH_EXTRAS_QUERY", s2);
        bundle.putString("SEARCH_EXTRAS_STORE", s3);
        bundle.putString("SEARCH_EXTRAS_STORE_NAME", s4);
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_FILTERS", as);
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_TAGS", as1);
        bundle.putString("PRODUCT_LIST_EXTRAS_SORT", s5);
        bundle.putStringArrayList("PRODUCT_LIST_EXTRAS_PRODUCTS", arraylist);
        bundle.putString("PRODUCT_LIST_EXTRAS_TITLE", s6);
        bundle.putString("PRODUCT_LIST_EXTRAS_PATH", s7);
        if (pagetypeutils == PageTypeUtils.ProductPage)
        {
            bundle.putString("PRODUCT_LIST_EXTRAS_PAGE_TYPE", PageTypeUtils.ProductPageRecommendation.name());
        } else
        if (pagetypeutils == PageTypeUtils.BrowseHistory)
        {
            bundle.putString("PRODUCT_LIST_EXTRAS_PAGE_TYPE", pagetypeutils.name());
        } else
        if (pagetypeutils == PageTypeUtils.ProductListNullPage)
        {
            bundle.putString("PRODUCT_LIST_EXTRAS_PAGE_TYPE", PageTypeUtils.ProductListRecommendation.name());
        } else
        {
            bundle.putString("PRODUCT_LIST_EXTRAS_PAGE_TYPE", PageTypeUtils.HomePageRecommendation.name());
        }
        openProductList(bundle, s1);
    }

    public void doSearch(String s1, String s2, String s3, String s4, String as[], String as1[], String s5, 
            ArrayList arraylist, String s6, String s7, boolean flag, String s8, String s9, boolean flag1, 
            String as2[])
    {
        Bundle bundle = new Bundle();
        bundle.putString("OMNITURE_DATA", s9);
        bundle.putString("SEARCH_EXTRAS_PINCODE", s1);
        bundle.putString("SEARCH_EXTRAS_QUERY", s2);
        bundle.putString("SEARCH_EXTRAS_STORE", s3);
        bundle.putString("SEARCH_EXTRAS_STORE_NAME", s4);
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_FILTERS", as);
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_TAGS", as1);
        bundle.putString("PRODUCT_LIST_EXTRAS_SORT", s5);
        bundle.putStringArrayList("PRODUCT_LIST_EXTRAS_PRODUCTS", arraylist);
        bundle.putString("PRODUCT_LIST_EXTRAS_TITLE", s6);
        bundle.putString("PRODUCT_LIST_EXTRAS_PATH", s7);
        bundle.putBoolean("PRODUCT_LIST_EXTRAS_AUGMENT_SEARCH", flag1);
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_VIEWS", as2);
        if (flag)
        {
            bundle.putString("PRODUCT_LIST_EXTRAS_PAGE_TYPE", PageTypeUtils.WishList.name());
            openProductList(bundle, "wishList");
            return;
        } else
        {
            bundle.putString("PRODUCT_LIST_EXTRAS_PAGE_TYPE", PageTypeUtils.ProductList.name());
            openProductList(bundle, "");
            return;
        }
    }

    public void doShowCart()
    {
        TrackingHelper.sendCartView();
        Bundle bundle = (new WebviewHelper()).getShowCartBundle();
        if (bundle == null)
        {
            return;
        } else
        {
            WebViewFragment webviewfragment = new WebViewFragment();
            webviewfragment.setArguments(bundle);
            a(webviewfragment, "web");
            return;
        }
    }

    public int getActionBarBgAlpha()
    {
        int i1;
        try
        {
            i1 = mActionBarBackgroundDrawable.getAlpha();
        }
        catch (NoSuchMethodError nosuchmethoderror)
        {
            return 255;
        }
        return i1;
    }

    public ActionBarState getActionBarState()
    {
        return actionBarState;
    }

    public void getAndUpdateCart(String s1)
    {
        if (!StringUtils.isNullOrEmpty(s1))
        {
            m = s1;
            n.showDlg("", "Loading...", this, true);
        }
        k.getCart();
    }

    public FlipkartBaseFragment getCurrentFragment()
    {
        return o;
    }

    public int getSearchAlpha()
    {
        if (mActionBarSearchDrawable != null)
        {
            return mActionBarSearchDrawable.getAlpha();
        } else
        {
            return 0;
        }
    }

    public FlipkartBaseFragment getcurrentFragment()
    {
        return o;
    }

    public void hideBackCarrotAndShowMenu()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(getResources().getDrawable(0x7f0200fd));
        if (p != null)
        {
            p.setVisible(true);
        }
        g.setDrawerIndicatorEnabled(true);
    }

    public void hideKeyPad(View view)
    {
        InputMethodManager inputmethodmanager = (InputMethodManager)getSystemService("input_method");
        if (inputmethodmanager != null && view != null)
        {
            inputmethodmanager.hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }

    public void hideMainMenu()
    {
        if (p != null)
        {
            p.setVisible(false);
        }
    }

    public void hideMenuAndShowBackCarrot()
    {
        if (p != null)
        {
            p.setVisible(false);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        g.setDrawerIndicatorEnabled(false);
    }

    public void initRefreshing()
    {
        if (s != null)
        {
            s.setVisibility(8);
        }
        if (r != null)
        {
            r.setVisibility(0);
        }
    }

    public boolean isDrawerOpen()
    {
        if (f != null)
        {
            return f.isDrawerOpen(3);
        } else
        {
            return false;
        }
    }

    public void loadFlyoutItem(String s1, String s2)
    {
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("MULTI_WIDGET_SCREEN_NAME", s1);
        bundle.putString("MULTI_WIDGET_SCREEN_ID", s2);
        MultiWidgetFragment multiwidgetfragment = new MultiWidgetFragment();
        multiwidgetfragment.setArguments(bundle);
        fragmenttransaction.replace(0x7f0a006d, multiwidgetfragment, "flyout");
        fragmenttransaction.commit();
    }

    public void loadHomeFragment()
    {
        FragmentManager fragmentmanager;
        try
        {
            fragmentmanager = getSupportFragmentManager();
        }
        catch (IllegalStateException illegalstateexception)
        {
            return;
        }
        catch (Exception exception)
        {
            return;
        }
        if (fragmentmanager == null)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        if (o != null)
        {
            o.onFragmentPushed();
            if (o instanceof SearchFragment)
            {
                getWindow().setSoftInputMode(32);
            }
        }
        fragmentmanager.popBackStackImmediate(null, 1);
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            b();
        }
    }

    public void loadHomeFragmentNotImmediate()
    {
        FragmentManager fragmentmanager = getSupportFragmentManager();
        if (fragmentmanager != null)
        {
            if (o != null)
            {
                o.onFragmentPushed();
            }
            fragmentmanager.popBackStack(null, 1);
            if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
            {
                b();
            }
        }
    }

    public void loadMultiWidgetScreen(String s1, String s2)
    {
        Bundle bundle = new Bundle();
        bundle.putString("MULTI_WIDGET_SCREEN_NAME", s1);
        bundle.putString("MULTI_WIDGET_SCREEN_ID", s2);
        s1.equals("homepage");
        MultiWidgetFragment multiwidgetfragment = new MultiWidgetFragment();
        multiwidgetfragment.setArguments(bundle);
        if (o != null)
        {
            o.onFragmentPushed();
        }
        closeDrawerIfOpen();
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        fragmenttransaction.replace(0x7f0a006c, multiwidgetfragment);
        fragmenttransaction.show(multiwidgetfragment);
        fragmenttransaction.commit();
        o = (FlipkartBaseFragment)multiwidgetfragment;
    }

    public void lockDrawer()
    {
        if (f != null)
        {
            f.setDrawerLockMode(1);
        }
    }

    public void onActivityResult(int i1, int j1, Intent intent)
    {
        super.onActivityResult(i1, j1, intent);
        w.onActivityResult(i1, j1, intent, new h(this));
        if (i1 != 34) goto _L2; else goto _L1
_L1:
        if (j1 != -1 || intent == null) goto _L4; else goto _L3
_L3:
        if (y != null && x != null)
        {
            ShareUtil.sendShareResultToServer(y, x, this);
            x = null;
            y = null;
        }
        TrackingHelper.sendShareCompleted(false);
_L6:
        return;
_L4:
        x = null;
        y = null;
        return;
_L2:
        if (i1 != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (j1 == -1 && (o instanceof WebViewFragment))
        {
            ((WebViewFragment)o).reloadWebview();
            return;
        }
        continue; /* Loop/switch isn't completed */
        if (i1 != 1 || j1 != -1) goto _L6; else goto _L5
_L5:
        String s1 = intent.getStringExtra("filterString");
        String s2 = intent.getStringExtra("PRODUCT_PAGE_UUID");
        if (s2 != null)
        {
            FkProductListContext fkproductlistcontext = (FkProductListContext)ContextCache.getInstance().getResponse(s2);
            if (fkproductlistcontext != null && o != null && (o instanceof MultiWidgetFragment))
            {
                ((MultiWidgetFragment)o).refreshFilterWidget(fkproductlistcontext, s1);
                return;
            }
        }
        if (true) goto _L6; else goto _L7
_L7:
    }

    public void onBackPressed()
    {
        if (p != null && !p.isVisible())
        {
            p.setVisible(true);
        }
        FlipkartPreferenceManager.instance().saveBackPressed(Boolean.valueOf(true));
        if (d)
        {
            A = false;
            d = false;
        }
        if (A)
        {
            A = false;
            finish();
        }
        if (this == null) goto _L2; else goto _L1
_L1:
        View view = ((ViewGroup)getWindow().getDecorView()).findViewWithTag("cue_tips_overlay");
        if (view == null) goto _L2; else goto _L3
_L3:
        CrashLoggerUtils.pushAndUpdate("On Back Press : HMF and removing cue tips");
        ((ViewGroup)getWindow().getDecorView()).removeView(view);
_L5:
        return;
_L2:
        if (f != null && f.isDrawerOpen(3))
        {
            closeDrawerIfOpen();
            return;
        }
        if (o == null || !(o instanceof FlipkartBaseFragment))
        {
            break; /* Loop/switch isn't completed */
        }
        if (o instanceof SearchFragment)
        {
            getWindow().setSoftInputMode(32);
        }
        if (o.handleBackPress()) goto _L5; else goto _L4
_L4:
        super.onBackPressed();
        return;
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        if (n != null)
        {
            n.dismissDlg();
            if (o != null)
            {
                o.handleBackPress();
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        ContextThemeWrapper contextthemewrapper = new ContextThemeWrapper(this, 0x7f0e0030);
        getTheme().setTo(contextthemewrapper.getTheme());
        super.onCreate(bundle);
        w = new UiLifecycleHelper(this, null);
        w.onCreate(bundle);
        FlipkartPreferenceManager.instance().saveIsPoppingRefineByFragment(Boolean.valueOf(false));
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.HomePage);
        n = new FkLoadingDialog(this);
        a();
        new AutoSuggestDao(this);
        setContentView(0x7f03001b);
        f = (DrawerLayout)findViewById(0x7f0a006b);
        g = new e(this, this, f, 0x7f0200ff, 0x7f0d0094, 0x7f0d002b);
        f.setDrawerListener(g);
        s = findViewById(0x7f0a006f);
        r = (ProgressBar)findViewById(0x7f0a006e);
        FragmentManager fragmentmanager;
        if (b.booleanValue())
        {
            b = Boolean.valueOf(false);
            openForgotPassword();
        } else
        if (a.booleanValue() || bundle == null)
        {
            a = Boolean.valueOf(false);
            loadMultiWidgetScreen("homepage", null);
            loadFlyoutItem("flyout", null);
        }
_L3:
        B = new IsLoggedInReceiver();
        registerReceiver(B, new IntentFilter(LOGGED_IN_ACTION_COMMAND));
        fragmentmanager = getSupportFragmentManager();
        if (fragmentmanager != null)
        {
            fragmentmanager.addOnBackStackChangedListener(new j(this, fragmentmanager));
        }
        j = new k(this);
        k = new l(this);
        i = new m(this);
        updateVariablesBasedOnLoginState(false);
        if (u != null && e != -1)
        {
            WidgetAction.performAction(u, this, PageTypeUtils.AppWidget);
            u = null;
            e = -1;
        } else
        if (v != null)
        {
            WidgetAction.performAction(v, this, PageTypeUtils.Notification);
            v = null;
        } else
        {
label0:
            {
                if (!c)
                {
                    break label0;
                }
                c = false;
                openSearchPage();
            }
        }
_L1:
        boolean flag;
        AppRateData appratedata;
        int i1;
        String s1;
        AppUpgradeData appupgradedata;
        int j1;
        int k1;
        String s2;
        if (bundle != null)
        {
            try
            {
                isFromKilledState = true;
                actionBarState = (ActionBarState)bundle.getSerializable("actionbar_savedstate");
                return;
            }
            catch (Exception exception)
            {
                return;
            }
        }
        break MISSING_BLOCK_LABEL_698;
        if (!FlipkartPreferenceManager.instance().isShowAppUpgradePopup().booleanValue() || !NetworkMonitor.isNetworkPresent())
        {
            break MISSING_BLOCK_LABEL_663;
        }
        appupgradedata = AppConfigUtils.getInstance().getAppUpgradeData();
        j1 = FlipkartPreferenceManager.instance().getAppLaunchCounts();
        k1 = FlipkartPreferenceManager.instance().getAppUpgradePromptShownCount();
        if (appupgradedata == null || !appupgradedata.isShowAppUpgradePrompt() || j1 == 0 || j1 % appupgradedata.getMinAppLaunchCounts() != 0 || k1 > appupgradedata.getMaxPromptCount() || FlipkartPreferenceManager.instance().getAppVersionNumber() >= appupgradedata.getLatestAppVersion())
        {
            break MISSING_BLOCK_LABEL_663;
        }
        s2 = ImageUtils.getImageUrl(appupgradedata.getAppIcon());
        CustomDialog.showAppUpgrade(this, appupgradedata.getAppUpgradeTitle(), appupgradedata.getAppUpgradeMessageHtml(), s2);
        flag = true;
_L2:
        if (!flag && FlipkartPreferenceManager.instance().isShowRateTheAppPopup().booleanValue() && NetworkMonitor.isNetworkPresent())
        {
            appratedata = AppConfigUtils.getInstance().getAppRateData();
            i1 = FlipkartPreferenceManager.instance().getAppLaunchCounts();
            if (appratedata != null && appratedata.isEnabled() && i1 != 0 && i1 % appratedata.getAppOpenCount() == 0)
            {
                if (FlipkartPreferenceManager.instance().getAppRatePromptShownCount() > appratedata.getMaxPromptCount())
                {
                    FlipkartPreferenceManager.instance().saveIsShowRateTheAppPopUp(Boolean.valueOf(false));
                } else
                {
                    s1 = ImageUtils.getImageUrl(appratedata.getAppIcon());
                    CustomDialog.showRateTheApp(this, appratedata.getTitle(), appratedata.getMessageHtml(), s1);
                }
            }
        }
          goto _L1
        flag = false;
          goto _L2
        actionBarState = ActionBarState.Home;
        TabContextCache.getInstance().putResponse("current_actionbar_state", null);
        return;
          goto _L3
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f0f0002, menu);
        t = menu;
        p = menu.findItem(0x7f0a0241);
        return super.onCreateOptionsMenu(menu);
    }

    public void onDestroy()
    {
        if (n != null)
        {
            n.dismissDlg();
            n = null;
        }
        ToastMessageUtils.dismissToast(this);
        CustomDialog.dismissDialog();
        w.onDestroy();
        unregisterReceiver(B);
        super.onDestroy();
    }

    public boolean onKeyUp(int i1, KeyEvent keyevent)
    {
        if (!(o instanceof SearchFragment) && keyevent != null && keyevent.getAction() == 1 && i1 == 82)
        {
            if (t != null)
            {
                t.performIdentifierAction(0x7f0a0241, 0);
            }
            return true;
        } else
        {
            return super.onKeyUp(i1, keyevent);
        }
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if (menuitem.getTitle() != null)
        {
            CrashLoggerUtils.pushAndUpdate((new StringBuilder("clicking menu item : ")).append(menuitem.getTitle()).toString());
        }
        switch (menuitem.getItemId())
        {
        default:
            return super.onOptionsItemSelected(menuitem);

        case 2131362369: 
            onprepare();
            return true;

        case 16908332: 
            if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            {
                d = true;
                TrackingHelper.sendUpCarrotClicked();
                onBackPressed();
                return true;
            } else
            {
                toggleDrawerLayout();
                return true;
            }

        case 2131362370: 
            doLogin();
            return true;

        case 2131362381: 
            doLogout();
            return true;

        case 2131362375: 
            openAddress();
            return true;

        case 2131362374: 
            trackOrder();
            return true;

        case 2131362373: 
            openMyOrders();
            return true;

        case 2131362378: 
            openStaticPage(WebviewAction.CONTACT_US);
            return true;

        case 2131362379: 
            openStaticPage(WebviewAction.POLICIES);
            return true;

        case 2131362372: 
            openWishListPage();
            return true;

        case 2131362376: 
            TrackingHelper.sendRateTheAppClicked();
            String s1 = getPackageName();
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("https://play.google.com/store/apps/details?id=")).append(s1).toString())));
            return true;

        case 2131362377: 
            shareTheApp();
            return true;

        case 2131362380: 
            openFlipkartFirstWebView();
            return true;
        }
    }

    protected void onPause()
    {
        super.onPause();
        w.onPause();
        z = true;
        if (n != null)
        {
            n.dismissDlg();
        }
    }

    protected void onPostCreate(Bundle bundle)
    {
        super.onPostCreate(bundle);
        g.syncState();
    }

    protected void onPostResume()
    {
        super.onPostResume();
        if (z && C != null)
        {
            c();
        }
        z = false;
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        return true;
    }

    protected void onResume()
    {
        super.onResume();
        ((FlipkartApplication)getApplicationContext()).setCurrentActivity(this);
        w.onResume();
        a();
        if (b.booleanValue())
        {
            b = Boolean.valueOf(false);
            openForgotPassword();
        } else
        if (a.booleanValue())
        {
            a = Boolean.valueOf(false);
            loadMultiWidgetScreen("homepage", null);
            loadFlyoutItem("flyout", null);
        }
        if (u != null && e != -1)
        {
            WidgetAction.performAction(u, this, PageTypeUtils.AppWidget);
            u = null;
            e = -1;
        } else
        if (v != null)
        {
            WidgetAction.performAction(v, this, PageTypeUtils.Notification);
            v = null;
        } else
        if (c)
        {
            c = false;
            openSearchPage();
        }
        try
        {
            AppEventsLogger.activateApp(getApplicationContext(), FacebookConstants.AppId);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if (ContextCache.getInstance() != null)
        {
            bundle.putSerializable("actionbar_savedstate", actionBarState);
        }
        w.onSaveInstanceState(bundle);
    }

    public void onprepare()
    {
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            t.findItem(0x7f0a0242).setVisible(false);
            t.findItem(0x7f0a024d).setVisible(true);
            t.findItem(0x7f0a0247).setVisible(true);
            t.findItem(0x7f0a0245).setVisible(true);
            t.findItem(0x7f0a0246).setVisible(false);
            MenuItem menuitem = t.findItem(0x7f0a0243);
            String s1 = FlipkartPreferenceManager.instance().getUserDisplayName();
            if (s1.length() > 12)
            {
                s1 = s1.substring(0, 12);
            }
            String s2 = (new StringBuilder()).append(s1).append("...").toString();
            menuitem.setVisible(true);
            menuitem.setTitle(s2);
        } else
        {
            t.findItem(0x7f0a0242).setVisible(true);
            t.findItem(0x7f0a0245).setVisible(false);
            t.findItem(0x7f0a024d).setVisible(false);
            t.findItem(0x7f0a0247).setVisible(false);
            t.findItem(0x7f0a0243).setVisible(false);
            t.findItem(0x7f0a0246).setVisible(true);
        }
        if (AppConfigUtils.getInstance().isEnableFlipkartFirst())
        {
            t.findItem(0x7f0a024c).setVisible(true);
            return;
        } else
        {
            t.findItem(0x7f0a024c).setVisible(false);
            return;
        }
    }

    public void openAddress()
    {
        Bundle bundle = (new WebviewHelper()).getMyAddresssBundle();
        if (bundle == null)
        {
            return;
        } else
        {
            WebViewFragment webviewfragment = new WebViewFragment();
            webviewfragment.setArguments(bundle);
            a(webviewfragment, "web");
            return;
        }
    }

    public void openAllCategories()
    {
        toggleDrawerLayout();
    }

    public void openDrawerLayout()
    {
        if (f != null && !A)
        {
            f.openDrawer(3);
        }
    }

    public void openFilterOptionsPage(RefineByCategoryResponse refinebycategoryresponse, boolean flag, String s1, boolean flag1)
    {
        AllFiltersFragment allfiltersfragment = new AllFiltersFragment();
        ContextCache contextcache = ContextCache.getInstance();
        UUID uuid = UUID.randomUUID();
        contextcache.putResponse(uuid.toString(), refinebycategoryresponse);
        Bundle bundle = new Bundle();
        bundle.putBoolean(FiltersListFragment.IS_FROM_OFFERS, flag1);
        bundle.putString("ALL_FILTERS_FRAG", uuid.toString());
        allfiltersfragment.setArguments(bundle);
        a(allfiltersfragment, "allrefineoptions");
    }

    public void openFlipkartFirstWebView()
    {
        Bundle bundle = (new WebviewHelper()).getFlipkartFirstBundle();
        if (bundle == null)
        {
            return;
        } else
        {
            WebViewFragment webviewfragment = new WebViewFragment();
            webviewfragment.setArguments(bundle);
            a(webviewfragment, "web");
            return;
        }
    }

    public void openForgotPassword()
    {
        Bundle bundle = (new WebviewHelper()).getForgotPasswordBundle();
        if (bundle == null)
        {
            return;
        } else
        {
            WebViewFragment webviewfragment = new WebViewFragment();
            webviewfragment.setArguments(bundle);
            a(webviewfragment, "web");
            return;
        }
    }

    public void openInAppNotificationPage()
    {
        FragmentManager fragmentmanager = getSupportFragmentManager();
        Fragment fragment = fragmentmanager.findFragmentByTag("inAppNotification");
        if (fragment != null && (fragment instanceof FlipkartBaseFragment) && (fragment instanceof InAppNotificationFragment))
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(true));
            fragmentmanager.popBackStack("inAppNotification", 1);
        }
        a(new InAppNotificationFragment(), "inAppNotification");
    }

    public void openMultiWidgetScreen(String s1, String s2, String s3, String s4)
    {
        if (s1.equals("homepage"))
        {
            clearStackAndLoadMultiWidgetScreen(s1, s2);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("OMNITURE_DATA", s4);
        bundle.putString("MULTI_WIDGET_SCREEN_NAME", s1);
        bundle.putString("MULTI_WIDGET_SCREEN_ID", s2);
        MultiWidgetFragment multiwidgetfragment = new MultiWidgetFragment();
        multiwidgetfragment.setArguments(bundle);
        if (s2 == null)
        {
            s2 = "";
        }
        if (s1 == null)
        {
            s1 = "";
        }
        String s5 = (new StringBuilder("componentScreen")).append(s1).append(s2).toString();
        FragmentManager fragmentmanager = getSupportFragmentManager();
        Fragment fragment = fragmentmanager.findFragmentByTag(s5);
        if (fragment != null && (fragment instanceof FlipkartBaseFragment) && (fragment instanceof MultiWidgetFragment))
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(true));
            fragmentmanager.popBackStack(s5, 1);
        }
        a(multiwidgetfragment, s5);
    }

    public void openMyOrders()
    {
        Bundle bundle = (new WebviewHelper()).getMyOrdersBundle();
        if (bundle == null)
        {
            return;
        } else
        {
            WebViewFragment webviewfragment = new WebViewFragment();
            webviewfragment.setArguments(bundle);
            a(webviewfragment, "web");
            return;
        }
    }

    public void openProductList(Bundle bundle, String s1)
    {
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("opening PL ")).append(s1).toString());
        Object obj;
        FragmentManager fragmentmanager;
        Fragment fragment;
        if (s1.equalsIgnoreCase("wishList"))
        {
            obj = new WishListFragment();
        } else
        if (s1.equalsIgnoreCase("searchListFragment"))
        {
            obj = new SearchListFragment();
        } else
        if (s1.equalsIgnoreCase("RECOMMENDATIONS"))
        {
            obj = new SimpleProductListFragment();
        } else
        if (s1.equalsIgnoreCase("BrowseHistoryWidget"))
        {
            obj = new SimpleProductListFragment();
        } else
        {
            obj = new FiltersListFragment();
        }
        ((ProductListFragment) (obj)).setArguments(bundle);
        fragmentmanager = getSupportFragmentManager();
        fragment = fragmentmanager.findFragmentByTag((new StringBuilder("productList")).append(s1).toString());
        if (fragment != null && (fragment instanceof FlipkartBaseFragment) && (fragment instanceof ProductListFragment))
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(true));
            fragmentmanager.popBackStack((new StringBuilder("productList")).append(s1).toString(), 1);
        }
        a(((Fragment) (obj)), (new StringBuilder("productList")).append(s1).toString());
    }

    public void openProductPage(String s1, String s2, int i1, String s3, String s4, String s5, String s6, 
            String s7, String as[], String as1[], String s8, String s9, AnalyticData analyticdata, boolean flag, 
            String as2[])
    {
        ProductPageFragment productpagefragment = new ProductPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PRODUCT_PAGE_SELECTED_INDEX", i1);
        bundle.putString("PRODUCT_PAGE_SELECTED_PRODUCT", s1);
        bundle.putString("PRODUCT_PAGE_SELECTED_PRODUCT_LISTING_ID", s2);
        bundle.putString("PRODUCT_PAGE_UUID", s3);
        bundle.putString("PRODUCT_PAGE_PATH_BAR_CODE", s9);
        bundle.putString("PRODUCT_PAGE_SLIDER_PRODUCT_LIST_TITLE", s4);
        bundle.putString("SEARCH_EXTRAS_QUERY", s5);
        bundle.putString("SEARCH_EXTRAS_STORE", s6);
        bundle.putString("SEARCH_EXTRAS_STORE_NAME", s7);
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_FILTERS", as);
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_TAGS", as1);
        bundle.putString("PRODUCT_LIST_EXTRAS_SORT", s8);
        bundle.putString("PRODUCT_LIST_EXTRAS_TITLE", s4);
        bundle.putString("X-SEARCH-TYPE", analyticdata.getSearchType());
        bundle.putStringArray("PRODUCT_LIST_EXTRAS_VIEWS", as2);
        bundle.putBoolean("PRODUCT_LIST_EXTRAS_AUGMENT_SEARCH", flag);
        productpagefragment.setArguments(bundle);
        a(productpagefragment, "product");
    }

    public void openProductPage(String s1, ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, int i1, String s2, String s3, 
            String s4)
    {
        int j1;
        FkProductListContext fkproductlistcontext;
        j1 = 0;
        fkproductlistcontext = new FkProductListContext();
        fkproductlistcontext.addProductIds(arraylist);
        HashMap hashmap1;
        if (StringUtils.isNullOrEmpty(arraylist1))
        {
            break MISSING_BLOCK_LABEL_79;
        }
        hashmap1 = new HashMap();
        int k1 = 0;
_L2:
        if (k1 >= arraylist.size())
        {
            break; /* Loop/switch isn't completed */
        }
        hashmap1.put(arraylist.get(k1), arraylist1.get(k1));
        k1++;
        if (true) goto _L2; else goto _L1
_L1:
        ContextCache contextcache;
        UUID uuid;
        Bundle bundle;
        ProductPageFragment productpagefragment;
        HashMap hashmap;
        try
        {
            fkproductlistcontext.setProdIdListingIdMap(hashmap1);
        }
        catch (Exception exception) { }
        if (StringUtils.isNullOrEmpty(arraylist2))
        {
            break MISSING_BLOCK_LABEL_139;
        }
        hashmap = new HashMap();
_L3:
        if (j1 >= arraylist.size())
        {
            break MISSING_BLOCK_LABEL_132;
        }
        hashmap.put(arraylist.get(j1), arraylist2.get(j1));
        j1++;
          goto _L3
        try
        {
            fkproductlistcontext.setProdIdOfferIdMap(hashmap);
        }
        catch (Exception exception1) { }
        contextcache = ContextCache.getInstance();
        uuid = UUID.randomUUID();
        contextcache.putResponse(uuid.toString(), fkproductlistcontext);
        bundle = new Bundle();
        bundle.putString("OMNITURE_DATA", s4);
        bundle.putInt("PRODUCT_PAGE_SELECTED_INDEX", i1);
        bundle.putString("PRODUCT_PAGE_UUID", uuid.toString());
        bundle.putString("PRODUCT_PAGE_SLIDER_PRODUCT_LIST_TITLE", s2);
        productpagefragment = new ProductPageFragment();
        productpagefragment.setArguments(bundle);
        if (s1.equalsIgnoreCase("RECOMMENDATIONS"))
        {
            FragmentManager fragmentmanager = getSupportFragmentManager();
            if (!StringUtils.isNullOrEmpty(s4))
            {
                Fragment fragment = fragmentmanager.findFragmentByTag(s4);
                if (fragment != null && (fragment instanceof FlipkartBaseFragment) && (fragment instanceof ProductPageFragment))
                {
                    FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(true));
                    fragmentmanager.popBackStack("RECOMMENDATIONS", 1);
                }
            } else
            {
                s4 = "product";
            }
            a(productpagefragment, s4);
            return;
        } else
        {
            a(productpagefragment, "product");
            return;
        }
    }

    public void openProductPage(ArrayList arraylist, ArrayList arraylist1, int i1, String s1, AnalyticData analyticdata)
    {
        FkProductListContext fkproductlistcontext = new FkProductListContext();
        fkproductlistcontext.addProductIds(arraylist);
        if (arraylist1 != null)
        {
            HashMap hashmap = new HashMap();
            int j1 = 0;
            do
            {
                try
                {
                    if (j1 >= arraylist.size())
                    {
                        break;
                    }
                    hashmap.put(arraylist.get(j1), arraylist1.get(j1));
                }
                catch (Exception exception)
                {
                    break;
                }
                j1++;
            } while (true);
            fkproductlistcontext.setProdIdListingIdMap(hashmap);
        }
        ContextCache contextcache = ContextCache.getInstance();
        UUID uuid = UUID.randomUUID();
        contextcache.putResponse(uuid.toString(), fkproductlistcontext);
        Bundle bundle = new Bundle();
        bundle.putInt("PRODUCT_PAGE_SELECTED_INDEX", i1);
        bundle.putString("PRODUCT_PAGE_UUID", uuid.toString());
        bundle.putString("PRODUCT_PAGE_SLIDER_PRODUCT_LIST_TITLE", s1);
        ProductPageFragment productpagefragment = new ProductPageFragment();
        productpagefragment.setArguments(bundle);
        a(productpagefragment, "product");
    }

    public void openProductPage(List list, String s1, String s2, int i1, String s3, String s4, String s5, 
            String s6)
    {
        ProductPageFragment productpagefragment = new ProductPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("OMNITURE_DATA", s6);
        bundle.putInt("PRODUCT_PAGE_SELECTED_INDEX", i1);
        bundle.putString("PRODUCT_PAGE_SELECTED_PRODUCT", s1);
        bundle.putString("PRODUCT_PAGE_SELECTED_PRODUCT_LISTING_ID", s2);
        bundle.putString("PRODUCT_PAGE_UUID", s3);
        bundle.putString("PRODUCT_PAGE_SLIDER_PRODUCT_LIST_TITLE", s4);
        bundle.putStringArrayList("PRODUCT_PAGE_SELECTED_SIZE", new ArrayList());
        productpagefragment.setArguments(bundle);
        a(productpagefragment, "product");
    }

    public void openProductPageImageGallary(String s1, int i1, int j1, String s2)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("PRODUCT_PAGE_IMAGE_SELECTED_INDEX", i1);
        bundle.putInt("PRODUCT_PAGE_SELECTED_INDEX", j1);
        bundle.putString("PRODUCT_PAGE_UUID", s2);
        ProductPageImageGallaryFragment productpageimagegallaryfragment = new ProductPageImageGallaryFragment();
        productpageimagegallaryfragment.setArguments(bundle);
        a(productpageimagegallaryfragment, "productImageGallery");
    }

    public void openProductPageMultipleSellers(int i1, String s1, String s2)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("PRODUCT_PAGE_SELECTED_INDEX", i1);
        bundle.putString("PRODUCT_PAGE_UUID", s1);
        ProductPageMultipleSellersFragment productpagemultiplesellersfragment = new ProductPageMultipleSellersFragment();
        productpagemultiplesellersfragment.setArguments(bundle);
        a(productpagemultiplesellersfragment, "multiplesellers");
    }

    public void openProductPageReviewsAndRating(int i1, String s1, boolean flag, String s2, long l1)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("PRODUCT_PAGE_SELECTED_INDEX", i1);
        bundle.putLong("PRODUCT_PAGE_TOTAL_REVIEW_COUNT", l1);
        bundle.putString("PRODUCT_PAGE_UUID", s1);
        bundle.putBoolean("PRODUCT_PAGE_IS_SIZE_SELECTED", flag);
        ProductPageReviewFragment productpagereviewfragment = new ProductPageReviewFragment();
        productpagereviewfragment.setArguments(bundle);
        a(productpagereviewfragment, "productReview");
    }

    public void openProductPageSpecification(int i1, String s1, boolean flag, String s2)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("PRODUCT_PAGE_SELECTED_INDEX", i1);
        bundle.putString("PRODUCT_PAGE_UUID", s1);
        bundle.putBoolean("PRODUCT_PAGE_IS_SIZE_SELECTED", flag);
        ProductPageSpecificationFragment productpagespecificationfragment = new ProductPageSpecificationFragment();
        productpagespecificationfragment.setArguments(bundle);
        a(productpagespecificationfragment, "productSpecification");
    }

    public void openProductPageSumary(int i1, String s1, boolean flag, String s2)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("PRODUCT_PAGE_SELECTED_INDEX", i1);
        bundle.putString("PRODUCT_PAGE_UUID", s1);
        bundle.putBoolean("PRODUCT_PAGE_IS_SIZE_SELECTED", flag);
        ProductPageSummaryFragment productpagesummaryfragment = new ProductPageSummaryFragment();
        productpagesummaryfragment.setArguments(bundle);
        a(productpagesummaryfragment, "productSumary");
    }

    public void openRefineByOptionsPage(RefineByCategoryResponse refinebycategoryresponse, String s1)
    {
        RefineCategoryFragment refinecategoryfragment = new RefineCategoryFragment();
        ContextCache contextcache = ContextCache.getInstance();
        UUID uuid = UUID.randomUUID();
        contextcache.putResponse(uuid.toString(), refinebycategoryresponse);
        Bundle bundle = new Bundle();
        bundle.putString(RefineCategoryFragment.REFINE_BY_FRAGMENT, uuid.toString());
        refinecategoryfragment.setArguments(bundle);
        a(refinecategoryfragment, "refinefragement");
    }

    public void openSearchPage()
    {
        a(new SearchFragment(), "search");
    }

    public void openSearchPageWithQuery(String s1, String s2)
    {
        SearchFragment searchfragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("SEARCH_QUERY", s2);
        searchfragment.setArguments(bundle);
        a(searchfragment, "search");
    }

    public void openSellerInfoPage(String s1, String s2)
    {
        Bundle bundle = new Bundle();
        bundle.putString("SELLER_ID_QUERY", s2);
        SellerFragment sellerfragment = new SellerFragment();
        sellerfragment.setArguments(bundle);
        a(sellerfragment, "sellerinfo");
    }

    public void openStaticPage(WebviewAction webviewaction)
    {
        TrackingHelper.sendContactUs();
        Bundle bundle = (new WebviewHelper()).getStaticPageBundle(webviewaction);
        if (bundle == null)
        {
            return;
        } else
        {
            WebViewFragment webviewfragment = new WebViewFragment();
            webviewfragment.setArguments(bundle);
            a(webviewfragment, "web");
            return;
        }
    }

    public void openWebView(String s1, boolean flag)
    {
        Bundle bundle = new Bundle();
        bundle.putString("WEBVIEW_EXTRAS_URL", s1);
        bundle.putInt("WEBVIEW_EXTRAS_VERB", 0);
        bundle.putBoolean("WEBVIEW_EXTRAS_DISABLE_LOADING_DIALOG", flag);
        WebViewFragment webviewfragment = new WebViewFragment();
        webviewfragment.setArguments(bundle);
        a(webviewfragment, "web");
    }

    public void openWishListPage()
    {
        if (!FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            doSearch(null, "", "", "", null, null, "", (new WishListDao(this)).getAllWishListPids(), null, "", true, null, null, false, null);
            return;
        } else
        {
            doSearch(null, "", "", "", null, null, "", null, null, "", true, null, null, false, null);
            return;
        }
    }

    public void performLoggedInActions(boolean flag)
    {
        int i1 = 0;
        getAndUpdateCart(null);
        b();
        FlipkartBaseFragment flipkartbasefragment = getcurrentFragment();
        if (flipkartbasefragment != null && (flipkartbasefragment instanceof InAppNotificationFragment))
        {
            ((InAppNotificationFragment)flipkartbasefragment).refreshView();
        }
        ActionBarView.updateFlipkartLogo();
        if (flag)
        {
            ArrayList arraylist = (new WishListDao(FlipkartApplication.getAppContext())).getAllWishListPids();
            if (arraylist.size() > 0)
            {
                Collections.reverse(arraylist);
                String as[] = new String[arraylist.size()];
                for (; i1 < arraylist.size(); i1++)
                {
                    as[i1] = (String)arraylist.get(i1);
                }

                (new g(this)).addToWishList(as, new AnalyticData(null, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
                return;
            } else
            {
                a(0, -1, -1);
                return;
            }
        } else
        {
            a(0, -1, -1);
            return;
        }
    }

    public void popFragmentStack()
    {
        FragmentManager fragmentmanager = getSupportFragmentManager();
        try
        {
            if (o instanceof SearchFragment)
            {
                getWindow().setSoftInputMode(32);
            }
            fragmentmanager.popBackStack();
            return;
        }
        catch (Throwable throwable)
        {
            return;
        }
    }

    public void purgeAndPushOrderWebView(String s1)
    {
        getSupportFragmentManager().popBackStackImmediate();
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            openWebView((new StringBuilder()).append(GsonRequest.BASE_WEB_URL_SECURE).append("/order_details?order_id=").append(s1).toString(), true);
            return;
        } else
        {
            loadHomeFragment();
            return;
        }
    }

    public void refreshPage()
    {
        FlipkartBaseFragment flipkartbasefragment = getcurrentFragment();
        if (flipkartbasefragment != null && (flipkartbasefragment instanceof MultiWidgetFragment))
        {
            ((MultiWidgetFragment)flipkartbasefragment).refreshPage();
        }
    }

    public void removeNullSearchPage()
    {
        FragmentManager fragmentmanager = getSupportFragmentManager();
        if ((o instanceof ProductListFragment) && ((ProductListFragment)o).getNullSearchPageValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(true));
            fragmentmanager.popBackStack();
        }
    }

    public void setActionBarBgAlpha(int i1)
    {
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() <= 18)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        if (o instanceof MultiWidgetFragment)
        {
            mActionBarBackgroundDrawable.setAlpha(i1);
            return;
        }
        try
        {
            mActionBarBackgroundDrawable.setAlpha(255);
            return;
        }
        catch (NoSuchMethodError nosuchmethoderror) { }
    }

    public void setActionBarMinAlpha(int i1)
    {
        if (h < i1)
        {
            setActionBarBgAlpha(i1);
        }
        if (h != -1)
        {
            h = i1;
        }
    }

    public void setActionBarState(ActionBarState actionbarstate)
    {
        actionBarState = actionbarstate;
    }

    public void setSearchAlpha(int i1)
    {
        if (getSupportActionBar() != null)
        {
            ImageView imageview = (ImageView)getSupportActionBar().getCustomView().findViewById(0x7f0a0068);
            if (imageview != null)
            {
                if (i1 != 0 && imageview.getVisibility() != 0)
                {
                    imageview.setVisibility(0);
                }
                imageview.setAlpha(i1);
                imageview.setOnClickListener(new p(this));
            }
        }
    }

    public void shareFacebookDialog(String s1, String s2, String s3, String s4)
    {
        y = s3;
        x = s4;
        try
        {
            FacebookDialog facebookdialog = (new com.facebook.widget.FacebookDialog.ShareDialogBuilder(this)).setLink(s1).setDescription(s2).build();
            w.trackPendingDialogCall(facebookdialog.present());
        }
        catch (Exception exception)
        {
            CustomDialog.showAlertMessage("Error", "Looks like your facebook app is not updated! Please update and try again.", false, this);
        }
        TrackingHelper.sendShareInitiated(true);
    }

    public void shareTheApp()
    {
        TrackingHelper.sendShareTheAppClicked();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "Try out Flipkart's app");
        intent.putExtra("android.intent.extra.TEXT", "Hey, have you tried Flipkart's new mobile app yet? I think you'd like it. \n\nhttp://www.flipkart.com/mobile-apps");
        startActivity(Intent.createChooser(intent, "Choose..."));
    }

    public void shareTwitter(String s1, String s2, String s3, String s4)
    {
        y = s3;
        x = s4;
        TwitterShare.share("twitter", s2, s1, this, 34);
        TrackingHelper.sendShareInitiated(false);
    }

    public void showBackCarrot()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        g.setDrawerIndicatorEnabled(false);
    }

    public void showMenu()
    {
        if (p != null)
        {
            p.setVisible(true);
        }
    }

    public void toggleDrawerLayout()
    {
        if (!g.isDrawerIndicatorEnabled())
        {
            d = true;
            TrackingHelper.sendUpCarrotClicked();
            onBackPressed();
        } else
        if (f != null)
        {
            if (f.isDrawerOpen(3))
            {
                f.closeDrawer(3);
                return;
            } else
            {
                f.openDrawer(3);
                return;
            }
        }
    }

    public void trackOrder()
    {
        Bundle bundle = (new WebviewHelper()).getTrackOrderBundle();
        if (bundle == null)
        {
            return;
        } else
        {
            WebViewFragment webviewfragment = new WebViewFragment();
            webviewfragment.setArguments(bundle);
            a(webviewfragment, "web");
            return;
        }
    }

    public void unlockDrawer()
    {
        if (f != null)
        {
            f.setDrawerLockMode(0);
        }
    }

    public void updateInAppNotificationCount()
    {
        a(0, 0, (TextView)getSupportActionBar().getCustomView().findViewById(0x7f0a0123));
    }

    public void updateVariablesBasedOnLoginState(boolean flag)
    {
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            performLoggedInActions(flag);
            return;
        } else
        {
            getAndUpdateCart(null);
            return;
        }
    }

    static 
    {
        (new StringBuilder("ASIMO.")).append(com/flipkart/android/activity/HomeFragmentHolderActivity.getSimpleName());
    }

    private class IsLoggedInReceiver extends BroadcastReceiver
    {

        private HomeFragmentHolderActivity a;

        public void onReceive(Context context, Intent intent)
        {
            try
            {
                intent.getBooleanExtra("updateLogo", false);
            }
            catch (Exception exception) { }
            if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
            {
                a.performLoggedInActions(true);
                return;
            } else
            {
                HomeFragmentHolderActivity.f(a);
                return;
            }
        }

        public IsLoggedInReceiver()
        {
            a = HomeFragmentHolderActivity.this;
            super();
        }
    }

}
