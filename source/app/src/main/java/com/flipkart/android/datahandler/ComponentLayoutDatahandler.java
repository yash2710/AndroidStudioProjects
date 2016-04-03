// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import android.os.AsyncTask;
import com.android.volley.RequestQueue;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.param.CompoentParams;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.filters.ComponentLayoutFilter;
import com.flipkart.android.volley.request.component.ComponentLayoutRequest;
import com.flipkart.android.volley.request.component.params.ComponentParam;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.Map;
import java.util.UUID;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler, f

public abstract class ComponentLayoutDatahandler extends BaseVDataHandler
{

    private String a;
    public String omnitureData;
    public String pageTrackingRequestId;

    public ComponentLayoutDatahandler()
    {
        a = null;
        a = UUID.randomUUID().toString();
        requestFilter = new ComponentLayoutFilter(a);
    }

    static void a(ComponentLayoutDatahandler componentlayoutdatahandler, CompoentParams compoentparams, String s, String s1)
    {
        componentlayoutdatahandler.pageTrackingRequestId = s;
        componentlayoutdatahandler.omnitureData = s1;
        ComponentLayoutRequest componentlayoutrequest = new ComponentLayoutRequest(new ComponentParam(compoentparams.getScreenName(), compoentparams.getScreenId(), compoentparams.getLastUpdated()), componentlayoutdatahandler.listner, componentlayoutdatahandler.errorListner, componentlayoutdatahandler.a, new AnalyticData(s, s1));
        componentlayoutdatahandler.request = componentlayoutrequest;
        FlipkartApplication.getRequestQueue().add(componentlayoutrequest);
    }

    public void errorReceived(int i, int j, String s)
    {
        super.errorReceived(i, j, s);
    }

    public void getComponentLayoutInfo(CompoentParams compoentparams, String s, String s1)
    {
        pageTrackingRequestId = s;
        omnitureData = s1;
        f f1 = new f(this);
        CompoentParams acompoentparams[] = {
            compoentparams
        };
        if (!(f1 instanceof AsyncTask))
        {
            f1.execute(acompoentparams);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)f1, acompoentparams);
            return;
        }
    }

    public abstract void onComponentLayoutResponseReceived(Map map, boolean flag);

    public volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((Map)obj, flag);
    }

    public void resultReceived(Map map, boolean flag)
    {
        onComponentLayoutResponseReceived(map, false);
    }
}
