// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.flipkart.android.cart.Cart;
import com.flipkart.android.cart.CartHandler;
import com.flipkart.android.datagovernance.BatchNetworkingHandler;
import com.flipkart.android.login.LoginType;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.config:
//            FlipkartDeviceInfoProvider

public class FlipkartPreferenceManager
{

    public static final String GCM_ID_SENT_TO_SERVER = a("gcm_id_sent_to_server");
    public static final String IS_NEW_OMNITURE_VISITOR_ID = a("is_new_omniture_visitor_id");
    public static final String KEY_APP_CONFIG = a("app_config");
    public static final String KEY_APP_LAUNCH_COUNTS = a("app_launch_counts");
    public static final String KEY_APP_RATE_PROMPT_SHOWN_COUNT = a("app_rate_prompt_show_count");
    public static final String KEY_APP_UPGRADE_PROMPT_SHOWN_COUNT = a("app_upgrade_prompt_show_count");
    public static final String KEY_APP_VERSION_GCM = a("app_version_for_gcm");
    public static final String KEY_APP_WIDGET_IMAGE_POSITION = a("app_widget_image_position");
    public static final String KEY_APP_WIDGET_RESPONSE = a("app_widget_json_response");
    public static final String KEY_APP_WIDGET_UPDATE_COUNT = a("app_widget_update_count");
    public static final String KEY_BACK_PRESSED = a("back_pressed");
    public static final String KEY_BROWSER_FAMILY = a("browser_family");
    public static final String KEY_CART_ITEMS = a("cart_items");
    public static final String KEY_DISMISS_PINCODE_WIDGET = a("dismiss_pincode_widget");
    public static final String KEY_FADEOUT_VIEW_HEIGHT = a("fadeout_view_height");
    public static final String KEY_FIRSTLOAD_TIMESTAMP = a("first_load_time");
    public static final String KEY_GCM_AVAILABLE = a("app_gcm_available");
    public static final String KEY_IMAGE_MATRIX = a("key_image_matrix");
    public static final String KEY_INAPP_UNREAD_COUNT = a("key_inapp_unread_count");
    public static final String KEY_IS_ADX_REFERRAL_RECEIVED = a("is_adx_referral_received");
    public static final String KEY_IS_APP_UPGRADE_NOTIFICATION_SHOWN = a("is_app_upgrade_notification_shown");
    public static final String KEY_IS_DDAY_POPUP_SHOWN = a("is_dday_opup_shown");
    public static final String KEY_IS_FIRST_TIME_ADD_TO_WISHLIST = a("is_first_time_add_to_wishlist");
    public static final String KEY_IS_FIRST_TIME_FILTER_LOAD = a("is_first_time_filter_load");
    public static final String KEY_IS_FIRST_TIME_HOMAEPAGE_LOAD = a("is_first_time_home_page_load");
    public static final String KEY_IS_FIRST_TIME_IN_APP_NOTIFICATION_PAGE_LOAD = a("is_first_time_in_app_notification_page_load");
    public static final String KEY_IS_FIRST_TIME_LOAD = a("is_first_time_load");
    public static final String KEY_IS_FIRST_TIME_PROD_IMAGE_PAGE_LOAD = a("is_first_time_prod_image_page_load");
    public static final String KEY_IS_FIRST_TIME_PROD_LIST_LOAD = a("is_first_time_prod_list_load");
    public static final String KEY_IS_FIRST_TIME_PROD_PAGE_LOAD = a("is_first_time_prod_page_load");
    public static final String KEY_IS_FIRST_TIME_REFINE_CAT_LOAD = a("is_first_time_refine_cat_load");
    public static final String KEY_IS_FIRST_TIME_WISHLIST_LOAD = a("is_first_time_wishlist_load");
    public static final String KEY_IS_LAST_TIME_APP_CRASH = a("is_last_time_app_crash");
    public static final String KEY_IS_LOGGED_IN = a("is_logged_in");
    public static final String KEY_IS_LOGIN_SHOWN_ON_FIRST_LOAD = a("is_login_shown_on_first_load");
    public static final String KEY_IS_NOKIA_DEVICE = a("app_is_nokia_device");
    public static final String KEY_IS_POPPING_ALLREFINE_FRAGMENT = a("is_popping_allrefine_fragment");
    public static final String KEY_IS_POPPING_PRODUCT_PAGE_FRAGMENT = a("is_popping_productpage_fragment");
    public static final String KEY_IS_POPPING_REFINE_BY_FRAGMENT = a("is_popping_refineby_fragment");
    public static final String KEY_IS_POPPING_SEARCH_FRAGMENT = a("is_popping_search_fragment");
    public static final String KEY_IS_PREBURN_APP = a("is_preburn_app");
    public static final String KEY_IS_PULL_OUT_SHOW = a("pull_out_show");
    public static final String KEY_IS_REFERRER_SENT = a("is_referrer_sent");
    public static final String KEY_IS_SHOW_APP_UPGRADE_NOTIFICATION = a("is_show_app_upgrade_notification");
    public static final String KEY_IS_SHOW_APP_UPGRADE_POPUP = a("is_show_app_upgrade_popup");
    public static final String KEY_IS_SHOW_RATE_THE_APP_POPUP = a("is_show_rate_the_app_popup");
    public static final String KEY_LAST_LOGIN_TYPE = a("last_login_type");
    public static final String KEY_LAST_PAGE_TYPE = a("last_page_type");
    public static final String KEY_LOGIN_ACCESS_EXPIRES = a("login_access_expires");
    public static final String KEY_LOGIN_ACCESS_TOKEN = a("login_access_token");
    public static final String KEY_NOTIFICATION_IDS = a("notification_ids");
    public static final String KEY_NOTIFICATION_REG_ID = a("notification");
    public static final String KEY_NSID = a("nsid");
    public static final String KEY_OFFER_TERMS_TEXT = a("offer_terms_text");
    public static final String KEY_OMNITURE_VISITOR_ID = a("omniture_visitor_id");
    public static final String KEY_OS_VERSION = a("key_os_version");
    public static final String KEY_PIN_CODE = a("user_pin_code");
    public static final String KEY_REFERRER_VALUE = a("referrer_value");
    public static final String KEY_REGISTER_KEY = a("register_key");
    public static final String KEY_SECURE_TOKEN = a("secure_token");
    public static final String KEY_SN = a("sn");
    public static final String KEY_TTL_SAVE_TIME = a("key_ttl_save_time");
    public static final String KEY_TTL_TIME = a("key_ttl_time");
    public static final String KEY_USER_ACCOUNT_ID = a("user_account_id");
    public static final String KEY_USER_AGENT = a("user_agent");
    public static final String KEY_USER_EMAIL = a("user_email");
    public static final String KEY_USER_FIRST_NAME = a("user_first_name");
    public static final String KEY_USER_LAST_NAME = a("user_last_name");
    public static final String KEY_USER_SUBSCRIBED_FF = a("flipkartfirst_subscribed_user");
    public static final String KEY_VERSION_CODE = a("version_code");
    public static final String KEY_VERSION_NUMBER = a("version_number");
    public static final String KEY_VID = a("vid");
    public static final String NOTIFICATION_COUNT = a("notification_count");
    private static FlipkartPreferenceManager a = null;
    private SharedPreferences b;

    private FlipkartPreferenceManager()
    {
    }

    private static String a(String s)
    {
        return (new StringBuilder("com.flipkart.app.ecom.")).append(s).toString();
    }

    public static FlipkartPreferenceManager instance()
    {
        if (a != null) goto _L2; else goto _L1
_L1:
        com/flipkart/android/config/FlipkartPreferenceManager;
        JVM INSTR monitorenter ;
        if (a == null)
        {
            a = new FlipkartPreferenceManager();
        }
        com/flipkart/android/config/FlipkartPreferenceManager;
        JVM INSTR monitorexit ;
_L2:
        return a;
        Exception exception;
        exception;
        throw exception;
    }

    public void clearUserSessionVariables()
    {
        saveNsid("");
        saveVid("");
        saveSn("");
        saveSecureToken("");
        saveUserEmail("");
        saveUserAccountId("");
        saveUserFirstName("");
        saveUserLastName("");
        saveIsLoggedIn(Boolean.valueOf(false));
        saveUserFlipkartFirstStatus(false);
        CartHandler.save(new Cart());
    }

    public String getAppConfig()
    {
        return b.getString(KEY_APP_CONFIG, "");
    }

    public int getAppLaunchCounts()
    {
        return b.getInt(KEY_APP_LAUNCH_COUNTS, 0);
    }

    public int getAppRatePromptShownCount()
    {
        return b.getInt(KEY_APP_RATE_PROMPT_SHOWN_COUNT, 0);
    }

    public int getAppUpgradePromptShownCount()
    {
        return b.getInt(KEY_APP_UPGRADE_PROMPT_SHOWN_COUNT, 0);
    }

    public String getAppVersionCode()
    {
        return b.getString(KEY_VERSION_CODE, "");
    }

    public int getAppVersionForGcm()
    {
        return b.getInt(KEY_APP_VERSION_GCM, 0x80000000);
    }

    public int getAppVersionNumber()
    {
        return b.getInt(KEY_VERSION_NUMBER, 0);
    }

    public int getAppWidgetCurrentImagePosition()
    {
        return b.getInt(KEY_APP_WIDGET_IMAGE_POSITION, -1);
    }

    public String getAppWidgetResponse()
    {
        return b.getString(KEY_APP_WIDGET_RESPONSE, "");
    }

    public int getAppWidgetUpadateCount()
    {
        return b.getInt(KEY_APP_WIDGET_UPDATE_COUNT, 0);
    }

    public String getBrowserFamily()
    {
        return b.getString(KEY_BROWSER_FAMILY, "");
    }

    public String getCartItems()
    {
        return b.getString(KEY_CART_ITEMS, "");
    }

    public int getFadeOutViewHeight()
    {
        return b.getInt(KEY_FADEOUT_VIEW_HEIGHT, 100);
    }

    public long getFirstLoadTime()
    {
        return b.getLong(KEY_FIRSTLOAD_TIMESTAMP, 0L);
    }

    public boolean getGcmIdSentToServerStatus()
    {
        return b.getBoolean(GCM_ID_SENT_TO_SERVER, true);
    }

    public String getImageMatrix()
    {
        return b.getString(KEY_IMAGE_MATRIX, "");
    }

    public int getInAppUnreadCount()
    {
        return b.getInt(KEY_INAPP_UNREAD_COUNT, 0);
    }

    public boolean getIsNewOmnitureVisitorId()
    {
        return b.getBoolean(IS_NEW_OMNITURE_VISITOR_ID, false);
    }

    public LoginType getLastLoginType()
    {
        int i = b.getInt(KEY_LAST_LOGIN_TYPE, LoginType.Unknown.ordinal());
        if (LoginType.Facebook.ordinal() == i)
        {
            return LoginType.Facebook;
        }
        if (LoginType.Google.ordinal() == i)
        {
            return LoginType.Google;
        }
        if (LoginType.Direct.ordinal() == i)
        {
            return LoginType.Direct;
        } else
        {
            return LoginType.Unknown;
        }
    }

    public String getLastOsVersion(Context context)
    {
        return b.getString(KEY_OS_VERSION, "");
    }

    public int getLastPageType()
    {
        return b.getInt(KEY_LAST_PAGE_TYPE, PageTypeUtils.None.ordinal());
    }

    public PageTypeUtils getLastPageTypeInPageTypeUtil()
    {
        return PageTypeUtils.values()[instance().getLastPageType()];
    }

    public long getLoginAccessExpires()
    {
        return b.getLong(KEY_LOGIN_ACCESS_EXPIRES, 0L);
    }

    public String getLoginAccessToken()
    {
        return b.getString(KEY_LOGIN_ACCESS_TOKEN, "");
    }

    public int getNotificationCount()
    {
        return b.getInt(NOTIFICATION_COUNT, 0);
    }

    public String getNotificationIds()
    {
        return b.getString(KEY_NOTIFICATION_IDS, "");
    }

    public String getNotificationRegId()
    {
        return b.getString(KEY_NOTIFICATION_REG_ID, "");
    }

    public String getNsid()
    {
        return b.getString(KEY_NSID, "");
    }

    public String getOfferText(String s)
    {
        return b.getString(KEY_OFFER_TERMS_TEXT, "");
    }

    public String getOmnitureVisitorId()
    {
        return b.getString(KEY_OMNITURE_VISITOR_ID, "");
    }

    public String getReferrerValue()
    {
        return b.getString(KEY_REFERRER_VALUE, "");
    }

    public String getRegisterKey()
    {
        return b.getString(KEY_REGISTER_KEY, "");
    }

    public String getSecureToken()
    {
        return b.getString(KEY_SECURE_TOKEN, "");
    }

    public String getSn()
    {
        return b.getString(KEY_SN, "");
    }

    public long getTTLSavedTime()
    {
        return b.getLong(KEY_TTL_SAVE_TIME, 0L);
    }

    public long getTTLTime()
    {
        return b.getLong(KEY_TTL_TIME, 0L);
    }

    public String getUserAccountId()
    {
        return b.getString(KEY_USER_ACCOUNT_ID, "");
    }

    public String getUserAgent()
    {
        return b.getString(KEY_USER_AGENT, "");
    }

    public String getUserDisplayName()
    {
        String s;
        if (StringUtils.isNullOrEmpty(getUserEmail()))
        {
            s = "User";
        } else
        {
            s = getUserEmail();
        }
        if (!StringUtils.isNullOrEmpty(getUserFirstName()))
        {
            s = getUserFirstName();
            if (!StringUtils.isNullOrEmpty(getUserLastName()))
            {
                s = (new StringBuilder()).append(s).append(" ").append(getUserLastName()).toString();
            }
        }
        return s;
    }

    public String getUserEmail()
    {
        return b.getString(KEY_USER_EMAIL, "");
    }

    public String getUserFirstName()
    {
        return b.getString(KEY_USER_FIRST_NAME, "");
    }

    public String getUserLastName()
    {
        return b.getString(KEY_USER_LAST_NAME, "");
    }

    public String getUserPinCode()
    {
        return b.getString(KEY_PIN_CODE, "");
    }

    public Boolean getUsersFfStatus()
    {
        return Boolean.valueOf(b.getBoolean(KEY_USER_SUBSCRIBED_FF, false));
    }

    public String getVid()
    {
        return b.getString(KEY_VID, "");
    }

    public void initialize(Context context)
    {
        b = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Boolean isAdxReferralReceived()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_ADX_REFERRAL_RECEIVED, false));
    }

    public Boolean isAppUpgradeNotificationShown()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_APP_UPGRADE_NOTIFICATION_SHOWN, false));
    }

    public Boolean isBackPressed()
    {
        return Boolean.valueOf(b.getBoolean(KEY_BACK_PRESSED, false));
    }

    public Boolean isDdayPopupShown()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_DDAY_POPUP_SHOWN, false));
    }

    public Boolean isFirstTimeAddToWishList()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_ADD_TO_WISHLIST, true));
    }

    public Boolean isFirstTimeFilterLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_FILTER_LOAD, true));
    }

    public Boolean isFirstTimeHomepageLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_HOMAEPAGE_LOAD, true));
    }

    public Boolean isFirstTimeInAppNotificationPageLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_IN_APP_NOTIFICATION_PAGE_LOAD, true));
    }

    public Boolean isFirstTimeLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_LOAD, true));
    }

    public Boolean isFirstTimeProdImagePageLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_PROD_IMAGE_PAGE_LOAD, true));
    }

    public Boolean isFirstTimeProdListLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_PROD_LIST_LOAD, true));
    }

    public Boolean isFirstTimeProdPageLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_PROD_PAGE_LOAD, true));
    }

    public Boolean isFirstTimeRefineCatLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_REFINE_CAT_LOAD, true));
    }

    public Boolean isFirstTimeWishListLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_FIRST_TIME_WISHLIST_LOAD, true));
    }

    public boolean isGCMAvailable()
    {
        return b.getBoolean(KEY_GCM_AVAILABLE, false);
    }

    public Boolean isLastTimeAppCrash()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_LAST_TIME_APP_CRASH, false));
    }

    public Boolean isLoggedIn()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_LOGGED_IN, false));
    }

    public Boolean isLoginShownOnFirstLoad()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_LOGIN_SHOWN_ON_FIRST_LOAD, false));
    }

    public boolean isNokiaDevice()
    {
        return b.getBoolean(KEY_IS_NOKIA_DEVICE, false);
    }

    public boolean isPincodeWidgetDismissed()
    {
        return b.getBoolean(KEY_DISMISS_PINCODE_WIDGET, false);
    }

    public Boolean isPoppingAllRefineFragment()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_POPPING_ALLREFINE_FRAGMENT, false));
    }

    public Boolean isPoppingProductPageFragment()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_POPPING_PRODUCT_PAGE_FRAGMENT, false));
    }

    public Boolean isPoppingRefineByFragment()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_POPPING_REFINE_BY_FRAGMENT, false));
    }

    public Boolean isPoppingSearchFragment()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_POPPING_SEARCH_FRAGMENT, false));
    }

    public Boolean isPreburnApp()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_PREBURN_APP, false));
    }

    public boolean isReferralSent()
    {
        return b.getBoolean(KEY_IS_REFERRER_SENT, false);
    }

    public Boolean isShowAppUpgradeNotification()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_SHOW_APP_UPGRADE_NOTIFICATION, true));
    }

    public Boolean isShowAppUpgradePopup()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_SHOW_APP_UPGRADE_POPUP, true));
    }

    public Boolean isShowPullOUt()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_PULL_OUT_SHOW, true));
    }

    public Boolean isShowRateTheAppPopup()
    {
        return Boolean.valueOf(b.getBoolean(KEY_IS_SHOW_RATE_THE_APP_POPUP, true));
    }

    public void saveAppConfig(String s)
    {
        b.edit().putString(KEY_APP_CONFIG, s).commit();
    }

    public void saveAppLaunchCounts(int i)
    {
        b.edit().putInt(KEY_APP_LAUNCH_COUNTS, i).commit();
    }

    public void saveAppRatePromptShownCount(int i)
    {
        b.edit().putInt(KEY_APP_RATE_PROMPT_SHOWN_COUNT, i).commit();
    }

    public void saveAppUpgradePromptShownCount(int i)
    {
        b.edit().putInt(KEY_APP_UPGRADE_PROMPT_SHOWN_COUNT, i).commit();
    }

    public void saveAppVersionCode(String s)
    {
        b.edit().putString(KEY_VERSION_CODE, s).commit();
    }

    public void saveAppVersionForGcm(int i)
    {
        b.edit().putInt(KEY_APP_VERSION_GCM, i).commit();
    }

    public void saveAppVersionNumber(int i)
    {
        b.edit().putInt(KEY_VERSION_NUMBER, i).commit();
    }

    public void saveAppWidgetCurrentImagePosition(int i)
    {
        b.edit().putInt(KEY_APP_WIDGET_IMAGE_POSITION, i).commit();
    }

    public void saveAppWidgetResponse(String s)
    {
        b.edit().putString(KEY_APP_WIDGET_RESPONSE, s).commit();
    }

    public void saveAppWidgetUpadateCount(int i)
    {
        b.edit().putInt(KEY_APP_WIDGET_UPDATE_COUNT, i).commit();
    }

    public void saveBackPressed(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_BACK_PRESSED, boolean1.booleanValue()).commit();
    }

    public void saveBrowserFamily(String s)
    {
        b.edit().putString(KEY_BROWSER_FAMILY, s).commit();
    }

    public void saveCartItems(String s)
    {
        b.edit().putString(KEY_CART_ITEMS, s).commit();
    }

    public void saveCurrentOsVersion(Context context)
    {
        b.edit().putString(KEY_OS_VERSION, FlipkartDeviceInfoProvider.getOsVersion()).commit();
    }

    public void saveDismissPincodeWidget(boolean flag)
    {
        b.edit().putBoolean(KEY_DISMISS_PINCODE_WIDGET, flag).commit();
    }

    public void saveFadeOutViewHeight(int i)
    {
        b.edit().putInt(KEY_FADEOUT_VIEW_HEIGHT, i).commit();
    }

    public void saveFirstTimeAddToWishList(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_ADD_TO_WISHLIST, boolean1.booleanValue()).commit();
    }

    public void saveFirstTimeFilterLoad(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_FILTER_LOAD, boolean1.booleanValue()).commit();
    }

    public void saveFirstTimeHomepageLoad(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_HOMAEPAGE_LOAD, boolean1.booleanValue()).commit();
    }

    public void saveFirstTimeInAppNotificationPageLoad(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_IN_APP_NOTIFICATION_PAGE_LOAD, boolean1.booleanValue()).commit();
    }

    public void saveFirstTimeProdImagePageLoad(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_PROD_IMAGE_PAGE_LOAD, boolean1.booleanValue()).commit();
    }

    public void saveFirstTimeProdListLoad(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_PROD_LIST_LOAD, boolean1.booleanValue()).commit();
    }

    public void saveFirstTimeProdPageLoad(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_PROD_PAGE_LOAD, boolean1.booleanValue()).commit();
    }

    public void saveFirstTimeRefineCatLoad(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_REFINE_CAT_LOAD, boolean1.booleanValue()).commit();
    }

    public void saveFirstTimeWishListLoad(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_WISHLIST_LOAD, boolean1.booleanValue()).commit();
    }

    public void saveGCMAvailable(boolean flag)
    {
        b.edit().putBoolean(KEY_GCM_AVAILABLE, flag).commit();
    }

    public void saveGcmIdSentToServerStatus(boolean flag)
    {
        b.edit().putBoolean(GCM_ID_SENT_TO_SERVER, flag).commit();
    }

    public void saveImageMatrix(String s)
    {
        b.edit().putString(KEY_IMAGE_MATRIX, s).commit();
    }

    public void saveInAppUnreadCount(int i)
    {
        b.edit().putInt(KEY_INAPP_UNREAD_COUNT, i).commit();
    }

    public void saveIsAdxReferralReceived(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_ADX_REFERRAL_RECEIVED, boolean1.booleanValue()).commit();
    }

    public void saveIsAppUpgradeNotificationShown(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_APP_UPGRADE_NOTIFICATION_SHOWN, boolean1.booleanValue()).commit();
    }

    public void saveIsDdayPopupShown(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_DDAY_POPUP_SHOWN, boolean1.booleanValue()).commit();
    }

    public void saveIsFirstTimeLoad(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_FIRST_TIME_LOAD, boolean1.booleanValue()).commit();
    }

    public void saveIsLoggedIn(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_LOGGED_IN, boolean1.booleanValue()).commit();
    }

    public void saveIsNewOmnitureVisitorId(boolean flag)
    {
        b.edit().putBoolean(IS_NEW_OMNITURE_VISITOR_ID, flag).commit();
    }

    public void saveIsPoppingAllRefineFragment(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_POPPING_ALLREFINE_FRAGMENT, boolean1.booleanValue()).commit();
    }

    public void saveIsPoppingProductPageFragment(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_POPPING_PRODUCT_PAGE_FRAGMENT, boolean1.booleanValue()).commit();
    }

    public void saveIsPoppingRefineByFragment(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_POPPING_REFINE_BY_FRAGMENT, boolean1.booleanValue()).commit();
    }

    public void saveIsPoppingSearchFragment(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_POPPING_SEARCH_FRAGMENT, boolean1.booleanValue()).commit();
    }

    public void saveIsPreburnApp(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_PREBURN_APP, boolean1.booleanValue()).commit();
    }

    public void saveIsShowAppUpgradeNotification(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_SHOW_APP_UPGRADE_NOTIFICATION, boolean1.booleanValue()).commit();
    }

    public void saveIsShowAppUpgradePopUp(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_SHOW_APP_UPGRADE_POPUP, boolean1.booleanValue()).commit();
    }

    public void saveIsShowPullOut(boolean flag)
    {
        b.edit().putBoolean(KEY_IS_PULL_OUT_SHOW, flag).commit();
    }

    public void saveIsShowRateTheAppPopUp(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_SHOW_RATE_THE_APP_POPUP, boolean1.booleanValue()).commit();
    }

    public void saveLastLoginType(LoginType logintype)
    {
        b.edit().putInt(KEY_LAST_LOGIN_TYPE, logintype.ordinal()).commit();
    }

    public void saveLastPageType(PageTypeUtils pagetypeutils)
    {
        b.edit().putInt(KEY_LAST_PAGE_TYPE, pagetypeutils.ordinal()).commit();
    }

    public void saveLastTimeAppCrash(Boolean boolean1)
    {
        b.edit().putBoolean(KEY_IS_LAST_TIME_APP_CRASH, boolean1.booleanValue()).commit();
    }

    public void saveLoginAccessExpires(long l)
    {
        b.edit().putLong(KEY_LOGIN_ACCESS_EXPIRES, l).commit();
    }

    public void saveLoginAccessToken(String s)
    {
        b.edit().putString(KEY_LOGIN_ACCESS_TOKEN, s).commit();
    }

    public void saveNokiaDevice(boolean flag)
    {
        b.edit().putBoolean(KEY_IS_NOKIA_DEVICE, flag).commit();
    }

    public void saveNotificationCount(int i)
    {
        b.edit().putInt(NOTIFICATION_COUNT, i).commit();
    }

    public void saveNotificationIds(String s)
    {
        b.edit().putString(KEY_NOTIFICATION_IDS, s).commit();
    }

    public void saveNotificationRegId(String s)
    {
        b.edit().putString(KEY_NOTIFICATION_REG_ID, s).commit();
    }

    public void saveNsid(String s)
    {
        b.edit().putString(KEY_NSID, s).commit();
    }

    public void saveOfferText(String s)
    {
        b.edit().putString(KEY_OFFER_TERMS_TEXT, s).commit();
    }

    public void saveOmnitureVisitorId(String s)
    {
        b.edit().putString(KEY_OMNITURE_VISITOR_ID, s).commit();
    }

    public void saveReferrerValue(String s)
    {
        b.edit().putString(KEY_REFERRER_VALUE, s).commit();
    }

    public void saveRegisterKey(String s)
    {
        b.edit().putString(KEY_REGISTER_KEY, s).commit();
    }

    public void saveSecureToken(String s)
    {
        b.edit().putString(KEY_SECURE_TOKEN, s).commit();
    }

    public void saveSn(String s)
    {
        b.edit().putString(KEY_SN, s).commit();
        BatchNetworkingHandler.updateSessionHeaders();
    }

    public void saveTTLSavedTime(long l)
    {
        b.edit().putLong(KEY_TTL_SAVE_TIME, l).commit();
    }

    public void saveTTLTime(long l)
    {
        b.edit().putLong(KEY_TTL_TIME, l).commit();
    }

    public void saveUserAccountId(String s)
    {
        b.edit().putString(KEY_USER_ACCOUNT_ID, s).commit();
    }

    public void saveUserAgent(String s)
    {
        b.edit().putString(KEY_USER_AGENT, s).commit();
    }

    public void saveUserEmail(String s)
    {
        b.edit().putString(KEY_USER_EMAIL, s).commit();
    }

    public void saveUserFirstName(String s)
    {
        b.edit().putString(KEY_USER_FIRST_NAME, s).commit();
    }

    public void saveUserFlipkartFirstStatus(boolean flag)
    {
        b.edit().putBoolean(KEY_USER_SUBSCRIBED_FF, flag).commit();
    }

    public void saveUserLastName(String s)
    {
        b.edit().putString(KEY_USER_LAST_NAME, s).commit();
    }

    public void saveUserPinCode(String s)
    {
        b.edit().putString(KEY_PIN_CODE, s).commit();
    }

    public void saveVid(String s)
    {
        b.edit().putString(KEY_VID, s).commit();
    }

    public void setFirstLoadTime(long l)
    {
        b.edit().putLong(KEY_FIRSTLOAD_TIMESTAMP, l).commit();
    }

    public void setLoginShownOnFirstLoad(boolean flag)
    {
        b.edit().putBoolean(KEY_IS_LOGIN_SHOWN_ON_FIRST_LOAD, flag).commit();
    }

    public void setReferralSent(boolean flag)
    {
        b.edit().putBoolean(KEY_IS_REFERRER_SENT, flag).commit();
    }

}
