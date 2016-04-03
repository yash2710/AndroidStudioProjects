// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import android.os.AsyncTask;
import com.android.volley.RequestQueue;
import com.flipkart.android.DB.ComponentWidgetData;
import com.flipkart.android.DB.ComponentWidgetDataDao;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.param.ComponentDataParams;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetData;
import com.flipkart.android.utils.GsonUtils;
import com.flipkart.android.volley.request.component.ComponentDataRequest;
import com.flipkart.android.volley.request.component.params.ComponentDataParam;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.flipkart.android.datahandler:
//            ComponentDataDataHandler

final class e extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private Map a;
    private ArrayList b;
    private ComponentDataDataHandler c;

    e(ComponentDataDataHandler componentdatadatahandler)
    {
        c = componentdatadatahandler;
        super();
        a = new HashMap();
        b = new ArrayList();
    }

    public void _nr_setTrace(Trace trace)
    {
        try
        {
            _nr_trace = trace;
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "e#doInBackground", null);
_L1:
        ArrayList arraylist = doInBackground((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return arraylist;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "e#doInBackground", null);
          goto _L1
    }

    protected final transient ArrayList doInBackground(Void avoid[])
    {
        ComponentWidgetDataDao componentwidgetdatadao = new ComponentWidgetDataDao(FlipkartApplication.getAppContext());
        long l = System.currentTimeMillis();
        ComponentDataParams acomponentdataparams[] = ComponentDataDataHandler.a(c);
        int i = acomponentdataparams.length;
        int j = 0;
        while (j < i) 
        {
            ComponentDataParams componentdataparams = acomponentdataparams[j];
            ComponentWidgetData componentwidgetdata = componentwidgetdatadao.getComponentWidgetDataById(componentdataparams.getScreenName(), componentdataparams.getDataId());
            if (componentwidgetdata != null)
            {
                if (l - componentwidgetdata.getLastUpdated() < componentdataparams.getTtl())
                {
                    WidgetData widgetdata1 = (WidgetData)GsonUtils.getResponse(com/flipkart/android/response/component/data/WidgetData, componentwidgetdata.getResponse(), true);
                    a.put(componentdataparams.getDataId(), widgetdata1);
                    FkLogger.debug("ViewUpdate", (new StringBuilder("within ttl items is ")).append(widgetdata1.getType()).toString());
                } else
                {
                    WidgetData widgetdata = (WidgetData)GsonUtils.getResponse(com/flipkart/android/response/component/data/WidgetData, componentwidgetdata.getResponse(), true);
                    FkLogger.debug("ViewUpdate", (new StringBuilder("old items is ")).append(widgetdata.getType()).toString());
                    componentdataparams.setHashCode(widgetdata.getDataId());
                    b.add(componentdataparams);
                    a.put(componentdataparams.getDataId(), widgetdata);
                }
            } else
            {
                b.add(componentdataparams);
            }
            j++;
        }
        FkLogger.debug("ViewUpdate", (new StringBuilder("pending items is ")).append(b).toString());
        return b;
    }

    protected final volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "e#onPostExecute", null);
_L1:
        onPostExecute((ArrayList)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "e#onPostExecute", null);
          goto _L1
    }

    protected final void onPostExecute(ArrayList arraylist)
    {
        ComponentDataParam componentdataparam;
        String as[];
        int ai[];
        Iterator iterator;
        if (a.size() != 0)
        {
            if (arraylist.size() == 0)
            {
                c.onComponentDataResponseReceived(a, false);
            } else
            {
                c.onComponentDataResponseReceived(a, true);
            }
        }
        componentdataparam = new ComponentDataParam();
        as = new String[arraylist.size()];
        ai = new int[arraylist.size()];
        iterator = arraylist.iterator();
        for (int i = 0; iterator.hasNext(); i++)
        {
            ComponentDataParams componentdataparams = (ComponentDataParams)iterator.next();
            as[i] = componentdataparams.getDataId();
            ai[i] = componentdataparams.getHashCode();
        }

        componentdataparam.setScreenName(ComponentDataDataHandler.a(c)[0].getScreenName());
        componentdataparam.setHashCodes(ai);
        componentdataparam.setWidgets(as);
        if (as.length > 0)
        {
            ComponentDataRequest componentdatarequest = new ComponentDataRequest(componentdataparam, c.listner, c.errorListner, ComponentDataDataHandler.b(c), new AnalyticData(c.requestId, c.omnitureData));
            FlipkartApplication.getRequestQueue().add(componentdatarequest);
        }
    }
}
