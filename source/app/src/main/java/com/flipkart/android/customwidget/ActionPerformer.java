// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.PageName;
import com.flipkart.android.analytics.PageType;
import com.flipkart.android.analytics.SearchMode;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.MapUtils;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.TabContextCache;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class ActionPerformer
{

    public static final String PARAMS_FILTER = "filter";
    public static final String PARAMS_OMNITURE = "omnitureData";
    public static final String PARAMS_PID = "pid";
    public static final String PARAMS_PINCODE = "pincode";
    public static final String PARAMS_POSITION = "position";
    public static final String PARAMS_PRODUCT_IDS_KEY = "pids";
    public static final String PARAMS_PRODUCT_LIST_IDS = "product_list_ids";
    public static final String PARAMS_PRODUCT_LIST_IDS_KEY = "lids";
    public static final String PARAMS_PRODUCT_OFFER_IDS_KEY = "offerIds";
    public static final String PARAMS_QUERY = "query";
    public static final String PARAMS_SORT = "sort";
    public static final String PARAMS_STORE_ID = "store_id";
    public static final String PARAMS_TAG = "tag";
    public static final String PARAMS_TITLE = "title";
    public static final String PARAMS_VIEW = "view";

    public ActionPerformer()
    {
    }

    private static ArrayList fetchArrayListOfIds(Map map, String s)
    {
        return MapUtils.getStringsList(map, s);
    }

    private static int fetchInt(Map map, String s)
    {
        String s1 = (new StringBuilder()).append(map.get(s)).toString();
        int i;
        try
        {
            i = Integer.valueOf(Integer.parseInt(s1)).intValue();
        }
        catch (Exception exception)
        {
            return 0;
        }
        return i;
    }

    public static String fetchString(Map map, String s)
    {
        String s1 = "";
        if (map != null && !StringUtils.isNullOrEmpty(s))
        {
            s1 = (String)map.get(s);
            if (s.contains("store") && StringUtils.isNullOrEmpty(s1))
            {
                s1 = (String)map.get("store");
            }
        }
        if (s1 == null)
        {
            s1 = "";
        }
        return s1;
    }

    private static boolean isPincodeSupportedStore(String s)
    {
        ArrayList arraylist = new ArrayList(Arrays.asList(s.split("/")));
        if (arraylist.size() > 0)
        {
            String s1 = (String)arraylist.get(-1 + arraylist.size());
            if (!StringUtils.isNullOrEmpty(s1))
            {
                ArrayList arraylist1 = AppConfigUtils.getInstance().getPincodeSupportedStores();
                if (arraylist1 != null && arraylist1.contains(s1))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static void performAllCategoriesActions(Activity activity, PageTypeUtils pagetypeutils, String s)
    {
        ((HomeFragmentHolderActivity)activity).openAllCategories();
    }

    public static void performAppUpgrade(Map map, Activity activity, PageTypeUtils pagetypeutils, String s)
    {
        if (pagetypeutils != PageTypeUtils.Notification) goto _L2; else goto _L1
_L1:
        TrackingHelper.sendRateAndUpgradeAppEvent("Upgrade pushNotification shown");
        TrackingHelper.sendRateAndUpgradeAppEvent("Upgrade_PushNotification");
_L4:
        String s1 = activity.getPackageName();
        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("https://play.google.com/store/apps/details?id=")).append(s1).toString())));
        return;
_L2:
        if (pagetypeutils == PageTypeUtils.InAppNotificationPage)
        {
            TrackingHelper.sendRateAndUpgradeAppEvent("Upgrade_InAppNotification");
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static void performCallUsActions(Map map, Activity activity, PageTypeUtils pagetypeutils, String s)
    {
        if (s.equals("CALL_US"))
        {
            TrackingHelper.sendCallUsClicked();
        }
        if (map == null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        String s1 = (String)map.get("number");
        if (!StringUtils.isNullOrEmpty(s1))
        {
            String s2 = s1.trim().replace("\"", "");
            if (!StringUtils.isNullOrEmpty(s2))
            {
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse((new StringBuilder("tel:")).append(s2).toString()));
                activity.startActivity(intent);
            }
        }
        return;
        Exception exception;
        exception;
    }

    public static void performMultiWidgetPageActions(Map map, Activity activity, PageTypeUtils pagetypeutils, String s)
    {
        HomeFragmentHolderActivity homefragmentholderactivity = (HomeFragmentHolderActivity)activity;
        if (map != null)
        {
            String s1 = fetchString(map, "screenName");
            String s2 = fetchString(map, "store");
            String s3 = fetchString(map, "tabKey");
            TabContextCache.getInstance().putResponse("defaultTab", s3);
            if (StringUtils.isNullOrEmpty(s2))
            {
                String s4;
                if (android.os.Build.VERSION.SDK_INT >= 14)
                {
                    s4 = fetchString(map, "pageKey");
                } else
                if (StringUtils.isNullOrEmpty(s3))
                {
                    s4 = fetchString(map, "pageKey");
                } else
                {
                    s4 = s3;
                }
                if (!StringUtils.isNullOrEmpty(s4))
                {
                    s3 = s4;
                }
                FkLogger.debug("ActionPerform", (new StringBuilder("Screen name is ")).append(s1).append(" Screen id is ").append(s3).toString());
            } else
            {
                s3 = s2;
            }
            if (!StringUtils.isNullOrEmpty(s1) || StringUtils.isNullOrEmpty(s3))
            {
                if (pagetypeutils == PageTypeUtils.Flyout)
                {
                    homefragmentholderactivity.clearBackStack();
                }
                homefragmentholderactivity.openMultiWidgetScreen(s1, s3, fetchString(map, "REQUEST_ID"), s);
            }
        }
    }

    public static void performProductListViewActions(Map map, Activity activity, PageTypeUtils pagetypeutils, String s)
    {
        HomeFragmentHolderActivity homefragmentholderactivity = (HomeFragmentHolderActivity)activity;
        if (pagetypeutils == PageTypeUtils.Flyout)
        {
            homefragmentholderactivity.clearBackStack();
        }
        if (map.containsKey("pids"))
        {
            ArrayList arraylist1 = fetchArrayListOfIds(map, "pids");
            String s8 = fetchString(map, "title");
            if (!StringUtils.isNullOrEmpty(s8))
            {
                s8 = (new StringBuilder()).append(s8).append(" ( ").append(arraylist1.size()).append(" Results)").toString();
            }
            homefragmentholderactivity.doSearch("RECOMMENDATIONS", null, null, null, null, null, null, arraylist1, s8, "", pagetypeutils, fetchString(map, "REQUEST_ID"), s);
            return;
        }
        String s1 = fetchString(map, "pincode");
        String s2 = fetchString(map, "store_id");
        if (StringUtils.isNullOrEmpty(s1) && isPincodeSupportedStore(s2))
        {
            s1 = FlipkartPreferenceManager.instance().getUserPinCode();
        }
        String s3 = fetchString(map, "query");
        String s4 = fetchString(map, "store_id");
        String as[] = new String[1];
        as[0] = fetchString(map, "filter");
        String as1[] = new String[1];
        as1[0] = fetchString(map, "tag");
        String s5 = fetchString(map, "sort");
        ArrayList arraylist = fetchArrayListOfIds(map, "pids");
        String s6 = fetchString(map, "title");
        String s7 = fetchString(map, "REQUEST_ID");
        String as2[] = new String[1];
        as2[0] = fetchString(map, "view");
        homefragmentholderactivity.doSearch(s1, s3, s4, null, as, as1, s5, arraylist, s6, "", false, s7, s, true, as2);
    }

    public static void performProductPageActions(Map map, Activity activity, PageTypeUtils pagetypeutils, String s)
    {
        HomeFragmentHolderActivity homefragmentholderactivity = (HomeFragmentHolderActivity)activity;
        if (pagetypeutils == PageTypeUtils.Flyout)
        {
            homefragmentholderactivity.clearBackStack();
        }
        if (map != null)
        {
            if (map.containsKey("pids"))
            {
                ArrayList arraylist1 = fetchArrayListOfIds(map, "pids");
                ArrayList arraylist2 = fetchArrayListOfIds(map, "lids");
                ArrayList arraylist3 = fetchArrayListOfIds(map, "offerIds");
                String s12 = fetchString(map, "pid");
                String s13 = fetchString(map, "title");
                if (arraylist1 != null && arraylist1.size() > 0)
                {
                    int j;
                    String s14;
                    String s15;
                    if (!StringUtils.isNullOrEmpty(s12))
                    {
                        int l = 0;
                        for (int i1 = 0; i1 < arraylist1.size(); i1++)
                        {
                            String s16 = (String)arraylist1.get(i1);
                            if (!StringUtils.isNullOrEmpty(s16) && s16.equals(s12))
                            {
                                l = i1;
                            }
                        }

                        j = l;
                    } else
                    {
                        j = 0;
                    }
                    String s1;
                    int i;
                    String s2;
                    String s3;
                    String s4;
                    String as[];
                    String as1[];
                    String s5;
                    AnalyticData analyticdata;
                    String as2[];
                    String s6;
                    ArrayList arraylist;
                    String s7;
                    String s8;
                    String as3[];
                    String s9;
                    String as4[];
                    String s10;
                    String s11;
                    String as5[];
                    BrowseParam browseparam;
                    UUID uuid;
                    FkProductListContext fkproductlistcontext;
                    if (!StringUtils.isNullOrEmpty(s13) && arraylist1 != null)
                    {
                        s14 = (new StringBuilder()).append(s13).append(" ( ").append(arraylist1.size()).append(" Results)").toString();
                    } else
                    {
                        s14 = s13;
                    }
                    s15 = null;
                    if (arraylist1 != null)
                    {
                        int k = arraylist1.size();
                        s15 = null;
                        if (k > j)
                        {
                            s15 = (String)arraylist1.get(j);
                        }
                    }
                    sendOmnitureData(s15, pagetypeutils, j);
                    homefragmentholderactivity.openProductPage(fetchString(map, "omnitureData"), arraylist1, arraylist2, arraylist3, j, s14, fetchString(map, "REQUEST_ID"), s);
                }
            } else
            {
                if (map.containsKey("product_list_ids"))
                {
                    s7 = fetchString(map, "query");
                    s8 = fetchString(map, "store_id");
                    as3 = new String[1];
                    as3[0] = fetchString(map, "filter");
                    s9 = fetchString(map, "sort");
                    as4 = new String[1];
                    as4[0] = fetchString(map, "tag");
                    s10 = fetchString(map, "title");
                    s11 = TrackingHelper.getProductFindingMethod();
                    as5 = new String[1];
                    as5[0] = fetchString(map, "view");
                    browseparam = new BrowseParam("", s7, s8, as3, s9, as4, s10, "", s11, true, as5);
                    uuid = UUID.randomUUID();
                    fkproductlistcontext = new FkProductListContext();
                    fkproductlistcontext.setParam(browseparam);
                    fkproductlistcontext.addProductIds((ArrayList)map.get("product_list_ids"));
                    ContextCache.getInstance().putResponse(uuid.toString(), fkproductlistcontext);
                    homefragmentholderactivity.openProductPage(null, fetchString(map, "pid"), "", fetchInt(map, "position"), uuid.toString(), fetchString(map, "title"), fetchString(map, "REQUEST_ID"), s);
                    return;
                }
                if (map.containsKey("pid"))
                {
                    s6 = (String)map.get("pid");
                    if (!StringUtils.isNullOrEmpty(s6))
                    {
                        arraylist = new ArrayList();
                        arraylist.add(s6);
                        homefragmentholderactivity.openProductPage(fetchString(map, "omnitureData"), arraylist, null, null, 0, (String)map.get("title"), fetchString(map, "REQUEST_ID"), s);
                        return;
                    }
                } else
                {
                    s1 = fetchString(map, "pid");
                    i = fetchInt(map, "position");
                    s2 = fetchString(map, "title");
                    s3 = fetchString(map, "query");
                    s4 = fetchString(map, "store_id");
                    as = new String[1];
                    as[0] = fetchString(map, "filter");
                    as1 = new String[1];
                    as1[0] = fetchString(map, "tag");
                    s5 = fetchString(map, "sort");
                    analyticdata = new AnalyticData(fetchString(map, "REQUEST_ID"), s, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil());
                    as2 = new String[1];
                    as2[0] = fetchString(map, "view");
                    homefragmentholderactivity.openProductPage(s1, "", i, null, s2, s3, s4, null, as, as1, s5, "", analyticdata, true, as2);
                    return;
                }
            }
        }
    }

    public static void performSearchPageActions(Map map, Activity activity, PageTypeUtils pagetypeutils, String s)
    {
        if (s.equals("SEARCH"))
        {
            TrackingHelper.sendSearchMode(SearchMode.UpFrontSearch);
        }
        ((HomeFragmentHolderActivity)activity).openSearchPage();
    }

    public static void performUrlExternalActions(Map map, Activity activity, PageTypeUtils pagetypeutils, String s)
    {
        if (map != null)
        {
            String s1 = fetchString(map, "url");
            if (!StringUtils.isNullOrEmpty(s1))
            {
                TrackingHelper.sendPageView(PageName.ExternalWebPage.name(), PageType.ExternalWebView);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(s1));
                intent.setFlags(0x10000000);
                FlipkartApplication.getAppContext().startActivity(intent);
            }
        }
    }

    public static void performWebViewActions(Map map, Activity activity, PageTypeUtils pagetypeutils, String s)
    {
        HomeFragmentHolderActivity homefragmentholderactivity = (HomeFragmentHolderActivity)activity;
        if (map != null)
        {
            String s1 = fetchString(map, "url");
            if (!StringUtils.isNullOrEmpty(s1))
            {
                if (pagetypeutils == PageTypeUtils.Flyout)
                {
                    homefragmentholderactivity.clearBackStack();
                }
                homefragmentholderactivity.openWebView(s1, false);
            }
        }
    }

    private static void sendOmnitureData(String s, PageTypeUtils pagetypeutils, int i)
    {
        if (pagetypeutils == PageTypeUtils.ProductPage)
        {
            TrackingHelper.sendRecommendationInfo(i, "pp");
            TrackingHelper.sendRecommendationInfoEvent(s, i, "pp");
            return;
        }
        if (pagetypeutils == PageTypeUtils.ProductListNullPage)
        {
            TrackingHelper.sendRecommendationInfo(i, "pl");
            TrackingHelper.sendRecommendationInfoEvent(s, i, "pl");
            return;
        } else
        {
            TrackingHelper.sendRecommendationInfo(i, "hp");
            TrackingHelper.sendRecommendationInfoEvent(s, i, "hp");
            return;
        }
    }
}
