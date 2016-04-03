// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.response.discovery.OmnitureData;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.AutoSuggestType;
import com.flipkart.android.utils.ProductSpecificSellerTypes;
import com.flipkart.android.utils.SellerTypes;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.fk_android_batchnetworking.BatchNetworking;
import com.google.mygson.JsonArray;
import com.google.mygson.JsonObject;
import com.google.mygson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.flipkart.android.analytics:
//            SearchMode, TrackingUtil, TrackingBuilder, AddCartLocation, 
//            PageName, PageType, EntryChannel

public class TrackingHelper
{

    private static String a;
    private static Boolean b = Boolean.valueOf(false);
    private static Boolean c = Boolean.valueOf(false);
    private static SearchMode d;
    private static String e = "utm_source";
    private static String f = "utm_campaign";
    private static String g = "utm_medium";
    private static String h = "utm_creative";

    public TrackingHelper()
    {
    }

    public static void configureAppMeasurement()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().configureAppMeasurement();
            return;
        }
    }

    public static String getProductFindingMethod()
    {
        return a;
    }

    public static void sendActionOmnitureData(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(29, s);
            return;
        }
    }

    public static void sendAddToCart(AddCartLocation addcartlocation, String s, String s1, String s2, String s3, ProductSpecificSellerTypes productspecificsellertypes, SellerTypes sellertypes)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (StringUtils.isNullOrEmpty(s1))
        {
            s1 = "";
        }
        if (StringUtils.isNullOrEmpty(s3))
        {
            s3 = "";
        }
        if (StringUtils.isNull(productspecificsellertypes))
        {
            productspecificsellertypes = ProductSpecificSellerTypes.NONE;
        }
        if (StringUtils.isNull(sellertypes))
        {
            sellertypes = SellerTypes.NONE;
        }
        TrackingBuilder.getInstance().init().setEvar(18, addcartlocation.toString()).setEvar(44, s3).setEvar(28, sellertypes.name()).setEvent("scAdd").setProducts((new StringBuilder(";")).append(s).append(";;;;eVar22=").append(s1).append("|eVar61=").append(a).append("|eVar30=").append(productspecificsellertypes.name()).toString()).trackLink();
    }

    public static void sendAddToWishList(String s, String s1)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (StringUtils.isNullOrEmpty(s1))
        {
            s1 = "";
        }
        TrackingBuilder.getInstance().init().setEvent("event21").setProducts((new StringBuilder(";")).append(s).append(";;;;eVar22=").append(s1).toString()).trackLink();
    }

    public static void sendAdxReferral(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(36, s);
            return;
        }
    }

    public static void sendAffid(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(41, s);
            return;
        }
    }

    public static void sendAugmentedSearchClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event42");
            return;
        }
    }

    public static void sendAugmentedSearchShown()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event45");
            return;
        }
    }

    public static void sendAutoSuggestShown()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            b = Boolean.valueOf(true);
            return;
        }
    }

    public static void sendAutoSuggestTapAheadUsed()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("event11");
            return;
        }
    }

    public static void sendAutoSuggestType(AutoSuggestType autosuggesttype)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(27, autosuggesttype.toString());
            return;
        }
    }

    public static void sendAutoSuggestUsed()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            c = Boolean.valueOf(true);
            return;
        }
    }

    public static void sendBannerClicked(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(20, s);
            return;
        }
    }

    public static void sendBarCodeClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event47");
            return;
        }
    }

    public static void sendBarCodeFailed()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event3");
            return;
        }
    }

    public static void sendBrowsePageToggleView(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(68, s).trackLink();
            return;
        }
    }

    public static void sendBrowsePageViewChange(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(68, s);
            return;
        }
    }

    public static void sendBuyNowClicked(String s, String s1, ProductSpecificSellerTypes productspecificsellertypes, SellerTypes sellertypes, String s2)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (StringUtils.isNullOrEmpty(s))
        {
            s = "";
        }
        TrackingBuilder.getInstance().init().setEvar(44, s1).setEvar(28, sellertypes.name()).setProducts((new StringBuilder(";")).append(s).append(";;;;eVar22=").append(s2).append("|eVar61=").append(a).append("|eVar30=").append(productspecificsellertypes.name()).toString()).setEvent("event10").trackLink();
    }

    public static void sendCLPPage(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (FlipkartPreferenceManager.instance().isBackPressed().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveBackPressed(Boolean.valueOf(false));
            return;
        } else
        {
            TrackingBuilder.getInstance().init().addPageParams((new StringBuilder()).append(PageName.CLP.name()).append(":").append(s).toString(), PageType.CLP, null).trackPage();
            sendCLPPageLoaded();
            return;
        }
    }

    public static void sendCLPPageLoaded()
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("event", "CATEGORY_PAGE_VIEW");
        BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "perf");
    }

    public static void sendCallUsClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("event18");
            return;
        }
    }

    public static void sendCartView()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("scView,scOpen");
            return;
        }
    }

    public static void sendCategoryRefinedOnSearch()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event43");
            return;
        }
    }

    public static void sendClickOnPinCode(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(52, s);
            return;
        }
    }

    public static void sendContactUs()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("event19");
            return;
        }
    }

    public static void sendDeepLinkCampaign(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(45, s).trackLink();
            return;
        }
    }

    public static void sendDeleteFromWishList(String s, String s1)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (StringUtils.isNullOrEmpty(s1))
        {
            s1 = "";
        }
        TrackingBuilder.getInstance().init().setEvent("event23").setProducts((new StringBuilder(";")).append(s).append(";;;;eVar22=").append(s1).toString()).trackLink();
    }

    public static void sendEntryChannel(EntryChannel entrychannel)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(2, entrychannel.name());
            return;
        }
    }

    public static void sendFilterApplied(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setListVar(1, s).trackLink();
            return;
        }
    }

    public static void sendFilterPage(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().addPageParams(s, PageType.StoreFilters, null).trackPage();
            return;
        }
    }

    public static void sendFlyoutClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event28").trackLink();
            return;
        }
    }

    public static void sendFoz(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (StringUtils.isNull(s))
        {
            s = "";
        }
        if (FlipkartPreferenceManager.instance().isBackPressed().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveBackPressed(Boolean.valueOf(false));
            return;
        } else
        {
            TrackingBuilder.getInstance().init().addPageParams((new StringBuilder()).append(PageName.Ozone.name()).append(":").append(s).toString(), PageType.Ozone, null).trackPage();
            return;
        }
    }

    public static void sendGPSLocation(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(74, s);
            return;
        }
    }

    public static void sendHomePage()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (FlipkartPreferenceManager.instance().isBackPressed().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveBackPressed(Boolean.valueOf(false));
            return;
        } else
        {
            TrackingBuilder.getInstance().init().addPageParams(PageName.Homepage.name(), PageType.Homepage, null).trackPage();
            sendHomePageLoaded();
            return;
        }
    }

    public static void sendHomePageLoaded()
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("event", "HOME_PAGE_VIEW");
        BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "perf");
    }

    public static void sendIcmpId(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(20, s);
            return;
        }
    }

    public static void sendImageZoom()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (FlipkartPreferenceManager.instance().isBackPressed().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveBackPressed(Boolean.valueOf(false));
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("event16");
            return;
        }
    }

    public static void sendInAppNotificationPage(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().addPageParams(s, PageType.Notification, null).trackPage();
            return;
        }
    }

    public static void sendInAppNotificationVariables(String s)
    {
        boolean flag;
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag | StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(18, s);
            return;
        }
    }

    public static void sendInAppNotificationVariablesIm(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(18, s).trackLink();
            return;
        }
    }

    public static void sendLoginComplete()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event5");
            return;
        }
    }

    public static void sendLoginPage()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().addPageParams(PageName.LoginPage.name(), PageType.Login, null).setEvent("event12").trackPage();
            return;
        }
    }

    public static void sendLoginSkipClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event17");
            return;
        }
    }

    public static void sendMakeAndModel(String s)
    {
        while (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s)) 
        {
            return;
        }
        TrackingBuilder.getInstance().init().setEvar(40, s).setEvar(42, FlipkartPreferenceManager.instance().isPreburnApp().toString());
    }

    public static void sendNoSellerAvailableForPinCode()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event24");
            return;
        }
    }

    public static void sendNotification(boolean flag)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (flag)
        {
            TrackingBuilder.getInstance().init().setProp(13, "Notification Enabled").trackLink();
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(13, "Notification Disabled").trackLink();
            return;
        }
    }

    public static void sendNotificationClicked(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(11, s).trackLink();
            return;
        }
    }

    public static void sendNotificationDataEvent(String s, String s1, int i)
    {
        if (StringUtils.isNullOrEmpty(s1))
        {
            return;
        } else
        {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("event", "NOTIFICATION_CLICK");
            JsonObject jsonobject1 = new JsonObject();
            jsonobject1.addProperty("notificationId", s1);
            jsonobject1.addProperty("type", s);
            jsonobject1.addProperty("position", Integer.valueOf(i));
            jsonobject1.addProperty("timestamp", Long.valueOf(System.currentTimeMillis()));
            jsonobject.add("data", jsonobject1);
            BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "perf");
            return;
        }
    }

    public static void sendOcmpid(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(58, s);
            return;
        }
    }

    public static void sendOfferEvent(String s, String s1, String s2)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("event", "OFFER_CLICK");
            JsonObject jsonobject1 = new JsonObject();
            jsonobject1.addProperty("offerId", s);
            jsonobject1.addProperty("otrackerId", s1);
            jsonobject1.addProperty("icmpId", s2);
            jsonobject1.addProperty("timestamp", Long.valueOf(System.currentTimeMillis()));
            jsonobject.add("data", jsonobject1);
            BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "perf");
            return;
        }
    }

    public static void sendOfferId(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(55, s).setProp(57, s);
            return;
        }
    }

    public static void sendOfferImpressions(String s, ArrayList arraylist)
    {
        if (arraylist == null)
        {
            return;
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("event", "OFFER_IMPRESSION");
        JsonObject jsonobject1 = new JsonObject();
        jsonobject1.addProperty("referrerRequestId", s);
        JsonArray jsonarray = new JsonArray();
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); jsonarray.add(new JsonPrimitive((String)iterator.next()))) { }
        jsonobject1.add("offerList", jsonarray);
        jsonobject1.addProperty("timestamp", Long.valueOf(System.currentTimeMillis()));
        jsonobject.add("data", jsonobject1);
        BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "perf");
    }

    public static void sendOtrackerId(String s, String s1, String s2)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("event", "OTRACKER_CLICK");
            JsonObject jsonobject1 = new JsonObject();
            jsonobject1.addProperty("otrackerId", s);
            jsonobject1.addProperty("offerId", s1);
            jsonobject1.addProperty("pageType", s2);
            jsonobject1.addProperty("timestamp", Long.valueOf(System.currentTimeMillis()));
            jsonobject.add("data", jsonobject1);
            BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "perf");
            return;
        }
    }

    public static void sendPageView(String s, PageType pagetype)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (FlipkartPreferenceManager.instance().isBackPressed().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveBackPressed(Boolean.valueOf(false));
            return;
        } else
        {
            TrackingBuilder.getInstance().init().addPageParams(s, pagetype, null).trackPage();
            return;
        }
    }

    public static void sendPincodeCheck(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event15").setEvar(52, s).trackLink();
            return;
        }
    }

    public static void sendProductClickedOnBrowsePage(Integer integer)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(9, Integer.toString(1 + integer.intValue()));
            return;
        }
    }

    public static void sendProductClickedOnSearchPage()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event44");
            return;
        }
    }

    public static void sendProductListPage(String s, String s1, String s2, String s3)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (StringUtils.isNull(s))
        {
            s = "";
        }
        if (StringUtils.isNull(s3))
        {
            s3 = "";
        }
        if (StringUtils.isNull(s2))
        {
            s2 = "";
        }
        if (StringUtils.isNull(s1))
        {
            s1 = "";
        }
        TrackingBuilder.getInstance().init().addPageParams((new StringBuilder()).append(PageName.ProductListPage.name()).append(":").append(s1).toString(), PageType.ProductList, s1).setSuperCategory(s).setSubCategory(s3).setVertical(s2).trackPage();
    }

    public static void sendProductNotifyMe(String s, String s1)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (StringUtils.isNullOrEmpty(s1))
        {
            s1 = "";
        }
        TrackingBuilder.getInstance().init().setEvent("event29").setProducts((new StringBuilder(";")).append(s).append(";;;;eVar22=").append(s1).toString()).trackLink();
    }

    public static void sendProductPageMoreSellersViewEvent(String s, String s1, String s2)
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("event", "SELLER_PAGE_VIEW");
        JsonObject jsonobject1 = new JsonObject();
        jsonobject1.addProperty("listingId", s);
        jsonobject1.addProperty("productId", s1);
        jsonobject1.addProperty("requestId", s2);
        jsonobject1.addProperty("timestamp", Long.valueOf(System.currentTimeMillis()));
        jsonobject.add("data", jsonobject1);
        BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "perf");
    }

    public static void sendProductPageViewEvent(String s, String s1, String s2)
    {
        if (s2 == null)
        {
            s2 = "";
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("event", "PRODUCT_VIEW");
        JsonObject jsonobject1 = new JsonObject();
        jsonobject1.addProperty("listingId", s);
        jsonobject1.addProperty("productId", s1);
        jsonobject1.addProperty("requestId", s2);
        jsonobject1.addProperty("timestamp", Long.valueOf(System.currentTimeMillis()));
        jsonobject.add("data", jsonobject1);
        BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "perf");
    }

    public static void sendProductPulloutOpened()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("event49");
            return;
        }
    }

    public static void sendProductSwypeUsed()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event9");
            return;
        }
    }

    public static void sendProductView(ProductInfo productinfo, ProductPageModel productpagemodel)
    {
_L3:
        omnituredata = productinfo.getOmnitureData();
        sellertypes = SellerTypes.NONE;
        String s6 = "";
        String s8;
        String s9;
        String s10;
        s7 = omnituredata.getCategory();
        s8 = omnituredata.getSubCategory();
        s9 = omnituredata.getSuperCategory();
        s10 = omnituredata.getVertical();
        String s5;
        String s12;
        String s13;
        String s14;
        if (s7 == null)
        {
            s11 = "";
        } else
        {
            s11 = s7;
        }
          goto _L1
_L2:
        boolean1 = Boolean.valueOf(false);
        if (productinfo.getPreferredSeller() != null)
        {
            boolean1 = Boolean.valueOf(productinfo.getPreferredSeller().isWSR());
        }
        if (productpagemodel == null)
        {
            break MISSING_BLOCK_LABEL_143;
        }
        sellertypes = productpagemodel.getSellerType();
        s6 = productpagemodel.getSellerName();
        if (sellertypes != null)
        {
            break MISSING_BLOCK_LABEL_424;
        }
        sellertypes = SellerTypes.NONE;
        break MISSING_BLOCK_LABEL_424;
_L12:
        trackingbuilder = TrackingBuilder.getInstance().init().addPageParams((new StringBuilder()).append(PageName.ProductViewPage.name()).append(":").append(s11).toString(), PageType.Product, s11).setSuperCategory(s13).setSubCategory(s12).setVertical(s14).setEvent("event2").setProp(49, s4).setEvar(14, s3).setEvar(28, sellertypes.name()).setEvar(44, s6).setProducts((new StringBuilder(";")).append(s5).append(";;;;eVar22=").append(s11).append("|eVar61=").append(a).toString());
        if (s3 != "Out of Stock")
        {
            break MISSING_BLOCK_LABEL_320;
        }
        if (boolean1.booleanValue())
        {
            trackingbuilder = trackingbuilder.setEvent("event20");
        }
        trackingbuilder.trackPage();
        return;
_L11:
        s14 = s10;
          goto _L2
_L9:
        s13 = s9;
        continue; /* Loop/switch isn't completed */
_L7:
        s12 = s8;
        continue; /* Loop/switch isn't completed */
_L5:
        s5 = s1;
          goto _L3
        sendProductViewForDG(productinfo, productpagemodel);
        if (!TrackingUtil.trackingEnabled.booleanValue() || productinfo == null)
        {
            return;
        }
        String s;
        String s2;
        String s3;
        String s4;
        OmnitureData omnituredata;
        SellerTypes sellertypes;
        String s7;
        String s11;
        Boolean boolean1;
        TrackingBuilder trackingbuilder;
        try
        {
            s = productinfo.getProductStatus();
            s1 = productinfo.getProductId();
            s2 = productinfo.getMainTitle();
        }
        catch (Exception exception)
        {
            return;
        }
        if (s == null)
        {
            s3 = "";
        } else
        {
            s3 = s;
        }
        while (s2 != null) 
        {
            s4 = s2;
            continue; /* Loop/switch isn't completed */
        }
        s4 = "";
        if (s1 != null) goto _L5; else goto _L4
_L4:
        s5 = "";
          goto _L3
_L1:
        if (s8 != null) goto _L7; else goto _L6
_L6:
        s12 = "";
        if (s9 != null) goto _L9; else goto _L8
_L8:
        s13 = "";
        if (s10 != null) goto _L11; else goto _L10
_L10:
        s14 = "";
          goto _L2
        if (s6 == null)
        {
            s6 = "";
        }
          goto _L12
    }

    public static void sendProductViewForDG(ProductInfo productinfo, ProductPageModel productpagemodel)
    {
        String s;
        if (!TrackingUtil.trackingEnabled.booleanValue() || productinfo == null || productpagemodel == null)
        {
            return;
        }
        try
        {
            s = productpagemodel.getRequestId();
        }
        catch (Exception exception)
        {
            return;
        }
        if (s != null) goto _L2; else goto _L1
_L1:
        s = productinfo.getRequestId();
          goto _L2
_L4:
        sendProductPageViewEvent(productinfo.getPreferredListingId(), productinfo.getProductId(), s);
        return;
_L2:
        if (s == null)
        {
            s = "";
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static void sendRateAndUpgradeAppEvent(String s)
    {
        while (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s)) 
        {
            return;
        }
        TrackingBuilder.getInstance().init().setProp(21, s).trackLink();
    }

    public static void sendRateTheAppClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("event32");
            return;
        }
    }

    public static void sendRecommendationInfo(int i, String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        String s1;
        if (s.contains("hp"))
        {
            s1 = (new StringBuilder()).append(s).append("_reco_").append(i).toString();
        } else
        {
            s1 = (new StringBuilder("reco_")).append(s).append("_").append(i).toString();
        }
        TrackingBuilder.getInstance().init().setEvar(20, s1);
    }

    public static void sendRecommendationInfoEvent(String s, int i, String s1)
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("event", "RECO_CLICK");
        JsonObject jsonobject1 = new JsonObject();
        jsonobject1.addProperty("pageType", s1);
        jsonobject1.addProperty("productId", s);
        jsonobject1.addProperty("position", Integer.valueOf(i));
        jsonobject1.addProperty("timestamp", Long.valueOf(System.currentTimeMillis()));
        jsonobject.add("data", jsonobject1);
        BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "perf");
    }

    public static void sendReferralInfo(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        }
        Map map = StringUtils.splitIntoMap(s, "&", "=");
        TrackingBuilder trackingbuilder = TrackingBuilder.getInstance().init();
        if (map.containsKey(e))
        {
            trackingbuilder.setEvar(12, (String)map.get(e));
        }
        if (map.containsKey(f))
        {
            trackingbuilder.setEvar(13, (String)map.get(f));
        }
        if (map.containsKey(g))
        {
            trackingbuilder.setEvar(15, (String)map.get(g));
        }
        if (map.containsKey(h))
        {
            trackingbuilder.setEvar(71, (String)map.get(h));
        }
        trackingbuilder.trackLink();
    }

    public static void sendReferrer(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(75, s);
            return;
        }
    }

    public static void sendSearchMode(SearchMode searchmode)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            d = searchmode;
            return;
        }
    }

    public static void sendSearchNullResults()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event7");
            return;
        }
    }

    public static void sendSearchSuccessful()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event14");
            return;
        }
    }

    public static void sendSearchTriggered(String s, SearchMode searchmode)
    {
        if (TrackingUtil.trackingEnabled.booleanValue())
        {
            if (d != SearchMode.None)
            {
                searchmode = d;
                d = SearchMode.None;
            }
            TrackingBuilder trackingbuilder = TrackingBuilder.getInstance().init();
            trackingbuilder.setProp(6, s).setEvar(6, s).setLastPageEvar().setEvar(21, searchmode.name()).setEvent("event6");
            if (b.booleanValue())
            {
                b = Boolean.valueOf(false);
                trackingbuilder.setEvent("event40");
            }
            if (c.booleanValue())
            {
                c = Boolean.valueOf(false);
                trackingbuilder.setEvent("event41");
                return;
            }
        }
    }

    public static void sendShareCompleted(boolean flag)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (flag)
        {
            TrackingBuilder.getInstance().init().setProp(36, "order_share_complete_fb");
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(36, "order_share_complete_twitter");
            return;
        }
    }

    public static void sendShareConfirmed()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().addPageParams(PageType.OrderConfirmationPage.name(), PageType.OrderConfirmationPage, null).setEvar(50, "AppShare:Confirm:Offer").trackPage();
            return;
        }
    }

    public static void sendShareInitiated(boolean flag)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (flag)
        {
            TrackingBuilder.getInstance().init().setProp(36, "order_share_initiate_fb");
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(36, "order_share_initiate_twitter");
            return;
        }
    }

    public static void sendShareTheAppClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("event31");
            return;
        }
    }

    public static void sendShowAllClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event30").trackLink();
            return;
        }
    }

    public static void sendSocialSharing()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("event13");
            return;
        }
    }

    public static void sendSortSelected(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(10, s).setEvent("event25");
            return;
        }
    }

    public static void sendStoreFilterUsed(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(64, s);
            return;
        }
    }

    public static void sendStoreFrontPage(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        }
        if (StringUtils.isNull(s))
        {
            s = "";
        }
        if (FlipkartPreferenceManager.instance().isBackPressed().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveBackPressed(Boolean.valueOf(false));
            return;
        } else
        {
            TrackingBuilder.getInstance().init().addPageParams((new StringBuilder()).append(PageName.StoreFront.name()).append(":").append(s).toString(), PageType.StoreFront, null).trackPage();
            return;
        }
    }

    public static void sendSubCategory(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(29, (new StringBuilder("FOZ_dropdown_")).append(s).toString()).trackLink();
            return;
        }
    }

    public static void sendTabWidgetChange(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue() || StringUtils.isNullOrEmpty(s))
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(58, s).trackLink();
            return;
        }
    }

    public static void sendTotalProductViewed(int i)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setProp(32, String.valueOf(i));
            return;
        }
    }

    public static void sendUpCarrotClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().trackEvents("event27");
            return;
        }
    }

    public static void sendViewMoreClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvar(29, "pp_offer_viewall").trackLink();
            return;
        }
    }

    public static void sendViewMoreOnClpClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event29").trackLink();
            return;
        }
    }

    public static void sendVoiceClicked()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event4");
            return;
        }
    }

    public static void sendVoiceFailed()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event8");
            return;
        }
    }

    public static void setClearFilterEvent()
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            TrackingBuilder.getInstance().init().setEvent("event69");
            return;
        }
    }

    public static void setProductFindingMethod(String s)
    {
        if (!TrackingUtil.trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            a = s;
            return;
        }
    }

    static 
    {
        d = SearchMode.None;
    }
}
