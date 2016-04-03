// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement.actionloader;

import android.app.Activity;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.customwidget.WidgetAction;
import com.flipkart.android.response.customwidgetitemvalue.Action;
import com.flipkart.android.response.customwidgetitemvalue.TrackingParams;
import com.flipkart.android.urlmanagement.AppAction;
import com.flipkart.android.urlmanagement.AppParams;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.urlmanagement.actionloader:
//            AppActionLoader

public class OpenEncodedUrlExternalActionLoader extends AppActionLoader
{

    private static String a = "url";
    private static String b = "otracker";

    public OpenEncodedUrlExternalActionLoader(AppParams appparams, Activity activity)
    {
        super(appparams, activity);
    }

    public void load()
    {
        String s = (String)getQueryParams().get(a);
        String s1;
        for (s1 = (String)getQueryParams().get(b); StringUtils.isNullOrEmpty(s) || !(activity instanceof HomeFragmentHolderActivity);)
        {
            return;
        }

        HomeFragmentHolderActivity homefragmentholderactivity = (HomeFragmentHolderActivity)activity;
        Action action = new Action();
        action.setScreenType(AppAction.openUrlExternal.toString());
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        linkedhashmap.put("url", s);
        action.setParams(linkedhashmap);
        if (!StringUtils.isNullOrEmpty(s1))
        {
            TrackingParams trackingparams = new TrackingParams();
            trackingparams.setPageType("webView");
            trackingparams.setOtracker(s1);
            action.setTracking(trackingparams);
        }
        WidgetAction.performAction(action, homefragmentholderactivity, PageTypeUtils.None);
    }

}
