// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.EntryChannel;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.log.ApiLogger;
import com.flipkart.android.log.LoggerTag;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.customwidgetitemvalue.TrackingParams;
import com.flipkart.android.urlmanagement.AppAction;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.util.HashMap;

// Referenced classes of package com.flipkart.android.customwidget:
//            ActionPerformer

public class WidgetAction
{

    public static final String PARAMS = "params";
    public static final String SCREEN_TYPE = "screenType";

    public WidgetAction()
    {
    }

    public static boolean performAction(Action action, Activity activity, PageTypeUtils pagetypeutils)
    {
        if (action != null)
        {
            FkLogger.debug("Action", (new StringBuilder("Action value is ")).append(action).toString());
            String s = action.getScreenType();
            if (!StringUtils.isNullOrEmpty(s))
            {
                TrackingParams trackingparams = action.getTracking();
                String s1 = null;
                if (trackingparams != null)
                {
                    s1 = action.getTracking().getOtracker();
                }
                if (pagetypeutils.equals(PageTypeUtils.Notification))
                {
                    TrackingHelper.sendEntryChannel(EntryChannel.Notification);
                    TrackingHelper.setProductFindingMethod("notification");
                }
                if (action.getTracking() != null)
                {
                    sendTrackingData(action.getTracking());
                    TrackingHelper.sendOtrackerId(action.getTracking().getOtracker(), action.getTracking().getOffer(), action.getTracking().getPageType());
                    TrackingHelper.sendOfferId(action.getTracking().getOffer());
                    TrackingHelper.sendIcmpId(action.getTracking().getIcmpid());
                    TrackingHelper.sendAffid(action.getTracking().getAffid());
                    TrackingHelper.sendReferrer(action.getTracking().getReferrer());
                    sendActionOmnitureData(action.getTracking().getOtracker(), action.getTracking());
                }
                sendOfferData(action.getTracking());
                if (activity instanceof HomeFragmentHolderActivity)
                {
                    if (s.equalsIgnoreCase(AppAction.productPage.toString()))
                    {
                        ActionPerformer.performProductPageActions(action.getParams(), activity, pagetypeutils, s1);
                    }
                    if (s.equalsIgnoreCase(AppAction.productListView.toString()))
                    {
                        ActionPerformer.performProductListViewActions(action.getParams(), activity, pagetypeutils, s1);
                        return false;
                    }
                    if (s.equalsIgnoreCase(AppAction.multiWidgetPage.toString()))
                    {
                        ActionPerformer.performMultiWidgetPageActions(action.getParams(), activity, pagetypeutils, s1);
                        return false;
                    }
                    if (s.equalsIgnoreCase(AppAction.webView.toString()))
                    {
                        ActionPerformer.performWebViewActions(action.getParams(), activity, pagetypeutils, s1);
                        return false;
                    }
                    if (s.equalsIgnoreCase(AppAction.allCategoriesMenu.toString()))
                    {
                        ActionPerformer.performAllCategoriesActions(activity, pagetypeutils, s1);
                        return false;
                    }
                    if (s.equalsIgnoreCase(AppAction.searchPage.toString()))
                    {
                        ActionPerformer.performSearchPageActions(action.getParams(), activity, pagetypeutils, s1);
                        return false;
                    }
                    if (s.equalsIgnoreCase(AppAction.callUsWidget.toString()))
                    {
                        ActionPerformer.performCallUsActions(action.getParams(), activity, pagetypeutils, s1);
                        return false;
                    }
                    if (s.equalsIgnoreCase(AppAction.openUrlExternal.toString()))
                    {
                        ActionPerformer.performUrlExternalActions(action.getParams(), activity, pagetypeutils, s1);
                        return false;
                    }
                    if (s.equalsIgnoreCase(AppAction.upgradeApp.toString()))
                    {
                        ActionPerformer.performAppUpgrade(action.getParams(), activity, pagetypeutils, s1);
                        return false;
                    }
                    HashMap hashmap = new HashMap();
                    if (action != null)
                    {
                        hashmap.put("JsonAction", action.toString());
                    }
                    ApiLogger.log(LoggerTag.Widget, "Not able to handle widget action", hashmap);
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean performAction(com.flipkart.android.response.customwidgetitemvalue.Action action, Activity activity, PageTypeUtils pagetypeutils)
    {
        Action action1 = new Action();
        action1.setOmnitureData(action.getOmnitureData());
        action1.setOriginalUrl(action.getOriginalUrl());
        action1.setParams(action.getParams());
        action1.setScreenType(action.getScreenType());
        action1.setTracking(action.getTracking());
        action1.setUrl(action.getUrl());
        return performAction(action1, activity, pagetypeutils);
    }

    private static void sendActionOmnitureData(String s, TrackingParams trackingparams)
    {
        String s1;
label0:
        {
            TrackingHelper.sendActionOmnitureData(s);
            if (trackingparams != null)
            {
                s1 = trackingparams.getWidgetType();
                String s2 = trackingparams.getPageType();
                if (!StringUtils.isNullOrEmpty(s1))
                {
                    if (StringUtils.isNullOrEmpty(s2) || !s2.equals("menu"))
                    {
                        break label0;
                    }
                    TrackingHelper.setProductFindingMethod("flyout");
                }
            }
            return;
        }
        TrackingHelper.setProductFindingMethod(s1);
    }

    public static void sendOfferData(TrackingParams trackingparams)
    {
        try
        {
            TrackingHelper.sendOfferEvent(trackingparams.getOffer(), trackingparams.getOtracker(), trackingparams.getIcmpid());
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    private static void sendTrackingData(TrackingParams trackingparams)
    {
        if (StringUtils.isNullOrEmpty(trackingparams.getAffid()) || trackingparams.getAffid().startsWith("EM-")) goto _L2; else goto _L1
_L1:
        String s = trackingparams.getAffid();
_L4:
        if (!StringUtils.isNullOrEmpty(trackingparams.getOcmpid()))
        {
            TrackingHelper.sendOcmpid(trackingparams.getOcmpid());
        }
        TrackingHelper.sendDeepLinkCampaign(s);
        return;
_L2:
        if (!StringUtils.isNullOrEmpty(trackingparams.getCmpid()))
        {
            s = trackingparams.getCmpid();
        } else
        if (!StringUtils.isNullOrEmpty(trackingparams.getSemcmpid()))
        {
            s = trackingparams.getSemcmpid();
        } else
        if (!StringUtils.isNullOrEmpty(trackingparams.getDisp()))
        {
            s = trackingparams.getDisp();
        } else
        {
            boolean flag = StringUtils.isNullOrEmpty(trackingparams.getOcmpid());
            s = null;
            if (!flag)
            {
                s = trackingparams.getOcmpid();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
