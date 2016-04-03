// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement.actionloader;

import android.app.Activity;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.customwidget.WidgetAction;
import com.flipkart.android.log.ApiLogger;
import com.flipkart.android.log.LoggerTag;
import com.flipkart.android.response.customwidgetitemvalue.Action;
import com.flipkart.android.urlmanagement.AppAction;
import com.flipkart.android.urlmanagement.AppParams;
import com.flipkart.android.utils.PageTypeUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

// Referenced classes of package com.flipkart.android.urlmanagement.actionloader:
//            AppActionLoader

public class DefaultJsonActionLoader extends AppActionLoader
{

    public DefaultJsonActionLoader(AppParams appparams, Activity activity)
    {
        super(appparams, activity);
    }

    public void load()
    {
        Action action = new Action();
        Map map;
        HashMap hashmap;
        Iterator iterator;
        action.setScreenType(params.getAction().toString());
        map = getQueryParams();
        hashmap = new HashMap();
        iterator = map.keySet().iterator();
_L3:
        String s;
        String s1;
        JSONArray jsonarray;
        String as[];
        int i;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_214;
        }
        s = (String)iterator.next();
        s1 = (String)map.get(s);
        if (!s1.contains(","))
        {
            break MISSING_BLOCK_LABEL_199;
        }
        jsonarray = new JSONArray();
        as = s1.split(",");
        i = as.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        jsonarray.put(as[j]);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        hashmap.put(s, jsonarray);
          goto _L3
        try
        {
            action.setParams(hashmap);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            ApiLogger.logException(LoggerTag.Webview, "Error while parsing app url", exception, null);
        }
        if (activity instanceof HomeFragmentHolderActivity)
        {
            WidgetAction.performAction(action, (HomeFragmentHolderActivity)activity, PageTypeUtils.None);
        }
        return;
        hashmap.put(s, s1);
          goto _L3
    }
}
